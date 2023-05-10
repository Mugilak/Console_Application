package model;

import java.util.ArrayList;
import java.util.List;

public class FlightRoutes {
	private int flightId;
	private String flightName;
	private int seats;
	private int vacantSeats;
	private List<String> routes;
	private int fare;
	private double departure;
	private double arrival;

	public FlightRoutes(int flightId, String flightName, double departure, double arrival, int seats, int fare) {
		this.flightId = flightId;
		this.flightName = flightName;
		this.seats = seats;
		this.vacantSeats = seats;
		this.fare = fare;
		this.departure = departure;
		this.arrival = arrival;
		routes = new ArrayList<>();
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public List<String> getRoutes() {
		return routes;
	}

	public void setRoutes(List<String> routes) {
		this.routes = routes;
	}

	public void addRoute(List<String> routes) {
		this.routes.addAll(routes);
	}

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}

	public double getDeparture() {
		return departure;
	}

	public void setDeparture(double departure) {
		this.departure = departure;
	}

	public double getArrival() {
		return arrival;
	}

	public void setArrival(double arrival) {
		this.arrival = arrival;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public int getVacantSeats() {
		return vacantSeats;
	}

	public void setVacantSeats(int unUsedSeats) {
		this.vacantSeats = unUsedSeats;
	}

}
