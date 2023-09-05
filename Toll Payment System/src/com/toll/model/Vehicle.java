package com.toll.model;

import java.util.Stack;

public class Vehicle {
	private String vehicleNo;
	private String type, name;
	private Double amount;
	private Stack<Journey> journeys;

	public Vehicle(String vehicleNo, String name) {
		super();
		this.vehicleNo = vehicleNo;
		this.name = name;
		this.journeys = new Stack<>();
		this.amount = 0.0;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Stack<Journey> getJourneys() {
		return journeys;
	}

	public void setJourneys(Journey journey) {
		this.journeys.push(journey);
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void addAmount(Double amount) {
		this.amount += amount;
	}

	@Override
	public String toString() {
		String print = "Vehicle [vehicle No=" + vehicleNo + ", name=" + name + ", total amount=" + amount + "]\n";
		print += "Journeys Taken : \n";
		if (journeys.isEmpty()) {
			return print + "No journeys taken!";
		}
		Stack<Journey> temp = journeys;
		while (!temp.isEmpty()) {
			Journey j = temp.pop();
			print += "start: " + j.getStart() + " | destination: " + j.getDestination() + " | amount: "
					+ j.getTotalAmount() + "   [ Tolls passed: ";
			if (j.getTollsPassed().size() <= 0) {
				print += "no tolls passed";
			} else {
				for (Toll t : j.getTollsPassed()) {
					print += t.place + " ";
				}
			}
			print += "]\n";
		}
		return print;
	}
}
