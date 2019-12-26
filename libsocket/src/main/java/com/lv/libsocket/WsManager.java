package com.lv.libsocket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;

import com.google.gson.Gson;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;


/**
 * 作者：created by albert on 2019/4/9 11:20
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class WsManager {

    private static WsManager mInstance;
    private final String TAG = this.getClass().getSimpleName();
    private AtomicLong seqId = new AtomicLong(SystemClock.uptimeMillis());//每个请求的唯一标识
    private Context mContext;
    /**
     * WebSocket config
     */
    private static final int FRAME_QUEUE_SIZE = 5;
    private static final int CONNECT_TIMEOUT = 5 * 1000;
    private static final long HEARTBEAT_INTERVAL = 6 * 1000;//心跳间隔
    private String apiUrl;

    private WsStatus mStatus;
    private WebSocket ws;
    private WsListener mListener;

    private int reconnectCount = 0;//重连次数
    private long minInterval = 5 * 1000;//重连最小时间间隔
    private long maxInterval = 60 * 1000;//重连最大时间间隔
    private Handler mHandler = new Handler();

    private String token;

    public WsManager(Context context, String token) {
        mContext = context;
        this.token = token;
        apiUrl = "ws://echo.websocket.org" ;
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static WsManager getInstance(Context mContext, String token) {
        if (mInstance == null) {
            synchronized (WsManager.class) {
                if (mInstance == null) {
                    mInstance = new WsManager(mContext, token);
                }
            }
        }
        return mInstance;
    }

    /**
     * 心跳
     */
    private Runnable heartbeatTask = new Runnable() {
        @Override
        public void run() {
            sendReq(Action.HEARTBEAT, null);
            mHandler.postDelayed(heartbeatTask, HEARTBEAT_INTERVAL);
        }
    };

    /**
     * 重新连接的runnable
     */
    private Runnable mReconnectTask = () -> {
        try {
            Log.i(TAG, "mReconnectTask ");
            mListener = new WsListener();
            ws = new WebSocketFactory().createSocket(apiUrl, CONNECT_TIMEOUT)
                    .setFrameQueueSize(FRAME_QUEUE_SIZE)
                    .setMissingCloseFrameAllowed(false)
                    .addListener(mListener)
                    .addHeader("authorization", token)
                    .connectAsynchronously();

        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "mReconnectTask: " + e.toString());
        }
    };

    private void sendReq(Action action, Object o) {
        sendReq(action, o, 1);
    }

    @SuppressWarnings("unchecked")
    private <T> void sendReq(Action action, T req, int reqCount) {

        Request request = new Request.Builder<T>()
                .action(action.getAction())
                .reqEvent(action.getReqEvent())
                .seqId(seqId.getAndIncrement())
                .reqCount(reqCount)
                .req(req)
                .build();

        Log.d(TAG, "sendReq: " + new Gson().toJson(request));
        ws.sendText(new Gson().toJson(request));
    }

    /**
     * 初始化
     */
    public void init() {
        try {
            reconnectCount = 0;
            mListener = new WsListener();
            ws = new WebSocketFactory().createSocket(apiUrl, CONNECT_TIMEOUT)
                    .setFrameQueueSize(FRAME_QUEUE_SIZE)//设置帧队列最大值为5
                    .setMissingCloseFrameAllowed(false)//设置不允许服务端关闭连接却未发送关闭帧
                    .addListener(mListener)//添加回调监听
                    .addHeader("authorization", token)
                    .connectAsynchronously();//异步连接
            setStatus(WsStatus.CONNECTING);
            Log.d(TAG, "init: 第一次连接");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 断开连接
     */
    public void disconnect() {
        if (ws != null)
            ws.disconnect();
        mHandler.removeCallbacks(heartbeatTask);

    }

    /**
     * websocket连接监听
     */
    class WsListener extends WebSocketAdapter {

        @Override
        public void onTextMessage(WebSocket websocket, String text) throws Exception {
            super.onTextMessage(websocket, text);
            Log.i(TAG, "onTextMessage: 接收到的推送消息" + text);
            parseNotifyTransaction(text);
        }

        @Override
        public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
            super.onConnected(websocket, headers);
            Log.i(TAG, "onConnected: 连接成功");
            setStatus(WsStatus.CONNECT_SUCCESS);
            cancleReconnect();
            startHeartbet();
        }

        @Override
        public void onConnectError(WebSocket websocket, WebSocketException exception) throws Exception {
            super.onConnectError(websocket, exception);
            Log.i(TAG, "onConnectError: 连接异常");
            setStatus(WsStatus.CONNECT_FAIL);
            reconnect();
        }

        @Override
        public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) throws Exception {
            super.onDisconnected(websocket, serverCloseFrame, clientCloseFrame, closedByServer);
            Log.i(TAG, "onDisconnected: 断开连接");
            setStatus(WsStatus.CONNECT_FAIL);
            reconnect();
        }
    }


    /**
     * 开始重连
     */
    public void reconnect() {

        Log.i(TAG, "reconnect: 重新连接");
        if (ws != null && !ws.isOpen() && getStatus() != WsStatus.CONNECTING) {
            reconnectCount++;
            Log.i(TAG, "reconnect: 重新连接01" + reconnectCount);

            setStatus(WsStatus.CONNECTING);
            cancelHeartbeat();
            long reconnectTime = minInterval;
            if (reconnectCount > 3) {
                Log.i(TAG, "重新连接大于3次，取消重连，并且关闭开关");
                long temp = minInterval * (reconnectCount - 2);
                reconnectTime = temp > maxInterval ? maxInterval : temp;
            } else {
                Log.d(TAG, "准备开始第" + reconnectCount + "次重连,重连间隔" + reconnectTime * reconnectCount);
                mHandler.postDelayed(mReconnectTask, reconnectTime * reconnectCount);
            }


        }
    }

    /**
     * 取消心跳
     */
    private void cancelHeartbeat() {
        mHandler.removeCallbacks(heartbeatTask);
    }

    /**
     * 开启心跳
     */
    private void startHeartbet() {
        mHandler.postDelayed(heartbeatTask, HEARTBEAT_INTERVAL);
    }

    /**
     * 取消重连
     */
    private void cancleReconnect() {
        reconnectCount = 0;
        mHandler.removeCallbacks(mReconnectTask);
    }

    public WsStatus getStatus() {
        return mStatus;
    }

    public void setStatus(WsStatus status) {
        mStatus = status;
    }


    /**
     * @param text json 数据，根据json进行解析使用即可
     */
    private void parseNotifyTransaction(String text) {

    }

}
