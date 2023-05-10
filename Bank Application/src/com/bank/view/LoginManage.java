package com.bank.view;

import java.util.Scanner;

import com.bank.controller.LoginController;
import com.bank.model.Customer;

public class LoginManage {
	private CustomerManage manage;
	private LoginController control;
	private Scanner input = new Scanner(System.in);
	private int i;

	public LoginManage() {
		manage = new CustomerManage();
		control = new LoginController();
	}

	public void login() {
		Customer customer = accountAvailable();
		if (customer != null) {
			System.out.println("You are logged in succesfully ! ");
			actions(customer);
		} else {
			System.out.println("Invalid credentials  /  not available account");
			i++;
			if (i < 3) {
				login();
			} else if (i == 3) {
				System.out.println("3 chances are over ... try again");
				i = 0;
			}
		}
	}

	private void actions(Customer customer) {
		String choice = "0";
		while (true) {
			try {
				showInstructions();
				if (customer.getTransactionCount() == 10) {
					manage.changePassword(customer);
				}
				choice = input.nextLine();
				switch (choice) {
				case "1":
					manage.viewDetails(customer);
					break;
				case "2":
					manage.actions(customer);
					break;
				case "3":
					manage.viewTransactions(customer);
					break;
				case "4":
					manage.changePassword(customer);
					break;
				case "5":
					manage.transferMoney(customer);
					break;
				case "6":
					System.out.println("Thank you ! You are logged out");
					return;
				default:
					System.out.println("Invalid input ");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Invalid input");
			}
		}
	}

	private void showInstructions() {
		System.out.println("""
				--------------------------------------
				1. View Account Details
				2. Perform Actions
				3. View Transaction List and Details
				4. Change Password
				5. Money transfer to another account
				6. Logout

				Enter choice Accordingly :
				""");
	}

	private Customer accountAvailable() {
		System.out.println("Enter account No : ");
		String accNo = input.nextLine();
		System.out.println("Enter Password : ");
		String password = input.nextLine();
		Customer customer = control.isAccountAvailable(accNo, password);
		return customer;
	}

}
