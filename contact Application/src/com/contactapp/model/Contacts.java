package com.contactapp.model;

import java.util.*;

public class Contacts {
	private String name;
	private long[] phoneNo;
	private String mail;
	private String dob;
	private int groupNo;
	private boolean isFavourite;

	public Contacts() {

	}

	public Contacts(String mail, String dob, String name, boolean isFav, int groupNo) {
		this.name = name;
		this.mail = mail;
		this.dob = dob;
		this.isFavourite = isFav;
		this.groupNo = groupNo;
	}

	public Contacts(String name, long[] phoneNo, String mail, String dob, boolean isFav, int groupNo) {
		this.name = name;
		this.phoneNo = phoneNo;
		this.mail = mail;
		this.dob = dob;
		this.isFavourite = isFav;
		this.groupNo = groupNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long[] getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long[] phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public boolean getFavourite() {
		return isFavourite;
	}

	public void setFavourite(boolean isFavourite) {
		this.isFavourite = isFavourite;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

}
