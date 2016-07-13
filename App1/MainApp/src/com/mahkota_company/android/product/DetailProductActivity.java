package com.mahkota_company.android.product;

import com.mahkota_company.android.database.DatabaseHandler;
import com.mahkota_company.android.database.Kemasan;
import com.mahkota_company.android.database.Product;
import com.mahkota_company.android.utils.CONFIG;
import com.mahkota_company.android.utils.FileUtils;
import com.mahkota_company.android.utils.GlobalApp;
import com.mahkota_company.android.utils.RowItem;
import com.mahkota_company.android.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class DetailProductActivity extends FragmentActivity {
	private Context act;
	private ImageView menuBackButton;
	private TextView tvTitle;
	private TextView tvHarga;
	private TextView tvStock;
	private TextView tvDeskripsi;
	private DatabaseHandler databaseHandler;
	private ProgressDialog progressDialog;
	private ImageView galleryImages;
	private ArrayList<Product> product_list = new ArrayList<Product>();
	private Product product;
	private Typeface typefaceSmall;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_product);
		act = this;
		databaseHandler = new DatabaseHandler(this);
		menuBackButton = (ImageView) findViewById(R.id.menuBackButton);
		menuBackButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				gotoProduct();
			}
		});
		typefaceSmall = Typeface.createFromAsset(getAssets(),
				"fonts/AliquamREG.ttf");
		progressDialog = new ProgressDialog(this);
		progressDialog.setTitle(getApplicationContext().getResources()
				.getString(R.string.app_name));
		progressDialog.setMessage(getApplicationContext().getResources()
				.getString(R.string.app_promosi_processing));
		progressDialog.setCancelable(true);
		progressDialog.setCanceledOnTouchOutside(false);
		tvTitle = (TextView) findViewById(R.id.detail_title);
		tvHarga = (TextView) findViewById(R.id.detail_harga);
		tvStock = (TextView) findViewById(R.id.detail_stock);
		tvDeskripsi = (TextView) findViewById(R.id.detail_deskripsi);
		tvTitle.setTypeface(typefaceSmall);
		tvStock.setTypeface(typefaceSmall);
		tvHarga.setTypeface(typefaceSmall);
		tvDeskripsi.setTypeface(typefaceSmall);
		galleryImages = (ImageView) findViewById(R.id.gallery1);
		SharedPreferences spPreferences = getSharedPrefereces();
		String main_app_table_id = spPreferences.getString(
				CONFIG.SHARED_PREFERENCES_TABLE_PRODUCT_ID_PRODUCT, null);
		if (main_app_table_id != null) {
			showProductFromDB(main_app_table_id);
		} else {
			gotoProduct();
		}

	}

	public HttpResponse getDownloadData(String url) {
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		HttpResponse response;
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(url);
			response = client.execute(get);
		} catch (UnsupportedEncodingException e1) {
			response = null;
		} catch (Exception e) {
			e.printStackTrace();
			response = null;
		}

		return response;
	}

	public void showProductFromDB(String id) {
		product_list.clear();
		product = null;
		Product product_from_db = databaseHandler.getProduct(Integer
				.parseInt(id));
		if (product_from_db != null) {
			product = product_from_db;
			File dir = new File(CONFIG.getFolderPath() + "/"
					+ CONFIG.CONFIG_APP_FOLDER_PRODUCT + "/"
					+ product_from_db.getFoto());
			if (!dir.exists()) {
				product_list.add(product_from_db);
			}

			if (product_list.size() > 0) {
				if (GlobalApp.checkInternetConnection(act)) {
					processDownloadContentProduct();
				} else {
					String message = act.getApplicationContext().getResources()
							.getString(R.string.app_product_processing_empty);
					showCustomDialog(message);
					tvTitle.setVisibility(View.GONE);
					tvHarga.setVisibility(View.GONE);
					tvDeskripsi.setVisibility(View.GONE);
					galleryImages.setVisibility(View.GONE);
				}
			} else {
				showDataProduct();
			}
		} else {
			tvTitle.setVisibility(View.GONE);
			tvHarga.setVisibility(View.GONE);
			tvDeskripsi.setVisibility(View.GONE);
			galleryImages.setVisibility(View.GONE);
		}
	}

	public void processDownloadContentProduct() {
		progressDialog = new ProgressDialog(this);
		progressDialog.setTitle(getApplicationContext().getResources()
				.getString(R.string.app_name));
		progressDialog.setMessage(getApplicationContext().getResources()
				.getString(R.string.app_product_processing_download_content));
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setIndeterminate(false);
		progressDialog.setMax(100);
		progressDialog.setCancelable(true);
		progressDialog.show();
		DownloadContentProduct task = new DownloadContentProduct();
		List<String> stringImg = new ArrayList<String>();
		for (int i = 0; i < product_list.size(); i++) {
			String img1 = product_list.get(i).getFoto().replaceAll(" ", "%20");
			String download_image = CONFIG.CONFIG_APP_URL_DIR_IMG_PRODUCT
					+ img1 + "#" + product_list.get(i).getFoto();
			stringImg.add(download_image);
		}
		String[] imgArr = new String[stringImg.size()];
		imgArr = stringImg.toArray(imgArr);
		task.execute(imgArr);
	}

	private class DownloadContentProduct extends
			AsyncTask<String, Integer, List<RowItem>> {
		List<RowItem> rowItems;
		int noOfURLs;

		protected List<RowItem> doInBackground(String... urls) {
			noOfURLs = urls.length;
			rowItems = new ArrayList<RowItem>();
			Bitmap map = null;
			for (String url : urls) {
				map = downloadImage(url.split("#")[0], url.split("#")[1]);
				rowItems.add(new RowItem(map));
			}
			return rowItems;
		}

		private Bitmap downloadImage(String urlString, String fileName) {
			int count = 0;
			Bitmap bitmap = null;
			URL url;
			InputStream inputStream = null;
			BufferedOutputStream outputStream = null;
			OutputStream output = null;

			File dir = new File(CONFIG.getFolderPath() + "/"
					+ CONFIG.CONFIG_APP_FOLDER_PRODUCT);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			try {
				url = new URL(urlString);
				output = new FileOutputStream(dir + "/" + fileName);
				URLConnection connection = url.openConnection();
				int lenghtOfFile = connection.getContentLength();
				inputStream = new BufferedInputStream(url.openStream());
				ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
				outputStream = new BufferedOutputStream(dataStream);
				byte data[] = new byte[512];
				long total = 0;
				while ((count = inputStream.read(data)) != -1) {
					total += count;
					publishProgress((int) ((total * 100) / lenghtOfFile));
					outputStream.write(data, 0, count);
					output.write(data, 0, count);
				}
				outputStream.flush();
				BitmapFactory.Options bmOptions = new BitmapFactory.Options();
				bmOptions.inSampleSize = 1;
				byte[] bytes = dataStream.toByteArray();
				bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length,
						bmOptions);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				FileUtils.close(output);
				FileUtils.close(inputStream);
				FileUtils.close(outputStream);
			}
			return bitmap;
		}

		protected void onProgressUpdate(Integer... progress) {
			progressDialog.setProgress(progress[0]);
			if (rowItems != null) {
				progressDialog.setMessage("Loading " + (rowItems.size() + 1)
						+ "/" + noOfURLs);
			}
		}

		protected void onPostExecute(List<RowItem> rowItems) {
			progressDialog.dismiss();
			showDataProduct();
		}
	}

	public void showCustomDialog(String msg) {
		if (progressDialog != null) {
			progressDialog.dismiss();
		}
		final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				act);
		alertDialogBuilder
				.setMessage(msg)
				.setCancelable(false)
				.setPositiveButton(
						act.getApplicationContext().getResources()
								.getString(R.string.MSG_DLG_LABEL_OK),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								AlertDialog alertDialog = alertDialogBuilder
										.create();
								alertDialog.dismiss();

							}
						});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();

	}

	public void showDataProduct() {
		if (product != null) {
			tvTitle.setVisibility(View.VISIBLE);
			tvHarga.setVisibility(View.VISIBLE);
			tvStock.setVisibility(View.VISIBLE);
			tvDeskripsi.setVisibility(View.VISIBLE);
			File dir = new File(CONFIG.getFolderPath() + "/"
					+ CONFIG.CONFIG_APP_FOLDER_PRODUCT + "/"
					+ product.getFoto());
			if (dir.exists()) {
				galleryImages.setImageBitmap(BitmapFactory.decodeFile(dir
						.getAbsolutePath()));
			} else {
				Bitmap icon = BitmapFactory.decodeResource(
						getApplicationContext().getResources(),
						R.drawable.ic_logo);
				galleryImages.setImageBitmap(icon);
			}

			tvTitle.setText(product.getNama_product());
			Kemasan kemasan = databaseHandler.getKemasan(Integer
					.parseInt(product.getId_kemasan()));
			if (kemasan != null)
				tvStock.setText(getApplicationContext().getResources()
						.getString(R.string.app_product_stock)
						+ " "
						+ product.getStock()
						+ " "
						+ kemasan.getNama_id_kemasan());
			Float priceIDR = Float.valueOf(product.getHarga_jual());

			DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
			otherSymbols.setDecimalSeparator(',');
			otherSymbols.setGroupingSeparator('.');

			DecimalFormat df = new DecimalFormat("#,##0", otherSymbols);

			tvHarga.setText(getApplicationContext().getResources().getString(
					R.string.app_product_harga)
					+ " " + "Rp. " + df.format(priceIDR));

			tvDeskripsi.setText(product.getDeskripsi());

		}

	}

	private SharedPreferences getSharedPrefereces() {
		return act.getSharedPreferences(CONFIG.SHARED_PREFERENCES_NAME,
				Context.MODE_PRIVATE);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void gotoProduct() {
		Intent i = new Intent(this, ProductActivity.class);
		startActivity(i);
		finish();
	}
}
