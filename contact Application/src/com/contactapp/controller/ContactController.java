package com.contactapp.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.contactapp.model.Contacts;
import com.contactapp.model.Groups;
import com.contactapp.repository.ContactDB;
import com.contactapp.view.ManageContact;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ContactController {
	private ContactDB contactDB = ContactDB.getInstance();
	private ManageContact manage;
	private InputController input;

	public ContactController(ManageContact manage) {
		this.manage = manage;
		this.input = new InputController();
	}

	public ContactController() {
	}

	public void setGroups() {
		contactDB.setGroupList();
	}

	public void addContact(String name, long[] phoneNo, String mail, String dob, boolean isFav, int group) {
		Contacts contact = new Contacts(name, phoneNo, mail, dob, isFav, group);
		contactDB.setContactsList(contact);
		Groups groups = contactDB.getGroup(group);
		groups.setContactsList(contact);
		groups.addMembers();
		addOnJSON(contact);
	}

	public void addOnJSON(Contacts contact) {
		ObjectMapper Obj = new ObjectMapper();
		try {
			String jsonStr = Obj.writeValueAsString(contact);
			System.out.println(jsonStr);
			JSONParser parser = new JSONParser();
			JSONObject jsonObj = (JSONObject) parser.parse(jsonStr);

			JSONArray jsonArray = input.getJsonArray();
			jsonArray.add(jsonObj);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

//		String path = "C:\\Users\\91638\\eclipse-workspace\\contact Application\\AllContactsData.json";
//		Scanner file;
//		try {
//			file = new Scanner(new File(path));
//			String jsonString = "";
//			if (!file.hasNext()) {
//				jsonString = "{}";
//			} else {
//				while (file.hasNext()) {
//					jsonString += file.nextLine();
//				}
//			}
//			System.out.println(jsonString);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
	}

	public Contacts isAvailable(String name) {
		List<Contacts> list = contactDB.getContactsList();
		for (Contacts contact : list) {
			if (contact.getName().equalsIgnoreCase(name)) {
				return contact;
			}
		}
		return null;
	}

	public boolean exportInJSON(Contacts contact) {
		ObjectMapper object = new ObjectMapper();
		try {
			if (createFile(contact)) {
				object.writeValue(new File(
						"C:\\Users\\91638\\eclipse-workspace\\contact Application\\" + contact.getName() + ".json"),
						contact);
				manage.alert(object,contact);
				return true;
			} else {
				System.out.println("could not create file to share");
			}
		} catch (StreamWriteException e) {
			e.printStackTrace();
		} catch (DatabindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean createFile(Contacts contact) {
		boolean isCreated = false;
		File file = new File(contact.getName() + ".json");
		try {
			isCreated = file.createNewFile();
			return isCreated;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isCreated;
	}

	public List<Contacts> getContacts() {
		return contactDB.getContactsList();
	}

	public List<Contacts> getFavoritesList() {
		List<Contacts> favList = new ArrayList<>();
		List<Contacts> list = contactDB.getContactsList();
		for (Contacts contact : list) {
			if (contact.getFavourite()) {
				favList.add(contact);
			}
		}
		return favList;
	}

}
