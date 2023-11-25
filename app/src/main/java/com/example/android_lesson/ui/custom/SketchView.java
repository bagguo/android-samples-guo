package com.example.android_lesson.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.android_lesson.R;

import androidx.annotation.Nullable;

public class SketchView extends View {

    private int custom_size;
    private int custom_background;
    private Paint mPaint;

    private final int SIZE = 15;

    private final int DEFAULT_COLOR = Color.BLUE;

    public SketchView(Context context) {
        this(context, null);
    }

    public SketchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SketchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SketchView, defStyleAttr, R.style.Theme_AppTheme);
        custom_size = a.getDimensionPixelSize(R.styleable.SketchView_size, SIZE);
        custom_background = a.getColor(R.styleable.SketchView_background_color, DEFAULT_COLOR);

        a.recycle();

        init();
    }

    public void init() {
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.black));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //draw a circle
        canvas.drawCircle(200f, 200f, 70f, mPaint);
    }
}
