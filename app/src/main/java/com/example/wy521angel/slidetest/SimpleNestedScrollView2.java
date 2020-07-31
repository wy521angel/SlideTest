package com.example.wy521angel.slidetest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import java.lang.reflect.Field;

public class SimpleNestedScrollView2 extends ScrollView {

    public SimpleNestedScrollView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getActionMasked() == MotionEvent.ACTION_DOWN)
            getParent().requestDisallowInterceptTouchEvent(true);

        if (ev.getActionMasked() == MotionEvent.ACTION_MOVE) {

            int offsetY = (int) ev.getY() - getField("mLastMotionY");
            if (Math.abs(offsetY) > getField("mTouchSlop")) {
                if ((offsetY > 0 && isScrollToTop()) || (offsetY < 0 && isScrollToBottom())) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private int getField(String name) {
        Field field = null;
        try {
            field = ScrollView.class.getDeclaredField(name);
            field.setAccessible(true); // 设置Java不检查权限。
            Log.e("SimpleNestedScrollView", "name:" + field.getInt(this));
            return field.getInt(this);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SimpleNestedScrollView", "name:" + e.getMessage());
        }
        Log.e("SimpleNestedScrollView", "name:" + (-1));
        return -1;
    }

    private boolean isScrollToTop() {
        return getScrollY() == 0;
    }

    private boolean isScrollToBottom() {
        return getScrollY() + getHeight() - getPaddingTop() - getPaddingBottom() == getChildAt(0).getHeight();
    }

    @Override
    public void scrollBy(int x, int y) {
        if ((y > 0 && isScrollToTop()) || (y < 0 && isScrollToBottom())) {
            ((View) getParent()).scrollBy(x, y);
        } else {
            super.scrollBy(x, y);
        }
    }
}
