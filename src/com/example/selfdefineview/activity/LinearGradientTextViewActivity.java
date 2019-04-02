package com.example.selfdefineview.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;

import com.example.selfdefineview.LinearGradientTextView;
import com.example.selfdefineview.R;

public class LinearGradientTextViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lineargradienttextview);
		final LinearGradientTextView linear = (LinearGradientTextView) findViewById(R.id.linear);

		linear.post(new Runnable() {

			@Override
			public void run() {
				ObjectAnimator animator = ObjectAnimator.ofInt(linear,
						"translate", 0, linear.getWidth());
				System.out.println(linear.getWidth());
				animator.setDuration(2000);
				animator.setStartDelay(1000);
				animator.setRepeatCount(ObjectAnimator.INFINITE);
				animator.setRepeatMode(ObjectAnimator.RESTART);
				animator.start();
			}
		});
	}
}
