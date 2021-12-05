package com.example.appet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main extends AppCompatActivity {

    private Button addPetBtn, addProBtn, logOutBtn;
    private ListView petList, productList;
    private ArrayList<Product> dataProduct;
    private ArrayList<Pets> dataPets;
    private ProductAdapter newadapter;
    private PetsAdapter petadapter;
    private FirebaseAuth auth;
    private FirebaseDatabase db;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Elements
        addPetBtn = findViewById(R.id.addPetBtn);
        addProBtn = findViewById(R.id.addProBtn);
        logOutBtn = findViewById(R.id.logOutBtn);
        petList = findViewById(R.id.petList);
        productList = findViewById(R.id.productList);


        //items
        dataProduct = new ArrayList<>();
        dataPets = new ArrayList<>();
        newadapter = new ProductAdapter(this, dataProduct);
        petadapter = new PetsAdapter(this, dataPets);
        productList.setAdapter(newadapter);
        petList.setAdapter(petadapter);

        //Firebase
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        loadUserInfo();

        getProducts();
        getPets();


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
            userID = auth.getCurrentUser().getUid();
            SharedPreferences data = getSharedPreferences("data", MODE_PRIVATE);
            data.edit().putString("userID", userID).apply();
        } else {
            goToLogin();
        }

    }

    //lista mascotas

    public void getPets(){
        DatabaseReference ref = db.getReference("users/" +userID+ "/pets");

        ref.addValueEventListener (new ValueEventListener(){
            @Override
            public void onDataChange (@NonNull DataSnapshot pets) {
                actualizarListaPets(pets);
                System.out.println("snapshot: "+ pets);
            }

            @Override
            public void onCancelled (@NonNull DatabaseError error) {
                Log.d("FirebaseError", error.getDetails());
            }
        });
    }

    public void actualizarListaPets(DataSnapshot pets) {

        this.dataPets.clear();

        for (DataSnapshot snapshot: pets.getChildren()) {
            Pets mascota = snapshot.getValue(Pets.class);
            this.dataPets.add(mascota);
        }
        petadapter.notifyDataSetChanged();

    }



    //lista productos

    public void getProducts(){
        DatabaseReference ref = db.getReference("users/" +userID+ "/products");

        ref.addValueEventListener (new ValueEventListener(){
            @Override
            public void onDataChange (@NonNull DataSnapshot products) {
                actualizarLista(products);
                System.out.println("snapshot: "+ products);
            }

                @Override
                public void onCancelled (@NonNull DatabaseError error) {
                    Log.d("FirebaseError", error.getDetails());
                }
                });
            }


    public void actualizarLista(DataSnapshot products) {

       this.dataProduct.clear();

       for (DataSnapshot snapshot: products.getChildren()) {
       Product producto = snapshot.getValue(Product.class);
       this.dataProduct.add(producto);
      }
        newadapter.notifyDataSetChanged();

    }

    private void goToLogin() {
       Intent login = new Intent(this, Login.class);
       startActivity(login);
       finish();
    }
    }


