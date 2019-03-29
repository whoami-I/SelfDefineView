package com.example.selfdefineview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RadialGradient;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.view.View;

public class HeartBeatView extends View {
	private int radius;
	private Paint paint;
	private RadialGradient gradient;

	public HeartBeatView(Context context) {
		this(context, null);
	}

	public HeartBeatView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public HeartBeatView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		paint = new Paint();
	}

	public void setRadius(int a) {
		radius = a;
		invalidate();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int height = MeasureSpec.getSize(heightMeasureSpec);
		int width = MeasureSpec.getSize(widthMeasureSpec);
		width = width < height ? width : height;
		setMeasuredDimension(width, width);
		System.out.println("onMeasure");
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);
		gradient = new RadialGradient(getWidth() / 2, getHeight() / 2, 300,
				Color.RED, Color.TRANSPARENT, TileMode.REPEAT);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		System.out.println("121");
		paint.setAntiAlias(true);
		paint.setShader(gradient);
		paint.setStrokeWidth(20);
		paint.setStyle(Style.STROKE);
		canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, paint);

	}
}
