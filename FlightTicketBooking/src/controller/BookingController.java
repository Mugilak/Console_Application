package controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import model.Bookings;
import model.FlightRoutes;
import model.Passenger;
import repository.FlightDB;
import view.AdminManage;
import view.PassengerManage;

public class BookingController {
	private static long pnr = 34564777;
	private static int id = 2000;
	private FlightDB flightDB = FlightDB.getInstance();
	private PassengerManage manage;
	private AdminManage adminManage;

	public BookingController(AdminManage adminManage) {
		this.adminManage = adminManage;
	}

	public BookingController(PassengerManage manage) {
		this.manage = manage;
	}

	public Set<FlightRoutes> getAvailableFlights(String from, String to) {
		Set<FlightRoutes> answer = new HashSet<>();
		List<FlightRoutes> routeList = flightDB.getRoutesList();
		for (FlightRoutes route : routeList) {
			List<String> locations = route.getRoutes();
			if (locations.contains(from.toLowerCase()) && locations.contains(to.toLowerCase())) {
				answer.add(route);
			}
		}
		return answer;
	}

	public Passenger addPassenger(String name, String age, String gender, FlightRoutes flight) {
		if (flight.getVacantSeats() > 0) {
			Passenger passenger = new Passenger(++id, name, age, gender, "CNF", flight.getFlightId());
			flightDB.setPassengerlist(passenger);
			return passenger;
		} else {
			manage.alert("Flight seats already filled");
		}
		return null;
	}

	public FlightRoutes getFlight(Integer flightId) throws NumberFormatException {
		List<FlightRoutes> flightsList = flightDB.getRoutesList();
		for (FlightRoutes flight : flightsList) {
			if (flight.getFlightId() == flightId) {
				return flight;
			}
		}
		return null;
	}

	public Bookings bookTicket(int amount, String from, String to, FlightRoutes flight, Stack<Passenger> user,
			int totalPassenger) {
		Bookings book = new Bookings(pnr++, flight.getFlightId(), totalPassenger, amount, from, to, "CNF");
		book.setPassenger(user);
		flightDB.setBookingList(book);
		return book;
	}

	public Bookings getBookingDetail(Long pnr) {
		List<Bookings> bookingsList = flightDB.getBookingList();
		for (Bookings booking : bookingsList) {
			if (booking.getPnrNo() == pnr) {
				return booking;
			}
		}
		return null;
	}

	public Passenger getPassenger(Integer id) {
		List<Passenger> passengerList = flightDB.getPassengerlist();
		for (Passenger passenger : passengerList) {
			if (passenger.getPassengerId() == id) {
				return passenger;
			}
		}
		return null;
	}

	public void setStatus(String status, Bookings book) {
		book.setStatus(status);
		adminManage.alert("Ticket Status Updated as  " + status);
		FlightRoutes flight = getFlight(book.getFlightId());
		manage.showTicketStatus(book, flight);
	}

	public int cancel(Bookings book) {
		int size = book.getPassenger().size();
		int amount = book.getAmount();
		FlightRoutes flight = getFlight(book.getFlightId());
		flight.setVacantSeats(size + flight.getVacantSeats());
		List<Passenger> passengerList = book.getPassenger();
		removePassenger(passengerList);
		flightDB.removeBooking(book);
		return amount;
	}

	private void removePassenger(List<Passenger> passengerList) {
		for (Passenger passenger : passengerList) {
			flightDB.removePassenger(passenger);
		}
	}

}
