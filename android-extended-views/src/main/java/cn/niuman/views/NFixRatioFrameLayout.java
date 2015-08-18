/*
 * Copyright (c) 1997-2015 Niuman. All rights reserved.
 */
package cn.niuman.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import cn.niuman.androidextendedviews.R;

/**
 * FragmentLayout with fix width/heigth ratio
 *
 * @author Niuman
 * @since 2014-08-27
 */
public class NFixRatioFrameLayout extends FrameLayout {

    private static final String TAG = NFixRatioFrameLayout.class.getSimpleName();

    private static final int FIX_WIDTH = 1;
    private static final int FIX_HEIGHT = 2;
    private static final int FIT_XY = 3;

    private int mScaleType;
    private float mRatio = 0;

    public NFixRatioFrameLayout(Context context) {
        super(context);
    }

    public NFixRatioFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public NFixRatioFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {

        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.NFixRatioFrameLayout, defStyle, 0);
        mRatio = a.getFloat(R.styleable.NFixRatioFrameLayout_frfRatio, 0);
        mScaleType = a.getInt(R.styleable.NFixRatioFrameLayout_frfScaleType, 1);
        a.recycle();
    }

    public void setRatio(float ratio) {
        if (mRatio != ratio) {
            mRatio = ratio;
            invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mRatio > 0) {
            setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
            switch (mScaleType) {
                case FIX_WIDTH:
                    widthMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), MeasureSpec.EXACTLY);
                    heightMeasureSpec = MeasureSpec.makeMeasureSpec((int)(MeasureSpec.getSize(widthMeasureSpec) * mRatio), MeasureSpec.EXACTLY);
                    break;
                case FIX_HEIGHT:
                    heightMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.EXACTLY);
                    widthMeasureSpec = MeasureSpec.makeMeasureSpec((int)(MeasureSpec.getSize(heightMeasureSpec) * mRatio), MeasureSpec.EXACTLY);
                    break;
                case FIT_XY:
                    int width = MeasureSpec.getSize(widthMeasureSpec);
                    int height = MeasureSpec.getSize(heightMeasureSpec);
                    float ratio = 1f * height / width;
                    if (ratio < mRatio) {
                        heightMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.EXACTLY);
                        widthMeasureSpec = MeasureSpec.makeMeasureSpec((int)(height / mRatio), MeasureSpec.EXACTLY);
                    } else {
                        widthMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), MeasureSpec.EXACTLY);
                        heightMeasureSpec = MeasureSpec.makeMeasureSpec((int)(width * mRatio), MeasureSpec.EXACTLY);
                    }
                    break;
            }
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
