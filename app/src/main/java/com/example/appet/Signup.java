package com.example.appet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class Signup extends AppCompatActivity {

    private FirebaseDatabase db;

    private EditText nombreTxt,apellidoTxt,usernameTxt,contraTxt,correoTxt;
    private Button ingresarBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ingresarBtn2 = findViewById(R.id.ingresarBtn2);
        nombreTxt = findViewById(R.id.nombreTxt);
        apellidoTxt = findViewById(R.id.apellidoTxt);
        contraTxt = findViewById(R.id.contraTxt);
        correoTxt= findViewById(R.id.correoTxt);


        db = FirebaseDatabase.getInstance();

        ingresarBtn2.setOnClickListener(
                (v) -> {
                    registrarUsuario();
                    Intent signup = new Intent( this, RegisterPet.class);
                    startActivity(signup);
                });


    }

        public void registrarUsuario(){

            String id = UUID.randomUUID().toString();
            DatabaseReference newUser = db.getReference().child("users").child(id).push();

            User usuario = new User(

                    id,
                    correoTxt.getText().toString(),
                    apellidoTxt.getText().toString(),
                    nombreTxt.getText().toString(),
                    contraTxt.getText().toString()
            );

            newUser.setValue(usuario);

     }
}