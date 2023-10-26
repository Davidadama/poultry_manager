package com.example.android.poultry_manager;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActualConsumptionActivity extends AppCompatActivity {
    DatabaseHelper myDB1;
    EditText editwk,editwk_g,editwk_kg,editdaily_consump,editweekly_consump,editweek;
    Button btnSave1;
    Button  btnupdate1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_consumption);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        myDB1 = new DatabaseHelper(this);
//casting happens here
        editweek = (EditText)findViewById(R.id.week);
        editwk = (EditText)findViewById(R.id.wk1);
        editwk_g = (EditText)findViewById(R.id.wk1g);
        editwk_kg = (EditText)findViewById(R.id.wk1kg);
        editdaily_consump = (EditText)findViewById(R.id.day_cons);
        editweekly_consump = (EditText)findViewById(R.id.wk_cons);
        btnSave1 = (Button)findViewById(R.id.savebtn1);
        btnupdate1 = (Button)findViewById(R.id.updatebtn1);

        Save1();
        updateData1();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    //function called to save user input
    public void Save1(){
        btnSave1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDB1.insertData0(editwk.getText().toString(),
                                editwk_g.getText().toString(),
                                editwk_kg.getText().toString(),
                                editdaily_consump.getText().toString(),
                                editweekly_consump.getText().toString());
                        if (isInserted != true) {
                            Toast.makeText(ActualConsumptionActivity.this, "Your input has not been saved", Toast.LENGTH_LONG).show();
                        }
                        if(editwk.getText().length()==0){
                            showMessage("Error","Please enter age of birds");
                          }else {
                            Toast.makeText(ActualConsumptionActivity.this, "Your input has been saved", Toast.LENGTH_LONG).show();
                        }

                    }

                }
        );
    }

    public void showMessage (String title,String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
       }
//function called to update previously saved data.
    public void updateData1(){
        btnupdate1.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        boolean isUpdated1 = myDB1.updateData1( editweek.getText().toString(),
                                editwk.getText().toString(),
                                editwk_g.getText().toString(),
                                editwk_kg.getText().toString(),
                                editdaily_consump.getText().toString(),
                                editweekly_consump.getText().toString());
                        if(editweek.getText().length()==0){
                            showMessage("Error","Please enter the week of the record you want to update");
                            return;
                        }
                        Cursor res1 =  myDB1.getAllData1();
                        if(res1.getCount() < Double.parseDouble(((EditText)findViewById(R.id.week))
                                .getText().toString())) {
                            showMessage("Error","No such Week exists,Please insert a valid week to update data");
                            return;
                        }
                        if(isUpdated1 == true){
                            Toast.makeText(ActualConsumptionActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                        }  else {
                            Toast.makeText(ActualConsumptionActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    }

