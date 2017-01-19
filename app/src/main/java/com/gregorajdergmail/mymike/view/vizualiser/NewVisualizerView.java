package com.gregorajdergmail.mymike.view.vizualiser;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.gregorajdergmail.mymike.util.Log;

public class NewVisualizerView extends View {
    public static int mSpectrumNum = 48;
    private Rect mRect = new Rect();
    private Paint mForePaint = new Paint();
    private float[] floats;
    private float[] mPoints = new float[mSpectrumNum * 4+4];
    int i=0;

    public NewVisualizerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NewVisualizerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public NewVisualizerView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mForePaint.setStrokeWidth(8f);
        mForePaint.setAntiAlias(true);
        mForePaint.setColor(Color.rgb(0, 128, 255));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (floats == null)
            return;
        mRect.set(0, 0, getWidth(), getHeight());
        final int baseX = mRect.width() / mSpectrumNum;
        final int height = mRect.height();

        for (int i = 0; i < mSpectrumNum; i++) {
            mPoints[i * 4] = floats[i*4] * baseX + baseX / 2;
            mPoints[i * 4 + 1] = height;
            mPoints[i * 4 + 2] = mPoints[i * 4];
            mPoints[i * 4 + 3] = height - floats[i * 4 + 3];
        }

        canvas.drawLines(mPoints, mForePaint);
    }

    public void update(float[] floats) {
        this.floats = floats;
        invalidate();
    }
}


