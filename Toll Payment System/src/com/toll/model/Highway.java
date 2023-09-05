package com.toll.model;

import java.util.ArrayList;
import java.util.List;

public class Highway {
	private List<Points> points;
	private List<Toll> tolls;
	private List<Vehicle> vehicles;
	private static Highway highway;

	private Highway() {
		this.points = new ArrayList<>();
		this.tolls = new ArrayList<>();
		this.vehicles = new ArrayList<>();
	}

	public static Highway getInstanceOf() {
		if (highway == null) {
			highway = new Highway();
		}
		return highway;
	}

	public List<Points> getPoints() {
		return points;
	}

	public void setPoints(List<Points> points) {
		this.points.addAll(points);
	}

	public void setPoints(Points point) {
		this.points.add(point);
	}

	public List<Toll> getTolls() {
		return tolls;
	}

	public void setTolls(List<Toll> tolls) {
		this.tolls = tolls;
	}

	public void setTolls(Toll toll) {
		this.tolls.add(toll);
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public void setVehicles(Vehicle vehicle) {
		this.vehicles.add(vehicle);
	}

	public int getSize() {
		return this.points.size();
	}
}
