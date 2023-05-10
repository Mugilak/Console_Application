package view;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import controller.BookingController;
import model.Bookings;
import model.FlightRoutes;
import model.Passenger;

public class PassengerManage {
	private BookingController booking;
	private Stack<Passenger> user;
	private FlightRoutes flight;
	private Scanner input = new Scanner(System.in);
	private Passenger users;
	private String from, to, flightId, name, age, id, gender;

	public PassengerManage() {
		booking = new BookingController(this);
	}

	public void initBooking() {
		procced();
	}

	private void procced() {
		System.out.println("### Plan My Journey ###");
		System.out.println("Enter From Station : ");
		from = input.nextLine();
		System.out.println("Enter To Station : ");
		to = input.nextLine();
		if (!from.equalsIgnoreCase(to)) {
			Set<FlightRoutes> flightsAvail = booking.getAvailableFlights(from, to);
			if (flightsAvail != null || flightsAvail.size() > 0) {
				showFlights(flightsAvail);
				System.out.println("Enter Flight no. :");
				flightId = input.nextLine();
				flight = booking.getFlight(Integer.valueOf(flightId));
				if (flight != null) {
					int totalPassenger = addPassenger(flight);
					bookTicket(flight, totalPassenger);
				}
			} else
				System.out.println("No flights are available right now");
		} else
			System.out.println("Invalid ... From and To locations Are Same !");
		System.out.println("-----------------\n");
	}

	private void bookTicket(FlightRoutes flight, int totalPassenger) {
		int amount = flight.getFare() * totalPassenger;
		System.out.println("Total fare : " + amount);
		System.out.println("""
				Pay :
				(1 - Pay , 2-Cancel , 3- reschedule)
				""");
		String choice = input.nextLine();
		switch (choice) {
		case "1":
			Bookings book = booking.bookTicket(amount, from, to, flight, user, totalPassenger);
			System.out.println("Ticket(s) booked Succesfully\n PNR  no. : " + book.getPnrNo() + "\n");
			showTicketStatus(book, flight);
			break;
		case "2":
			System.out.println("Cancelled....Thankyou for using Application");
			break;
		case "3":
			System.out.println("Enter date to reschedule : ");
			String date = input.nextLine();
			System.out.println("Your data are drafted ... you can book it by later");
			break;
		default:
			System.out.println("Invalid number ");
		}
		System.out.println("-----------------\n");
	}

	public void showTicketStatus(Bookings book, FlightRoutes flight) {
		System.out.println("Ticket Details  \n\nFlight Details :\n----------------");
		System.out.println("Flight Id : " + book.getFlightId() + " || Flight name: " + flight.getFlightName()
				+ " || Seats Booked : " + book.getSeatsBooked() + "");
		System.out.println("Arrival : " + flight.getArrival() + " || Departure : " + flight.getDeparture()
				+ " || fare :  " + flight.getFare());
		System.out
				.println("From : " + book.getFrom() + " || To : " + book.getTo() + " || PNR no. : " + book.getPnrNo());
		System.out.println("\npassenger Details : \n ----------------");
		List<Passenger> passenger = book.getPassenger();
		for (Passenger user : passenger) {
			System.out.println("Id: " + user.getPassengerId() + " || Name : " + user.getName() + " || Age : "
					+ user.getAge() + " || Gender : " + user.getGender());
		}
		System.out.println("---------------\n");
	}

	private int addPassenger(FlightRoutes flight) {
		user = new Stack<>();
		System.out.println("Enter number of Passenger : ");
		String count = input.nextLine();
		for (int i = 1; i <= Integer.valueOf(count); i++) {
			System.out.println("Passenger : " + i);
			getPassengerDetails();
			users = booking.addPassenger(name, age, gender, flight);
			if (users != null) {
				System.out.println("passenger " + i + " id :" + users.getPassengerId() + "created");
				user.push(users);
			}
		}
		return Integer.valueOf(count);
	}

	private void getPassengerDetails() {
		System.out.println("Enter Passenger Name : ");
		name = input.nextLine();
		System.out.println("enter Age : ");
		age = input.nextLine();
		System.out.println("Enter Gender : ");
		gender = input.nextLine();
	}

	private void showFlights(Set<FlightRoutes> flightsAvail) {
		System.out.println("Available Flights : \n--------------");
		for (FlightRoutes routes : flightsAvail) {
			System.out.println("Flight Id : " + routes.getFlightId() + " || Flight name: " + routes.getFlightName()
					+ " || total Seats : " + routes.getSeats() + "");
			System.out.println("Arrival : " + routes.getArrival() + " || Departure : " + routes.getDeparture()
					+ " || fare :  " + routes.getFare() + "\n\n");
		}
		System.out.println("--------------");
	}

	public void alert(String string) {
		System.out.println(string);
	}

	public void getStatus() throws NumberFormatException {
		System.out.println("Enter PNR no. : ");
		String pnr = input.nextLine();
		Bookings book = booking.getBookingDetail(Long.valueOf(pnr));
		if (book != null) {
			flight = booking.getFlight(book.getFlightId());
			showTicketStatus(book, flight);
		} else
			System.out.println("Entered Wrong PNR no.");
		System.out.println("-----------------\n");
	}

	public void searchPassenger() throws NumberFormatException {
		System.out.println("Enter Passenger Id : ");
		id = input.nextLine();
		users = booking.getPassenger(Integer.valueOf(id));
		if (users != null) {
			System.out.println("\nPassenger Detail : \n-------------");
			System.out.println("Id: " + users.getPassengerId() + " || Name : " + users.getName() + " || Age : "
					+ users.getAge() + " || Gender : " + users.getGender());
		} else
			System.out.println("Invalid Passenger Id / no passenger in this ID");
		System.out.println("-----------------\n");
	}

	public void cancelTicket() {
		System.out.println("Enter PNR no. : ");
		String pnr = input.nextLine();
		Bookings book = booking.getBookingDetail(Long.valueOf(pnr));
		if (book != null) {
			System.out.println("Do You wnat to cancel Ticket :");
			String choice = input.nextLine();
			if (choice.equalsIgnoreCase("YES")) {
				int amount = booking.cancel(book);
				System.out.println("Ticket Cancelled Successfully.Your refund amount of Rs. " + amount
						+ " will be processed soon.");
			} else if (choice.equalsIgnoreCase("NO")) {
				System.out.println("Thankyou");
			} else {
				System.out.println("Wrong input");
			}
		} else
			System.out.println("Entered Wrong PNR no.");
		System.out.println("-----------------\n");
	}

}
