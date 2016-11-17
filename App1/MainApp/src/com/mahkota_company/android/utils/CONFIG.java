package com.mahkota_company.android.utils;

import java.io.File;

import android.os.Environment;

public class CONFIG {

	public static final String SHARED_PREFERENCES_SUPPLIER_FOTO_1 = "mahkota_supplier_foto_1";
	public static final String SHARED_PREFERENCES_SUPPLIER_FOTO_2 = "mahkota_supplier_foto_2";
	public static final String SHARED_PREFERENCES_SUPPLIER_FOTO_3 = "mahkota_supplier_foto_3";
	public static final String SHARED_PREFERENCES_SUPPLIER_FOTO_4 = "mahkota_supplier_foto_4";
	public static final String CONFIG_APP_URL_UPLOAD_INSERT_SUPPLIER = "mahkota/ws/insert_supervisor_report.php";

	public static final String CONFIG_APP_URL_UPLOAD_INSERT_SALES = "mahkota/ws/insert_penjualan.php";
	public static final String CONFIG_APP_URL_UPLOAD_INSERT_SALES_DETAIL = "mahkota/ws/insert_penjualan_detail.php";

	////////////////////////////////
	//186.132.0.58
	public static final String CONFIG_APP_URL_DOWNLOAD_STOCK_VAN = "mahkota/ws/get_request_load.php";
	public static final String SHARED_PREFERENCES_TABLE_STOCK_VAN = "mahkota_table_stock_van";
	public static final String SHARED_PREFERENCES_TABLE_STOCK_VAN_SAME_DATA = "mahkota_table_stock_van_same_data";
	public static final String SHARED_PREFERENCES_TABLE_STOCK_VAN_NO_REQUEST_LOAD = "mahkota_table_no_ request_load";

	public static final String CONFIG_APP_URL_DOWNLOAD_TARGET_PENJUALAN = "mahkota/ws/get_product_target.php";
	public static final String CONFIG_APP_URL_UPLOAD_INSERT_RETUR_STOK = "mahkota/ws/insert_retur_load.php";
	public static final String SHARED_PREFERENCES_TABLE_TARGET_PENJUALAN = "mahkota_table_target_penjualan";
	public static final String SHARED_PREFERENCES_TABLE_TARGET_PENJUALAN_SAME_DATA = "mahkota_table_target_penjualan_same_data";
	public static final String SHARED_PREFERENCES_TABLE_TARGET_PENJUALAN_NO_TARGET_PENJUALAN = "mahkota_table_no_target_penjualan";
	public static final String SHARED_PREFERENCES_TABLE_TARGET_PENJUALAN_ID_CUSTOMER = "mahkota_table_no_target_penjualan_id_customer";
	public static final String SHARED_PREFERENCES_TABLE_TARGET_PENJUALAN_KODE_CUSTOMER = "mahkota_table_no_target_penjualan_kode_customer";
	public static final String SHARED_PREFERENCES_TABLE_TARGET_PENJUALAN_NAMA_CUSTOMER = "mahkota_table_no_target_penjualan_nama_customer";

	public static final String CONFIG_APP_FOLDER_PROMOSI = "promosi";
	public static final String CONFIG_APP_FOLDER_PRODUCT = "product";
	public static final String CONFIG_APP_FOLDER_CUSTOMER = "customer";
	public static final String CONFIG_APP_FOLDER_CUSTOMER_PROSPECT = "customer_prospect";
	public static final String CONFIG_APP_FOLDER_PHOTO_PURCHASE = "photo_purchase";
	public static final String CONFIG_APP_FOLDER_DISPLAY_PRODUCT = "photo_display_product";
	// public static final String CONFIG_APP_URL_PUBLIC = "http://mahkota.com/";
	public static final String CONFIG_APP_URL_PUBLIC = "http://186.132.0.58:8081/";
	public static final String CONFIG_APP_APP_FOLDER = "mahkota";
	public static final String SHARED_PREFERENCES_NAME = "mahkota_android";

