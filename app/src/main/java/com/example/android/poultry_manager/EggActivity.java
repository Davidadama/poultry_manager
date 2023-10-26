package com.example.android.poultry_manager;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class EggActivity extends AppCompatActivity {
    DatabaseHelper myDB2;
    Button btnViewall2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egg);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        myDB2 = new DatabaseHelper(this);
        btnViewall2=(Button)findViewById(R.id.viewbtn2);

        ViewAll2();
    }
    public  void ViewAll2(){
        btnViewall2.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Cursor res2 =  myDB2.getAllData2();
                        if(res2.getCount()== 0){
                            //show message
                            showMessage("Error","No Entries yet");
                            return;
                        }
                        StringBuffer buffer2 = new StringBuffer();
                        while (res2.moveToNext()){
                            buffer2.append("DAY:  " + res2.getString(0) + "\n\n");
                            buffer2.append("Date:  " + res2.getString(1) + "\n\n");
                            buffer2.append("Month_in_Lay:  " + res2.getString(2) + "\n\n");
                            buffer2.append("first_collection:  " + res2.getString(3) + "\n\n");
                            buffer2.append("second_collection:  " + res2.getString(4) + "\n\n");
                            buffer2.append("third_collection:  " + res2.getString(5) + "\n\n");
                            buffer2.append("other_collection:  " + res2.getString(6) + "\n\n");
                            buffer2.append("total:  " + res2.getString(7) + "\n\n\n");
                        }
                        //show all data
                        showMessage("Saved Data",buffer2.toString());
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
                Intent intent = new Intent(EggActivity.this,EggRecordActivity.class);
                startActivity(intent);
            }
        });
        builder.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
    public void laying_perf(View view) {
        Intent intent = new Intent(this,EgggradingActivity1.class);
        intent.putExtra("fragment_index_key", 0);
        startActivity(intent);
    }
    public void Egg_size(View view) {
        Intent intent = new Intent(this,EgggradingActivity1.class);
        intent.putExtra("fragment_index_key", 1);
        startActivity(intent);
    }



}
