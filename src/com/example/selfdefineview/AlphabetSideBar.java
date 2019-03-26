package com.example.selfdefineview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AlphabetSideBar extends LinearLayout {
	private static final String TAG = "AlphabetSideBar";
	private int textColor;
	private int mSelectedColor;
	private int mBackgroundColor;
	private int mTextSize;
	private Listener mListener;
	private TextView mLastTextView;
	private String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z", "#" };

	public AlphabetSideBar(Context context) {
		this(context, null);
	}

	public AlphabetSideBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public AlphabetSideBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initAttributes(context, attrs, defStyle);
		initViews(context);
	}

	private void initViews(Context context) {
		for (int i = 0; i < alphabet.length; i++) {
			TextView textView = new TextView(context);

			this.addView(textView);
			textView.setText(alphabet[i]);
			textView.setBackgroundColor(mBackgroundColor);
			textView.setTextColor(textColor);
			textView.setTextSize(mTextSize);

			// ����TextView�ڸ������еĶ��뷽ʽ���������Ե�
			LayoutParams layoutParams = (LinearLayout.LayoutParams) textView
					.getLayoutParams();
			layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
			layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
			layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
			textView.setPadding(5, 5, 5, 5);
			textView.setLayoutParams(layoutParams);
			textView.setGravity(Gravity.CENTER_VERTICAL);
			Log.i(TAG,
					"alpha:" + textView.getText() + " height:"
							+ textView.getHeight() + " width:"
							+ textView.getWidth());

		}
		// requestLayout();
	}

	private int dp2px(Context context, int dp) {

		return (int) (context.getResources().getDisplayMetrics().density * dp + 0.5f);

	}

	private int sp2px(Context context, int sp) {

		return (int) (context.getResources().getDisplayMetrics().scaledDensity
				* sp + 0.5f);
	}

	private void initAttributes(Context context, AttributeSet attrs,
			int defStyle) {
		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.AlphabetSideBar);
		textColor = array.getColor(
				R.styleable.AlphabetSideBar_android_textColor, Color.GRAY);
		mSelectedColor = array.getColor(
				R.styleable.AlphabetSideBar_selectedColor, Color.BLUE);
		mBackgroundColor = array.getColor(
				R.styleable.AlphabetSideBar_android_background, Color.WHITE);
		mTextSize = array.getDimensionPixelSize(
				R.styleable.AlphabetSideBar_android_textSize, 13);
		array.recycle();

	}

	/*
	 * ��дonTouch������ʹ���ܹ�������ָ����
	 * 
	 * @see android.view.View#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		int y = (int) event.getY();
		int height = getChildAt(0).getHeight();
		int num = (y - getPaddingTop()) / height;
		if (num < 0 || (num > getChildCount() - 1))
			return true;
		final TextView textView = (TextView) getChildAt(num);
		switch (action) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:

			// �����ϴ�����ͬ����ĸ����ֱ�ӷ���
			if (mLastTextView == textView)
				return true;
			// selectTextView(mLastTextView, false);
			selectTextView(textView, true);
			if (mListener != null) {
				mListener.onHover(textView);
			}
			break;
		case MotionEvent.ACTION_UP:

			if (mListener != null) {

				mListener.onFinish(textView);

			}
			break;
		default:
			break;
		}

		return true;
	}

	public void selectItem(int item, boolean b) {
		int childCount = getChildCount();
		if (item > childCount - 1 || item < 0)
			return;
		TextView textView = (TextView) getChildAt(item);
		selectTextView(textView, b);
	}

	public void selectItem(TextView textView, boolean b) {
		selectTextView(textView, b);
	}

	/**
	 * �÷���һ����Դ���ȥ��TextView��������һ���潫֮ǰ��textView���ó� ������ɫ��Ҳ����˵�����ֻ��һ�� *
	 * TextView��ѡ�У�Ҳ���Ǳ����ѡ�е���ɫ
	 * 
	 * @param textView
	 *            ��Ҫѡ���TextView
	 * @param b
	 *            �Ƿ�ѡ��
	 */
	private void selectTextView(TextView textView, boolean b) {
		if (textView == null)
			return;
		if (b) {
			textView.setTextColor(mSelectedColor);

		} else {
			textView.setTextColor(textColor);
		}
		if (mLastTextView != null) {
			mLastTextView.setTextColor(textColor);
		}
		mLastTextView = textView;
	}

	/**
	 * ���һ������������������Ŀ״̬�ĸı�
	 * 
	 * @param l
	 *            ������
	 */
	public void addListener(Listener l) {
		mListener = l;
	}

	public interface Listener {
		public void onHover(TextView textView);

		public void onFinish(TextView textView);
	}

}
