package com.mahkota_company.android.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler_old extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "mahkota";

	// Contacts table name
	private static final String TABLE_STAFF = "staff";
	private static final String TABLE_STAFF_TEMP = "staff_temp";
	private static final String TABLE_BRANCH = "branch";
	private static final String TABLE_TYPE_CUSTOMER = "type_customer";
	private static final String TABLE_CLUSTER = "cluster";
	private static final String TABLE_WILAYAH = "wilayah";
	private static final String TABLE_KEMASAN = "kemasan";
	private static final String TABLE_PRODUCT = "product";
	private static final String TABLE_CUSTOMER = "customer";
	private static final String TABLE_JADWAL = "jadwal";
	private static final String TABLE_PROMOSI = "promosi";
	private static final String TABLE_PHOTO_PURCHASE = "photo_purchase";
	private static final String TABLE_DISPLAY_PRODUCT = "display_product";
	private static final String TABLE_STOCK_ON_HAND = "stock_on_hand";
	private static final String TABLE_SALES_ORDER = "sales_order";
	private static final String TABLE_TRACKING = "tracking";

	// STAFF Table Columns names
	private static final String KEY_STAFF_ID_STAFF = "id_staff";
	private static final String KEY_STAFF_NAMA_LENGKAP = "nama_lengkap";
	private static final String KEY_STAFF_USERNAME = "username";
	private static final String KEY_STAFF_NO_TELP = "notelp";
	private static final String KEY_STAFF_PASSWORD = "password";
	private static final String KEY_STAFF_LEVEL = "level";
	private static final String KEY_STAFF_ID_BRANCH = "id_branch";
	private static final String KEY_STAFF_TYPE_CUSTOMER = "id_type_customer";
	private static final String KEY_STAFF_ID_DEPO = "id_depo";


	// STAFF TEMP Table Columns names
	private static final String KEY_STAFF_TEMP_ID_STAFF = "id_staff";
	private static final String KEY_STAFF_TEMP_NAMA_LENGKAP = "nama_lengkap";
	private static final String KEY_STAFF_TEMP_USERNAME = "username";
	private static final String KEY_STAFF_TEMP_NO_TELP = "notelp";
	private static final String KEY_STAFF_TEMP_PASSWORD = "password";
	private static final String KEY_STAFF_TEMP_LEVEL = "level";
	private static final String KEY_STAFF_TEMP_ID_BRANCH = "id_branch";
	private static final String KEY_STAFF_TEMP_TYPE_CUSTOMER = "id_type_customer";
	private static final String KEY_STAFF_TEMP_ID_DEPO = "id_depo";

	// CUSTOMER Table Columns names
	private static final String KEY_CUSTOMER_ID_CUSTOMER = "id_customer";
	private static final String KEY_CUSTOMER_KODE_CUSTOMER = "kode_customer";
	private static final String KEY_CUSTOMER_EMAIL_CUSTOMER = "email";
	private static final String KEY_CUSTOMER_ALAMAT_CUSTOMER = "alamat";
	private static final String KEY_CUSTOMER_LATS_CUSTOMER = "lats";
	private static final String KEY_CUSTOMER_LONGS_CUSTOMER = "longs";
	private static final String KEY_CUSTOMER_NAMA_LENGKAP_CUSTOMER = "nama_lengkap";
	private static final String KEY_CUSTOMER_NO_TELP_CUSTOMER = "no_telp";
	private static final String KEY_CUSTOMER_ID_WILAYAH_CUSTOMER = "id_wilayah";
	private static final String KEY_CUSTOMER_FOTO_1_CUSTOMER = "foto_1";
	private static final String KEY_CUSTOMER_FOTO_2_CUSTOMER = "foto_2";
	private static final String KEY_CUSTOMER_FOTO_3_CUSTOMER = "foto_3";
	private static final String KEY_CUSTOMER_ID_TYPE_CUSTOMER = "id_type_customer";
	private static final String KEY_CUSTOMER_BLOKIR = "blokir";
	private static final String KEY_CUSTOMER_DATE = "date";
	private static final String KEY_CUSTOMER_STATUS_UPDATE = "status_update";
	private static final String KEY_CUSTOMER_ID_STAFF = "id_staff";
	private static final String KEY_CUSTOMER_NO_KTP = "no_ktp";
	private static final String KEY_CUSTOMER_TANGGAL_LAHIR = "tanggal_lahir";
	private static final String KEY_CUSTOMER_NAMA_BANK = "nama_bank";
	private static final String KEY_CUSTOMER_NO_REKENING = "no_rekening";
	private static final String KEY_CUSTOMER_ATAS_NAMA = "atas_nama";
	private static final String KEY_CUSTOMER_NPWP = "npwp";
	private static final String KEY_CUSTOMER_NAMA_PASAR = "nama_pasar";
	private static final String KEY_CUSTOMER_ID_CLUSTER = "id_cluster";
	private static final String KEY_CUSTOMER_TELP = "telp";
	private static final String KEY_CUSTOMER_FAX = "fax";
	private static final String KEY_CUSTOMER_OMSET= "omset";
	private static final String KEY_CUSTOMER_CARA_PEMBAYARAN = "cara_pembayaran";
	private static final String KEY_CUSTOMER_PLAFON_KREDIT = "plafon_kredit";
	private static final String KEY_CUSTOMER_TERM_KREDIT = "term_kredit";
	private static final String KEY_CUSTOMER_NAMA_ISTRI = "nama_istri";
	private static final String KEY_CUSTOMER_NAMA_ANAK1 = "nama_anak1";
	private static final String KEY_CUSTOMER_NAMA_ANAK2 = "nama_anak2";
	private static final String KEY_CUSTOMER_NAMA_ANAK3 = "nama_anak3";
	private static final String KEY_CUSTOMER_KODE_POS = "kode_pos";
	private static final String KEY_CUSTOMER_ID_DEPO = "id_depo";
	private static final String KEY_CUSTOMER_ISACTIVE = "isactive";
	private static final String KEY_CUSTOMER_DESCRIPTION = "description";
	private static final String KEY_CUSTOMER_NAMA_TOKO = "nama_toko";


	// PRODUCT Table Columns names
	private static final String KEY_PRODUCT_ID_PRODUCT = "id_product";
	private static final String KEY_PRODUCT_NAMA_PRODUCT = "nama_product";
	private static final String KEY_PRODUCT_KODE_PRODUCT = "kode_product";
	private static final String KEY_PRODUCT_HARGA_JUAL = "harga_jual";
	private static final String KEY_PRODUCT_STOCK = "stock";
	private static final String KEY_PRODUCT_ID_KEMASAN = "id_kemasan";
	private static final String KEY_PRODUCT_FOTO = "foto";
	private static final String KEY_PRODUCT_DESKRIPSI = "deskripsi";

	// JADWAL Table Columns names
	private static final String KEY_JADWAL_ID_JADWAL = "id_jadwal";
	private static final String KEY_JADWAL_KODE_JADWAL = "kode_jadwal";
	private static final String KEY_JADWAL_KODE_CUSTOMER = "kode_customer";
	private static final String KEY_JADWAL_USERNAME = "username";
	private static final String KEY_JADWAL_ALAMAT = "alamat";
	private static final String KEY_JADWAL_NAMA_LENGKAP = "nama_lengkap";
	private static final String KEY_JADWAL_ID_WILAYAH = "id_wilayah";
	private static final String KEY_JADWAL_STATUS = "status";
	private static final String KEY_JADWAL_DATE = "date";
	private static final String KEY_JADWAL_CHECK_IN = "checkin";
	private static final String KEY_JADWAL_CHECK_OUT = "checkout";
	private static final String KEY_JADWAL_STATUS_UPDATE = "status_update";

//	// PROMOSI Table Columns names
//	private static final String KEY_PROMOSI_ID_PROMOSI = "id_promosi";
//	private static final String KEY_PROMOSI_TITLE_PROMOSI = "title_promosi";
//	private static final String KEY_PROMOSI_JENIS_PROMOSI = "jenis_promosi";
//	private static final String KEY_PROMOSI_JUMLAH_DISKON = "jumlah_diskon";
//	private static final String KEY_PROMOSI_DESKRIPSI = "deskripsi";
//	private static final String KEY_PROMOSI_FOTO = "foto";
//	private static final String KEY_PROMOSI_DATE_TIME = "datetime";

	// DISPLAY_PRODUCT Table Columns names
	private static final String KEY_DISPLAY_PRODUCT_ID_DISPLAY_PRODUCT = "id_display_product";
	private static final String KEY_DISPLAY_PRODUCT_KODE_CUSTOMER = "kode_customer";
	private static final String KEY_DISPLAY_PRODUCT_NAMA_LENGKAP = "nama_lengkap";
	private static final String KEY_DISPLAY_PRODUCT_FOTO = "foto";
	private static final String KEY_DISPLAY_PRODUCT_NAMA_DISPLAY_PRODUCT = "nama_display_product";
	private static final String KEY_DISPLAY_PRODUCT_DESKRIPSI = "deskripsi";
	private static final String KEY_DISPLAY_PRODUCT_DATE_TIME = "datetime";

