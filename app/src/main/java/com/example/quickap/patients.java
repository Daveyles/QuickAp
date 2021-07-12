package com.example.quickap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class patients extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter adapter;
    DatabaseReference database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients);

        database=FirebaseDatabase.getInstance().getReference().child("Patients");

        recyclerView = findViewById(R.id.patientRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Patient> options =
                new FirebaseRecyclerOptions.Builder<Patient>()
                        .setQuery(database, Patient.class)
                        .build();

        adapter=new MyAdapter(options);
        recyclerView.setAdapter(adapter);



    }

    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public  void onBackPressed(){
        super.onBackPressed();
        finish();
    }

}








