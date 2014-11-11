package com.example.informationsystem;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PatientView extends ActionBarActivity {
	
//	EditText patientID = (EditText) findViewById(R.id.txtPatientId);
//	EditText patientName = (EditText) findViewById(R.id.txtPatientName);
//	EditText PatientAddress = (EditText) findViewById(R.id.txtPatientAddress);
//	EditText PatientAge = (EditText) findViewById(R.id.txtPatientAge);
//	EditText patientDescription = (EditText) findViewById(R.id.txtPatientDescription);
//	EditText PatientMedication = (EditText) findViewById(R.id.txtPatientMedication);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_view);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patient_view, menu);
		
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
		if (id == R.id.mnuAddUsers) {
			Intent intent  = new Intent (this, AddUser.class);
		//	intent.putExtra("LogedinUser", txtUName.getText().toString());
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void AddPatientData(View view) {
		UtilityClass uc1 = new UtilityClass();
		
		
		EditText patientID = (EditText) findViewById(R.id.txtPatId);
		EditText patientName = (EditText) findViewById(R.id.txtPatientName);
		EditText PatientAddress = (EditText) findViewById(R.id.txtPatientAddress);
		EditText PatientAge = (EditText) findViewById(R.id.txtPatientAge);
		EditText patientDescription = (EditText) findViewById(R.id.txtPatientDescription);
		EditText PatientMedication = (EditText) findViewById(R.id.txtPatientMedication);

		
		DatabaseHandler db = new DatabaseHandler(this);

		try {
			uc1.createPatient(patientID.getText().toString(), patientName.getText().toString(), PatientAddress.getText().toString(), 
			PatientMedication.getText().toString(), PatientAge.getText().toString(), patientDescription.getText().toString());
			
			
			Patients p1 = new Patients();
			p1.setId(db.getContactCount());
			p1.setPatientId(patientID.getText().toString());
			p1.setPatientName(patientName.getText().toString());
			p1.setPatientAddress(PatientAddress.getText().toString());
			p1.setpAge(PatientAge.getText().toString());
			p1.setDescription(patientDescription.getText().toString());
			p1.setMedications(PatientMedication.getText().toString());
			
			db.addPatientData(p1);
			Toast.makeText(this, "Patient data added successfully" , Toast.LENGTH_SHORT).show();
			
			
		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
			return ;
		}
		

		this.clearDATA(view);
		
	}
	
	public void SearchPatient(View view) {
		EditText patientName = (EditText) findViewById(R.id.txtPatientName);
		
		DatabaseHandler db = new DatabaseHandler(this);
		Patients p1 = new Patients();
		p1.setPatientName(patientName.getText().toString());
		Patients patient =db.getPatientByName(p1.getPatientName());
		
		if (patient == null) {
			Toast.makeText(this, "Patient not available", Toast.LENGTH_LONG).show();
			this.clearDATA(view);
			return ;
		}
		
		EditText patientID = (EditText) findViewById(R.id.txtPatId);
		EditText PatientAddress = (EditText) findViewById(R.id.txtPatientAddress);
		EditText PatientAge = (EditText) findViewById(R.id.txtPatientAge);
		EditText patientDescription = (EditText) findViewById(R.id.txtPatientDescription);
		EditText PatientMedication = (EditText) findViewById(R.id.txtPatientMedication);
		
		
		Toast.makeText(this, patient.getPatientName().toString() , Toast.LENGTH_SHORT).show();
		patientName.setText(patient.getPatientName().toString());
		PatientAddress.setText(patient.getPatientAddress().toString());
		patientID.setText(patient.getPatientAddress().toString());
		PatientAge.setText(patient.getpAge().toString());
		
		patientDescription.setText(patient.getDescription());
		PatientMedication.setText(patient.getMedications());
		
	}
	
	public void ClearData(View view) {

		this.clearDATA(view);
		
	}
	
	public void clearDATA (View view) {
		EditText patientID = (EditText) findViewById(R.id.txtPatId);
		EditText patientName = (EditText) findViewById(R.id.txtPatientName);
		EditText PatientAddress = (EditText) findViewById(R.id.txtPatientAddress);
		EditText PatientAge = (EditText) findViewById(R.id.txtPatientAge);
		EditText patientDescription = (EditText) findViewById(R.id.txtPatientDescription);
		EditText PatientMedication = (EditText) findViewById(R.id.txtPatientMedication);
		
		
		patientID.setText("");
		patientName.setText("");
		PatientAddress.setText("");
		PatientAge.setText("");
		patientDescription.setText("");
		PatientMedication.setText("");
		
	}
	
	public void Close() {
		finish();
	}
}
