package com.bank.repository;

import java.util.*;

import com.bank.model.*;

public class BankDatabase {
	private static BankDatabase bankDB;
	private List<Customer> customerList;
	private List<Transaction> transactionsList;

	private BankDatabase() {
		customerList = new ArrayList<>();
		transactionsList = new ArrayList<>();
	}

	public static BankDatabase getInstanceOf() {
		if (bankDB == null) {
			bankDB = new BankDatabase();
		}
		return bankDB;
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(Customer customer) {
		this.customerList.add(customer);
	}

	public List<Transaction> getTransactionsList() {
		return transactionsList;
	}

	public void setTransactionsList(Transaction transaction) {
		this.transactionsList.add(transaction);
	}

}
