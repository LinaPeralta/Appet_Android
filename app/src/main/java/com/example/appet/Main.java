package com.example.appet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Main extends AppCompatActivity {

    private Button addPetBtn, addProBtn, logOutBtn;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Elements
        addPetBtn = findViewById(R.id.addPetBtn);
        addProBtn = findViewById(R.id.addProBtn);
        logOutBtn = findViewById(R.id.logOutBtn);

        //Firebase
        auth = FirebaseAuth.getInstance();

        loadUserInfo();

        //Button functions
        addProBtn.setOnClickListener((v)->{
            Intent addProduct = new Intent(this, ProductInfo.class);
            startActivity(addProduct);
        });

        addPetBtn.setOnClickListener((v)->{
            Intent addPet = new Intent(this, RegisterPet.class);
            startActivity(addPet);
        });

        logOutBtn.setOnClickListener((v)->{
            auth.signOut();
            goToLogin();
            finish();
        });
    }

    private void loadUserInfo(){
        if (auth.getCurrentUser() != null){

        } else {
            goToLogin();
        }
    }

    private void goToLogin(){
        Intent login = new Intent(this, Login.class);
        startActivity(login);
        finish();
    }
}