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

public class EggRecordActivity extends AppCompatActivity {
    DatabaseHelper myDB2;
    EditText editday,editdate,editmonth_in_lay,editfirst_collection,editsecond_collection,editthird_colllection,editother_collection,edittotal;
    Button btnSave2;
    Button btnUpdate2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egg_record);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        myDB2 = new DatabaseHelper(this);


        //casting happens here
        editday=(EditText)findViewById(R.id.txt_day);
        editdate=(EditText)findViewById(R.id.txt_date);
        editmonth_in_lay=(EditText)findViewById(R.id.txt_month);
        editfirst_collection=(EditText)findViewById(R.id.first_coll);
        editsecond_collection=(EditText)findViewById(R.id.second_coll);
        editthird_colllection=(EditText)findViewById(R.id.third_coll);
        editother_collection=(EditText)findViewById(R.id.other_coll);
        edittotal=(EditText)findViewById(R.id.total);
        btnSave2=(Button)findViewById(R.id.savebtn2);
        btnUpdate2=(Button)findViewById(R.id.updatebtn2);

        Save2();
        UpdateData2();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
    //function called to save user input
    public void Save2(){
        btnSave2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDB2.insertData1 (editdate.getText().toString(),
                                editmonth_in_lay.getText().toString(),
                                editfirst_collection.getText().toString(),
                                editsecond_collection.getText().toString(),
                                        editthird_colllection.getText().toString(),
                                        editother_collection.getText().toString(),
                                        edittotal.getText().toString()) ;

                        if (isInserted != true) {
                            Toast.makeText(EggRecordActivity.this, "Your input has not been saved", Toast.LENGTH_LONG).show();
                        }
                        if(edittotal.getText().length()==0){
                            showMessage("Error","Please enter total egg collection");
                        }
                        if(editdate.getText().length()==0){
                            showMessage("Error","Please enter date");
                        }
                        else {
                            Toast.makeText(EggRecordActivity.this, "Your input has been saved", Toast.LENGTH_LONG).show();
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
    //function called to update data in Egg records table
    public void UpdateData2(){
        btnUpdate2.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        boolean isUpdated = myDB2.updateData2( editday.getText().toString(),
                                editdate.getText().toString(),
                                editmonth_in_lay.getText().toString(),
                                editfirst_collection.getText().toString(),
                                editsecond_collection.getText().toString(),
                                editthird_colllection.getText().toString(),
                                editother_collection.getText().toString(),
                                edittotal.getText().toString());
                        if(editday.getText().length()==0){
                            showMessage("Error","Please enter the day of the record you want to update");
                            return;
                        }

                        Cursor res2 =  myDB2.getAllData2();
                        if(res2.getCount() < Double.parseDouble(((EditText)findViewById(R.id.txt_day))
                                .getText().toString())) {
                            showMessage("Error","No such Day exists,Please use a valid day to update data");
                            return;
                        }

                        if(isUpdated == true){
                            Toast.makeText(EggRecordActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                        }  else {
                            Toast.makeText(EggRecordActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

}
