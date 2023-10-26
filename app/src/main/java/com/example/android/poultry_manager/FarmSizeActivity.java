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

public class FarmSizeActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText editNumber_of_birds, editSource,editPrice,editTotal_amount_paid,editDate_purchased,editId ;
    Button btnSave;
    Button btnupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_farm_size);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        myDB = new DatabaseHelper(this);
//casting happens here

        editNumber_of_birds = (EditText) findViewById(R.id.frmsize);
        editSource = (EditText) findViewById(R.id.Source);
        editPrice = (EditText) findViewById(R.id.Price);
        editTotal_amount_paid = (EditText) findViewById(R.id.Total);
        editDate_purchased = (EditText) findViewById(R.id.datepurchased);
        editId = (EditText) findViewById(R.id.identity);
        btnSave = (Button) findViewById(R.id.savebtn);
        btnupdate = (Button) findViewById(R.id.updatebtn);

        Save();
        UpdateData();

    }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            if (item.getItemId() == android.R.id.home)
                finish();
            return super.onOptionsItemSelected(item);
        }

    // function called to update saved data
    public void UpdateData(){
        btnupdate.setOnClickListener(
            new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    boolean isUpdated = myDB.updateData( editId.getText().toString(),
                            editNumber_of_birds.getText().toString(),
                            editSource.getText().toString(),
                            editPrice.getText().toString(),
                            editTotal_amount_paid.getText().toString(),
                            editDate_purchased.getText().toString());
                    if(editId.getText().length()==0){
                        showMessage("Error","Please enter the ID of the record you want to update");
                        return;
                    }

                    Cursor res =  myDB.getAllData();
                    if(res.getCount() < Double.parseDouble(((EditText)findViewById(R.id.identity))
                            .getText().toString())) {
                        showMessage("Error","No such ID exists");
                        return;
                    }
                    if(isUpdated == true){
                        Toast.makeText(FarmSizeActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                    }  else {
                        Toast.makeText(FarmSizeActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();
                    }
                }
            }
        );
    }
    //function called to save data
    public void Save(){
        btnSave.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDB.insertData(editNumber_of_birds.getText().toString(),
                                editSource.getText().toString(),
                                editPrice.getText().toString(),
                                editTotal_amount_paid.getText().toString(),
                                editDate_purchased.getText().toString());

                        int first = Integer.valueOf(editNumber_of_birds.getText().toString());
                        int second = Integer.valueOf(editPrice.getText().toString());
                        int product = first * second;
                        editTotal_amount_paid.setText(String.valueOf(product));



                        if(editDate_purchased.getText().length()==0){
                            showMessage("Error","Please enter date when birds where purchased");
                        }
                        if(editDate_purchased.getText().length()==0){

                            isInserted=false;
                        }
                        if(editNumber_of_birds.getText().length()==0){
                            showMessage("Error","Please enter number of birds");
                        }
                        if(editPrice.getText().length()==0){
                            showMessage("Error","Please enter price per bird");
                        }

                        if (isInserted != true) {
                            Toast.makeText(FarmSizeActivity.this, "Your input has not been saved", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(FarmSizeActivity.this, "Your input has been saved", Toast.LENGTH_LONG).show();
                        }


                    }

                }
        );
    }
       public void showMessage (String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this,android.R.style.Theme_Holo_Light_DarkActionBar);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
