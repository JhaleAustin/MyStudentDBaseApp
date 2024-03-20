package com.example.mystudentdbaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class StudentDataBase extends SQLiteOpenHelper {
    private String query;
    private SQLiteDatabase db;
    private Cursor recordSet;
    private ContentValues cv;

    public StudentDataBase( Context context) {
        super(context, "ProductDetails.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        query = "CREATE TABLE Product (id INTEGER PRIMARY KEY AUTOINCREMENT, Productname TEXT, ProductPrice TEXT, Productcode TEXT, Productquantity TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        query = "DROP TABLE IF EXISTS Product";
        db.execSQL(query);
    }



    public boolean addStudentRecord(Student s){
        Log.d("ProductDataBase","Adding a record...");

        boolean flag = false;
        db= this.getReadableDatabase();

        cv= new ContentValues();
        cv.put("Productname", s.getProductname());
      cv.put("ProductPrice", s.getProductPrice());
       cv.put("Productcode", s.getProductcode());
        cv.put("Productquantity", s.getProductquantity());

        long value =db.insert("Product", null,cv);

        if (value < 0){
            flag = false;
        }else {
            flag = true;
        }
        return flag;
    }

    public boolean editStudentRecord(String id, String ProductPrice, String Productname,String Productcode, String Productquantity){
        boolean flag = false;
        db = this.getWritableDatabase();
        cv= new ContentValues();
        cv.put("Productname", Productname);
        cv.put("ProductPrice", ProductPrice);

        cv.put("Productcode", Productcode);
        cv.put("Productquantity", Productquantity);
        query = "SELECT * FROM Product WHERE id ='"+ id + "'";
        recordSet = db.rawQuery(query, null);

        if (recordSet.getCount()>0){
            long value = db.update("Product",cv,"id=?", new String[]{id});
            if (value < 0){
                flag = false;
            }else {
                flag = true;
            }
        }

         return flag;
    }

    public boolean deleteStudentRecord(String id){
        boolean flag = false;
        db = this.getWritableDatabase();

        query = "SELECT * FROM Product WHERE id ='"+ id + "'";
        recordSet = db.rawQuery(query, null);

        if (recordSet.getCount()>0){
            long value = db.delete("Product","id=?", new String[]{id});
            if (value < 0){
                flag = false;
            }else {
                flag = true;
            }
        }

        return flag;
    }



    public Cursor getAllStudentRecords() {
        query = "SELECT * FROM Product";
        db =  this.getReadableDatabase();
        recordSet = db.rawQuery(query, null);

        return recordSet;
    }
   public Cursor searchRecord(String fieldName, String id, String ProductPrice, String Productname,String Productcode, String Productquantity){
        db =  this.getReadableDatabase();
        query = "SELECT * FROM Product";
        recordSet = db.rawQuery(query, null);


        switch (fieldName){
            case "ID":
                query = "SELECT * FROM Product WHERE id = '"+ id+"'";
                break;
            case "ProductPrice":
                query = "SELECT * FROM Product WHERE ProductPrice = '"+ ProductPrice+"'";
                break;
            case "Productname":
                query = "SELECT * FROM Product WHERE Productname = '"+Productname+"'";
                break;
            case "Productcode":
                query = "SELECT * FROM Product WHERE Productcode = '"+ Productcode+"'";
                break;
            case "Productquantity":
                query = "SELECT * FROM Product WHERE Productquantity = '"+Productquantity+"'";
                break;
        }
        recordSet = db.rawQuery(query, null);
        return recordSet;

   }
    public Cursor searchRecord(String fieldName, String value){
        db =  this.getReadableDatabase();
        query = "SELECT * FROM Product";
        recordSet = db.rawQuery(query, null);


        switch (fieldName){
            case "ID":
                query = "SELECT * FROM Product WHERE id = '"+ value+"'";
                break;
            case "ProductPrice":
                query = "SELECT * FROM Product WHERE ProductPrice = '"+ value+"'";
                break;
            case "Productname":
                query = "SELECT * FROM Product WHERE Productname = '"+value+"'";
                break;
            case "Productcode":
                query = "SELECT * FROM Product WHERE Productcode = '"+ value+"'";
                break;
            case "Productquantity":
                query = "SELECT * FROM Product WHERE Productquantity = '"+value+"'";
                break;
        }
        recordSet = db.rawQuery(query, null);
        return recordSet;

    }

}
