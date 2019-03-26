package com.example.selfdefineview;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

public class ThreeBallView extends View {

	private Paint mPaint;
	private Paint mPaintMiddle;
	private Paint mPaintRight;
	private int mMaxDis = 60;
	private int mCur;
	private ValueAnimator animator;

	public ThreeBallView(Context context) {
		this(context, null);
	}

	public ThreeBallView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ThreeBallView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setAnimation();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setStrokeWidth(20);
		mPaint.setStrokeCap(Cap.ROUND);

	}

	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);
		// ÏÈ»­×ó±ß
		mPaint.setColor(Color.RED);
		canvas.drawPoint(getWidth() / 2 + mCur, getHeight() / 2, mPaint);
		mPaint.setColor(Color.BLUE);
		canvas.drawPoint(getWidth() / 2, getHeight() / 2, mPaint);
		mPaint.setColor(Color.RED);
		canvas.drawPoint(getWidth() / 2 - mCur, getHeight() / 2, mPaint);
	}

	public void setAnimation() {

		animator = ValueAnimator.ofInt(-mMaxDis, mMaxDis);
		animator.setDuration(700);
		animator.setInterpolator(new AccelerateDecelerateInterpolator());
		animator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				mCur = (Integer) animation.getAnimatedValue();
				invalidate();
			}
		});
		animator.setRepeatCount(ValueAnimator.INFINITE);
		animator.setRepeatMode(ValueAnimator.RESTART);

	}

	public void startAnimation() {
		animator.start();
	}

}
