package com.example.appet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class registro extends AppCompatActivity {

    private EditText emailTxt, passwordTxt;
    private Button loginBtn;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //Authentication
        auth = FirebaseAuth.getInstance();

        //Elements
        emailTxt = findViewById(R.id.emailTxt);
        passwordTxt = findViewById(R.id.passwordTxt);
        loginBtn = findViewById(R.id.ingresarBtn);

        //Button to sign in
        loginBtn.setOnClickListener((v)->{
            auth.signInWithEmailAndPassword(emailTxt.getText().toString(), passwordTxt.getText().toString())
                    .addOnCompleteListener((authTask) -> {
                        if (authTask.isSuccessful()){
                            Intent main = new Intent(this, detallesProducto.class);
                            startActivity(main);
                            finish();
                        } else {
                            Toast.makeText(this, authTask.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });

    }
}