package com.example.informationsystem;

public class Users {

	private String uname;
	private String upword;
	private int uid;
	private String utype;
	
	Users (int uid,String uname, String upword, String uType) {
		this.uid=uid;
		this.uname=uname;
		this.upword=upword;;
		this.utype=uType;
		
		
	} 

	Users () {
		// default constroocutor
	}

	public String getUname() {
		return uname;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUtype() {
		return utype;
	}
	public void setUtype(String utype) {
		this.utype = utype;
	}
	public void setUname(String uname) {
		
		this.uname = uname;
	}
	public String getUpword() {
		return upword;
	}
	public void setUpword(String upword) {
		this.upword = upword;
	}

}
