package com.mahkota_company.android.Contact;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.http.ParseException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.mahkota_company.android.NavigationDrawerCallbacks;
import com.mahkota_company.android.NavigationDrawerFragment;
import com.mahkota_company.android.customer.CustomerActivity;
import com.mahkota_company.android.database.DatabaseHandler;
import com.mahkota_company.android.database.DisplayProduct;
import com.mahkota_company.android.display_product.DisplayProductActivity;
import com.mahkota_company.android.inventory.InventoryActivity;
import com.mahkota_company.android.jadwal.JadwalActivity;
import com.mahkota_company.android.locator.LocatorActivity;
import com.mahkota_company.android.product.ProductActivity;
import com.mahkota_company.android.prospect.CustomerProspectActivity;
import com.mahkota_company.android.sales_order.SalesOrderActivity;
import com.mahkota_company.android.stock_on_hand.StockOnHandActivity;
import com.mahkota_company.android.R;

@SuppressWarnings("deprecation")
public class PilihanContactUnused extends ActionBarActivity implements
        NavigationDrawerCallbacks {
    private Context act;
    private Toolbar mToolbar;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private DatabaseHandler databaseHandler;
    private ListView listview;
    private ArrayList<DisplayProduct> display_product_list = new ArrayList<DisplayProduct>();
    private ListViewAdapter cAdapter;
    private ProgressDialog progressDialog;
    private Handler handler = new Handler();
    private String response_data;
    private static final String LOG_TAG = PilihanContactUnused.class
            .getSimpleName();
    private Button addDisplayProduct;
    private Typeface typefaceSmall;
    private Button mButtonKantor;
    private Button mButtonKaryawan;
    private Button mButtonCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pilihan_kontak);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        typefaceSmall = Typeface.createFromAsset(getAssets(),
                "fonts/AliquamREG.ttf");

        mButtonKantor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent KontakKantor=new Intent(getApplicationContext(),ListContactCustomer.class);
                startActivity(KontakKantor);
            }
        });

        mButtonKaryawan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent KontakKantor=new Intent(getApplicationContext(),ListContactCustomer.class);
                startActivity(KontakKantor);
            }
        });

        mButtonCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent KontakKantor=new Intent(getApplicationContext(),ListContactCustomer.class);
                startActivity(KontakKantor);
            }
        });

        act = this;
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
                .findFragmentById(R.id.fragment_drawer);
        mNavigationDrawerFragment.setup(R.id.fragment_drawer,
                (DrawerLayout) findViewById(R.id.drawer), mToolbar);
        //mNavigationDrawerFragment.selectItem(8);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.menu_refresh);
        if (item != null) {
            item.setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public void CustomDialogUploadSuccess(String msg) {
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
                                databaseHandler.deleteTableDisplayProduct();
                                finish();
                                startActivity(getIntent());
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    public class ListViewAdapter extends ArrayAdapter<DisplayProduct> {
        Activity activity;
        int layoutResourceId;
        DisplayProduct displayProductData;
        ArrayList<DisplayProduct> data = new ArrayList<DisplayProduct>();

        public ListViewAdapter(Activity act, int layoutResourceId,
                               ArrayList<DisplayProduct> data) {
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
                holder.list_kodeCustomer = (TextView) row
                        .findViewById(R.id.display_product_title_kode_customer);
                holder.list_namaCustomer = (TextView) row
                        .findViewById(R.id.display_product_title_nama_customer);
                holder.list_waktu = (TextView) row
                        .findViewById(R.id.display_product_title_waktu);

                row.setTag(holder);
            } else {
                holder = (UserHolder) row.getTag();
            }
            displayProductData = data.get(position);
            holder.list_kodeCustomer.setText(displayProductData
                    .getKode_customer());
            holder.list_namaCustomer.setText(displayProductData
                    .getNama_lengkap());

            SimpleDateFormat fromUser = new SimpleDateFormat(
                    "yyyy-MM-dd h:m:ss");
            SimpleDateFormat myFormat = new SimpleDateFormat(
                    "dd-MM-yyyy h:m:ss");

            try {
                String inputString = displayProductData.getDatetime();
                String reformattedStr = myFormat.format(fromUser
                        .parse(inputString));
                holder.list_waktu.setText(reformattedStr);
            } catch (ParseException | java.text.ParseException e) {
                e.printStackTrace();
            }
            holder.list_kodeCustomer.setTypeface(typefaceSmall);
            holder.list_namaCustomer.setTypeface(typefaceSmall);
            holder.list_waktu.setTypeface(typefaceSmall);
            row.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String id_display_product = String.valueOf(data.get(
                            position).getId_display_product());
                    //showEditDeleteDialog(id_display_product);
                }
            });
            return row;

        }

        class UserHolder {
            TextView list_kodeCustomer;
            TextView list_namaCustomer;
            TextView list_waktu;
        }

    }
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        if (mNavigationDrawerFragment != null) {
            if (mNavigationDrawerFragment.getCurrentSelectedPosition() != 8) {
                if (position == 0) {
                    Intent intentActivity = new Intent(this,
                            CustomerActivity.class);
                    startActivity(intentActivity);
                    finish();
                } else if (position == 1) {
                    Intent intentActivity = new Intent(this,
                            JadwalActivity.class);
                    startActivity(intentActivity);
                    finish();
                } else if (position == 2) {
                    Intent intentActivity = new Intent(this,
                            ProductActivity.class);
                    startActivity(intentActivity);
                    finish();
                } else if (position == 3) {
                    Intent intentActivity = new Intent(this,
                            CustomerProspectActivity.class);
                    startActivity(intentActivity);
                    finish();
                } else if (position == 4) {
                    Intent intentActivity = new Intent(this,
                            LocatorActivity.class);
                    startActivity(intentActivity);
                    finish();
                } else if (position == 5) {
                    Intent intentActivity = new Intent(this,
                            SalesOrderActivity.class);
                    startActivity(intentActivity);
                    finish();
                } else if (position == 6) {
                    Intent intentActivity = new Intent(this,
                            StockOnHandActivity.class);
                    startActivity(intentActivity);
                    finish();
                }else if (position == 7) {
                    Intent intentActivity = new Intent(this,
                            DisplayProductActivity.class);
                    startActivity(intentActivity);
                    finish();
                }
				else if (position == 9) {
					Intent intentActivity = new Intent(this,
							InventoryActivity.class);
					startActivity(intentActivity);
					finish();
				}
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }

    public void showCustomDialogExit() {
        String msg = getApplicationContext().getResources().getString(
                R.string.MSG_DLG_LABEL_EXIT_DIALOG);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                act);
        alertDialogBuilder
                .setMessage(msg)
                .setCancelable(false)
                .setNegativeButton(
                        getApplicationContext().getResources().getString(
                                R.string.MSG_DLG_LABEL_YES),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                android.os.Process
                                        .killProcess(android.os.Process.myPid());

                            }
                        })
                .setPositiveButton(
                        getApplicationContext().getResources().getString(
                                R.string.MSG_DLG_LABEL_NO),
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            showCustomDialogExit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
