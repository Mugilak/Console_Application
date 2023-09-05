package com.toll.view;

import com.toll.controller.OutputController;
import com.toll.model.Journey;
import com.toll.model.Vehicle;

public class InputManager extends TollSystem {
	String vehicleNo, start, dest, name;
	Vehicle vehicle;
	OutputController output = new OutputController();

	public void displayVehicles() {
		System.out.println(output.getVehiclesData());
	}

	public void displayTolls() {
		System.out.println(output.getTollData());
	}

	public void calculateCharge() {
		System.out.println(" ~~~Selected Charge Calculating ~~~\n");
		if (getInput()) {
			if (vehicle == null)
				vehicle = output.createVehicle(name, vehicleNo);
			int choice = selectRoute();
			Journey journey = output.createJourney(vehicleNo, start, dest);
			Double amount = output.calculateCharge(journey, vehicle, choice);
			System.out.println("\n[ Vehicle type: " + name + " | Start: " + start + " | destination: " + dest
					+ " | Tolls passed: " + journey.tolls() + " | Amount paid: " + amount + " ]");
			inp.nextLine();
		}
	}

	private int selectRoute() {
		System.out.println("Route selection\n1-One way\n2-return\n3-monthly rate");
		char c = inp.next().charAt(0);
		if (Integer.valueOf(c + "") > 0 && Integer.valueOf(c + "") <= 3) {
			return Integer.valueOf(c + "");
		} else {
			return selectRoute();
		}
	}

	private boolean getInput() {
		System.out.println("Enter vehicleNo : (TN-0001 or tn-0034)");
		vehicleNo = inp.nextLine();
		if (output.check(vehicleNo, "^[A-Za-z]{2}-[0-9]{4}$")) {
			vehicleNo = vehicleNo.toUpperCase();
			System.out.println("Enter starting location : (A to H) ");
			start = inp.nextLine();
			System.out.println("Enter Destination location : (A to H) ");
			dest = inp.nextLine();
			if (output.check(start, dest, "^[a-hA-H]{1}$") && !start.equals(dest)) {
				start = start.toUpperCase();
				dest = dest.toUpperCase();
				System.out.println("Enter vehicle name (car / bus / truck ) : ");
				name = inp.nextLine();
				name = name.toLowerCase();
				if (!name.equals("car") && !name.equals("bus") && !name.equals("truck")) {
					System.out.println("Invalid Input!");
				} else {
					vehicle = output.getVehicle(vehicleNo);
					return true;
				}
			} else {
				System.out.println("Invalid locations!");
			}
		} else {
			System.out.println("Invalid vehicle no!");
		}
		return false;
	}

}
