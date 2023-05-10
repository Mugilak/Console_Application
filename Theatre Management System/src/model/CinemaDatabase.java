package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Cinema;

public class CinemaDatabase {
	private static CinemaDatabase cinemaDB;
	private List<Location> locationList;
	private List<User> userList;
	private List<Admin> adminList;
	private List<Theatre> theatreList;
	private List<Screen> screenList;
	private List<ShowTime> showTimeList;

	private CinemaDatabase() {
		locationList = new ArrayList<Location>();
		userList = new ArrayList<User>();
		adminList = new ArrayList<Admin>();
		theatreList = new ArrayList<Theatre>();
		screenList = new ArrayList<Screen>();
		showTimeList = new ArrayList<ShowTime>();
	}

	public static CinemaDatabase getInstance() {
		if (cinemaDB == null) {
			cinemaDB = new CinemaDatabase();
		}
		return cinemaDB;
	}

	public void addLocationList(Location location) {
		this.locationList.add(location);
	}

	public void addLocationList(Location location, String query) throws SQLException {
		query = "insert into location " + "values('" + location.getLocationId() + "','" + location.getLocation() + "')";
		Cinema.getInstance().setData(query);
	}

	public void deleteLocationList(Location location) {
		this.locationList.remove(location);
	}

	public List<Location> getLocationList() {
		return locationList;
	}

	public void addUserList(User user) {
		this.userList.add(user);
	}

	public void deleteUserList(User user) {
		this.userList.remove(user);
	}

	public List<User> getUserList() {
		return userList;
	}

	public void addAdminList(Admin admin) {
		this.adminList.add(admin);
	}

	public void deleteAdminList(Admin admin) {
		this.adminList.remove(admin);
	}

	public List<Admin> getAdminList() {
		return adminList;
	}

	public void addTheatreList(Theatre theatre) {
		this.theatreList.add(theatre);
	}

	public void addTheatreList(Theatre theatre, String query) throws SQLException {
		query = "insert into theatrelist " + "values('" + theatre.getTheatreId() + "','" + theatre.getTheatreName()
				+ "','" + theatre.getLocationId() + "')";
		Cinema.getInstance().setData(query);
	}

	public void deleteTheatreList(Theatre theatre) {
		this.theatreList.remove(theatre);
	}

	public List<Theatre> getTheatreList() {
		return theatreList;
	}

	public void addScreenList(Screen screen) {
		this.screenList.add(screen);
	}

	public void addScreenList(Screen screen, String query) throws SQLException {
		query = "insert into cinema.screenlist " + "values('" + screen.getTheatreId() + "','" + screen.getScreenId()
				+ "','" + screen.getScreenName() + "','" + screen.getSeats() + "')";
		Cinema.getInstance().setData(query);
	}

	public void deleteScreenList(Screen screen) {
		this.screenList.remove(screen);
	}

	public List<Screen> getScreenList() {
		return screenList;
	}

	public void addShowsList(ShowTime shows) {
		this.showTimeList.add(shows);
	}

	public void addShowsList(ShowTime shows, String query) throws SQLException {
		query = "insert into shows " + "values('" + shows.getScreenId() + "','" + shows.getShowTime() + "','"
				+ shows.getMovieName() + "')";
		Cinema.getInstance().setData(query);
	}

	public void deleteShowsList(ShowTime shows) {
		this.showTimeList.remove(shows);
	}

	public List<ShowTime> getShowsList() {
		return showTimeList;
	}

}
