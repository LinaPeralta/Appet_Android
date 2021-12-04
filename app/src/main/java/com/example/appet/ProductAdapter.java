package com.example.appet;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ProductAdapter extends ArrayAdapter {

    private ArrayList<Product> dataProduct;
    private FirebaseDatabase db;
    private String userID;

    public ProductAdapter(Context context, ArrayList<Product>dataProduct){
      super (context,0,dataProduct);

      db = FirebaseDatabase.getInstance();
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        convertView = inflater.inflate(R.layout.row,parent,false);

        Product producto = (Product) getItem(pos);

        Button refreshBtn = convertView.findViewById(R.id.refreshBtn);
        TextView productRow = convertView.findViewById(R.id.productRow);
        TextView unidadesRow = convertView.findViewById(R.id.unidadesRow);
        TextView fechaRow = convertView.findViewById(R.id.fechaRow);
        TextView diasRow = convertView.findViewById(R.id.diasRow);

        //Set elements with info
        productRow.setText(producto.getName());
        unidadesRow.setText(Integer.toString(producto.getAmount()));
        fechaRow.setText(producto.getDate());
        diasRow.setText(Integer.toString(producto.getDays()));

        //Check that user hasn't refreshed yet in day
        if (checkDates(producto)){
            refreshBtn.setVisibility(View.GONE);
        } else {
            refreshBtn.setVisibility(View.VISIBLE);
        }

        //Button function to calculate days passed
        refreshBtn.setOnClickListener((v)->{
            calculateDays(producto);
            addNotification(producto);
        });

        return convertView;
    }

    private boolean checkDates(Product product){
        //Todays date
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/d/yyyy");
        String dateFormat = formatter.format(date);

        if (dateFormat.equals(product.getDateBtnClick())){
            return true;
        } else {
            return false;
        }
    }

    private void calculateDays(Product product){
        //Todays date
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String todayFormat = formatter.format(today);

        //Product date (registered)
        Date proDate = new Date(product.getDate());

        //Calculate time differences of dates
        long timeDif = today.getTime() - proDate.getTime();
        int daysCal = (int) (timeDif/(1000*3600*24));

        //Calculate amount left
        int amountLeft = product.getAmount() - (product.getUse()*daysCal);

        //Shared preferences to get user ID
        SharedPreferences data = getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        userID = data.getString("userID", "null");

        //Update variables
        DatabaseReference ref = db.getReference();
        DatabaseReference productRef = db.getReference("users/" + userID + "/products/" + product.getId());
        //Check that days is equal to 0, to delete, else, update
        if (product.getDays() <= 0){
            remove(productRef);
        } else {
            int daysPro = product.getDays();
            ref.child("users/" + userID + "/products/" + product.getId() + "/days").setValue(daysPro-daysCal);
            ref.child("users/" + userID + "/products/" + product.getId() + "/amount").setValue(amountLeft);
            ref.child("users/" + userID + "/products/" + product.getId() + "/button").setValue(false);
            ref.child("users/" + userID + "/products/" + product.getId() + "/dateBtnClick").setValue(todayFormat);
        }
    }

    private void addNotification(Product product){
        //Calculate amount left
        int amountLeft = product.getAmount()- product.getUse();
        String message = amountLeft + product.getMeasurement();

        // Builds your notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder (this.getContext())
                    .setSmallIcon (R.drawable.bell)
                    .setContentTitle("Appet Alerta de Productos")
                    .setContentText ("Se actualizó tu producto, te quedan: " + message);

        // Creates the intent needed to show the notification
        Intent notificationIntent = new Intent (this.getContext(), Main.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this.getContext(), 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        //Add as notification
        NotificationManager manager = (NotificationManager) this.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
