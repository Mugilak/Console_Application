package controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import database.Cinema;
import model.CinemaDatabase;
import model.User;
import view.AdminManage;
import view.LoginManage;
import view.UserManage;

public class LoginController {
	private Scanner input = new Scanner(System.in);
	private CinemaDatabase cinemaDB = CinemaDatabase.getInstance();
	private User user;
	private Cinema cinema = Cinema.getInstance();
	private AdminManage admin;
	private UserManage userManage;
	private String query;
	private SetupController setup;
	private LoginManage login;

	public LoginController() {
		setup = new SetupController();
		admin = new AdminManage();
		userManage = new UserManage();
	}

	public void loginCheck(String userName, String password) {
		login = new LoginManage();
		if ((userName.equals("admin001") && password.equals("nimda"))
				|| (userName.equals("admin002") && password.equals("nimda2"))) {
			setup.getAdminDB();
			System.out.println("\n      Welcome ADMIN");
			if (setup.isSetUped()) {
				System.out.println("Locations set uped... do operations ");
				admin.doProcess();
			} else {
				System.out.println("Application not yet set uped... create now");
				admin.init();
			}
		} else if ((checkCredential(userName, password)) == false) {
			setup.getAdminDB();
			System.out.println("\nSuccessfully Logged In !\nUnlimited Movies... Book now and enjoy watching");
			userManage.view();
		} else {
			System.out.println("Invalid Credentials.. start over again\n\n");
			login.doLogin();
		}
	}

	public boolean userNameCheck(String userName) {
		if ((isValid("^[a-z]{6}[0-9]{2}$", userName)) == false) {
			while ((isValid("^[a-z]{6}[0-9]{2}$", userName)) == false) {
				System.out.println(
						"Invalid username!  ----  Enter valid USERNAME (8 letters--- 1st 6 should be (lowercase only)character, last 2 should be numbers  e.g. mugila12)");
				userName = input.nextLine();
			}
		}
		return true;
	}
	// "^[a-zA-Z0-9@!#$%^&*]$"

	public boolean passwordCheck(String password) {
		if ((isValid("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}", password)) == false) {
			while ((isValid("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}", password)) == false) {
				System.out.println("Invalid password!  ----  Enter valid password (8 numbers)");
				password = input.nextLine();
			}
		}
		return true;
	}

	public boolean isValid(String regex, String word) {
		Pattern pattern = Pattern.compile(regex);
		Matcher match = pattern.matcher(word);
		if (match.find()) {
			return true;
		}
		return false;
	}

	public boolean checkCredential(String userName, String password) {
		setup.getUserDB();
		List<User> users = cinemaDB.getUserList();
		for (int index = 0; index < users.size(); index++) {
			User eachUser = users.get(index);
			if ((eachUser.getUserName().equals(userName)) && (eachUser.getPassword().equals(password))) {
				return false;
			}
		}
		return true;
	}

	public boolean checkUsername(String userName) {
		List<User> users = cinemaDB.getUserList();
		for (int index = 0; index < users.size(); index++) {
			User eachUser = users.get(index);
			if ((eachUser.getUserName().equals(userName))) {
				return false;
			}
		}
		return true;
	}

	public void register(String userName, String password, String address) throws SQLException {
		user = new User(userName, password, address);
		cinemaDB.addUserList(user);
		query = "insert into users " + "values('" + user.getUserName() + "','" + user.getPassword() + "','"
				+ user.getAddress() + "')";
		cinema.setData(query);
	}

}
