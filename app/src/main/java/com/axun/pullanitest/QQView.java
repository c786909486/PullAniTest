package com.axun.pullanitest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hz-java on 2018/5/28.
 */

public class QQView extends View {

    private Paint paint;//画笔
    private Paint textPaint;//.
    private int textColor = Color.WHITE;
    private int color = Color.BLUE;
    private int width;
    private int height = 0;

    private Context context;

    public void setHeight(int height) {
        this.height = height;
        postInvalidate();
    }

    public QQView(Context context) {
        this(context,null);
    }

    public QQView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public QQView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    private void initPaint(){
        paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        width = getWidth();
        textPaint = new Paint();
        textPaint.setColor(textColor);
        textPaint.setTextSize(DensityUtils.sp2px(context,15f));
        textPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initPaint();
        RectF rectF = new RectF(0,-height,width,height);
        canvas.drawArc(rectF,0,180,true,paint);
        String text = "下拉刷新";
        canvas.drawText(text,width/2-textPaint.measureText(text)/2,height-DensityUtils.dp2px(context,15),textPaint);
    }


}
