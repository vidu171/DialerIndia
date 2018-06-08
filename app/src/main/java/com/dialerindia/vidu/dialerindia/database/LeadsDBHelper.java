package com.dialerindia.vidu.dialerindia.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.dialerindia.vidu.dialerindia.Constants;
import com.dialerindia.vidu.dialerindia.classes.Leads;

import java.util.ArrayList;

public class LeadsDBHelper extends SQLiteOpenHelper {

    private static final int DATATBASE_VERSION = 1;
    private static final String DATABASE_NAME= "LEADS";
    private static final String TABLE_NAME = "Leads";
    private static final String COLOUMN_ID = "id";
    private static final String COLOUMN_NAME = "Name";
    private static final String COLOUMN_CONTACT1 = "contact1";
    private static final String COLOUMN_CONTACT2 = "contact2";
    private static final String COLOUMN_EMAIL = "email";
    private static final String COLOUMN_ADDRESS = "address";
    private static final String COLOUMN_CITY = "city";
    private static final String COLOUMN_GROUP = "_group";
    private static final String COLOUMN_PENDING = "pending";
    private static final String COLOUMN_MISSED = "missed";

    Constants constants= new Constants();

    public LeadsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATATBASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+COLOUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ COLOUMN_NAME+
                " TEXT,"+COLOUMN_CONTACT1+" TEXT,"+COLOUMN_CONTACT2+" TEXT,"+COLOUMN_EMAIL+ " TEXT,"+COLOUMN_ADDRESS+" TEXT,"+COLOUMN_CITY+" TEXT," +
                COLOUMN_GROUP + " TEXT," +COLOUMN_PENDING + " INTEGER, " + COLOUMN_MISSED +"  INTEGER"+")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void AddData(Leads Data){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLOUMN_NAME, Data.Name.trim());
        values.put(COLOUMN_CONTACT1, Data.Contact1.trim());
        values.put(COLOUMN_CONTACT2, Data.Contact2.trim());
        values.put(COLOUMN_ADDRESS, Data.Address.trim());
        values.put(COLOUMN_EMAIL, Data.Email.trim());
        values.put(COLOUMN_CITY, Data.City.trim());
        values.put(COLOUMN_GROUP, Data.Group.trim());
        values.put(COLOUMN_PENDING, 1);
        values.put(COLOUMN_MISSED, 0);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }



    public Leads getFirstUncalledLead(){
        Leads firstUncalledcLead = null;
        String select_query = "SELECT *FROM "+TABLE_NAME;
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);
        if(cursor.moveToFirst()){
            do {
                Log.w("Datbase", cursor.getInt(cursor.getColumnIndex(COLOUMN_PENDING))+"");
                if(cursor.getInt(cursor.getColumnIndex(COLOUMN_PENDING))==1){

                    String name = cursor.getString(cursor.getColumnIndex(COLOUMN_NAME));
                    String Contact1 = cursor.getString(cursor.getColumnIndex(COLOUMN_CONTACT1));
                    String Contact2 = cursor.getString(cursor.getColumnIndex(COLOUMN_CONTACT2));
                    String Email = cursor.getString(cursor.getColumnIndex(COLOUMN_EMAIL));
                    String Address = cursor.getString(cursor.getColumnIndex(COLOUMN_ADDRESS));
                    String City = cursor.getString(cursor.getColumnIndex(COLOUMN_CITY));
                    String Group = cursor.getString(cursor.getColumnIndex(COLOUMN_GROUP));
                    firstUncalledcLead = new Leads(name,Contact1,Contact2,Email,Address,City,Group);
                    firstUncalledcLead.id = cursor.getInt(cursor.getColumnIndex(COLOUMN_ID));
                    return  firstUncalledcLead;
                }

            }
            while (cursor.moveToNext());
        }
        return null;
    }

    public void setCalledState(int id){
        String select_query = "UPDATE "+TABLE_NAME +" SET "+COLOUMN_PENDING+" = 0 "+" WHERE "+COLOUMN_ID+" = "+id;
        SQLiteDatabase db  = this.getReadableDatabase();
        db.execSQL(select_query);
        db.close();
    }

    public void setMissedState(int id){
        String select_query = "UPDATE "+TABLE_NAME +" SET "+COLOUMN_MISSED+" = 1 "+" WHERE "+COLOUMN_ID+" = "+ id;
        SQLiteDatabase db  = this.getReadableDatabase();
        db.execSQL(select_query);
        db.close();
    }

    public ArrayList<Leads> getLeadsFromSQL(int type) {
        ArrayList<Leads> myLeadsList = new ArrayList<>();
        String select_query = "SELECT *FROM "+TABLE_NAME;
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);
        if(cursor.moveToFirst()){
            do {
                String name = cursor.getString(cursor.getColumnIndex(COLOUMN_NAME));
                String Contact1 = cursor.getString(cursor.getColumnIndex(COLOUMN_CONTACT1));
                String Contact2 = cursor.getString(cursor.getColumnIndex(COLOUMN_CONTACT2));
                String Email = cursor.getString(cursor.getColumnIndex(COLOUMN_EMAIL));
                String Address = cursor.getString(cursor.getColumnIndex(COLOUMN_ADDRESS));
                String City = cursor.getString(cursor.getColumnIndex(COLOUMN_CITY));
                String Group = cursor.getString(cursor.getColumnIndex(COLOUMN_GROUP));
                Leads createLead = new Leads(name,Contact1,Contact2,Email,Address,City,Group);
                createLead.setPending(cursor.getInt(cursor.getColumnIndex(COLOUMN_PENDING))==1);
                createLead.setMissed(cursor.getInt(cursor.getColumnIndex(COLOUMN_MISSED))==1);
                createLead.id = cursor.getInt(cursor.getColumnIndex(COLOUMN_ID));

                if(type == constants.LEADS_ANSWERED && !createLead.Pending && !createLead.Missed){
                    myLeadsList.add(createLead);
                }
                else if(type == constants.LEADS_NOT_ANSWERED && createLead.Missed  && !createLead.Pending){
                    myLeadsList.add(createLead);
                }
                else if(type== constants.LEADS_PENDING && createLead.Pending) {
                    myLeadsList.add(createLead);
                }
                else if(type == constants.LEADS_ALL){
                    myLeadsList.add(createLead);
                }
            }
            while (cursor.moveToNext());
        }

        db.close();
        return myLeadsList;
    }

    public boolean checkPending(){
        Leads firstUncalledcLead = null;
        String select_query = "SELECT *FROM "+TABLE_NAME;
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);
        if(cursor.moveToFirst()){
            do {
                Log.w("Datbase", cursor.getInt(cursor.getColumnIndex(COLOUMN_PENDING))+"");
                if(cursor.getInt(cursor.getColumnIndex(COLOUMN_PENDING))==1){
                    return  true;
                }

            }
            while (cursor.moveToNext());
        }
        return false;
    }
}
