package com.example.selfdefineview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

public class SweepGradientView extends View {

	private Paint paint;
	private int angle;

	public SweepGradientView(Context context) {
		this(context, null);
	}

	public SweepGradientView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SweepGradientView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initData();
	}

	private void initData() {
		paint = new Paint();
		paint.setAntiAlias(true);
		// paint.setShader(new SweepGradient(getWidth() / 2, getHeight() / 2,
		// Color.parseColor("#ffff0000"), Color.parseColor("#00ff0000")));

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);
		int width = MeasureSpec.getSize(widthMeasureSpec);
		width = height > width ? width : height;
		setMeasuredDimension(width, width);
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {

		RectF rectF = new RectF(10, 10, getWidth() - 10, getHeight() - 10);
		// canvas.drawArc(rectF, angle, 270, true, paint);
		SweepGradient sweepGradient = new SweepGradient(getWidth() / 2,
				getHeight() / 2, Color.TRANSPARENT, Color.GREEN);
		paint.setColor(0x9D00ff00);
		paint.setShader(sweepGradient);
		canvas.drawCircle(getWidth() / 2, getHeight() / 2, 250, paint);
		super.onDraw(canvas);
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

}
