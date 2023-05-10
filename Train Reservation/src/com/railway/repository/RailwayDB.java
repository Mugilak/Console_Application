package com.railway.repository;

import java.util.*;

import com.railway.model.*;

public class RailwayDB {
	private static RailwayDB railDB;
	private List<Passenger> passengerList;
	private List<Ticket> ticketList;
	private List<Passenger> confirmedList;
	private Queue<Passenger> racList, waitingList;
	private int upperAvailable = 2, lowerAvailable = 2, middleAvailable = 2, sidelowerAvailable = 2,
			sideupperAvailable = 2, racAvailable = 4, waitingAvailable = 4;

	private RailwayDB() {
		passengerList = new ArrayList<>();
		confirmedList = new ArrayList<>();
		racList = new LinkedList<>();
		waitingList = new LinkedList<>();
		ticketList = new ArrayList<>();
	}

	public static RailwayDB getInstanceOf() {
		if (railDB == null) {
			railDB = new RailwayDB();
		}
		return railDB;
	}

	public int getUpperAvailable() {
		return upperAvailable;
	}

	public void setUpperAvailable() {
		this.upperAvailable--;
	}

	public void addUpperAvailable() {
		this.upperAvailable++;
	}

	public int getLowerAvailable() {
		return lowerAvailable;
	}

	public void setLowerAvailable() {
		this.lowerAvailable--;
	}

	public void addLowerAvailable() {
		this.lowerAvailable++;
	}

	public int getMiddleAvailable() {
		return middleAvailable;
	}

	public void setMiddleAvailable() {
		this.middleAvailable -= 1;
	}

	public void addMiddleAvailable() {
		this.middleAvailable += 1;
	}

	public int getSideLowerAvailable() {
		return sidelowerAvailable;
	}

	public void setSideLowerAvailable() {
		this.sidelowerAvailable -= 1;
	}

	public int getSideUpperAvailable() {
		return sideupperAvailable;
	}

	public void setSideUpperAvailable() {
		this.sideupperAvailable -= 1;
	}

	public int getRacAvailable() {
		return racAvailable;
	}

	public void setRacAvailable() {
		this.racAvailable--;
	}

	public void addRacAvailable() {
		this.racAvailable++;
	}

	public int getWaitingAvailable() {
		return waitingAvailable;
	}

	public void setWaitingAvailable() {
		this.waitingAvailable--;
	}

	public void addWaitingAvailable() {
		this.waitingAvailable++;
	}

	public List<Passenger> getPassengerList() {
		return passengerList;
	}

	public void setPassengerList(Passenger passenger) {
		this.passengerList.add(passenger);
	}

	public void removePassenger(Passenger passenger) {
		this.passengerList.remove(passenger);
	}

	public List<Passenger> getConfirmedTicketList() {
		return confirmedList;
	}

	public int getConfirmedTickets() {
		return this.confirmedList.size();
	}

	public int getRACTickets() {
		return this.racList.size();
	}

	public int getWaitingTickets() {
		return this.waitingList.size();
	}

	public void setConfirmedTicketList(Passenger passenger) {
		this.confirmedList.add(passenger);
	}

	public void removeConfirmedTicket(Passenger passenger) {
		this.confirmedList.remove(passenger);
	}

	public void removeConfirmedTicket(Ticket ticket) {
		this.ticketList.remove(ticket);
	}

	public Queue<Passenger> getRacList() {
		return racList;
	}

	public Passenger removeRacList() {
		return racList.poll();
	}

	public void removeRacList(Passenger passenger) {
		racList.remove(passenger);
	}

	public void setRacList(Passenger passenger) {
		this.racList.offer(passenger);
	}

	public Queue<Passenger> getWaitingList() {
		return waitingList;
	}

	public Passenger removeWaitingList() {
		return waitingList.poll();
	}

	public void removeWaitingList(Passenger passenger) {
		waitingList.remove(passenger);
	}

	public void setWaitingList(Passenger passenger) {
		this.waitingList.offer(passenger);
	}

	public void setTicketList(Ticket ticket) {
		this.ticketList.add(ticket);
	}

	public void removeTicket(Ticket ticket) {
		this.ticketList.remove(ticket);
	}

	public List<Ticket> getTicketList() {
		return ticketList;
	}

	public void removePassengers(List<Passenger> passengerList) {
		this.passengerList.removeAll(passengerList);
	}

}
