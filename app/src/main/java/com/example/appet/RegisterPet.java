package com.example.appet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class RegisterPet extends AppCompatActivity {

    private FirebaseDatabase db;
    private EditText petnameTxt,edadTxt,animalTxt,razaTxt,nacimientoTxt,condicionTxt;
    private Button registrarBtn;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerpet);

        registrarBtn = findViewById(R.id.registrarBtn);
        petnameTxt = findViewById(R.id.petnameTxt);
        edadTxt = findViewById(R.id.edadTxt);
        animalTxt = findViewById(R.id.animalTxt);
        razaTxt= findViewById(R.id.razaTxt);
        nacimientoTxt= findViewById(R.id.nacimientoTxt);
        condicionTxt= findViewById(R.id.condicionTxt);

        db = FirebaseDatabase.getInstance();

        registrarBtn.setOnClickListener(
                (v) -> {
                    registrarMascota();
                    Intent intent = new Intent( this, Main.class);
                    startActivity(intent);
                });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Get id from shared preferences
        SharedPreferences data = getSharedPreferences("data", MODE_PRIVATE);
        userID = data.getString("userID", "null");
    }

    public void registrarMascota(){
        DatabaseReference newPet = db.getReference("users/" + userID + "/pets");
        DatabaseReference petRef = newPet.push();

        Pets mascota = new Pets(
                edadTxt.getText().toString(),
                animalTxt.getText().toString(),
                razaTxt.getText().toString(),
                nacimientoTxt.getText().toString(),
                condicionTxt.getText().toString(),
                petRef.getKey(),
                petnameTxt.getText().toString()
        );

        petRef.setValue(mascota);
    }
}