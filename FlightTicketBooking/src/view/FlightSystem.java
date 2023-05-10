package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FlightSystem {
	private Scanner input = new Scanner(System.in);
	private CreatingFlight create;
	private PassengerManage passenger;
	private AdminManage admin;

	FlightSystem() {
		admin = new AdminManage();
		create = new CreatingFlight();
		passenger = new PassengerManage();
	}

	public static void main(String[] args) {
		new FlightSystem().getStart();
	}

	private void getStart() {
		System.out.println("---- WELCOME TO INDIAN AIRWAYS ----");
		create.createFlight();
		proceed();
	}

	private void proceed() {
		System.out.println("Welcome to IRCTC");
		try {
			String choice = "1";
			do {
				System.out.println();
				System.out.println("""
						1. Booking
						2. View PNR Status
						3. Booked Tickets
						4. Cancel Tickets
						5. Search Passenger
						6. Change Ticket Status
						7. List Flight Routes
						8. Add Flight Routes
						9. Exit\n
						---Enter choices accordingly---
						""");
				choice = input.nextLine();
				switch (choice) {
				case "1":
					passenger.initBooking();
					break;
				case "2":
					passenger.getStatus();
					break;
				case "3":
					admin.getTotalTickets();
					break;
				case "4":
					passenger.cancelTicket();
					break;
				case "5":
					passenger.searchPassenger();
					break;
				case "6":
					admin.changeStatus();
					break;
				case "7":
					admin.listFlight();
					break;
				case "8":
					create.createFlight(0);
					break;
				case "9":
					choice = "0";
					break;
				default:
					System.out.println("invalid input");
					choice = "1";
				}
			} while (choice != "0");
			System.out.println("exited.. Thank You!");
		} catch (InputMismatchException e) {
			System.out.println("Invalid Input .. Exited");
		}

	}
}