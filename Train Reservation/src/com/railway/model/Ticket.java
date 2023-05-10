package com.railway.model;

import java.util.*;

public class Ticket {
	private static int id = 1;
	private String pnr = "VGSFE06322100" + id;
	private int members;
	private List<Passenger> passengerList;

	public Ticket(int size) {
		this.members = size;
		this.id++;
		this.passengerList = new ArrayList<>();
	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		Ticket.id = id;
	}

	public int getMembers() {
		return members;
	}

	public void setMembers(int members) {
		this.members = members;
	}

	public List<Passenger> getPassengerList() {
		return passengerList;
	}

	public void setPassengerList(List<Passenger> passengerList) {
		this.passengerList = passengerList;
	}

	public String getPnrNo() {
		return pnr;
	}

	public void setPnrNo(String pnr) {
		this.pnr = pnr;
	}
}