	public static final String SHARED_PREFERENCES_STAFF_USERNAME = "mahkota_staff_username";
	public static final String SHARED_PREFERENCES_STAFF_NAMA_LENGKAP = "mahkota_staff_nama_lengkap";
	public static final String SHARED_PREFERENCES_STAFF_KODE_BRANCH = "mahkota_staff_kode_branch";
	public static final String SHARED_PREFERENCES_STAFF_ID_WILAYAH = "mahkota_staff_id_wilayah";
	public static final String SHARED_PREFERENCES_STAFF_ID_DEPO = "mahkota_staff_id_depo";
	public static final String SHARED_PREFERENCES_STAFF_ID_STAFF = "mahkota_staff_id_staff";
	public static final String SHARED_PREFERENCES_STAFF_LEVEL = "mahkota_staff_level";

	public static final String CONFIG_APP_URL_UPLOAD_INSERT_REQUEST_LOAD = "mahkota/ws/insert_request_load.php";
	public static final String CONFIG_APP_URL_UPLOAD_UPDATE_REQUEST_LOAD = "mahkota/ws/update_request_load.php";
	public static final String CONFIG_APP_URL_UPLOAD_UPDATE_PRODUCT_TARGET = "mahkota/ws/update_product_target.php";
	public static final String CONFIG_APP_URL_UPLOAD_UPDATE_PENJUALAN = "mahkota/ws/insert_product_terjual.php";
	public static final String CONFIG_APP_URL_UPLOAD_UPDATE_PENJUALAN_DETAIL = "mahkota/ws/insert_product_terjual_detail.php";

	public static final String CONFIG_APP_URL_DOWNLOAD_JADWAL = "mahkota/ws/get_jadwal.php";
	public static final String SHARED_PREFERENCES_TABLE_JADWAL = "mahkota_table_jadwal";
	public static final String SHARED_PREFERENCES_TABLE_JADWAL_SAME_DATA = "mahkota_table_jadwal_same_data";
	public static final String SHARED_PREFERENCES_TABLE_JADWAL_ID_JADWAL = "mahkota_table_jadwal_id_jadwal";
	public static final String SHARED_PREFERENCES_TABLE_JADWAL_KODE_JADWAL = "mahkota_table_jadwal_kode_jadwal";

	// public static final String CONFIG_APP_URL_DIR_IMG_PRODUCT =
	// "http://mahkota.com/mahkota/imgLib/product/";
	public static final String CONFIG_APP_URL_DIR_IMG_PRODUCT = "http://186.132.0.58:8081/mahkota/imgLib/product/";
	public static final String CONFIG_APP_URL_DOWNLOAD_PRODUCT = "mahkota/ws/get_product.php";
	public static final String SHARED_PREFERENCES_TABLE_PRODUCT = "mahkota_table_product";
	public static final String SHARED_PREFERENCES_TABLE_PRODUCT_SAME_DATA = "mahkota_table_product_same_data";
	public static final String SHARED_PREFERENCES_TABLE_PRODUCT_ID_PRODUCT = "mahkota_table_product_id_product";

	// public static final String CONFIG_APP_URL_DIR_IMG_PROMOSI =
	// "http://mahkota.com/mahkota/imgLib/promosi/";
	public static final String CONFIG_APP_URL_DIR_IMG_PROMOSI = "http://186.132.0.58:8081/mahkota/imgLib/promosi/";
	public static final String CONFIG_APP_URL_DOWNLOAD_PROMOSI = "mahkota/ws/get_promosi.php";
	public static final String SHARED_PREFERENCES_TABLE_PROMOSI = "mahkota_table_promosi";
	public static final String SHARED_PREFERENCES_TABLE_PROMOSI_SAME_DATA = "mahkota_table_promosi_same_data";
	public static final String SHARED_PREFERENCES_TABLE_PROMOSI_ID_PROMOSI = "mahkota_table_promosi_id_promosi";

