package com.cab.view;

import java.util.Scanner;
import java.util.Stack;

import com.cab.controller.CabController;
import com.cab.model.*;

public class CabManaging {
	private Scanner input = new Scanner(System.in);
	private CabController create;
	private int cabsCount;

	public CabManaging() {
		create = new CabController();
	}

	public void createCab() {
		System.out.println("Enter number of cabs you want to add : ");
		cabsCount = input.nextInt();
		create.addPoints(6);
		create.createCabs(cabsCount);
		System.out.println("Succesfully cabs Created !");
	}

	public void displayDetails() {
		System.out.print("Enter Taxi No : ");
		int taxiNo = input.nextInt();
		if (taxiNo > 0 && taxiNo <= cabsCount) {
			Cab cab = create.getTaxi(taxiNo);
			if (cab != null) {
				showDetails(cab);
			} else {
				System.out.println("\nNot available taxi no");
			}
		} else {
			System.out.println("Invalid taxi No.... ");
		}
	}

	private void showDetails(Cab cab) {
		System.out.println("Taxi No : " + cab.getCabNo() + " | Total Earnings : " + cab.getEarnings());
		Stack<Bookings> bookings = cab.getBookings();
		if (bookings.size() > 0) {
			System.out.println("Booking Id  Customer Id   From      To       Pickup Time   Drop Time   Amount");
			for (Bookings booking : bookings) {
				System.out.printf("%10d", booking.getBookingId());
				System.out.printf("%10d", booking.getCustomerId());
				System.out.printf("%10s", booking.getFrom());
				System.out.printf("%8s", booking.getTo());
				System.out.printf("%10d", booking.getPickupTime());
				System.out.printf("%10d", booking.getDropTime());
				System.out.printf("%15d", booking.getAmount());
			}
			System.out.println();
		} else {
			System.out.println("No Bookings done !");
		}
	}
}
