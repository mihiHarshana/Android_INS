package com.example.informationsystem;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION =1;
	private static final String DATABASE_NAME = "INS";
	
	private static final String TABLE_PATIENT_DATA = "patient_data";
	//filds for Patient data table
	private static final String KEY_ID ="id";
	private static final String KEY_NAME ="name";
	private static final String KEY_ADDRESS ="address";
	private static final String KEY_PATIENT_ID ="patientId";
	private static final String KEY_DESCRIPTION ="description";
	private static final String KEY_MEDICATION ="medication";
	private static final String KEY_AGE ="age";
	
	private static final String TABLE_USER_DATA = "user_data";
	//fieds for user table
	private static final String KEY_UID ="uId";
	private static final String KEY_UNAME = "uname";
	private static final String KEY_UPWORD = "upword";
	private static final String KEY_UTYPE = "utype";
	
	public DatabaseHandler(Context context) {
		super (context, DATABASE_NAME, null,DATABASE_VERSION);
	}
	
	public void onCreate(SQLiteDatabase db) {
		String CREATE_PATIENT_DATA = "CREATE TABLE  IF NOT EXISTS " + TABLE_PATIENT_DATA + "("
				+ KEY_ID + " INTEGER PRIMARY KEY ," 
				+ KEY_PATIENT_ID + " TEXT,"	
				+ KEY_NAME + " TEXT," 
				+ KEY_ADDRESS + " TEXT," 
				+ KEY_DESCRIPTION + " TEXT,"
				+ KEY_MEDICATION + " TEXT,"
				+ KEY_AGE + " TEXT )";
		
		String CREATE_USER_DATA = "CREATE TABLE IF NOT EXISTS " + TABLE_USER_DATA + "("
				+ KEY_UID + " INTEGER PRIMARY KEY ,"
				+ KEY_UNAME + " TEXT,"
				+ KEY_UPWORD +" TEXT,"
				+ KEY_UTYPE + " TEXT )";
				 
		db.execSQL(CREATE_PATIENT_DATA);
		db.execSQL(CREATE_USER_DATA);
		Log.d("Data base Created ", "Data base Created");
		

	//	db.close();
		
//		ContentValues values= new ContentValues();
//	//	values.put(KEY_UID, "0");
//		values.put(KEY_UNAME, "admin");
//		values.put(KEY_UPWORD, "admin"); 
//		values.put(KEY_UTYPE, "admin");
//		Log.d("User added", "Added User");
//		
	}
	
	// ALL CRUD (CREATE, READ , UPDATE , DELETE ) Operations
	
	void addPatientData(Patients patient) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values= new ContentValues();
		values.put(KEY_ID, patient.getId());
		values.put(KEY_ADDRESS, patient.getPatientAddress());
		values.put(KEY_PATIENT_ID, patient.getPatientId());
		values.put(KEY_NAME, patient.getPatientName());
		values.put(KEY_DESCRIPTION, patient.getDescription());
		values.put(KEY_MEDICATION, patient.getMedications());
		values.put(KEY_AGE, patient.getpAge());
		db.insert(TABLE_PATIENT_DATA, null, values);
	//	db.close();
		
	}
	
	Patients getPatient(int pid) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_PATIENT_DATA, new String[] {KEY_ID, KEY_NAME , KEY_ADDRESS, KEY_PATIENT_ID, KEY_DESCRIPTION, KEY_MEDICATION, KEY_AGE}, KEY_ID + "=?" ,
				new String [] { String.valueOf(pid)  } , null,null,null,null);
		
		if (cursor != null) 
			
			cursor.moveToFirst();
				Patients patient = new Patients(Integer.parseInt(String.valueOf(cursor.getString(0))), cursor.getString(1) , cursor.getString(2), cursor.getString(3), cursor.getString(4) ,cursor.getString(5) ,cursor.getString(6));
		
		return patient;	
	}
	
	Patients getPatientByName(String pName) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_PATIENT_DATA, new String[] {KEY_ID, KEY_NAME , KEY_ADDRESS, KEY_PATIENT_ID, KEY_DESCRIPTION, KEY_MEDICATION, KEY_AGE}, KEY_NAME + "=?" ,
				new String [] {String.valueOf(pName) } , null,null,null,null);
		
		
			
			if (cursor != null && cursor.getCount() != 0 ) {
				cursor.moveToFirst();
				
				Patients patient = new Patients(Integer.parseInt(cursor.getString(0)), cursor.getString(1) , cursor.getString(2), cursor.getString(3), cursor.getString(4) ,cursor.getString(5) ,cursor.getString(6));
				return patient;
			}
			else { return null;}
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
		//db.close();
	}
	
	public String getContactCount () {
	
		String countQuery = "SELECT * from " + TABLE_PATIENT_DATA;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		//cursor.close();
		
		return String.valueOf(cursor.getCount());
		
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
                patient.setPatientId(cursor.getString(0));
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
	
	
	void addUsers(Users users) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values= new ContentValues();
		values.put(KEY_UID, users.getUid());
		values.put(KEY_UNAME, users.getUname());
		values.put(KEY_UPWORD, users.getUpword());
		values.put(KEY_UTYPE, users.getUtype());
		
		db.insert(TABLE_USER_DATA, null, values);
		//db.close();
	}
	
	void addDefaultUser(String uname, String upword, String utype) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values= new ContentValues();
		values.put(KEY_UID, 0);
		values.put(KEY_UNAME, uname);
		values.put(KEY_UPWORD, upword);
		values.put(KEY_UTYPE, utype);
		
		db.insert(TABLE_USER_DATA, null, values);
	//	db.close();
	}
	
	
	
	public int getUserCount () {
		String countQuery = "SELECT * from " + TABLE_USER_DATA;
		SQLiteDatabase db1 = this.getReadableDatabase();
		Cursor cursor1 = db1.rawQuery(countQuery, null);
		//cursor1.close();
		return cursor1.getCount();
		
	}
	
	
	Users getUser(String  uname) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_USER_DATA, new String[] {KEY_UID , KEY_UNAME , KEY_UPWORD, KEY_UTYPE}, KEY_UNAME + "=?" ,
				new String [] {String.valueOf(uname) } , null,null,null,null);
		
		if (cursor != null && cursor.getCount() != 0)  {
			cursor.moveToFirst();
			Users user = new Users(Integer.parseInt(cursor.getString(0)), cursor.getString(1) , cursor.getString(2), cursor.getString(3)  );
		
		return user;	
		} else {
			
			return null;
		}
		
	}
	
	public ArrayList<String> getAllUserName() {
		ArrayList<String> ar =  new  ArrayList<String>() ;
		String countQuery = "SELECT uname from " + TABLE_USER_DATA;
		SQLiteDatabase db1 = this.getReadableDatabase();
		Cursor  cursor1 = db1.rawQuery(countQuery, null);
		
		for (int i=0; i<= cursor1.getCount() -1 ; i++){
			ar.add(cursor1.getString(0));
		}
		
		//cursor1.clos);
		return ar;
		
	}
	
}
