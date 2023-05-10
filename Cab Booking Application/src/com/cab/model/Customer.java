package com.cab.model;

import java.util.Stack;

public class Customer {
	private int customerId;
	private Stack<Bookings> bookings;

	private Customer(int customerId) {
		this.bookings = new Stack<Bookings>();
		this.customerId = customerId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Stack<Bookings> getBookings() {
		return bookings;
	}

	public void setBookings(Bookings bookings) {
		this.bookings.push(bookings);
	}

}
