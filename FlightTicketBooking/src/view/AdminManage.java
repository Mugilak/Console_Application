package view;

import java.util.List;
import java.util.Scanner;

import controller.BookingController;
import model.Bookings;
import model.FlightRoutes;
import model.Passenger;
import repository.FlightDB;

public class AdminManage {
	private Scanner input = new Scanner(System.in);
	private FlightDB flightDB = FlightDB.getInstance();
	private FlightRoutes flight;
	private BookingController booking;
	private FlightDB data = FlightDB.getInstance();

	public AdminManage() {
		booking = new BookingController(this);
//		passengerManage = new PassengerManage();
	}

	public void getTotalTickets() {
		List<Bookings> bookingList = flightDB.getBookingList();
		System.out.println("Tickets Booked : " + bookingList.size());
		printDetails(bookingList);
	}

	private void printDetails(List<Bookings> bookingList) {
		for (Bookings book : bookingList) {
			flight = booking.getFlight(book.getFlightId());
			System.out.println("Ticket Details : \nFlight Details :\n----------------\n");
			System.out.println("Flight Id : " + book.getFlightId() + " || Flight name: " + flight.getFlightName()
					+ " || Seats Booked : " + book.getSeatsBooked() + "");
			System.out.println("Arrival : " + flight.getArrival() + " || Departure : " + flight.getDeparture()
					+ " || fare :  " + flight.getFare() + "\n\n");
			System.out.println(
					"From : " + book.getFrom() + " || To : " + book.getTo() + " || PNR no. : " + book.getPnrNo());
			System.out.println("\npassenger Details : \n ----------------");
			List<Passenger> passenger = book.getPassenger();
			for (Passenger user : passenger) {
				System.out.println("Id: " + user.getPassengerId() + " || Name : " + user.getName() + " || Age : "
						+ user.getAge() + " || Gender : " + user.getGender());
			}
		}
	}

	public void changeStatus() throws NumberFormatException {
		System.out.println("Enter PNR no. : ");
		String pnr = input.nextLine();
		Bookings book = booking.getBookingDetail(Long.valueOf(pnr));
		if (book != null) {
			System.out.println("Enter Ticket Status : \n(1. CNF  , 2. CANCEL)");
			String choice = input.nextLine();
			if (choice.equals("1")) {
				booking.setStatus("CNF", book);
			} else if (choice.equals("2")) {
				booking.setStatus("CANCELLED", book);
			} else {
				System.out.println("Invalid input !");
			}
		} else
			System.out.println("Invalid Input ");
	}

	public void alert(String string) {
		System.out.println(string);
	}

	public void listFlight() {
		System.out.println("\nFlight Routes \n-----------------");
		List<FlightRoutes> routes = data.getRoutesList();
		for (FlightRoutes route : routes) {
			System.out.println("Flight Id : " + route.getFlightId() + " || Flight name: " + route.getFlightName()
					+ " || total Seats : " + route.getSeats() + "");
			System.out.println("Arrival : " + route.getArrival() + " || Departure : " + route.getDeparture()
					+ " || fare :  " + route.getFare());
			System.out.println("VacantSeats : " + route.getVacantSeats() + "\n Routes : " + route.getRoutes());
			System.out.println("-----------------\n");
		}
	}
}
