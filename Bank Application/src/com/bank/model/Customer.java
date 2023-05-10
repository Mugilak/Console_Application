package com.bank.model;

import java.util.*;

public class Customer {
	private String accountNo = "ABCB00";
	private static long accNo = 001;
	private static int id = 1;
	private long balance;
	private String name, password, lastpwd;
	private Stack<Transaction> selfTransactionList, tranferedList, recievedList;
	private int maintenanceFee;

	public Customer(String name, String pwd) {
		accountNo = accountNo + accNo;
		accNo++;
		this.id++;
		this.name = name;
		this.password = pwd;
//		this.confirmPassword = confirmPwd;
		this.selfTransactionList = new Stack<Transaction>();
		this.tranferedList = new Stack<Transaction>();
		this.recievedList = new Stack<Transaction>();
		this.balance = 10000;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public String getConfirmPassword() {
//		return confirmPassword;
//	}
//
//	public void setConfirmPassword(String confirmPassword) {
//		this.confirmPassword = confirmPassword;
//	}

	public Stack<Transaction> getTransactionList() {
		return selfTransactionList;
	}

	public int getTransactionCount() {
		return this.selfTransactionList.size();
	}

	public void setTransactionList(Transaction transaction) {
		this.selfTransactionList.add(transaction);
	}

	public int getMaintenanceFee() {
		return maintenanceFee;
	}

	public void setMaintenanceFee(int maintenanceFee) {
		this.maintenanceFee = maintenanceFee;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public String getLastpwd() {
		return lastpwd;
	}

	public void setLastpwd(String lastpwd) {
		this.lastpwd = lastpwd;
	}

	public long add(long money) {
		this.balance += money;
		return this.balance;
	}

	public long less(long money) {
		this.balance -= money;
		return this.balance;
	}

	public Stack<Transaction> getTranferedList() {
		return tranferedList;
	}

	public void setTranferedList(Transaction tranfered) {
		this.tranferedList.push(tranfered);
	}

	public Stack<Transaction> getRecievedList() {
		return recievedList;
	}

	public void setRecievedList(Transaction recieved) {
		this.recievedList.push(recieved);
	}

}
