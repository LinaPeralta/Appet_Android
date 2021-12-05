package com.example.appet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class PetsAdapter extends ArrayAdapter {

    private ArrayList<Product> dataPets;

    public PetsAdapter(Context context, ArrayList<Pets>dataPets){
        super (context,0,dataPets);
    }


    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        convertView = inflater.inflate(R.layout.rowpets,parent,false);

       Pets pet = (Pets) getItem(pos);

        Button editarBtn = convertView.findViewById(R.id.editarBtn);
        TextView nombreRow = convertView.findViewById(R.id.nombreRow);

        nombreRow.setText(pet.getName());

        return convertView;
    }
}
