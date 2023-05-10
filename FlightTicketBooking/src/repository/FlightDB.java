package repository;

import java.util.ArrayList;
import java.util.List;

import model.Bookings;
import model.FlightRoutes;
import model.Passenger;

public class FlightDB {
	private static FlightDB flightDB;
	private List<FlightRoutes> routeList;
	private List<Bookings> bookingList;
	private List<Passenger> passengerList;

	private FlightDB() {
		routeList = new ArrayList<>();
		bookingList = new ArrayList<>();
		passengerList = new ArrayList<>();
	}

	public static FlightDB getInstance() {
		if (flightDB == null) {
			flightDB = new FlightDB();
		}
		return flightDB;
	}

	public List<Bookings> getBookingList() {
		return bookingList;
	}

	public void setBookingList(Bookings booking) {
		this.bookingList.add(booking);
	}

	public void removeBooking(Bookings booking) {
		this.bookingList.remove(booking);
	}

	public void removePassenger(Passenger passenger) {
		this.passengerList.remove(passenger);
	}

	public List<Passenger> getPassengerlist() {
		return passengerList;
	}

	public void setPassengerlist(Passenger passenger) {
		this.passengerList.add(passenger);
	}

	public List<FlightRoutes> getRoutesList() {
		return routeList;
	}

	public void setRoutesList(FlightRoutes routesList) {
		this.routeList.add(routesList);
	}

}
