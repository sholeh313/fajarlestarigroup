package com.mahkota_company.android.utils;

import android.graphics.Bitmap;

public class RowItem {

	private Bitmap bitmapImage;

	public RowItem(Bitmap bitmapImage) {
		this.bitmapImage = bitmapImage;
	}

	public Bitmap getBitmapImage() {
		return bitmapImage;
	}

	public void setBitmapImage(Bitmap bitmapImage) {
		this.bitmapImage = bitmapImage;
	}
}
