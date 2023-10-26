package com.example.android.poultry_manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by david adama on 9/4/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG = "DatabaseHelper";
    private static final int DATABASE_VERSION= 16;
    public static final String DATABASE_NAME="Poultrymanger.db";

    public static final String TABLE_NAME="Farmsize_table";
    public static final String TABLE_NAME0="ActualConsumption_table";
    public static final String TABLE_NAME1="Egg_Records_table";
    public static final String TABLE_NAME2="Mortality_Records_table";
    public static final String TABLE_NAME3="Sickness_Records_table";
    public static final String TABLE_NAME4="Marketing_Records_table";


    public static final String col_1_KEY="ID";
    public static final String col_2="number_of_birds";
    public static final String col_3="source";
    public static final String col_4="price";
    public static final String col_5="total_amount_paid";
    public static final String col_6="date_purchased";

    public static final String col_7_KEY="WEEK";
    public static final String col_8="Age_wk";
    public static final String col_9="Grammes_per_bird";
    public static final String col_10="Per_birds_kg";
    public static final String col_111="Daily_consumption";
    public static final String col_122="Weekly_consumption";

    public static final String col_11_KEY="DAY";
    public static final String col_12="Date";
    public static final String col_13="Month_in_Lay";
    public static final String col_14="first_collection";
    public static final String col_15="second_collection";
    public static final String col_16="third_collection";
    public static final String col_17="other_collection";
    public static final String col_18="total";

    public static final String col_19_KEY="ID";
    public static final String col_20="DATE";
    public static final String col_21="Age_of_Birds";
    public static final String col_22="Number_of_deaths";
    public static final String col_23="Number_of_deaths_caused_by_disease";
    public static final String col_24="Number_of_deaths_due_to_culling";
    public static final String col_25="Number_of_Birds_Left";

    public static final String col_25_KEY="ID";
    public static final String col_26="dATE";
    public static final String col_27="Age_of_birds";
    public static final String col_28="Possible_cause_of_sickness";
    public static final String col_29="Number_sick";

    public static final String col_29_KEY="ID";
    public static final String col_30="Sale_or_Purchase";
    public static final String col_31="DATe";
    public static final String col_32="Item_or_commodity_sold_or_Bought";
    public static final String col_33="Number_of_item_sold_or_bought";
    public static final String col_34="Price_of_item_sold_or_bought";
    public static final String col_35="Total_amount_of_sale_or_purchase";
    public static final String col_36="Brand";
    public static final String col_37="Purchase_Source";

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME , null,DATABASE_VERSION);
    }
    @Override
    //table creation for farm size happens here
    //table creation for Actual consumption happens here
    //table creation for Egg Records happens here
    //table creation for Mortality Records happens here
    public void onCreate(SQLiteDatabase db) {

       db.execSQL("create table " + TABLE_NAME+ "('ID' INTEGER PRIMARY KEY AUTOINCREMENT,'number_of_birds' INTEGER,'source' TEXT,'price' INTEGER, 'total_amount_paid' INTEGER,'date_purchased' TEXT)");
       db.execSQL("create table " + TABLE_NAME0+ "('WEEK' INTEGER PRIMARY KEY AUTOINCREMENT,'Age_wk' INTEGER,'Grammes_per_bird' INTEGER,'Per_birds_kg' INTEGER,'Daily_consumption' INTEGER,'Weekly_consumption' INTEGER)");
        db.execSQL("create table " + TABLE_NAME1+"('DAY' INTEGER PRIMARY KEY AUTOINCREMENT, 'Date' INTEGER,'Month_in_Lay' INTEGER,'first_collection' INTEGER,'second_collection' INTEGER,'third_collection'INTEGER,'other_collection'INTEGER,'total' INTEGER )");
        db.execSQL("create table " + TABLE_NAME2+"('ID' INTEGER PRIMARY KEY AUTOINCREMENT,'DATE' INTEGER, 'Age_of_Birds' INTEGER,'Number_of_deaths' INTEGER,'Number_of_deaths_caused_by_disease' INTEGER,'Number_of_deaths_due_to_culling' INTEGER,'Number_of_Birds_Left'INTEGER)");
        db.execSQL("create table " + TABLE_NAME3+"('ID' INTEGER PRIMARY KEY AUTOINCREMENT,'dATE' INTEGER, 'Age_of_birds' INTEGER,'Possible_cause_of_sickness' INTEGER,'Number_sick' INTEGER)");
        db.execSQL("create table " + TABLE_NAME4+"('ID' INTEGER PRIMARY KEY AUTOINCREMENT,'Sale_or_Purchase' INTEGER, 'DATe' INTEGER,'Item_or_commodity_sold_or_Bought' INTEGER,'Number_of_item_sold_or_bought' INTEGER,'Price_of_item_sold_or_bought' INTEGER,'Total_amount_of_sale_or_purchase' INTEGER,'Brand' INTEGER,'Purchase_Source' INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME0 );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1 );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2 );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3 );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME4 );

        onCreate(db);
    }
    //function called to view all data from Egg records table
    public boolean insertData1(String Date,String Month_in_Lay, String first_collection, String second_collection, String third_collection,String other_collection, String total){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_12,Date);
        contentValues.put(col_13,Month_in_Lay);
        contentValues.put(col_14,first_collection);
        contentValues.put(col_15,second_collection);
        contentValues.put(col_16,third_collection);
        contentValues.put(col_17,other_collection);
        contentValues.put(col_18,total);

        long result = db.insert(TABLE_NAME1, null,contentValues);
        if(result == -1) {
            return false;
        }
        else
            return true;}
    //function called to insert data in mortality record table

    public boolean insertData3(String Date,String Age_of_Birds , String Number_of_death, String Deaths_by_disease, String Death_due_to_culling, String Number_of_Birds_Left) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_20,  Date);
        contentValues.put(col_21, Age_of_Birds);
        contentValues.put(col_22, Number_of_death);
        contentValues.put(col_23, Deaths_by_disease);
        contentValues.put(col_24, Death_due_to_culling);
        contentValues.put(col_25, Number_of_Birds_Left);

        long result = db.insert(TABLE_NAME2, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    //function called to insert data in sickness records table

    public boolean insertData4(String Date,String Age_of_birds , String Possible_cause_of_sickness , String Number_sick) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_26, Date);
        contentValues.put(col_27, Age_of_birds);
        contentValues.put(col_28, Possible_cause_of_sickness);
        contentValues.put(col_29, Number_sick);

        long result = db.insert(TABLE_NAME3, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    //function called to insert data in Marketing records table
    public boolean insertData5(String Sale_or_Purchase ,String DATe, String Item_or_commodity_sold_or_bought,String Number_of_items_sold_or_purchased,
                               String Price_per_item_sold_or_bought, String Total_amount_of_sale_or_purchase,
                               String Brand, String Purchase_Source) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_30, Sale_or_Purchase);
        contentValues.put(col_31, DATe);
        contentValues.put(col_32, Item_or_commodity_sold_or_bought);
        contentValues.put(col_33, Number_of_items_sold_or_purchased);
        contentValues.put(col_34, Price_per_item_sold_or_bought);
        contentValues.put(col_35, Total_amount_of_sale_or_purchase);
        contentValues.put(col_36, Brand);
        contentValues.put(col_37, Purchase_Source);

        long result = db.insert(TABLE_NAME4, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }


