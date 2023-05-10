package com.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;

import com.model.Destinations;
import com.model.Users;
import com.repository.EWalletDB;
import com.view.EWallet;

public class InputController {
	private Users user;
	private EWalletDB eWallet = EWalletDB.getInstance();

	public void getInput() {
		File path = new File("C:\\Users\\91638\\eclipse-workspace\\E-Wallet\\src\\com\\model\\input.txt");
		String currentLine = "";
		Scanner reader;
		try {
			reader = new Scanner(path);
			int i = 1;
			Queue<String> input = new LinkedList<>();
			while (reader.hasNext()) {
				currentLine = reader.nextLine();
				input.offer(currentLine);
				i++;
				if (i == 8) {
					user = new Users(input.poll(), Integer.valueOf(input.poll()), input.poll(),
							Long.valueOf(input.poll()), Integer.valueOf(input.poll()), input.poll(), input.poll());
					eWallet.addUsers(user);
					input.clear();
					i = 1;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	public void getRoutes() {
		Destinations destination;
		String[] line;
		try {
			Scanner reader = new Scanner(
					new File("C:\\Users\\91638\\eclipse-workspace\\E-Wallet\\src\\com\\model\\Destination.txt"));
			while (reader.hasNext()) {
				line = reader.nextLine().split(",");
				destination = new Destinations(line[0], Integer.valueOf(line[1]));
				eWallet.addDestination(destination);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("Invalid input");
		}

	}

	public Users isValid(String username, String password) {
		List<Users> userList = eWallet.getUsersList();
		for (Users user : userList) {
			if (user.getType().equals("user") && user.getUsername().equals(username)
					&& user.getPassword().equals(doEncrypt(password))) {
				return user;
			}
		}
		return null;
	}

	public boolean adminLogin(String username, String password) {
		List<Users> userList = eWallet.getUsersList();
		for (Users user : userList) {
			if (user.getType().equals("admin") && user.getUsername().equals(username)
					&& user.getPassword().equals(doEncrypt(password))) {
				return true;
			}
		}
		return false;
	}

	public String doEncrypt(String password) {
		String pwd = "";
		int len = password.length();
		for (int i = 0; i < len; i++) {
			char c = password.charAt(i);
			c -= 1;
			if (c == 47)
				c = '9';
			else if (c == 64)
				c = 'Z';
			else if (c == 96)
				c = 'z';
			pwd += c;
		}
		return pwd;
	}

}
