package com.example.selfdefineview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FlowLayout extends ViewGroup {

	private List<List<View>> mLineList;
	private List<Integer> mHeightList;

	public FlowLayout(Context context) {
		super(context);
	}

	public FlowLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FlowLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mLineList = new ArrayList<List<View>>();
		mHeightList = new ArrayList<Integer>();
	}

	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new MarginLayoutParams(getContext(), attrs);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int paddingLeft = getPaddingLeft();
		int left = 0;
		int top = getPaddingTop();
		int maxHeight = 0;
		for (int i = 0; i < mLineList.size(); i++) {
			List<View> views = mLineList.get(i);
			left = paddingLeft;

			for (int j = 0; j < views.size(); ++j) {
				View view = views.get(j);
				MarginLayoutParams lp = (MarginLayoutParams) view
						.getLayoutParams();

				int childLeft = left + lp.leftMargin;
				int childTop = top + lp.topMargin;
				int childRight = childLeft + view.getMeasuredWidth();
				int childBottom = childTop + view.getMeasuredHeight();
				System.out.println("��" + i + "�� ��" + j + "�У�");
				TextView view2 = (TextView) view;
				System.out.println("content:" + view2.getText());
				System.out.println("left:" + childLeft);
				System.out.println("top:" + childTop);
				System.out.println("right:" + childRight);
				System.out.println("bottom:" + childBottom);
				view.layout(childLeft, childTop, childRight, childBottom);
				left += view.getMeasuredWidth() + lp.rightMargin
						+ lp.leftMargin;
			}

			maxHeight = mHeightList.get(i);
			top += maxHeight;

		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		final int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		// �������õĿ��
		final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		System.out.println("widthSize" + widthSize);
		// ��ǰʹ���˵Ŀ��
		int currentWidth = 0;
		// �������������
		int maxWidth = 0;
		// ��¼һ��ʹ�õ����߶�
		int maxLineHeight = 0;
		int count = getChildCount();
		mLineList.clear();
		mHeightList.clear();

		// ���ڴ��ÿһ�е�view
		List<View> lineViews = new ArrayList<View>();
		for (int i = 0; i < count; ++i) {
			View childView = getChildAt(i);

			measureChild(childView, widthMeasureSpec, heightMeasureSpec);

			MarginLayoutParams lp = (MarginLayoutParams) childView
					.getLayoutParams();
			int childWidth = lp.leftMargin + lp.rightMargin
					+ childView.getMeasuredWidth();
			int childHeight = lp.topMargin + lp.bottomMargin
					+ childView.getMeasuredHeight();
			maxLineHeight = Math.max(maxLineHeight, childHeight);
			if (childWidth + currentWidth > widthSize) {
				// ˵��Ҫ����
				mLineList.add(lineViews);
				lineViews = new ArrayList<View>();
				// lineViews.clear();
				lineViews.add(childView);
				// ��Ȼ������Ѿ�������widthSize����ômaxWidth=widthSize
				maxWidth = widthSize;

				mHeightList.add(maxLineHeight);
				// ��һ�еĲ����仯
				maxLineHeight = childHeight;
				currentWidth = childWidth;

			} else {
				lineViews.add(childView);
				maxWidth = Math.max(maxWidth, childWidth + currentWidth);
				currentWidth += childWidth;

				// maxLineHeight = Math.max(maxLineHeight, childHeight);

			}

			if (i == count - 1) {
				// ���һ��
				mLineList.add(lineViews);
				mHeightList.add(maxLineHeight);
			}
		}

		int heightSum = 0;
		for (Integer height : mHeightList) {
			heightSum += height.intValue();
		}
		heightSum += getPaddingTop() + getPaddingBottom();
		System.out.println("mLineList.size:" + mLineList.size());

		int resultWidth = 0;
		int resultHeight = 0;
		// �������ô˲��ֵĿ�ߣ�����������viewռ�ݵ�λ�ú�����Ŀ��ģʽ����
		if (widthMode == MeasureSpec.EXACTLY) {
			resultWidth = widthSize;
		} else if (widthMode == MeasureSpec.AT_MOST) {
			resultWidth = Math.min(widthSize, maxWidth);
		}

		if (heightMode == MeasureSpec.EXACTLY) {
			resultHeight = heightSize;
		} else if (heightMode == MeasureSpec.AT_MOST) {
			resultHeight = Math.min(heightSize, heightSum);
		}
		// setMeasuredDimension(maxWidth, heightSum);
		setMeasuredDimension(resultWidth, resultHeight);

	}
}
