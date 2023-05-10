package view;

import java.sql.SQLException;
import java.util.Scanner;

import controller.LoginController;
import controller.SetupController;

public class LoginManage {
	private Scanner input = new Scanner(System.in);
	private String userName, password, address;
	private LoginController control;

	public LoginManage() {
		control = new LoginController();
	}

	public void doSignup() {
		System.out.println("(for User) Start signing In--->\n");
		System.out.print("Enter USERNAME : ");
		userName = input.nextLine();
		control.userNameCheck(userName);
		if ((control.checkUsername(userName)) == false) {
			while ((control.checkUsername(userName)) == false) {
				System.out.println("USERNAME Already Available... Enter again");
				userName = input.nextLine();
				control.userNameCheck(userName);
			}
		}
		System.out.print("Enter PASSWORD : ");
		password = input.nextLine();
		System.out.print("Enter ADDRESS : ");
		address = input.nextLine();
		try {
			control.register(userName, password, address);
		} catch (SQLException e) {
			System.out.println("Cannot able to continue the process...");
		} finally {
			System.out.println("\\nlogin after sometimes...\n ---Thank you---");
		}
	}

	public void doLogin() {
		System.out.println("select ->  1  to login As ADMIN\n       ->  2  to login as USER");
		String choice = input.nextLine();
		if ((control.isValid("^[1-2]{1}$", choice)) == false) {

			while ((control.isValid("^[1-2]{1}$", choice)) == false) {
				System.out.println("Invalid Choice!  ----  Enter valid choice (1 or 2)");
				choice = input.nextLine();
			}
		}
		switch (choice) {
		case "1":
			getInput();
			control.loginCheck(userName, password);
			break;
		case "2":
			getInput();
			control.loginCheck(userName, password);
		}
	}

	private void getInput() {
		System.out.print("Enter USERNAME : ");
		userName = input.nextLine();
		System.out.print("Enter PASSWORD : ");
		password = input.nextLine();
	}
}