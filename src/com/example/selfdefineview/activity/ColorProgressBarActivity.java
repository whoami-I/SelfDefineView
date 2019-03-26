package com.example.selfdefineview.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.selfdefineview.ColorProgressBar;
import com.example.selfdefineview.R;

public class ColorProgressBarActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_colorprogressbar);

		final ColorProgressBar pb = (ColorProgressBar) findViewById(R.id.pb);
		pb.post(new Runnable() {

			@Override
			public void run() {
				pb.startAnimation();

			}
		});
	}
}
