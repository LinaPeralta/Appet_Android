package com.example.appet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class Signup extends AppCompatActivity {

    private FirebaseDatabase db;
    private FirebaseAuth auth;

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
        auth = FirebaseAuth.getInstance();

        //Shared preference to put the user id in
        SharedPreferences data = getSharedPreferences("data", MODE_PRIVATE);

        ingresarBtn2.setOnClickListener(
                (v) -> {
                    //Add user to database
                    auth.createUserWithEmailAndPassword(correoTxt.getText().toString(),contraTxt.getText().toString()).addOnSuccessListener(
                            response->{

                                String id = auth.getCurrentUser().getUid();
                                DatabaseReference newUser = db.getReference().child("users").child(id);
                                User usuario = new User(
                                        correoTxt.getText().toString(),
                                        id,
                                        apellidoTxt.getText().toString(),
                                        nombreTxt.getText().toString(),
                                        contraTxt.getText().toString()
                                );
                                newUser.setValue(usuario);

                                //Switch screens and share id
                                Intent signup = new Intent( this, RegisterPet.class);
                                data.edit().putString("userID", id).apply();
                                startActivity(signup);

                            }

                    ).addOnFailureListener(error->{
                        Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();});

                });
    }
}