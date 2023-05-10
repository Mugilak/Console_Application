package com.bank.view;

import java.util.Scanner;

public class InitialPage {
	private CustomerManage manage;
	private LoginManage login;
	private Scanner input = new Scanner(System.in);

	InitialPage() {
		login = new LoginManage();
		manage = new CustomerManage();
	}

	public static void main(String[] args) {
		InitialPage initial = new InitialPage();
		initial.init(initial);
	}

	private void init(InitialPage initial) {
		System.out.println("----- WELCOME TO ABC BANK ----- \n");
		initial.performs();
	}

	private void performs() {
		String choice = "0";
		while (true) {
			try {
				showInstructions();
				choice = input.nextLine();
				switch (choice) {
				case "1":
					manage.add();
					break;
				case "2":
					login.login();
					break;
				case "3":
					System.out.println("exited .. Thank you !");
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

				1. Add New Customer
				2. Login
				3. Exit

				Enter choice Accordingly :
				""");
	}

}
