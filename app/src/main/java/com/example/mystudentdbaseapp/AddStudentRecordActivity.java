package com.example.mystudentdbaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudentRecordActivity extends AppCompatActivity {

    private Button btnMain, btnAddRecord, btnSaveRecord;
    private EditText editTxtAddProductname, editTxtAddProductPrice, editTxtAddProductcode, editTxtAddProductquantity;
    private StudentDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_record);

        db = new StudentDataBase(this);

        btnMain = findViewById(R.id.btnAddMain);
        btnSaveRecord = findViewById(R.id.btnSaveRecord);
        btnAddRecord = findViewById(R.id.btnAddRecord);

        editTxtAddProductname = findViewById(R.id.editTxtAddProductname);
        editTxtAddProductPrice = findViewById(R.id.editTxtAddProductPrice);
        editTxtAddProductcode = findViewById(R.id.editTxtAddProductcode);
        editTxtAddProductquantity = findViewById(R.id.editTxtAddProductquantity);

        btnAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableFields(true);
            }
        });

        btnSaveRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRecord();
            }
        });

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddStudentRecordActivity.this, MainActivity.class));
            }
        });
    }

    private void enableFields(boolean enabled) {
        editTxtAddProductname.setEnabled(enabled);
        editTxtAddProductPrice.setEnabled(enabled);
        editTxtAddProductcode.setEnabled(enabled);
        editTxtAddProductquantity.setEnabled(enabled);
        btnSaveRecord.setEnabled(enabled);
        btnAddRecord.setEnabled(!enabled);
    }

    private void saveRecord() {
        String name = editTxtAddProductname.getText().toString().trim();
        String price = editTxtAddProductPrice.getText().toString().trim();
        String code = editTxtAddProductcode.getText().toString().trim();
        String quantity = editTxtAddProductquantity.getText().toString().trim();

        if (name.isEmpty() || price.isEmpty() || code.isEmpty() || quantity.isEmpty()) {
            Toast.makeText(AddStudentRecordActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else {
            Student student = new Student(name, price, code, quantity);
            boolean isRecordAdded = db.addStudentRecord(student);

            if (isRecordAdded) {
                Toast.makeText(AddStudentRecordActivity.this, "Record added successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AddStudentRecordActivity.this, "Failed to add record", Toast.LENGTH_SHORT).show();
            }
            enableFields(false);
            clearFields();
        }
    }

    private void clearFields() {
        editTxtAddProductname.setText("");
        editTxtAddProductPrice.setText("");
        editTxtAddProductcode.setText("");
        editTxtAddProductquantity.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
