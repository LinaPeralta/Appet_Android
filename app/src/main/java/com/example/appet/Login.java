package com.example.appet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText emailTxt, passwordTxt;
    private TextView registerTxt;
    private Button ingresarBtn;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Authentication
        auth = FirebaseAuth.getInstance();

        //Elements
        emailTxt = findViewById(R.id.emailTxt);
        passwordTxt = findViewById(R.id.passwordTxt);
        ingresarBtn = findViewById(R.id.ingresarBtn);
        registerTxt = findViewById(R.id.registrarseTxt);

        //Button to sign in
        ingresarBtn.setOnClickListener((v)->{
            auth.signInWithEmailAndPassword(emailTxt.getText().toString(), passwordTxt.getText().toString())
                    .addOnCompleteListener((authTask) -> {
                        if (authTask.isSuccessful()){
                            Intent main = new Intent( this, RegisterPet.class);
                            startActivity(main);
                            finish();
                        } else {
                            Toast.makeText(this, authTask.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });


        registerTxt.setOnClickListener((v)->{
            Intent signup = new Intent( this, Signup.class);
            startActivity(signup);
            finish();
        });

    }
}