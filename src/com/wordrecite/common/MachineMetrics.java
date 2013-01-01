package com.wordrecite.common;

import android.app.Activity;
import android.os.Build;
import android.util.DisplayMetrics;

public class MachineMetrics {
	private DisplayMetrics displayMetrics;
	private Activity activity;

	public MachineMetrics(Activity activity) {
		this.displayMetrics = new DisplayMetrics();
		this.activity = activity;
		activity.getWindowManager().getDefaultDisplay()
				.getMetrics(displayMetrics);
	}

	/**
	 * ��ȡ��Ļ�߶�
	 * 
	 * @return
	 */
	public int getScreenHeight() {
		int height = displayMetrics.heightPixels;
		if (isEmulator()) {
			return height;
		} else {
			return (int) (height * displayMetrics.density);
		}
	}

	/**
	 * ��ȡ��Ļ���
	 * 
	 * @return
	 */
	public int getScreenWidth() {
		int width = displayMetrics.widthPixels;
		if (isEmulator()) {
			return width;
		} else {
			return (int) (width * displayMetrics.density);
		}
	}

	/**
	 * ��ȡ��Ļ�ܶ�
	 * 
	 * @return
	 */
	public float getDentity() {
		return displayMetrics.density;
	}

	/**
	 * �ж��Ƿ���android�����
	 * 
	 * @return
	 */
	public final boolean isEmulator() {
		return Build.BRAND.toLowerCase().indexOf("generic") != -1
				&& Build.MODEL.toLowerCase().indexOf("sdk") != -1;
	}

}
