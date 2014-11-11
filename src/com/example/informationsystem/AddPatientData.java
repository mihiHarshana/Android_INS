package com.example.informationsystem;

import android.support.v7.app.ActionBarActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddPatientData extends ActionBarActivity {
	 public static final String PREFS_NAME = "MyPrefsFile";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_patient_data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_patient_data, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}  
	
	public void SaveData (View view) {
	      // SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
//	       boolean silent = settings.getBoolean("silentMode", false);
//	       setSient(silent);
//	       
     	EditText txtUName = (EditText) findViewById(R.id.txtPName);
     	String UName = txtUName.getText().toString();
     	
     	EditText txtPAddress = (EditText) findViewById(R.id.txtPAddress);
     	String PAddress = txtPAddress.getText().toString();
	       SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
	       
	       String value = settings.getString(PREFS_NAME, UName);
	       
	       Toast.makeText(this, value, Toast.LENGTH_LONG).show();
	       
	       SharedPreferences.Editor editor = settings.edit();
	       editor.putString("PatientName", UName);
	       editor.putString("PatientAddress", "Wattala");
	       editor.commit();
	       
	      // String retAddress= settings.getString(PREFS_NAME, "PatientAddress");
	     

	         
		  
	}
	
	public void GetData(View view) {
		
		
		EditText txtUName = (EditText) findViewById(R.id.txtPName);
		
		 SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
	     String  silent = settings.getString("PatientAddress", "PatientAddress");
	     Toast.makeText(this, "address returned is " + silent , Toast.LENGTH_LONG).show();
	}
}
 