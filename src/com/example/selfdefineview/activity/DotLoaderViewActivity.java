package com.example.selfdefineview.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.selfdefineview.DotLoaderView;
import com.example.selfdefineview.R;

public class DotLoaderViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dotloaderview);

		final DotLoaderView dl = (DotLoaderView) findViewById(R.id.dl);
		dl.post(new Runnable() {

			@Override
			public void run() {
				dl.startAnimation();
			}
		});
	}
}
