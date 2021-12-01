package com.example.appet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button ingresarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ingresarBtn = findViewById(R.id.ingresarBtn);

        ingresarBtn.setOnClickListener(
                (v) -> {
                    Intent main = new Intent( this, signup.class);
                    startActivity(main);
                });



    }
}