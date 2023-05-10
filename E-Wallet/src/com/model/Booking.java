package com.model;

public class Booking {
	private int utsNo;
	private int passengers;
	private String from, to;
	private int amount;
	private String start, end;
	private static int revenue;

	public Booking(int utsNo, int passengers, String from, String to, int amount, String start, String end) {
		this.utsNo = utsNo;
		this.passengers = passengers;
		this.amount = amount;
		this.from = from;
		this.to = to;
		this.start = start;
		this.end = end;
	}

	public int getUtsNo() {
		return utsNo;
	}

	public void setUtsNo(int utsNo) {
		this.utsNo = utsNo;
	}

	public int getPassengers() {
		return passengers;
	}

	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public static int getRevenue() {
		return revenue;
	}

	public static void setRevenue(int revenue) {
		revenue += revenue;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
}
