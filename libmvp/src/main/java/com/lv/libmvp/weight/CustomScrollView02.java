package com.lv.libmvp.weight;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * 作者：created by albert on 2019-10-10 16:27
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class CustomScrollView02 extends ScrollView {

    private View childView;

    public CustomScrollView02(Context context) {
        super(context);
    }

    public CustomScrollView02(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView02(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            childView = getChildAt(0);
        }
    }

    private int lastY;//上次Y轴方向操作的位置
    private Rect normal = new Rect();//用于记录临界状态的左右上下
    private boolean isFinshAnimation = true;
    private int lastX, downY, downX;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean isIntercept = false;
        int eventX = (int) ev.getX();
        int eventY = (int) ev.getY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = downX = eventX;
                lastY = downY = eventY;
                break;
            case MotionEvent.ACTION_MOVE:
                int absX = Math.abs(eventX - downX);
                int absY = Math.abs(eventY - downY);
                if (absY > absX && absY >= 10) {
                    isIntercept = true;
                }
                lastX = eventX;
                lastY = eventY;
                break;
        }


        return isIntercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (childView == null || !isFinshAnimation) {
            return super.onTouchEvent(ev);
        }
        int eventY = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = eventY;
                break;
            case MotionEvent.ACTION_MOVE:
                int dy = eventY - lastY;
                if (isNeedMove()) {
                    if (normal.isEmpty()) {
                        normal.set(childView.getLeft(), childView.getTop(), childView.getRight(), childView.getBottom());
                    }
                    childView.layout(childView.getLeft(), childView.getTop() + dy / 2, childView.getRight(), childView.getBottom() + dy / 2);
                }
                lastY = eventY; //重新赋值
                break;

            case MotionEvent.ACTION_UP:
                if (isNeedAnimation()) {
                    int translateY = childView.getBottom() - normal.bottom;
                    TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, -translateY);
                    translateAnimation.setDuration(200);

                    translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            isFinshAnimation = false;
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            isFinshAnimation = true;
                            childView.clearAnimation(); //清除动画
                            //重新布局，停留在最终的位置上
                            childView.layout(normal.left, normal.top, normal.right, normal.bottom);
                            //清除normal左、上、右、下的数据
                            normal.isEmpty();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    childView.setAnimation(translateAnimation);
                }
                break;
        }


        return super.onTouchEvent(ev);
    }


    //判断是否需要执行平移动画
    private boolean isNeedAnimation() {
        return !normal.isEmpty();
    }

    private boolean isNeedMove() {
        int childMeasuredHeight = childView.getMeasuredHeight();  //获取子视图的高度
        int scrollViewMeasuredHeight = this.getMeasuredHeight();  //获取布局的高度

        Log.e("TAG","childMeasuredHeight = " + childMeasuredHeight);
        Log.e("TAG","scrollViewMeasuredHeight = " + scrollViewMeasuredHeight);

        int dy = childMeasuredHeight - scrollViewMeasuredHeight; //dy >=0

        int scrollY = this.getScrollY(); //获取用户在y轴方向上的偏移量（上加"+"，下减"-"）
        if (scrollY <= 0 || scrollY >=dy){
            return true; //按照我们自定义的scrollView的方式处理
        }
        //其他处在临界范围内的，返回false,即表示，仍按照scrollView的方式处理
        return false;
    }
}
