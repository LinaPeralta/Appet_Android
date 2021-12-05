package com.example.appet;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;



public class ProductAdapter extends ArrayAdapter {

    private ArrayList<Product> dataProduct;

    public ProductAdapter(Context context, ArrayList<Product>dataProduct){
      super (context,0,dataProduct);
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        convertView = inflater.inflate(R.layout.row,parent,false);

        Product producto = (Product) getItem(pos);

        Button refreshBtn = convertView.findViewById(R.id.refreshBtn);
        TextView productRow = convertView.findViewById(R.id.productRow);
        TextView unidadesRow = convertView.findViewById(R.id.unidadesRow);
        TextView tipoRow = convertView.findViewById(R.id.tipoRow);
        TextView fechaRow = convertView.findViewById(R.id.fechaRow);
        TextView diasRow = convertView.findViewById(R.id.diasRow);


        productRow.setText(producto.getName());
        unidadesRow.setText(Integer.toString(producto.getAmount()));
        tipoRow.setText(producto.getMeasurement());
        fechaRow.setText(producto.getDate());
        diasRow.setText(Integer.toString(producto.getDays()));

        return convertView;
    }
}
