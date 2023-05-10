package com.contactapp.repository;

import java.util.*;

import com.contactapp.model.Contacts;
import com.contactapp.model.Groups;

public class ContactDB {
	static ContactDB contact;
	private List<Contacts> contactsList;
	private Groups[] groupList;

	private ContactDB() {
		contactsList = new ArrayList<>();
		groupList = new Groups[3];
	}

	public static ContactDB getInstance() {
		if (contact == null) {
			contact = new ContactDB();
		}
		return contact;
	}

	public List<Contacts> getContactsList() {
		return contactsList;
	}

	public void setContactsList(Contacts contactsList) {
		this.contactsList.add(contactsList);
	}

	public Groups[] getGroupList() {
		return groupList;
	}

	public Groups getGroup(int index) {
		return groupList[index];
	}

	public void setGroupList() {
		Groups group1 = new Groups("Family");
		Groups group2 = new Groups("Friends");
		Groups group3 = new Groups("Colleagues");
		groupList[0] = group1;
		groupList[1] = group2;
		groupList[2] = group3;
	}
}
