package com.example.mystudentdbaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

    private Spinner spinner;
    private EditText editTxtEditID, editTxtEditProductprice, editTxtEditProductname, editTxtEditProductcode, editTxtEditProductquantity;
    private Button btnEditSearch,btnEditSave,btnEditMain;
    private ArrayList<String> options;
    private String id, Productname, Productprice, Productcode, Productquantity, choice;
    private StudentDataBase db;
    private Cursor recordSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        spinner= findViewById(R.id.spinnerSearch);
        editTxtEditID = findViewById(R.id.editTxtEditID);
        editTxtEditProductprice = findViewById(R.id.editTxtEditProductPrice);
        editTxtEditProductname=findViewById(R.id.editTxtEditProductname);


        editTxtEditProductcode = findViewById(R.id.editTxtEditProductcode);
        editTxtEditProductquantity=findViewById(R.id.editTxtEditProductquantity);

        btnEditSearch = findViewById(R.id.btnEditSearch);
        btnEditSave = findViewById(R.id.btnEditSave);
        btnEditMain = findViewById(R.id.btnEditMain);




        options = populate();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {

                choice = options.get(i).toString();

                switch (choice){
                    case "ID":
                        editTxtEditID.setEnabled(true);
                        editTxtEditProductprice.setEnabled(false);
                        editTxtEditProductname.setEnabled(false);

                        editTxtEditProductcode.setEnabled(false);
                            editTxtEditProductquantity.setEnabled(false);
                        editTxtEditID.setFocusable(true);
                        break;
                    case "Productprice":
                        editTxtEditProductprice.setEnabled(true);
                        editTxtEditID.setEnabled(false);

                        editTxtEditProductcode.setEnabled(false);
                        editTxtEditProductquantity.setEnabled(false);
                        editTxtEditProductname.setEnabled(false);
                        editTxtEditProductprice.setFocusable(true);
                        break;
                    case "Productname":
                        editTxtEditProductname.setEnabled(true);
                        editTxtEditID.setEnabled(false);

                        editTxtEditProductcode.setEnabled(false);
                        editTxtEditProductquantity.setEnabled(false);
                        editTxtEditProductprice.setEnabled(false);
                        editTxtEditProductname.setFocusable(true);
                        break;
                    case "Productcode":
                        editTxtEditProductprice.setEnabled(false);
                        editTxtEditID.setEnabled(false);

                        editTxtEditProductcode.setEnabled(true);
                        editTxtEditProductquantity.setEnabled(false);
                        editTxtEditProductname.setEnabled(false);
                        editTxtEditProductcode.setFocusable(true);
                        break;
                    case "Productquatity":
                        editTxtEditProductname.setEnabled(false);
                        editTxtEditID.setEnabled(false);

                        editTxtEditProductcode.setEnabled(false);
                        editTxtEditProductquantity.setEnabled(true);
                        editTxtEditProductprice.setEnabled(false);
                        editTxtEditProductquantity.setFocusable(true);
                        break;
                }
                btnEditSearch.setEnabled(true);




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnEditSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db = new StudentDataBase(EditActivity.this);

                id = editTxtEditID.getText().toString();
                editTxtEditID.setEnabled(false);
                Productprice = editTxtEditProductprice.getText().toString();
                Productname =editTxtEditProductname.getText().toString();

                Productcode= editTxtEditProductprice.getText().toString();
                Productquantity=editTxtEditProductname.getText().toString();

                recordSet = db.searchRecord(choice, id, Productprice,Productname,Productcode,Productquantity);

                if(recordSet.getCount()==0){
                    Toast.makeText(EditActivity.this,"No record found!",Toast.LENGTH_LONG).show();
                    editTxtEditID.setText("");
                    editTxtEditProductprice.setText("");
                    editTxtEditProductprice.setText("");
                    editTxtEditProductcode.setText("");
                    editTxtEditProductquantity.setText("");
                    btnEditSave.setEnabled(false);
                    btnEditSearch.setEnabled(true);
                }else {
                    if (recordSet.moveToNext()) {
                        editTxtEditID.setText(recordSet.getString(0));
                        editTxtEditProductprice.setText(recordSet.getString(2));
                        editTxtEditProductname.setText(recordSet.getString(1));
                        editTxtEditProductcode.setText(recordSet.getString(3));
                        editTxtEditProductquantity.setText(recordSet.getString(4));
                        editTxtEditProductprice.setEnabled(true);
                        editTxtEditProductname.setEnabled(true);
                        editTxtEditProductcode.setEnabled(true);
                        editTxtEditProductquantity.setEnabled(true);
                        btnEditSave.setEnabled(true);
                        btnEditSearch.setEnabled(false);

                    }
                }

            }
        });
        btnEditSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new StudentDataBase(EditActivity.this);

                id = editTxtEditID.getText().toString();
                Productprice = editTxtEditProductprice.getText().toString();
                Productname =editTxtEditProductname.getText().toString();


                Productcode= editTxtEditProductcode.getText().toString();
                Productquantity =editTxtEditProductquantity.getText().toString();

                boolean validateEditRecord = db.editStudentRecord( id, Productprice,Productname,Productcode,Productquantity);

                if(validateEditRecord){
                    Toast.makeText(EditActivity.this,"Updated the record successfully", Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(EditActivity.this,"Sorry, updating of record Failed!", Toast.LENGTH_LONG).show();
                }

                btnEditSave.setEnabled(false);
                btnEditSearch.setEnabled(true);
                editTxtEditID.setText("");
                editTxtEditProductprice.setText("");
                editTxtEditProductname.setText("");

                editTxtEditProductcode.setText("");
                editTxtEditProductquantity.setText("");
                editTxtEditID.setEnabled(false);
                editTxtEditProductprice.setEnabled(false);
                editTxtEditProductname.setEnabled(false);

            }
        });

        btnEditMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    protected ArrayList<String>  populate(){
        ArrayList<String> list = new ArrayList<>();
        list.add("ID");
        list.add("Productname");
        list.add("Productprice");

        list.add("Productcode");
        list.add("Productquantity");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditActivity.this, android.R.layout.simple_list_item_1, list );
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        spinner.setAdapter(adapter);
        return list;
    }


}