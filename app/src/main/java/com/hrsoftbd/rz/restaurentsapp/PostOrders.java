package com.hrsoftbd.rz.restaurentsapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;

public class PostOrders extends AppCompatActivity {

    Button one, two, three, four, five, six, seven, eight, nine, zero, save, remove_digit;
    MaterialEditText total_amount, vat_amount, payback_amount, paid_amount;
    TextView serial1, name1, quantity1, price1;
    LocalDatabase db;
    SQLiteDatabase sqdb_write, sqdb_read;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_orders);


        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        zero = findViewById(R.id.zero);
        save = findViewById(R.id.save);
        total_amount = findViewById(R.id.total_amount);
        vat_amount = findViewById(R.id.vat_amount);
        payback_amount = findViewById(R.id.payback_amount);
        paid_amount = findViewById(R.id.paid_amount);
        serial1 = findViewById(R.id.serial1);
        name1 = findViewById(R.id.name1);
        quantity1 = findViewById(R.id.quantity1);
        price1 = findViewById(R.id.price1);


        total_amount.setInputType(InputType.TYPE_NULL);
        vat_amount.setInputType(InputType.TYPE_NULL);
        payback_amount.setInputType(InputType.TYPE_NULL);
        paid_amount.setInputType(InputType.TYPE_NULL);
        remove_digit = findViewById(R.id.remove_digit);


        ////data show table row method


        showData();


        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                set_amount_into_field(1);

            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                set_amount_into_field(2);

            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                set_amount_into_field(3);

            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                set_amount_into_field(4);

            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                set_amount_into_field(5);

            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                set_amount_into_field(6);

            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                set_amount_into_field(7);

            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                set_amount_into_field(8);

            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_amount_into_field(9);
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                set_amount_into_field(0);

            }
        });
        remove_digit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                set_amount_into_field(9999);
//                Toast.makeText(PostOrders.this,"pres okkkk",Toast.LENGTH_LONG).show();

            }
        });


    }

    /////MENU ITEM OVER THE ACTION BAR

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);


    }


    ///////////////////////////


    public void showData() {

        int total_price = 0;

        TextView serial1, name1, quantity1, price1;

        db = new LocalDatabase(this, "Ecommerce.db");
        sqdb_write = db.getWritableDatabase();
        sqdb_read = db.getReadableDatabase();

        TableLayout tableLayout1 = findViewById(R.id.tablelayout2);

        sqdb_read = db.getReadableDatabase();

        String query = "select * from tbl_temporary_order";

        Cursor read_data = sqdb_read.rawQuery(query, null);


        if (read_data.moveToFirst()) {


            do {
                serial1 = new TextView(this);
                name1 = new TextView(this);
                quantity1 = new TextView(this);
                price1 = new TextView(this);

                TableRow tablerow = new TableRow(tableLayout1.getContext());

                final TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                tablerow.setLayoutParams(layoutParams);


                serial1.setText(read_data.getString(0));
                serial1.setTextSize(16);
                tablerow.addView(serial1, 0);

                name1.setText(read_data.getString(1));
                name1.setTextSize(16);
                tablerow.addView(name1, 1);

                quantity1.setText(read_data.getString(3));
                quantity1.setTextSize(16);
                tablerow.addView(quantity1, 2);


                price1.setText(read_data.getString(2));
                price1.setTextSize(16);
                tablerow.addView(price1, 3);


                tableLayout1.addView(tablerow);

                total_price += (Integer.parseInt(read_data.getString(2)) * Integer.parseInt(read_data.getString(3)));

            } while (read_data.moveToNext());
            read_data.close();

            total_amount.setText(String.valueOf(total_price));
        }

    }


    public void total_price(DataModel thisdata) {


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(this, PreOrders.class);
                this.startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item1) {
//
//        switch (item1.getItemId()) {
//            case R.id.two:
//                Intent intent = new Intent(this, PreOrders.class);
//                this.startActivity(intent);
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item1);
//        }
//
//    }


    public void set_amount_into_field(int value) {

        total_amount = findViewById(R.id.total_amount);
        vat_amount = findViewById(R.id.vat_amount);
        payback_amount = findViewById(R.id.payback_amount);
        paid_amount = findViewById(R.id.paid_amount);

        int s = getCurrentFocus().getId();

        if (s == total_amount.getId()) {

            if (value == 9999) {
                total_amount.setText(remove_last_character(total_amount.getText().toString()));
            } else {
                total_amount.setText(total_amount.getText() + String.valueOf(value));
            }

        } else if (s == vat_amount.getId()) {


            if (value == 9999) {
                vat_amount.setText(remove_last_character(vat_amount.getText().toString()));
            } else {
                vat_amount.setText(vat_amount.getText() + String.valueOf(value));
            }

        } else if (s == payback_amount.getId()) {
            if (value == 9999) {
                payback_amount.setText(remove_last_character(payback_amount.getText().toString()));
            } else {
                payback_amount.setText(payback_amount.getText() + String.valueOf(value));
            }

        } else if (s == paid_amount.getId()) {

            if (value == 9999) {
                paid_amount.setText(remove_last_character(paid_amount.getText().toString()));
            } else {
                paid_amount.setText(paid_amount.getText() + String.valueOf(value));
            }

        }

    }

    public String remove_last_character(String str) {
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }


}
