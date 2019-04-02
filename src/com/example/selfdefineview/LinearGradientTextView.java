package com.example.selfdefineview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

public class LinearGradientTextView extends View {
	private int radius;
	private Paint paint;
	private LinearGradient gradient;
	private String text = "Hello wolrd";
	private Matrix localM = new Matrix();
	private int translate;

	public LinearGradientTextView(Context context) {
		this(context, null);
	}

	public LinearGradientTextView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public LinearGradientTextView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();

	}

	private void init() {
		paint = new Paint();
		paint.setTextSize(60);
		Rect rect = new Rect();
		paint.getTextBounds(text, 0, text.length() - 1, rect);
		float[] pos = { -rect.width() / 2, rect.height() / 2, 0,
				rect.height() / 2, rect.width() / 2, rect.height() / 2 };
		// int[] colos = { Color.BLACK, Color.GREEN, Color.b };
		gradient = new LinearGradient(0, rect.height() / 2, rect.width(),
				rect.height() / 2, Color.BLUE, Color.BLACK,
				Shader.TileMode.CLAMP);
		// gradient.getLocalMatrix(localM);
		paint.setAntiAlias(true);
		paint.setShader(gradient);
		// paint.setStrokeWidth(5);
		// paint.setColor(Color.GREEN);
		paint.setStyle(Style.STROKE);

	}

	public void setTranslate(int width) {
		translate = width;
		if (translate > getWidth()) {
			translate = -translate;
		}
		localM.setTranslate(translate, 0);
		postInvalidate();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		// int height = MeasureSpec.getSize(heightMeasureSpec);
		// int width = MeasureSpec.getSize(widthMeasureSpec);
		setMeasuredDimension(300, 300);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (localM != null)
			gradient.setLocalMatrix(localM);
		canvas.drawText(text, 0, 100, paint);
	}
}
