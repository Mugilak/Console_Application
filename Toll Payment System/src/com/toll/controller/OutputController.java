package com.toll.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.Map.Entry;

import com.toll.model.Highway;
import com.toll.model.Journey;
import com.toll.model.Points;
import com.toll.model.Toll;
import com.toll.model.Vehicle;

public class OutputController {

	public String getTollData() {
		String print = "";
		for (Toll v : Highway.getInstanceOf().getTolls()) {
			print += v + "\n----------------------\n";
		}
		return print;
	}

	public String getVehiclesData() {
		String print = "";
		for (Vehicle v : Highway.getInstanceOf().getVehicles()) {
			print += v + "\n\n";
		}
		return print;
	}

	public boolean check(String input, String regex) {
		return input.matches(regex);
	}

	public boolean check(String inp1, String inp2, String regex) {
		return inp1.matches(regex) && inp2.matches(regex);
	}

	public Vehicle getVehicle(String num) {
		for (Vehicle v : Highway.getInstanceOf().getVehicles()) {
			if (v.getVehicleNo().equals(num)) {
				return v;
			}
		}
		return null;
	}

	public Vehicle createVehicle(String name, String vehicleNo) {
		Vehicle vehicle = new Vehicle(vehicleNo, name);
		Highway.getInstanceOf().setVehicles(vehicle);
		return vehicle;
	}

	public Journey createJourney(String vehicleNo, String start, String dest) {
		return new Journey(start, dest, vehicleNo);
	}

	public Double calculateCharge(Journey journey, Vehicle vehicle, int choice) {
		int points = Highway.getInstanceOf().getSize();
		char start = journey.getStart().charAt(0);
		char dest = journey.getDestination().charAt(0);
		if (dest < start) {
			char temp = start;
			start = dest;
			dest = temp;
		}
		int cDist = (dest - 64) - (start - 64);
		int aDist = (points - (dest - 64)) + (start - 64);
		List<Points> point = Highway.getInstanceOf().getPoints();
		double amount = 0.0;
		if (cDist < aDist) {
			for (int i = start - 'A'; i <= dest - 'A'; i++) {
				if (point.get(i).isToll()) {
					Double eachToll = 0.0;
					Toll toll = point.get(i).getTollData();
					eachToll = toll.getRate(vehicle.getName(), choice - 1);
					toll.setVehiclePassed(vehicle, eachToll);
					amount += eachToll;
					journey.setTollsPassed(toll);
				}
			}
		} else {
			int count = 0, i = start - 'A';
			while (count++ <= aDist) {
				if (i < 0) {
					i = points - 1;
				}
				if (point.get(i).isToll()) {
					Double eachToll = 0.0;
					Toll toll = point.get(i).getTollData();
					eachToll = toll.getRate(vehicle.getName(), choice - 1);
					toll.setVehiclePassed(vehicle, eachToll);
					amount += eachToll;
					journey.setTollsPassed(toll);
				}
				i--;
			}
		}
		journey.setTotalAmount(amount);
		vehicle.addAmount(amount);
		vehicle.setJourneys(journey);
		return amount;
	}

}
