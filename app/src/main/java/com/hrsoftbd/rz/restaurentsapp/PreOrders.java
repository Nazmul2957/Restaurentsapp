package com.hrsoftbd.rz.restaurentsapp;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.hrsoftbd.rz.restaurentsapp.retrofit.ApiClient;
import com.hrsoftbd.rz.restaurentsapp.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreOrders extends AppCompatActivity {


    GridView gridView;
    CardView cardview;
    AutoCompleteTextView autocomplete;
    ImageView search;
    TextView serial, name, quantity, price;
    EditText total_amount, vat_amount, payback_amount, paid_amount;
    //Button return_bill, bill, sell, invoice, stock;
    Button process;
    ProgressBar myProgressBar;
    //    List<DataModel> fooddata;
    GridviewCustomAdapter adapter;
    TableRow tableRow;
    DataModel thisData;
    TableLayout table1;
    LocalDatabase db;
    SQLiteDatabase sqdb_write, sqdb_read;
    String last_update_date = "0000-00-00";


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_orders);

        db = new LocalDatabase(this, "Ecommerce.db");
        sqdb_write = db.getWritableDatabase();
        sqdb_read = db.getReadableDatabase();

        get_all_latest_data();

        ////////Find ID

        cardview = findViewById(R.id.cardview);
        gridView = findViewById(R.id.gridview);
        autocomplete = findViewById(R.id.autocomplete);
        search = findViewById(R.id.search);
        serial = findViewById(R.id.serial);
        name = findViewById(R.id.name12);
        quantity = findViewById(R.id.quantity);
        price = findViewById(R.id.price);
        total_amount = findViewById(R.id.total_amount);
        vat_amount = findViewById(R.id.vat_amount);
        payback_amount = findViewById(R.id.payback_amount);
        paid_amount = findViewById(R.id.paid_amount);
        process = findViewById(R.id.process);
        myProgressBar = findViewById(R.id.myProgressBar);
        tableRow = findViewById(R.id.table2);
        table1 = findViewById(R.id.table1);

        show_table_design();

        process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PreOrders.this, PostOrders.class);
                startActivity(intent);
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query_value = autocomplete.getText().toString().trim();
                String search_query = "SELECT * FROM tbl_word_data WHERE Name LIKE '%" + query_value + "%' OR  Price LIKE '%" + query_value + "%'";

                Cursor search_data_find = sqdb_read.rawQuery(search_query, null);

                //Toast.makeText(PreOrders.this, query_value.toString() + " - " +String.valueOf(search_data_find.getCount()) + " - " + search_query, Toast.LENGTH_LONG).show();

            }
        });

        String query = "select * from tbl_product";

        final Cursor read_data = sqdb_read.rawQuery(query, null);

        /////AUTOFILL SEARCH BAR

        Cursor autofil_table_data = read_data;
        ArrayList<String> autofil_table_data_array = new ArrayList<String>();

        if (autofil_table_data.moveToFirst()) {
            do {
                autofil_table_data_array.add(read_data.getString(1));
                autofil_table_data_array.add(read_data.getString(2));

            } while (autofil_table_data.moveToNext());
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, autofil_table_data_array);

        autocomplete.setAdapter(adapter);
        autocomplete.setThreshold(2);


