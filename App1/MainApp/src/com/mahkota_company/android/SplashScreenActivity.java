package com.mahkota_company.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class SplashScreenActivity extends Activity {
	protected boolean _active = true;
	protected int _splashTime = 1500;
	private Typeface typefaceSmall;
	private TextView txtDevelopedBy;
	private TextView txtDeveloper;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_splash_screen);

		typefaceSmall = Typeface.createFromAsset(getAssets(),
				"fonts/AliquamREG.ttf");
		txtDevelopedBy = (TextView) findViewById(R.id.splash_screen_developed_by);
		txtDevelopedBy.setTypeface(typefaceSmall);
		txtDeveloper = (TextView) findViewById(R.id.splash_screen_developer);
		txtDeveloper.setTypeface(typefaceSmall);
		Thread splashTread = new Thread() {
			@Override
			public void run() {
				try {
					int waited = 0;
					while (_active && (waited < _splashTime)) {
						sleep(100);
						if (_active) {
							waited += 100;
						}
					}
				} catch (InterruptedException e) {
				} finally {
					finish();
					startActivity(new Intent(getApplicationContext(),
							LoginActivity.class));
				}
			}
		};

		splashTread.start();

	}

}
