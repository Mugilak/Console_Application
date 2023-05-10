package com.cab.model;

public class Bookings {
	private int bookingId, customerId, pickupTime, dropTime, amount;
	private char from, to;

	public Bookings(int bookingId, int customerId, char from, char to, int pickupTime, int dropTime, int amount) {
		this.bookingId = bookingId;
		this.customerId = customerId;
		this.from = from;
		this.to = to;
		this.pickupTime = pickupTime;
		this.dropTime = dropTime;
		this.amount = amount;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(int pickupTime) {
		this.pickupTime = pickupTime;
	}

	public int getDropTime() {
		return dropTime;
	}

	public void setDropTime(int dropTime) {
		this.dropTime = dropTime;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public char getFrom() {
		return from;
	}

	public void setFrom(char from) {
		this.from = from;
	}

	public char getTo() {
		return to;
	}

	public void setTo(char to) {
		this.to = to;
	}

}
