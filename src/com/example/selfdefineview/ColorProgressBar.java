package com.example.selfdefineview;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

public class ColorProgressBar extends View {

	private int mColor;
	private Paint mPaint;
	private float headOffset;
	protected float endOffset;
	private float headOffset2;
	private float endOffset2;
	private AnimatorSet animSet;

	public ColorProgressBar(Context context) {
		this(context, null);
	}

	public ColorProgressBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ColorProgressBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initAttibute(context, attrs, defStyle);
	}

	private void initAttibute(Context context, AttributeSet attrs, int defStyle) {
		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.ColorProgressBar);

		mColor = array.getInt(R.styleable.ColorProgressBar_color, Color.BLUE);

		array.recycle();
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.BLUE);
		mPaint.setStrokeWidth(20);
		mPaint.setStyle(Style.STROKE);

		initAnimation();

	}

	private void initAnimation() {

		ValueAnimator animator1 = ValueAnimator.ofFloat(0, 240);
		animator1.setDuration(2000);
		animator1.setInterpolator(new DecelerateInterpolator());
		animator1.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				headOffset = (Float) animation.getAnimatedValue();
				invalidate();
			}
		});

		ValueAnimator animator2 = ValueAnimator.ofFloat(0, 120 - 15);
		animator2.setDuration(2000);
		animator2.setInterpolator(new LinearInterpolator());
		animator2.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				endOffset = (Float) animation.getAnimatedValue();
			}
		});

		//  ’ÀıŒ≤≤ø
		ValueAnimator animator3 = ValueAnimator.ofFloat(0, 240);
		animator3.setDuration(2000);
		animator3.setInterpolator(new DecelerateInterpolator());
		animator3.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				endOffset2 = (Float) animation.getAnimatedValue();

			}
		});

		ValueAnimator animator4 = ValueAnimator.ofFloat(0, 20);
		animator4.setDuration(2000);
		animator4.setInterpolator(new LinearInterpolator());
		animator4.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				headOffset2 = (Float) animation.getAnimatedValue();
				// headOffset = headOffset - endOffset2;
				invalidate();
			}
		});

		animSet = new AnimatorSet();
		animSet.play(animator1).with(animator2);
		animSet.play(animator3).with(animator4).after(animator1);

	}

	public void startAnimation() {

		animSet.start();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		RectF rectF = new RectF();
		int strokeWidth = 20;
		rectF.set(strokeWidth, strokeWidth, getWidth() - strokeWidth,
				getHeight() - strokeWidth);

		canvas.drawArc(rectF, endOffset + endOffset2, headOffset - endOffset2,
				false, mPaint);
		System.out.println("endOffset:" + endOffset);
		System.out.println("endOffset2:" + endOffset2);
		System.out.println("headOffset:" + headOffset);

	}
}
