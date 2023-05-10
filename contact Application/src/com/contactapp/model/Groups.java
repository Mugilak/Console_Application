package com.contactapp.model;

import java.util.*;

public class Groups {
	private String name;
	private List<Contacts> contactsList;
	private int membersCount;

	public Groups(String name) {
		this.name = name;
		contactsList = new ArrayList<>();
	}

	public int getMembersCount() {
		return membersCount;
	}

	public void addMembers() {
		this.membersCount++;
	}

	public List<Contacts> getContactsList() {
		return contactsList;
	}

	public void setContactsList(Contacts contactsList) {
		this.contactsList.add(contactsList);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
