package com.example.selfdefineview.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.selfdefineview.R;
import com.example.selfdefineview.ThreeBallView;

public class ThreeBallViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_threeballview);

		final ThreeBallView ball = (ThreeBallView) findViewById(R.id.ball);
		ball.post(new Runnable() {

			@Override
			public void run() {
				ball.startAnimation();

			}
		});

	}
}
