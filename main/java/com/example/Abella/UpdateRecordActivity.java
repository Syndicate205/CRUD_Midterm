package com.example.Abella;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateRecordActivity extends AppCompatActivity {

    private EditText recordNameEdt, recordAgeEdt, recordGenderEdt, recordAddressEdt, recordStatusEdt;
    private Button updateDataBtn, deleteDataBtn;
    private Database database;
    String name_holder, age_holder, gender_holder, address_holder, status_holder;
    String name_holder_two, age_holder_two, gender_holder_two, address_holder_two, status_holder_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_record);

//Edit Text
        recordNameEdt = findViewById(R.id.idUDRecordName);
        recordAgeEdt = findViewById(R.id.idUDRecordAge);
        recordGenderEdt = findViewById(R.id.idUDRecordGender);
        recordAddressEdt = findViewById(R.id.idUDRecordAddress);
        recordStatusEdt = findViewById(R.id.idUDRecordStatus);

//Button
        updateDataBtn = findViewById(R.id.idBtnUpdate);
        deleteDataBtn = findViewById(R.id.idBtnDelete);

//Database
        database = new Database(UpdateRecordActivity.this);

//Get data and assign to a variable charot hahaha
        name_holder = getIntent().getStringExtra("user_name");
        age_holder = getIntent().getStringExtra("user_age");
        gender_holder = getIntent().getStringExtra("user_gender");
        address_holder = getIntent().getStringExtra("user_address");
        status_holder = getIntent().getStringExtra("user_status");

//Set Text to Edit Text
        recordNameEdt.setText(name_holder);
        recordAgeEdt.setText(age_holder);
        recordGenderEdt.setText(gender_holder);
        recordAddressEdt.setText(address_holder);
        recordStatusEdt.setText(status_holder);

//Similar above for the sake of comparison (wala na gamit hahaha)
        name_holder_two = recordNameEdt.getText().toString();
        age_holder_two = recordAgeEdt.getText().toString();
        gender_holder_two = recordGenderEdt.getText().toString();
        address_holder_two = recordAddressEdt.getText().toString();
        status_holder_two = recordStatusEdt.getText().toString();

//Update button
        updateDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.updateData(name_holder,
                        recordNameEdt.getText().toString(),
                        recordAgeEdt.getText().toString(),
                        recordGenderEdt.getText().toString(),
                        recordAddressEdt.getText().toString(),
                        recordStatusEdt.getText().toString());

                Toast.makeText(UpdateRecordActivity.this, "Record Updated", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(UpdateRecordActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
//Delete button
        deleteDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.deleteData(name_holder);
                Toast.makeText(UpdateRecordActivity.this, "Record Deleted", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateRecordActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}