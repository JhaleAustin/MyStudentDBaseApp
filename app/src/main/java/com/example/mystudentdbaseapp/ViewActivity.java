package com.example.mystudentdbaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewActivity extends AppCompatActivity {

    private Button btnNext, btnMainPage, btnPrevious;
    private TextView txtViewID,txtViewProductname,txtViewProductprice,txtViewProductcode,txtViewProductquantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        String id, first, last;
        Cursor recordSet;

        btnNext = findViewById(R.id.btnNext);
        btnMainPage=findViewById(R.id.btnViewMain);
        btnPrevious = findViewById(R.id.btnPrevious);
        txtViewID =findViewById(R.id.txtViewID);
        txtViewProductname=findViewById(R.id.txtViewProductname);
        txtViewProductprice= findViewById(R.id.txtViewProductprice);

        txtViewProductcode=findViewById(R.id.txtViewProductcode);
        txtViewProductquantity= findViewById(R.id.txtViewProductquantity);

        StudentDataBase db = new StudentDataBase(ViewActivity.this);
        recordSet = db.getAllStudentRecords();

        if(recordSet.getCount()==0){
            Toast.makeText(ViewActivity.this,"No record found!",Toast.LENGTH_LONG).show();

        }else {
            if (recordSet.moveToFirst()) {
                txtViewID.setText(recordSet.getString(0));
                txtViewProductname.setText(recordSet.getString(1));
                txtViewProductprice.setText(recordSet.getString(2));

                txtViewProductcode.setText(recordSet.getString(3));
                txtViewProductquantity.setText(recordSet.getString(4));
            }
        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(recordSet.moveToNext()){
                    txtViewID.setText(recordSet.getString(0));
                    txtViewProductname.setText(recordSet.getString(1));
                    txtViewProductprice.setText(recordSet.getString(2));

                    txtViewProductcode.setText(recordSet.getString(3));
                    txtViewProductquantity.setText(recordSet.getString(4));
                }
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(recordSet.moveToPrevious()){
                    txtViewID.setText(recordSet.getString(0));
                    txtViewProductname.setText(recordSet.getString(1));
                    txtViewProductprice.setText(recordSet.getString(2));

                    txtViewProductcode.setText(recordSet.getString(3));
                    txtViewProductquantity.setText(recordSet.getString(4));
                }
            }
        });

        btnMainPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent( ViewActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });




    }
}