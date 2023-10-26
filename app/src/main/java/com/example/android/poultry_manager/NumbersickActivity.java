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

public class NumbersickActivity extends AppCompatActivity {

    DatabaseHelper myDB4;
    EditText editId, editdate, editage_of_birds, editpossible_cause, editnumber_sick;

    Button btnSave4;
    Button btnUpdate4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbersick);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        myDB4 = new DatabaseHelper(this);

        //casting happens here
        editId = (EditText) findViewById(R.id.idn2);
        editdate = (EditText) findViewById(R.id.sick_date);
        editage_of_birds = (EditText) findViewById(R.id.sick_age);
        editpossible_cause = (EditText) findViewById(R.id.possible_cause);
        editnumber_sick= (EditText) findViewById(R.id.num_sick);
        btnSave4 = (Button) findViewById(R.id.savebtn4);
        btnUpdate4 = (Button) findViewById(R.id.updatebtn4);

        Save4();
        UpdateData4();
       }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    //function called to save user input
    public void Save4() {
        btnSave4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDB4.insertData4(editdate.getText().toString(),
                                editage_of_birds.getText().toString(),
                                editpossible_cause.getText().toString(),
                                editnumber_sick.getText().toString());

                        if (isInserted != true) {
                            Toast.makeText(NumbersickActivity.this, "Your input has not been saved", Toast.LENGTH_LONG).show();
                        }
                        if(editnumber_sick.getText().length()==0){
                            showMessage4("Error","Please enter number of birds sick");
                        }
                        if(editdate.getText().length()==0){
                            showMessage4("Error","Please enter date");
                        }
                        else {
                            Toast.makeText(NumbersickActivity.this, "Your input has been saved", Toast.LENGTH_LONG).show();
                        }


                    }

                }
        );
    }
    //function called to view all saved data
    public void showMessage4 (String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    //function called to update data in Mortality records table
    public void UpdateData4(){
        btnUpdate4.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        boolean isUpdated = myDB4.updateData4(  editId.getText().toString(),
                                editdate.getText().toString(),
                                editage_of_birds.getText().toString(),
                                editpossible_cause.getText().toString(),
                                editnumber_sick.getText().toString());
                        if(editId.getText().length()==0){
                            showMessage4("Error","Please enter the ID of the record you want to update");
                            return;
                        }
                        Cursor res4 =  myDB4.getAllData4();
                        if(res4.getCount() < Double.parseDouble(((EditText)findViewById(R.id.idn2))
                                .getText().toString())) {
                            showMessage4("Error","No such ID exists,please use a valid ID to update data");
                            return;
                        }

                        if(isUpdated == true){
                            Toast.makeText(NumbersickActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                        }  else {
                            Toast.makeText(NumbersickActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );

    }
}

