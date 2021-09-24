package com.example.android_lesson.dispatcheventdemo.dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.LongDef;
import androidx.annotation.Nullable;

public class MyRootLinearLayout extends LinearLayout {

    public static final String TAG = MyRootLinearLayout.class.getSimpleName();

    public MyRootLinearLayout(Context context) {
        super(context);
    }

    public MyRootLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRootLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyRootLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean b = true;
        Log.d(TAG, "onInterceptTouchEvent: =========" + b);
        return b;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);
        Log.d(TAG, "dispatchTouchEvent: =========" + b);
        return b;
    }



}
