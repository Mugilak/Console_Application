package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.FlightInputController;

public class CreatingFlight {
	private Scanner input = new Scanner(System.in);
	private FlightInputController flightController;
	private String flightId, flightName, departure, arrival, seats, fare, stops;
	private List<String> routes;

	public CreatingFlight() {
		flightController = new controller.FlightInputController();
		routes = new ArrayList<>();
	}

	public void createFlight() {
		flightController.getFlightsInput();
	}

	public void createFlight(int i) {
		createSchedules();
	}

	private void createSchedules() {
		System.out.println("Enter No. of Schedules : ");
		String schedules = input.nextLine();
		for (int index = 1; index <= Integer.valueOf(schedules); index++) {
			System.out.println("\nSchedule No. " + index);
			routes = new ArrayList<>();
			getInput();
			flightController.addFlight(flightId, flightName, seats, departure, arrival, fare, stops, routes);
		}
		System.out.println("Flights are Successfully Created !");
	}

	private void getInput() {
		String location;
		System.out.println("Flight No. : ");
		flightId = check(input.nextLine(), "^[0-9]{4}+$", "Invalid Flight No ...  (Ex : 2134)");
		System.out.println("FlightName : ");
		flightName = check(input.nextLine(), "^[a-zA-Z]+$", "Invalid Flight Name ...  (Ex : ABc)");
		System.out.println("Enter Total Seats : ");
		seats = check(input.nextLine(), "^[0-9]+$", "Invalid Seats No ...  (Ex : 213)");
		System.out.println("Enter Departure time : ");
		departure = input.nextLine();
		System.out.println("Enter Arrival time : ");
		arrival = input.nextLine();
		System.out.println("Enter fare : ");
		fare = check(input.nextLine(), "^[0-9]+$", "Invalid fare ...  (Ex : 2134)");
		System.out.println("Enter No.of Stops : ");
		stops = input.nextLine();
		for (int index2 = 0; index2 < Integer.valueOf(stops); index2++) {
			location = input.nextLine();
			routes.add(location);
		}
	}

	private String check(String word, String regex, String print) {
		while (!flightController.isValid(word, regex)) {
			System.out.println(print + "Enter again : ");
			word = input.nextLine();
		}
		return word;
	}

}
