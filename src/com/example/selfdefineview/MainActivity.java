package com.example.selfdefineview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.ScrollView;

class MyScrollView extends ScrollView {

	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.setFillViewport(true);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		System.out.println(super.isFillViewport());

	}

}

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qqstepview);

		final QQStepView qs = (QQStepView) findViewById(R.id.qs);
		qs.setStepMax(4000);
		qs.setStart(100);
		qs.setEnd(3000);
		qs.setDuration(1000);
		qs.start();

	}
}
