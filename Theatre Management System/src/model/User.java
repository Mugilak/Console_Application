package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class User {
	private String userName;
	private String password;
	private String address;
	private Stack<UserBookings> booking;

	public User(String userName, String password, String address) {
		this.userName = userName;
		this.password = password;
		this.address = address;
	}

	public void setBooking(UserBookings booking) {
		this.booking.push(booking);
	}

	public List<UserBookings> getBookingList() {
		List<UserBookings> list = new ArrayList<UserBookings>();
		Iterator<UserBookings> itr = booking.iterator();
		while (itr.hasNext()) {
			list.add((UserBookings) itr.next());
		}
		return list;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}
}
