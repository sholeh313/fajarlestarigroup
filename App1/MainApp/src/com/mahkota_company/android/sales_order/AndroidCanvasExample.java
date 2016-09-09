package com.mahkota_company.android.sales_order;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.mahkota_company.android.R;

public class AndroidCanvasExample extends Activity {

	private CanvasView customCanvas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.canvas_ttd_main);

		customCanvas = (CanvasView) findViewById(R.id.signature_canvas);
	}

	public void clearCanvas(View v) {
		customCanvas.clearCanvas();
	}



}