/*
 * Copyright (c) 1997-2015 Niuman. All rights reserved.
 */
package cn.niuman.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import cn.niuman.androidextendedviews.R;


/**
 * Flow LinearLayout
 *
 * @author Niuman
 * @since 2014-10-20
 */
public class NFlowLinearLayout extends ViewGroup implements View.OnClickListener {

    private static final String TAG = NFlowLinearLayout.class.getSimpleName();

    private ListAdapter mAdapter;

    private int spaceVertical;
    private int spaceHorizontal;

    private OnFlowItemClickListener mItemClickListener;

    private final DataSetObserver mDataSetObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            updateChildren();
        }
    };


    public interface OnFlowItemClickListener {
        void onItemClick(View v);
    }

    public NFlowLinearLayout(Context context) {
        super(context);
    }

    public NFlowLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(attrs, 0);
    }

    public NFlowLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        parseAttributes(attrs, defStyle);
    }

    private void parseAttributes(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.NFlowLinearLayout, defStyle, 0);
        spaceVertical = a.getDimensionPixelSize(R.styleable.NFlowLinearLayout_fllSpaceVertical, 0);
        spaceHorizontal = a.getDimensionPixelSize(R.styleable.NFlowLinearLayout_fllSpaceHorizontal, 0);
        a.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        int positionX = getPaddingLeft();
        int positionY = getPaddingTop();
        int limitX = r - l - getPaddingRight();
        for (int i = 0; i < count; i++) {
            final View child = this.getChildAt(i);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            positionX += width;
            if (positionX > limitX) {
                positionX = getPaddingLeft() + width;
                positionY += height + spaceVertical;
            }
            child.layout(positionX - width, positionY, positionX, positionY + height);
            positionX += spaceHorizontal;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setAdapter(ListAdapter adapter) {
        this.mAdapter = adapter;
        mAdapter.registerDataSetObserver(mDataSetObserver);
        mDataSetObserver.onChanged();
    }

    private void updateChildren() {
        removeAllViews();
        for (int i = 0; i < mAdapter.getCount(); i++) {
            View view = mAdapter.getView(i, null, this);
            view.setOnClickListener(this);
            addView(view);
        }
        requestLayout();
    }

    public void setOnFlowItemClickListener(OnFlowItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (this.mItemClickListener != null) {
            this.mItemClickListener.onItemClick(v);
        }
    }
}
