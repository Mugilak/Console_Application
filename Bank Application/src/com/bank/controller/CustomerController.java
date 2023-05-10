package com.bank.controller;

import java.util.*;

import com.bank.model.*;
import com.bank.repository.BankDatabase;

public class CustomerController {
	private static int id = 1;
	private LoginController login;
	BankDatabase bankDB = BankDatabase.getInstanceOf();

	public CustomerController() {
		login = new LoginController();
	}

	public String addCustomer(String name, String password) {
		Customer customer = new Customer(name, login.getEncrypted(password.toCharArray()));
		String last = password.substring(password.length() - 3);
		customer.setLastpwd(last);
		bankDB.setCustomerList(customer);
		return customer.getAccountNo();
	}

	public void changePassword(Customer customer, String password) {
		customer.setPassword(login.getEncrypted(password.toCharArray()));
	}

	public long depositMoney(Customer customer, long money) {
		long amount = customer.add(money);
		Transaction transaction = new Transaction(id++, money, Transaction.Type.DEPOSIT);
		transaction.setBalance(amount);
		transaction.setUser(customer.getAccountNo());
		customer.setTransactionList(transaction);
		bankDB.setTransactionsList(transaction);
		return amount;
	}

	public long withdrawMoney(Customer customer, Long money) {
		long amount = customer.less(money);
		Transaction transaction = new Transaction(id++, money, Transaction.Type.WITHDRAW);
		transaction.setBalance(amount);
		transaction.setUser(customer.getAccountNo());
		customer.setTransactionList(transaction);
		bankDB.setTransactionsList(transaction);
		return amount;
	}

	public Customer accountAvailable(String accountNo, Customer sender) {
		List<Customer> list = bankDB.getCustomerList();
		for (Customer each : list) {
			if (each.getAccountNo().equals(accountNo) && each != sender) {
				return each;
			}
		}
		return null;
	}

	public void transferMoney(Customer sender, Customer reciever, Long money) {
		sender.less(money);
		reciever.add(money);
		Transaction transaction = new Transaction(id++, money, Transaction.Type.TRANSFER);
		transaction.setSender(sender.getAccountNo());
		transaction.setReciever(reciever.getAccountNo());
		sender.setTranferedList(transaction);
		reciever.setRecievedList(transaction);
		bankDB.setTransactionsList(transaction);
	}

}
