package com.example.appet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class PetInfo extends AppCompatActivity {

    private TextView petName, type, breed, birth, condition;
    private FirebaseDatabase db;
    private Button regresarBtn;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_info);

        //Elements
        petName = findViewById(R.id.petName);
        type = findViewById(R.id.type);
        breed = findViewById(R.id.breed);
        birth = findViewById(R.id.birth);
        condition = findViewById(R.id.condition);
        regresarBtn = findViewById(R.id.regresarBtn);

        //Firebase
        db = FirebaseDatabase.getInstance();

        getInfo();

        regresarBtn.setOnClickListener((v)->{
            Intent main = new Intent(this, Main.class);
            startActivity(main);
        });


    }

    private void getInfo(){

        SharedPreferences data = getSharedPreferences("data", Context.MODE_PRIVATE);
        userID = data.getString("userID", "null");

        String petID = getIntent().getStringExtra("petID");

        DatabaseReference ref = db.getReference("users/" +userID+ "/pets");

        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot pets) {
                petName.setText(" ");
                type.setText(" ");
                breed.setText(" ");
                birth.setText(" ");
                condition.setText(" ");

                for (DataSnapshot user : pets.getChildren()) {

                    Pets pet = user.getValue(Pets.class);

                    if(petID.equals(pet.getId())){

                        petName.setText(pet.getName());
                        type.setText(pet.getAnimal());
                        breed.setText(pet.getBreed());
                        birth.setText(pet.getDatebirth());
                        condition.setText(pet.getConditions());
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

}