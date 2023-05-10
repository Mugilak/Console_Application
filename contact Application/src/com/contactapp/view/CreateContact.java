package com.contactapp.view;

import java.util.Scanner;

import com.contactapp.controller.ContactController;
import com.contactapp.controller.InputController;

public class CreateContact {
	private Scanner input = new Scanner(System.in);
	private ContactController control;
	private InputController inputControl;
	private String name, mail, dob;
	private long[] phoneNo = new long[3];
	boolean isFav;
	private int group;

	CreateContact() {
		inputControl = new InputController();
		control = new ContactController();
	}

	public void setApplication() {
		control.setGroups();
		inputControl.setContact();
	}

	public void createContact() {
		addContact();
	}

	private void addContact() {
		getInput();
		control.addContact(name, phoneNo, mail, dob, isFav, group);
		System.out.println("Succesfully created the contact !");
	}

	private void getInput() {
		getName();
		getPhoneNo();
		System.out.println("\nEnter e-mail");
		mail = input.nextLine();
		System.out.println("Enter Date of Birth : ");
		dob = input.nextLine();
		getFav();
		showGroups();
	}

	private void getName() {
		System.out.println("Enter Name : ");
		name = input.nextLine();
		if (control.isAvailable(name) != null) {
			System.out.println("Already available Name ! Enter again .....");
			getName();
		}
	}

	private void getFav() {
		System.out.println("Favourite contact ? (Enter - yes/no) : ");
		String fav = input.nextLine();
		if (fav.equalsIgnoreCase("yes")) {
			isFav = true;
		} else if (fav.equalsIgnoreCase("no")) {
			isFav = false;
		} else {
			System.out.println("invalid input");
			getFav();
		}
	}

	private void showGroups() {
		System.out.println("""
				Select Group :
				1. Family
				2. Friends
				3. Colleagues
				""");
		group = input.nextInt();
		if (group <= 0 && group > 3) {
			showGroups();
		}
		System.out.println();
	}

	private void getPhoneNo() {
		System.out.println("Enter phone numbers (maximum 3)");
		for (int i = 0; i < 3; i++) {
			System.out.print("\nEnter phone No. " + i + " (or exit) : ");
			String phone = input.nextLine();
			if (!(phone.equalsIgnoreCase("exit"))) {
				phoneNo[i] = Long.valueOf(phone);
			} else {
				if (i == 0) {
					System.out.println("Atleast one number needed !");
					getPhoneNo();
					break;
				} else {
					break;
				}
			}
		}
	}

}
