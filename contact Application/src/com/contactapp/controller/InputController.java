package com.contactapp.controller;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.contactapp.model.Contacts;
import com.contactapp.repository.ContactDB;

public class InputController {
	private ContactDB contactDB = ContactDB.getInstance();

	public JSONArray getJsonArray() {
		String path = "C:\\Users\\91638\\git\\Console_Application\\contact Application\\src\\com\\contactapp\\model\\AllContactsData.json";
		Scanner file;
		JSONArray jsonArray = null;
		try {
			file = new Scanner(new File(path));
			String jsonString = "";
			if (!file.hasNext()) {
				jsonString = "{}";
			} else {
				while (file.hasNext()) {
					jsonString += file.nextLine();
				}
			}
			JSONParser parser = new JSONParser();
			JSONObject jsonObj = (JSONObject) parser.parse(jsonString);
			jsonArray = (JSONArray) jsonObj.get("Contacts");
			return jsonArray;
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	public void setContact() {
		String path = "C:\\Users\\91638\\eclipse-workspace\\contact Application\\AllContactsData.json";
		Scanner file;
		try {
			JSONArray jsonArray = getJsonArray();
			Iterator itr2 = (jsonArray).iterator();
			while (itr2.hasNext()) {
				Iterator itr1 = ((Map) itr2.next()).entrySet().iterator();
//				Object obj = ((Map) itr2.next()).entrySet();
				Queue<String> data = new LinkedList<>();
				while (itr1.hasNext()) {
					Map.Entry pair = (Entry) itr1.next();
					data.offer(pair.getValue().toString());
				}
				setOnClass(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setOnClass(Queue<String> data) {
		Contacts contact = new Contacts(data.poll(), data.poll(), data.poll(), Boolean.valueOf(data.poll()),
				Integer.valueOf(data.poll()));
		String number = data.poll();
		long[] phoneNo = setContact(number);
		contact.setPhoneNo(phoneNo);
		contactDB.setContactsList(contact);
	}

	private long[] setContact(String poll) {
		long[] phoneNo = new long[3];
		int index = 0;
		long val = 0;
		for (int i = 1; i < poll.length() - 1; i++) {
			if (poll.charAt(i) == '[' || poll.charAt(i) == ']') {
				continue;
			} else if (poll.charAt(i) == ',') {
				phoneNo[index++] = val;
				val = 0;
			} else if (poll.charAt(i) >= 48 && poll.charAt(i) <= 57) {
				val = val * 10 + (long) (poll.charAt(i) - '0');
			}
		}
		phoneNo[index] = val;
		return phoneNo;
	}

}
