package com.mahkota_company.android.inventory;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.mahkota_company.android.database.DetailSalesOrder;
import com.mahkota_company.android.database.Jadwal;
import com.mahkota_company.android.database.Product;
import com.mahkota_company.android.database.SalesOrder;
import com.mahkota_company.android.utils.CONFIG;
import com.mahkota_company.android.utils.SpinnerAdapter;

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
	public ArrayList<Customer> customerList;
	public ArrayList<String> customerStringList;
	private String kodeCustomer;
	private Spinner spinnerPromosi;
	public ArrayList<String> promosiStringList;
	private int idPromosi = -1;
	private ListViewChooseAdapter cAdapterChooseAdapter;
	public ArrayList<DetailSalesOrder> detailSalesOrderList = new ArrayList<DetailSalesOrder>();
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
				gotoSalesOrder();
			}
		});
		mButtonCancel = (Button) findViewById(R.id.activity_sales_order_btn_cancel);
		mButtonCancel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				gotoSalesOrder();
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
								gotoSalesOrder();
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

					int countData = databaseHandler.getCountSalesOrder();
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

					ArrayList<SalesOrder> tempSales_order_list = databaseHandler
							.getAllSalesOrder();
					int tempIndex = 0;
					for (SalesOrder salesOrder : tempSales_order_list) {
						tempIndex = salesOrder.getId_sales_order();
						}
					int index = 1;
					for (DetailSalesOrder detailSalesOrder : detailSalesOrderList) {
                        SalesOrder salesOrder = new SalesOrder();
					    salesOrder.setId_sales_order(tempIndex + index);
						salesOrder.setDate_order(checkDate);
						salesOrder.setHarga_jual(detailSalesOrder
								.getHarga_jual());
						if(detailSalesOrder.getJumlah_order().equals(null)){
							salesOrder.setJumlah_order("0");
						}else{
							salesOrder.setJumlah_order(detailSalesOrder.getJumlah_order());
						}

						if(detailSalesOrder.getJumlah_order1().equals(null)){
							salesOrder.setJumlah_order1("0");
						}else{
							salesOrder.setJumlah_order1(detailSalesOrder.getJumlah_order1());
						}

						if(detailSalesOrder.getJumlah_order2().equals(null)){
							salesOrder.setJumlah_order2("0");
						}else{
							salesOrder.setJumlah_order2(detailSalesOrder.getJumlah_order2());
						}
						salesOrder.setNama_product(detailSalesOrder
								.getNama_product());
						salesOrder.setNomer_order(nomerOrder);
						salesOrder.setNomer_order_detail(nomerOrder
								+ String.valueOf(countData));
						salesOrder.setTime_order(time);
						salesOrder.setUsername(username);
						databaseHandler.add_SalesOrder(salesOrder);
						index += 1;
						}
						String msg = getApplicationContext().getResources()
								.getString(R.string.app_sales_order_save_success);
						showCustomDialogSaveSuccess(msg);



				break;
			default:
				break;
			}
		}

	};

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
				.findViewById(R.id.activity_product_edittext_pack);
		final EditText jumlahProduct2 = (EditText) chooseProductDialog
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
						jumlahProduct1, jumlahProduct2,
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
									jumlahProduct,jumlahProduct1, jumlahProduct2, product_list,
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
									jumlahProduct, jumlahProduct1, jumlahProduct2, product_list,
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

	private void updateListViewDetailOrder(DetailSalesOrder detailSalesOrder) {
		detailSalesOrderList.add(detailSalesOrder);
		cAdapter = new ListViewAdapter(AddRequestActivity.this,
				R.layout.list_item_detail_sales_order, detailSalesOrderList);
		listview.setAdapter(cAdapter);
		cAdapter.notifyDataSetChanged();
	}

	public class ListViewAdapter extends ArrayAdapter<DetailSalesOrder> {
		Activity activity;
		int layoutResourceId;
		DetailSalesOrder productData;
		ArrayList<DetailSalesOrder> data = new ArrayList<DetailSalesOrder>();

		public ListViewAdapter(Activity act, int layoutResourceId,
				ArrayList<DetailSalesOrder> data) {
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

				row.setTag(holder);
			} else {
				holder = (UserHolder) row.getTag();
			}
			productData = data.get(position);
			holder.list_kode_product.setText(productData.getKode_product());

			if(productData.getJumlah_order().equals(null)){
				holder.list_jumlah_order.setText(0);
			}else{
				holder.list_jumlah_order.setText(productData.getJumlah_order());
			}

			if(productData.getJumlah_order1().equals(null)){
				holder.list_jumlah_order1.setText(0);
			}else{
				holder.list_jumlah_order1.setText(productData.getJumlah_order1());
			}

			if(productData.getJumlah_order1().equals(null)){
				holder.list_jumlah_order2.setText(0);
			}else{
				holder.list_jumlah_order2.setText(productData.getJumlah_order2());
			}



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
		Dialog chooseProductDialog;

		public ListViewChooseAdapter(Activity mainActivity,
				int layoutResourceId, EditText jumlahProduct, EditText jumlahProduct1, EditText jumlahProduct2,
				ArrayList<Product> data, Dialog chooseProductDialog) {
			super(mainActivity, layoutResourceId, data);
			this.layoutResourceId = layoutResourceId;
			this.data = data;
			this.chooseProductDialog = chooseProductDialog;
			this.mainActivity = mainActivity;
			this.jumlahProduct = jumlahProduct;
			this.jumlahProduct1 = jumlahProduct1;
			this.jumlahProduct2 = jumlahProduct2;
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
										R.string.app_sales_order_failed_please_add_pck);
						showCustomDialog(msg);
					} else if(jumlahProduct2.getText().length()==0){
						String msg = getApplicationContext()
								.getResources()
								.getString(
										R.string.app_sales_order_failed_please_add_dus);
						showCustomDialog(msg);
					}else {
						if (jumlahProduct.getText().toString().length() > 0 || jumlahProduct1.getText().toString().length() > 0 ||
								jumlahProduct2.getText().toString().length() > 0) {
							boolean containSameProduct = false;
							for (DetailSalesOrder detailSalesOrder : detailSalesOrderList) {
								if (detailSalesOrder.getKode_product()
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
								int count = detailSalesOrderList.size() + 1;
								updateListViewDetailOrder(new DetailSalesOrder(
										count,
										data.get(position).getNama_product(),
										data.get(position).getKode_product(),
										data.get(position).getHarga_jual(),
										jumlahProduct.getText().toString(),
										jumlahProduct1.getText().toString(),
										jumlahProduct2.getText().toString()));
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

	public void gotoSalesOrder() {
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
