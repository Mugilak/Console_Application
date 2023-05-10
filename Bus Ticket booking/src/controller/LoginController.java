package controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.BusBookingDatabase;
import model.Passenger;
import view.AdminManage;
import view.LoginManage;
import view.PassengerManage;

public class LoginController {
//	private SetupController setup;
	PassengerManage passengerManage;
	private AdminManage admin;
	private String query;
	private BusBookingDatabase busDB = BusBookingDatabase.getInstance();

	public LoginController() {
		
	}

	public boolean isValid(String regex, String word) {
		Pattern pattern = Pattern.compile(regex);
		Matcher match = pattern.matcher(word);
		if (match.find()) {
			return true;
		}
		return false;
	}

	public boolean isUserNameExists(String userName) {
		List<Passenger> passengerList = busDB.getPassengerList();
		for (int index = 0; index < passengerList.size(); index++) {
			Passenger eachPassenger = passengerList.get(index);
			if ((eachPassenger.getUserName().equals(userName))) {
				return true;
			}
		}
		return false;
	}

	public boolean checkCredential(String userName, String password) {
//		setup.getUserDB();
		List<Passenger> passengerList = busDB.getPassengerList();
		for (int index = 0; index < passengerList.size(); index++) {
			Passenger eachPassenger = passengerList.get(index);
			if ((eachPassenger.getUserName().equals(userName)) && (eachPassenger.getPassword().equals(password))) {
				return true;
			}
		}
		return false;
	}

	public String getEncoded(String textToHash) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] byteOfTextToHash = textToHash.getBytes(StandardCharsets.UTF_8);
			byte[] hashedByetArray = digest.digest(byteOfTextToHash);
			String encoded = Base64.getEncoder().encodeToString(hashedByetArray);
			return encoded;
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Sorry , Cannot  process.. try again");
		}
		return null;
	}

	public void register(String userName, String password, int age) throws SQLException {
		Passenger passenger = new Passenger(userName, password, age);
		busDB.setPassengerList(passenger);
		busDB.setPassengerList(passenger, query);
	}

	public void adminLoginCheck(String userName, String password, int adminCount) {
		LoginManage login = new LoginManage();
		if (adminCount >= 3) {
			try {
				Thread.sleep(3000);
				login.doLogin(0);
			} catch (InterruptedException e) {
				System.out.println("Cannot proceed .. you are exited... Thankyou");
			}
		} else {
			if ((userName.equals("admina01") && password.equals(getEncoded("Root@123")))
					|| (userName.equals("adminb02") && password.equals(getEncoded("Root@312")))) {
				System.out.println("\n      Welcome ADMIN");
				try {
					admin = new AdminManage();
					admin.start();
				} catch (SQLException e) {
					System.out.println("Cannot proceed with your data");
				}
				return;
			} else {
				System.out.println("Invalid Credentials.. start over again\n\n");
				login.doLogin(adminCount++);
			}
		}
	}

	public void userLoginCheck(String userName, String password, int userCount) {
		LoginManage login = new LoginManage();
		if (userCount >= 3) {
			try {
				Thread.sleep(3000);
				login.doLogin(0);
			} catch (InterruptedException e) {
				System.out.println("Cannot proceed .. Thankyou");
			}
		} else {
			if ((checkCredential(userName, password))) {
//				setup.getAdminDB();
				System.out.println(
						"\nSuccessfully Logged In !\n\nTravel Unlimitedly ... Book now and enjoy travelling\n");
				passengerManage = new PassengerManage();
				passengerManage.start(userName);
			} else {
				System.out.println("Invalid Credentials.. start over again\n\n");
				login.doLogin(userCount++);
			}
		}
	}

}