	// public static final String CONFIG_APP_URL_DIR_IMG_CUSTOMER =
	// "http://mahkota.com/mahkota/imgLib/customer/";
	public static final String CONFIG_APP_URL_DIR_IMG_CUSTOMER = "http://186.132.0.58:8081/mahkota/imgLib/customer/";
	public static final String CONFIG_APP_URL_DOWNLOAD_CUSTOMER = "mahkota/ws/get_customer_pst.php";
	public static final String SHARED_PREFERENCES_TABLE_CUSTOMER = "mahkota_table_customer";
	public static final String SHARED_PREFERENCES_TABLE_CUSTOMER_SAME_DATA = "mahkota_table_customer_same_data";
	public static final String SHARED_PREFERENCES_TABLE_CUSTOMER_ID_CUSTOMER = "mahkota_table_customer_id_customer";
	public static final String SHARED_PREFERENCES_TABLE_CUSTOMER_KODE_CUSTOMER = "mahkota_table_customer_kode_customer";

	public static final String CONFIG_APP_URL_DOWNLOAD_STAFF = "mahkota/ws/get_staff.php";
	public static final String SHARED_PREFERENCES_TABLE_STAFF = "mahkota_table_staff";
	public static final String SHARED_PREFERENCES_TABLE_STAFF_SAME_DATA = "mahkota_table_staff_same_data";

	public static final String CONFIG_APP_URL_DOWNLOAD_TRACKING = "mahkota/ws/get_locator_tracking.php";
	public static final String SHARED_PREFERENCES_TABLE_TRACKING = "mahkota_table_tracking";
	public static final String SHARED_PREFERENCES_TABLE_TRACKING_SAME_DATA = "mahkota_table_tracking_same_data";

	public static final String CONFIG_APP_URL_UPLOAD_LOGIN = "mahkota/ws/login.php";
	public static final String CONFIG_APP_URL_UPLOAD_CUSTOMER = "mahkota/ws/update_customer.php";
	public static final String CONFIG_APP_URL_UPLOAD_JADWAL = "mahkota/ws/update_jadwal.php";
	public static final String CONFIG_APP_URL_UPLOAD_CUSTOMER_PROSPECT = "mahkota/ws/update_customer_prospect1.php";
	public static final String CONFIG_APP_URL_UPLOAD_PHOTO_PURCHASE = "mahkota/ws/update_photo_purchase.php";
	public static final String CONFIG_APP_URL_UPLOAD_SALES_ORDER = "mahkota/ws/update_sales_order.php";
	public static final String CONFIG_APP_URL_UPLOAD_REQ_LOAD = "mahkota/ws/update_req_load.php";
	public static final String CONFIG_APP_URL_UPLOAD_RETUR = "mahkota/ws/update_retur.php";
	public static final String CONFIG_APP_URL_UPLOAD_STOCK_ON_HAND = "mahkota/ws/update_stock_on_hand.php";
	public static final String CONFIG_APP_URL_UPLOAD_DISPLAY_PRODUCT = "mahkota/ws/update_display_product.php";
	public static final String CONFIG_APP_URL_UPLOAD_LOCATOR = "mahkota/ws/update_locator_sales.php";
	public static final String CONFIG_APP_KODE_CUSTOMER_HEADER = "CST.";
	public static final String CONFIG_APP_KODE_SO_HEADER = "SO.";
	public static final String CONFIG_APP_KODE_SHO_HEADER = "SOH.";
	public static final String CONFIG_APP_KODE_TP_HEADER = "TP.";
	public static final String CONFIG_APP_KODE_RT_HEADER = "RT.";
	public static final String CONFIG_APP_ERROR_MESSAGE_ADDRESS_FAILED = "Alamat Tidak Terdeteksi";

