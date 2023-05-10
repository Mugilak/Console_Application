package com.railway.view;

import java.util.*;
import java.util.Scanner;

import com.railway.controller.TicketController;
import com.railway.model.Passenger;
import com.railway.model.Ticket;

public class TicketManaging {
	private Scanner input = new Scanner(System.in);
	private String name, age, gender;
	private TicketController control;

	public TicketManaging() {
		control = new TicketController(this);
	}

	public void book() {
		try {
			if (control.isNoAvailability()) {
				System.out.println("tickets not available");
				return;
			}
			System.out.println("you can book only maximum 4 passengers in a ticket!");
			System.out.println("Enter Passenger count to book the ticket : ");
			String inputCount = input.nextLine(), pnr;
			int count = 0;
			ArrayList<Passenger> ticketList = new ArrayList<>();
			ArrayList<Passenger> elderList = new ArrayList<>();
			ArrayList<Passenger> femaleList = new ArrayList<>();
			for (int i = 1; ((i <= Integer.valueOf(inputCount) && Integer.valueOf(inputCount) <= 4)
					|| count < 4); i++) {
				getPassengerData(i);
				if (Integer.valueOf(age) > 5) {
					count++;
				}
				if (control.createPassenger(ticketList, elderList, femaleList, name, age, gender) == false) {
					if (i <= 1) {
						System.out.println("Seat not Available !");
						return;
					}
				}
				if (i == Integer.valueOf(inputCount) &&count!=4) {
					System.out.println("\nEnter yes to continue adding passenger than your needs: ");
					String choice = input.nextLine();
					if (!(choice.equalsIgnoreCase("yes"))) {
						break;
					}
				}
			}
			pnr = control.bookTickets(elderList, femaleList, ticketList);
			System.out.println("Tickets booked successfully \n Ticket ID : " + pnr);
		} catch (Exception e) {
			System.out.println("Invalid input");
		}
	}

	private void getPassengerData(int i) {
		System.out.println("\n" + i + ") Enter data of passenger " + i);
		System.out.println("Enter Name : ");
		name = input.nextLine();
		System.out.println("Enter age : (Numbers only allowed)");
		age = input.nextLine();
		age = check(age, "^[0-9]+$", "age ! (Numbers only allowed)");
		System.out.println("Enter Gender : (1=MEN, 2=WOMEN, 3=TRANSGENDER)");
		gender = input.nextLine();
		gender = check(gender, "^[1-3]{1}+$", "Gender ! (1=MEN, 2=WOMEN, 3=TRANSGENDER)");
	}

	private String check(String value, String string, String print) {
		while (value.matches(string) == false) {
			System.out.println("Invalid input " + print);
			value = input.nextLine();
		}
		return value;
	}

	public void cancel() {
		System.out.println("Enter PNR No. : ");
		String pnr = input.nextLine();
		Ticket ticket = control.getTicket(pnr);
		if (ticket != null) {
			control.cancelTicket(ticket);
			System.out.println("Successfully ticket cancelled");
		} else {
			System.out.println("Invalid ticket id !");
		}
	}

	public void showAvailability() {
		int confirm = control.isConfirmedAvailable();
		int rac = control.isRACAvailable();
		int wait = control.iswaitingListAvailable();
		if (confirm > 0) {
			System.out.println("Available confirmed : " + confirm);
		} else if (rac > 0) {
			System.out.println("Available RAC : " + rac);
		} else if (wait > 0) {
			System.out.println("Available Waiting List : " + wait);
		} else {
			System.out.println("No tickets available!");
		}
	}

	public void prepareChart() {
		control.prepareChart();
		showPassengers();
	}

	private void showPassengers() {
		List<Passenger> list = control.getPassengerList();
		for (Passenger passenger : list) {
			System.out
					.println("Name : " + passenger.getName() + (passenger.isChild() ? "\nstatus : " + passenger.getType()
							: "\nstatus : " + passenger.getType() + "\nberth : " + passenger.getBerth()));
			System.out.println("--------------");
		}
	}

	public void alert(String print) {
		System.out.println(print);
	}

}
