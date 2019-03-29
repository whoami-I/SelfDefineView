package com.example.selfdefineview.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;

import com.example.selfdefineview.BeerBubbleLoadView;
import com.example.selfdefineview.R;

public class BeerBubbleLoadActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beerbubbleloadview);
		BeerBubbleLoadView beerBubble = (BeerBubbleLoadView) findViewById(R.id.beerBubble);
		final ObjectAnimator animator = ObjectAnimator.ofInt(beerBubble,
				"BeerProgress", 0, 100);
		animator.setDuration(20000);
		animator.setRepeatCount(ObjectAnimator.INFINITE);
		animator.setRepeatMode(ObjectAnimator.RESTART);
		beerBubble.post(new Runnable() {

			@Override
			public void run() {
				animator.start();
			}
		});

	}
}