//function called to view all data from actual consumption table

    public boolean insertData0(String Age_wk, String Grammes_per_bird, String Per_birds_kg,String Daily_consumption,String Weekly_consumption)
                             {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_8,Age_wk);
        contentValues.put(col_9,Grammes_per_bird);
        contentValues.put(col_10,Per_birds_kg);
        contentValues.put(col_111,Daily_consumption);
        contentValues.put(col_122,Weekly_consumption);


        long result = db.insert(TABLE_NAME0, null,contentValues);
        if(result == -1) {
            return false;
        }
        else
            return true;}
    //function called to insert data in farm size table

    public boolean insertData(String number_of_birds, String source, String price, String total_amount_paid, String date_purchased){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2,number_of_birds);
        contentValues.put(col_3,source);
        contentValues.put(col_4,price);
        contentValues.put(col_5,total_amount_paid);
        contentValues.put(col_6,date_purchased);
        long result = db.insert(TABLE_NAME, null,contentValues);
        if(result == -1)
            return false;

        else
            return true;
    }
    //method called to view all data from farm size table
    public Cursor getAllData(){
         SQLiteDatabase db = this.getWritableDatabase();
         Cursor res = db.rawQuery("select * from " + TABLE_NAME,null);
        return res;
    }
    //method called to view all data from actual consumption table
    public Cursor getAllData1(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res1 = db.rawQuery("select * from " + TABLE_NAME0,null);
        return res1;
    }
    //method called to view all data from Egg records table
    public Cursor getAllData2(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res2 = db.rawQuery("select * from " + TABLE_NAME1,null);
        return res2;
    }
    //method called to view all data from Mortality records table
    public Cursor getAllData3(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res3 = db.rawQuery("select * from " + TABLE_NAME2,null);
        return res3;
    }
    //method called to view all data from sickness records table
    public Cursor getAllData4(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res4 = db.rawQuery("select * from " + TABLE_NAME3,null);
        return res4;
    }
    //method called to view all data from Marketing records table
    public Cursor getAllData5(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res5 = db.rawQuery("select * from " + TABLE_NAME4,null);
        return res5;
    }


    //function called to update data in farm size table
    public  boolean updateData(String id,String number_of_birds,String source,String price,String total_amount_paid,String date_purchased){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1_KEY,id);
        contentValues.put(col_2,number_of_birds);
        contentValues.put(col_3,source);
        contentValues.put(col_4,price);
        contentValues.put(col_5,total_amount_paid);
        contentValues.put(col_6,date_purchased);

        db.update(TABLE_NAME, contentValues,"ID = ?",new String[]{id});
           return true;

    }
