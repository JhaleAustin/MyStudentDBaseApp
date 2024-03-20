package com.example.mystudentdbaseapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.SplittableRandom;

public class DeleteActivity extends AppCompatActivity {

    private Spinner spinnerDelete;
    private TextView txtViewDelID, txtViewDelProductPrice, txtViewDelProductname,txtViewDelProductcode, txtViewDelProductquantity, txtViewSearchField;
    private Button btnDelSearch, btnDelRecord, btnDelMain;
    private ArrayList<String> options;
    private String choice,id;
    private StudentDataBase db;
    private Cursor recordSet;


    private EditText editTxtFieldName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        btnDelMain = findViewById(R.id.btnDelMain);
        btnDelRecord= findViewById(R.id.btnDelRecord);
        btnDelSearch = findViewById(R.id.btnDelSearch);
        txtViewDelID = findViewById(R.id.txtViewDelID);
        txtViewDelProductPrice =findViewById(R.id.txtViewDelProductPrice);
        txtViewDelProductname = findViewById(R.id.txtViewDelProductname);

        txtViewDelProductcode =findViewById(R.id.txtViewDelProductcode);
        txtViewDelProductquantity = findViewById(R.id.txtViewDelProductquantity);
        editTxtFieldName = findViewById(R.id.editTxtFieldname);
        spinnerDelete = findViewById(R.id.spinnerDelete);
        txtViewSearchField = findViewById(R.id.txtViewSearchField);

        options=populate();

        spinnerDelete.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                choice = options.get(i).toString();
                editTxtFieldName.setFocusable(true);

                switch (choice){
                    case "ID":
                        txtViewSearchField.setText("ID");
                        break;
                    case "ProductPrice":
                        txtViewSearchField.setText("ProductPrice");
                        break;
                    case "Productname":
                        txtViewSearchField.setText("Productname");
                        break;

                    case "Productcode":
                        txtViewSearchField.setText("Product code");
                        break;
                    case "Productquantity":
                        txtViewSearchField.setText("Product quantity");
                        break;
                }
                btnDelSearch.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnDelSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new StudentDataBase(DeleteActivity.this);
                String value;
                value= editTxtFieldName.getText().toString();

                recordSet = db.searchRecord(choice, value);
                if(recordSet.getCount()==0) {
                    Toast.makeText(DeleteActivity.this, "No record found!", Toast.LENGTH_LONG).show();
                    btnDelSearch.setEnabled(true);
                    btnDelRecord.setEnabled(false);
                }else {
                    recordSet.moveToNext();
                    txtViewDelID.setText(recordSet.getString(0));
                    txtViewDelProductPrice.setText(recordSet.getString(2));
                    txtViewDelProductname.setText(recordSet.getString(1));

                    txtViewDelProductcode.setText(recordSet.getString(3));
                    txtViewDelProductquantity.setText(recordSet.getString(4));
                    editTxtFieldName.setEnabled(false);
                    btnDelSearch.setEnabled(false);
                    btnDelRecord.setEnabled(true);
                    id = txtViewDelID.getText().toString();
                }

            }
        });

        btnDelRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(DeleteActivity.this);

                builder.setTitle("Delete Record")
                        .setMessage("Are you sure you want to delete the Record?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
 //                               id = txtViewDelID.getText().toString();
                                boolean flag;
                                db = new StudentDataBase(DeleteActivity.this);
                                flag = db.deleteStudentRecord(id);

                                if (flag){
                                    Toast.makeText(DeleteActivity.this, "The record has been permanently deleted!", Toast.LENGTH_LONG).show();
                                }
                                else {
                                    Toast.makeText(DeleteActivity.this, "Record was not deleted!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DeleteActivity.this, "Record was not deleted!", Toast.LENGTH_LONG).show();
                    }
                });

                builder.show();

                btnDelSearch.setEnabled(true);
                btnDelRecord.setEnabled(false);
                txtViewDelID.setText("");
                txtViewDelProductPrice.setText("");
                txtViewDelProductname.setText("");

                txtViewDelProductcode.setText("");
                txtViewDelProductquantity.setText("");
                editTxtFieldName.setText("");
                editTxtFieldName.setEnabled(true);
            }
        });

        btnDelMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeleteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    protected ArrayList<String> populate(){
        ArrayList<String> list = new ArrayList<>();
        list.add("ID");
        list.add("Productname");
        list.add("ProductPrice");

        list.add("Productcode");
        list.add("Productquantity");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(DeleteActivity.this, android.R.layout.simple_list_item_1, list );
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        spinnerDelete.setAdapter(adapter);
        return list;
    }


}