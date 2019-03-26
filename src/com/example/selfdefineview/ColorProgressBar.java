package com.example.selfdefineview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

public class ColorProgressBar extends View {

	private int mColor;

	public ColorProgressBar(Context context) {
		this(context, null);
	}

	public ColorProgressBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ColorProgressBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initAttibute(context, attrs, defStyle);
	}

	private void initAttibute(Context context, AttributeSet attrs, int defStyle) {
		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.ColorProgressBar);

		mColor = array.getInt(R.styleable.ColorProgressBar_color, Color.BLUE);

		array.recycle();
	}

}
