package com.mahkota_company.android.inventory;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mahkota_company.android.R;
import com.mahkota_company.android.database.DatabaseHandler;
import com.mahkota_company.android.database.NewDetailPenjualan;
import com.mahkota_company.android.database.Penjualan;
import com.mahkota_company.android.database.PenjualanDetail;
import com.mahkota_company.android.database.Product;
import com.mahkota_company.android.database.StockVan;
import com.mahkota_company.android.utils.CONFIG;
import com.mahkota_company.android.utils.GlobalApp;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SalesKanvasActivity extends FragmentActivity {
	private Context act;
	private ImageView menuBackButton;
	private DatabaseHandler databaseHandler;
	private Typeface typefaceSmall;
	private EditText etNamaCustomer;
	private EditText etAlamat;
	private EditText etKeterangan;

	private TextView tvNoNotaValue;
	private TextView tvTotalbayarValue;
	private TextView tvHeaderTotalbayarTitle;


	private Button mButtonAddProduct;
	private Button mButtonSave;

	private ListViewChooseAdapter cAdapterChooseAdapter;
	private ArrayList<NewDetailPenjualan> detailPenjualanList = new ArrayList<NewDetailPenjualan>();
	private ArrayList<Penjualan> penjualanList = new ArrayList<Penjualan>();
	private ArrayList<PenjualanDetail> penjualanDetailList = new ArrayList<PenjualanDetail>();
	private ListView listview;
	private ListViewAdapter cAdapter;

	private ArrayList<StockVan> stokvan_from_db = new ArrayList<StockVan>();

	private ScrollView scrollView;

	private static final String LOG_TAG = SalesKanvasActivity.class
			.getSimpleName();
	private String main_app_id_staff;
	private String nama;
	private String alamat;
	private String keterangan;

	private ProgressDialog progressDialog;
	private Handler handler = new Handler();
	private String message;
	private String response_data;
	private String no_penjualan;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_sales_kanvas);
		act = this;
		progressDialog = new ProgressDialog(this);
		progressDialog.setTitle(getApplicationContext().getResources()
				.getString(R.string.app_name));
		progressDialog
				.setMessage(getApplicationContext()
						.getResources()
						.getString(
								R.string.app_inventory_processing_download_stock_summary));
		progressDialog.setCancelable(true);
		progressDialog.setCanceledOnTouchOutside(false);
		databaseHandler = new DatabaseHandler(this);
		menuBackButton = (ImageView) findViewById(R.id.menuBackButton);
		menuBackButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				gotoInventory();
			}
		});
		typefaceSmall = Typeface.createFromAsset(getAssets(),
				"fonts/AliquamREG.ttf");
		scrollView = (ScrollView) findViewById(R.id.scrollView);
		listview = (ListView) findViewById(R.id.list);
		listview.setItemsCanFocus(false);
		tvNoNotaValue = (TextView) findViewById(R.id.sales_to_value_nomor_nota);
		etNamaCustomer = (EditText) findViewById(R.id.sales_to_value_nama_customer);
		etAlamat = (EditText) findViewById(R.id.sales_to_value_alamat);
		etKeterangan = (EditText) findViewById(R.id.sales_to_value_keterangan);



		tvTotalbayarValue = (TextView) findViewById(R.id.sales_to_total_bayar_value);
		tvHeaderTotalbayarTitle = (TextView) findViewById(R.id.sales_to_total_bayar_title);

		mButtonAddProduct = (Button) findViewById(R.id.sales_to_btn_add_product);
		mButtonSave = (Button) findViewById(R.id.sales_to_btn_save);

		tvTotalbayarValue.setTypeface(typefaceSmall);
		tvHeaderTotalbayarTitle.setTypeface(typefaceSmall);
		SharedPreferences spPreferences = getSharedPrefereces();
		main_app_id_staff = spPreferences.getString(
				CONFIG.SHARED_PREFERENCES_STAFF_ID_STAFF,
				null);
		String unNota = new SimpleDateFormat("yyyyMMddHHmmss",
				Locale.getDefault()).format(new Date());
		tvNoNotaValue.setText(unNota+main_app_id_staff);
 		mButtonSave.setOnClickListener(buttonOnClickListener);
		mButtonAddProduct.setOnClickListener(buttonOnClickListener);

	}


	public void showCustomDialogSaveSuccess(String msg) {
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
								gotoInventory();
							}
						});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();

	}

	public String zero(int num) {
		String number = (num < 10) ? ("0" + num) : ("" + num);
		return number;
	}

	/**
	 * Validasi Empty Box
	 *
	 * @return
	 */
	public boolean passValidationForUpload() {
		if (GlobalApp.isBlank(etNamaCustomer)) {
			GlobalApp.takeDefaultAction(
					etNamaCustomer,
					SalesKanvasActivity.this,
					getApplicationContext().getResources().getString(
							R.string.app_sales_to_failed_no_nama));
			return false;
		}
		else if (GlobalApp.isBlank(etAlamat)) {
			GlobalApp.takeDefaultAction(
					etAlamat,
					SalesKanvasActivity.this,
					getApplicationContext().getResources().getString(
							R.string.app_sales_to_failed_no_alamat));
			return false;
		}
		else if (GlobalApp.isBlank(etKeterangan)) {
			GlobalApp.takeDefaultAction(
					etKeterangan,
					SalesKanvasActivity.this,
					getApplicationContext().getResources().getString(
							R.string.app_sales_to_failed_no_keterangan));
			return false;
		}
		return true;
	}

	private final OnClickListener buttonOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			int getId = arg0.getId();
			switch (getId) {
				case R.id.sales_to_btn_add_product:
					if(isRunningDummyVersion()) {
						ChooseProductDialog();
					}
					else {
						int countProduct = databaseHandler.getCountStockVan();
						if (countProduct == 0) {
							String msg = getApplicationContext()
									.getResources()
									.getString(R.string.app_inventory_physical_counting_failed_empty_data_stock_van);
							showCustomDialog(msg);
						} else {
							ChooseProductDialog();
						}
					}

					break;
				case R.id.sales_to_btn_save:
					if(passValidationForUpload()) {
						nama = etNamaCustomer.getText().toString();
						alamat = etAlamat.getText().toString();
						keterangan = etKeterangan.getText().toString();
						no_penjualan = tvNoNotaValue.getText().toString();
						if (GlobalApp.checkInternetConnection(act)) {
							new UploadPenjualanKeServer().execute();
						} else {
							String message = act
									.getApplicationContext()
									.getResources()
									.getString(
											R.string.MSG_DLG_LABEL_CHECK_INTERNET_CONNECTION);
							showCustomDialog(message);
						}
//						for (DetailPenjualan detailPenjualan : detailPenjualanList) {
//
//						}
//						String msg = getApplicationContext()
//								.getResources()
//								.getString(
//										R.string.app_inventory_unload_product_target_penjualan_add_save_success);
//						showCustomDialogSaveSuccess(msg);
					}
					break;
				default:
					break;
			}
		}

	};


	/**
	 * Setup the URL Sales Order
	 *
	 * @return
	 */
	public String prepareUrlForPenjualan() {
		return String.format(CONFIG.CONFIG_APP_URL_PUBLIC
				+ CONFIG.CONFIG_APP_URL_UPLOAD_INSERT_SALES);
	}

	/**
	 * Post Penjualan ke Server
	 *
	 * @return
	 */
	public String postDataForPenjualan() {
		String textUrl = prepareUrlForPenjualan();
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		HttpResponse response;
		nameValuePairs.add(new BasicNameValuePair("no_penjualan",
				no_penjualan));
		nameValuePairs.add(new BasicNameValuePair("type_penjualan", "0"));
		nameValuePairs.add(new BasicNameValuePair("nama_customer",
				nama));
		nameValuePairs.add(new BasicNameValuePair("alamat", alamat));
		nameValuePairs.add(new BasicNameValuePair("id_staff", main_app_id_staff));
		nameValuePairs.add(new BasicNameValuePair("keterangan", keterangan));
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(textUrl);
		String responseString = null;
		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			response = httpclient.execute(httppost);
			HttpEntity r_entity = response.getEntity();
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				// Server response
				responseString = EntityUtils.toString(r_entity);
			} else {
				responseString = "Error occurred! Http Status Code: "
						+ statusCode;
			}

		} catch (UnsupportedEncodingException e1) {
			response = null;
		} catch (IOException e) {
			response = null;
		}
		return responseString;
	}

	class UploadPenjualanKeServer extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
			progressDialog.setTitle(getApplicationContext().getResources()
					.getString(R.string.app_name));
			progressDialog.setMessage(getApplicationContext().getResources()
					.getString(R.string.app_sales_to_processing));
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {

			response_data = postDataForPenjualan();

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (response_data != null) {
				if (response_data.startsWith("Error occurred")) {
					message = act
							.getApplicationContext()
							.getResources()
							.getString(
									R.string.app_sales_to_processing_error);
					handler.post(new Runnable() {
						public void run() {
							showCustomDialog(message);
						}
					});
				} else {
					JSONObject objectResponse;
					String status = "false";
					Log.d(LOG_TAG, "response_data:" + response_data);
					try {
						objectResponse = new JSONObject(response_data);
						status = objectResponse.isNull("error") ? "True"
								: objectResponse.getString("error");
						Log.d(LOG_TAG, "status:" + status);
						if (status.equalsIgnoreCase("True")) {
							final String msg = act
									.getApplicationContext()
									.getResources()
									.getString(
											R.string.app_sales_to_processing_failed);
							handler.post(new Runnable() {
								public void run() {
									showCustomDialog(msg);
								}
							});
						} else {
							if (progressDialog != null) {
								progressDialog.dismiss();
							}
							new UpdatePenjualanDetailToServer().execute();

						}
					} catch (JSONException e) {
						final String msg = getApplicationContext()
								.getResources()
								.getString(
										R.string.app_sales_to_processing_error);
						handler.post(new Runnable() {
							public void run() {
								showCustomDialog(msg);
							}
						});
					}
				}
			} else {
				final String msg = getApplicationContext().getResources()
						.getString(R.string.app_sales_to_processing_failed);
				handler.post(new Runnable() {
					public void run() {
						showCustomDialog(msg);
					}
				});
			}
		}
	}

	/**
	 * Setup the URL Sales Order
	 *
	 * @return
	 */
	public String prepareUrlForPenjualanDetail() {
		return String.format(CONFIG.CONFIG_APP_URL_PUBLIC
				+ CONFIG.CONFIG_APP_URL_UPLOAD_INSERT_SALES_DETAIL);
	}

	/**
	 * Post Add Sales Order ke Server
	 *
	 * @return
	 */
	public String postDataForPenjualanDetail(String no_penjualan, String id_product,
											 String jumlah,  String unit) {
		String textUrl = prepareUrlForPenjualanDetail();
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		HttpResponse response;
		nameValuePairs.add(new BasicNameValuePair("no_penjualan", no_penjualan));
		nameValuePairs.add(new BasicNameValuePair("id_product", id_product));
		nameValuePairs.add(new BasicNameValuePair("jumlah", jumlah));
		nameValuePairs.add(new BasicNameValuePair("unit", unit));
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(textUrl);
		String responseString = null;
		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			response = httpclient.execute(httppost);
			HttpEntity r_entity = response.getEntity();
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				// Server response
				responseString = EntityUtils.toString(r_entity);
			} else {
				responseString = "Error occurred! Http Status Code: "
						+ statusCode;
			}

		} catch (UnsupportedEncodingException e1) {
			response = null;
		} catch (IOException e) {
			response = null;
		}
		return responseString;
	}

	class UpdatePenjualanDetailToServer extends
			AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
			progressDialog.setTitle(getApplicationContext().getResources()
					.getString(R.string.app_name));
			progressDialog.setMessage(getApplicationContext().getResources()
					.getString(R.string.app_sales_to_processing));
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {

			for (NewDetailPenjualan detailPenjualan : detailPenjualanList) {
				response_data = postDataForPenjualanDetail(
						no_penjualan, String.valueOf(detailPenjualan.getId_product()),
						String.valueOf(detailPenjualan.getJumlah_order()), detailPenjualan.getUnit());
				if (response_data.startsWith("Error occurred")) {
					break;
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (response_data != null) {
				if (response_data.startsWith("Error occurred")) {
					message = act
							.getApplicationContext()
							.getResources()
							.getString(
									R.string.app_sales_to_processing_error);
					handler.post(new Runnable() {
						public void run() {
							showCustomDialog(message);
						}
					});
				} else {
					JSONObject objectResponse;
					String status = "false";
					Log.d(LOG_TAG, "response_data:" + response_data);
					try {
						objectResponse = new JSONObject(response_data);
						status = objectResponse.isNull("error") ? "True"
								: objectResponse.getString("error");
						Log.d(LOG_TAG, "status:" + status);
					} catch (JSONException e) {
						final String msg = getApplicationContext()
								.getResources()
								.getString(
										R.string.app_sales_to_processing_error);
						handler.post(new Runnable() {
							public void run() {
								showCustomDialog(msg);
							}
						});
					}

					if (status.equalsIgnoreCase("True")) {
						final String msg = act
								.getApplicationContext()
								.getResources()
								.getString(
										R.string.app_sales_to_processing_failed);
						handler.post(new Runnable() {
							public void run() {
								showCustomDialog(msg);
							}
						});
					} else {
						final String msg = getApplicationContext()
								.getResources()
								.getString(
										R.string.app_sales_to_processing_sukses);
						handler.post(new Runnable() {
							public void run() {
								showCustomDialogPenjualanSuccess(msg);
							}
						});

					}

				}
			} else {
				final String msg = getApplicationContext().getResources()
						.getString(R.string.app_sales_to_processing_failed);
				handler.post(new Runnable() {
					public void run() {
						showCustomDialog(msg);
					}
				});
			}
		}
	}

	public void showCustomDialogPenjualanSuccess(String msg) {
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
								refreshStatus();
							}
						});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}

	public void refreshStatus() {
		finish();
		startActivity(getIntent());
	}

	protected boolean isRunningDummyVersion() {
		SharedPreferences spPreferences = getSharedPrefereces();
		String main_app_staff_username = spPreferences.getString(
				CONFIG.SHARED_PREFERENCES_STAFF_USERNAME, null);
		if (main_app_staff_username != null) {
			return main_app_staff_username.equalsIgnoreCase("demo");
		}
		return false;
	}

	private void ChooseProductDialog() {
		final Dialog chooseProductDialog = new Dialog(act);
		chooseProductDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		chooseProductDialog
				.setContentView(R.layout.activity_main_product_choose_dialog);
		chooseProductDialog.setCanceledOnTouchOutside(false);
		chooseProductDialog.setCancelable(true);
		chooseProductDialog
				.setOnCancelListener(new DialogInterface.OnCancelListener() {
					@Override
					public void onCancel(DialogInterface dialog) {
						chooseProductDialog.dismiss();
					}
				});
		TextView tvHeaderKodeProduct = (TextView) chooseProductDialog
				.findViewById(R.id.activity_sales_order_title_kode_product);
		TextView tvHeaderNamaProduct = (TextView) chooseProductDialog
				.findViewById(R.id.activity_sales_order_title_nama_product);
		TextView tvHeaderHargaProduct = (TextView) chooseProductDialog
				.findViewById(R.id.activity_sales_order_title_harga_product);
		TextView tvHeaderAction = (TextView) chooseProductDialog
				.findViewById(R.id.activity_sales_order_title_action);
		tvHeaderKodeProduct.setTypeface(typefaceSmall);
		tvHeaderNamaProduct.setTypeface(typefaceSmall);
		tvHeaderHargaProduct.setTypeface(typefaceSmall);
		tvHeaderAction.setTypeface(typefaceSmall);
		EditText searchProduct = (EditText) chooseProductDialog
				.findViewById(R.id.activity_product_edittext_search);
		final ListView listview = (ListView) chooseProductDialog
				.findViewById(R.id.list);
		final EditText jumlahPieces = (EditText) chooseProductDialog
				.findViewById(R.id.activity_product_edittext_pieces);
		final EditText jumlahPack = (EditText) chooseProductDialog
				.findViewById(R.id.activity_product_edittext_pack);
		final EditText jumlahDus = (EditText) chooseProductDialog
				.findViewById(R.id.activity_product_edittext_dus);

		listview.setItemsCanFocus(false);

		stokvan_from_db.clear();
		if(isRunningDummyVersion()) {
			stokvan_from_db.add(new StockVan(1, "Item 1", "MASFA1",
					"30000", 10, 10, 10, "1", "",""));
			stokvan_from_db.add(new StockVan(2, "Item 3", "MASFA3",
					"50000", 20, 20,20,"1", "",""));
			stokvan_from_db.add(new StockVan(3, "Item 5", "MASFA5",
					"150000", 70, 70, 70, "1", "",""));
		}
		else {
			stokvan_from_db = databaseHandler.getAllStockVan();
		}

		if (stokvan_from_db.size() > 0) {
			listview.setVisibility(View.VISIBLE);
			cAdapterChooseAdapter = new ListViewChooseAdapter(
					SalesKanvasActivity.this,
					R.layout.list_item_product_sales_order, jumlahPieces,
					jumlahPack,
					jumlahDus,
					stokvan_from_db, chooseProductDialog);
			listview.setAdapter(cAdapterChooseAdapter);
			cAdapterChooseAdapter.notifyDataSetChanged();
		} else {
			listview.setVisibility(View.INVISIBLE);
		}

		searchProduct.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
									  int arg3) {
				if (cs.toString().length() > 0) {
					stokvan_from_db.clear();
					stokvan_from_db = databaseHandler
							.getAllStockVanBaseOnSearch(cs.toString());
					if (stokvan_from_db.size() > 0) {
						listview.setVisibility(View.VISIBLE);
						cAdapterChooseAdapter = new ListViewChooseAdapter(
								SalesKanvasActivity.this,
								R.layout.list_item_product_sales_order,
								jumlahPieces,
								jumlahPack,
								jumlahDus, stokvan_from_db,
								chooseProductDialog);
						listview.setAdapter(cAdapterChooseAdapter);
						cAdapterChooseAdapter.notifyDataSetChanged();
					} else {
						listview.setVisibility(View.INVISIBLE);
					}

				} else {
					stokvan_from_db.clear();
					stokvan_from_db = databaseHandler
							.getAllStockVan();
					if (stokvan_from_db.size() > 0) {
						listview.setVisibility(View.VISIBLE);
						cAdapterChooseAdapter = new ListViewChooseAdapter(
								SalesKanvasActivity.this,
								R.layout.list_item_product_sales_order,
								jumlahPieces,
								jumlahPack,
								jumlahDus, stokvan_from_db,
								chooseProductDialog);
						listview.setAdapter(cAdapterChooseAdapter);
						cAdapterChooseAdapter.notifyDataSetChanged();
					} else {
						listview.setVisibility(View.INVISIBLE);
					}
				}
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
										  int arg2, int arg3) {

			}

			@Override
			public void afterTextChanged(Editable arg0) {

			}
		});
		Handler handler = new Handler();
		handler.post(new Runnable() {
			public void run() {
				chooseProductDialog.show();
			}
		});
	}

	private void updateListViewDetailOrder(NewDetailPenjualan detailPenjualan) {
		detailPenjualanList.add(detailPenjualan);

		listview.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				scrollView.requestDisallowInterceptTouchEvent(true);
				int action = event.getActionMasked();
				switch (action) {
					case MotionEvent.ACTION_UP:
						scrollView.requestDisallowInterceptTouchEvent(false);
						break;
				}
				return false;
			}
		});

		cAdapter = new ListViewAdapter(SalesKanvasActivity.this,
				R.layout.list_item_detail_new_penjualan, detailPenjualanList);
		listview.setAdapter(cAdapter);
		cAdapter.notifyDataSetChanged();
		updateTotalBayar();
	}

	private void updateTotalBayar() {
		int totalBayar = 0;
		for (NewDetailPenjualan tempDetailPenjualan : detailPenjualanList) {
			totalBayar += Integer.parseInt(tempDetailPenjualan.getHarga_jual())
					* tempDetailPenjualan.getJumlah_order();
		}

		String totalBayarTemp = String.valueOf(totalBayar);
		Float priceIDR = Float.valueOf(totalBayarTemp);
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
		otherSymbols.setDecimalSeparator(',');
		otherSymbols.setGroupingSeparator('.');
		DecimalFormat df = new DecimalFormat("#,##0", otherSymbols);
		tvTotalbayarValue.setText("Rp. " + df.format(priceIDR));
	}

	public class ListViewAdapter extends ArrayAdapter<NewDetailPenjualan> {
		Activity activity;
		int layoutResourceId;
		ArrayList<NewDetailPenjualan> data = new ArrayList<NewDetailPenjualan>();

		public ListViewAdapter(Activity act, int layoutResourceId,
							   ArrayList<NewDetailPenjualan> data) {
			super(act, layoutResourceId, data);
			this.layoutResourceId = layoutResourceId;
			this.activity = act;
			this.data = data;
			notifyDataSetChanged();
		}

		@Override
		public View getView(final int position, View convertView,
							ViewGroup parent) {
			View row = convertView;
			UserHolder holder = null;

			if (row == null) {
				LayoutInflater inflater = LayoutInflater.from(activity);

				row = inflater.inflate(layoutResourceId, parent, false);
				holder = new UserHolder();
				holder.list_kode_product = (TextView) row
						.findViewById(R.id.sales_kode_product);
				holder.list_jumlah_order = (TextView) row
						.findViewById(R.id.sales_jumlah_order);
				holder.list_harga_jual = (TextView) row
						.findViewById(R.id.sales_harga);
				holder.list_unit = (TextView) row
						.findViewById(R.id.sales_unit);

				row.setTag(holder);
			} else {
				holder = (UserHolder) row.getTag();
			}
			holder.list_kode_product.setText(data.get(position)
					.getKode_product());
			holder.list_jumlah_order.setText(String.valueOf(data.get(position)
					.getJumlah_order()));
			Float priceIDR = Float.valueOf(data.get(position).getHarga_jual());
			DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
			otherSymbols.setDecimalSeparator(',');
			otherSymbols.setGroupingSeparator('.');

			DecimalFormat df = new DecimalFormat("#,##0", otherSymbols);
			holder.list_harga_jual.setText("Rp. " + df.format(priceIDR));
			holder.list_unit.setText(String.valueOf(data.get(position)
					.getUnit()));
			holder.list_kode_product.setTypeface(typefaceSmall);
			holder.list_jumlah_order.setTypeface(typefaceSmall);
			holder.list_harga_jual.setTypeface(typefaceSmall);
			holder.list_unit.setTypeface(typefaceSmall);

			return row;

		}

		class UserHolder {
			TextView list_kode_product;
			TextView list_jumlah_order;
			TextView list_harga_jual;
			TextView list_unit;
		}

	}

	class ListViewChooseAdapter extends ArrayAdapter<StockVan> {
		int layoutResourceId;
		StockVan productData;
		ArrayList<StockVan> data = new ArrayList<StockVan>();
		Activity mainActivity;
		EditText jumlahPieces;
		EditText jumlahPack;
		EditText jumlahDus;
		Dialog chooseProductDialog;

		public ListViewChooseAdapter(Activity mainActivity,
									 int layoutResourceId,
									 EditText jumlahPieces,
									 EditText jumlahPack,
									 EditText jumlahDus,
									 ArrayList<StockVan> data, Dialog chooseProductDialog) {
			super(mainActivity, layoutResourceId, data);
			this.layoutResourceId = layoutResourceId;
			this.data = data;
			this.chooseProductDialog = chooseProductDialog;
			this.mainActivity = mainActivity;
			this.jumlahPieces = jumlahPieces;
			this.jumlahPack = jumlahPack;
			this.jumlahDus = jumlahDus;
			notifyDataSetChanged();
		}

		@Override
		public View getView(final int position, View convertView,
							ViewGroup parent) {
			View row = convertView;
			UserHolder holder = null;

			if (row == null) {
				LayoutInflater inflater = LayoutInflater.from(mainActivity);

				row = inflater.inflate(layoutResourceId, parent, false);
				holder = new UserHolder();
				holder.list_kodeProduct = (TextView) row
						.findViewById(R.id.sales_order_title_kode_product);
				holder.list_namaProduct = (TextView) row
						.findViewById(R.id.sales_order_title_nama_product);
				holder.list_harga = (TextView) row
						.findViewById(R.id.sales_order_title_harga_product);
				holder.mButtonAddItem = (Button) row
						.findViewById(R.id.sales_order_dialog_btn_add_item);

				row.setTag(holder);
			} else {
				holder = (UserHolder) row.getTag();
			}
			productData = data.get(position);
			holder.list_kodeProduct.setText(productData.getKode_product());
			holder.list_namaProduct.setText(productData.getNama_product());
			Float priceIDR = Float.valueOf(productData.getHarga_jual());
			DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
			otherSymbols.setDecimalSeparator(',');
			otherSymbols.setGroupingSeparator('.');

			DecimalFormat df = new DecimalFormat("#,##0", otherSymbols);
			holder.list_harga.setText("Rp. " + df.format(priceIDR));
			holder.list_kodeProduct.setTypeface(getTypefaceSmall());
			holder.list_namaProduct.setTypeface(getTypefaceSmall());
			holder.list_harga.setTypeface(getTypefaceSmall());
			holder.mButtonAddItem.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (jumlahPieces.getText().toString().length() > 0 && !jumlahPieces.getText().toString().equalsIgnoreCase("0")) {
						boolean containSameProduct = false;
						for (NewDetailPenjualan detailPenjualan : detailPenjualanList) {
							if (detailPenjualan.getKode_product()
									.equalsIgnoreCase(
											data.get(position)
													.getKode_product())) {
								containSameProduct = true;
								break;
							}
						}
						if (containSameProduct) {
							String msg = getApplicationContext()
									.getResources()
									.getString(
											R.string.app_sales_order_failed_please_add_another_item);
							showCustomDialog(msg);
						} else {
							int count = detailPenjualanList.size() + 1;
							updateListViewDetailOrder(new NewDetailPenjualan(
									count, data.get(position).getId_product(),
									data.get(position).getNama_product(), data
									.get(position).getKode_product(),
									data.get(position).getHarga_jual(), Integer
									.parseInt(jumlahPieces.getText()
											.toString()), "Pcs"));
							chooseProductDialog.hide();
						}
					}
					else if (jumlahPack.getText().toString().length() > 0 && !jumlahPack.getText().toString().equalsIgnoreCase("0")) {
						boolean containSameProduct = false;
						for (NewDetailPenjualan detailPenjualan : detailPenjualanList) {
							if (detailPenjualan.getKode_product()
									.equalsIgnoreCase(
											data.get(position)
													.getKode_product())) {
								containSameProduct = true;
								break;
							}
						}
						if (containSameProduct) {
							String msg = getApplicationContext()
									.getResources()
									.getString(
											R.string.app_sales_order_failed_please_add_another_item);
							showCustomDialog(msg);
						} else {
							int count = detailPenjualanList.size() + 1;
							updateListViewDetailOrder(new NewDetailPenjualan(
									count, data.get(position).getId_product(),
									data.get(position).getNama_product(), data
									.get(position).getKode_product(),
									data.get(position).getHarga_jual(), Integer
									.parseInt(jumlahPack.getText()
											.toString()), "Pack"));
							chooseProductDialog.hide();
						}
					}
					else if (jumlahDus.getText().toString().length() > 0 && !jumlahDus.getText().toString().equalsIgnoreCase("0")) {
						boolean containSameProduct = false;
						for (NewDetailPenjualan detailPenjualan : detailPenjualanList) {
							if (detailPenjualan.getKode_product()
									.equalsIgnoreCase(
											data.get(position)
													.getKode_product())) {
								containSameProduct = true;
								break;
							}
						}
						if (containSameProduct) {
							String msg = getApplicationContext()
									.getResources()
									.getString(
											R.string.app_sales_order_failed_please_add_another_item);
							showCustomDialog(msg);
						} else {
							int count = detailPenjualanList.size() + 1;
							updateListViewDetailOrder(new NewDetailPenjualan(
									count, data.get(position).getId_product(),
									data.get(position).getNama_product(), data
									.get(position).getKode_product(),
									data.get(position).getHarga_jual(), Integer
									.parseInt(jumlahDus.getText()
											.toString()), "Dus"));
							chooseProductDialog.hide();
						}
					}

					else {
						String msg = getApplicationContext()
								.getResources()
								.getString(
										R.string.app_sales_order_failed_please_add_jumlah);
						showCustomDialog(msg);

					}
				}
			});
			return row;

		}

		class UserHolder {
			TextView list_kodeProduct;
			TextView list_namaProduct;
			TextView list_harga;
			Button mButtonAddItem;
		}

	}

	public void showCustomDialog(String msg) {
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

	public void gotoInventory() {
		Intent i = new Intent(this, InventoryActivity.class);
		startActivity(i);
		finish();
	}

	public Typeface getTypefaceSmall() {
		return typefaceSmall;
	}

	public void setTypefaceSmall(Typeface typefaceSmall) {
		this.typefaceSmall = typefaceSmall;
	}
}
