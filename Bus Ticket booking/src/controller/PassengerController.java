package controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Stack;

import model.Bookings;
import model.Bus;
import model.BusBookingDatabase;

public class PassengerController {
	private BusBookingDatabase busDB = BusBookingDatabase.getInstance();
	private String query;

	public List<Bus> getBuses(String boarding, String dropping) {
		List<Bus> availableBuses = new ArrayList<>();
		List<Bus> busList = busDB.getBusList();
		for (Bus eachBus : busList) {
			if (eachBus.getBoardingPoint().compareTo(boarding) <= 0
					&& eachBus.getDroppingPoint().compareTo(dropping) >= 0) {
				availableBuses.add(eachBus);
			}
		}
		return availableBuses;
	}

	public Bus isBusExists(int busId) {
		List<Bus> busList = busDB.getBusList();
		for (int index = 0; index < busList.size(); index++) {
			Bus eachBus = busList.get(index);
			if (eachBus.getBusId() == busId) {
				return eachBus;
			}
		}
		return null;
	}

	public int bookTicket(String userName, String boarding, String dropping, int seats, int paid, int busId, Bus bus)
			throws SQLException {
		String entry = new SimpleDateFormat("EEE / dd-MMM-YYYY / hh:mm:ss aa").format(Calendar.getInstance().getTime());
		if(bus.getAvailableSeats()<=seats) {
			return -1;
		}
		Bookings bookings = new Bookings(userName, entry, boarding, dropping, seats, paid, busId);
		int availableSeats = Math.abs(bus.getAvailableSeats() - seats);
		int unavailableSeats = bus.getUnavailableSeats() + seats;
		bus.setAvailableSeats(availableSeats);
		bus.setUnavailableSeats(unavailableSeats);
		busDB.updateQuery(bus);
		busDB.setBookingsList(bookings);
		busDB.setBookingsList(bookings, query);
		return bookings.getBookId();
	}

	public Stack<Bookings> getBookingList(String userName) {
		Stack<Bookings> booking = new Stack<>();
		List<Bookings> bookingList = busDB.getBookingsList();
		for (Bookings list : bookingList) {
			if (list.getUserName().equals(userName)) {
				booking.push(list);
			}
		}
		return booking;
	}

	public Bookings isIdAvailable(Integer id) {
		List<Bookings> bookingList = busDB.getBookingsList();
		for (Bookings list : bookingList) {
			if (list.getBookId() == id) {
				return list;
			}
		}
		return null;
	}

	public int cancelTicket(Bookings booking, Bus bus) throws SQLException {
		bus.setAvailableSeats(bus.getAvailableSeats() + booking.getSeatsBooked());
		bus.setUnavailableSeats(bus.getUnavailableSeats() - booking.getSeatsBooked());
		booking.setCanceled(true);
		busDB.updateQuery(bus);
		busDB.updateQuery(booking);
		return booking.getPaid();
	}

}
