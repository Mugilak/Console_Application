package com.toll.view;

import java.util.Scanner;

//There are ‘n’ number of points in a highway out of which some points collect toll.
//Each toll has its own charging scheme according to the vehicles and whether or not they
//are a VIP user.
//If they are VIP user, 20% discount apply.
//If the vehicle passes 3 toll gates, it has to pay in all the 3 toll gates according to the
//scheme of respective tolls.
//There were 4 modules.
//
//1. Given the details of vehicle type, start and destination……display the total toll paid during
//the journey and print the amount after applying the discount.
//
//2. Display the details of all the tolls…..like what are all the vehicles(vehicle number) passed
//that respective toll and the amount each vehicle paid……and the total amount charged in
//that toll.
//
//3. Display the details of all the vehicles …….like what are all the journeys did it take….the
//start and destination of the same……tolls it passed during that journey….amount paid in
//that journey…..and the total amount paid by that vehicle.
//
//4. Assume the highway as a circular path……we have to find the short route and identify
//the tolls between that route and calculate the amount.

import com.toll.controller.InputController;

public class TollSystem {
	Scanner inp = new Scanner(System.in);
	
	static {
		InputController input = new InputController();
		input.parseInput();
	}

	protected TollSystem() {
	}

	public static void main(String[] args) {
		System.out.println(" ~~~~~ Welcome to TOLL PAYMENT SYSTEM CALCULATOR ~~~~~");
		new TollSystem().start();
	}

	private void start() {
		String choice = "";
		InputManager manage = new InputManager();
		do {
			showInstruction();
			choice = inp.nextLine();
			process(choice, manage);
		} while (!choice.equals("4"));
	}

	private void process(String choice, InputManager manage) {
		switch (choice) {
		case "1":
			manage.calculateCharge();
			break;
		case "2":
			manage.displayTolls();
			break;
		case "3":
			manage.displayVehicles();
			break;
		case "4":
			System.out.println("\n ~~~ Thank you ~~~");
			break;
		default:
			System.out.println("invalid input");
		}
	}

	private void showInstruction() {
		System.out.println();
		System.out.print("""
				1 . Get toll charges of Journey
				2 . Display toll Details
				3 . Display vehicle Details
				4 . Exit

				Enter choice:
				""");
	}

}
