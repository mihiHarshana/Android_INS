package com.example.informationsystem;

public class Patients {
	private int id;
	private String patientName;
	private String patientAddress;
	private String satulation;
	private String description;
	private String medications;
	private String patientId;

	
	
	public String getSatulation() {
		return satulation;
	}
	public void setSatulation(String satulation) {
		this.satulation = satulation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMedications() {
		return medications;
	}
	public void setMedications(String medications) {
		this.medications = medications;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
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