//function called to update data in actual consumption table

    public boolean updateData1(String week, String Age_wk, String Grammes_per_bird, String Per_birds_kg, String Daily_consumption,String Weekly_consumption){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(col_7_KEY,week);
        contentValues1.put(col_8,Age_wk);
        contentValues1.put(col_9,Grammes_per_bird);
        contentValues1.put(col_10,Per_birds_kg);
        contentValues1.put(col_111,Daily_consumption);
        contentValues1.put(col_122,Weekly_consumption);

        db.update(TABLE_NAME0,  contentValues1, "WEEK = ?", new String[]{week});
        return true;
    }
    //function called to update data in Egg Records table
    public boolean updateData2(String Day, String Date,String Month_in_Lay, String first_collection, String second_collection, String third_collection,String other_collection, String total){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(col_11_KEY,Day);
        contentValues2.put(col_12,Date);
        contentValues2.put(col_13,Month_in_Lay);
        contentValues2.put(col_14,first_collection);
        contentValues2.put(col_15,second_collection);
        contentValues2.put(col_16,third_collection);
        contentValues2.put(col_17,other_collection);
        contentValues2.put(col_18,total);

        db.update(TABLE_NAME1,  contentValues2, "DAY = ?", new String[]{Day});
        return true;
    }
    //function called to update data in Mortality table
    public  boolean updateData3(String Id,String Date,String Age_of_Birds , String Number_of_deaths, String Deaths_by_disease, String Death_due_to_culling, String Number_of_Birds_Left){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_19_KEY,Id);
        contentValues.put(col_20,Date);
        contentValues.put(col_21,Age_of_Birds);
        contentValues.put(col_22,Number_of_deaths);
        contentValues.put(col_23,Deaths_by_disease);
        contentValues.put(col_24,Death_due_to_culling);
        contentValues.put(col_25,Number_of_Birds_Left);

        db.update(TABLE_NAME2, contentValues,"ID = ?",new String[]{Id});
        return true;

    }
    //function called to update data in sickness records table
    public  boolean updateData4(String Id,String dATE, String Age_of_birds , String Possible_cause_of_sickness , String Number_sick){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_25_KEY,Id);
        contentValues.put(col_26,dATE);
        contentValues.put(col_27,Age_of_birds);
        contentValues.put(col_28,Possible_cause_of_sickness);
        contentValues.put(col_29,Number_sick);

        db.update(TABLE_NAME3, contentValues,"ID= ?",new String[]{Id});
        return true;

    }
    //function called to update data in Marketing records table
    public  boolean updateData5(String ID,String DATe, String Sale_or_Purchase , String Item_or_commodity_sold_or_bought,String Number_of_items_sold_or_purchased,
                                String Price_per_item_sold_or_bought, String Total_amount_of_sale_or_purchase,
                                String Brand, String Purchase_Source){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_29_KEY,ID);
        contentValues.put(col_30,DATe);
        contentValues.put(col_31,Sale_or_Purchase);
        contentValues.put(col_32,Item_or_commodity_sold_or_bought);
        contentValues.put(col_33,Number_of_items_sold_or_purchased);
        contentValues.put(col_34,Price_per_item_sold_or_bought);
        contentValues.put(col_35,Total_amount_of_sale_or_purchase);
        contentValues.put(col_36,Brand);
        contentValues.put(col_37,Purchase_Source);


        db.update(TABLE_NAME4, contentValues,"ID = ?",new String[]{ID});
        return true;

    }


}




