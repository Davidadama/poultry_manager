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

public class MarketingRecordsActivity extends AppCompatActivity {
    DatabaseHelper myDB5;
    EditText editID, editdAte, editsale_purchase, edititem_sold_bought,
            editnumber_of_item_sold_or_bought, editprice_per, edittotal_sale, editbrand, editpurchase_source;

    Button btnSave5;
    Button btnUpdate5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketing_records);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
                myDB5 = new DatabaseHelper(this);

        //casting happens here
        editID = (EditText) findViewById(R.id.idn);
        editdAte = (EditText) findViewById(R.id.mark_date);
        editsale_purchase = (EditText) findViewById(R.id.sale_purchase);
        edititem_sold_bought = (EditText) findViewById(R.id.item_sold);
        editnumber_of_item_sold_or_bought = (EditText) findViewById(R.id.num_sold);
        editprice_per = (EditText) findViewById(R.id.price_per);
        edittotal_sale = (EditText) findViewById(R.id.total_purch);
        editbrand = (EditText) findViewById(R.id.brand);
        editpurchase_source = (EditText) findViewById(R.id.source_purch);
        btnSave5 = (Button) findViewById(R.id.savebtn5);
        btnUpdate5 = (Button) findViewById(R.id.updatebtn5);

        Save5();
        UpdateData5();
      }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    //function called to save user input
    public void Save5() {
        btnSave5.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDB5.insertData5(editsale_purchase.getText().toString(),
                                editdAte.getText().toString(),
                                edititem_sold_bought.getText().toString(),
                                editnumber_of_item_sold_or_bought.getText().toString(),
                                editprice_per.getText().toString(),
                                edittotal_sale.getText().toString(),
                                editbrand.getText().toString(),
                                editpurchase_source.getText().toString());

                        if (isInserted != true) {
                            Toast.makeText(MarketingRecordsActivity.this, "Your input has not been saved", Toast.LENGTH_LONG).show();
                        }
                        if(edititem_sold_bought.getText().length()==0){
                            showMessage("Error","Please enter item sold or bought");
                        }
                        if(editdAte.getText().length()==0){
                            showMessage("Error","Please enter date");
                        }
                        if(editsale_purchase.getText().length()==0){
                            showMessage("Error","Please enter sale or purchase");
                        }


                        else {
                            Toast.makeText(MarketingRecordsActivity.this, "Your input has been saved", Toast.LENGTH_LONG).show();
                        }

                    }

                }
        );
    }
    public void showMessage (String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    //function called to update data in Marketing records table
    public void UpdateData5(){
        btnUpdate5.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        boolean isUpdated = myDB5.updateData5(
                                editID.getText().toString(),
                                editsale_purchase.getText().toString(),
                                editdAte.getText().toString(),
                                edititem_sold_bought.getText().toString(),
                                editnumber_of_item_sold_or_bought.getText().toString(),
                                editprice_per.getText().toString(),
                                edittotal_sale.getText().toString(),
                                editbrand.getText().toString(),
                                editpurchase_source.getText().toString());
                        if(editID.getText().length()==0){
                            showMessage("Error","Please enter the ID of the record you want to update");
                            return;
                        }
                        Cursor res5 =  myDB5.getAllData5();
                        if(res5.getCount() < Double.parseDouble(((EditText)findViewById(R.id.idn))
                                .getText().toString())) {
                            showMessage("Error","No such ID exists");
                            return;
                        }
                        if(isUpdated == true){
                            Toast.makeText(MarketingRecordsActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                        }  else {
                            Toast.makeText(MarketingRecordsActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );

    }


}
