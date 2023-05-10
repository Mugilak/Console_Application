package com.bank.controller;

import java.util.List;

import com.bank.model.Customer;
import com.bank.repository.BankDatabase;

public class LoginController {
	BankDatabase bankDB = BankDatabase.getInstanceOf();

	public String getEncrypted(char[] password) {
		String pwd = "";
		int key = 3;
		for (char c : password) {
			if (c >= 48 && c <= 57) {
				pwd += (char) (((c - '0') + key) % 10 + 48);
			} else if (c >= 65 && c <= 90) {
				pwd += (char) (((c - 'A') + key) % 26 + 65);
			} else if (c >= 97 && c <= 122) {
				pwd += (char) (((c - 'a') + key) % 26 + 97);
			} else {
				pwd += c;
			}
		}
		return pwd;
	}

	public Customer isAccountAvailable(String accNo, String password) {
		List<Customer> list = bankDB.getCustomerList();
		for (Customer each : list) {
			if (each.getAccountNo().equals(accNo) && each.getPassword().equals(getEncrypted(password.toCharArray()))) {
				return each;
			}
		}
		return null;
	}

}
