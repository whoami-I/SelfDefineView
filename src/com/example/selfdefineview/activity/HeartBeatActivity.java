package com.example.selfdefineview.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;

import com.example.selfdefineview.HeartBeatView;
import com.example.selfdefineview.R;

public class HeartBeatActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_heartbeatview);
		HeartBeatView hb = (HeartBeatView) findViewById(R.id.hb);
		final ObjectAnimator animator = ObjectAnimator.ofInt(hb, "radius", 0,
				100);
		animator.setInterpolator(new AccelerateInterpolator());
		animator.setDuration(1000);
		animator.setRepeatCount(ObjectAnimator.INFINITE);
		animator.setRepeatMode(ObjectAnimator.RESTART);
		hb.post(new Runnable() {

			@Override
			public void run() {
				animator.start();
			}
		});
	}
}
