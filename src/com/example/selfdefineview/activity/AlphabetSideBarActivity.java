package com.example.selfdefineview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.selfdefineview.AlphabetSideBar;
import com.example.selfdefineview.AlphabetSideBar.Listener;
import com.example.selfdefineview.R;

public class AlphabetSideBarActivity extends Activity {

	protected static final String TAG = "AlphabetSideBarActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final AlphabetSideBar ab = (AlphabetSideBar) findViewById(R.id.ab);
		final TextView tv = (TextView) findViewById(R.id.tv);
		final Animation animation = AnimationUtils.loadAnimation(
				getApplicationContext(), R.anim.alpha);
		ab.addListener(new Listener() {

			@Override
			public void onHover(TextView textView) {
				tv.setVisibility(View.VISIBLE);
				tv.setText(textView.getText());
			}

			@Override
			public void onFinish(TextView textView) {
				tv.startAnimation(animation);
				// tv.setVisibility(View.GONE);
			}
		});
	}
}
