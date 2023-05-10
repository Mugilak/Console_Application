package com.model;

import java.util.*;

public class Users {
	private String username;
	private int userId;
	private String emailId;
	private long phoneNo;
	private int amount;
	private String password;
	private String type;
	private Stack<Booking> transactions;

	public Users(String userName, int userId, String emailId, long phoneNo, int amount, String password, String type) {
		this.username = userName;
		this.userId = userId;
		this.amount = amount;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
		this.password = password;
		this.type = type;
		this.transactions = new Stack<>();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public int getAmount() {
		return amount;
	}

	public void addAmount(int amount) {
		this.amount += amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Stack<Booking> getTransactions() {
		return transactions;
	}

	public void setTransactions(Stack<Booking> transactions) {
		this.transactions = transactions;
	}

	public void addTransaction(Booking transaction) {
		this.transactions.push(transaction);
	}

}
