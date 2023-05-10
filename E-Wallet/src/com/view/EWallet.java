package com.view;

import java.util.Scanner;

import com.controller.InputController;
import com.model.Users;

public class EWallet {
	private InputController control;
	private AdminManage admin;
	private UserManage user;
	private Scanner input = new Scanner(System.in);

	EWallet() {
		control = new InputController();
		user = new UserManage();
		admin = new AdminManage();
	}

	static public void main(String[] args) {
		EWallet newWallet = new EWallet();
		newWallet.start();
	}

	private void start() {
		System.out.println("Welcome to IRCTC\n---------------------");
		startLogin();
	}

	private void startLogin() {
		control.getInput();
		System.out.println("Enter username : ");
		String username = input.nextLine();
		System.out.println("Enter password : ");
		String password = input.nextLine();
		Users users = control.isValid(username, password);
		if (control.adminLogin(username, password)) {
			System.out.println("\n***WELCOME " + username + "***\n--You are successfully Logged in--");
			control.getRoutes();
			admin.start();
		} else if (users != null) {
			System.out.println("\n***WELCOME " + username + "***\n--You are successfully Logged in--\n");
			control.getRoutes();
			user.start(users);
		} else
			System.out.println("Incorrect Credentials !");
	}
}
