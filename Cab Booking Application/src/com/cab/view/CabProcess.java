package com.cab.view;

import java.util.Scanner;

public class CabProcess {
	private CabManaging cab;
	private CabBookings book;
	private Scanner input = new Scanner(System.in);

	CabProcess() {
		cab = new CabManaging();
		book = new CabBookings();
	}

	static public void main(String[] args) {
		CabProcess process = new CabProcess();
		process.start();
	}

	private void start() {
		System.out.println("       Welcome to ABC Taxi Booking Application !\n");
		cab.createCab();
		initProcess();
	}

	private void initProcess() {
		int choice = 0;
		loop: while (true) {
			showProcess();
			try {
				choice = input.nextInt();
				switch (choice) {
				case (1):
					book.proceed(choice);
					break;
				case 2:
					cab.displayDetails();
					break;
				case (3):
					book.proceed(choice);
					break;
				case (4):
					System.out.println("Thank you !");
					break loop;
				default:
					System.out.println("You have entered Invalid Input...");
				}
			} catch (Exception e) {
				System.out.println("Invalid input");
				e.printStackTrace();
			}
		}
	}

	private void showProcess() {
		System.out.println("""
				-------------------------

				1. Book Taxi
				2. Display Taxi Details
				3. Cancel Taxi
				4. Exit

				Enter the choice accordingly
				""");
	}
}
