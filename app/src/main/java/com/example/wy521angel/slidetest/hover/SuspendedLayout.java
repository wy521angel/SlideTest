package com.example.wy521angel.slidetest.hover;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.math.MathUtils;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;

public class SuspendedLayout extends LinearLayout implements NestedScrollingParent3 {
    private NestedScrollingParentHelper mParentHelper;

    public SuspendedLayout(Context context) {
        super(context);
        initView();
    }

    public SuspendedLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mParentHelper = new NestedScrollingParentHelper(this);
    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {
        onNestedScrollInternal(dyUnconsumed, type, consumed);
    }

    private void onNestedScrollInternal(int dyUnconsumed, int type, int[] consumed) {
        if (dyUnconsumed < 0) scrollDown(dyUnconsumed, consumed);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {
        mParentHelper.onNestedScrollAccepted(child, target, axes, type);
    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {
        mParentHelper.onStopNestedScroll(target, type);
    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        onNestedScrollInternal(dyUnconsumed, type, null);
    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        if (dy > 0) scrollUp(dy, consumed);
    }


    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return onStartNestedScroll(child, target, nestedScrollAxes, ViewCompat.TYPE_TOUCH);
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        onNestedScrollAccepted(child, target, axes, ViewCompat.TYPE_TOUCH);
    }

    @Override
    public void onStopNestedScroll(View child) {
        onStopNestedScroll(child, ViewCompat.TYPE_TOUCH);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        onNestedScrollInternal(dyUnconsumed, ViewCompat.TYPE_TOUCH, null);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        onNestedPreScroll(target, dx, dy, consumed, ViewCompat.TYPE_TOUCH);
    }

    @Override
    public int getNestedScrollAxes() {
        return mParentHelper.getNestedScrollAxes();
    }

    private void scrollDown(int dyUnconsumed, int[] consumed) {

        int oldScrollY = getScrollY();
        scrollBy(0, dyUnconsumed);
        int myConsumed = getScrollY() - oldScrollY;

        if (consumed != null) {
            consumed[1] += myConsumed;
        }
    }

    private void scrollUp(int dy, int[] consumed) {
        int oldScrollY = getScrollY();
        scrollBy(0, dy);
        consumed[1] = getScrollY() - oldScrollY;
    }

    @Override
    public void scrollTo(int x, int y) {
        int validY = MathUtils.clamp(y, 0, headerHeight);
        super.scrollTo(x, validY);
    }

    // 简单把第一个 child 作为 header
    private int headerHeight = 0;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getChildCount() > 0) {
            View headView = getChildAt(0);
            measureChildWithMargins(headView, widthMeasureSpec, 0, MeasureSpec.UNSPECIFIED, 0);
            headerHeight = headView.getMeasuredHeight();
            super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec) + headerHeight, MeasureSpec.EXACTLY));
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
