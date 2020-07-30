package com.example.wy521angel.slidetest.event;

import android.view.MotionEvent;

public class MViewGroup extends MView implements ViewParent {
    private MView child;
    private boolean isChildNeedEvent = false;
    private boolean isSelfNeedEvent = false;
    private boolean isDisallowIntercept = false;

    public MViewGroup(MView child) {
        this.child = child;
        // 这里只是示意，实际中不建议这么写，会造成提前发布未构造完成的实例
        child.parent = this;
    }

    @Override
    public boolean dispatch(MotionEvent ev) {
        boolean handled = false;

        if (ev.getActionMasked() == MotionEvent.ACTION_DOWN) {
            clearStatus();

            // add isDisallowIntercept
            if (!isDisallowIntercept && onIntercept(ev)) {
                isSelfNeedEvent = true;
                handled = onTouch(ev);
            } else {
                handled = child.dispatch(ev);
                if (handled) isChildNeedEvent = true;

                if (!handled) {
                    handled = onTouch(ev);
                    if (handled) isSelfNeedEvent = true;
                }
            }
        } else {
            // 这里 isSelfNeedEvent 条件判断应该放在 isChildNeedEvent 前面
            // 因为两个都为真的情况只能是自己之后通过 onIntercept 抢了控制权，那这之后的控制权就不会去 child 那儿了
            if (isSelfNeedEvent) {
                handled = onTouch(ev);
            } else if (isChildNeedEvent) {
                // add isDisallowIntercept
                if (!isDisallowIntercept && onIntercept(ev)) {
                    isSelfNeedEvent = true;

                    // add cancel
                    MotionEvent cancel = MotionEvent.obtain(ev);
                    cancel.setAction(MotionEvent.ACTION_CANCEL);
                    handled = child.dispatch(cancel);
                    cancel.recycle();
                } else {
                    handled = child.dispatch(ev);
                }
            }
            // 这里不用再 else 了，因为如果 isSelfNeedEvent 和 isChildNeedEvent 都不为 true，上面不会再发事件下来了
        }

        if (ev.getActionMasked() == MotionEvent.ACTION_UP ||
                ev.getActionMasked() == MotionEvent.ACTION_CANCEL) {
            clearStatus();
        }

        return handled;
    }

    private void clearStatus() {
        isChildNeedEvent = false;
        isSelfNeedEvent = false;
        isDisallowIntercept = false;
    }

    @Override
    public boolean onTouch(MotionEvent ev) {
        return false;
    }

    public boolean onIntercept(MotionEvent ev) {
        return false;
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean isDisallowIntercept) {
        this.isDisallowIntercept = isDisallowIntercept;
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(isDisallowIntercept);
        }
    }
}
