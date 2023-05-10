package com.model;

public class Destinations {
	private String destination;
	private int km;

	public Destinations(String destination, int km) {
		this.destination = destination;
		this.km = km;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

}