//	// PHOTO_PURCHASE Table Columns names
//	private static final String KEY_PHOTO_PURCHASE_ID_PHOTO_PURCHASE = "id_photo_purchase";
//	private static final String KEY_PHOTO_PURCHASE_KODE_CUSTOMER = "kode_customer";
//	private static final String KEY_PHOTO_PURCHASE_NAMA_LENGKAP = "nama_lengkap";
//	private static final String KEY_PHOTO_PURCHASE_FOTO = "foto";
//	private static final String KEY_PHOTO_PURCHASE_NAMA_PHOTO_PURCHASE = "nama_photo_purchase";
//	private static final String KEY_PHOTO_PURCHASE_DESKRIPSI = "deskripsi";
//	private static final String KEY_PHOTO_PURCHASE_DATE_TIME = "datetime";

	// TYPE_CUSTOMER Table Columns names
	private static final String KEY_TYPE_CUSTOMER_ID_TYPE_CUSTOMER = "id_type_customer";
	private static final String KEY_TYPE_CUSTOMER_TYPE_CUSTOMER = "type_customer";
	private static final String KEY_TYPE_CUSTOMER_DESKRIPSI = "deskripsi";

	//CLUSTER table columns names
	private static final String KEY_CLUSTER_ID_CLUSTER = "id_cluster";
	private static final String KEY_CLUSTER_NAMA_CLUSTER = "nama_cluster";
	private static final String KEY_CLUSTER_DESCRIPTION = "description";

	// BRANCH Table Columns names
	private static final String KEY_BRANCH_ID_BRANCH = "id_branch";
	private static final String KEY_BRANCH_KODE_BRANCH = "kode_branch";
	private static final String KEY_BRANCH_DESKRIPSI_BRANCH = "deskripsi";

	// WILAYAH Table Columns names
	private static final String KEY_WILAYAH_ID_WILAYAH = "id_wilayah";
	private static final String KEY_WILAYAH_NAMA_WILAYAH = "nama_wilayah";
	private static final String KEY_WILAYAH_DESKRIPSI_WILAYAH = "deskripsi";
	private static final String KEY_WILAYAH_LATS_WILAYAH = "lats";
	private static final String KEY_WILAYAH_LONGS_WILAYAH = "longs";

	// KEMASAN Table Columns names
	private static final String KEY_KEMASAN_ID_KEMASAN = "id_kemasan";
	private static final String KEY_KEMASAN_NAMA_KEMASAN = "nama_id_kemasan";

	// STOCK_ON_HAND Table Columns names
	private static final String KEY_STOCK_ON_HAND_ID_STOCK_ON_HAND = "id_stock_on_hand";
	private static final String KEY_STOCK_ON_HAND_NOMER_STOCK_ON_HAND = "nomer_stock_on_hand";
	private static final String KEY_STOCK_ON_HAND_NOMER_STOCK_ON_HAND_DETAIL = "nomer_stock_on_hand_detail";
	private static final String KEY_STOCK_ON_HAND_DATE_STOCK_ON_HAND = "date_stock_on_hand";
	private static final String KEY_STOCK_ON_HAND_TIME_STOCK_ON_HAND = "time_stock_on_hand";
	private static final String KEY_STOCK_ON_HAND_DESKRIPSI = "deskripsi";
	private static final String KEY_STOCK_ON_HAND_USERNAME = "username";
	private static final String KEY_STOCK_ON_HAND_KODE_CUSTOMER = "kode_customer";
	private static final String KEY_STOCK_ON_HAND_ALAMAT = "alamat";
	private static final String KEY_STOCK_ON_HAND_NAMA_LENGKAP = "nama_lengkap";
	private static final String KEY_STOCK_ON_HAND_NAMA_PRODUCT = "nama_product";
	private static final String KEY_STOCK_ON_HAND_KODE_PRODUCT = "kode_product";
	private static final String KEY_STOCK_ON_HAND_HARGA_JUAL = "harga_jual";
	private static final String KEY_STOCK_ON_HAND_STOCK = "stock";

	// SALES_ORDER Table Columns names
	private static final String KEY_SALES_ORDER_ID_SALES_ORDER = "id_sales_order";
	private static final String KEY_SALES_ORDER_NOMER_ORDER = "nomer_order";
	private static final String KEY_SALES_ORDER_NOMER_ORDER_DETAIL = "nomer_order_detail";
	private static final String KEY_SALES_ORDER_DATE_STOCK_ON_HAND = "date_order";
	private static final String KEY_SALES_ORDER_TIME_STOCK_ON_HAND = "time_order";
	private static final String KEY_SALES_ORDER_DESKRIPSI = "deskripsi";
	private static final String KEY_SALES_ORDER_ID_PROMOSI = "id_promosi";
	private static final String KEY_SALES_ORDER_USERNAME = "username";
	private static final String KEY_SALES_ORDER_KODE_CUSTOMER = "kode_customer";
	private static final String KEY_SALES_ORDER_ALAMAT = "alamat";
	private static final String KEY_SALES_ORDER_NAMA_LENGKAP = "nama_lengkap";
	private static final String KEY_SALES_ORDER_NAMA_PRODUCT = "nama_product";
	private static final String KEY_SALES_ORDER_KODE_PRODUCT = "kode_product";
	private static final String KEY_SALES_ORDER_HARGA_JUAL = "harga_jual";
	private static final String KEY_SALES_ORDER_JUMLAH_ORDER = "jumlah_order";

	// TRACKING Table Columns names
	private static final String KEY_TRACKING_ID_LOCATOR = "id_locator";
	private static final String KEY_TRACKING_USERNAME = "username";
	private static final String KEY_TRACKING_NAMA_LENGKAP = "nama_lengkap";
	private static final String KEY_TRACKING_LEVEL = "level";
	private static final String KEY_TRACKING_LATS = "lats";
	private static final String KEY_TRACKING_LONGS = "longs";
	private static final String KEY_TRACKING_ADDRESS = "address";
	private static final String KEY_TRACKING_IMEI = "imei";
	private static final String KEY_TRACKING_MCC = "mcc";
	private static final String KEY_TRACKING_MNC = "mnc";
	private static final String KEY_TRACKING_DATE = "date";
	private static final String KEY_TRACKING_TIME = "time";

	private final ArrayList<Staff> staff_list = new ArrayList<Staff>();
	private final ArrayList<TypeCustomer> type_customer_list = new ArrayList<TypeCustomer>();
	private final ArrayList<Cluster> cluster_list = new ArrayList<Cluster>();

	private final ArrayList<Branch> branch_list = new ArrayList<Branch>();
	private final ArrayList<Kemasan> kemasan_list = new ArrayList<Kemasan>();
	private final ArrayList<Wilayah> wilayah_list = new ArrayList<Wilayah>();
	private final ArrayList<Product> product_list = new ArrayList<Product>();
	private final ArrayList<Customer> customer_list = new ArrayList<Customer>();
	private final ArrayList<Jadwal> jadwal_list = new ArrayList<Jadwal>();
	//	private final ArrayList<Promosi> promosi_list = new ArrayList<Promosi>();
	private final ArrayList<DisplayProduct> display_product_list = new ArrayList<DisplayProduct>();
	//	private final ArrayList<PhotoPurchase> photo_purchase_list = new ArrayList<PhotoPurchase>();
	private final ArrayList<StockOnHand> stock_on_hand_list = new ArrayList<StockOnHand>();
	private final ArrayList<SalesOrder> sales_order_list = new ArrayList<SalesOrder>();
	private final ArrayList<Tracking> tracking_list = new ArrayList<Tracking>();
	private final ArrayList<StaffTemp> staff_temp_list = new ArrayList<StaffTemp>();

	public DatabaseHandler_old(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TABLE_STAFF = "CREATE TABLE " + TABLE_STAFF + "("
				+ KEY_STAFF_ID_STAFF + " INTEGER PRIMARY KEY,"
				+ KEY_STAFF_NAMA_LENGKAP + " TEXT," + KEY_STAFF_USERNAME
				+ " TEXT," + KEY_STAFF_PASSWORD + " TEXT," + KEY_STAFF_NO_TELP
				+ " TEXT," + KEY_STAFF_LEVEL + " INTEGER,"
				+ KEY_STAFF_ID_BRANCH + " TEXT," + KEY_STAFF_TYPE_CUSTOMER
				+ " TEXT," + KEY_STAFF_ID_DEPO + " TEXT" + ")";
		db.execSQL(CREATE_TABLE_STAFF);

		String CREATE_TABLE_STAFF_TEMP = "CREATE TABLE " + TABLE_STAFF_TEMP
				+ "(" + KEY_STAFF_TEMP_ID_STAFF + " INTEGER PRIMARY KEY,"
				+ KEY_STAFF_TEMP_NAMA_LENGKAP + " TEXT,"
				+ KEY_STAFF_TEMP_USERNAME + " TEXT," + KEY_STAFF_TEMP_PASSWORD
				+ " TEXT," + KEY_STAFF_TEMP_NO_TELP + " TEXT,"
				+ KEY_STAFF_TEMP_LEVEL + " INTEGER," + KEY_STAFF_TEMP_ID_BRANCH
				+ " TEXT," + KEY_STAFF_TEMP_TYPE_CUSTOMER + " TEXT,"
				+ KEY_STAFF_TEMP_ID_DEPO + " TEXT" + ")";
		db.execSQL(CREATE_TABLE_STAFF_TEMP);

		String CREATE_TABLE_TYPE_CUSTOMER = "CREATE TABLE "
				+ TABLE_TYPE_CUSTOMER + "("
				+ KEY_TYPE_CUSTOMER_ID_TYPE_CUSTOMER + " INTEGER PRIMARY KEY,"
				+ KEY_TYPE_CUSTOMER_TYPE_CUSTOMER + " TEXT,"
				+ KEY_TYPE_CUSTOMER_DESKRIPSI + " TEXT" + ")";
		db.execSQL(CREATE_TABLE_TYPE_CUSTOMER);

		String CREATE_TABLE_CLUSTER = "CREATE TABLE "
				+ TABLE_CLUSTER + "("
				+ KEY_CLUSTER_ID_CLUSTER + " INTEGER PRIMARY KEY,"
				+ KEY_CLUSTER_NAMA_CLUSTER + " TEXT,"
				+ KEY_CLUSTER_DESCRIPTION + " TEXT" + ")";
		db.execSQL(CREATE_TABLE_CLUSTER);

		String CREATE_TABLE_BRANCH = "CREATE TABLE " + TABLE_BRANCH + "("
				+ KEY_BRANCH_ID_BRANCH + " INTEGER PRIMARY KEY,"
				+ KEY_BRANCH_KODE_BRANCH + " TEXT,"
				+ KEY_BRANCH_DESKRIPSI_BRANCH + " TEXT" + ")";
		db.execSQL(CREATE_TABLE_BRANCH);

		String CREATE_TABLE_KEMASAN = "CREATE TABLE " + TABLE_KEMASAN + "("
				+ KEY_KEMASAN_ID_KEMASAN + " INTEGER PRIMARY KEY,"
				+ KEY_KEMASAN_NAMA_KEMASAN + " TEXT" + ")";
		db.execSQL(CREATE_TABLE_KEMASAN);

		String CREATE_TABLE_WILAYAH = "CREATE TABLE " + TABLE_WILAYAH + "("
				+ KEY_WILAYAH_ID_WILAYAH + " INTEGER PRIMARY KEY,"
				+ KEY_WILAYAH_NAMA_WILAYAH + " TEXT,"
				+ KEY_WILAYAH_DESKRIPSI_WILAYAH + " TEXT,"
				+ KEY_WILAYAH_LATS_WILAYAH + " TEXT,"
				+ KEY_WILAYAH_LONGS_WILAYAH + " TEXT" + ")";
		db.execSQL(CREATE_TABLE_WILAYAH);

		String CREATE_TABLE_PRODUCT = "CREATE TABLE " + TABLE_PRODUCT + "("
				+ KEY_PRODUCT_ID_PRODUCT + " INTEGER PRIMARY KEY,"
				+ KEY_PRODUCT_NAMA_PRODUCT + " TEXT,"
				+ KEY_PRODUCT_KODE_PRODUCT + " TEXT," + KEY_PRODUCT_HARGA_JUAL
				+ " TEXT," + KEY_PRODUCT_STOCK + " TEXT,"
				+ KEY_PRODUCT_ID_KEMASAN + " TEXT," + KEY_PRODUCT_FOTO
				+ " TEXT," + KEY_PRODUCT_DESKRIPSI + " TEXT" + ")";
		db.execSQL(CREATE_TABLE_PRODUCT);

		String CREATE_TABLE_CUSTOMER = "CREATE TABLE " + TABLE_CUSTOMER + "("
				+ KEY_CUSTOMER_ID_CUSTOMER + " INTEGER PRIMARY KEY,"
				+ KEY_CUSTOMER_KODE_CUSTOMER + " TEXT,"
				+ KEY_CUSTOMER_EMAIL_CUSTOMER + " TEXT,"
				+ KEY_CUSTOMER_ALAMAT_CUSTOMER + " TEXT,"
				+ KEY_CUSTOMER_LATS_CUSTOMER + " TEXT,"
				+ KEY_CUSTOMER_LONGS_CUSTOMER + " TEXT,"
				+ KEY_CUSTOMER_NAMA_LENGKAP_CUSTOMER + " TEXT,"
				+ KEY_CUSTOMER_NO_TELP_CUSTOMER + " TEXT,"
				+ KEY_CUSTOMER_ID_WILAYAH_CUSTOMER + " INTEGER,"
				+ KEY_CUSTOMER_FOTO_1_CUSTOMER + " TEXT,"
				+ KEY_CUSTOMER_FOTO_2_CUSTOMER + " TEXT,"
				+ KEY_CUSTOMER_FOTO_3_CUSTOMER + " TEXT,"
				+ KEY_CUSTOMER_ID_TYPE_CUSTOMER + " INTEGER, "
				+ KEY_CUSTOMER_BLOKIR + " TEXT, " + KEY_CUSTOMER_DATE
				+ " TEXT, " + KEY_CUSTOMER_STATUS_UPDATE + " TEXT, "
				+ KEY_CUSTOMER_ID_STAFF + " INTEGER,"
				+ KEY_CUSTOMER_NO_KTP + " TEXT,"
				+ KEY_CUSTOMER_TANGGAL_LAHIR + " TEXT,"
				+ KEY_CUSTOMER_NAMA_BANK + " TEXT,"
				+ KEY_CUSTOMER_NO_REKENING + " TEXT,"
				+ KEY_CUSTOMER_ATAS_NAMA + " TEXT,"
				+ KEY_CUSTOMER_NPWP + " TEXT,"
				+ KEY_CUSTOMER_NAMA_PASAR + " TEXT,"
				+ KEY_CUSTOMER_ID_CLUSTER + " INTEGER,"
				+ KEY_CUSTOMER_TELP + " TEXT,"
				+ KEY_CUSTOMER_FAX + " TEXT,"
				+ KEY_CUSTOMER_OMSET + " TEXT,"
				+ KEY_CUSTOMER_CARA_PEMBAYARAN + " TEXT,"
				+ KEY_CUSTOMER_PLAFON_KREDIT + " TEXT,"
				+ KEY_CUSTOMER_TERM_KREDIT + " TEXT,"
				+ KEY_CUSTOMER_NAMA_ISTRI + " TEXT,"
				+ KEY_CUSTOMER_NAMA_ANAK1 + " TEXT,"
				+ KEY_CUSTOMER_NAMA_ANAK2 + " TEXT,"
				+ KEY_CUSTOMER_NAMA_ANAK3 + " TEXT,"
				+ KEY_CUSTOMER_KODE_POS + " TEXT,"
				+ KEY_CUSTOMER_ID_DEPO + " TEXT,"
				+ KEY_CUSTOMER_ISACTIVE + " TEXT,"
				+ KEY_CUSTOMER_DESCRIPTION + " TEXT,"
				+ KEY_CUSTOMER_NAMA_TOKO + " TEXT"

				+ ")";
		db.execSQL(CREATE_TABLE_CUSTOMER);

		String CREATE_TABLE_JADWAL = "CREATE TABLE " + TABLE_JADWAL + "("
				+ KEY_JADWAL_ID_JADWAL + " INTEGER PRIMARY KEY,"
				+ KEY_JADWAL_KODE_JADWAL + " TEXT," + KEY_JADWAL_KODE_CUSTOMER
				+ " TEXT," + KEY_JADWAL_USERNAME + " TEXT," + KEY_JADWAL_ALAMAT
				+ " TEXT," + KEY_JADWAL_NAMA_LENGKAP + " TEXT,"
				+ KEY_JADWAL_ID_WILAYAH + " INTEGER," + KEY_JADWAL_STATUS
				+ " TEXT," + KEY_JADWAL_DATE + " TEXT," + KEY_JADWAL_CHECK_IN
				+ " TEXT," + KEY_JADWAL_CHECK_OUT + " TEXT,"
				+ KEY_JADWAL_STATUS_UPDATE + " TEXT" + ")";
		db.execSQL(CREATE_TABLE_JADWAL);

//		String CREATE_TABLE_PROMOSI = "CREATE TABLE " + TABLE_PROMOSI + "("
//				+ KEY_PROMOSI_ID_PROMOSI + " INTEGER PRIMARY KEY,"
//				+ KEY_PROMOSI_TITLE_PROMOSI + " TEXT,"
//				+ KEY_PROMOSI_JENIS_PROMOSI + " TEXT,"
//				+ KEY_PROMOSI_JUMLAH_DISKON + " INTEGER,"
//				+ KEY_PROMOSI_DESKRIPSI + " TEXT," + KEY_PROMOSI_FOTO
//				+ " TEXT," + KEY_PROMOSI_DATE_TIME + " TEXT" + ")";
//		db.execSQL(CREATE_TABLE_PROMOSI);

		String CREATE_TABLE_DISPLAY_PRODUCT = "CREATE TABLE "
				+ TABLE_DISPLAY_PRODUCT + "("
				+ KEY_DISPLAY_PRODUCT_ID_DISPLAY_PRODUCT
				+ " INTEGER PRIMARY KEY," + KEY_DISPLAY_PRODUCT_KODE_CUSTOMER
				+ " TEXT," + KEY_DISPLAY_PRODUCT_NAMA_LENGKAP + " TEXT,"
				+ KEY_DISPLAY_PRODUCT_FOTO + " TEXT,"
				+ KEY_DISPLAY_PRODUCT_NAMA_DISPLAY_PRODUCT + " TEXT,"
				+ KEY_DISPLAY_PRODUCT_DESKRIPSI + " TEXT,"
				+ KEY_DISPLAY_PRODUCT_DATE_TIME + " TEXT" + ")";
		db.execSQL(CREATE_TABLE_DISPLAY_PRODUCT);

//		String CREATE_TABLE_PHOTO_PURCHASE = "CREATE TABLE "
//				+ TABLE_PHOTO_PURCHASE + "("
//				+ KEY_PHOTO_PURCHASE_ID_PHOTO_PURCHASE
//				+ " INTEGER PRIMARY KEY," + KEY_PHOTO_PURCHASE_KODE_CUSTOMER
//				+ " TEXT," + KEY_PHOTO_PURCHASE_NAMA_LENGKAP + " TEXT,"
//				+ KEY_PHOTO_PURCHASE_FOTO + " TEXT,"
//				+ KEY_PHOTO_PURCHASE_NAMA_PHOTO_PURCHASE + " TEXT,"
//				+ KEY_PHOTO_PURCHASE_DESKRIPSI + " TEXT,"
//				+ KEY_PHOTO_PURCHASE_DATE_TIME + " TEXT" + ")";
//		db.execSQL(CREATE_TABLE_PHOTO_PURCHASE);

		String CREATE_TABLE_STOCK_ON_HAND = "CREATE TABLE "
				+ TABLE_STOCK_ON_HAND + "("
				+ KEY_STOCK_ON_HAND_ID_STOCK_ON_HAND + " INTEGER PRIMARY KEY,"
				+ KEY_STOCK_ON_HAND_NOMER_STOCK_ON_HAND + " TEXT,"
				+ KEY_STOCK_ON_HAND_NOMER_STOCK_ON_HAND_DETAIL + " TEXT,"
				+ KEY_STOCK_ON_HAND_DATE_STOCK_ON_HAND + " TEXT,"
				+ KEY_STOCK_ON_HAND_TIME_STOCK_ON_HAND + " TEXT,"
				+ KEY_STOCK_ON_HAND_DESKRIPSI + " TEXT,"
				+ KEY_STOCK_ON_HAND_USERNAME + " TEXT,"
				+ KEY_STOCK_ON_HAND_KODE_CUSTOMER + " TEXT,"
				+ KEY_STOCK_ON_HAND_ALAMAT + " TEXT,"
				+ KEY_STOCK_ON_HAND_NAMA_LENGKAP + " TEXT,"
				+ KEY_STOCK_ON_HAND_NAMA_PRODUCT + " TEXT,"
				+ KEY_STOCK_ON_HAND_KODE_PRODUCT + " TEXT,"
				+ KEY_STOCK_ON_HAND_HARGA_JUAL + " TEXT,"
				+ KEY_STOCK_ON_HAND_STOCK + " TEXT" + ")";
		db.execSQL(CREATE_TABLE_STOCK_ON_HAND);

		String CREATE_TABLE_SALES_ORDER = "CREATE TABLE " + TABLE_SALES_ORDER
				+ "(" + KEY_SALES_ORDER_ID_SALES_ORDER
				+ " INTEGER PRIMARY KEY," + KEY_SALES_ORDER_NOMER_ORDER
				+ " TEXT," + KEY_SALES_ORDER_NOMER_ORDER_DETAIL + " TEXT,"
				+ KEY_SALES_ORDER_DATE_STOCK_ON_HAND + " TEXT,"
				+ KEY_SALES_ORDER_TIME_STOCK_ON_HAND + " TEXT,"
				+ KEY_SALES_ORDER_DESKRIPSI + " TEXT,"
				+ KEY_SALES_ORDER_ID_PROMOSI + " INTEGER,"
				+ KEY_SALES_ORDER_USERNAME + " TEXT,"
				+ KEY_SALES_ORDER_KODE_CUSTOMER + " TEXT,"
				+ KEY_SALES_ORDER_ALAMAT + " TEXT,"
				+ KEY_SALES_ORDER_NAMA_LENGKAP + " TEXT,"
				+ KEY_SALES_ORDER_NAMA_PRODUCT + " TEXT,"
				+ KEY_SALES_ORDER_KODE_PRODUCT + " TEXT,"
				+ KEY_SALES_ORDER_HARGA_JUAL + " TEXT,"
				+ KEY_SALES_ORDER_JUMLAH_ORDER + " TEXT" + ")";
		db.execSQL(CREATE_TABLE_SALES_ORDER);

		String CREATE_TABLE_TRACKING = "CREATE TABLE " + TABLE_TRACKING + "("
				+ KEY_TRACKING_ID_LOCATOR + " INTEGER PRIMARY KEY,"
				+ KEY_TRACKING_USERNAME + " TEXT," + KEY_TRACKING_NAMA_LENGKAP
				+ " TEXT," + KEY_TRACKING_LEVEL + " INTEGER,"
				+ KEY_TRACKING_LATS + " TEXT," + KEY_TRACKING_LONGS + " TEXT,"
				+ KEY_TRACKING_ADDRESS + " TEXT," + KEY_TRACKING_IMEI
				+ " TEXT," + KEY_TRACKING_MCC + " TEXT," + KEY_TRACKING_MNC
				+ " TEXT," + KEY_TRACKING_DATE + " TEXT," + KEY_TRACKING_TIME
				+ " TEXT" + ")";
		db.execSQL(CREATE_TABLE_TRACKING);

	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_STAFF);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BRANCH);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TYPE_CUSTOMER);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLUSTER);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_WILAYAH);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_KEMASAN);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_JADWAL);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROMOSI);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHOTO_PURCHASE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DISPLAY_PRODUCT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_STOCK_ON_HAND);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SALES_ORDER);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRACKING);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_STAFF_TEMP);
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new staff
	public void add_Staff(Staff staff) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_STAFF_ID_STAFF, staff.getId_staff());
		values.put(KEY_STAFF_NAMA_LENGKAP, staff.getNama_lengkap());
		values.put(KEY_STAFF_USERNAME, staff.getUsername());
		values.put(KEY_STAFF_NO_TELP, staff.getNotelp());
		values.put(KEY_STAFF_PASSWORD, staff.getPassword());
		values.put(KEY_STAFF_LEVEL, staff.getLevel());
		values.put(KEY_STAFF_ID_BRANCH, staff.getId_branch());
		values.put(KEY_STAFF_TYPE_CUSTOMER, staff.getId_type_customer());
		values.put(KEY_STAFF_ID_DEPO, staff.getId_depo());
		// Inserting Row
		db.insert(TABLE_STAFF, null, values);
		db.close(); // Closing database connection
	}

	// Adding new staff temp
	public void add_Staff_Temp(StaffTemp staff_temp) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_STAFF_TEMP_ID_STAFF, staff_temp.getId_staff());
		values.put(KEY_STAFF_TEMP_NAMA_LENGKAP, staff_temp.getNama_lengkap());
		values.put(KEY_STAFF_TEMP_USERNAME, staff_temp.getUsername());
		values.put(KEY_STAFF_TEMP_NO_TELP, staff_temp.getNotelp());
		values.put(KEY_STAFF_TEMP_PASSWORD, staff_temp.getPassword());
		values.put(KEY_STAFF_TEMP_LEVEL, staff_temp.getLevel());
		values.put(KEY_STAFF_TEMP_ID_BRANCH, staff_temp.getId_branch());
		values.put(KEY_STAFF_TEMP_TYPE_CUSTOMER,
				staff_temp.getId_type_customer());
		values.put(KEY_STAFF_TEMP_ID_DEPO, staff_temp.getId_depo());
		// Inserting Row
		db.insert(TABLE_STAFF_TEMP, null, values);
		db.close(); // Closing database connection
	}

	// Adding new customer
	public void add_Customer(Customer customer) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_CUSTOMER_ID_CUSTOMER, customer.getId_customer());
		values.put(KEY_CUSTOMER_KODE_CUSTOMER, customer.getKode_customer());
		values.put(KEY_CUSTOMER_EMAIL_CUSTOMER, customer.getEmail());
		values.put(KEY_CUSTOMER_ALAMAT_CUSTOMER, customer.getAlamat());
		values.put(KEY_CUSTOMER_LATS_CUSTOMER, customer.getLats());
		values.put(KEY_CUSTOMER_LONGS_CUSTOMER, customer.getLongs());
		values.put(KEY_CUSTOMER_NAMA_LENGKAP_CUSTOMER,
				customer.getNama_lengkap());
		values.put(KEY_CUSTOMER_NO_TELP_CUSTOMER, customer.getNo_telp());
		values.put(KEY_CUSTOMER_ID_WILAYAH_CUSTOMER, customer.getId_wilayah());
		values.put(KEY_CUSTOMER_FOTO_1_CUSTOMER, customer.getFoto_1());
		values.put(KEY_CUSTOMER_FOTO_2_CUSTOMER, customer.getFoto_2());
		values.put(KEY_CUSTOMER_FOTO_3_CUSTOMER, customer.getFoto_3());
		values.put(KEY_CUSTOMER_ID_TYPE_CUSTOMER, customer.getId_type_customer());
		values.put(KEY_CUSTOMER_BLOKIR, customer.getBlokir());
		values.put(KEY_CUSTOMER_DATE, customer.getDate());
		values.put(KEY_CUSTOMER_STATUS_UPDATE, customer.getStatus_update());
		values.put(KEY_CUSTOMER_ID_STAFF, customer.getId_staff());
		values.put(KEY_CUSTOMER_NO_KTP, customer.getNo_ktp());
		values.put(KEY_CUSTOMER_TANGGAL_LAHIR, customer.getTanggal_lahir());
		values.put(KEY_CUSTOMER_NAMA_BANK, customer.getNama_bank());
		values.put(KEY_CUSTOMER_NO_REKENING, customer.getNo_rekening());
		values.put(KEY_CUSTOMER_ATAS_NAMA, customer.getAtas_nama());
		values.put(KEY_CUSTOMER_NPWP, customer.getNpwp());
		values.put(KEY_CUSTOMER_NAMA_PASAR, customer.getNama_pasar());
		values.put(KEY_CUSTOMER_ID_CLUSTER, customer.getId_cluster());
		values.put(KEY_CUSTOMER_TELP, customer.getTelp());
		values.put(KEY_CUSTOMER_FAX, customer.getFax());
		values.put(KEY_CUSTOMER_OMSET, customer.getOmset());
		values.put(KEY_CUSTOMER_CARA_PEMBAYARAN, customer.getCara_pembayaran());
		values.put(KEY_CUSTOMER_PLAFON_KREDIT, customer.getPlafon_kredit());
		values.put(KEY_CUSTOMER_TERM_KREDIT, customer.getTerm_kredit());

		values.put(KEY_CUSTOMER_NAMA_ISTRI, customer.getNama_istri());
		values.put(KEY_CUSTOMER_NAMA_ANAK1, customer.getNama_anak1());
		values.put(KEY_CUSTOMER_NAMA_ANAK2, customer.getNama_anak2());
		values.put(KEY_CUSTOMER_NAMA_ANAK3, customer.getNama_anak3());
		values.put(KEY_CUSTOMER_KODE_POS, customer.getKode_pos());
		values.put(KEY_CUSTOMER_ID_DEPO, customer.getId_depo());
		values.put(KEY_CUSTOMER_ISACTIVE, customer.getIsactive());
		values.put(KEY_CUSTOMER_DESCRIPTION, customer.getDescription());
		values.put(KEY_CUSTOMER_NAMA_TOKO, customer.getNama_toko());


		// Inserting Row
		db.insert(TABLE_CUSTOMER, null, values);
		db.close(); // Closing database connection
	}

	// Adding new jadwal
	public void add_Jadwal(Jadwal jadwal) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_JADWAL_ID_JADWAL, jadwal.getId_jadwal());
		values.put(KEY_JADWAL_KODE_JADWAL, jadwal.getKode_jadwal());
		values.put(KEY_JADWAL_KODE_CUSTOMER, jadwal.getKode_customer());
		values.put(KEY_JADWAL_USERNAME, jadwal.getUsername());
		values.put(KEY_JADWAL_ALAMAT, jadwal.getAlamat());
		values.put(KEY_JADWAL_NAMA_LENGKAP, jadwal.getNama_lengkap());
		values.put(KEY_JADWAL_ID_WILAYAH, jadwal.getId_wilayah());
		values.put(KEY_JADWAL_STATUS, jadwal.getStatus());
		values.put(KEY_JADWAL_DATE, jadwal.getDate());
		values.put(KEY_JADWAL_CHECK_IN, jadwal.getCheckin());
		values.put(KEY_JADWAL_CHECK_OUT, jadwal.getCheckout());
		values.put(KEY_JADWAL_STATUS_UPDATE, jadwal.getStatus_update());
		// Inserting Row
		db.insert(TABLE_JADWAL, null, values);
		db.close(); // Closing database connection
	}

	// Adding new product
	public void add_Product(Product product) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_PRODUCT_ID_PRODUCT, product.getId_product());
		values.put(KEY_PRODUCT_NAMA_PRODUCT, product.getNama_product());
		values.put(KEY_PRODUCT_KODE_PRODUCT, product.getKode_product());
		values.put(KEY_PRODUCT_HARGA_JUAL, product.getHarga_jual());
		values.put(KEY_PRODUCT_STOCK, product.getStock());
		values.put(KEY_PRODUCT_ID_KEMASAN, product.getId_kemasan());
		values.put(KEY_PRODUCT_FOTO, product.getFoto());
		values.put(KEY_PRODUCT_DESKRIPSI, product.getDeskripsi());
		// Inserting Row
		db.insert(TABLE_PRODUCT, null, values);
		db.close(); // Closing database connection
	}

