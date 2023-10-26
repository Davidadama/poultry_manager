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

public class FeedingActivity extends AppCompatActivity
{
    DatabaseHelper myDB1;
    Button btnviewAll0;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeding);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        myDB1 = new DatabaseHelper(this);
        btnviewAll0 = (Button)findViewById(R.id.viewbtn1);
        ViewAll0();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
    public  void ViewAll0(){
        btnviewAll0.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Cursor res1 =  myDB1.getAllData1();
                        if(res1.getCount()== 0){
                            //show message
                            showMessage("Error","No Entries yet");
                            return;
                        }
                        StringBuffer buffer1 = new StringBuffer();
                        while (res1.moveToNext()){
                            buffer1.append("WEEK:  " + res1.getString(0) + "\n");
                            buffer1.append("Age_wk:  " + res1.getString(1) + "\n");
                            buffer1.append("Grammes_per_bird:  " + res1.getString(2) + "\n");
                            buffer1.append("Per_birds_kg:  " + res1.getString(3) + "\n");
                            buffer1.append("Daily_consumption:  " + res1.getString(4) + "\n");
                            buffer1.append("Weekly_consumption:  " + res1.getString(5) + "\n\n");

                        }
                        //show all data
                        showMessage("Saved Data",buffer1.toString());
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
                Intent intent = new Intent(FeedingActivity.this,ActualConsumptionActivity.class);
                startActivity(intent);
            }
        });
        builder.show();
    }


    public void BroilEst(View view) {
        intent= new Intent(FeedingActivity.this, FeedingActivity1.class);
        intent.putExtra("fragment_index_key", 0);
        startActivity(intent);
    }
    public void feedpro(View view){
        intent = new Intent(FeedingActivity.this,FeedingActivity1.class);
        intent.putExtra("fragment_index_key", 2);
        startActivity(intent);
    }

    public void wght(View view){
        intent = new Intent(FeedingActivity.this,FeedingActivity1.class);
        intent.putExtra("fragment_index_key", 4);
        startActivity(intent);
    }
    public void LayerEst(View view){
        intent = new Intent(FeedingActivity.this,FeedingActivity1.class);
        intent.putExtra("fragment_index_key", 1);
        startActivity(intent);
    }
    public void nutri(View view){
        intent = new Intent(FeedingActivity.this,FeedingActivity1.class);
        intent.putExtra("fragment_index_key", 3);
        startActivity(intent);
    }

  }

