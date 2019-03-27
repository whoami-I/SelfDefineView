package com.example.selfdefineview;

import java.util.ArrayList;
import java.util.List;

import android.animation.AnimatorSet;
import android.animation.AnimatorSet.Builder;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

public class DotLoaderView extends RelativeLayout {

	private Path path;
	private Paint paint;
	private List<View> views = new ArrayList<View>();
	private AnimatorSet set = new AnimatorSet();

	public DotLoaderView(Context context) {
		this(context, null);
	}

	public DotLoaderView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DotLoaderView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		initData(context);

	}

	public void initData(Context context) {
		path = new Path();
		path.moveTo(-100, 100);
		path.quadTo(300, 150, 400, 400);
		path.moveTo(400, 400);
		path.quadTo(500, 150, 700, 100);
		AnimatorSet s;
		Builder builder = null;
		// for (int i = 0; i < 3; i++) {
		// s = getSet(i);
		// if (i == 0) {
		// builder = set.play(s);
		// } else {
		// builder.with(s);
		// }
		// }
		set.play(getSet(0));

	}

	private AnimatorSet getSet(int i) {
		int delay = 300;
		int duration = 1500;
		View view = LayoutInflater.from(getContext()).inflate(
				R.layout.circleview, this, false);
		addView(view);
		// View view = View.inflate(getContext(), R.layout.circleview, this);
		views.add(view);
		ObjectAnimator animator = ObjectAnimator.ofFloat(views.get(i), "x",
				"y", path);
		animator.setDuration(2000);

		ObjectAnimator animator2 = ObjectAnimator.ofFloat(views.get(i),
				"scaleX", 1f, 0.2f, 1f);
		animator2.setDuration(2000);
		ObjectAnimator animator3 = ObjectAnimator.ofFloat(views.get(i),
				"scaleY", 1f, 0.2f, 1f);
		animator3.setDuration(2000);
		// view.setBackgroundColor(Color.RED);
		AnimatorSet set = new AnimatorSet();
		set.setInterpolator(new AccelerateDecelerateInterpolator());
		// set.play(animator).with(animator3).with(animator2);
		set.play(animator).with(animator3);
		set.setStartDelay(delay * i);
		return set;
	}

	public void startAnimation() {
		// animator.start();
		set.start();
		// animator2.start();

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {

		super.onLayout(changed, l, t, r, b);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// View view = views.get(0);
		// LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
		// .getLayoutParams();
		// layoutParams.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
		// layoutParams.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
		// view.setLayoutParams(layoutParams);
		// addView(view);
	}

}