//	// Adding new promosi
//	public void add_Promosi(Promosi promosi) {
//		SQLiteDatabase db = this.getWritableDatabase();
//		ContentValues values = new ContentValues();
//		values.put(KEY_PROMOSI_ID_PROMOSI, promosi.getId_promosi());
//		values.put(KEY_PROMOSI_TITLE_PROMOSI, promosi.getTitle_promosi());
//		values.put(KEY_PROMOSI_JENIS_PROMOSI, promosi.getJenis_promosi());
//		values.put(KEY_PROMOSI_JUMLAH_DISKON, promosi.getJumlah_diskon());
//		values.put(KEY_PROMOSI_DESKRIPSI, promosi.getDeskripsi());
//		values.put(KEY_PROMOSI_FOTO, promosi.getFoto());
//		values.put(KEY_PROMOSI_DATE_TIME, promosi.getDatetime());
//
//		// Inserting Row
//		db.insert(TABLE_PROMOSI, null, values);
//		db.close(); // Closing database connection
//	}

	// Adding new displayProduct
	public void add_Display_Product(DisplayProduct displayProduct) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_DISPLAY_PRODUCT_ID_DISPLAY_PRODUCT,
				displayProduct.getId_display_product());
		values.put(KEY_DISPLAY_PRODUCT_KODE_CUSTOMER,
				displayProduct.getKode_customer());
		values.put(KEY_DISPLAY_PRODUCT_NAMA_LENGKAP,
				displayProduct.getNama_lengkap());
		values.put(KEY_DISPLAY_PRODUCT_FOTO, displayProduct.getFoto());
		values.put(KEY_DISPLAY_PRODUCT_NAMA_DISPLAY_PRODUCT,
				displayProduct.getNama_display_product());
		values.put(KEY_DISPLAY_PRODUCT_DESKRIPSI, displayProduct.getDeskripsi());
		values.put(KEY_DISPLAY_PRODUCT_DATE_TIME, displayProduct.getDatetime());

		// Inserting Row
		db.insert(TABLE_DISPLAY_PRODUCT, null, values);
		db.close(); // Closing database connection
	}

//	// Adding new displayProduct
//	public void add_Photo_Purchase(PhotoPurchase photoPurchase) {
//		SQLiteDatabase db = this.getWritableDatabase();
//		ContentValues values = new ContentValues();
//		values.put(KEY_PHOTO_PURCHASE_ID_PHOTO_PURCHASE,
//				photoPurchase.getId_photo_purchase());
//		values.put(KEY_PHOTO_PURCHASE_KODE_CUSTOMER,
//				photoPurchase.getKode_customer());
//		values.put(KEY_PHOTO_PURCHASE_NAMA_LENGKAP,
//				photoPurchase.getNama_lengkap());
//		values.put(KEY_PHOTO_PURCHASE_FOTO, photoPurchase.getFoto());
//		values.put(KEY_PHOTO_PURCHASE_NAMA_PHOTO_PURCHASE,
//				photoPurchase.getNama_photo_purchase());
//		values.put(KEY_PHOTO_PURCHASE_DESKRIPSI, photoPurchase.getDeskripsi());
//		values.put(KEY_PHOTO_PURCHASE_DATE_TIME, photoPurchase.getDatetime());
//
//		// Inserting Row
//		db.insert(TABLE_PHOTO_PURCHASE, null, values);
//		db.close(); // Closing database connection
//
//	}

	// Adding new typeCustomer
	public void add_TypeCustomer(TypeCustomer typeCustomer) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_TYPE_CUSTOMER_ID_TYPE_CUSTOMER,
				typeCustomer.getId_type_customer());
		values.put(KEY_TYPE_CUSTOMER_TYPE_CUSTOMER,
				typeCustomer.getType_customer());
		values.put(KEY_TYPE_CUSTOMER_DESKRIPSI, typeCustomer.getDeskripsi());
		// Inserting Row
		db.insert(TABLE_TYPE_CUSTOMER, null, values);
		db.close(); // Closing database connection
	}

	// Adding new Cluster
	public void add_Cluster(Cluster cluster) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_CLUSTER_ID_CLUSTER,
				cluster.getId_cluster());
		values.put(KEY_CLUSTER_NAMA_CLUSTER,
				cluster.getNama_cluster());
		values.put(KEY_CLUSTER_DESCRIPTION, cluster.getDescription());
		// Inserting Row
		db.insert(TABLE_CLUSTER, null, values);
		db.close(); // Closing database connection
	}

	// Adding new branch
	public void add_Branch(Branch branch) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_BRANCH_ID_BRANCH, branch.getId_branch());
		values.put(KEY_BRANCH_KODE_BRANCH, branch.getKode_branch());
		values.put(KEY_BRANCH_DESKRIPSI_BRANCH, branch.getDeskripsi());
		// Inserting Row
		db.insert(TABLE_BRANCH, null, values);
		db.close(); // Closing database connection
	}

	// adding new kemasan
	public void add_Kemasan(Kemasan kemasan) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_KEMASAN_ID_KEMASAN, kemasan.getId_kemasan());
		values.put(KEY_KEMASAN_NAMA_KEMASAN, kemasan.getNama_id_kemasan());
		// Inserting Row
		db.insert(TABLE_KEMASAN, null, values);
		db.close(); // Closing database connection
	}

	// Adding new branch
	public void add_Wilayah(Wilayah wilayah) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_WILAYAH_ID_WILAYAH, wilayah.getId_wilayah());
		values.put(KEY_WILAYAH_NAMA_WILAYAH, wilayah.getNama_wilayah());
		values.put(KEY_WILAYAH_DESKRIPSI_WILAYAH, wilayah.getDeskripsi());
		values.put(KEY_WILAYAH_LATS_WILAYAH, wilayah.getLats());
		values.put(KEY_WILAYAH_LONGS_WILAYAH, wilayah.getLongs());

		// Inserting Row
		db.insert(TABLE_WILAYAH, null, values);
		db.close(); // Closing database connection
	}

	// Adding new StockOnHand
	public void add_StockOnHand(StockOnHand stockOnHand) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_STOCK_ON_HAND_ID_STOCK_ON_HAND,
				stockOnHand.getId_stock_on_hand());
		values.put(KEY_STOCK_ON_HAND_NOMER_STOCK_ON_HAND,
				stockOnHand.getNomer_stock_on_hand());
		values.put(KEY_STOCK_ON_HAND_NOMER_STOCK_ON_HAND_DETAIL,
				stockOnHand.getNomer_stock_on_hand_detail());
		values.put(KEY_STOCK_ON_HAND_DATE_STOCK_ON_HAND,
				stockOnHand.getDate_stock_on_hand());
		values.put(KEY_STOCK_ON_HAND_TIME_STOCK_ON_HAND,
				stockOnHand.getTime_stock_on_hand());
		values.put(KEY_STOCK_ON_HAND_DESKRIPSI, stockOnHand.getDeskripsi());
		values.put(KEY_STOCK_ON_HAND_USERNAME, stockOnHand.getUsername());
		values.put(KEY_STOCK_ON_HAND_KODE_CUSTOMER,
				stockOnHand.getKode_customer());
		values.put(KEY_STOCK_ON_HAND_ALAMAT, stockOnHand.getAlamat());
		values.put(KEY_STOCK_ON_HAND_NAMA_LENGKAP,
				stockOnHand.getNama_lengkap());
		values.put(KEY_STOCK_ON_HAND_NAMA_PRODUCT,
				stockOnHand.getNama_product());
		values.put(KEY_STOCK_ON_HAND_KODE_PRODUCT,
				stockOnHand.getKode_product());
		values.put(KEY_STOCK_ON_HAND_HARGA_JUAL, stockOnHand.getHarga_jual());
		values.put(KEY_STOCK_ON_HAND_STOCK, stockOnHand.getStock());

		// Inserting Row
		db.insert(TABLE_STOCK_ON_HAND, null, values);
		db.close(); // Closing database connection

	}

	// Adding new SalesOrder
	public void add_SalesOrder(SalesOrder salesOrder) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_SALES_ORDER_ID_SALES_ORDER,
				salesOrder.getId_sales_order());
		values.put(KEY_SALES_ORDER_NOMER_ORDER, salesOrder.getNomer_order());
		values.put(KEY_SALES_ORDER_NOMER_ORDER_DETAIL,
				salesOrder.getNomer_order_detail());
		values.put(KEY_SALES_ORDER_DATE_STOCK_ON_HAND,
				salesOrder.getDate_order());
		values.put(KEY_SALES_ORDER_TIME_STOCK_ON_HAND,
				salesOrder.getTime_order());
		values.put(KEY_SALES_ORDER_DESKRIPSI, salesOrder.getDeskripsi());
		values.put(KEY_SALES_ORDER_ID_PROMOSI, salesOrder.getId_promosi());
		values.put(KEY_SALES_ORDER_USERNAME, salesOrder.getUsername());
		values.put(KEY_SALES_ORDER_KODE_CUSTOMER, salesOrder.getKode_customer());
		values.put(KEY_SALES_ORDER_ALAMAT, salesOrder.getAlamat());
		values.put(KEY_SALES_ORDER_NAMA_LENGKAP, salesOrder.getNama_lengkap());
		values.put(KEY_SALES_ORDER_NAMA_PRODUCT, salesOrder.getNama_product());
		values.put(KEY_SALES_ORDER_KODE_PRODUCT, salesOrder.getKode_product());
		values.put(KEY_SALES_ORDER_HARGA_JUAL, salesOrder.getHarga_jual());
		values.put(KEY_SALES_ORDER_JUMLAH_ORDER, salesOrder.getJumlah_order());
		// Inserting Row
		db.insert(TABLE_SALES_ORDER, null, values);
		db.close(); // Closing database connection
	}

	// Adding new Tracking
	public void add_Tracking(Tracking tracking) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_TRACKING_ID_LOCATOR, tracking.getId_locator());
		values.put(KEY_TRACKING_USERNAME, tracking.getUsername());
		values.put(KEY_TRACKING_NAMA_LENGKAP, tracking.getNama_lengkap());
		values.put(KEY_TRACKING_LEVEL, tracking.getLevel());
		values.put(KEY_TRACKING_LATS, tracking.getLats());
		values.put(KEY_TRACKING_LONGS, tracking.getLongs());
		values.put(KEY_TRACKING_ADDRESS, tracking.getAddress());
		values.put(KEY_TRACKING_IMEI, tracking.getImei());
		values.put(KEY_TRACKING_MCC, tracking.getMcc());
		values.put(KEY_TRACKING_MNC, tracking.getMnc());
		values.put(KEY_TRACKING_DATE, tracking.getDate());
		values.put(KEY_TRACKING_TIME, tracking.getTime());
		// Inserting Row
		db.insert(TABLE_TRACKING, null, values);

		db.close(); // Closing database connection
	}

	// Getting single Staff
	public Staff getStaff(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_STAFF, new String[] {
						KEY_STAFF_ID_STAFF, KEY_STAFF_NAMA_LENGKAP, KEY_STAFF_USERNAME,
						KEY_STAFF_PASSWORD, KEY_STAFF_NO_TELP, KEY_STAFF_LEVEL,
						KEY_STAFF_ID_BRANCH, KEY_STAFF_TYPE_CUSTOMER,
						KEY_STAFF_ID_DEPO }, KEY_STAFF_ID_STAFF + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);

		if (cursor != null)
			cursor.moveToFirst();

		Staff staff = new Staff(cursor.getInt(0), cursor.getString(1),
				cursor.getString(2), cursor.getString(3), cursor.getString(4),
				cursor.getInt(5), cursor.getString(6), cursor.getString(7),
				cursor.getInt(8));
		// return staff
		cursor.close();
		db.close();

		return staff;
	}

	// Getting single Customer untuk nampilin dihalaman awal
	public Customer getCustomer(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CUSTOMER, new String[] {
						KEY_CUSTOMER_ID_CUSTOMER, KEY_CUSTOMER_KODE_CUSTOMER,
						KEY_CUSTOMER_EMAIL_CUSTOMER, KEY_CUSTOMER_ALAMAT_CUSTOMER,
						KEY_CUSTOMER_LATS_CUSTOMER, KEY_CUSTOMER_LONGS_CUSTOMER,
						KEY_CUSTOMER_NAMA_LENGKAP_CUSTOMER,
						KEY_CUSTOMER_NO_TELP_CUSTOMER,
						KEY_CUSTOMER_ID_WILAYAH_CUSTOMER, KEY_CUSTOMER_FOTO_1_CUSTOMER,
						KEY_CUSTOMER_FOTO_2_CUSTOMER, KEY_CUSTOMER_FOTO_3_CUSTOMER,
						KEY_CUSTOMER_ID_TYPE_CUSTOMER, KEY_CUSTOMER_BLOKIR,
						KEY_CUSTOMER_DATE, KEY_CUSTOMER_STATUS_UPDATE,
						KEY_CUSTOMER_ID_STAFF, KEY_CUSTOMER_NO_KTP, KEY_CUSTOMER_TANGGAL_LAHIR,
						KEY_CUSTOMER_NAMA_BANK, KEY_CUSTOMER_NO_REKENING, KEY_CUSTOMER_ATAS_NAMA,
						KEY_CUSTOMER_NPWP, KEY_CUSTOMER_NAMA_PASAR, KEY_CUSTOMER_ID_CLUSTER, KEY_CUSTOMER_TELP, KEY_CUSTOMER_FAX,
						KEY_CUSTOMER_OMSET, KEY_CUSTOMER_CARA_PEMBAYARAN, KEY_CUSTOMER_PLAFON_KREDIT,
						KEY_CUSTOMER_TERM_KREDIT,KEY_CUSTOMER_NAMA_ISTRI, KEY_CUSTOMER_NAMA_ANAK1, KEY_CUSTOMER_NAMA_ANAK2,
						KEY_CUSTOMER_NAMA_ANAK3,KEY_CUSTOMER_KODE_POS,KEY_CUSTOMER_ID_DEPO,KEY_CUSTOMER_ISACTIVE,KEY_CUSTOMER_DESCRIPTION,
						KEY_CUSTOMER_NAMA_TOKO},
				KEY_CUSTOMER_ID_CUSTOMER + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);


		if (cursor != null)
			cursor.moveToFirst();

		Customer customer = new Customer(cursor.getInt(0), cursor.getString(1),
				cursor.getString(2), cursor.getString(3), cursor.getString(4),
				cursor.getString(5), cursor.getString(6), cursor.getString(7),
				cursor.getInt(8), cursor.getString(9), cursor.getString(10),
				cursor.getString(11), cursor.getInt(12), cursor.getString(13),
				cursor.getString(14), cursor.getString(15), cursor.getInt(16),
				cursor.getString(17), cursor.getString(18), cursor.getString(19),
				cursor.getString(20), cursor.getString(21), cursor.getString(22),
				cursor.getString(23), cursor.getInt(24), cursor.getString(25),
				cursor.getString(26), cursor.getString(27), cursor.getString(28),
				cursor.getString(29), cursor.getString(30), cursor.getString(31),
				cursor.getString(32), cursor.getString(33), cursor.getString(34),
				cursor.getString(35), cursor.getInt(36),cursor.getString(37),
				cursor.getString(38),cursor.getString(39),
				cursor.getString(40),cursor.getString(41));
		// return customer
		cursor.close();
		db.close();

		return customer;
	}

	// Getting single Customer
	public Customer getCustomer(String kode_customer) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CUSTOMER, new String[] {
						KEY_CUSTOMER_ID_CUSTOMER, KEY_CUSTOMER_KODE_CUSTOMER,
						KEY_CUSTOMER_EMAIL_CUSTOMER, KEY_CUSTOMER_ALAMAT_CUSTOMER,
						KEY_CUSTOMER_LATS_CUSTOMER, KEY_CUSTOMER_LONGS_CUSTOMER,
						KEY_CUSTOMER_NAMA_LENGKAP_CUSTOMER,
						KEY_CUSTOMER_NO_TELP_CUSTOMER,
						KEY_CUSTOMER_ID_WILAYAH_CUSTOMER, KEY_CUSTOMER_FOTO_1_CUSTOMER,
						KEY_CUSTOMER_FOTO_2_CUSTOMER, KEY_CUSTOMER_FOTO_3_CUSTOMER,
						KEY_CUSTOMER_ID_TYPE_CUSTOMER, KEY_CUSTOMER_BLOKIR,
						KEY_CUSTOMER_DATE, KEY_CUSTOMER_STATUS_UPDATE,
						KEY_CUSTOMER_ID_STAFF, KEY_CUSTOMER_NO_KTP, KEY_CUSTOMER_TANGGAL_LAHIR,
						KEY_CUSTOMER_NAMA_BANK, KEY_CUSTOMER_NO_REKENING, KEY_CUSTOMER_ATAS_NAMA,
						KEY_CUSTOMER_NPWP,KEY_CUSTOMER_NAMA_PASAR, KEY_CUSTOMER_ID_CLUSTER,
						KEY_CUSTOMER_TELP, KEY_CUSTOMER_FAX, KEY_CUSTOMER_OMSET,
						KEY_CUSTOMER_CARA_PEMBAYARAN, KEY_CUSTOMER_PLAFON_KREDIT,
						KEY_CUSTOMER_TERM_KREDIT,KEY_CUSTOMER_NAMA_ISTRI, KEY_CUSTOMER_NAMA_ANAK1, KEY_CUSTOMER_NAMA_ANAK2,
						KEY_CUSTOMER_NAMA_ANAK3, KEY_CUSTOMER_KODE_POS, KEY_CUSTOMER_ID_DEPO, KEY_CUSTOMER_ISACTIVE,
						KEY_CUSTOMER_DESCRIPTION, KEY_CUSTOMER_NAMA_TOKO}, KEY_CUSTOMER_KODE_CUSTOMER + "=?",
				new String[] { kode_customer }, null, null, null, null);

		if (cursor != null)
			cursor.moveToFirst();

		Customer customer = new Customer(cursor.getInt(0), cursor.getString(1),
				cursor.getString(2), cursor.getString(3), cursor.getString(4),
				cursor.getString(5), cursor.getString(6), cursor.getString(7),
				cursor.getInt(8), cursor.getString(9), cursor.getString(10),
				cursor.getString(11), cursor.getInt(12), cursor.getString(13),
				cursor.getString(14), cursor.getString(15), cursor.getInt(16),
				cursor.getString(17), cursor.getString(18), cursor.getString(19),
				cursor.getString(20), cursor.getString(21), cursor.getString(22),
				cursor.getString(23), cursor.getInt(24), cursor.getString(25),
				cursor.getString(26), cursor.getString(27), cursor.getString(28),
				cursor.getString(29), cursor.getString(30), cursor.getString(31),
				cursor.getString(32), cursor.getString(33), cursor.getString(34),
				cursor.getString(35),cursor.getInt(36),cursor.getString(37),
				cursor.getString(38),cursor.getString(39),
				cursor.getString(40),cursor.getString(41));
		// return customer
		cursor.close();
		db.close();

		return customer;
	}

	// Getting single Customer
	public Customer getCustomerByKodeCustomer(String kode_customer) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CUSTOMER, new String[] {
						KEY_CUSTOMER_ID_CUSTOMER, KEY_CUSTOMER_KODE_CUSTOMER,
						KEY_CUSTOMER_EMAIL_CUSTOMER, KEY_CUSTOMER_ALAMAT_CUSTOMER,
						KEY_CUSTOMER_LATS_CUSTOMER, KEY_CUSTOMER_LONGS_CUSTOMER,
						KEY_CUSTOMER_NAMA_LENGKAP_CUSTOMER,
						KEY_CUSTOMER_NO_TELP_CUSTOMER,
						KEY_CUSTOMER_ID_WILAYAH_CUSTOMER, KEY_CUSTOMER_FOTO_1_CUSTOMER,
						KEY_CUSTOMER_FOTO_2_CUSTOMER, KEY_CUSTOMER_FOTO_3_CUSTOMER,
						KEY_CUSTOMER_ID_TYPE_CUSTOMER, KEY_CUSTOMER_BLOKIR,
						KEY_CUSTOMER_DATE, KEY_CUSTOMER_STATUS_UPDATE,
						KEY_CUSTOMER_ID_STAFF, KEY_CUSTOMER_NO_KTP, KEY_CUSTOMER_TANGGAL_LAHIR,
						KEY_CUSTOMER_NAMA_BANK, KEY_CUSTOMER_NO_REKENING, KEY_CUSTOMER_ATAS_NAMA,
						KEY_CUSTOMER_NPWP,KEY_CUSTOMER_NAMA_PASAR, KEY_CUSTOMER_ID_CLUSTER,
						KEY_CUSTOMER_TELP, KEY_CUSTOMER_FAX, KEY_CUSTOMER_OMSET,
						KEY_CUSTOMER_CARA_PEMBAYARAN, KEY_CUSTOMER_PLAFON_KREDIT,
						KEY_CUSTOMER_TERM_KREDIT,KEY_CUSTOMER_NAMA_ISTRI, KEY_CUSTOMER_NAMA_ANAK1, KEY_CUSTOMER_NAMA_ANAK2,
						KEY_CUSTOMER_NAMA_ANAK3,KEY_CUSTOMER_KODE_POS, KEY_CUSTOMER_ID_DEPO, KEY_CUSTOMER_ISACTIVE,
						KEY_CUSTOMER_DESCRIPTION, KEY_CUSTOMER_NAMA_TOKO}, KEY_CUSTOMER_KODE_CUSTOMER + "=?",
				new String[] { String.valueOf(kode_customer) }, null, null,
				null, null);

		if (cursor != null)
			cursor.moveToFirst();

		Customer customer = new Customer(cursor.getInt(0), cursor.getString(1),
				cursor.getString(2), cursor.getString(3), cursor.getString(4),
				cursor.getString(5), cursor.getString(6), cursor.getString(7),
				cursor.getInt(8), cursor.getString(9), cursor.getString(10),
				cursor.getString(11), cursor.getInt(12), cursor.getString(13),
				cursor.getString(14), cursor.getString(15), cursor.getInt(16),
				cursor.getString(17), cursor.getString(18), cursor.getString(19),
				cursor.getString(20), cursor.getString(21), cursor.getString(22),
				cursor.getString(23), cursor.getInt(24), cursor.getString(25),
				cursor.getString(26), cursor.getString(27), cursor.getString(28),
				cursor.getString(29), cursor.getString(30), cursor.getString(31),
				cursor.getString(32), cursor.getString(33), cursor.getString(34),
				cursor.getString(35),cursor.getInt(36),cursor.getString(37),
				cursor.getString(38),cursor.getString(39),
				cursor.getString(40),cursor.getString(41));
		// return customer
		cursor.close();
		db.close();

		return customer;
	}

	// Getting single Jadwal
	public Jadwal getJadwal(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_JADWAL, new String[] {
						KEY_JADWAL_ID_JADWAL, KEY_JADWAL_KODE_JADWAL,
						KEY_JADWAL_KODE_CUSTOMER, KEY_JADWAL_USERNAME,
						KEY_JADWAL_ALAMAT, KEY_JADWAL_NAMA_LENGKAP,
						KEY_JADWAL_ID_WILAYAH, KEY_JADWAL_STATUS, KEY_JADWAL_DATE,
						KEY_JADWAL_CHECK_IN, KEY_JADWAL_CHECK_OUT,
						KEY_JADWAL_STATUS_UPDATE }, KEY_JADWAL_ID_JADWAL + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);

		if (cursor != null)
			cursor.moveToFirst();

		Jadwal jadwal = new Jadwal(cursor.getInt(0), cursor.getString(1),
				cursor.getString(2), cursor.getString(3), cursor.getString(4),
				cursor.getString(5), cursor.getInt(6), cursor.getString(7),
				cursor.getString(8), cursor.getString(9), cursor.getString(10),
				cursor.getString(11));
		// return customer
		cursor.close();
		db.close();

		return jadwal;
	}

	// Getting single Product
	public Product getProduct(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_PRODUCT, new String[] {
						KEY_PRODUCT_ID_PRODUCT, KEY_PRODUCT_NAMA_PRODUCT,
						KEY_PRODUCT_KODE_PRODUCT, KEY_PRODUCT_HARGA_JUAL,
						KEY_PRODUCT_STOCK, KEY_PRODUCT_ID_KEMASAN, KEY_PRODUCT_FOTO,
						KEY_PRODUCT_DESKRIPSI }, KEY_PRODUCT_ID_PRODUCT + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Product product = new Product(cursor.getInt(0), cursor.getString(1),
				cursor.getString(2), cursor.getString(3), cursor.getString(4),
				cursor.getString(5), cursor.getString(6), cursor.getString(7),
				cursor.getString(8), cursor.getString(9), cursor.getString(10),
				cursor.getString(11),cursor.getString(12));
		// return staff
		cursor.close();
		db.close();

		return product;
	}

