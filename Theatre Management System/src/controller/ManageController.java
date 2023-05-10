package controller;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import database.Cinema;
import model.CinemaDatabase;
import model.Location;
import model.Screen;
import model.ShowTime;
import model.Theatre;
import model.User;
import view.AdminManage;

public class ManageController {
	private CinemaDatabase cinemaDB = CinemaDatabase.getInstance();

	private String query = "";

	public User checkUser(String userName) {
		List<User> users = cinemaDB.getUserList();
		for (int index = 0; index < users.size(); index++) {
			User eachUser = users.get(index);
			if (eachUser.getUserName().equals(userName)) {
				return eachUser;
			}
		}
		return null;
	}

	public Location checkLocation(int locationId) {
		List<Location> locations = cinemaDB.getLocationList();

		for (int index = 0; index < locations.size(); index++) {
			Location eachLocation = locations.get(index);
			if ((eachLocation.getLocationId()) == (locationId)) {
				return eachLocation;
			}
		}
		return null;
	}

	public Location checkLocation(String location) {
		List<Location> locations = cinemaDB.getLocationList();

		for (int index = 0; index < locations.size(); index++) {
			Location eachLocation = locations.get(index);
			if ((eachLocation.getLocation().equalsIgnoreCase(location))) {
				return eachLocation;
			}
		}
		return null;
	}

	public Theatre checkTheatre(int theatreId) {
		List<Theatre> theatre = cinemaDB.getTheatreList();

		for (int index = 0; index < theatre.size(); index++) {
			Theatre eachTheatre = theatre.get(index);
			if (eachTheatre.getTheatreId() == theatreId) {
				return eachTheatre;
			}
		}
		return null;
	}

	public Screen checkScreen(int screenId) {
		List<Screen> screens = cinemaDB.getScreenList();

		for (int index = 0; index < screens.size(); index++) {
			Screen eachScreen = screens.get(index);
			if (eachScreen.getScreenId() == screenId) {
				return eachScreen;
			}
		}
		return null;
	}

	public Screen checkScreen(String screenName) {
		List<Screen> screens = cinemaDB.getScreenList();
		if (screens.size() == 0) {
			return null;
		}

		for (int index = 0; index < screens.size(); index++) {
			Screen eachScreen = screens.get(index);
			if (eachScreen.getScreenName().equalsIgnoreCase(screenName)) {
				return eachScreen;
			}
		}
		return null;
	}

	public boolean isValid(String regex, String word) {
		Pattern pattern = Pattern.compile(regex);
		Matcher match = pattern.matcher(word);
		if (match.find()) {
			return true;
		}
		return false;
	}

	public boolean updateMovies(String screenId, String showTime, String movieName, Screen screen) throws SQLException {
		showTime = giveTime(showTime);
		List<ShowTime> showList = cinemaDB.getShowsList();
		for (int index = 0; index < showList.size(); index++) {
			ShowTime eachShow = showList.get(index);
			if (eachShow.getShowTime().equals(showTime)) {
				addShows(screenId, showTime, movieName, screen);
				return true;
			}
		}
		return false;
	}

	private String giveTime(String showTime) {
		if (showTime.equals("1")) {
			return "8:00";
		} else if (showTime.equals("2")) {
			return "12:00";
		} else if (showTime.equals("3")) {
			return "16:00";
		} else if (showTime.equals("4")) {
			return "20:30";
		} else if (showTime.equals("5")) {
			return "1:00";
		}
		return null;
	}

	public void addShows(String screenId, String showTime, String movieName, Screen screen) throws SQLException {
		showTime = giveTime(showTime);
		ShowTime shows = new ShowTime(Integer.valueOf(screenId), showTime, movieName);
		screen.setShowTime(shows);
		cinemaDB.addShowsList(shows);
		cinemaDB.addShowsList(shows, query);
	}

	public void addScreen(Theatre theatre, String theatreId, String screenId, String screenName, String noOfSeats)
			throws SQLException {
		Screen screen = new Screen(Integer.valueOf(theatreId), Integer.valueOf(screenId), screenName,
				Integer.valueOf(noOfSeats));
		theatre.setScreen(screen);
		cinemaDB.addScreenList(screen);
		cinemaDB.addScreenList(screen, query);
	}

	public void addTheatre(String theatreId, String theatreName, String locationId, Location locations)
			throws SQLException {
		Theatre theatre = new Theatre(Integer.valueOf(theatreId), theatreName, Integer.valueOf(locationId));
		locations.setTheatre(theatre);
		cinemaDB.addTheatreList(theatre);
		cinemaDB.addTheatreList(theatre, query);
	}

	public void addLocation(String locationId, String location) throws SQLException {
		Location locations = new Location(Integer.valueOf(locationId), location);
		cinemaDB.addLocationList(locations);
		cinemaDB.addLocationList(locations, query);
	}

	public boolean isUsedShows(String showTime) {
		showTime = giveTime(showTime);
		List<ShowTime> showList = cinemaDB.getShowsList();
		for (int index = 0; index < showList.size(); index++) {
			ShowTime eachShow = showList.get(index);
			if (eachShow.getShowTime().equals(showTime)) {
				return true;
			}
		}
		return false;
	}

}
