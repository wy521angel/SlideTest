package com.example.wy521angel.slidetest.event;

import android.view.MotionEvent;

public class MView {

    public ViewParent parent = null;

    public boolean dispatch(MotionEvent ev) {
        // 源码里没有这么直接但区别不大，主要会考虑是否设置了 onTouchListener 和是否 enable
        return onTouch(ev);
    }

    public boolean onTouch(MotionEvent ev) {
        return false;
    }
}
