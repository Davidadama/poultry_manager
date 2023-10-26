package com.example.android.poultry_manager;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class RecordsActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    DatabaseHelper myDB3;
    DatabaseHelper myDB4;
    DatabaseHelper myDB5;

    Button btnViewAll;
    Button btnViewall3;
    Button btnViewall4;
    Button btnViewall5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        myDB = new DatabaseHelper(this);
        myDB3 = new DatabaseHelper(this);
        myDB4 = new DatabaseHelper(this);
        myDB5 = new DatabaseHelper(this);

        btnViewAll = (Button)findViewById(R.id.viewbtn);
        btnViewall3 = (Button) findViewById(R.id.viewbtn4);
        btnViewall4 = (Button) findViewById(R.id.viewbtn5);
        btnViewall5 = (Button) findViewById(R.id.viewbtn6);

        ViewAll();
        ViewAll3();
        ViewAll4();
        ViewAll5();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
    //function called to view saved data from farmsize activity
    public  void ViewAll(){
        btnViewAll.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Cursor res =  myDB.getAllData();
                        if(res.getCount()== 0){
                            //show message
                            showMessage("Error","No Entries yet");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("ID:  " + res.getString(0) + "\n\n");
                            buffer.append("number_of_birds:  " + res.getString(1) + "\n\n");
                            buffer.append("source:  " + res.getString(2) + "\n\n");
                            buffer.append("price:  " + res.getString(3) + "\n\n");
                            buffer.append("total_amount_paid:  " + res.getString(4) + "\n\n");
                            buffer.append("date_purchased:  " + res.getString(5) + "\n\n\n");

                        }
                        //show all data
                        showMessage("Saved Data",buffer.toString());
                    }
                }
        );
    }
    public  void ViewAll3(){
        btnViewall3.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Cursor res3 =  myDB3.getAllData3();
                        if(res3.getCount()== 0){
                            //show message
                            showMessage3("Error","No Entries yet");
                            return;
                        }
                        StringBuffer buffer3 = new StringBuffer();
                        while (res3.moveToNext()){
                            buffer3.append("ID:  " + res3.getString(0) + "\n\n");
                            buffer3.append("DATE:  " + res3.getString(1) + "\n\n");
                            buffer3.append("Age_of_Birds:  " + res3.getString(2) + "\n\n");
                            buffer3.append("Number_of_deaths:  " + res3.getString(3) + "\n\n");
                            buffer3.append("Number_of_deaths_caused_by_disease:  " + res3.getString(4) + "\n\n");
                            buffer3.append("Number_of_deaths_due_to_culling:  " + res3.getString(5) + "\n\n");
                            buffer3.append("Number_of_Birds_Left:  " + res3.getString(6) + "\n\n\n");
                        }
                        //show all data
                        showMessage3("Saved Data",buffer3.toString());
                    }
                }
        );
    }
    public  void ViewAll4(){
        btnViewall4.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Cursor res4 =  myDB4.getAllData4();
                        if(res4.getCount()== 0){
                            //show message
                            showMessage4("Error","No Entries yet");
                            return;
                        }
                        StringBuffer buffer4 = new StringBuffer();
                        while (res4.moveToNext()){
                            buffer4.append("ID:  " + res4.getString(0) + "\n\n");
                            buffer4.append("dATE:  " + res4.getString(1) + "\n\n");
                            buffer4.append("Age_of_birds:  " + res4.getString(2) + "\n\n");
                            buffer4.append("Possible_cause_of_sickness:  " + res4.getString(3) + "\n\n");
                            buffer4.append("Number_sick:  " + res4.getString(4) + "\n\n\n");
                        }
                        //show all data
                        showMessage4("Saved Data",buffer4.toString());
                    }
                }
        );
    }
    public  void ViewAll5(){
        btnViewall5.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Cursor res5 =  myDB5.getAllData5();
                        if(res5.getCount()== 0){
                            //show message
                            showMessage5("Error","No Entries yet");
                            return;
                        }
                        StringBuffer buffer5 = new StringBuffer();
                        while (res5.moveToNext()){
                            buffer5.append("ID:  " + res5.getString(0) + "\n\n");
                            buffer5.append("Sale_or_Purchase:  " + res5.getString(1) + "\n\n");
                            buffer5.append("DATe:  " + res5.getString(2) + "\n\n");
                            buffer5.append("Item_or_commodity_sold_or_Bought:  " + res5.getString(3) + "\n\n");
                            buffer5.append("Number_of_item_sold_or_bought:  " + res5.getString(4) + "\n\n");
                            buffer5.append("Price_of_item_sold_or_bought:  " + res5.getString(5) + "\n\n");
                            buffer5.append("Total_amount_of_sale_or_purchase:  " + res5.getString(6) + "\n\n");
                            buffer5.append("Brand:  " + res5.getString(7) + "\n\n");
                            buffer5.append("Purchase_Source:  " + res5.getString(8) + "\n\n\n");

                        }
                        //show all data
                        showMessage5("Saved Data",buffer5.toString());
                    }
                }
        );
    }

    public void showMessage (String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this,android.R.style.Theme_Material_Light_NoActionBar_Fullscreen);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.setPositiveButton("Add/Edit",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int which){
                Intent intent = new Intent(RecordsActivity.this,FarmSizeActivity.class);
                startActivity(intent);
            }
        });
        builder.show();
    }
    public void showMessage3 (String title,String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_NoActionBar_Fullscreen);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.setPositiveButton("Add/Edit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(RecordsActivity.this, MortalityActivity.class);
                startActivity(intent);
            }
        });
        builder.show();

    }
    public void showMessage4 (String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this,android.R.style.Theme_Material_Light_NoActionBar_Fullscreen);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.setPositiveButton("Add/Edit",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int which){
                Intent intent = new Intent(RecordsActivity.this,NumbersickActivity.class);
                startActivity(intent);
            }
        });

        builder.show();
    }

    public void showMessage5 (String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this,android.R.style.Theme_Material_Light_NoActionBar_Fullscreen);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.setPositiveButton("Add/Edit",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int which){
                Intent intent = new Intent(RecordsActivity.this,MarketingRecordsActivity.class);
                startActivity(intent);
            }
        });

        builder.show();
    }

    //click function to start EggActivity
    public void feed(View view) {
        Intent intent = new Intent(this,FeedingActivity.class);
        startActivity(intent);
    }
    public void Egg(View view) {
        Intent intent = new Intent(this,EggActivity.class);
        startActivity(intent);
    }

}





