package com.contactapp.view;

import java.util.Scanner;

public class ContactView {
	private Scanner input = new Scanner(System.in);
	private CreateContact create;
	private ManageContact manage;

	ContactView() {
		create = new CreateContact();
		manage = new ManageContact();
	}

	public static void main(String[] args) {
		new ContactView().start();
	}

	private void start() {
		System.out.println("----- Welcome to Contact Application----");
		create.setApplication();
		actionList();
	}

	private void actionList() {
		String option = "";
		try {
			do {
				provideList();
				option = input.nextLine();
				int num = (int) option.charAt(0) - '0';
				if (num < 1 || num > 7) {
					System.out.println("Invalid input ...");
					continue;
				}
				if (num == 1) {
					create.createContact();
				} else if (num >= 2 && num < 7) {
					manage.proceed(num);
				} else if (num == 7) {
					option = "0";
					System.out.println("Thank you !");
				}
			} while (!(option.equals("0")));
		} catch (Exception e) {
			System.out.println("invalid input");
		}
	}

	private void provideList() {
		System.out.println();
		System.out.println("""
				1. Add Contacts
				2. Contacts list
				3. Favourite list
				4. Search
				5. Personal details
				6. Share / export
				7. Exit
				~~~~~~~~
				Enter Choice Accordingly
				""");
	}
}
