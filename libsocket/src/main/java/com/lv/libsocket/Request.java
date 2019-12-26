package com.lv.libsocket;

import com.google.gson.annotations.SerializedName;

public class Request<T> {
    @SerializedName("action")
    private String action;

    @SerializedName("code")
    private int reqEvent;

    @SerializedName("seq_id")
    private long seqId;

    @SerializedName("req")
    private T req;

    private transient int reqCount;

    public Request(String action, int reqEvent, long seqId, T req, int reqCount) {
        this.action = action;
        this.reqEvent = reqEvent;
        this.seqId = seqId;
        this.req = req;
        this.reqCount = reqCount;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getReqEvent() {
        return reqEvent;
    }

    public void setReqEvent(int reqEvent) {
        this.reqEvent = reqEvent;
    }

    public long getSeqId() {
        return seqId;
    }

    public void setSeqId(long seqId) {
        this.seqId = seqId;
    }

    public T getReq() {
        return req;
    }

    public void setReq(T req) {
        this.req = req;
    }

    public int getReqCount() {
        return reqCount;
    }

    public void setReqCount(int reqCount) {
        this.reqCount = reqCount;
    }


    //这里还有各个参数对应get、set方法,为节省篇幅省略了

    public static class Builder<T> {
        private String action;
        private int reqEvent;
        private long seqId;
        private T req;
        private int reqCount;

        public Builder action(String action) {
            this.action = action;
            return this;
        }

        public Builder reqEvent(int reqEvent) {
            this.reqEvent = reqEvent;
            return this;
        }

        public Builder seqId(long seqId) {
            this.seqId = seqId;
            return this;
        }

        public Builder req(T req) {
            this.req = req;
            return this;
        }

        public Builder reqCount(int reqCount) {
            this.reqCount = reqCount;
            return this;
        }

        public Request build() {
            return new Request<T>(action, reqEvent, seqId, req, reqCount);
        }

    }
}
