package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import model.FlightRoutes;
import repository.FlightDB;

public class FlightInputController {
	private FlightDB flightDB = FlightDB.getInstance();
	private FlightRoutes route;

	public boolean isFlightsCreated() {
		if (FlightDB.getInstance().getRoutesList().size() > 0) {
			return true;
		}
		return false;
	}

	public void getFlightsInput() {
		File file = new File("C:\\Users\\91638\\eclipse-workspace\\FlightTicketBooking\\src\\model\\input.txt");
		Queue<String> input = new LinkedList<>();
		List<String> list = new ArrayList<>();
		try {
			Scanner sc = new Scanner(file);
			for (int i = 1; sc.hasNext() && i <= 7; i++) {
				String currentLine = sc.nextLine();
				if (i != 5) {
					input.offer(currentLine);
				} else {
					list.addAll(Arrays.asList(currentLine.split(",")));
				}
				if (i == 7) {
					route = new FlightRoutes(Integer.valueOf(input.poll()), input.poll(), Double.valueOf(input.poll()),
							Double.valueOf(input.poll()), Integer.valueOf(input.poll()), Integer.valueOf(input.poll()));
					route.addRoute(list);
					flightDB.setRoutesList(route);
					list.clear();
					input.clear();
					i = 0;
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found !");
		}
	}

	public void addFlight(String flightId, String flightName, String seats, String departure, String arrival,
			String fare, String stops, List<String> routes) {

	}

	public boolean isValid(String word, String regex) {
		if (word.matches(regex))
			return true;
		return false;
	}

}
