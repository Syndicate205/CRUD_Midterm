package com.example.Abella;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    private static final String DB_NAME = "recorddb";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "myrecord";
    private static final String ID_COL = "id";
    private static final String USER_NAME_COL = "user_name";
    private static final String USER_AGE_COL = "user_age";
    private static final String USER_GENDER_COL = "user_gender";
    private static final String USER_ADDRESS_COL = "user_address";
    private static final String USER_STATUS_COL = "user_status";

    public Database(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

//Create og table
    @Override
    public void onCreate(SQLiteDatabase db){
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_NAME_COL + " TEXT,"
                + USER_AGE_COL + " TEXT,"
                + USER_GENDER_COL + " TEXT,"
                + USER_ADDRESS_COL + " TEXT,"
                + USER_STATUS_COL + " TEXT)";

        db.execSQL(query);
    }
//Add new data
    public void addNewData(String recordName, String recordAddress, String recordAge,
                           String recordGender, String recordStatus){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(USER_NAME_COL, recordName);             //1
        values.put(USER_AGE_COL, recordAge);               //2
        values.put(USER_GENDER_COL, recordGender);         //3
        values.put(USER_ADDRESS_COL, recordAddress);       //4
        values.put(USER_STATUS_COL, recordStatus);         //5

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
//Read all data
    public ArrayList<ModalClass> readAllData(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorRecord = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<ModalClass> recordModalArrayList = new ArrayList<>();

        if (cursorRecord.moveToFirst()){
            do{
                recordModalArrayList.add(new ModalClass(cursorRecord.getString(1),
                        cursorRecord.getString(2),
                        cursorRecord.getString(3),
                        cursorRecord.getString(4),
                        cursorRecord.getString(5)));
            }
            while (cursorRecord.moveToNext());
        }
        cursorRecord.close();
        return recordModalArrayList;
    }
//Validation if naay data sa database or wala alrights na okay siya hahaha
//Gigamit ni sa MainActivity class
    public boolean validation(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT count(*) FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        boolean flag = false;

        if(count > 0){
            flag = true;
            return flag;
        }
        cursor.close();
        return flag;
    }
//Update data isa ra
    public void updateData(String originalRecordName, String recordName, String recordAge,
                           String recordGender, String recordAddress, String recordStatus){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(USER_NAME_COL, recordName);
        values.put(USER_AGE_COL, recordAge);
        values.put(USER_GENDER_COL, recordGender);
        values.put(USER_ADDRESS_COL, recordAddress);
        values.put(USER_STATUS_COL, recordStatus);

        db.update(TABLE_NAME, values, "user_name=?", new String[]{originalRecordName});
        db.close();
    }
//Delete ra og isa ka data
    public void deleteData(String recordName){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, "user_name=?", new String []{recordName});
        db.close();
    }
//read one data pero wala na gamit sad life :(
    public void readOne(String recordName){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("SELECT * FROM " +TABLE_NAME, new String []{recordName});
        db.close();
    }
//Delete tanan data
    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
        db.close();
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
