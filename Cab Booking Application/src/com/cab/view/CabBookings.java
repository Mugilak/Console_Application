package com.cab.view;

import java.util.Scanner;

import com.cab.controller.BookingController;

public class CabBookings {
	private Scanner input = new Scanner(System.in);
	private BookingController control;
	private int customerId, pickupTime;
	private char from, to;

	public CabBookings() {
		control = new BookingController(this);
	}

	private void cancelTaxi() {

	}

	private void bookTaxi() {
		if (getInput()) {
			int amount = control.calculateFair(from, to);
			int taxiNo = control.bookTaxi(customerId, from, to, pickupTime, amount);
			if (taxiNo != -1) {
				System.out.println("Taxi-" + taxiNo + " is allocated");
			}
		} else {
			System.out.println("Booking rejected due to Invalid input !");
		}
	}

	private boolean getInput() {
		try {
			System.out.print("Enter Customer Id : ");
			customerId = input.nextInt();
			if (getPointsInput()) {
				System.out.print("\nEnter pickup Time : ");
				pickupTime = input.nextInt();
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("\nInvalid input ");
		}
		return true;
	}

	private boolean getPointsInput() {
		System.out.print("\nEnter Pickup point (choose A to F) : ");
		from = input.next().charAt(0);
		System.out.print("\nEnter Drop point (choose A to F) : ");
		to = input.next().charAt(0);
		if (from == to) {
			System.out.println("Invalid.....Both are same point");
			return false;
		}
		if ((isInvalid(from)) || (isInvalid(to))) {
			System.out.println("pickup or drop points not serviceable");
			return false;
		}
		return true;
	}

	private boolean isInvalid(int points) {
		if (points < 65 || points > 70) {
			return true;
		}
		return false;
	}

	public void proceed(int choice) {
		if (choice == 1) {
			bookTaxi();
		} else {
			cancelTaxi();
		}
	}

	public void alert(String string) {
		System.out.println("\n" + string);
	}
}
