package com.example.appet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Main extends AppCompatActivity {

    private Button addPetBtn, addProBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Elements
        addPetBtn = findViewById(R.id.addPetBtn);
        addProBtn = findViewById(R.id.addProBtn);

        //Button functions
        addProBtn.setOnClickListener((v)->{
            Intent addProduct = new Intent(this, ProductInfo.class);
            startActivity(addProduct);
        });

    }
}