package com.example.selfdefineview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class BeerBubbleLoadView extends View {
	private static final int MAXPROGRESS = 100;
	private int WaveAmptitude = 8;
	private Paint WavePaint;
	private Path path;
	private int WaveWidth = 300;
	private int WaveHeight = 400;
	private int time;
	private int BeerHeight = 0;
	private int BeerProgress = 0;

	private ObjectAnimator WaveAnimator;

	public BeerBubbleLoadView(Context context) {
		this(context, null);
	}

	public BeerBubbleLoadView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public BeerBubbleLoadView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	private void init(Context context) {
		WavePaint = new Paint();
		WavePaint.setAntiAlias(true);
		WavePaint.setColor(Color.parseColor("#EFA601"));
		WavePaint.setStrokeCap(Cap.ROUND);
		WavePaint.setStyle(Style.FILL);
		path = new Path();
		setupWaveAnimation();

	}

	private void setBeerHeight(int BeerHeight) {
		this.BeerHeight = BeerHeight;
	}

	private void setupWaveAnimation() {
		if (!(getVisibility() == VISIBLE)) {
			System.out.println("can not see");
			return;
		}
		System.out.println("can see");
		WaveAnimator = ObjectAnimator.ofInt(this, "time", 0, 360);
		WaveAnimator.setDuration(1000);
		WaveAnimator.setRepeatMode(ObjectAnimator.RESTART);
		WaveAnimator.setRepeatCount(ObjectAnimator.INFINITE);
		WaveAnimator.setInterpolator(new LinearInterpolator());
		if (!WaveAnimator.isRunning()) {
			WaveAnimator.start();
		}
	}

	public void setBeerProgress(int BeerProgress) {
		if (BeerProgress < 0) {
			this.BeerProgress = 0;
		} else if (BeerProgress > MAXPROGRESS) {
			this.BeerProgress = MAXPROGRESS;
		} else {
			this.BeerProgress = BeerProgress;
		}
		int BeerHeight = (int) (BeerProgress / 100.0f * WaveHeight);
		setBeerHeight(BeerHeight);
	}

	public void setTime(int time) {
		this.time = time;
		invalidate();
	}

	private void initPath() {
		float y;
		int i = 0;
		for (i = 0; i < getWidth(); i++) {
			y = (float) (WaveAmptitude
					* Math.sin((Math.PI * (i + time)) / 1.5 / 180.0f) + BeerHeight);
			y = getHeight() - y;
			if (i == 0) {
				path.reset();
				path.moveTo(0, y);
			}
			path.lineTo(i, y);
		}
		path.lineTo(i, getHeight());
		path.lineTo(0, getHeight());
		path.close();

	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);

		WaveWidth = right - left;
		WaveHeight = bottom - top;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		initPath();

		canvas.drawPath(path, WavePaint);
	}

}
