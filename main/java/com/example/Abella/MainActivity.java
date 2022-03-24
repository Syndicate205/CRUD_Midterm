package com.example.Abella;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText nameTxt, ageTxt, genderTxt, statusTxt, addressTxt;
    private Button addDataBtn, readDataBtn, deleteAllBtn;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//Edit Text
        nameTxt = findViewById(R.id.idName);
        ageTxt = findViewById(R.id.idAge);
        genderTxt = findViewById(R.id.idGender);
        addressTxt = findViewById(R.id.idAddress);
        statusTxt = findViewById(R.id.idStatus);

//Buttons
        addDataBtn = findViewById(R.id.idBtnAddData);
        readDataBtn = findViewById(R.id.idBtnReadData);
        deleteAllBtn = findViewById(R.id.idBtnDeleteAll);

//Database
        database = new Database(MainActivity.this);

//Add button
        addDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recordName = nameTxt.getText().toString();           //1
                String recordAddress = addressTxt.getText().toString();     //4
                String recordAge = ageTxt.getText().toString();             //2
                String recordGender = genderTxt.getText().toString();       //3
                String recordStatus = statusTxt.getText().toString();       //5

//Validation kung naay sulod ang Edit Text or wala
                if(recordName.isEmpty() && recordAge.isEmpty() && recordGender.isEmpty()
                        && recordAddress.isEmpty() && recordStatus.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter a data", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(recordName.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please Enter your name", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else{
                        database.addNewData(recordName,recordAddress, recordAge, recordGender, recordStatus);
                        Toast.makeText(MainActivity.this, "Data Recorded", Toast.LENGTH_SHORT).show();
                    }
                }
 //Gi Clear tanan Edit Text after na add na ang data
                nameTxt.setText(""); ageTxt.setText(""); genderTxt.setText("");
                addressTxt.setText(""); statusTxt.setText("");
            }
        });
//Read button
        readDataBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

//Validation kung naay sulod ang table or wala
                if(!database.validation()){
                    Toast.makeText(MainActivity.this, "No records to see", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i = new Intent(MainActivity.this, ViewData.class);
                    startActivity(i);
                }
            }
        });
//Delete button
        deleteAllBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

//Validation similar ra sa read button
                if(!database.validation()){
                    Toast.makeText(MainActivity.this, "No records to delete", Toast.LENGTH_SHORT).show();
                }
                else {
                    database.deleteAll();
                    Toast.makeText(MainActivity.this, "All Data Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}