//	// Getting single Promosi
//	public Promosi getPromosi(int id) {
//		SQLiteDatabase db = this.getReadableDatabase();
//
//		Cursor cursor = db.query(TABLE_PROMOSI,
//				new String[] { KEY_PROMOSI_ID_PROMOSI,
//						KEY_PROMOSI_TITLE_PROMOSI, KEY_PROMOSI_JENIS_PROMOSI,
//						KEY_PROMOSI_JUMLAH_DISKON, KEY_PROMOSI_DESKRIPSI,
//						KEY_PROMOSI_FOTO, KEY_PROMOSI_DATE_TIME },
//				KEY_PROMOSI_ID_PROMOSI + "=?",
//				new String[] { String.valueOf(id) }, null, null, null, null);
//		if (cursor != null)
//			cursor.moveToFirst();
//
//		Promosi promosi = new Promosi(cursor.getInt(0), cursor.getString(1),
//				cursor.getString(2), cursor.getInt(3), cursor.getString(4),
//				cursor.getString(5), cursor.getString(6));
//		// return staff
//		cursor.close();
//		db.close();
//
//		return promosi;
//	}

	// Getting single PhotoPurchase
	public DisplayProduct getDisplayProduct(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_DISPLAY_PRODUCT, new String[] {
						KEY_DISPLAY_PRODUCT_ID_DISPLAY_PRODUCT,
						KEY_DISPLAY_PRODUCT_KODE_CUSTOMER,
						KEY_DISPLAY_PRODUCT_NAMA_LENGKAP,
						KEY_DISPLAY_PRODUCT_NAMA_DISPLAY_PRODUCT,
						KEY_DISPLAY_PRODUCT_DESKRIPSI, KEY_DISPLAY_PRODUCT_FOTO,
						KEY_DISPLAY_PRODUCT_DATE_TIME },
				KEY_DISPLAY_PRODUCT_ID_DISPLAY_PRODUCT + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		DisplayProduct displayProduct = new DisplayProduct(cursor.getInt(0),
				cursor.getString(1), cursor.getString(2), cursor.getString(3),
				cursor.getString(4), cursor.getString(5), cursor.getString(6));
		// return staff
		cursor.close();
		db.close();

		return displayProduct;
	}