//        arrangetable(read_data);


        /*Create handle for the RetrofitInstance interface*/
        ApiInterface myAPIService = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<DataModel>> call = (Call<List<DataModel>>) myAPIService.getdata();
        call.enqueue(new Callback<List<DataModel>>() {

            @Override
            public void onResponse(retrofit2.Call<List<DataModel>> call, Response<List<DataModel>> response) {
                myProgressBar.setVisibility(View.GONE);
                populateGridView(response.body());
            }

            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable throwable) {
                myProgressBar.setVisibility(View.GONE);
                Toast.makeText(PreOrders.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                get_all_latest_data();
                thisData = (DataModel) parent.getItemAtPosition(position);

                set_temporary_orders(thisData);

                Log.v("griditem", "clicked");
                show_table_design();


            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void show_table_design() {

        TableLayout tableLayout = findViewById(R.id.table1);

        String query = "select * from tbl_temporary_order";

        final Cursor read_data = sqdb_read.rawQuery(query, null);

        int total_price = 0;


        while (tableLayout.getChildCount() > 1) {
            tableLayout.removeViewAt(tableLayout.getChildCount() - 1);
        }

        if (read_data.moveToFirst()) {
            do {

                TableRow tablerow = new TableRow(tableLayout.getContext());
                final TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                tablerow.setLayoutParams(layoutParams);

                //  total_price += Integer.valueOf(read_data.getColumnIndex(String.valueOf(1)));

                TextView textView0 = new TextView(tablerow.getContext());
                textView0.setText(read_data.getString(0));
                textView0.setTextSize(16);
                tablerow.addView(textView0, 0);

                TextView textView1 = new TextView(tablerow.getContext());
                textView1.setText(read_data.getString(1));
                textView1.setTextSize(16);
                tablerow.addView(textView1, 1);

                TextView textView2 = new TextView(tablerow.getContext());
                textView2.setText(read_data.getString(3));
                textView2.setTextSize(16);
                tablerow.addView(textView2, 2);

                TextView textView3 = new TextView(tablerow.getContext());
                textView3.setText(read_data.getString(2));
                textView3.setTextSize(16);
                tablerow.addView(textView3, 3);

                ImageButton imageButton = new ImageButton(tablerow.getContext());
                imageButton.setClickable(true);
                imageButton.setImageResource(R.drawable.delete);
                imageButton.setBackground(null);
                tablerow.addView(imageButton, 4);

                final String myid = read_data.getString(0);
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        delete_temporary_orders(Integer.parseInt(myid));
                        Toast.makeText(PreOrders.this, myid, Toast.LENGTH_SHORT).show();


                    }
                });

                tableLayout.addView(tablerow);

                total_price += (Integer.parseInt(read_data.getString(2)) * Integer.parseInt(read_data.getString(3)));


                process.setText("Process" + "(" + total_price + " tk.)");


            } while (read_data.moveToNext());
        }


    }

    @TargetApi(Build.VERSION_CODES.M)
    private void delete_temporary_orders(int id) {

        final ContentValues values = new ContentValues();
        //Find existing and update database
        String delete_temporary_order_id = "DELETE FROM tbl_temporary_order WHERE id=" + id;
        int a = sqdb_write.delete("tbl_temporary_order", "id=?", new String[]{Integer.toString(id)}) ;

        show_table_design();

    }

    private void set_temporary_orders(DataModel thisData) {

        final ContentValues values = new ContentValues();
        final ContentValues update_values = new ContentValues();


        //Find existing and update database
        String find_existing_data = "SELECT * FROM tbl_temporary_order WHERE id=" + thisData.getId();
        Cursor find_existing_data_check = sqdb_read.rawQuery(find_existing_data, null);
        int find_existing_data_count = find_existing_data_check.getCount();
        if (find_existing_data_count < 1) {

            values.put("id", thisData.getId());
            values.put("Name", thisData.getName().toString());
            values.put("Price", thisData.getPrice().toString());
            values.put("Quantity", 1);

            long status = sqdb_write.insert("tbl_temporary_order", null, values);


        } else {
            find_existing_data_check.moveToFirst();
            int previous_qn = Integer.valueOf(find_existing_data_check.getString(3));

            update_values.put("Quantity", previous_qn + 1);

            long update_status = sqdb_write.update("tbl_temporary_order", update_values, "id = ?", new String[]{thisData.getId()});
        }

    }


    /////MENU ITEM OVER THE ACTION BAR

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    /////SEARCH BOX READ DATA FROM DATABASE

    public String[] getItemsFromDb(String searchTerm) {

        // add items on the array dynamically

        List<Myobject> products = this.read(searchTerm);
        int rowCount = products.size();

        String[] item = new String[rowCount];
        int x = 0;

        for (Myobject record : products) {

            item[x] = record.objectName;
            x++;
        }

        return item;
    }

    public List<Myobject> read(String searchTerm) {

        List<Myobject> recordlist = new ArrayList<Myobject>();


        String query = "select * from tbl_product";

        Cursor read_data = sqdb_read.rawQuery(query, null);


        if (read_data.moveToFirst()) {
            do {

                String objectName = read_data.getString(read_data.getColumnIndex(String.valueOf(this)));
                Myobject myObject = new Myobject(objectName);

                recordlist.add(myObject);

            } while (read_data.moveToNext());
        }

        read_data.close();
        db.close();
        return recordlist;

    }


    private void populateGridView(List<DataModel> fooddata) {
        adapter = new GridviewCustomAdapter(this, fooddata);
        gridView.setAdapter(adapter);
    }

    private void get_all_latest_data() {

        //DATABASE


        last_update_date = "0000-00-00";

        // GET Last inserted date
        String search_query = "SELECT * FROM tbl_product order by last_update DESC LIMIT 1";
        Cursor last_update_note = sqdb_read.rawQuery(search_query, null);

        if (last_update_note.getCount() < 1) {

            //Toast.makeText(this, "No Data into Database", Toast.LENGTH_SHORT).show();
        } else {
            if (last_update_note.moveToFirst()) {
                last_update_date = last_update_note.getString(3);
                //Toast.makeText(this, last_update_date.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        // Start Server Call
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<DataModel>> call = apiInterface.getdata();

        call.enqueue(new Callback<List<DataModel>>() {
            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {
                List<DataModel> get_all_latest_data = response.body();

                if (get_all_latest_data != null) {
                    final ContentValues values = new ContentValues();
                    final ContentValues update_values = new ContentValues();

                    int get_all_latest_data_size = get_all_latest_data.size();

                    for (int i = 0; i < get_all_latest_data_size; i++) {
                        if (last_update_date.equals("0000-00-00")) {
                            values.put("id", get_all_latest_data.get(i).getId());
                            values.put("Name", get_all_latest_data.get(i).getName().toString());
                            values.put("Description", get_all_latest_data.get(i).getDescription().toString());
                            values.put("Price", get_all_latest_data.get(i).getPrice().toString());
                            values.put("Last_Update", get_all_latest_data.get(i).getLastUpdate().toString());
                            long status = sqdb_write.insert("tbl_product", null, values);
                        } else {

                            //Find existing and update database
                            String find_existing_data = "SELECT * FROM tbl_product WHERE id=" + get_all_latest_data.get(i).getId();
                            Cursor find_existing_data_check = sqdb_read.rawQuery(find_existing_data, null);
                            int find_existing_data_count = find_existing_data_check.getCount();
                            if (find_existing_data_count < 1) {

                                values.put("id", get_all_latest_data.get(i).getId());
                                values.put("Name", get_all_latest_data.get(i).getName().toString());
                                values.put("Description", get_all_latest_data.get(i).getDescription().toString());
                                values.put("Price", get_all_latest_data.get(i).getPrice().toString());
                                values.put("last_update", get_all_latest_data.get(i).getLastUpdate().toString());
                                long status = sqdb_write.insert("tbl_product", null, values);

                            } else {
                                update_values.put("id", get_all_latest_data.get(i).getId());
                                update_values.put("Name", get_all_latest_data.get(i).getName().toString());
                                update_values.put("Description", get_all_latest_data.get(i).getDescription().toString());
                                update_values.put("Price", get_all_latest_data.get(i).getPrice().toString());
                                update_values.put("last_update", get_all_latest_data.get(i).getLastUpdate().toString());
                                long update_status = sqdb_write.update("tbl_product", update_values, "id = ?", new String[]{get_all_latest_data.get(i).getId().toString()});
                            }
                        }
                    }

                } else {
                    Toast.makeText(PreOrders.this, "dit not hit", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {

                Toast.makeText(PreOrders.this, t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}



