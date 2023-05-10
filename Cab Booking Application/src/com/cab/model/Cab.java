package com.cab.model;

import java.util.*;

public class Cab {
	private int cabNo, freeTime, earnings;
	private char presentAt;
	private Stack<Bookings> bookings;

	public Cab(int cabNo, char presentAt) {
		this.cabNo = cabNo;
		this.presentAt = presentAt;
		this.bookings = new Stack<Bookings>();
	}

	public int getCabNo() {
		return cabNo;
	}

	public void setCabNo(int cabNo) {
		this.cabNo = cabNo;
	}

	public int getFreeTime() {
		return freeTime;
	}

	public void setFreeTime(int freeTime) {
		this.freeTime = freeTime;
	}

	public Stack<Bookings> getBookings() {
		return bookings;
	}

	public void setBookings(Bookings bookings) {
		this.bookings.push(bookings);
	}

	public int getEarnings() {
		return earnings;
	}

	public void addEarnings(int earnings) {
		this.earnings += earnings;
	}

	public char getPresentAt() {
		return presentAt;
	}

	public void setPresentAt(char presentAt) {
		this.presentAt = presentAt;
	}

}
