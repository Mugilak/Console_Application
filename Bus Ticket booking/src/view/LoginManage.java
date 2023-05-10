package view;

import java.sql.SQLException;
import java.util.Scanner;

import controller.LoginController;
import controller.SetupController;

public class LoginManage extends BusTicket {
	private Scanner input = new Scanner(System.in);
	private LoginController control;
	private int adminCount = 0, userCount = 0;
	private String userName, password;
//	private SetupController setup;

	public LoginManage() {
		control = new LoginController();
	}

	public void doLogin(int count) {
		System.out.println("Start Log in --->");
		System.out.println("select ->  1  to login As ADMIN\n       ->  2  to login as USER");
		String choice = input.nextLine();
		choice = super.check("^[1-2]+$", choice, "choice", "(ex: 1 or 2)");
		switch (choice) {
		case "1":
			getInput();
			adminCount = count;
			control.adminLoginCheck(userName, password, adminCount);
			System.out.println("\nYou are loggedd out ... Thankyou !\n");
			doLogin(0);
			break;
		case "2":
			getInput();
			userCount = count;
			control.userLoginCheck(userName, password, userCount);
			break;
		}
	}

	private void getInput() {
		System.out.println("Enter UserName : ");
		userName = input.nextLine();
		super.check("^[a-z]{6}[0-9]{2}$", userName, "username", "ex : mugila12");
		System.out.println("Enter password : ");
		password = input.nextLine();
		super.check("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}", password, "password",
				"(length= 8 to 20) atleast one uppercase, lowercase, number, special character");
		password = control.getEncoded(password);
	}

	/*
	 * sugirt19 asdfG123$ eQxH9pMTImGpLQfHsP5PZdDfFpli7TwpKZrYaNaTEz8=
	 */
	public void doSignup() {
		System.out.println("Start register ----->");
		getInput();
		System.out.println("Enter Age (should be greater than 18): ");
		try {
			String age = input.nextLine();
			if (control.isUserNameExists(userName) || Integer.valueOf(age) < 18) {
				System.out.println("Invalid data ... try again\n");
				doSignup();
			} else {
				control.register(userName, password, Integer.valueOf(age));
				System.out.println("\nWelcome " + userName + "\n");
				PassengerManage passengerManage = new PassengerManage();
				passengerManage.start(userName);
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid data entry... Exited");
		} catch (SQLException e) {
			System.out.println("Cannot proceed to register try again !\n     Thank you");
			e.printStackTrace();
		}

	}

}
