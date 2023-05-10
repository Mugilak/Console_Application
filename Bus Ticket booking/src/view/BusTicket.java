package view;

import java.sql.SQLException;
import java.util.Scanner;

import controller.LoginController;
import controller.SetupController;

public class BusTicket {
	private Scanner input = new Scanner(System.in);
	private LoginManage manage;
	private LoginController control;
	private SetupController setup;

	BusTicket() {
		control = new LoginController();
		setup = new SetupController();
	}

	public static void main(String[] args) {
		new BusTicket().start();
	}

	private void setup() {
		setup.getAdminDB();
		if (!(setup.isSetUped())) {
			AdminManage admin = new AdminManage();
			try {
				admin.start();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		setup.getUserDB();
	}

	private void start() {
		System.out.println("           ----- Bus Ticket Booking Management System -----");
		setup();
		System.out.println("\nBuses are setuped \n");
		System.out.println("select ->  1  to LOGIN\n       ->  2  to SIGN UP");
		String choice = input.nextLine();
		choice = check("^[1-2]{1}+$", choice, "choice", "(ex: 1 or 2)");
		manage = new LoginManage();
		switch (choice) {
		case "1":
			manage.doLogin(0);
			break;
		case "2":
			manage.doSignup();
			break;
		}
	}

	protected String check(String regex, String word, String print, String example) {
		if ((control.isValid(regex, word)) == false) {
			while ((control.isValid(regex, word)) == false) {
				System.out.println("Invalid " + print + " !  ----  Enter " + print + " again " + example);
				word = input.nextLine();
			}
		}
		return word;
	}

}
