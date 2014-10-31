package com.example.informationsystem;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION =1;
	private static final String DATABASE_NAME = "INS";
	private static final String TABLE_PATIENT_DATA = "patient_data";
	
	private static final String KEY_ID ="id";
	private static final String KEY_NAME ="name";
	private static final String KEY_ADDRESS ="address";
	
	public DatabaseHandler(Context context) {
		super (context, DATABASE_NAME, null,DATABASE_VERSION);
	}
	
	public void onCreate(SQLiteDatabase db) {
		String CREATE_PATIENT_DATA = "CREATE TABLE " + TABLE_PATIENT_DATA + "("
				+ KEY_ID + " INTEGER PRIMARY KEY,"
				+ KEY_NAME + " TEXT," 
				+ KEY_ADDRESS + " TEXT" + ")";
						
		db.execSQL(CREATE_PATIENT_DATA);
		
	}
	
	// ALL CRUD (CREATE, READ , UPDATE , DELETE ) Operations
	
	void addPatientData(Patients patient) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values= new ContentValues();
		values.put(KEY_NAME, patient.getPatientName());
		values.put(KEY_ADDRESS, patient.getPatientAddress());
		
		db.insert(TABLE_PATIENT_DATA, null, values);
		db.close();
		
	}
	
	Patients getPatient(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_PATIENT_DATA, new String[] {KEY_ID , KEY_NAME , KEY_ADDRESS}, KEY_ID + "=?" ,
				new String [] {String.valueOf(id) } , null,null,null);
		
		if (cursor != null) 
			cursor.moveToFirst();
		Patients patient = new Patients(Integer.parseInt(cursor.getString(0)), cursor.getString(1) , cursor.getString(2) );
		
		return patient;	
	}
	
	
	public int updatePatient(Patients patient) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, patient.getPatientName());
		values.put(KEY_ADDRESS, patient.getPatientAddress());
		
		return db.update(TABLE_PATIENT_DATA, values, KEY_ID + "= ?", new String [] { String.valueOf(patient.getId()) });
		
	}
	public void deletePatient (Patients patient) {
		
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_PATIENT_DATA, KEY_ID + " = ?" , new String [] {String.valueOf(patient.getId() )});
		db.close();
	}
	
	public int getContactCount () {
		String countQuery = "SELECT * from " + TABLE_PATIENT_DATA;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();
		
		return cursor.getCount();
		
	}
	
	public List<Patients> getAllContacts() {
        List<Patients> patientList = new ArrayList<Patients>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PATIENT_DATA;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Patients patient = new Patients();
                patient.setId(Integer.parseInt(cursor.getString(0)));
                patient.setPatientName(cursor.getString(1));
                patient.setPatientAddress(cursor.getColumnName(2));
                // Adding contact to list
                patientList.add(patient);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return patientList;
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT_DATA);
		onCreate(db);
		
	}
	
	
}
