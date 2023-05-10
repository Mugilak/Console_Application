package com.railway.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import com.railway.model.*;
import com.railway.repository.RailwayDB;
import com.railway.view.TicketManaging;

public class TicketController {
	private RailwayDB railDB = RailwayDB.getInstanceOf();
	private TicketManaging manage;
	private String berth, type = "";
	private static int seatNo = 1;

	public TicketController(TicketManaging manage) {
		this.manage = manage;
	}

	public boolean isNoAvailability() {
		if (railDB.getWaitingAvailable() + railDB.getRacAvailable() + railDB.getUpperAvailable()
				+ railDB.getMiddleAvailable() + railDB.getLowerAvailable() == 0) {
			return true;
		}
		return false;
	}

	public boolean createPassenger(ArrayList<Passenger> ticketList, ArrayList<Passenger> elderList,
			ArrayList<Passenger> femaleList, String name, String age, String gender) {
		if (isNoAvailability()) {
			return false;
		}
		int ageOfPassenger = Integer.valueOf(age);
		Passenger passenger = new Passenger(name, ageOfPassenger);
		passenger.setGender(Passenger.Gender.valueOf(Integer.valueOf(gender)));
		if (ageOfPassenger >= 5) {
			passenger.setChild(false);
			passenger.setTicketNo(Ticket.getId());
		} else {
			passenger.setChild(true);
		}
		if (ageOfPassenger >= 60) {
			elderList.add(passenger);
		} else if (gender == "2" && ageOfPassenger > 30) {
			femaleList.add(passenger);
		} else
			ticketList.add(passenger);
		railDB.setPassengerList(passenger);
		return true;
	}

	private void checkBerth() {
		berth = "";
		type = "";
		if (railDB.getLowerAvailable() > 0) {
			railDB.setLowerAvailable();
			berth = "lower";
			type = "confirmed";
		} else if (railDB.getMiddleAvailable() > 0) {
			berth = "middle";
			railDB.setMiddleAvailable();
			type = "confirmed";
		} else if (railDB.getUpperAvailable() > 0) {
			berth = "upper";
			type = "confirmed";
			railDB.setUpperAvailable();
		} else if (railDB.getRacAvailable() > 0) {
			berth = "side lower";
			type = "RAC";
			railDB.setRacAvailable();
		} else if (railDB.getWaitingAvailable() > 0) {
			berth = "-";
			type = "Waiting";
			railDB.setWaitingAvailable();
		}
	}

	public String bookTickets(ArrayList<Passenger> elderList, ArrayList<Passenger> femaleList,
			ArrayList<Passenger> ticketList) {
		String accompanyType = "";
		boolean isFirst = true;
		if (elderList != null) {
			for (Passenger passenger : elderList) {
				checkBerth();
				if (berth != "") {
					passenger.setBerth(berth);
					passenger.setType(type);
					if (isFirst) {
						accompanyType = type;
						isFirst = false;
					}
				}
			}
		}
		isFirst = true;
		if (femaleList != null) {
			for (Passenger passenger : femaleList) {
				checkBerth();
				if (berth != "") {
					passenger.setBerth(berth);
					passenger.setType(type);
					if (accompanyType == "" && isFirst) {
						accompanyType = type;
						isFirst = false;
					}
				}
			}
		}
		if (ticketList != null) {
			for (Passenger passenger : ticketList) {
				if (!(passenger.isChild())) {
					checkBerth();
					if (berth != "") {
						passenger.setBerth(berth);
						passenger.setType(type);
					}
				} else {
					passenger.setType(accompanyType);
				}
			}
		}
		if (elderList != null) {
			ticketList.addAll(elderList);
		}
		if (femaleList != null) {
			ticketList.addAll(femaleList);
		}
		Ticket ticket = new Ticket(ticketList.size());
		setTicketStatus(ticketList);
		ticket.setPassengerList(ticketList);
		railDB.setTicketList(ticket);
		return ticket.getPnrNo();
	}

	private void setTicketStatus(List<Passenger> list) {
		manage.alert("------------");
		for (Passenger passenger : list) {
			if (passenger.getType() == null) {
				continue;
			}
			if (passenger.isChild()) {
				manage.alert("Name : " + passenger.getName() + "\nAge : less than 5\n------------");
				continue;
			}
			if (passenger.getType().equals("confirmed")) {
				if (!(passenger.isChild())) {
					passenger.setSeatNo(seatNo++);
				}
				manage.alert("Name : " + passenger.getName() + "\nstatus : confirmed\nberth : " + passenger.getBerth());
				railDB.setConfirmedTicketList(passenger);
			} else if (passenger.getType().equals("RAC")) {
				manage.alert("Name : " + passenger.getName() + "\nstatus : RAC\nberth : " + passenger.getBerth());
				railDB.setRacList(passenger);
			} else if (passenger.getType().equals("Waiting")) {
				manage.alert(
						"Name : " + passenger.getName() + "\nstatus : WaitingList\nberth : " + passenger.getBerth());
				railDB.setWaitingList(passenger);
			}
			manage.alert("------------");
		}
		manage.alert("------------");
	}

