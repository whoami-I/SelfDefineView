package com.example.selfdefineview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.selfdefineview.QQStepView;
import com.example.selfdefineview.R;

public class QQStepActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qqstepview);

		final QQStepView qs = (QQStepView) findViewById(R.id.qs);

		Button btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				qs.start();

			}
		});
		qs.setStepMax(4000);
		qs.setStart(100);
		qs.setEnd(3000);
		qs.setDuration(1000);

	}
}
