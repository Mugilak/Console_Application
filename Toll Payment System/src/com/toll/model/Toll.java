package com.toll.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Stack;

public class Toll {
	String place;
	private HashMap<String, Double[]> rates;
	private Stack<HashMap<Vehicle, Double>> vehiclePassed;

	public Toll(String place) {
		this.place = place;
		this.rates = new HashMap<>();
//		this.vehiclePassed = new LinkedHashMap<>();
		this.vehiclePassed = new Stack<>();
	}

	public HashMap<String, Double[]> getRates() {
		return rates;
	}

	public double getOneWayRate(String vehicleName) {
		return this.rates.get(vehicleName)[0];
	}

	public double getReturnRate(String vehicleName) {
		return this.rates.get(vehicleName)[1];
	}

	public double getMonthlyRate(String vehicleName) {
		return this.rates.get(vehicleName)[2];
	}

	public void setRates(String vehicleName, String[] word) {
		Double[] rate = new Double[word.length];
		for (int i = 0; i < word.length; i++) {
			rate[i] = Double.valueOf(word[i]);
		}
		this.rates.put(vehicleName, rate);
	}

	public Stack<HashMap<Vehicle, Double>> getVehiclePassed() {
		return vehiclePassed;
	}

	public void setVehiclePassed(Stack<HashMap<Vehicle, Double>> vehiclePassed) {
		this.vehiclePassed = vehiclePassed;
	}

	public void setVehiclePassed(Vehicle v, Double amount) {
		HashMap<Vehicle, Double> map = new LinkedHashMap<>();
		map.put(v, amount);
		this.vehiclePassed.push(map);
	}

	@Override
	public String toString() {
		String print = "Toll " + this.place + "\n[vehiclePassed:";
		if (vehiclePassed.isEmpty()) {
			return print + " No vehicles passed ]";
		}
		print += "\n";
		Stack<HashMap<Vehicle, Double>> temp = vehiclePassed;
		while (!temp.isEmpty()) {
			HashMap<Vehicle, Double> map = temp.pop();
			for (Entry<Vehicle, Double> e : map.entrySet()) {
				print += e.getKey().getVehicleNo() + " | " + e.getKey().getName() + " | " + e.getValue() + "\n";
			}
		}
		return print + "]";
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Double getRate(String vehicleName, int choice) {
		return this.rates.get(vehicleName)[choice];
	}
}
