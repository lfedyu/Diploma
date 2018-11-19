package com.fedyushko.lilia.p0031_firstprogect.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;


public class RoundImageView extends AppCompatImageView {

    private int mUsableRadius;

    private int mW;
    private int mH;

    private Paint mPaint;
    private Bitmap mBitmap;
    private Canvas mBitmapCanvas;


    public RoundImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        init();
    }

    private void init()
    {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        if (getDrawable() == null || mBitmap == null)
        {
            return;
        }
        mBitmapCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        super.onDraw(mBitmapCanvas);

        canvas.drawColor(Color.TRANSPARENT);
        canvas.drawCircle(mW/2, mH/2, mUsableRadius, mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        mW = getMeasuredWidth();
        mH = getMeasuredHeight();

        mUsableRadius = mW == mH ? mW / 2 : Math.min(mH, mW)/2;

        updateImageSize();
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);

        updateImageSize();
    }

    private void updateImageSize()
    {
        if (getDrawable() != null && 0 != mW && 0 != mH
                && (mBitmap == null || mBitmap.getWidth() != mW || mBitmap.getHeight() != mH))
        {
            if (mBitmap != null)
            {
                mBitmap.recycle();
            }
            mBitmap = Bitmap.createBitmap(mW, mH, Bitmap.Config.ARGB_4444);
            BitmapShader shader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            mPaint.setShader(shader);
            mBitmapCanvas = new Canvas(mBitmap);
        }
    }
}