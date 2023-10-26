package com.example.android.poultry_manager;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MortalityActivity extends AppCompatActivity {

    DatabaseHelper myDB3;
    EditText editID, editdate, editage_of_birds, editnumber_of_deaths, editdeath_caused_by_disease, editdeath_due_to_culling, editnumber_of_birds_left;

    Button btnSave3;
    Button btnUpdate3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortality);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        myDB3 = new DatabaseHelper(this);

        //casting happens here
        editID = (EditText) findViewById(R.id.idn1);
        editdate = (EditText) findViewById(R.id.mortdate);
        editage_of_birds = (EditText) findViewById(R.id.mort_age);
        editnumber_of_deaths = (EditText) findViewById(R.id.num_of_death);
        editdeath_caused_by_disease = (EditText) findViewById(R.id.death_cause_dis);
        editdeath_due_to_culling = (EditText) findViewById(R.id.death_culling);
        editnumber_of_birds_left = (EditText) findViewById(R.id.num_left);
        btnSave3 = (Button) findViewById(R.id.savebtn3);
        btnUpdate3 = (Button) findViewById(R.id.updatebtn3);

        Save3();
        UpdateData3();
         }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    //function called to save user input
    public void Save3() {
        btnSave3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDB3.insertData3(editdate.getText().toString(),
                                editage_of_birds.getText().toString(),
                                editnumber_of_deaths.getText().toString(),
                                editdeath_caused_by_disease.getText().toString(),
                                editdeath_due_to_culling.getText().toString(),
                                editnumber_of_birds_left.getText().toString());
                        if (isInserted != true) {
                            Toast.makeText(MortalityActivity.this, "Your input has not been saved", Toast.LENGTH_LONG).show();
                        }
                        if(editnumber_of_birds_left.getText().length()==0){
                            showMessage("Error","Please enter the number of birds left");
                        }
                        if(editdate.getText().length()==0){
                            showMessage("Error","Please enter date");
                        }
                        else {
                            Toast.makeText(MortalityActivity.this, "Your input has been saved", Toast.LENGTH_LONG).show();
                        }

                    }

                }
        );
    }

    //function called to view all saved data
    public void showMessage (String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    //function called to update data in Mortality records table
    public void UpdateData3(){
        btnUpdate3.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                            boolean isUpdated = myDB3.updateData3(editID.getText().toString(),
                                editdate.getText().toString(),
                                editage_of_birds.getText().toString(),
                                editnumber_of_deaths.getText().toString(),
                                editdeath_caused_by_disease.getText().toString(),
                                editdeath_due_to_culling.getText().toString(),
                                editnumber_of_birds_left.getText().toString());
                        if(editID.getText().length()==0){
                            showMessage("Error","Please enter date of the record you want to update");
                            return;
                        }
                        Cursor res3 =  myDB3.getAllData3();
                        if(res3.getCount() < Double.parseDouble(((EditText)findViewById(R.id.idn1))
                                .getText().toString())) {
                            showMessage("Error","No such ID exists");
                            return;
                        }
                            if(isUpdated == true){
                            Toast.makeText(MortalityActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                        }  else {
                            Toast.makeText(MortalityActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );

    }
}