//	// Getting single PhotoPurchase
//	public PhotoPurchase getPhotoPurchase(int id) {
//		SQLiteDatabase db = this.getReadableDatabase();
//
//		Cursor cursor = db.query(TABLE_PHOTO_PURCHASE, new String[] {
//				KEY_PHOTO_PURCHASE_ID_PHOTO_PURCHASE,
//				KEY_PHOTO_PURCHASE_KODE_CUSTOMER,
//				KEY_PHOTO_PURCHASE_NAMA_LENGKAP,
//				KEY_PHOTO_PURCHASE_NAMA_PHOTO_PURCHASE,
//				KEY_PHOTO_PURCHASE_DESKRIPSI, KEY_PHOTO_PURCHASE_FOTO,
//				KEY_PHOTO_PURCHASE_DATE_TIME },
//				KEY_PHOTO_PURCHASE_ID_PHOTO_PURCHASE + "=?",
//				new String[] { String.valueOf(id) }, null, null, null, null);
//		if (cursor != null)
//			cursor.moveToFirst();
//
//		PhotoPurchase photoPurchase = new PhotoPurchase(cursor.getInt(0),
//				cursor.getString(1), cursor.getString(2), cursor.getString(3),
//				cursor.getString(4), cursor.getString(5), cursor.getString(6));
//		// return staff
//		cursor.close();
//		db.close();
//
//		return photoPurchase;
//	}

	// Getting single Kemasan
	public Kemasan getKemasan(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_KEMASAN, new String[] {
						KEY_KEMASAN_ID_KEMASAN, KEY_KEMASAN_NAMA_KEMASAN },
				KEY_KEMASAN_ID_KEMASAN + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);

		if (cursor != null)
			cursor.moveToFirst();

		Kemasan kemasan = new Kemasan(cursor.getInt(0), cursor.getString(1));
		// return kemasan
		cursor.close();
		db.close();

		return kemasan;
	}

	// Getting single Branch
	public Branch getBranch(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_BRANCH, new String[] {
						KEY_BRANCH_ID_BRANCH, KEY_BRANCH_KODE_BRANCH,
						KEY_BRANCH_DESKRIPSI_BRANCH }, KEY_BRANCH_ID_BRANCH + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);

		if (cursor != null)
			cursor.moveToFirst();

		Branch branch = new Branch(cursor.getInt(0), cursor.getString(1),
				cursor.getString(2));
		// return branch
		cursor.close();
		db.close();

		return branch;
	}

	// Getting single TypeCustomer
	public TypeCustomer getTypeCustomer(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_TYPE_CUSTOMER, new String[] {
						KEY_TYPE_CUSTOMER_ID_TYPE_CUSTOMER,
						KEY_TYPE_CUSTOMER_TYPE_CUSTOMER, KEY_TYPE_CUSTOMER_DESKRIPSI },
				KEY_TYPE_CUSTOMER_ID_TYPE_CUSTOMER + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);

		if (cursor != null)
			cursor.moveToFirst();

		TypeCustomer typeCustomer = new TypeCustomer(cursor.getInt(0),
				cursor.getString(1), cursor.getString(2));
		// return branch
		cursor.close();
		db.close();
		return typeCustomer;
	}

	// Getting single Cluster
	public Cluster getCluster(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CLUSTER, new String[] {
						KEY_CLUSTER_ID_CLUSTER,
						KEY_CLUSTER_NAMA_CLUSTER, KEY_CLUSTER_DESCRIPTION },
				KEY_CLUSTER_ID_CLUSTER + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);

		if (cursor != null)
			cursor.moveToFirst();

		Cluster cluster = new Cluster(cursor.getInt(0),
				cursor.getString(1), cursor.getString(2));
		// return branch
		cursor.close();
		db.close();
		return cluster;
	}

	// Getting single Wilayah
	public Wilayah getWilayah(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_WILAYAH, new String[] {
						KEY_WILAYAH_ID_WILAYAH, KEY_WILAYAH_NAMA_WILAYAH,
						KEY_WILAYAH_DESKRIPSI_WILAYAH, KEY_WILAYAH_LATS_WILAYAH,
						KEY_WILAYAH_LONGS_WILAYAH }, KEY_WILAYAH_ID_WILAYAH + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);

		if (cursor != null)
			cursor.moveToFirst();

		Wilayah wilayah = new Wilayah(cursor.getInt(0), cursor.getString(1),
				cursor.getString(2), cursor.getString(3), cursor.getString(4));
		// return branch
		cursor.close();
		db.close();
		return wilayah;
	}

	// Getting ArrayList<Staff> below level
	public ArrayList<Staff> getAllStaff_Low_Level(int level) {

		try {
			staff_list.clear();

			SQLiteDatabase db = this.getReadableDatabase();

			Cursor cursor = db
					.rawQuery("select * FROM " + TABLE_STAFF
									+ " WHERE level >?",
							new String[] { String.valueOf(level) });
			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Staff staff = new Staff();
					staff.setId_staff(cursor.getInt(0));
					staff.setNama_lengkap(cursor.getString(1));
					staff.setUsername(cursor.getString(2));
					staff.setNotelp(cursor.getString(3));
					staff.setPassword(cursor.getString(4));
					staff.setLevel(cursor.getInt(5));
					staff.setId_branch(cursor.getString(6));
					staff.setId_type_customer(cursor.getString(7));
					staff.setId_depo(cursor.getInt(8));

					// Adding staff to list
					staff_list.add(staff);
				} while (cursor.moveToNext());
			}

			// return staff_list
			cursor.close();
			db.close();
			Log.e("staff_list", "staff_list = " + staff_list.size());
			return staff_list;
		} catch (Exception e) {
			Log.e("staff_list", "" + e);
		}

		return staff_list;

	}

	// Getting ArrayList<Staff> below level
	public ArrayList<StaffTemp> getAllStaff_Temp_Low_Level(int level) {

		try {
			staff_temp_list.clear();

			SQLiteDatabase db = this.getReadableDatabase();

			Cursor cursor = db
					.rawQuery("select * FROM " + TABLE_STAFF_TEMP
									+ " WHERE level >?",
							new String[] { String.valueOf(level) });
			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					StaffTemp staff_temp = new StaffTemp();
					staff_temp.setId_staff(cursor.getInt(0));
					staff_temp.setNama_lengkap(cursor.getString(1));
					staff_temp.setUsername(cursor.getString(2));
					staff_temp.setNotelp(cursor.getString(3));
					staff_temp.setPassword(cursor.getString(4));
					staff_temp.setLevel(cursor.getInt(5));
					staff_temp.setId_branch(cursor.getString(6));
					staff_temp.setId_type_customer(cursor.getString(7));
					staff_temp.setId_depo(cursor.getInt(8));

					// Adding staff_temp_list to list
					staff_temp_list.add(staff_temp);
				} while (cursor.moveToNext());
			}

			// return staff_list
			cursor.close();
			db.close();
			Log.e("staff_temp_list",
					"staff_temp_list = " + staff_temp_list.size());
			return staff_temp_list;
		} catch (Exception e) {
			Log.e("staff_temp_list", "" + e);
		}

		return staff_temp_list;

	}

	// Getting All Staff
	public ArrayList<Staff> getAllStaff() {
		try {
			staff_list.clear();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_STAFF;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Staff staff = new Staff();
					staff.setId_staff(cursor.getInt(0));
					staff.setNama_lengkap(cursor.getString(1));
					staff.setUsername(cursor.getString(2));
					staff.setNotelp(cursor.getString(3));
					staff.setPassword(cursor.getString(4));
					staff.setLevel(cursor.getInt(5));
					staff.setId_branch(cursor.getString(6));
					staff.setId_type_customer(cursor.getString(7));
					staff.setId_depo(cursor.getInt(8));

					// Adding staff to list
					staff_list.add(staff);
				} while (cursor.moveToNext());
			}

			// return staff_list
			cursor.close();
			db.close();
			return staff_list;
		} catch (Exception e) {
			Log.e("staff_list", "" + e);
		}

		return staff_list;
	}

	// Getting All Staff Temp
	public ArrayList<StaffTemp> getAllStaffTemp() {
		try {
			staff_temp_list.clear();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_STAFF_TEMP;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					StaffTemp staff = new StaffTemp();
					staff.setId_staff(cursor.getInt(0));
					staff.setNama_lengkap(cursor.getString(1));
					staff.setUsername(cursor.getString(2));
					staff.setNotelp(cursor.getString(3));
					staff.setPassword(cursor.getString(4));
					staff.setLevel(cursor.getInt(5));
					staff.setId_branch(cursor.getString(6));
					staff.setId_type_customer(cursor.getString(7));
					staff.setId_depo(cursor.getInt(8));

					// Adding staff to list
					staff_temp_list.add(staff);
				} while (cursor.moveToNext());
			}

			// return staff_list
			cursor.close();
			db.close();
			return staff_temp_list;
		} catch (Exception e) {
			Log.e("staff_temp_list", "" + e);
		}

		return staff_temp_list;
	}

	// Getting All Customer
	public ArrayList<Customer> getAllCustomer() {
		try {
			customer_list.clear();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_CUSTOMER;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Customer customer = new Customer();
					customer.setId_customer(cursor.getInt(0));
					customer.setKode_customer(cursor.getString(1));
					customer.setEmail(cursor.getString(2));
					customer.setAlamat(cursor.getString(3));
					customer.setLats(cursor.getString(4));
					customer.setLongs(cursor.getString(5));
					customer.setNama_lengkap(cursor.getString(6));
					customer.setNo_telp(cursor.getString(7));
					customer.setId_wilayah(cursor.getInt(8));
					customer.setFoto_1(cursor.getString(9));
					customer.setFoto_2(cursor.getString(10));
					customer.setFoto_3(cursor.getString(11));
					customer.setId_type_customer(cursor.getInt(12));
					customer.setBlokir(cursor.getString(13));
					customer.setDate(cursor.getString(14));
					customer.setStatus_update(cursor.getString(15));
					customer.setId_staff(cursor.getInt(16));
					customer.setNo_ktp(cursor.getString(17));
					customer.setTanggal_lahir(cursor.getString(18));
					customer.setNama_bank(cursor.getString(19));
					customer.setNo_rekening(cursor.getString(20));
					customer.setAtas_nama(cursor.getString(21));
					customer.setNpwp(cursor.getString(22));
					customer.setNama_pasar(cursor.getString(23));
					customer.setId_cluster(cursor.getInt(24));
					customer.setTelp(cursor.getString(25));
					customer.setFax(cursor.getString(26));
					customer.setOmset(cursor.getString(27));
					customer.setCara_pembayaran(cursor.getString(28));
					customer.setPlafon_kredit(cursor.getString(29));
					customer.setTerm_kredit(cursor.getString(30));
					customer.setNama_istri(cursor.getString(31));
					customer.setNama_anak1(cursor.getString(32));
					customer.setNama_anak2(cursor.getString(33));
					customer.setNama_anak3(cursor.getString(34));
					customer.setKode_pos(cursor.getString(35));
					customer.setId_depo(cursor.getInt(36));
					customer.setIsactive(cursor.getString(37));
					customer.setDescription(cursor.getString(38));
					customer.setNama_toko(cursor.getString(39));

					// Adding customer_list to list
					customer_list.add(customer);
				} while (cursor.moveToNext());
			}

			// return customer_list
			cursor.close();
			db.close();
			return customer_list;
		} catch (Exception e) {
			Log.e("customer_list", "" + e);
		}

		return customer_list;
	}

	// Getting All Jadwal
	public ArrayList<Jadwal> getAllJadwal() {
		try {
			jadwal_list.clear();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_JADWAL;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Jadwal jadwal = new Jadwal();
					jadwal.setId_jadwal(cursor.getInt(0));
					jadwal.setKode_jadwal(cursor.getString(1));
					jadwal.setKode_customer(cursor.getString(2));
					jadwal.setUsername(cursor.getString(3));
					jadwal.setAlamat(cursor.getString(4));
					jadwal.setNama_lengkap(cursor.getString(5));
					jadwal.setId_wilayah(cursor.getInt(6));
					jadwal.setStatus(cursor.getString(7));
					jadwal.setDate(cursor.getString(8));
					jadwal.setCheckin(cursor.getString(9));
					jadwal.setCheckout(cursor.getString(10));
					jadwal.setStatus_update(cursor.getString(11));
					// Adding jadwal_list to list
					jadwal_list.add(jadwal);
				} while (cursor.moveToNext());
			}

			// return jadwal_list
			cursor.close();
			db.close();
			return jadwal_list;
		} catch (Exception e) {
			Log.e("jadwal_list", "" + e);
		}

		return jadwal_list;
	}

	// Getting All Jadwal
	public ArrayList<Jadwal> getAllJadwalWhereKodeStaffAndGroupBy(
			String username) {
		try {
			jadwal_list.clear();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_JADWAL
					+ " WHERE username ='" + username + "' AND "
					+ KEY_JADWAL_CHECK_IN + "=''" + " GROUP BY "
					+ KEY_JADWAL_KODE_JADWAL;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Jadwal jadwal = new Jadwal();
					jadwal.setId_jadwal(cursor.getInt(0));
					jadwal.setKode_jadwal(cursor.getString(1));
					jadwal.setKode_customer(cursor.getString(2));
					jadwal.setUsername(cursor.getString(3));
					jadwal.setAlamat(cursor.getString(4));
					jadwal.setNama_lengkap(cursor.getString(5));
					jadwal.setId_wilayah(cursor.getInt(6));
					jadwal.setStatus(cursor.getString(7));
					jadwal.setDate(cursor.getString(8));
					jadwal.setCheckin(cursor.getString(9));
					jadwal.setCheckout(cursor.getString(10));
					jadwal.setStatus_update(cursor.getString(11));
					// Adding jadwal_list to list
					jadwal_list.add(jadwal);
				} while (cursor.moveToNext());
			}

			// return jadwal_list
			cursor.close();
			db.close();
			return jadwal_list;
		} catch (Exception e) {
			Log.e("jadwal_list", "" + e);
		}

		return jadwal_list;
	}

	// Getting All Jadwal
	public ArrayList<Jadwal> getAllJadwalWhereKodeStaffAndGroupByCustomer(
			String username) {
		try {
			jadwal_list.clear();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_JADWAL
					+ " WHERE username ='" + username + "' AND "
					+ "status_update =" + "2" + " GROUP BY "
					+ KEY_JADWAL_KODE_CUSTOMER;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Jadwal jadwal = new Jadwal();
					jadwal.setId_jadwal(cursor.getInt(0));
					jadwal.setKode_jadwal(cursor.getString(1));
					jadwal.setKode_customer(cursor.getString(2));
					jadwal.setUsername(cursor.getString(3));
					jadwal.setAlamat(cursor.getString(4));
					jadwal.setNama_lengkap(cursor.getString(5));
					jadwal.setId_wilayah(cursor.getInt(6));
					jadwal.setStatus(cursor.getString(7));
					jadwal.setDate(cursor.getString(8));
					jadwal.setCheckin(cursor.getString(9));
					jadwal.setCheckout(cursor.getString(10));
					jadwal.setStatus_update(cursor.getString(11));
					// Adding jadwal_list to list
					jadwal_list.add(jadwal);
				} while (cursor.moveToNext());
			}

			// return jadwal_list
			cursor.close();
			db.close();
			return jadwal_list;
		} catch (Exception e) {
			Log.e("jadwal_list", "" + e);
		}

		return jadwal_list;
	}

	// Getting All Jadwal
	public ArrayList<Jadwal> getAllJadwalWhereAlreadyCheckOut() {
		try {
			jadwal_list.clear();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_JADWAL + " WHERE "
					+ KEY_JADWAL_STATUS_UPDATE + "='3'";

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Jadwal jadwal = new Jadwal();
					jadwal.setId_jadwal(cursor.getInt(0));
					jadwal.setKode_jadwal(cursor.getString(1));
					jadwal.setKode_customer(cursor.getString(2));
					jadwal.setUsername(cursor.getString(3));
					jadwal.setAlamat(cursor.getString(4));
					jadwal.setNama_lengkap(cursor.getString(5));
					jadwal.setId_wilayah(cursor.getInt(6));
					jadwal.setStatus(cursor.getString(7));
					jadwal.setDate(cursor.getString(8));
					jadwal.setCheckin(cursor.getString(9));
					jadwal.setCheckout(cursor.getString(10));
					jadwal.setStatus_update(cursor.getString(11));
					// Adding jadwal_list to list
					jadwal_list.add(jadwal);
				} while (cursor.moveToNext());
			}

			// return jadwal_list
			cursor.close();
			db.close();
			return jadwal_list;
		} catch (Exception e) {
			Log.e("jadwal_list", "" + e);
		}

		return jadwal_list;
	}

	// Getting All Jadwal
	public ArrayList<Jadwal> getAllJadwalWhereKodeJadwal(String kode_jadwal) {
		try {
			jadwal_list.clear();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_JADWAL + " WHERE "
					+ KEY_JADWAL_KODE_JADWAL + "='" + kode_jadwal + "'";

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Jadwal jadwal = new Jadwal();
					jadwal.setId_jadwal(cursor.getInt(0));
					jadwal.setKode_jadwal(cursor.getString(1));
					jadwal.setKode_customer(cursor.getString(2));
					jadwal.setUsername(cursor.getString(3));
					jadwal.setAlamat(cursor.getString(4));
					jadwal.setNama_lengkap(cursor.getString(5));
					jadwal.setId_wilayah(cursor.getInt(6));
					jadwal.setStatus(cursor.getString(7));
					jadwal.setDate(cursor.getString(8));
					jadwal.setCheckin(cursor.getString(9));
					jadwal.setCheckout(cursor.getString(10));
					jadwal.setStatus_update(cursor.getString(11));
					// Adding jadwal_list to list
					jadwal_list.add(jadwal);
				} while (cursor.moveToNext());
			}

			// return jadwal_list
			cursor.close();
			db.close();
			return jadwal_list;
		} catch (Exception e) {
			Log.e("jadwal_list", "" + e);
		}

		return jadwal_list;
	}

	// Getting All Jadwal
	public ArrayList<Jadwal> getAllJadwalWhereKodeJadwalAndSearch(
			String kode_jadwal, String search) {
		try {
			jadwal_list.clear();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_JADWAL + " WHERE "
					+ KEY_JADWAL_KODE_JADWAL + "='" + kode_jadwal + "' AND "
					+ KEY_JADWAL_NAMA_LENGKAP + " LIKE '" + search + "%'";

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Jadwal jadwal = new Jadwal();
					jadwal.setId_jadwal(cursor.getInt(0));
					jadwal.setKode_jadwal(cursor.getString(1));
					jadwal.setKode_customer(cursor.getString(2));
					jadwal.setUsername(cursor.getString(3));
					jadwal.setAlamat(cursor.getString(4));
					jadwal.setNama_lengkap(cursor.getString(5));
					jadwal.setId_wilayah(cursor.getInt(6));
					jadwal.setStatus(cursor.getString(7));
					jadwal.setDate(cursor.getString(8));
					jadwal.setCheckin(cursor.getString(9));
					jadwal.setCheckout(cursor.getString(10));
					jadwal.setStatus_update(cursor.getString(11));
					// Adding jadwal_list to list
					jadwal_list.add(jadwal);
				} while (cursor.moveToNext());
			}

			// return jadwal_list
			cursor.close();
			db.close();
			return jadwal_list;
		} catch (Exception e) {
			Log.e("jadwal_list", "" + e);
		}

		return jadwal_list;
	}

	// Getting All Jadwal
	public ArrayList<Jadwal> getAllJadwalGroupByKodeJadwal() {
		try {
			jadwal_list.clear();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_JADWAL
					+ " GROUP BY " + KEY_JADWAL_KODE_JADWAL;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Jadwal jadwal = new Jadwal();
					jadwal.setId_jadwal(cursor.getInt(0));
					jadwal.setKode_jadwal(cursor.getString(1));
					jadwal.setKode_customer(cursor.getString(2));
					jadwal.setUsername(cursor.getString(3));
					jadwal.setAlamat(cursor.getString(4));
					jadwal.setNama_lengkap(cursor.getString(5));
					jadwal.setId_wilayah(cursor.getInt(6));
					jadwal.setStatus(cursor.getString(7));
					jadwal.setDate(cursor.getString(8));
					jadwal.setCheckin(cursor.getString(9));
					jadwal.setCheckout(cursor.getString(10));
					jadwal.setStatus_update(cursor.getString(11));
					// Adding jadwal_list to list
					jadwal_list.add(jadwal);
				} while (cursor.moveToNext());
			}

			// return jadwal_list
			cursor.close();
			db.close();
			return jadwal_list;
		} catch (Exception e) {
			Log.e("jadwal_list", "" + e);
		}

		return jadwal_list;
	}

	// Getting All Customer
	public ArrayList<Customer> getAllCustomerProspect() {
		try {
			customer_list.clear();

			// Select All Query
			String selectQuery = "SELECT * FROM " + TABLE_CUSTOMER
					+ " WHERE blokir ='Y'";
			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Customer customer = new Customer();
					customer.setId_customer(cursor.getInt(0));
					customer.setKode_customer(cursor.getString(1));
					customer.setEmail(cursor.getString(2));
					customer.setAlamat(cursor.getString(3));
					customer.setLats(cursor.getString(4));
					customer.setLongs(cursor.getString(5));
					customer.setNama_lengkap(cursor.getString(6));
					customer.setNo_telp(cursor.getString(7));
					customer.setId_wilayah(cursor.getInt(8));
					customer.setFoto_1(cursor.getString(9));
					customer.setFoto_2(cursor.getString(10));
					customer.setFoto_3(cursor.getString(11));
					customer.setId_type_customer(cursor.getInt(12));
					customer.setBlokir(cursor.getString(13));
					customer.setDate(cursor.getString(14));
					customer.setStatus_update(cursor.getString(15));
					customer.setId_staff(cursor.getInt(16));
					customer.setNo_ktp(cursor.getString(17));
					customer.setTanggal_lahir(cursor.getString(18));
					customer.setNama_bank(cursor.getString(19));
					customer.setNo_rekening(cursor.getString(20));
					customer.setAtas_nama(cursor.getString(21));
					customer.setNpwp(cursor.getString(22));
					customer.setNama_pasar(cursor.getString(23));
					customer.setId_cluster(cursor.getInt(24));
					customer.setTelp(cursor.getString(25));
					customer.setFax(cursor.getString(26));
					customer.setOmset(cursor.getString(27));
					customer.setCara_pembayaran(cursor.getString(28));
					customer.setPlafon_kredit(cursor.getString(29));
					customer.setTerm_kredit(cursor.getString(30));
					customer.setNama_istri(cursor.getString(31));
					customer.setNama_anak1(cursor.getString(32));
					customer.setNama_anak2(cursor.getString(33));
					customer.setNama_anak3(cursor.getString(34));
					customer.setKode_pos(cursor.getString(35));
					customer.setId_depo(cursor.getInt(36));
					customer.setIsactive(cursor.getString(37));
					customer.setDescription(cursor.getString(38));
					customer.setNama_toko(cursor.getString(39));
					// Adding customer_list to list
					customer_list.add(customer);
				} while (cursor.moveToNext());
			}

			// return customer_list
			cursor.close();
			db.close();
			return customer_list;
		} catch (Exception e) {
			Log.e("customer_list", "" + e);
		}

		return customer_list;
	}

	// Getting All Customer
	public ArrayList<Customer> getAllCustomerActive() {
		try {
			customer_list.clear();

			// Select All Query
			String selectQuery = "SELECT * FROM " + TABLE_CUSTOMER
					+ " WHERE blokir ='N'";
			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Customer customer = new Customer();
					customer.setId_customer(cursor.getInt(0));
					customer.setKode_customer(cursor.getString(1));
					customer.setEmail(cursor.getString(2));
					customer.setAlamat(cursor.getString(3));
					customer.setLats(cursor.getString(4));
					customer.setLongs(cursor.getString(5));
					customer.setNama_lengkap(cursor.getString(6));
					customer.setNo_telp(cursor.getString(7));
					customer.setId_wilayah(cursor.getInt(8));
					customer.setFoto_1(cursor.getString(9));
					customer.setFoto_2(cursor.getString(10));
					customer.setFoto_3(cursor.getString(11));
					customer.setId_type_customer(cursor.getInt(12));
					customer.setBlokir(cursor.getString(13));
					customer.setDate(cursor.getString(14));
					customer.setStatus_update(cursor.getString(15));
					customer.setId_staff(cursor.getInt(16));
					customer.setNo_ktp(cursor.getString(17));
					customer.setTanggal_lahir(cursor.getString(18));
					customer.setNama_bank(cursor.getString(19));
					customer.setNo_rekening(cursor.getString(20));
					customer.setAtas_nama(cursor.getString(21));
					customer.setNpwp(cursor.getString(22));
					customer.setNama_pasar(cursor.getString(23));
					customer.setId_cluster(cursor.getInt(24));
					customer.setTelp(cursor.getString(25));
					customer.setFax(cursor.getString(26));
					customer.setOmset(cursor.getString(27));
					customer.setCara_pembayaran(cursor.getString(28));
					customer.setPlafon_kredit(cursor.getString(29));
					customer.setTerm_kredit(cursor.getString(30));

					customer.setNama_istri(cursor.getString(31));
					customer.setNama_anak1(cursor.getString(32));
					customer.setNama_anak2(cursor.getString(33));
					customer.setNama_anak3(cursor.getString(34));
					customer.setKode_pos(cursor.getString(35));
					customer.setId_depo(cursor.getInt(36));
					customer.setIsactive(cursor.getString(37));
					customer.setDescription(cursor.getString(38));
					customer.setNama_toko(cursor.getString(39));
					// Adding customer_list to list
					customer_list.add(customer);
				} while (cursor.moveToNext());
			}

			// return customer_list
			cursor.close();
			db.close();
			return customer_list;
		} catch (Exception e) {
			Log.e("customer_list", "" + e);
		}

		return customer_list;
	}

	// Getting All Customer
	public ArrayList<Customer> getAllCustomerActive(int id_depo) {
		try {
			customer_list.clear();

			// Select All Query
			String selectQuery = "SELECT * FROM " + TABLE_CUSTOMER
					+ " WHERE blokir ='N' AND id_depo=" + id_depo;
			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Customer customer = new Customer();
					customer.setId_customer(cursor.getInt(0));
					customer.setKode_customer(cursor.getString(1));
					customer.setEmail(cursor.getString(2));
					customer.setAlamat(cursor.getString(3));
					customer.setLats(cursor.getString(4));
					customer.setLongs(cursor.getString(5));
					customer.setNama_lengkap(cursor.getString(6));
					customer.setNo_telp(cursor.getString(7));
					customer.setId_wilayah(cursor.getInt(8));
					customer.setFoto_1(cursor.getString(9));
					customer.setFoto_2(cursor.getString(10));
					customer.setFoto_3(cursor.getString(11));
					customer.setId_type_customer(cursor.getInt(12));
					customer.setBlokir(cursor.getString(13));
					customer.setDate(cursor.getString(14));
					customer.setStatus_update(cursor.getString(15));
					customer.setId_staff(cursor.getInt(16));
					customer.setNo_ktp(cursor.getString(17));
					customer.setTanggal_lahir(cursor.getString(18));
					customer.setNama_bank(cursor.getString(19));
					customer.setNo_rekening(cursor.getString(20));
					customer.setAtas_nama(cursor.getString(21));
					customer.setNpwp(cursor.getString(22));
					customer.setNama_pasar(cursor.getString(23));
					customer.setId_cluster(cursor.getInt(24));
					customer.setTelp(cursor.getString(25));
					customer.setFax(cursor.getString(26));
					customer.setOmset(cursor.getString(27));
					customer.setCara_pembayaran(cursor.getString(28));
					customer.setPlafon_kredit(cursor.getString(29));
					customer.setTerm_kredit(cursor.getString(30));
					customer.setNama_istri(cursor.getString(31));
					customer.setNama_anak1(cursor.getString(32));
					customer.setNama_anak2(cursor.getString(33));
					customer.setNama_anak3(cursor.getString(34));
					customer.setKode_pos(cursor.getString(35));
					customer.setId_depo(cursor.getInt(36));
					customer.setIsactive(cursor.getString(37));
					customer.setDescription(cursor.getString(38));
					customer.setNama_toko(cursor.getString(39));
					// Adding customer_list to list
					customer_list.add(customer);
				} while (cursor.moveToNext());
			}

			// return customer_list
			cursor.close();
			db.close();
			return customer_list;
		} catch (Exception e) {
			Log.e("customer_list", "" + e);
		}

		return customer_list;
	}

	// Getting All Customer
	public ArrayList<Customer> getAllCustomerActiveAndUpdateByUser() {
		try {
			customer_list.clear();

			// Select All Query
			String selectQuery = "SELECT * FROM " + TABLE_CUSTOMER
					+ " WHERE blokir ='N' AND status_update='2'";
			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Customer customer = new Customer();
					customer.setId_customer(cursor.getInt(0));
					customer.setKode_customer(cursor.getString(1));
					customer.setEmail(cursor.getString(2));
					customer.setAlamat(cursor.getString(3));
					customer.setLats(cursor.getString(4));
					customer.setLongs(cursor.getString(5));
					customer.setNama_lengkap(cursor.getString(6));
					customer.setNo_telp(cursor.getString(7));
					customer.setId_wilayah(cursor.getInt(8));
					customer.setFoto_1(cursor.getString(9));
					customer.setFoto_2(cursor.getString(10));
					customer.setFoto_3(cursor.getString(11));
					customer.setId_type_customer(cursor.getInt(12));
					customer.setBlokir(cursor.getString(13));
					customer.setDate(cursor.getString(14));
					customer.setStatus_update(cursor.getString(15));
					customer.setId_staff(cursor.getInt(16));
					customer.setNo_ktp(cursor.getString(17));
					customer.setTanggal_lahir(cursor.getString(18));
					customer.setNama_bank(cursor.getString(19));
					customer.setNo_rekening(cursor.getString(20));
					customer.setAtas_nama(cursor.getString(21));
					customer.setNpwp(cursor.getString(22));
					customer.setNama_pasar(cursor.getString(23));
					customer.setId_cluster(cursor.getInt(24));
					customer.setTelp(cursor.getString(25));
					customer.setFax(cursor.getString(26));
					customer.setOmset(cursor.getString(27));
					customer.setCara_pembayaran(cursor.getString(28));
					customer.setPlafon_kredit(cursor.getString(29));
					customer.setTerm_kredit(cursor.getString(30));
					customer.setNama_istri(cursor.getString(31));
					customer.setNama_anak1(cursor.getString(32));
					customer.setNama_anak2(cursor.getString(33));
					customer.setNama_anak3(cursor.getString(34));
					customer.setKode_pos(cursor.getString(35));
					customer.setId_depo(cursor.getInt(36));
					customer.setIsactive(cursor.getString(37));
					customer.setDescription(cursor.getString(38));
					customer.setNama_toko(cursor.getString(39));
					// Adding customer_list to list
					customer_list.add(customer);
				} while (cursor.moveToNext());
			}

			// return customer_list
			cursor.close();
			db.close();
			return customer_list;
		} catch (Exception e) {
			Log.e("customer_list", "" + e);
		}

		return customer_list;
	}

	// Getting All Customer
	public ArrayList<Customer> getAllCustomerActiveBaseOnSearch(String search,
																int id_wilayah) {
		try {
			customer_list.clear();

			// Select All Query
			String selectQuery = "SELECT * FROM " + TABLE_CUSTOMER
					+ " WHERE blokir ='N' AND "
					+ KEY_CUSTOMER_NAMA_LENGKAP_CUSTOMER + " LIKE '" + search
					+ "%' AND id_depo=" + id_wilayah;
			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Customer customer = new Customer();
					customer.setId_customer(cursor.getInt(0));
					customer.setKode_customer(cursor.getString(1));
					customer.setEmail(cursor.getString(2));
					customer.setAlamat(cursor.getString(3));
					customer.setLats(cursor.getString(4));
					customer.setLongs(cursor.getString(5));
					customer.setNama_lengkap(cursor.getString(6));
					customer.setNo_telp(cursor.getString(7));
					customer.setId_wilayah(cursor.getInt(8));
					customer.setFoto_1(cursor.getString(9));
					customer.setFoto_2(cursor.getString(10));
					customer.setFoto_3(cursor.getString(11));
					customer.setId_type_customer(cursor.getInt(12));
					customer.setBlokir(cursor.getString(13));
					customer.setDate(cursor.getString(14));
					customer.setStatus_update(cursor.getString(15));
					customer.setId_staff(cursor.getInt(16));
					customer.setNo_ktp(cursor.getString(17));
					customer.setTanggal_lahir(cursor.getString(18));
					customer.setNama_bank(cursor.getString(19));
					customer.setNo_rekening(cursor.getString(20));
					customer.setAtas_nama(cursor.getString(21));
					customer.setNpwp(cursor.getString(22));
					customer.setNama_pasar(cursor.getString(23));
					customer.setId_cluster(cursor.getInt(24));
					customer.setTelp(cursor.getString(25));
					customer.setFax(cursor.getString(26));
					customer.setOmset(cursor.getString(27));
					customer.setCara_pembayaran(cursor.getString(28));
					customer.setPlafon_kredit(cursor.getString(29));
					customer.setTerm_kredit(cursor.getString(30));

					customer.setNama_istri(cursor.getString(31));
					customer.setNama_anak1(cursor.getString(32));
					customer.setNama_anak2(cursor.getString(33));
					customer.setNama_anak3(cursor.getString(34));
					customer.setId_depo(cursor.getInt(36));
					customer.setIsactive(cursor.getString(37));
					customer.setDescription(cursor.getString(38));
					customer.setNama_toko(cursor.getString(39));
					// Adding customer_list to list
					customer_list.add(customer);
				} while (cursor.moveToNext());
			}

			// return customer_list
			cursor.close();
			db.close();
			return customer_list;
		} catch (Exception e) {
			Log.e("customer_list", "" + e);
		}

		return customer_list;
	}

	// Getting All Customer
	public ArrayList<Customer> getAllCustomerProspectBaseOnSearch(String search) {
		try {
			customer_list.clear();

			// Select All Query
			String selectQuery = "SELECT * FROM " + TABLE_CUSTOMER
					+ " WHERE blokir ='Y' AND "
					+ KEY_CUSTOMER_NAMA_LENGKAP_CUSTOMER + " LIKE '" + search
					+ "%'";
			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Customer customer = new Customer();
					customer.setId_customer(cursor.getInt(0));
					customer.setKode_customer(cursor.getString(1));
					customer.setEmail(cursor.getString(2));
					customer.setAlamat(cursor.getString(3));
					customer.setLats(cursor.getString(4));
					customer.setLongs(cursor.getString(5));
					customer.setNama_lengkap(cursor.getString(6));
					customer.setNo_telp(cursor.getString(7));
					customer.setId_wilayah(cursor.getInt(8));
					customer.setFoto_1(cursor.getString(9));
					customer.setFoto_2(cursor.getString(10));
					customer.setFoto_3(cursor.getString(11));
					customer.setId_type_customer(cursor.getInt(12));
					customer.setBlokir(cursor.getString(13));
					customer.setDate(cursor.getString(14));
					customer.setStatus_update(cursor.getString(15));
					customer.setId_staff(cursor.getInt(16));
					customer.setNo_ktp(cursor.getString(17));
					customer.setTanggal_lahir(cursor.getString(18));
					customer.setNama_bank(cursor.getString(19));
					customer.setNo_rekening(cursor.getString(20));
					customer.setAtas_nama(cursor.getString(21));
					customer.setNpwp(cursor.getString(22));
					customer.setNama_pasar(cursor.getString(23));
					customer.setId_cluster(cursor.getInt(24));
					customer.setTelp(cursor.getString(25));
					customer.setFax(cursor.getString(26));
					customer.setOmset(cursor.getString(27));
					customer.setCara_pembayaran(cursor.getString(28));
					customer.setPlafon_kredit(cursor.getString(29));
					customer.setTerm_kredit(cursor.getString(30));
					customer.setNama_istri(cursor.getString(31));
					customer.setNama_anak1(cursor.getString(32));
					customer.setNama_anak2(cursor.getString(33));
					customer.setNama_anak3(cursor.getString(34));
					customer.setKode_pos(cursor.getString(35));
					customer.setId_depo(cursor.getInt(36));
					customer.setIsactive(cursor.getString(37));
					customer.setDescription(cursor.getString(38));
					customer.setNama_toko(cursor.getString(39));
					// Adding customer_list to list
					customer_list.add(customer);
				} while (cursor.moveToNext());
			}

			// return customer_list
			cursor.close();
			db.close();
			return customer_list;
		} catch (Exception e) {
			Log.e("customer_list", "" + e);
		}

		return customer_list;
	}

	// Getting All Stock On Hand
	public ArrayList<StockOnHand> getAllStockOnHand() {
		try {
			stock_on_hand_list.clear();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_STOCK_ON_HAND;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					StockOnHand stockOnHand = new StockOnHand();
					stockOnHand.setId_stock_on_hand(cursor.getInt(0));
					stockOnHand.setNomer_stock_on_hand(cursor.getString(1));
					stockOnHand.setNomer_stock_on_hand_detail(cursor
							.getString(2));
					stockOnHand.setDate_stock_on_hand(cursor.getString(3));
					stockOnHand.setTime_stock_on_hand(cursor.getString(4));
					stockOnHand.setDeskripsi(cursor.getString(5));
					stockOnHand.setUsername(cursor.getString(6));
					stockOnHand.setKode_customer(cursor.getString(7));
					stockOnHand.setAlamat(cursor.getString(8));
					stockOnHand.setNama_lengkap(cursor.getString(9));
					stockOnHand.setNama_product(cursor.getString(10));
					stockOnHand.setKode_product(cursor.getString(11));
					stockOnHand.setHarga_jual(cursor.getString(12));
					stockOnHand.setStock(cursor.getString(13));

					// Adding stock_on_hand_list to list
					stock_on_hand_list.add(stockOnHand);
				} while (cursor.moveToNext());
			}

			// return stock_on_hand_list
			cursor.close();
			db.close();
			return stock_on_hand_list;
		} catch (Exception e) {
			Log.e("stock_on_hand_list", "" + e);
		}

		return stock_on_hand_list;
	}

	// Getting All Stock On Hand
	public ArrayList<StockOnHand> getAllStockOnHandWhereStockOnHand(
			String nomer_stock_on_hand) {
		try {
			stock_on_hand_list.clear();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_STOCK_ON_HAND
					+ " WHERE nomer_stock_on_hand ='" + nomer_stock_on_hand
					+ "' ";

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					StockOnHand stockOnHand = new StockOnHand();
					stockOnHand.setId_stock_on_hand(cursor.getInt(0));
					stockOnHand.setNomer_stock_on_hand(cursor.getString(1));
					stockOnHand.setNomer_stock_on_hand_detail(cursor
							.getString(2));
					stockOnHand.setDate_stock_on_hand(cursor.getString(3));
					stockOnHand.setTime_stock_on_hand(cursor.getString(4));
					stockOnHand.setDeskripsi(cursor.getString(5));
					stockOnHand.setUsername(cursor.getString(6));
					stockOnHand.setKode_customer(cursor.getString(7));
					stockOnHand.setAlamat(cursor.getString(8));
					stockOnHand.setNama_lengkap(cursor.getString(9));
					stockOnHand.setNama_product(cursor.getString(10));
					stockOnHand.setKode_product(cursor.getString(11));
					stockOnHand.setHarga_jual(cursor.getString(12));
					stockOnHand.setStock(cursor.getString(13));

					// Adding stock_on_hand_list to list
					stock_on_hand_list.add(stockOnHand);
				} while (cursor.moveToNext());
			}

			// return stock_on_hand_list
			cursor.close();
			db.close();
			return stock_on_hand_list;
		} catch (Exception e) {
			Log.e("stock_on_hand_list", "" + e);
		}

		return stock_on_hand_list;
	}

	// Getting All Sales Order
	public ArrayList<StockOnHand> getAllStockOnHandGroupByNomerOrder() {
		try {
			stock_on_hand_list.clear();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_STOCK_ON_HAND
					+ " GROUP BY " + KEY_STOCK_ON_HAND_NOMER_STOCK_ON_HAND;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					StockOnHand stockOnHand = new StockOnHand();
					stockOnHand.setId_stock_on_hand(cursor.getInt(0));
					stockOnHand.setNomer_stock_on_hand(cursor.getString(1));
					stockOnHand.setNomer_stock_on_hand_detail(cursor
							.getString(2));
					stockOnHand.setDate_stock_on_hand(cursor.getString(3));
					stockOnHand.setTime_stock_on_hand(cursor.getString(4));
					stockOnHand.setDeskripsi(cursor.getString(5));
					stockOnHand.setUsername(cursor.getString(6));
					stockOnHand.setKode_customer(cursor.getString(7));
					stockOnHand.setAlamat(cursor.getString(8));
					stockOnHand.setNama_lengkap(cursor.getString(9));
					stockOnHand.setNama_product(cursor.getString(10));
					stockOnHand.setKode_product(cursor.getString(11));
					stockOnHand.setHarga_jual(cursor.getString(12));
					stockOnHand.setStock(cursor.getString(13));

					// Adding stock_on_hand_list to list
					stock_on_hand_list.add(stockOnHand);
				} while (cursor.moveToNext());
			}

			// return stock_on_hand_list
			cursor.close();
			db.close();
			return stock_on_hand_list;
		} catch (Exception e) {
			Log.e("stock_on_hand_list", "" + e);
		}

		return stock_on_hand_list;
	}

	// Getting All Sales Order
	public ArrayList<SalesOrder> getAllSalesOrder() {
		try {
			sales_order_list.clear();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_SALES_ORDER;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					SalesOrder salesOrder = new SalesOrder();
					salesOrder.setId_sales_order(cursor.getInt(0));
					salesOrder.setNomer_order(cursor.getString(1));
					salesOrder.setNomer_order_detail(cursor.getString(2));
					salesOrder.setDate_order(cursor.getString(3));
					salesOrder.setTime_order(cursor.getString(4));
					salesOrder.setDeskripsi(cursor.getString(5));
					salesOrder.setId_promosi(cursor.getInt(6));
					salesOrder.setUsername(cursor.getString(7));
					salesOrder.setKode_customer(cursor.getString(8));
					salesOrder.setAlamat(cursor.getString(9));
					salesOrder.setNama_lengkap(cursor.getString(10));
					salesOrder.setNama_product(cursor.getString(11));
					salesOrder.setKode_product(cursor.getString(12));
					salesOrder.setHarga_jual(cursor.getString(13));
					salesOrder.setJumlah_order(cursor.getString(14));

					// Adding sales_order_list to list
					sales_order_list.add(salesOrder);
				} while (cursor.moveToNext());
			}

			// return sales_order_list
			cursor.close();
			db.close();
			return sales_order_list;
		} catch (Exception e) {
			Log.e("sales_order_list", "" + e);
		}

		return sales_order_list;
	}

	// Getting All Sales Order
	public ArrayList<SalesOrder> getAllSalesOrderGroupByNomerOrder() {
		try {
			sales_order_list.clear();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_SALES_ORDER
					+ " GROUP BY " + KEY_SALES_ORDER_NOMER_ORDER;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					SalesOrder salesOrder = new SalesOrder();
					salesOrder.setId_sales_order(cursor.getInt(0));
					salesOrder.setNomer_order(cursor.getString(1));
					salesOrder.setNomer_order_detail(cursor.getString(2));
					salesOrder.setDate_order(cursor.getString(3));
					salesOrder.setTime_order(cursor.getString(4));
					salesOrder.setDeskripsi(cursor.getString(5));
					salesOrder.setId_promosi(cursor.getInt(6));
					salesOrder.setUsername(cursor.getString(7));
					salesOrder.setKode_customer(cursor.getString(8));
					salesOrder.setAlamat(cursor.getString(9));
					salesOrder.setNama_lengkap(cursor.getString(10));
					salesOrder.setNama_product(cursor.getString(11));
					salesOrder.setKode_product(cursor.getString(12));
					salesOrder.setHarga_jual(cursor.getString(13));
					salesOrder.setJumlah_order(cursor.getString(14));

					// Adding sales_order_list to list
					sales_order_list.add(salesOrder);
				} while (cursor.moveToNext());
			}

			// return sales_order_list
			cursor.close();
			db.close();
			return sales_order_list;
		} catch (Exception e) {
			Log.e("sales_order_list", "" + e);
		}

		return sales_order_list;
	}

	// Getting All Sales Order
	public ArrayList<SalesOrder> getAllSalesOrderWhereNomerOrder(
			String nomer_order) {
		try {
			sales_order_list.clear();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_SALES_ORDER
					+ " WHERE nomer_order ='" + nomer_order + "' ";

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					SalesOrder salesOrder = new SalesOrder();
					salesOrder.setId_sales_order(cursor.getInt(0));
					salesOrder.setNomer_order(cursor.getString(1));
					salesOrder.setNomer_order_detail(cursor.getString(2));
					salesOrder.setDate_order(cursor.getString(3));
					salesOrder.setTime_order(cursor.getString(4));
					salesOrder.setDeskripsi(cursor.getString(5));
					salesOrder.setId_promosi(cursor.getInt(6));
					salesOrder.setUsername(cursor.getString(7));
					salesOrder.setKode_customer(cursor.getString(8));
					salesOrder.setAlamat(cursor.getString(9));
					salesOrder.setNama_lengkap(cursor.getString(10));
					salesOrder.setNama_product(cursor.getString(11));
					salesOrder.setKode_product(cursor.getString(12));
					salesOrder.setHarga_jual(cursor.getString(13));
					salesOrder.setJumlah_order(cursor.getString(14));

					// Adding sales_order_list to list
					sales_order_list.add(salesOrder);
				} while (cursor.moveToNext());
			}

			// return sales_order_list
			cursor.close();
			db.close();
			return sales_order_list;
		} catch (Exception e) {
			Log.e("sales_order_list", "" + e);
		}

		return sales_order_list;
	}

	// Getting All Tracking
	public ArrayList<Tracking> getAllTracking() {
		try {
			tracking_list.clear();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_TRACKING;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Tracking tracking = new Tracking();
					tracking.setId_locator(cursor.getInt(0));
					tracking.setUsername(cursor.getString(1));
					tracking.setNama_lengkap(cursor.getString(2));
					tracking.setLevel(cursor.getInt(3));
					tracking.setLats(cursor.getString(4));
					tracking.setLongs(cursor.getString(5));
					tracking.setAddress(cursor.getString(6));
					tracking.setImei(cursor.getString(7));
					tracking.setMcc(cursor.getString(8));
					tracking.setMnc(cursor.getString(9));
					tracking.setDate(cursor.getString(10));
					tracking.setTime(cursor.getString(11));
					// Adding tracking_list to list
					tracking_list.add(tracking);
				} while (cursor.moveToNext());
			}

			// return tracking_list
			cursor.close();
			db.close();
			return tracking_list;
		} catch (Exception e) {
			Log.e("tracking_list", "" + e);
		}

		return tracking_list;
	}

	// Update Customer
	public int updateCustomer(int id, Customer customer) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_CUSTOMER_KODE_CUSTOMER, customer.getKode_customer());
		values.put(KEY_CUSTOMER_EMAIL_CUSTOMER, customer.getEmail());
		values.put(KEY_CUSTOMER_ALAMAT_CUSTOMER, customer.getAlamat());
		values.put(KEY_CUSTOMER_LATS_CUSTOMER, customer.getLats());
		values.put(KEY_CUSTOMER_LONGS_CUSTOMER, customer.getLongs());
		values.put(KEY_CUSTOMER_NAMA_LENGKAP_CUSTOMER,
				customer.getNama_lengkap());
		values.put(KEY_CUSTOMER_NO_TELP_CUSTOMER, customer.getNo_telp());
		values.put(KEY_CUSTOMER_ID_WILAYAH_CUSTOMER, customer.getId_wilayah());
		values.put(KEY_CUSTOMER_FOTO_1_CUSTOMER, customer.getFoto_1());
		values.put(KEY_CUSTOMER_FOTO_2_CUSTOMER, customer.getFoto_2());
		values.put(KEY_CUSTOMER_FOTO_3_CUSTOMER, customer.getFoto_3());
		values.put(KEY_CUSTOMER_ID_TYPE_CUSTOMER, customer.getId_type_customer());
		values.put(KEY_CUSTOMER_BLOKIR, customer.getBlokir());
		values.put(KEY_CUSTOMER_DATE, customer.getDate());
		values.put(KEY_CUSTOMER_STATUS_UPDATE, customer.getStatus_update());
		values.put(KEY_CUSTOMER_ID_STAFF, customer.getId_staff());
		values.put(KEY_CUSTOMER_NO_KTP, customer.getNo_ktp());
		values.put(KEY_CUSTOMER_TANGGAL_LAHIR, customer.getTanggal_lahir());
		values.put(KEY_CUSTOMER_NAMA_BANK, customer.getNama_bank());
		values.put(KEY_CUSTOMER_NO_REKENING, customer.getNo_rekening());
		values.put(KEY_CUSTOMER_ATAS_NAMA, customer.getAtas_nama());
		values.put(KEY_CUSTOMER_NPWP, customer.getNpwp());
		values.put(KEY_CUSTOMER_NAMA_PASAR, customer.getNama_pasar());
		values.put(KEY_CUSTOMER_ID_CLUSTER, customer.getId_cluster());
		values.put(KEY_CUSTOMER_TELP, customer.getTelp());
		values.put(KEY_CUSTOMER_FAX, customer.getFax());
		values.put(KEY_CUSTOMER_OMSET, customer.getOmset());
		values.put(KEY_CUSTOMER_CARA_PEMBAYARAN, customer.getCara_pembayaran());
		values.put(KEY_CUSTOMER_PLAFON_KREDIT, customer.getPlafon_kredit());
		values.put(KEY_CUSTOMER_TERM_KREDIT, customer.getTerm_kredit());

		values.put(KEY_CUSTOMER_NAMA_ISTRI, customer.getNama_istri());
		values.put(KEY_CUSTOMER_NAMA_ANAK1, customer.getNama_anak1());
		values.put(KEY_CUSTOMER_NAMA_ANAK2, customer.getNama_anak2());
		values.put(KEY_CUSTOMER_NAMA_ANAK3, customer.getNama_anak3());
		values.put(KEY_CUSTOMER_KODE_POS, customer.getKode_pos());
		values.put(KEY_CUSTOMER_ID_DEPO, customer.getId_depo());
		values.put(KEY_CUSTOMER_ISACTIVE, customer.getIsactive());
		values.put(KEY_CUSTOMER_DESCRIPTION, customer.getDescription());
		values.put(KEY_CUSTOMER_NAMA_TOKO, customer.getNama_toko());


		// updating row
		return db.update(TABLE_CUSTOMER, values, KEY_CUSTOMER_ID_CUSTOMER
				+ " = ?", new String[] { String.valueOf(id) });
	}

	//update status update customer
	public int updateCustomer1(int id, Customer customer) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_CUSTOMER_KODE_CUSTOMER, customer.getKode_customer());
		values.put(KEY_CUSTOMER_EMAIL_CUSTOMER, customer.getEmail());
		values.put(KEY_CUSTOMER_ALAMAT_CUSTOMER, customer.getAlamat());
		values.put(KEY_CUSTOMER_LATS_CUSTOMER, customer.getLats());
		values.put(KEY_CUSTOMER_LONGS_CUSTOMER, customer.getLongs());

		values.put(KEY_CUSTOMER_NAMA_LENGKAP_CUSTOMER,
				customer.getNama_lengkap());
		values.put(KEY_CUSTOMER_NO_TELP_CUSTOMER, customer.getNo_telp());
		values.put(KEY_CUSTOMER_ID_WILAYAH_CUSTOMER, customer.getId_wilayah());
		values.put(KEY_CUSTOMER_FOTO_1_CUSTOMER, customer.getFoto_1());
		values.put(KEY_CUSTOMER_FOTO_2_CUSTOMER, customer.getFoto_2());
		values.put(KEY_CUSTOMER_FOTO_3_CUSTOMER, customer.getFoto_3());
		values.put(KEY_CUSTOMER_ID_TYPE_CUSTOMER, customer.getId_type_customer());
		values.put(KEY_CUSTOMER_BLOKIR, customer.getBlokir());
		values.put(KEY_CUSTOMER_DATE, customer.getDate());
		values.put(KEY_CUSTOMER_STATUS_UPDATE, customer.getStatus_update());
		values.put(KEY_CUSTOMER_ID_STAFF, customer.getId_staff());
		values.put(KEY_CUSTOMER_NO_KTP, customer.getNo_ktp());
		values.put(KEY_CUSTOMER_TANGGAL_LAHIR, customer.getTanggal_lahir());
		values.put(KEY_CUSTOMER_NAMA_BANK, customer.getNama_bank());
		values.put(KEY_CUSTOMER_NO_REKENING, customer.getNo_rekening());
		values.put(KEY_CUSTOMER_ATAS_NAMA, customer.getAtas_nama());
		values.put(KEY_CUSTOMER_NPWP, customer.getNpwp());
		values.put(KEY_CUSTOMER_NAMA_PASAR, customer.getNama_pasar());
		values.put(KEY_CUSTOMER_ID_CLUSTER, customer.getId_cluster());
		values.put(KEY_CUSTOMER_TELP, customer.getTelp());
		values.put(KEY_CUSTOMER_FAX, customer.getFax());
		values.put(KEY_CUSTOMER_OMSET, customer.getOmset());
		values.put(KEY_CUSTOMER_CARA_PEMBAYARAN, customer.getCara_pembayaran());
		values.put(KEY_CUSTOMER_PLAFON_KREDIT, customer.getPlafon_kredit());
		values.put(KEY_CUSTOMER_TERM_KREDIT, customer.getTerm_kredit());

		values.put(KEY_CUSTOMER_NAMA_ISTRI, customer.getNama_istri());
		values.put(KEY_CUSTOMER_NAMA_ANAK1, customer.getNama_anak1());
		values.put(KEY_CUSTOMER_NAMA_ANAK2, customer.getNama_anak2());
		values.put(KEY_CUSTOMER_NAMA_ANAK3, customer.getNama_anak3());
		values.put(KEY_CUSTOMER_KODE_POS, customer.getKode_pos());
		values.put(KEY_CUSTOMER_ID_DEPO, customer.getId_depo());


		// updating row
		return db.update(TABLE_CUSTOMER, values, KEY_CUSTOMER_ID_CUSTOMER
				+ " = ?", new String[] { String.valueOf(id) });
	}

	// Update Customer
	public int updateJadwal(int id, Jadwal jadwal) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_JADWAL_ID_JADWAL, jadwal.getId_jadwal());
		values.put(KEY_JADWAL_KODE_JADWAL, jadwal.getKode_jadwal());
		values.put(KEY_JADWAL_KODE_CUSTOMER, jadwal.getKode_customer());
		values.put(KEY_JADWAL_USERNAME, jadwal.getUsername());
		values.put(KEY_JADWAL_ALAMAT, jadwal.getAlamat());
		values.put(KEY_JADWAL_NAMA_LENGKAP, jadwal.getNama_lengkap());
		values.put(KEY_JADWAL_ID_WILAYAH, jadwal.getId_wilayah());
		values.put(KEY_JADWAL_STATUS, jadwal.getStatus());
		values.put(KEY_JADWAL_DATE, jadwal.getDate());
		values.put(KEY_JADWAL_CHECK_IN, jadwal.getCheckin());
		values.put(KEY_JADWAL_CHECK_OUT, jadwal.getCheckout());
		values.put(KEY_JADWAL_STATUS_UPDATE, jadwal.getStatus_update());

		// updating row
		return db.update(TABLE_JADWAL, values, KEY_JADWAL_ID_JADWAL + " = ?",
				new String[] { String.valueOf(id) });
	}

	// Update displayProduct
	public int updateDisplayProduct(int id, DisplayProduct displayProduct) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_DISPLAY_PRODUCT_KODE_CUSTOMER,
				displayProduct.getKode_customer());
		values.put(KEY_DISPLAY_PRODUCT_NAMA_LENGKAP,
				displayProduct.getNama_lengkap());
		values.put(KEY_DISPLAY_PRODUCT_FOTO, displayProduct.getFoto());
		values.put(KEY_DISPLAY_PRODUCT_NAMA_DISPLAY_PRODUCT,
				displayProduct.getNama_display_product());
		values.put(KEY_DISPLAY_PRODUCT_DESKRIPSI, displayProduct.getDeskripsi());
		values.put(KEY_DISPLAY_PRODUCT_DATE_TIME, displayProduct.getDatetime());

		// updating row
		return db.update(TABLE_DISPLAY_PRODUCT, values,
				KEY_DISPLAY_PRODUCT_ID_DISPLAY_PRODUCT + " = ?",
				new String[] { String.valueOf(id) });
	}

	// // Update photoPurchase
	// public int updatePhotoPurchase(int id, PhotoPurchase photoPurchase) {
	// SQLiteDatabase db = this.getWritableDatabase();
	// ContentValues values = new ContentValues();
	// values.put(KEY_PHOTO_PURCHASE_KODE_CUSTOMER,
	// photoPurchase.getKode_customer());
	// values.put(KEY_PHOTO_PURCHASE_NAMA_LENGKAP,
	// photoPurchase.getNama_lengkap());
	// values.put(KEY_PHOTO_PURCHASE_FOTO, photoPurchase.getFoto());
	// values.put(KEY_PHOTO_PURCHASE_NAMA_PHOTO_PURCHASE,
	// photoPurchase.getNama_photo_purchase());
	// values.put(KEY_PHOTO_PURCHASE_DESKRIPSI, photoPurchase.getDeskripsi());
	// values.put(KEY_PHOTO_PURCHASE_DATE_TIME, photoPurchase.getDatetime());
	//
	// // updating row
	// return db.update(TABLE_PHOTO_PURCHASE, values,
	// KEY_PHOTO_PURCHASE_ID_PHOTO_PURCHASE + " = ?",
	// new String[] { String.valueOf(id) });
	// }

	// Getting All Product
	public ArrayList<Product> getAllProduct() {
		try {
			product_list.clear();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_PRODUCT;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Product product = new Product();
					product.setId_product(cursor.getInt(0));
					product.setNama_product(cursor.getString(1));
					product.setKode_product(cursor.getString(2));
					product.setHarga_jual(cursor.getString(3));
					product.setStock(cursor.getString(4));
					product.setId_kemasan(cursor.getString(5));
					product.setFoto(cursor.getString(6));
					product.setDeskripsi(cursor.getString(7));

					// Adding product to list
					product_list.add(product);
				} while (cursor.moveToNext());
			}

			// return product_list
			cursor.close();
			db.close();
			return product_list;
		} catch (Exception e) {
			Log.e("product_list", "" + e);
		}

		return product_list;
	}

	// Getting All Product
	public ArrayList<Product> getAllProductBaseOnSearch(String search) {
		try {
			product_list.clear();


			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_PRODUCT + " WHERE "
					+ KEY_PRODUCT_KODE_PRODUCT + " LIKE '" + search + "%'";

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Product product = new Product();
					product.setId_product(cursor.getInt(0));
					product.setNama_product(cursor.getString(1));
					product.setKode_product(cursor.getString(2));
					product.setHarga_jual(cursor.getString(3));
					product.setStock(cursor.getString(4));
					product.setId_kemasan(cursor.getString(5));
					product.setFoto(cursor.getString(6));
					product.setDeskripsi(cursor.getString(7));

					// Adding product to list
					product_list.add(product);
				} while (cursor.moveToNext());
			}

			// return product_list
			cursor.close();
			db.close();
			return product_list;
		} catch (Exception e) {
			Log.e("product_list", "" + e);
		}

		return product_list;
	}

	// // Getting All Promosi
	// public ArrayList<Promosi> getAllPromosi() {
	// try {
	// promosi_list.clear();
	//
	// // Select All Query
	// String selectQuery = "SELECT  * FROM " + TABLE_PROMOSI;
	//
	// SQLiteDatabase db = this.getWritableDatabase();
	// Cursor cursor = db.rawQuery(selectQuery, null);
	//
	// // looping through all rows and adding to list
	// if (cursor.moveToFirst()) {
	// do {
	// Promosi promosi = new Promosi();
	// promosi.setId_promosi(cursor.getInt(0));
	// promosi.setTitle_promosi(cursor.getString(1));
	// promosi.setJenis_promosi(cursor.getString(2));
	// promosi.setJumlah_diskon(cursor.getInt(3));
	// promosi.setDeskripsi(cursor.getString(4));
	// promosi.setFoto(cursor.getString(5));
	// promosi.setDatetime(cursor.getString(6));
	//
	// // Adding promosi_list to list
	// promosi_list.add(promosi);
	// } while (cursor.moveToNext());
	// }
	//
	// // return promosi_list
	// cursor.close();
	// db.close();
	// return promosi_list;
	// } catch (Exception e) {
	// Log.e("promosi_list", "" + e);
	// }
	//
	// return promosi_list;
	// }
	//
	// // Getting All Promosi
	// public ArrayList<Promosi> getAllPromosiWhereDiskon() {
	// try {
	// promosi_list.clear();
	//
	// // Select All Query
	// String selectQuery = "SELECT  * FROM " + TABLE_PROMOSI + " WHERE "
	// + KEY_PROMOSI_JENIS_PROMOSI + "='Diskon'";
	//
	// SQLiteDatabase db = this.getWritableDatabase();
	// Cursor cursor = db.rawQuery(selectQuery, null);
	//
	// // looping through all rows and adding to list
	// if (cursor.moveToFirst()) {
	// do {
	// Promosi promosi = new Promosi();
	// promosi.setId_promosi(cursor.getInt(0));
	// promosi.setTitle_promosi(cursor.getString(1));
	// promosi.setJenis_promosi(cursor.getString(2));
	// promosi.setJumlah_diskon(cursor.getInt(3));
	// promosi.setDeskripsi(cursor.getString(4));
	// promosi.setFoto(cursor.getString(5));
	// promosi.setDatetime(cursor.getString(6));
	//
	// // Adding promosi_list to list
	// promosi_list.add(promosi);
	// } while (cursor.moveToNext());
	// }
	//
	// // return promosi_list
	// cursor.close();
	// db.close();
	// return promosi_list;
	// } catch (Exception e) {
	// Log.e("promosi_list", "" + e);
	// }
	//
	// return promosi_list;
	// }

	// Getting All DisplayProduct
	public ArrayList<DisplayProduct> getAllDisplayProduct() {
		try {
			display_product_list.clear();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_DISPLAY_PRODUCT;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					DisplayProduct displayProduct = new DisplayProduct();
					displayProduct.setId_display_product(cursor.getInt(0));
					displayProduct.setKode_customer(cursor.getString(1));
					displayProduct.setNama_lengkap(cursor.getString(2));
					displayProduct.setFoto(cursor.getString(3));
					displayProduct.setNama_display_product(cursor.getString(4));
					displayProduct.setDeskripsi(cursor.getString(5));
					displayProduct.setDatetime(cursor.getString(6));

					// Adding display_product_list to list
					display_product_list.add(displayProduct);
				} while (cursor.moveToNext());
			}

			// return display_product_list
			cursor.close();
			db.close();
			return display_product_list;
		} catch (Exception e) {
			Log.e("display_product_list", "" + e);
		}

		return display_product_list;
	}

	// // Getting All PhotoPurchase
	// public ArrayList<PhotoPurchase> getAllPhotoPurchase() {
	// try {
	// photo_purchase_list.clear();
	//
	// // Select All Query
	// String selectQuery = "SELECT  * FROM " + TABLE_PHOTO_PURCHASE;
	//
	// SQLiteDatabase db = this.getWritableDatabase();
	// Cursor cursor = db.rawQuery(selectQuery, null);
	//
	// // looping through all rows and adding to list
	// if (cursor.moveToFirst()) {
	// do {
	// PhotoPurchase photoPurchase = new PhotoPurchase();
	// photoPurchase.setId_photo_purchase(cursor.getInt(0));
	// photoPurchase.setKode_customer(cursor.getString(1));
	// photoPurchase.setNama_lengkap(cursor.getString(2));
	// photoPurchase.setFoto(cursor.getString(3));
	// photoPurchase.setNama_photo_purchase(cursor.getString(4));
	// photoPurchase.setDeskripsi(cursor.getString(5));
	// photoPurchase.setDatetime(cursor.getString(6));
	//
	// // Adding photo_purchase_list to list
	// photo_purchase_list.add(photoPurchase);
	// } while (cursor.moveToNext());
	// }
	//
	// // return photo_purchase_list
	// cursor.close();
	// db.close();
	// return photo_purchase_list;
	// } catch (Exception e) {
	// Log.e("photo_purchase_list", "" + e);
	// }
	//
	// return photo_purchase_list;
	// }

	// Getting All Kemasan
	public ArrayList<Kemasan> getAllKemasan() {
		try {
			kemasan_list.clear();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_KEMASAN;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Kemasan staff = new Kemasan();
					staff.setId_kemasan(cursor.getInt(0));
					staff.setNama_id_kemasan(cursor.getString(1));

					// Adding kemasan to list
					kemasan_list.add(staff);
				} while (cursor.moveToNext());
			}

			// return kemasan_list
			cursor.close();
			db.close();
			return kemasan_list;
		} catch (Exception e) {
			Log.e("kemasan_list", "" + e);
		}

		return kemasan_list;
	}

	// Getting All Branch
	public ArrayList<Branch> getAllBranch() {
		try {
			branch_list.clear();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_BRANCH;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Branch branch = new Branch();
					branch.setId_branch(cursor.getInt(0));
					branch.setKode_branch(cursor.getString(1));
					branch.setDeskripsi(cursor.getString(2));

					// Adding branch to list
					branch_list.add(branch);
				} while (cursor.moveToNext());
			}

			// return branch_list
			cursor.close();
			db.close();
			return branch_list;
		} catch (Exception e) {
			Log.e("branch_list", "" + e);
		}

		return branch_list;
	}

	// Getting All TypeCustomer
	public ArrayList<TypeCustomer> getAllTypeCustomer() {
		try {
			type_customer_list.clear();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_TYPE_CUSTOMER;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					TypeCustomer typeCustomer = new TypeCustomer();
					typeCustomer.setId_type_customer(cursor.getInt(0));
					typeCustomer.setType_customer(cursor.getString(1));
					typeCustomer.setDeskripsi(cursor.getString(2));

					// Adding type_customer_list to list
					type_customer_list.add(typeCustomer);
				} while (cursor.moveToNext());
			}

			// return type_customer_list
			cursor.close();
			db.close();
			return type_customer_list;
		} catch (Exception e) {
			Log.e("type_customer_list", "" + e);
		}

		return type_customer_list;
	}

	// Getting All Cluster
	public ArrayList<Cluster> getAllCluster() {
		try {
			cluster_list.clear();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_CLUSTER;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Cluster cluster = new Cluster();
					cluster.setId_cluster(cursor.getInt(0));
					cluster.setNama_cluster(cursor.getString(1));
					cluster.setDescription(cursor.getString(2));

					// Adding cluster_list to list
					cluster_list.add(cluster);
				} while (cursor.moveToNext());
			}

			// return cluster_list
			cursor.close();
			db.close();
			return cluster_list;
		} catch (Exception e) {
			Log.e("cluster_list", "" + e);
		}

		return cluster_list;
	}

	// Getting All Wilayah
	public ArrayList<Wilayah> getAllWilayah() {
		try {
			wilayah_list.clear();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_WILAYAH;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Wilayah wilayah = new Wilayah();
					wilayah.setId_wilayah(cursor.getInt(0));
					wilayah.setNama_wilayah(cursor.getString(1));
					wilayah.setDeskripsi(cursor.getString(2));

					// Adding wilayah_list to list
					wilayah_list.add(wilayah);
				} while (cursor.moveToNext());
			}

			// return wilayah_list
			cursor.close();
			db.close();
			return wilayah_list;
		} catch (Exception e) {
			Log.e("wilayah_list", "" + e);
		}

		return wilayah_list;
	}



	public int getCountStaff() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor mCount = db
				.rawQuery("select count(*) from " + TABLE_STAFF, null);
		mCount.moveToFirst();
		int count = mCount.getInt(0);
		mCount.close();
		return count;
	}

	public int getCountStaffTemp() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor mCount = db
				.rawQuery("select count(*) from " + TABLE_STAFF_TEMP, null);
		mCount.moveToFirst();
		int count = mCount.getInt(0);
		mCount.close();
		return count;
	}


	public int getCountCustomer() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor mCount = db.rawQuery("select count(*) from " + TABLE_CUSTOMER,
				null);
		mCount.moveToFirst();
		int count = mCount.getInt(0);
		mCount.close();
		return count;
	}

	public int getCountCustomerProspect() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor mCount = db.rawQuery("select count(*) from " + TABLE_CUSTOMER
				+ " WHERE " + KEY_CUSTOMER_BLOKIR + "='Y'", null);
		mCount.moveToFirst();
		int count = mCount.getInt(0);
		mCount.close();
		return count;
	}

	public int getCountCustomerProspect(String date) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor mCount = db.rawQuery("select count(*) from " + TABLE_CUSTOMER
				+ " WHERE " + KEY_CUSTOMER_BLOKIR + "='Y' AND "
				+ KEY_CUSTOMER_DATE + "='" + date + "'", null);
		mCount.moveToFirst();
		int count = mCount.getInt(0);
		mCount.close();
		return count;
	}

	public int getCountCustomerWhereValidAndUpdate() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor mCount = db.rawQuery("select count(*) from " + TABLE_CUSTOMER
				+ " WHERE " + KEY_CUSTOMER_BLOKIR
				+ "='N' AND status_update='2'", null);
		mCount.moveToFirst();
		int count = mCount.getInt(0);
		mCount.close();
		return count;
	}

	public int getCountJadwalNotYetCheckOut() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor mCount = db.rawQuery("select count(*) from " + TABLE_JADWAL
				+ " WHERE " + KEY_JADWAL_STATUS_UPDATE + "='2'", null);
		mCount.moveToFirst();
		int count = mCount.getInt(0);
		mCount.close();
		return count;
	}

	public int getCountJadwal() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor mCount = db.rawQuery("select count(*) from " + TABLE_JADWAL,
				null);
		mCount.moveToFirst();
		int count = mCount.getInt(0);
		mCount.close();
		return count;
	}

	public int getCountJadwalGetTotalCustomerWhereKodeJadwal(String kode_jadwal) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor mCount = db.rawQuery(
				"select count(*) from " + TABLE_JADWAL + " WHERE "
						+ KEY_JADWAL_KODE_JADWAL + "='" + kode_jadwal + "'",
				null);
		mCount.moveToFirst();
		int count = mCount.getInt(0);
		mCount.close();
		return count;
	}

	public int getCountProduct() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor mCount = db.rawQuery("select count(*) from " + TABLE_PRODUCT,
				null);
		mCount.moveToFirst();
		int count = mCount.getInt(0);
		mCount.close();
		return count;
	}

	public int getCountPromosi() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor mCount = db.rawQuery("select count(*) from " + TABLE_PROMOSI,
				null);
		mCount.moveToFirst();
		int count = mCount.getInt(0);
		mCount.close();
		return count;
	}

	public int getCountDisplayProduct() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor mCount = db.rawQuery("select count(*) from "
				+ TABLE_DISPLAY_PRODUCT, null);
		mCount.moveToFirst();
		int count = mCount.getInt(0);
		mCount.close();
		return count;
	}

	public int getCountDisplayProduct(String kode_customer) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor mCount = db.rawQuery("select count(*) from "
				+ TABLE_DISPLAY_PRODUCT + " WHERE "
				+ KEY_DISPLAY_PRODUCT_KODE_CUSTOMER + "='" + kode_customer
				+ "'", null);
		mCount.moveToFirst();
		int count = mCount.getInt(0);
		mCount.close();
		return count;
	}

	public int getCountPhotoPurchase() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor mCount = db.rawQuery("select count(*) from "
				+ TABLE_PHOTO_PURCHASE, null);
		mCount.moveToFirst();
		int count = mCount.getInt(0);
		mCount.close();
		return count;
	}

	// public int getCountPhotoPurchase(String kode_customer) {
	// SQLiteDatabase db = this.getReadableDatabase();
	// Cursor mCount = db.rawQuery(
	// "select count(*) from " + TABLE_PHOTO_PURCHASE + " WHERE "
	// + KEY_PHOTO_PURCHASE_KODE_CUSTOMER + "='"
	// + kode_customer + "'", null);
	// mCount.moveToFirst();
	// int count = mCount.getInt(0);
	// mCount.close();
	// return count;
	// }

	public int getCountSalesOrder() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor mCount = db.rawQuery(
				"select count(*) from " + TABLE_SALES_ORDER, null);
		mCount.moveToFirst();
		int count = mCount.getInt(0);
		mCount.close();
		return count;
	}

	public int getCountSalesOrderWhereNomerOrder(String nomerOrder) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor mCount = db.rawQuery("select count(*) from " + TABLE_SALES_ORDER
				+ " WHERE " + KEY_SALES_ORDER_NOMER_ORDER + "='" + nomerOrder
				+ "'", null);
		mCount.moveToFirst();
		int count = mCount.getInt(0);
		mCount.close();
		return count;
	}

	public int getCountSalesOrder(String kode_customer) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor mCount = db.rawQuery("select count(*) from " + TABLE_SALES_ORDER
				+ " WHERE " + KEY_SALES_ORDER_KODE_CUSTOMER + "='"
				+ kode_customer + "'", null);
		mCount.moveToFirst();
		int count = mCount.getInt(0);
		mCount.close();
		return count;
	}

	public int getCountStockOnHand() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor mCount = db.rawQuery("select count(*) from "
				+ TABLE_STOCK_ON_HAND, null);
		mCount.moveToFirst();
		int count = mCount.getInt(0);
		mCount.close();
		return count;
	}

	public int getCountStockOnHand(String kode_customer) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor mCount = db.rawQuery("select count(*) from "
						+ TABLE_STOCK_ON_HAND + " WHERE "
						+ KEY_STOCK_ON_HAND_KODE_CUSTOMER + "='" + kode_customer + "'",
				null);
		mCount.moveToFirst();
		int count = mCount.getInt(0);
		mCount.close();
		return count;
	}

	public int getCountBranch() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor mCount = db.rawQuery("select count(*) from " + TABLE_BRANCH,
				null);
		mCount.moveToFirst();
		int count = mCount.getInt(0);
		mCount.close();
		return count;
	}

	public int getCountKemasan() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor mCount = db.rawQuery("select count(*) from " + TABLE_KEMASAN,
				null);
		mCount.moveToFirst();
		int count = mCount.getInt(0);
		mCount.close();
		return count;
	}

	public int getCountTypeCustomer() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor mCount = db.rawQuery("select count(*) from "
				+ TABLE_TYPE_CUSTOMER, null);
		mCount.moveToFirst();
		int count = mCount.getInt(0);
		mCount.close();
		return count;
	}

	public int getCountCluster() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor mCount = db.rawQuery("select count(*) from "
				+ TABLE_CLUSTER, null);
		mCount.moveToFirst();
		int count = mCount.getInt(0);
		mCount.close();
		return count;
	}

	public int getCountWilayah() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor mCount = db.rawQuery("select count(*) from " + TABLE_WILAYAH,
				null);
		mCount.moveToFirst();
		int count = mCount.getInt(0);
		mCount.close();
		return count;
	}

	public void deleteTableBranch() {
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("DELETE FROM " + TABLE_BRANCH);
	}

	public void deleteTableKemasan() {
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("DELETE FROM " + TABLE_KEMASAN);
	}

	public void deleteTableStaff() {
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("DELETE FROM " + TABLE_STAFF);
	}

	public void deleteTableStaffTemp() {
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("DELETE FROM " + TABLE_STAFF_TEMP);
	}

	public void deleteTableCustomer() {
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("DELETE FROM " + TABLE_CUSTOMER);
	}

	public void deleteTableStockOnHand() {
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("DELETE FROM " + TABLE_STOCK_ON_HAND);
	}

	public void deleteTableSalesOrder() {
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("DELETE FROM " + TABLE_SALES_ORDER);
	}

	public void deleteTableSalesOrderWhereNomerOrder(String nomerOrder) {
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("DELETE FROM " + TABLE_SALES_ORDER + " WHERE "
				+ KEY_SALES_ORDER_NOMER_ORDER + "='" + nomerOrder + "'");
	}

	public void deleteTableTracking() {
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("DELETE FROM " + TABLE_TRACKING);
	}

	// Deleting single Table Customer
	public void deleteTableCustomer(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CUSTOMER, KEY_CUSTOMER_ID_CUSTOMER + " = ?",
				new String[] { String.valueOf(id) });
		db.close();
	}

	// Deleting single Table Tracking
	public void deleteTableTracking(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_TRACKING, KEY_TRACKING_ID_LOCATOR + " = ?",
				new String[] { String.valueOf(id) });
		db.close();
	}

	// Deleting single Table Stock On Hand
	public void deleteTableStockOnHand(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_STOCK_ON_HAND, KEY_STOCK_ON_HAND_ID_STOCK_ON_HAND
				+ " = ?", new String[] { String.valueOf(id) });
		db.close();
	}

	// Deleting single Table Stock On Hand
	public void deleteTableStockOnHandWhereNomerOrder(String nomerOrder) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DELETE FROM " + TABLE_STOCK_ON_HAND + " WHERE "
				+ KEY_STOCK_ON_HAND_NOMER_STOCK_ON_HAND + "='" + nomerOrder
				+ "'");
		db.close();
	}

	// Deleting single Table Sales Order
	public void deleteTableSalesOrder(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_SALES_ORDER, KEY_SALES_ORDER_ID_SALES_ORDER + " = ?",
				new String[] { String.valueOf(id) });
		db.close();
	}

	public void deleteTableJadwal() {
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("DELETE FROM " + TABLE_JADWAL);
	}

	public void deleteTableCustomerProspect() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CUSTOMER, KEY_CUSTOMER_BLOKIR + " = 'Y'", null);
		db.close();
	}

	public void deleteTableProduct() {
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("DELETE FROM " + TABLE_PRODUCT);
	}

	public void deleteTablePromosi() {
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("DELETE FROM " + TABLE_PROMOSI);
	}

	public void deleteTableDisplayProduct() {
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("DELETE FROM " + TABLE_DISPLAY_PRODUCT);
	}

	// Deleting single Photo Purchase
	public void deleteTableDisplayProduct(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_DISPLAY_PRODUCT, KEY_DISPLAY_PRODUCT_ID_DISPLAY_PRODUCT
				+ " = ?", new String[] { String.valueOf(id) });
		db.close();
	}

	public void deleteTablePhotoPurchase() {
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("DELETE FROM " + TABLE_PHOTO_PURCHASE);
	}

	// // Deleting single Photo Purchase
	// public void deleteTablePhotoPurchase(int id) {
	// SQLiteDatabase db = this.getWritableDatabase();
	// db.delete(TABLE_PHOTO_PURCHASE, KEY_PHOTO_PURCHASE_ID_PHOTO_PURCHASE
	// + " = ?", new String[] { String.valueOf(id) });
	// db.close();
	// }

	public void deleteTableTypeCustomer() {
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("DELETE FROM " + TABLE_TYPE_CUSTOMER);
	}

	public void deleteTableCluster() {
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("DELETE FROM " + TABLE_CLUSTER);
	}

	public void deleteTableWilayah() {
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("DELETE FROM " + TABLE_WILAYAH);
	}

	public void updateStatus() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("update customer set status_update='1' where status_update='2'");
	}

}