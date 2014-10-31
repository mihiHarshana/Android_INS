package com.example.informationsystem;

public class Patients {
	private int id;
	private String patientName;
	private String patientAddress;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getPatientAddress() {
		return patientAddress;
	}
	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}
	
	public Patients (int id, String name, String address) {
		this.id=id;
		this.patientName= name;
		this.patientAddress = address;
		
	}
	public Patients() {
		
		
	}

}
