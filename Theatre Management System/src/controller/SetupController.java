package controller;

import database.Cinema;
import model.CinemaDatabase;

public class SetupController {
	private Cinema cinema = Cinema.getInstance();

	public void getAdminDB() {
		cinema.getLocationData();
		cinema.getTheatreData();
		cinema.getScreenData();
		cinema.getShowsData();
	}

	public void getUserDB() {
		cinema.getUsersData();
		cinema.getUserbookingsData();
	}

	public boolean isSetUped() {
		int Location = CinemaDatabase.getInstance().getLocationList().size();
		if (Location > 0) {
			return true;
		}
		return false;
	}
}
