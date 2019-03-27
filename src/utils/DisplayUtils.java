package utils;

import android.content.Context;

public class DisplayUtils {

	public static int dip2px(Context context, float dip) {
		float density = context.getResources().getDisplayMetrics().density;
		return (int) (0.5f + dip * density);
	}

	public static int px2dip(Context context, float px) {
		float density = context.getResources().getDisplayMetrics().density;
		return (int) (0.5f + px / density);
	}

	public static int sp2px(Context context, float sp) {
		float scaleDensity = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (0.5f + sp * scaleDensity);
	}

	public static int px2sp(Context context, float px) {
		float scaleDensity = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (0.5f + px / scaleDensity);
	}
}