	public int isConfirmedAvailable() {
		return Math.abs(6 - railDB.getConfirmedTickets());
	}

	public int isRACAvailable() {
		return Math.abs(2 - railDB.getRACTickets());
	}

	public int iswaitingListAvailable() {
		return Math.abs(2 - railDB.getWaitingTickets());
	}

	public Ticket getTicket(String pnr) {
		List<Ticket> ticketList = railDB.getTicketList();
		for (Ticket ticket : ticketList) {
			if (ticket.getPnrNo().equals(pnr)) {
				return ticket;
			}
		}
		return null;
	}

	public void cancelTicket(Ticket ticket) {
		List<Passenger> passengerList = ticket.getPassengerList();
		for (Passenger passenger : passengerList) {
			if (passenger.isChild()) {
				continue;
			}
			setAvailabilities(passenger);
			setBacks(passenger);
		}
		railDB.removePassengers(passengerList);
		railDB.removeTicket(ticket);
	}

	private void setBacks(Passenger passenger) {
		if (passenger.getType().equals("confirmed")) {
			railDB.removeConfirmedTicket(passenger);
			Passenger rac = railDB.removeRacList(), waiting = railDB.removeWaitingList();
			if (waiting != null && rac != null) {
				setAvailabilities(waiting);
				waiting.setType("RAC");
				waiting.setBerth(rac.getBerth());
				railDB.setRacList(waiting);
				setAvailabilities(waiting, -1);
				setAvailabilities(rac);
				rac.setType("confirmed");
				rac.setBerth(passenger.getBerth());
				railDB.setConfirmedTicketList(rac);
				setAvailabilities(rac, -1);
			} else if (waiting != null) {
				setAvailabilities(waiting);
				waiting.setType("confirmed");
				waiting.setBerth(passenger.getBerth());
				railDB.setRacList(waiting);
				setAvailabilities(waiting, -1);
			} else if (rac != null) {
				setAvailabilities(rac);
				rac.setType("confirmed");
				rac.setBerth(passenger.getBerth());
				railDB.setConfirmedTicketList(rac);
				setAvailabilities(rac, -1);
			}
		} else if (passenger.getType().equals("RAC")) {
			railDB.removeRacList(passenger);
			Passenger waiting = railDB.removeWaitingList();
			if (waiting != null) {
				setAvailabilities(waiting);
				waiting.setType("RAC");
				waiting.setBerth(passenger.getBerth());
				railDB.setRacList(waiting);
				setAvailabilities(waiting, -1);
			}
		} else if (passenger.getType().equals("Waiting")) {
			railDB.removeWaitingList(passenger);
		}
	}

	private void setAvailabilities(Passenger passenger, int i) {
		if (passenger.getBerth() == "lower") {
			railDB.setLowerAvailable();
		} else if (passenger.getBerth() == "middle") {
			railDB.setMiddleAvailable();
		} else if (passenger.getBerth() == "upper") {
			railDB.setUpperAvailable();
		} else if (passenger.getBerth() == "side lower") {
			railDB.setRacAvailable();
		} else if (passenger.getBerth() == null) {
			railDB.setWaitingAvailable();
		}
	}

	private void setAvailabilities(Passenger passenger) {
		if (passenger.getBerth() == "lower") {
			railDB.addLowerAvailable();
		} else if (passenger.getBerth() == "middle") {
			railDB.addMiddleAvailable();
		} else if (passenger.getBerth() == "upper") {
			railDB.addUpperAvailable();
		} else if (passenger.getBerth() == "side lower") {
			railDB.addRacAvailable();
		} else if (passenger.getBerth() == null) {
			railDB.addWaitingAvailable();
		}
	}

	public int getSeatNo() {
		return seatNo;
	}

	public void prepareChart() {
		Queue<Passenger> rac = railDB.getRacList();
		Queue<Passenger> waiting = railDB.getWaitingList();
		if (rac != null) {
			for (Passenger passenger : rac) {
				if (!passenger.isChild()) {
					passenger.setSeatNo(seatNo++);
					gotoUpper(passenger);
				}
				passenger.setType("confirmed");
			}
			rac.clear();
			if (waiting != null) {
				for (Passenger passenger : waiting) {
					passenger.setType("RAC");
				}
				rac.addAll(waiting);
				waiting.clear();
			}
		}
	}

	private void gotoUpper(Passenger passenger) {
		if (railDB.getSideUpperAvailable() > 0) {
			railDB.setSideUpperAvailable();
			passenger.setBerth("side upper");
		}
	}

	public List<Passenger> getPassengerList() {
		return railDB.getPassengerList();
	}

}
