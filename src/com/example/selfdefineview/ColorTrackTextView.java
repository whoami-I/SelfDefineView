package com.example.selfdefineview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

public class ColorTrackTextView extends TextView {
	private int mOriginColor;
	private int mChangeColor;
	private Paint paint;
	private Direction mDirection;
	private float mProgress;

	public enum Direction {
		LEFT_TO_RIGHT, RIGHT_TO_LEFT
	}

	public void setDirection(Direction d) {
		mDirection = d;
	}

	public void setOriginColor(int color) {
		mOriginColor = color;
	}

	public void setChangeColor(int color) {
		mChangeColor = color;
	}

	public ColorTrackTextView(Context context) {
		this(context, null);
	}

	public ColorTrackTextView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ColorTrackTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initAttribute(context, attrs, defStyle);
		mDirection = Direction.LEFT_TO_RIGHT;
		paint = new Paint();
	}

	private void initAttribute(Context context, AttributeSet attrs, int defStyle) {
		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.ColorTrackTextView);
		mOriginColor = array.getColor(
				R.styleable.ColorTrackTextView_originColor, Color.BLACK);
		mChangeColor = array.getColor(
				R.styleable.ColorTrackTextView_changeColor, Color.RED);
		array.recycle();

	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {

		if (mDirection == Direction.LEFT_TO_RIGHT) {
			drawText(canvas, 0, (int) (getWidth() * mProgress), mChangeColor);
			drawText(canvas, (int) (getWidth() * mProgress), getWidth(),
					mOriginColor);
		} else if (mDirection == Direction.RIGHT_TO_LEFT) {
			drawText(canvas, 0, (int) (getWidth() * (1 - mProgress)),
					mOriginColor);
			drawText(canvas, (int) (getWidth() * (1 - mProgress)), getWidth(),
					mChangeColor);
		}

	}

	private void drawText(Canvas canvas, int start, int end, int color) {
		Rect rect = new Rect(start, 0, end, getBottom());
		canvas.save();
		canvas.clipRect(rect);

		String text = getText().toString();
		float textSize = getTextSize();
		paint.setTextSize(textSize);
		paint.setColor(color);
		Rect bound = new Rect();
		paint.getTextBounds(text, 0, text.length(), bound);
		int x = (getWidth() - bound.width()) / 2;
		FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
		// 获取基线
		int dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2
				- fontMetricsInt.bottom;
		int y = getHeight() / 2 + dy;
		canvas.drawText(text, x, y, paint);
		canvas.restore();

	}

	public void setProgress(float p) {
		mProgress = p;
		invalidate();

	}
}
