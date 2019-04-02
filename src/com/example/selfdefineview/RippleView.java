package com.example.selfdefineview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

public class RippleView extends View {

	private static final int DEFAULT_RADIUS = 50;
	private static final int MAX_RADIUX = 100;
	private Paint mPaintGradient;
	private RadialGradient mRippleGradient;
	private int width;
	private int height;
	private int mX;
	private int mY;
	private int mGradientRadius;
	private ObjectAnimator mGradientAnimator;
	private int mLastX;
	private int mLasty;

	public RippleView(Context context) {
		this(context, null);
	}

	public RippleView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public RippleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {

		mPaintGradient = new Paint();
		mPaintGradient.setAntiAlias(true);

	}

	public void setGradientRadius(int radius) {
		mGradientRadius = radius;
		if (radius > 0) {
			mRippleGradient = new RadialGradient(mX, mY, radius, 0x00FFFFFF,
					0xFF58FAAC, Shader.TileMode.CLAMP);

		}
		mPaintGradient.setShader(mRippleGradient);
		postInvalidate();
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		width = right - left;
		height = bottom - top;
		initAnimator();

	}

	private void initAnimator() {
		mGradientAnimator = ObjectAnimator.ofInt(this, "GradientRadius",
				DEFAULT_RADIUS, MAX_RADIUX);
		mGradientAnimator.setDuration(100);
		mGradientAnimator.setInterpolator(new AccelerateInterpolator());

		mGradientAnimator.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				setGradientRadius(0);
				super.onAnimationEnd(animation);
			}
		});
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		if (mRippleGradient != null) {
			System.out.println("onDraw");
			canvas.drawCircle(mX, mY, mGradientRadius, mPaintGradient);
		}
	}

	private void triggerAnimator() {
		if (mGradientAnimator != null && mGradientAnimator.isRunning())
			mGradientAnimator.cancel();
		mGradientAnimator.start();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		System.out.println("onTouchEvent");
		int action = event.getAction();
		int x = (int) event.getX();
		int y = (int) event.getY();
		mX = x;
		mY = y;
		switch (action) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			setGradientRadius(DEFAULT_RADIUS);

			break;
		case MotionEvent.ACTION_UP:
			triggerAnimator();
			break;
		default:
			break;
		}
		return true;
	}

}
