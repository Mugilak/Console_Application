package com.bank.view;

import java.util.*;

import com.bank.controller.*;
import com.bank.model.Customer;
import com.bank.model.Transaction;

public class CustomerManage {
	private CustomerController control;
	private LoginController login;
	private Scanner input = new Scanner(System.in);
	private String name, password, confirmPassword;

	CustomerManage() {
		login = new LoginController();
		control = new CustomerController();
	}

	public void actions(Customer customer) {
		String choice = "1";
		if (customer != null) {
			try {
				showInstructions();
				choice = input.nextLine();
				switch (choice) {
				case "1":
					checkBalance(customer);
					break;
				case "2":
					deposit(customer);
					break;
				case "3":
					withdraw(customer);
					break;
				default:
					System.out.println("Invalid input ");
				}
			} catch (Exception e) {
				System.out.println("Invalid input");
			}
		} else {
			System.out.println("Invalid credentials !");
		}
	}

	private void withdraw(Customer customer) {
		if (passwordCheck(customer)) {
			System.out.println("Enter amount to withdraw : ");
			String money = input.nextLine();
			if (checkMoney(customer, Long.valueOf(money))) {
				long balance = control.withdrawMoney(customer, Long.valueOf(money));
				System.out.println("Rs. " + money + " withdrawn successfully \n Your current balance : " + balance);
			} else {
				System.out.println("not a sufficient balance to withdraw from your account...");
			}
		} else {
			System.out.println("Invalid Credentials !");
		}
	}

	private boolean checkMoney(Customer customer, Long money) {
		if (customer.getBalance() < (money + 1000)) {
			return false;
		}
		return true;
	}

	private void deposit(Customer customer) {
		if (passwordCheck(customer)) {
			System.out.println("Enter amount to deposit : ");
			String money = input.nextLine();
			control.depositMoney(customer, Long.valueOf(money));
			System.out.println("Rs. " + money + " deposited successfully ");
		} else {
			System.out.println("Invalid Credentials !");
		}
	}

	private void checkBalance(Customer customer) {
		if (passwordCheck(customer)) {
			System.out.println("Your Current Balance : " + customer.getBalance());
		} else {
			System.out.println("Invalid Credentials !");
		}
	}

	private boolean passwordCheck(Customer customer) {
		System.out.println("Enter password to continue further process : ");
		String password = input.nextLine();
		password = login.getEncrypted(password.toCharArray());
		if (password.equals(customer.getPassword())) {
			return true;
		}
		return false;
	}

	private void showInstructions() {
		System.out.println("""
				1. Check Balance
				2. Deposit Money
				3. Withdraw Money
				Enter choice accordingly :
				""");
	}

	public void viewDetails(Customer customer) {
		System.out.println("--------------------------------------");
		System.out.printf("Name       : %10s", customer.getName());
		System.out.printf("\nAccount No : %10s", customer.getAccountNo());
		System.out.println();
	}

	public void add() {
		System.out.println("Enter name : ");
		name = input.nextLine();
		checkPasswordCorrect();
		String accNo = control.addCustomer(name, password);
		System.out.println("Account successfully created ! \n Your account no : " + accNo);
	}

	private void checkPasswordCorrect() {
		System.out.println("Enter new password   (Atleast 2 Upper case , Atleast 2 Lower case , Atleast 3 Number) :");
		password = input.nextLine();
		password = checkPassword(password);
		System.out.println("Enter confirm password : ");
		confirmPassword = input.nextLine();
		if (!(password.equals(confirmPassword))) {
			System.out.println("Invalid password");
			checkPasswordCorrect();
		}
	}

	private String checkPassword(String password) {
		while (password.matches("(?=.*[0-9]{3})(?=.*[a-z]{2})(?=.*[A-Z]{2}).{8,20}") == false) {
			System.out.println("Invalid password ! ");
			password = input.nextLine();
		}
		return password;
	}

	public void changePassword(Customer customer) {
		if (passwordCheck(customer)) {
			checkPasswordCorrect(customer);
			control.changePassword(customer, password);
		} else {
			System.out.println("Invalid password !");
		}
	}

	private void checkPasswordCorrect(Customer customer) {
		System.out.println("Enter new password   (Atleast 2 Upper case , Atleast 2 Lower case , Atleast 3 Number) :");
		password = input.nextLine();
		password = checkPassword(password);
		System.out.println("Enter confirm password : ");
		confirmPassword = input.nextLine();
		confirmPassword = checkPassword(confirmPassword);
		String last = password.substring(password.length() - 3);
		if ((password.equals(confirmPassword) == false) || customer.getLastpwd().equals(last)) {
			System.out.println("Invalid password  or  same as previous password");
			checkPasswordCorrect();
		} else {
			System.out.println("Password changed successfully ! ");
		}
	}

	public void viewTransactions(Customer customer) {
		if (customer.getTransactionCount() <= 0) {
			System.out.println("No transactions happen");
			return;
		}
		Stack<Transaction> transactionList = getTansactionList(customer);
		System.out.println("Id   Type        Amount      Balance\n------------------------------------------------");
		for (Transaction each : transactionList) {
			System.out.printf("%1d", each.getId());
			System.out.print(each.getType());
			System.out.printf("%10d", each.getAmount());
			System.out.printf("%10d", each.getBalance());
			System.out.println();
		}
	}

	private Stack<Transaction> getTansactionList(Customer customer) {
		return customer.getTransactionList();
	}

	public void transferMoney(Customer sender) {
		System.out.println("Enter account number : ");
		String accountNo = input.nextLine();
		Customer reciever = control.accountAvailable(accountNo, sender);
		if (reciever != null) {
			System.out.println("Enter amount to transfer : ");
			String money = input.nextLine();
			if (checkMoney(sender, Long.valueOf(money))) {
				control.transferMoney(sender, reciever, Long.valueOf(money));
				System.out.println(money + " transfered from Account No : " + sender.getAccountNo()
						+ " to Account No : " + reciever.getAccountNo());
			} else {
				System.out.println("not a sufficient balance to transfer money from your account...");
			}
		} else {
			System.out.println("Invalid account to transfer money");
		}
	}

}
