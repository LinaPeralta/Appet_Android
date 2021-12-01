package com.example.appet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class signup extends AppCompatActivity {

    private Button ingresarBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ingresarBtn2 = findViewById(R.id.ingresarBtn2);

        ingresarBtn2.setOnClickListener(
                (v) -> {
                    Intent signup = new Intent( this, registro.class);
                    startActivity(signup);
                });

    }
}