	public static final String SHARED_PREFERENCES_TABLE_PHOTO_PURCHASE_ID_PHOTO_PURCHASE = "mahkota_table_photo_purchase";
	public static final String SHARED_PREFERENCES_TABLE_DISPLAY_PRODUCT_ID_DISPLAY_PRODUCT = "mahkota_table_display_product";
	public static final String SHARED_PREFERENCES_TABLE_SALES_ORDER_NOMER_ORDER = "mahkota_table_sales_order";
	public static final String SHARED_PREFERENCES_TABLE_REQ_LOAD_NOMER_ORDER = "mahkota_table_req_load";
	public static final String SHARED_PREFERENCES_TABLE_RETUR_NOMER_ORDER = "mahkota_table_retur";
	public static final String SHARED_PREFERENCES_TABLE_STOCK_ON_HAND_NOMER_ORDER_STOCK_ON_HAND = "mahkota_table_stock_on_hand";

	public static final String CONFIG_APP_URL_DOWNLOAD_KEMASAN = "mahkota/ws/get_kemasan.php";
	public static final String SHARED_PREFERENCES_TABLE_KEMASAN = "mahkota_table_kemasan";
	public static final String SHARED_PREFERENCES_TABLE_KEMASAN_SAME_DATA = "mahkota_table_kemasan_same_data";
	public static final String SHARED_PREFERENCES_TABLE_KEMASAN_ID_KEMASAN = "mahkota_table_kemasan_id_kemasan";

	public static final String CONFIG_APP_URL_DOWNLOAD_BRANCH = "mahkota/ws/get_branch.php";
	public static final String SHARED_PREFERENCES_TABLE_BRANCH = "mahkota_table_branch";
	public static final String SHARED_PREFERENCES_TABLE_BRANCH_SAME_DATA = "mahkota_table_branch_same_data";
	public static final String SHARED_PREFERENCES_TABLE_BRANCH_ID_BRANCH = "mahkota_table_branch_id_branch";

	public static final String CONFIG_APP_URL_DOWNLOAD_TYPE_CUSTOMER = "mahkota/ws/get_type_customer.php";
	public static final String SHARED_PREFERENCES_TABLE_TYPE_CUSTOMER = "mahkota_table_type_customer";
	public static final String SHARED_PREFERENCES_TABLE_TYPE_CUSTOMER_SAME_DATA = "mahkota_table_type_customer_same_data";
	public static final String SHARED_PREFERENCES_TABLE_TYPE_CUSTOMER_ID_TYPE_CUSTOMER = "mahkota_table_type_customer_id_type_customer";

	public static final String CONFIG_APP_URL_DOWNLOAD_CLUSTER = "mahkota/ws/get_cluster.php";
	public static final String SHARED_PREFERENCES_TABLE_CLUSTER = "mahkota_table_cluster";
	public static final String SHARED_PREFERENCES_TABLE_CLUSTER_SAME_DATA = "mahkota_table_cluster_same_data";
	public static final String SHARED_PREFERENCES_TABLE_TYPE_CUSTOMER_ID_CLUSTER = "mahkota_table_cluster_id_cluster";

	public static final String CONFIG_APP_URL_DOWNLOAD_WILAYAH = "mahkota/ws/get_wilayah.php";
	public static final String SHARED_PREFERENCES_TABLE_WILAYAH = "mahkota_table_wilayah";
	public static final String SHARED_PREFERENCES_TABLE_WILAYAH_SAME_DATA = "mahkota_table_wilayah_same_data";
	public static final String SHARED_PREFERENCES_TABLE_WILAYAH_ID_WILAYAH = "mahkota_table_wilayah_id_wilayah";

	public static final String APPLICATION_PACKAGE_NAME = "com.mahkota_company.android";

	public static String getFolderPath() {
		StringBuilder path_builder = new StringBuilder();
		path_builder.append(File.separatorChar);
		path_builder.append("Android");
		path_builder.append(File.separatorChar);
		path_builder.append("data");
		path_builder.append(File.separatorChar);
		path_builder.append(APPLICATION_PACKAGE_NAME);
		String temp_middle_path = path_builder.toString();

		return Environment.getExternalStorageDirectory().getAbsolutePath()
				+ temp_middle_path;

	}

}