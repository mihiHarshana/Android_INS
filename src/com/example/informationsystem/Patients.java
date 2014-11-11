package com.example.informationsystem;

public class Patients {
	
	private String patientName;
	private String patientAddress;
	private int pid;
	private String description;
	private String medications;
	private String patientId;
	private String pAge;
	
	
	
	public String getpAge() {
		return pAge;
	}
	public void setpAge(String pAge) {
		this.pAge = pAge;
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
		return pid;
	}
	public void setId(String pid) {
		this.pid = Integer.parseInt(pid);
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
	
	public Patients (int pid ,String name, String address, String patientID,String Description , String Medication ,String age) {
		this.pid=pid;
		this.patientName= name;
		this.patientAddress = address;
		this.description = Description;
		this.medications = Medication;
		this.pAge = age;
		this.patientId=patientID;
		
	}
	public Patients() {
		
		
	}

}
