package com.contactapp.view;

import java.util.List;
import java.util.Scanner;

import com.contactapp.controller.ContactController;
import com.contactapp.model.Contacts;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ManageContact {
	private Scanner input = new Scanner(System.in);
	private ContactController control;

	public ManageContact() {
		control = new ContactController(this);
	}

	private void shareContact() {
		System.out.println("Enter Name of Contact : ");
		String name = input.nextLine();
		Contacts contact = control.isAvailable(name);
		if (contact != null) {
			share(contact);
		} else {
			System.out.println("Invalid Name ! ");
		}
	}

	private void share(Contacts contact) {
		boolean isShared = control.exportInJSON(contact);
		if (isShared) {
			System.out.println("Successfully exported !\n");
		}
	}

	public void proceed(int choice) {
		switch (choice) {
		case 2:
			getAllContacts();
			break;
		case 3:
			getFavourites();
			break;
		case 4:
			searchContacts();
			break;
		case 5:
			getPersonalDetails();
			break;
		case 6:
			shareContact();
			break;
		}
	}

	private void getPersonalDetails() {

	}

	private void getAllContacts() {
		List<Contacts> list = control.getContacts();
		showContacts(list);
	}

	private void showContacts(List<Contacts> list) {
		System.out.println("-----------------------");
		for (Contacts contact : list) {
			System.out.print("Name     :");
			System.out.printf("%8s", contact.getName());
			long[] no =contact.getPhoneNo();
			System.out.print("\nPhone No :");
			System.out.print(no[0]);
			System.out.println("\n-----------------------");
		}
	}

	private void searchContacts() {
		System.out.println("Enter Name of Contact : ");
		String name = input.nextLine();
		Contacts contact = control.isAvailable(name);
		control.addOnJSON(contact);
		if (contact != null) {
			showContacts(contact);
			System.out.println("\n to Share or export - press 1\n or - press 0");
			String choice = input.nextLine();
			if (Integer.valueOf(choice) == 1) {
				share(contact);
			} else if (Integer.valueOf(choice) == 0 || Integer.valueOf(choice) > 1) {
				return;
			}
		} else {
			System.out.println("Invalid Name ! ");
		}
	}

	private void showContacts(Contacts contact) {
		System.out.println("-----------------------");
		System.out.printf("Name : %5s", contact.getName());
		long[] phoneNo = contact.getPhoneNo();
		System.out.printf("phoneNo : %5s", phoneNo[0]);
		System.out.println("\n-----------------------");
	}

	private void getFavourites() {
		List<Contacts> list = control.getFavoritesList();
		showContacts(list);
	}

	public void alert(ObjectMapper object, Contacts contact) {
		try {
			System.out.println(object.writeValueAsString(contact));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
