package com.example.selfdefineview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class TextView extends View {
	private static final String TAG = "TextView";
	private String text;
	private int textColor = Color.BLACK;
	private int backgroundColor = Color.BLACK;
	private int textSize = 20;

	public TextView(Context context) {
		this(context, null);
	}

	public TextView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.TextView);
		text = array.getString(R.styleable.TextView_text);
		backgroundColor = array.getColor(R.styleable.TextView_background,
				backgroundColor);
		textColor = array.getColor(R.styleable.TextView_textColor, textColor);
		textSize = array.getDimensionPixelSize(R.styleable.TextView_textSize,
				textSize);
		Log.i(TAG, "text:" + text);
		Log.i(TAG, "backgroundColor:" + backgroundColor);
		Log.i(TAG, "textColor:" + textColor);
		Log.i(TAG, "textSize:" + textSize);
		array.recycle();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		setMeasuredDimension(measureWidth(widthMeasureSpec),
				measureHeight(heightMeasureSpec));

	}

	private int measureHeight(int heightMeasureSpec) {

		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);
		int resultSize;
		if (heightMode == MeasureSpec.EXACTLY) {
			resultSize = height;
		} else {
			resultSize = 100;
		}
		return resultSize;
	}

	private int measureWidth(int widthMeasureSpec) {

		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int resultSize;
		if (widthMode == MeasureSpec.EXACTLY) {
			resultSize = width;
		} else {
			resultSize = 100;
		}
		return resultSize;
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint paint = new Paint();

		paint.setColor(backgroundColor);
		Rect rect = new Rect();
		rect.set(0, 0, getWidth(), getHeight());
		canvas.drawRect(rect, paint);
		paint.setColor(textColor);
		// paint.setTextSize(20);
		paint.setTextSize(textColor);
		canvas.drawText(text, 0, getHeight() / 2, paint);
	}
}
