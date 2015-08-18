/*
 * Copyright (c) 1997-2015 Niuman. All rights reserved.
 */

package cn.niuman.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import cn.niuman.utils.Utils;


/**
 * ImageView with xfer mode, drawing image in background area
 *
 * @author LR-Zhi
 * @since 2015/7/27.
 */
public class NXferImageView extends ImageView {

    private static final String TAG = NXferImageView.class.getSimpleName();

    private Bitmap mBitmap;

    private NinePatchDrawable mMaskDrawable;
    private PorterDuffXfermode mMaskXferMode;

    private Paint imagePaint;

    private int mCoverColor;

    public NXferImageView(Context context) {
        super(context);

        init();
    }

    public NXferImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NXferImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mMaskDrawable = (NinePatchDrawable)getBackground();
        mMaskXferMode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);

        imagePaint = new Paint();
        imagePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        mBitmap = bm;
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        mBitmap = Utils.getBitmapFromDrawable(drawable);
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        mBitmap = Utils.getBitmapFromDrawable(getDrawable());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mMaskDrawable != null) {
            mMaskDrawable.setBounds(0, 0, getWidth(), getHeight());
            mMaskDrawable.getPaint().setXfermode(mMaskXferMode);
            mMaskDrawable.draw(canvas);
        }

        if (mCoverColor != 0) {
            canvas.drawColor(mCoverColor, PorterDuff.Mode.DST_OVER);
        }

        canvas.save();
        canvas.concat(getImageMatrix());
        canvas.drawBitmap(mBitmap, 0, 0, imagePaint);
        canvas.restore();
    }

    /**
     * Draw cover over the image
     * @param color color of the cover, hide with 0
     */
    public void setCoverColor(int color) {
        mCoverColor = color;
        invalidate();
    }
}
