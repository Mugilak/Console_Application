package controller;

import database.BusBooking;
import model.BusBookingDatabase;

public class SetupController {
	private BusBooking database = BusBooking.getInstance();

	public void getUserDB() {
		database.getPassengerData();
		database.getBookingsData();
	}

	public boolean isSetUped() {
		int bus = BusBookingDatabase.getInstance().getBusList().size();
		if (bus > 0) {
			return true;
		}
		return false;
	}

	public void getAdminDB() {
		database.getBusData();
	}
}
