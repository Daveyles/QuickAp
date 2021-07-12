package com.example.quickap;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    LinearLayout mdate;

    private EditText mname,msurname,mgender,mAge,datetxt;
    private Button addbtn;
    Button viewbtn;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Patients");

    private int day,month,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mdate = findViewById(R.id.mdate);
        mname = findViewById(R.id.name);
        msurname = findViewById(R.id.surname);
        mgender = findViewById(R.id.gender);
        mAge = findViewById(R.id.age);
        addbtn = findViewById(R.id.addbtn);
        datetxt = findViewById(R.id.datetxt);
        viewbtn = findViewById(R.id.viewbtn);

        datetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar Cal = Calendar.getInstance();
                day = Cal.get(Calendar.DATE);
                month = Cal.get(Calendar.MONTH);
                year = Cal.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int myear, int mMonth, int mday) {
                        datetxt.setText(mday+"/"+mMonth+"/"+myear);

                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });
        mdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar Cal = Calendar.getInstance();
                day =Cal.get(Calendar.DATE);
                month = Cal.get(Calendar.MONTH);
                year = Cal.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int myear, int mMonth, int mday) {
                        datetxt.setText(mday+"/"+mMonth+"/"+myear);

                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mname.getText().toString();
                String surname = msurname.getText().toString();
                String gender = mgender.getText().toString();
                String age = mAge.getText().toString();
                String date = datetxt.getText().toString();

                if(mname.length()==0){
                    mname.setError("Name required");
                }
                else if(msurname.length()==0){
                    msurname.setError("Surname required");
                }
                else if(mgender.length()==0){
                    mgender.setError("Gender required");
                }
                else if(mAge.length()==0){
                    mAge.setError("Age required");
                }
                else if(datetxt.length()==0){
                    datetxt.setError("Date required");
                }
                else {

                    HashMap<String, String> userMap = new HashMap<>();

                    userMap.put("name", name);
                    userMap.put("surname", surname);
                    userMap.put("gender", gender);
                    userMap.put("age", age);
                    userMap.put("date", date);

                    root.push().setValue(userMap);

                    mname.setText("");
                    msurname.setText("");
                    mgender.setText("");
                    mAge.setText("");
                    datetxt.setText("");

                    Toast.makeText(MainActivity.this, "Appointment Added Successfully!", Toast.LENGTH_LONG).show();
                }
            }
        });

        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });






    }

    public void openActivity(){
        Intent i = new Intent(this,patients.class);
        startActivity(i);
    }
}