package com.sxu.levellayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/*******************************************************************************
 * FileName: RefreshLayout
 * <p>
 * Description:
 * <p>
 * Author: juhg
 * <p>
 * Version: v1.0
 * <p>
 * Date: 17/2/14
 * <p>
 * Copyright: all rights reserved by zhinanmao.
 *******************************************************************************/
public class RefreshLayout extends FrameLayout {

    /**
     * 需要的资源：
     *
     * 下拉时的资源， 刷新中的资源， 刷新完成时的资源， 分别对应相应的加载方法（加载布局， 设置数据）
     * 上啦时同理；
     * 是否可下拉，是否可上拉， 手动设置刷新完成；下拉刷新监听，上拉加载监听。
     *
     */

    private OnRefreshListener mListener;

    private final int REFRESH_STATUS_NORMAL = 0;
    private final int REFRESH_STATUS_PULL_TO_DOWN = 1;
    private final int REFRESH_STATUS_PULL_TO_DOWN_REFRESHING = 2;
    private final int REFRESH_STATUS_PULL_TO_DOWN_SPRINGBACK = 3;
    private final int REFRESH_STATUS_PULL_TO_UP = 4;
    private final int REFRESH_STATUS_PULL_TO_UP_LOADING = 5;
    private final int REFRESH_STATUS_PULL_TO_UP_SPRINGBACK = 6;

    public RefreshLayout(Context context) {
        super(context);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void showPulltoDownView() {

    }

    protected void showPulltoDownRefreshingView() {

    }

    protected void showPulltoDownSpringbackView() {

    }

    protected void showPulltoUpView() {

    }

    protected void showPulltoUpLoadingView() {

    }

    protected void showPullUpSpringbackView() {

    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        this.mListener = listener;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int childCount = getChildCount();
        if (childCount > 0) {
            View childView = getChildAt(0);
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public interface OnRefreshListener {
        void onRefreshing();
        void onLoadMore();
    }
}
