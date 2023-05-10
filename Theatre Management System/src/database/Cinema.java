package database;

import java.sql.*;

import controller.ManageController;
import model.CinemaDatabase;
import model.Location;
import model.Screen;
import model.ShowTime;
import model.Theatre;
import model.User;
import model.UserBookings;

public class Cinema {
	private static Cinema database;
	private User user;
	private ManageController manage;
	private CinemaDatabase cinemaDB = CinemaDatabase.getInstance();
	private Statement statement;
	private UserBookings userBookings;
	private Theatre theatre;
	private Location location;
	private ShowTime shows;
	private Screen screen;

	public static Cinema getInstance() {
		if (database == null) {
			database = new Cinema();
		}
		return database;
	}

	public Statement setConnection() {
		String url = "jdbc:mysql://localhost:3306/cinema";
		String username = "root";
		String pwd = "mugi123";
		try {
			Connection con = DriverManager.getConnection(url, username, pwd);
			statement = con.createStatement();
			return statement;
		} catch (Exception e) {
			System.out.println("Connection Failed");
		}
		return null;
	}

	public void setData(String query) throws SQLException {
		statement = setConnection();
		try {
			int i = statement.executeUpdate(query);
			if (i > 0) {
				System.out.println("Registered successfully");
			} else {
				System.out.println("Sorry for the inconveniencce....Cannot able to register !");
			}

		} catch (SQLException e) {
			throw new SQLException(); 
		}
	}

	public void getUsersData() {
		statement = setConnection();
		try {
			ResultSet rs = statement.executeQuery("select * from cinema.users");
			while (rs.next()) {
				user = new User(rs.getString(1), rs.getString(2), rs.getString(3));
				cinemaDB.addUserList(user);
			}
		} catch (SQLException e) {
			System.out.println("Cannot retrieve data from Database");
		}
	}

	public void getUserbookingsData() {
		statement = setConnection();
		try {
			ResultSet rs = statement.executeQuery("select * from cinema.userbookings");
			while (rs.next()) {
				userBookings = new UserBookings(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getString(4),
						rs.getInt(6));
				manage = getObject();
				userBookings.setBookTime(rs.getTimestamp(5));
				user = manage.checkUser(userBookings.getUserName());
				user.setBooking(userBookings);
			}
		} catch (SQLException e) {
			System.out.println("Cannot retrieve data from Database");
		}
	}

	public void getLocationData() {
		statement = setConnection();
		try {
			ResultSet rs = statement.executeQuery("select * from cinema.location");
			while (rs.next()) {
				location = new Location(rs.getInt(1), rs.getString(2));
				cinemaDB.addLocationList(location);
			}
		} catch (SQLException e) {
			System.out.println("Cannot retrieve data from Database");
		}
	}

	public void getTheatreData() {
		statement = setConnection();
		try {
			ResultSet rs = statement.executeQuery("select * from cinema.theatrelist");
			while (rs.next()) {
				theatre = new Theatre(rs.getInt(1), rs.getString(2), rs.getInt(3));
				cinemaDB.addTheatreList(theatre);
				manage = getObject();
				location = manage.checkLocation(theatre.getLocationId());
				location.setTheatre(theatre);
			}
		} catch (SQLException e) {
			System.out.println("Cannot retrieve data from Database");
		}
	}

	public void getScreenData() {
		statement = setConnection();
		try {
			ResultSet rs = statement.executeQuery("select * from cinema.screenlist");
			while (rs.next()) {
				screen = new Screen(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
				cinemaDB.addScreenList(screen);
				manage = getObject();
				theatre = manage.checkTheatre(screen.getTheatreId());
				theatre.setScreen(screen);
			}
		} catch (SQLException e) {
			System.out.println("Cannot retrieve data from Database");
		}
	}

	public void getShowsData() {
		statement = setConnection();
		try {
			ResultSet rs = statement.executeQuery("select * from cinema.shows");
			while (rs.next()) {
				shows = new ShowTime(rs.getInt(1), rs.getString(2), rs.getString(3));
				cinemaDB.addShowsList(shows);
				manage = getObject();
				screen = manage.checkScreen(shows.getScreenId());
				screen.setShowTime(shows);
			}
		} catch (SQLException e) {
			System.out.println("Cannot retrieve data from Database");
		}
	}

	private ManageController getObject() {
		if (manage == null) {
			manage = new ManageController();
		}
		return manage;
	}

}
