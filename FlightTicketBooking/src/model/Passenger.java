package model;

import java.util.List;

public class Passenger {
	private int passengerId;
	private String name;
	private String age;
	private String gender;
	private String status;
	private int flightId;
	private List<Bookings> bookings;

	public Passenger(int id, String name, String age, String gender, String status, int flightId) {
		this.passengerId = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.flightId = flightId;
	}

	public int getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String dateOfBirth) {
		this.age = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Bookings> getBookings() {
		return bookings;
	}

	public void setBookings(Bookings bookings) {
		this.bookings.add(bookings);
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public void setBookings(List<Bookings> bookings) {
		this.bookings = bookings;
	}

}
