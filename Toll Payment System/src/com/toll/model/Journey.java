package com.toll.model;

import java.util.ArrayList;
import java.util.List;

public class Journey {
	private String start, destination, vehicleNo;
	private Double totalAmount;
	private List<Toll> tollsPassed;

	public Journey(String start, String destination, String vehicleNo) {
		this.start = start;
		this.destination = destination;
		this.vehicleNo = vehicleNo;
		this.tollsPassed = new ArrayList<>();
		this.totalAmount = 0.0;
	}

	public int tolls() {
		return this.tollsPassed.size();
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void addTollsAmount(Double totalAmount) {
		this.totalAmount += totalAmount;
	}

	public List<Toll> getTollsPassed() {
		return tollsPassed;
	}

	public void setTollsPassed(List<Toll> tollsPassed) {
		this.tollsPassed = tollsPassed;
	}

	public void setTollsPassed(Toll tollsPassed) {
		this.tollsPassed.add(tollsPassed);
	}
}
