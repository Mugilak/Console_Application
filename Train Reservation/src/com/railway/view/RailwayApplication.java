package com.railway.view;

import java.util.Scanner;

public class RailwayApplication {
	private Scanner input = new Scanner(System.in);
	private TicketManaging manage;

	public RailwayApplication() {
		manage = new TicketManaging();
	}

	public static void main(String[] args) {
		RailwayApplication application = new RailwayApplication();
		application.start();
	}

	private void start() {
		System.out.println("       WELCOME TO IRCTC RAILWAY RESERVATION APPLICATION  ");
		operations();
	}

	private void operations() {
		String choice = "0";
		while (true) {
			try {
				showInstructions();
				choice = input.nextLine();
				switch (choice) {
				case "1":
					manage.showAvailability();
					break;
				case "2":
					manage.book();
					break;
				case "3":
					manage.cancel();
					break;
				case "4":
					manage.prepareChart();
					break;
				case "5":
					System.out.println("exited .. Thank you !");
					return;
				default:
					System.out.println("Invalid input ");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Invalid input");
			}
		}
	}

	private void showInstructions() {
		System.out.println("""
				
				----------------------------
				1. Print availability
				2. Book Ticket
				3. Cancel Ticket
				4. Prepare chart
				5. Exit

				Enter choice Accordingly :
				""");
	}
}
