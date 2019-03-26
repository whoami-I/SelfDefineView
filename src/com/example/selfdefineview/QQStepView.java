package com.example.selfdefineview;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class QQStepView extends View {

	private int mStepTextSize = 20;
	private int mStepTextColor = Color.RED;
	private int mArchWidth = 6;
	private int mOuterArchColor = Color.BLUE;
	private int mInnerArchColor = Color.RED;
	private Paint mPaint;

	private int mStepMax;
	private int mStepCur;
	private int mStart;

	public int getStart() {
		return mStart;
	}

	public void setStart(int mStart) {
		this.mStart = mStart;
		this.mStepCur = mStart;
	}

	public int getEnd() {
		return mEnd;
	}

	public void setEnd(int mEnd) {
		this.mEnd = mEnd;
	}

	private int mEnd;

	private ValueAnimator mAnimator;

	public int getStepMax() {
		return mStepMax;
	}

	public void setStepMax(int mStepMax) {
		this.mStepMax = mStepMax;

	}

	public int getStepCur() {
		return mStepCur;
	}

	public int getStepTextSize() {
		return mStepTextSize;
	}

	public void setStepTextSize(int mStepTextSize) {
		this.mStepTextSize = mStepTextSize;
	}

	public int getStepTextColor() {
		return mStepTextColor;
	}

	public void setStepTextColor(int mStepTextColor) {
		this.mStepTextColor = mStepTextColor;
	}

	public int getArchWidth() {
		return mArchWidth;
	}

	public void setArchWidth(int mArchWidth) {
		this.mArchWidth = mArchWidth;
	}

	public int getOuterArchColor() {
		return mOuterArchColor;
	}

	public void setOuterArchColor(int mOuterArchColor) {
		this.mOuterArchColor = mOuterArchColor;
	}

	public int getInnerArchColor() {
		return mInnerArchColor;
	}

	public void setInnerArchColor(int mInnerArchColor) {
		this.mInnerArchColor = mInnerArchColor;
	}

	public QQStepView(Context context) {
		this(context, null);
	}

	public QQStepView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public QQStepView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initAttibute(context, attrs, defStyle);
		mPaint = new Paint();
		mAnimator = ValueAnimator.ofInt(0, mStepMax);
	}

	private void initAttibute(Context context, AttributeSet attrs, int defStyle) {
		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.QQStepView);

		mStepTextSize = (int) array.getDimension(
				R.styleable.QQStepView_stepTextSize, mStepTextSize);
		mStepTextColor = array.getInt(R.styleable.QQStepView_stepTextColor,
				mStepTextColor);
		mInnerArchColor = array.getInt(R.styleable.QQStepView_innerArchColor,
				mInnerArchColor);
		mOuterArchColor = array.getInt(R.styleable.QQStepView_outerArchColor,
				mOuterArchColor);

		mArchWidth = (int) array.getDimension(R.styleable.QQStepView_archWidth,
				mArchWidth);
		array.recycle();
	}

	public void setDuration(int millis) {
		mAnimator.setDuration(millis);
	}

	public void start() {
		if (mAnimator.isRunning()) {
			mAnimator.cancel();
		}
		if (mStepMax == 0) {
			throw new RuntimeException(
					"mStepMax can not be 0, use setStepMax(int mStepMax) to set value");
		}

		mAnimator.setIntValues(mStart, mEnd);
		mAnimator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				int value = (Integer) animation.getAnimatedValue();
				setCurrent(value);
			}
		});
		mAnimator.start();

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		if (widthMode == MeasureSpec.AT_MOST
				|| heightMode == MeasureSpec.AT_MOST
				|| widthMode == MeasureSpec.UNSPECIFIED
				|| heightMode == MeasureSpec.UNSPECIFIED) {
			setMeasuredDimension(300, 300);

		}
		// System.out.println("height:" + height);
		// width = Math.min(width, height);
		// setMeasuredDimension(width, width);
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		int w = Math.min(getWidth(), getHeight());
		int left = (getWidth() - w + mArchWidth) / 2;
		int right = (getWidth() + w - mArchWidth) / 2;
		int top = (getHeight() - w + mArchWidth) / 2;
		int bottom = (getHeight() + w - mArchWidth) / 2;
		// 画外圆弧
		mPaint.reset();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setColor(mOuterArchColor);
		mPaint.setStrokeWidth(mArchWidth);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeCap(Cap.ROUND);

		RectF rectF = new RectF(left, top, right, bottom);
		canvas.drawArc(rectF, 135, 270, false, mPaint);
		if (mStepMax == 0)
			return;

		// 画内圆弧
		mPaint.reset();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setColor(mInnerArchColor);
		mPaint.setStrokeWidth(mArchWidth);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeCap(Cap.ROUND);

		float proportion = ((float) mStepCur) / mStepMax;

		canvas.drawArc(rectF, 135, 270 * proportion, false, mPaint);

		// 字
		mPaint.reset();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setTextSize(mStepTextSize);
		mPaint.setColor(mStepTextColor);
		String stepCur = Integer.toString(mStepCur);
		Rect rect = new Rect();
		mPaint.getTextBounds(stepCur, 0, stepCur.length(), rect);
		int x = (getWidth() - rect.width()) / 2;
		FontMetricsInt fontMetricsInt = mPaint.getFontMetricsInt();
		// 获取基线
		int dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2
				- fontMetricsInt.bottom;
		int y = getHeight() / 2 + dy;
		canvas.drawText(stepCur, x, y, mPaint);

	}

	synchronized private void setCurrent(int value) {
		mStepCur = value;
		invalidate();
	}
}
