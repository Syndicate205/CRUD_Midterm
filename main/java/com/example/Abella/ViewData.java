package com.example.Abella;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewData extends AppCompatActivity{

    private ArrayList<ModalClass> recordModalArrayList;
    private Database database;
    private RecordRVAdapter recordRVAdapter;
    private RecyclerView recordRV;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        recordModalArrayList = new ArrayList<>();
        database = new Database(ViewData.this);

        recordModalArrayList = database.readAllData();

        recordRVAdapter = new RecordRVAdapter(recordModalArrayList, ViewData.this);
        recordRV = findViewById(R.id.idRVData);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewData.this, RecyclerView.VERTICAL, false);
        recordRV.setLayoutManager(linearLayoutManager);

        recordRV.setAdapter(recordRVAdapter);
    }
}
