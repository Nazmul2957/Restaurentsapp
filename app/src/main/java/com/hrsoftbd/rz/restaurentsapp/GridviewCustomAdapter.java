package com.hrsoftbd.rz.restaurentsapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;


public class GridviewCustomAdapter extends BaseAdapter {


    private List<DataModel> fooddata;
    private Context context;
    public TextView name;


    public GridviewCustomAdapter(Context context, List<DataModel> fooddata) {
        this.context = context;
        this.fooddata = fooddata;


    }

    @Override
    public int getCount() {
        return fooddata.size();
    }

    @Override
    public Object getItem(int position) {
        return fooddata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.gridview_model, parent, false);
        }

        TextView nameTxt = convertView.findViewById(R.id.food_title);
        TextView txtPrice = convertView.findViewById(R.id.food_price);
        ImageView ImageView = convertView.findViewById(R.id.imageview);
        // final TextView textView1 = convertView.findViewById(R.id.name12);///table id

        final DataModel thisData = fooddata.get(position);

        nameTxt.setText(thisData.getName());
        txtPrice.setText(thisData.getPrice());
//        textView.setText(thisData.getName());///table id


        if (thisData.getPhoto() != null && thisData.getPhoto().length() > 0) {

            Picasso.get().load(Config.base_url + thisData.getPhoto()).placeholder(R.drawable.placeholder).into(ImageView);

        } else {
            Toast.makeText(context, "Empty Image URL", Toast.LENGTH_SHORT).show();
            Picasso.get().load(R.drawable.placeholder).into(ImageView);
        }


        return convertView;
    }

}
