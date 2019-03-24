package com.example.selfdefineview.activity;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.selfdefineview.ColorTrackTextView;
import com.example.selfdefineview.ColorTrackTextView.Direction;
import com.example.selfdefineview.R;

public class ColorTrackTextViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_colortracktextview);
		Button left_to_right = (Button) findViewById(R.id.left_to_right);

		Button right_to_left = (Button) findViewById(R.id.right_to_left);
		final ColorTrackTextView ct = (ColorTrackTextView) findViewById(R.id.ct);
		final ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
		animator.setDuration(1000);
		animator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float value = (Float) animation.getAnimatedValue();
				ct.setProgress(value);
			}
		});

		left_to_right.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ct.setDirection(Direction.LEFT_TO_RIGHT);
				animator.start();
			}
		});

		right_to_left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ct.setDirection(Direction.RIGHT_TO_LEFT);
				animator.start();
			}
		});
	}
}
