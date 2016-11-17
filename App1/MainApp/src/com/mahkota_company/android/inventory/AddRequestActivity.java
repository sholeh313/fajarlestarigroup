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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.mahkota_company.android.R;
import com.mahkota_company.android.database.Branch;
import com.mahkota_company.android.database.Customer;
import com.mahkota_company.android.database.DatabaseHandler;
import com.mahkota_company.android.database.DetailReqLoad;
import com.mahkota_company.android.database.DetailSalesOrder;
import com.mahkota_company.android.database.Jadwal;
import com.mahkota_company.android.database.Product;
import com.mahkota_company.android.database.ReqLoad;
import com.mahkota_company.android.database.SalesOrder;
import com.mahkota_company.android.utils.CONFIG;
import com.mahkota_company.android.utils.GlobalApp;
import com.mahkota_company.android.utils.SpinnerAdapter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddRequestActivity extends FragmentActivity {
	private Context act;
	private ImageView menuBackButton;
	private DatabaseHandler databaseHandler;
	private Typeface typefaceSmall;
	private Button mButtonAddProduct;
	private Button mButtonSave;
	private Button mButtonCancel;
	private Button mButtonTTD;
	private Handler handler = new Handler();
	private static final String LOG_TAG = RequestActivity.class
			.getSimpleName();
	private ProgressDialog progressDialog;
	public ArrayList<Customer> customerList;
	public ArrayList<String> customerStringList;
	private String kodeCustomer;
	private Spinner spinnerPromosi;
	public ArrayList<String> promosiStringList;
	private int idPromosi = -1;
	private String response_data;
	private ListViewChooseAdapter cAdapterChooseAdapter;
	public ArrayList<DetailReqLoad> detailReqLoadList = new ArrayList<DetailReqLoad>();
	private ListView listview;
	private ListViewAdapter cAdapter;
	private Jadwal jadwal;
	private String status_update;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_request);
		act = this;
		databaseHandler = new DatabaseHandler(this);
		menuBackButton = (ImageView) findViewById(R.id.menuBackButton);
		menuBackButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				gotoInventory();
			}
		});
		mButtonCancel = (Button) findViewById(R.id.activity_sales_order_btn_cancel);
		mButtonCancel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				gotoInventory();
			}
		});
		typefaceSmall = Typeface.createFromAsset(getAssets(),
				"fonts/AliquamREG.ttf");
		listview = (ListView) findViewById(R.id.list);
		listview.setItemsCanFocus(false);
		spinnerPromosi = (Spinner) findViewById(R.id.activity_sales_order_promosi_value);
		mButtonAddProduct = (Button) findViewById(R.id.activity_sales_order_btn_add_product);
		mButtonSave = (Button) findViewById(R.id.activity_sales_order_btn_save);
		mButtonTTD = (Button) findViewById(R.id.ttd_customer);

		mButtonTTD.setVisibility(View.INVISIBLE);

		customerList = new ArrayList<Customer>();
		customerStringList = new ArrayList<String>();
		SharedPreferences spPreferences = getSharedPrefereces();
		String main_app_staff_username = spPreferences.getString(
				CONFIG.SHARED_PREFERENCES_STAFF_USERNAME, null);

		promosiStringList = new ArrayList<String>();
		promosiStringList.add("Tidak Ada");

		mButtonSave.setOnClickListener(maddSalesOrderButtonOnClickListener);
		mButtonTTD.setOnClickListener(maddSalesOrderButtonOnClickListener);
		mButtonAddProduct.setOnClickListener(maddSalesOrderButtonOnClickListener);
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

	private final OnClickListener maddSalesOrderButtonOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			int getId = arg0.getId();
			switch (getId) {
			case R.id.activity_sales_order_btn_add_product:
				int countProduct = databaseHandler.getCountProduct();
				if (countProduct == 0) {
					String msg = getApplicationContext()
							.getResources()
							.getString(R.string.app_sales_order_no_data_product);
					showCustomDialog(msg);
				} else {
					ChooseProductDialog();
				}
				break;
			case R.id.ttd_customer:
					BukaTTD();

					break;
			case R.id.activity_sales_order_btn_save:
                    String timeStamp = new SimpleDateFormat("HHmmss",
					Locale.getDefault()).format(new Date());

                    final String date = "yyyyMMdd";
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat(date);
					final String dateOutput = dateFormat.format(calendar
                            .getTime());

					int countData = databaseHandler.getCountReqLoad();
					countData = countData + 1;
					String datetime = dateOutput + timeStamp + countData;

					SharedPreferences spPreferences = getSharedPrefereces();
					String kodeBranch = spPreferences.getString(
                            CONFIG.SHARED_PREFERENCES_STAFF_KODE_BRANCH, null);
					String username = spPreferences.getString(
                            CONFIG.SHARED_PREFERENCES_STAFF_USERNAME, null);
					Branch branch = databaseHandler.getBranch(Integer
							.parseInt(kodeBranch));
					String nomerOrder = CONFIG.CONFIG_APP_KODE_SO_HEADER
							+ branch.getKode_branch() + "." + datetime
							+ countData;

					final String date2 = "yyyy-MM-dd";
					Calendar calendar2 = Calendar.getInstance();
					SimpleDateFormat dateFormat2 = new SimpleDateFormat(date2);
					final String checkDate = dateFormat2.format(calendar2
							.getTime());
					Calendar now = Calendar.getInstance();
					int hrs = now.get(Calendar.HOUR_OF_DAY);
					int min = now.get(Calendar.MINUTE);
					int sec = now.get(Calendar.SECOND);
					final String time = zero(hrs) + zero(min) + zero(sec);

					ArrayList<ReqLoad> tempReq_load_list = databaseHandler
							.getAllReqLoad();
					int tempIndex = 0;
					for (ReqLoad reqLoad : tempReq_load_list) {
						tempIndex = reqLoad.getId_sales_order();
						}
					int index = 1;
					for (DetailReqLoad detailReqLoad : detailReqLoadList) {
                        ReqLoad reqLoad = new ReqLoad();
					    reqLoad.setId_sales_order(tempIndex + index);
						reqLoad.setDate_order(checkDate);
						reqLoad.setHarga_jual(detailReqLoad
								.getHarga_jual());

							reqLoad.setJumlah_order(detailReqLoad.getJumlah_order());
							reqLoad.setJumlah_order1(detailReqLoad.getJumlah_order1());
							reqLoad.setJumlah_order2(detailReqLoad.getJumlah_order2());
							reqLoad.setJumlah_order3(detailReqLoad.getJumlah_order3());


						reqLoad.setNama_product(detailReqLoad
								.getNama_product());
						reqLoad.setNomer_order(nomerOrder);
						reqLoad.setNomer_order_detail(nomerOrder
								+ String.valueOf(countData));
						reqLoad.setTime_order(time);
						reqLoad.setUsername(username);
						databaseHandler.add_ReqLoad(reqLoad);
						index += 1;
						}
						String msg = getApplicationContext().getResources()
								.getString(R.string.app_req_load_save_success);
						showCustomDialogSaveSuccess(msg);

				//UploadHasil();

				break;
			default:
				break;
			}
		}

	};

	private void UploadHasil() {
		if (GlobalApp.checkInternetConnection(act)) {
			int countUpload = databaseHandler.getCountReqLoad();
			if (countUpload == 0) {
				String message = act
						.getApplicationContext()
						.getResources()
						.getString(
								R.string.app_sales_order_processing_upload_no_data);
				showCustomDialog(message);
			} else {
				new UploadData().execute();
			}
		} else {
			String message = act
					.getApplicationContext()
					.getResources()
					.getString(
							R.string.app_sales_order_processing_upload_empty);
			showCustomDialog(message);
		}
	}

	public class UploadData extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
			progressDialog.setMessage(getApplicationContext().getResources()
					.getString(R.string.app_sales_order_processing_upload));
			progressDialog.show();
			progressDialog
					.setOnCancelListener(new DialogInterface.OnCancelListener() {
						@Override
						public void onCancel(DialogInterface dialog) {
							String msg = getApplicationContext()
									.getResources()
									.getString(
											R.string.MSG_DLG_LABEL_SYNRONISASI_DATA_CANCEL);
							showCustomDialog(msg);
						}
					});
		}

		@Override
		protected String doInBackground(String... params) {
			String url_add_sales_order = CONFIG.CONFIG_APP_URL_PUBLIC
					+ CONFIG.CONFIG_APP_URL_UPLOAD_REQ_LOAD;

			List<ReqLoad> dataReqLoad = databaseHandler
					.getAllReqLoad();
			for (ReqLoad reqLoad : dataReqLoad) {
				if (reqLoad.getId_promosi() == -1) {
					response_data = uploadReqLoad(url_add_sales_order,
							reqLoad.getNomer_order(),
							reqLoad.getNomer_order_detail(),
							reqLoad.getDate_order(),
							reqLoad.getTime_order(),
							//reqLoad.getDeskripsi(),
							String.valueOf("0"),
							reqLoad.getUsername(),
							//reqLoad.getKode_customer(),
							//reqLoad.getAlamat(),
							//reqLoad.getNama_lengkap(),
							reqLoad.getNama_product(),
							//reqLoad.getKode_product(),
							String.valueOf(reqLoad.getHarga_jual()),
							String.valueOf(reqLoad.getJumlah_order()),
							String.valueOf(reqLoad.getJumlah_order1()),
							String.valueOf(reqLoad.getJumlah_order2()),
							String.valueOf(reqLoad.getJumlah_order3()));

				} else {
					response_data = uploadReqLoad(url_add_sales_order,
							reqLoad.getNomer_order(),
							reqLoad.getNomer_order_detail(),
							reqLoad.getDate_order(),
							reqLoad.getTime_order(),
							//reqLoad.getDeskripsi(),
							String.valueOf(reqLoad.getId_promosi()),
							reqLoad.getUsername(),
							//reqLoad.getKode_customer(),
							//reqLoad.getAlamat(),
							//reqLoad.getNama_lengkap(),
							reqLoad.getNama_product(),
							//reqLoad.getKode_product(),
							String.valueOf(reqLoad.getHarga_jual()),
							String.valueOf(reqLoad.getJumlah_order()),
							String.valueOf(reqLoad.getJumlah_order1()),
							String.valueOf(reqLoad.getJumlah_order2()),
							String.valueOf(reqLoad.getJumlah_order3()));
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			List<ReqLoad> dataAddReqLoad = databaseHandler
					.getAllReqLoad();
			int countData = dataAddReqLoad.size();
			if (countData > 0) {
				try {
					Thread.sleep(dataAddReqLoad.size() * 1000 * 3);
				} catch (final InterruptedException e) {
					Log.d(LOG_TAG, "InterruptedException " + e.getMessage());
					handler.post(new Runnable() {
						public void run() {
							showCustomDialog(e.getMessage());
						}
					});
				}
				if (response_data != null && response_data.length() > 0) {
					if (response_data.startsWith("Error occurred")) {
						final String msg = act
								.getApplicationContext()
								.getResources()
								.getString(
										R.string.app_sales_order_processing_upload_failed);
						handler.post(new Runnable() {
							public void run() {
								showCustomDialog(msg);
							}
						});
					} else {
						handler.post(new Runnable() {
							public void run() {
								initUploadReqLoad();
							}
						});
					}
				}
			} else {
				final String msg = act
						.getApplicationContext()
						.getResources()
						.getString(
								R.string.app_sales_order_processing_upload_failed);
				handler.post(new Runnable() {
					public void run() {
						showCustomDialog(msg);
					}
				});
			}
		}
	}

	private String uploadReqLoad(final String url, final String nomer_order,
									final String nomer_order_detail, final String date_order,
									final String time_order, //final String deskripsi,
									final String id_promosi, final String username,
									//final String kode_customer, final String alamat,
									//final String nama_lengkap,
								 	final String nama_product,
									//final String kode_product,
								 	final String harga_jual,
									final String jumlah_order,
									final String jumlah_order1,
									final String jumlah_order2,
									final String jumlah_order3) {

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);
		String responseString = null;
		try {
			if (android.os.Build.VERSION.SDK_INT > 9) {
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
						.permitAll().build();
				StrictMode.setThreadPolicy(policy);
			}

			MultipartEntity entity = new MultipartEntity();

			entity.addPart("nomer_order", new StringBody(nomer_order));
			entity.addPart("nomer_order_detail", new StringBody(
					nomer_order_detail));
			entity.addPart("date_order", new StringBody(date_order));
			entity.addPart("time_order", new StringBody(time_order));
			//entity.addPart("deskripsi", new StringBody(deskripsi));
			entity.addPart("id_promosi", new StringBody(id_promosi));
			entity.addPart("username", new StringBody(username));
			//entity.addPart("kode_customer", new StringBody(kode_customer));
			//entity.addPart("alamat", new StringBody(alamat));
			//entity.addPart("nama_lengkap", new StringBody(nama_lengkap));
			entity.addPart("nama_product", new StringBody(nama_product));
			//entity.addPart("kode_product", new StringBody(kode_product));
			entity.addPart("harga_jual", new StringBody(harga_jual));
			entity.addPart("jumlah_order", new StringBody(jumlah_order));
			entity.addPart("jumlah_order1", new StringBody(jumlah_order1));
			entity.addPart("jumlah_order2", new StringBody(jumlah_order2));
			entity.addPart("jumlah_order3", new StringBody(jumlah_order3));

			httppost.setEntity(entity);

			// Making server call
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity r_entity = response.getEntity();

			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				// Server response
				responseString = EntityUtils.toString(r_entity);
			} else {
				responseString = "Error occurred! Http Status Code: "
						+ statusCode;
			}

		} catch (ClientProtocolException e) {
			responseString = e.toString();
		} catch (IOException e) {
			responseString = e.toString();
		}
		return responseString;
	}

	public void initUploadReqLoad() {
		JSONObject oResponse;
		try {
			oResponse = new JSONObject(response_data);
			String status = oResponse.isNull("error") ? "True" : oResponse
					.getString("error");
			if (response_data.isEmpty()) {
				final String msg = act
						.getApplicationContext()
						.getResources()
						.getString(
								R.string.app_sales_order_processing_upload_failed);
				showCustomDialog(msg);
			} else {
				Log.d(LOG_TAG, "status=" + status);
				if (status.equalsIgnoreCase("True")) {
					final String msg = act
							.getApplicationContext()
							.getResources()
							.getString(
									R.string.app_sales_order_processing_upload_failed);
					showCustomDialog(msg);
				} else {
					final String msg = act
							.getApplicationContext()
							.getResources()
							.getString(
									R.string.app_sales_order_processing_upload_success);
					CustomDialogUploadSuccess(msg);
				}

			}

		} catch (JSONException e) {
			final String message = e.toString();
			showCustomDialog(message);

		}
	}

	public void CustomDialogUploadSuccess(String msg) {
		if (progressDialog != null) {
			progressDialog.dismiss();
		}
		final android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(
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
								android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder
										.create();
								alertDialog.dismiss();
								databaseHandler.deleteTableReqLoad();
								finish();
								startActivity(getIntent());
							}
						});
		android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();

	}

	private void BukaTTD() {
		/*Intent intentActivity = new Intent(
				AddRequestActivity.this,
				AndroidCanvasExample.class);
		startActivity(intentActivity);
		*/
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
		final ArrayList<Product> product_list = new ArrayList<Product>();
		final ListView listview = (ListView) chooseProductDialog
				.findViewById(R.id.list);
		final EditText jumlahProduct = (EditText) chooseProductDialog
				.findViewById(R.id.activity_product_edittext_pieces);
		final EditText jumlahProduct1 = (EditText) chooseProductDialog
				.findViewById(R.id.activity_product_edittext_renceng);
		final EditText jumlahProduct2 = (EditText) chooseProductDialog
				.findViewById(R.id.activity_product_edittext_pack);
		final EditText jumlahProduct3 = (EditText) chooseProductDialog
				.findViewById(R.id.activity_product_edittext_dus);

		listview.setItemsCanFocus(false);
		ArrayList<Product> product_from_db = databaseHandler.getAllProduct();
		if (product_from_db.size() > 0) {
			listview.setVisibility(View.VISIBLE);
			for (int i = 0; i < product_from_db.size(); i++) {
				int id_product = product_from_db.get(i).getId_product();
				String nama_product = product_from_db.get(i).getNama_product();
				String kode_product = product_from_db.get(i).getKode_product();
				String harga_jual = product_from_db.get(i).getHarga_jual();
				String stock = product_from_db.get(i).getStock();
				String id_kemasan = product_from_db.get(i).getId_kemasan();
				String foto = product_from_db.get(i).getFoto();
				String deskripsi = product_from_db.get(i).getDeskripsi();

				Product product = new Product();
				product.setId_product(id_product);
				product.setNama_product(nama_product);
				product.setKode_product(kode_product);
				product.setHarga_jual(harga_jual);
				product.setStock(stock);
				product.setId_kemasan(id_kemasan);
				product.setFoto(foto);
				product.setDeskripsi(deskripsi);
				product_list.add(product);
				cAdapterChooseAdapter = new ListViewChooseAdapter(
						AddRequestActivity.this,
						R.layout.list_item_product_sales_order, jumlahProduct,
						jumlahProduct1, jumlahProduct2,jumlahProduct3,
						product_list, chooseProductDialog);
				listview.setAdapter(cAdapterChooseAdapter);
				cAdapterChooseAdapter.notifyDataSetChanged();
			}
		} else {
			listview.setVisibility(View.INVISIBLE);
		}

		searchProduct.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				if (cs.toString().length() > 0) {
					product_list.clear();
					ArrayList<Product> product_from_db = databaseHandler
							.getAllProductBaseOnSearch(cs.toString());
					if (product_from_db.size() > 0) {
						listview.setVisibility(View.VISIBLE);
						for (int i = 0; i < product_from_db.size(); i++) {
							int id_product = product_from_db.get(i)
									.getId_product();
							String nama_product = product_from_db.get(i)
									.getNama_product();
							String kode_product = product_from_db.get(i)
									.getKode_product();
							String harga_jual = product_from_db.get(i)
									.getHarga_jual();
							String stock = product_from_db.get(i).getStock();
							String id_kemasan = product_from_db.get(i)
									.getId_kemasan();
							String foto = product_from_db.get(i).getFoto();
							String deskripsi = product_from_db.get(i)
									.getDeskripsi();

							Product product = new Product();
							product.setId_product(id_product);
							product.setNama_product(nama_product);
							product.setKode_product(kode_product);
							product.setHarga_jual(harga_jual);
							product.setStock(stock);
							product.setId_kemasan(id_kemasan);
							product.setFoto(foto);
							product.setDeskripsi(deskripsi);
							product_list.add(product);
							cAdapterChooseAdapter = new ListViewChooseAdapter(
									AddRequestActivity.this,
									R.layout.list_item_product_sales_order,
									jumlahProduct,jumlahProduct1, jumlahProduct2,jumlahProduct3, product_list,
									chooseProductDialog);
							listview.setAdapter(cAdapterChooseAdapter);
							cAdapterChooseAdapter.notifyDataSetChanged();
						}
					} else {
						listview.setVisibility(View.INVISIBLE);
					}

				} else {
					ArrayList<Product> product_from_db = databaseHandler
							.getAllProduct();
					if (product_from_db.size() > 0) {
						listview.setVisibility(View.VISIBLE);
						for (int i = 0; i < product_from_db.size(); i++) {
							int id_product = product_from_db.get(i)
									.getId_product();
							String nama_product = product_from_db.get(i)
									.getNama_product();
							String kode_product = product_from_db.get(i)
									.getKode_product();
							String harga_jual = product_from_db.get(i)
									.getHarga_jual();
							String stock = product_from_db.get(i).getStock();
							String id_kemasan = product_from_db.get(i)
									.getId_kemasan();
							String foto = product_from_db.get(i).getFoto();
							String deskripsi = product_from_db.get(i)
									.getDeskripsi();

							Product product = new Product();
							product.setId_product(id_product);
							product.setNama_product(nama_product);
							product.setKode_product(kode_product);
							product.setHarga_jual(harga_jual);
							product.setStock(stock);
							product.setId_kemasan(id_kemasan);
							product.setFoto(foto);
							product.setDeskripsi(deskripsi);
							product_list.add(product);
							cAdapterChooseAdapter = new ListViewChooseAdapter(
									AddRequestActivity.this,
									R.layout.list_item_product_sales_order,
									jumlahProduct, jumlahProduct1, jumlahProduct2, jumlahProduct3, product_list,
									chooseProductDialog);
							listview.setAdapter(cAdapterChooseAdapter);
							cAdapterChooseAdapter.notifyDataSetChanged();

						}
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

	private void updateListViewDetailOrder(DetailReqLoad detailReqLoad) {
		detailReqLoadList.add(detailReqLoad);
		cAdapter = new ListViewAdapter(AddRequestActivity.this,
				R.layout.list_item_detail_sales_order, detailReqLoadList);
		listview.setAdapter(cAdapter);
		cAdapter.notifyDataSetChanged();
	}

	public class ListViewAdapter extends ArrayAdapter<DetailReqLoad> {
		Activity activity;
		int layoutResourceId;
		DetailReqLoad productData;
		ArrayList<DetailReqLoad> data = new ArrayList<DetailReqLoad>();

		public ListViewAdapter(Activity act, int layoutResourceId,
				ArrayList<DetailReqLoad> data) {
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
						.findViewById(R.id.sales_order_title_kode_product);
				holder.list_harga_jual = (TextView) row
						.findViewById(R.id.sales_order_title_harga);
				holder.list_jumlah_order = (TextView) row
						.findViewById(R.id.sales_order_title_jumlah_order);
				holder.list_jumlah_order1 = (TextView) row
						.findViewById(R.id.sales_order_title_jumlah_order1);
				holder.list_jumlah_order2 = (TextView) row
						.findViewById(R.id.sales_order_title_jumlah_order2);
				holder.list_jumlah_order3 = (TextView) row
						.findViewById(R.id.sales_order_title_jumlah_order3);

				row.setTag(holder);
			} else {
				holder = (UserHolder) row.getTag();
			}
			productData = data.get(position);
			holder.list_kode_product.setText(productData.getKode_product());

				holder.list_jumlah_order.setText(productData.getJumlah_order());
				holder.list_jumlah_order1.setText(productData.getJumlah_order1());
				holder.list_jumlah_order2.setText(productData.getJumlah_order2());
				holder.list_jumlah_order3.setText(productData.getJumlah_order3());

			Float priceIDR = Float.valueOf(productData.getHarga_jual());
			DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
			otherSymbols.setDecimalSeparator(',');
			otherSymbols.setGroupingSeparator('.');

			DecimalFormat df = new DecimalFormat("#,##0", otherSymbols);
			holder.list_harga_jual.setText("Rp. " + df.format(priceIDR));
			holder.list_kode_product.setTypeface(typefaceSmall);
			holder.list_jumlah_order.setTypeface(typefaceSmall);
			holder.list_jumlah_order1.setTypeface(typefaceSmall);
			holder.list_jumlah_order2.setTypeface(typefaceSmall);
			holder.list_harga_jual.setTypeface(typefaceSmall);

			return row;

		}

		class UserHolder {
			TextView list_kode_product;
			TextView list_jumlah_order;
			TextView list_jumlah_order1;
			TextView list_jumlah_order2;
			TextView list_jumlah_order3;
			TextView list_harga_jual;
		}

	}

	class ListViewChooseAdapter extends ArrayAdapter<Product> {
		int layoutResourceId;
		Product productData;
		ArrayList<Product> data = new ArrayList<Product>();
		Activity mainActivity;
		EditText jumlahProduct;
		EditText jumlahProduct1;
		EditText jumlahProduct2;
		EditText jumlahProduct3;
		Dialog chooseProductDialog;

		public ListViewChooseAdapter(Activity mainActivity,
				int layoutResourceId, EditText jumlahProduct, EditText jumlahProduct1, EditText jumlahProduct2,EditText jumlahProduct3,
				ArrayList<Product> data, Dialog chooseProductDialog) {
			super(mainActivity, layoutResourceId, data);
			this.layoutResourceId = layoutResourceId;
			this.data = data;
			this.chooseProductDialog = chooseProductDialog;
			this.mainActivity = mainActivity;
			this.jumlahProduct = jumlahProduct;
			this.jumlahProduct1 = jumlahProduct1;
			this.jumlahProduct2 = jumlahProduct2;
			this.jumlahProduct3 = jumlahProduct3;
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
					if (jumlahProduct.getText().length()==0){
						String msg = getApplicationContext()
								.getResources()
								.getString(
										R.string.app_sales_order_failed_please_add_pcs);
						showCustomDialog(msg);
					}else if(jumlahProduct1.getText().length()==0){
						String msg = getApplicationContext()
								.getResources()
								.getString(
										R.string.app_sales_order_failed_please_add_renceng);
						showCustomDialog(msg);
					} else if(jumlahProduct2.getText().length()==0){
						String msg = getApplicationContext()
								.getResources()
								.getString(
										R.string.app_sales_order_failed_please_add_pck);
						showCustomDialog(msg);
					}else if(jumlahProduct3.getText().length()==0){
						String msg = getApplicationContext()
								.getResources()
								.getString(
										R.string.app_sales_order_failed_please_add_dus);
						showCustomDialog(msg);
					}else {
						if (jumlahProduct.getText().toString().length() > 0 || jumlahProduct1.getText().toString().length() > 0 ||
								jumlahProduct2.getText().toString().length() > 0||jumlahProduct3.getText().toString().length() > 0) {
							boolean containSameProduct = false;
							for (DetailReqLoad detailReqLoad : detailReqLoadList) {
								if (detailReqLoad.getKode_product()
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
								int count = detailReqLoadList.size() + 1;
								updateListViewDetailOrder(new DetailReqLoad(
										count,
										data.get(position).getNama_product(),
										data.get(position).getKode_product(),
										data.get(position).getHarga_jual(),
										jumlahProduct.getText().toString(),
										jumlahProduct1.getText().toString(),
										jumlahProduct2.getText().toString(),
										jumlahProduct3.getText().toString()));
								chooseProductDialog.hide();
							}
						} else {
							String msg = getApplicationContext()
									.getResources()
									.getString(
											R.string.app_sales_order_failed_please_add_jumlah);
							showCustomDialog(msg);

						}
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
