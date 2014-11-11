package com.example.informationsystem;

import android.text.TextUtils;

public class UtilityClass {
	
	public void createUser (String name, String address, String salutation, String Description, String medication) throws Exception {
		
		if (name.isEmpty() ) throw new Exception("Empty Name");
		
		
	}
	
	public void createPatient(String id, String name, String address, String medication, String age, String description ) throws Exception{

		if (id.isEmpty() ) { throw new Exception("Patient ID cannot be empty");  }
		if (!TextUtils.isDigitsOnly(id))    { throw new Exception("Patient ID Cannot be string"); }
		if (name.isEmpty() ) { throw new Exception("Patient name cannot be empty"); }
		if (address.isEmpty()) { throw new Exception("Patient adreess cannot be empty");}
		if (medication.isEmpty()) {throw new Exception("Patient medication cannot be empty");}
		if (description.isEmpty()) {throw new Exception("Description cannot be empty");}
		if (age.isEmpty()) {throw new Exception("Patient age cannot be empty");}
		
		
	} 
	
	
	

}
