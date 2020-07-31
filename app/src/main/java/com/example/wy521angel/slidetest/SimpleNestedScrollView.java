package com.example.wy521angel.slidetest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

import java.lang.reflect.Field;

public class SimpleNestedScrollView extends ScrollView {

    private boolean isFirstIntercept = true;
    private boolean isNeedRequestDisallowIntercept = true;

    public SimpleNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getActionMasked() == MotionEvent.ACTION_DOWN) {
            isFirstIntercept = true;
        }
        boolean result = super.onInterceptTouchEvent(ev);
        if (result && isFirstIntercept) {
            isFirstIntercept = false;
            return false;
        }
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getActionMasked() == MotionEvent.ACTION_DOWN) isNeedRequestDisallowIntercept = true;
        if (ev.getActionMasked() == MotionEvent.ACTION_MOVE) {
            if (!isNeedRequestDisallowIntercept) {
                return false;
            } else {
                int offsetY = (int) ev.getY() - getField("mLastMotionY");
                if (Math.abs(offsetY) > getField("mTouchSlop")) { // 滑动距离足够判断滑动方向是上还是下后
                    // 判断自己是否能在对应滑动方向上进行滑动（不能则返回false）
                    if ((offsetY > 0 && isScrollToTop()) || (offsetY < 0 && isScrollToBottom())) {
                        isNeedRequestDisallowIntercept = false;
                        return false;
                    }
                }
            }
        }
        return super.onTouchEvent(ev);
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

}
