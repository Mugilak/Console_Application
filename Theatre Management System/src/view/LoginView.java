package view;

import java.util.Scanner;

import controller.LoginController;
import database.Cinema;

public class LoginView {
	private Scanner input = new Scanner(System.in);
	private LoginController control;
	private LoginManage manage;
	private static Cinema cinema = Cinema.getInstance();

	public LoginView() {
		control = new LoginController();
		manage = new LoginManage();
	}

	public static void main(String args[]) {
		LoginView login = new LoginView();
		cinema.setConnection();
		login.entry();
	}

	private void entry() {
		System.out.println("Welcome to BOOK MY SHOW \n-----------------------------------");
		System.out.println("~~~~~Enjoy by watching Unlimited Movies~~~~~\n\n");
		System.out.println("select ->  1  to LOGIN\n       ->  2  to SIGN UP");
		String choice = input.nextLine();
		if ((control.isValid("^[1-2]+$", choice)) == false) {

			while ((control.isValid("^[1-2]+$", choice)) == false) {
				System.out.println("Invalid Choice!  ----  Enter valid choice (1 or 2)");
				choice = input.nextLine();
			}
		}
		switch (choice) {
		case "1":
			manage.doLogin();
			break;
		case "2":
			manage.doSignup();
		}
	}
	
}
