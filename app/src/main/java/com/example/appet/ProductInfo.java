package com.example.appet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductInfo extends AppCompatActivity {

    private EditText productName, amountTxt, useTxt;
    private Button registerBtn;
    private CheckBox unitsCheck, gramsCheck;
    private FirebaseDatabase db;
    private String userID, measurement, finalDate;
    private int amount, use;
    private Date date;
    private SimpleDateFormat formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        //Elements
        productName = findViewById(R.id.productName);
        amountTxt = findViewById(R.id.amountTxt);
        useTxt = findViewById(R.id.useTxt);
        registerBtn = findViewById(R.id.registerBtn);
        unitsCheck = (CheckBox) findViewById(R.id.unitsCheck);
        gramsCheck = (CheckBox) findViewById(R.id.gramsCheck);

        //Firebase
        db = FirebaseDatabase.getInstance();

        checkBoxes();

        //Button function
        registerBtn.setOnClickListener((v)->{
            if (productName.getText().toString().isEmpty() || amountTxt.getText().toString().isEmpty()
                    || useTxt.getText().toString().isEmpty() || !unitsCheck.isChecked() && !gramsCheck.isChecked()){
                Toast.makeText(this, "Llena los campos", Toast.LENGTH_SHORT).show();
            } else {
                //Turn strings into int
                amount = Integer.parseInt(amountTxt.getText().toString());
                use = Integer.parseInt(useTxt.getText().toString());

                //Calculate days
                int daysCalculated = amount/use;

                getDate();

                //Get reference (create task in user file)
                //Database reference
                DatabaseReference productRef = db.getReference("users/" + userID + "/products");
                DatabaseReference newProRef = productRef.push();

                //Create product and add to database
                Product product = new Product(amount, use, daysCalculated, productName.getText().toString(),
                        measurement, newProRef.getKey(), finalDate, " ", true);

                newProRef.setValue(product);

                goToMain();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Get id from shared preferences
        SharedPreferences data = getSharedPreferences("data", MODE_PRIVATE);
        userID = data.getString("userID", "null");
    }

    private void checkBoxes(){
        //Depending on which box is checked, set measurement
        if (gramsCheck.isChecked()){
            unitsCheck.setVisibility(View.GONE);
            measurement = "gramos";
        }

        if (unitsCheck.isChecked()){
            gramsCheck.setVisibility(View.GONE);
            measurement = "unidades";
        }
    }

    private void getDate(){
        //Get date information for when added
        date = new Date();
        formatter = new SimpleDateFormat("MM/dd/yyyy");
        finalDate = formatter.format(date);
    }

    private void goToMain(){
        Intent main = new Intent(this, Main.class);
        startActivity(main);
        finish();
    }

}