package com.example.android_lesson.widgets.custom;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

public class NewCardView extends CardView {

    public static final String TAG = NewCardView.class.getSimpleName();

    public NewCardView(@NonNull Context context) {
        super(context);
    }

    public NewCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NewCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //点击事件到来的时候进行判断处理
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // 获取事件类型
        int actionMarked = ev.getActionMasked();
        Log.d(TAG, "dispatchTouchEvent: ======actionMarked:" + actionMarked);
        // 根据时间类型判断调用哪个方法来展示动画
        switch (actionMarked) {
            case MotionEvent.ACTION_DOWN: {
                Log.d(TAG, "dispatchTouchEvent: ========ACTION_DOWN");
                clickEvent();
                break;
            }
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "dispatchTouchEvent: ========ACTION_CANCEL");
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "dispatchTouchEvent: ========ACTION_UP");
                upEvent();
                break;
            default:
                break;
        }
        // 最后回调默认的事件分发方法即可，让子view分发
        boolean b = super.dispatchTouchEvent(ev);
        Log.d(TAG, "dispatchTouchEvent: =====" + b);
        return b;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean b = super.onInterceptTouchEvent(ev);
        Log.d(TAG, "onInterceptTouchEvent: =======" + b);
        return b;
    }

    //手指按下的时候触发的事件;大小高度变小，透明度减少
    private void clickEvent() {
        setCardElevation(4);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(this, "scaleX", 1, 0.97f),
                ObjectAnimator.ofFloat(this, "scaleY", 1, 0.97f),
                ObjectAnimator.ofFloat(this, "alpha", 1, 0.9f)
        );
        set.setDuration(100).start();
    }

    //手指抬起的时候触发的事件；大小高度恢复，透明度恢复
    private void upEvent() {
        setCardElevation(getCardElevation());
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(this, "scaleX", 0.97f, 1),
                ObjectAnimator.ofFloat(this, "scaleY", 0.97f, 1),
                ObjectAnimator.ofFloat(this, "alpha", 0.9f, 1)
        );
        set.setDuration(100).start();
    }
}

