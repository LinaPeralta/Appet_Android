package com.example.appet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        //Firebase
        db = FirebaseDatabase.getInstance();

        getInfo();

    }

    private void getInfo(){
        DatabaseReference dbRef = db.getReference().child("pets").push();

        dbRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot pets) {
                petName.setText(" ");
                for (DataSnapshot user : pets.getChildren()) {

                    Pets pet = user.getValue(Pets.class);
                    petName.append(pet.getName());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }
}