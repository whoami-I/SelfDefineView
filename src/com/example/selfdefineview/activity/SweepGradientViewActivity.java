package com.example.selfdefineview.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;

import com.example.selfdefineview.R;
import com.example.selfdefineview.SweepGradientView;

public class SweepGradientViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sweepgradientview);
		SweepGradientView sg = (SweepGradientView) findViewById(R.id.sg);

		final ObjectAnimator animator = ObjectAnimator.ofFloat(sg, "rotation",
				0, -360);

		animator.setDuration(10000);
		sg.post(new Runnable() {

			@Override
			public void run() {
				animator.start();
			}
		});
	}
}
