package com.dialerindia.vidu.dialerindia.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        values.put(COLOUMN_NAME, Data.Name);
        values.put(COLOUMN_CONTACT1, Data.Contact1);
        values.put(COLOUMN_CONTACT2, Data.Contact2);
        values.put(COLOUMN_ADDRESS, Data.Address);
        values.put(COLOUMN_EMAIL, Data.Email);
        values.put(COLOUMN_CITY, Data.City);
        values.put(COLOUMN_GROUP, Data.Group);
        values.put(COLOUMN_PENDING, 1);
        values.put(COLOUMN_MISSED, 0);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<Leads> getAllLeadsFromSQL(){
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
                myLeadsList.add(createLead);
            }
            while (cursor.moveToNext());
        }

        db.close();
        return myLeadsList;

    }

}
