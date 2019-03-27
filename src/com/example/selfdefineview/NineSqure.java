package com.example.selfdefineview;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class NineSqure extends View {
	private int mColorNormal = Color.GRAY;
	private int mColorSelected = Color.BLUE;
	private int mColorError = Color.RED;
	private Paint mPaintOuter;
	private Paint mPaintInner;
	private Paint mPaintLine;
	private Paint mPaintTriangle;
	private Path path;
	private int mRadiusOuter = 20;
	private int mRadiusInner = 5;
	private int mOuterThickness = 5;
	private Point[] mPoints;
	private int mFingerX;
	private int mFingerY;
	private ArrayList<NineSqure.Point> mPointSelected = new ArrayList<NineSqure.Point>();

	public NineSqure(Context context) {
		this(context, null);
	}

	public NineSqure(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public NineSqure(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initAttributes(context, attrs, defStyle);
		initData();
	}

	private void initData() {
		mPoints = new Point[9];
		for (int i = 0; i < mPoints.length; i++) {
			mPoints[i] = new Point();
			mPoints[i].setStatus(Point.NORMAL);
		}

	}

	private void initAttributes(Context context, AttributeSet attrs,
			int defStyle) {
		mPaintOuter = new Paint();
		mPaintOuter.setAntiAlias(true);
		mPaintOuter.setColor(mColorNormal);
		mPaintOuter.setStrokeWidth(mOuterThickness);
		mPaintOuter.setStyle(Style.STROKE);
		mPaintInner = new Paint();
		mPaintInner.setAntiAlias(true);
		mPaintInner.setColor(mColorNormal);
		mPaintInner.setStrokeWidth(mRadiusInner);
		mPaintInner.setStyle(Style.FILL);
		mPaintLine = new Paint();
		mPaintLine.setAntiAlias(true);
		mPaintLine.setColor(Color.GRAY);
		mPaintLine.setStrokeWidth(8);
		mPaintLine.setStyle(Style.STROKE);
		mPaintTriangle = new Paint();
		mPaintTriangle.setAntiAlias(true);
		mPaintTriangle.setColor(Color.GRAY);
		mPaintTriangle.setStrokeWidth(2);
		mPaintTriangle.setStyle(Style.FILL);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int height = MeasureSpec.getSize(heightMeasureSpec);
		int width = MeasureSpec.getSize(widthMeasureSpec);
		// 维持一个正方形
		int size = height > width ? width : height;
		setMeasuredDimension(size, size);

	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		// 执行完layout就可以获取宽高
		int width = getWidth();
		float part = width / 6.0f;
		mPoints[0].set((int) (part), (int) (part));
		mPoints[1].set((int) (3 * part), (int) (part));
		mPoints[2].set((int) (5 * part), (int) (part));
		mPoints[3].set((int) (part), (int) (3 * part));
		mPoints[4].set((int) (3 * part), (int) (3 * part));
		mPoints[5].set((int) (5 * part), (int) (3 * part));
		mPoints[6].set((int) (part), (int) (5 * part));
		mPoints[7].set((int) (3 * part), (int) (5 * part));
		mPoints[8].set((int) (5 * part), (int) (5 * part));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		for (int i = 0; i < mPoints.length; i++) {
			Point point = mPoints[i];
			int status = point.getStatus();
			switch (status) {
			case Point.NORMAL:
				mPaintInner.setColor(mColorNormal);
				mPaintOuter.setColor(mColorNormal);
				break;
			case Point.SELECTED:
				mPaintInner.setColor(mColorSelected);
				mPaintOuter.setColor(mColorSelected);
				break;
			case Point.ERROR:
				mPaintInner.setColor(mColorError);
				mPaintOuter.setColor(mColorError);
				break;
			default:
				break;
			}
			// 先画内部的点
			canvas.drawPoint(point.x, point.y, mPaintInner);
			// 外部的圆
			canvas.drawCircle(point.x, point.y, mRadiusOuter, mPaintOuter);
		}
		// 画点之间的线
		if (!mPointSelected.isEmpty()) {
			int x0 = mPointSelected.get(0).x;
			int y0 = mPointSelected.get(0).y;
			for (int i = 1; i < mPointSelected.size(); i++) {
				int x = mPointSelected.get(i).x;
				int y = mPointSelected.get(i).y;
				canvas.drawLine(x0, y0, x, y, mPaintLine);
				// 画箭头
				drawTritangle(canvas, x, y, 30, 25,
						(float) Math.atan2(y - y0, x - x0), mPaintTriangle);
				x0 = x;
				y0 = y;
			}
			// 画最后一个点到手指的线
			canvas.drawLine(x0, y0, mFingerX, mFingerY, mPaintLine);
		}

	}

	/**
	 * @param canvas
	 * @param x
	 *            三角形的顶点x
	 * @param y
	 *            三角形的顶点x
	 * @param height
	 *            三角形的高
	 * @param bottom
	 *            底边长度
	 * @param angle
	 *            高对应直线的角度
	 * @param paint
	 *            画笔
	 */
	public void drawTritangle(Canvas canvas, int x, int y, float height,
			float bottom, float angle, Paint paint) {
		Path path = new Path();
		float centerX = (float) (x - height * Math.cos(angle));
		float centerY = (float) (y - height * Math.sin(angle));
		int leftX = (int) (centerX + bottom * Math.sin(angle) / 2);
		int leftY = (int) (centerY - bottom * Math.cos(angle) / 2);
		int rightX = (int) (centerX - bottom * Math.sin(angle) / 2);
		int rightY = (int) (centerY + bottom * Math.cos(angle) / 2);

		path.moveTo(x, y);
		path.lineTo(leftX, leftY);
		path.lineTo(rightX, rightY);
		path.close();

		canvas.drawPath(path, paint);
	}

	public boolean isInCircle(int x, int y, int x0, int y0, int radius) {

		float distance = (float) Math.sqrt(Math.pow(x - x0, 2)
				+ Math.pow(y - y0, 2));
		return distance > radius ? false : true;

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		int x = (int) event.getX();
		int y = (int) event.getY();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			// 记录手指的位置
			mFingerX = x;
			mFingerY = y;
			for (int i = 0; i < mPoints.length; i++) {
				// 如果没有被选中，则继续判断是否在圆内
				if (!mPointSelected.contains(mPoints[i])) {
					if (isInCircle(x, y, mPoints[i].getX(), mPoints[i].y,
							mRadiusOuter)) {
						mPointSelected.add(mPoints[i]);
						mPoints[i].setStatus(Point.SELECTED);
					}
				}
			}
			invalidate();

			break;
		case MotionEvent.ACTION_UP:
			reset();
			invalidate();
			break;
		default:
			break;
		}
		return true;
		// return super.onTouchEvent(event);
	}

	private void reset() {
		for (int i = 0; i < mPoints.length; i++) {
			mPoints[i].status = Point.NORMAL;

		}
		mPointSelected.clear();
	}

	class Point {
		private int x;
		private int y;
		private int status;
		public static final int NORMAL = 0;
		public static final int SELECTED = 1;
		public static final int ERROR = 2;

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public void set(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
