package com.toll.model;

public class Points {
	private String name;
	private boolean isToll;
	private Toll tollData;

	public Points(String name, boolean isToll) {
		this.name = name;
		this.isToll = isToll;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isToll() {
		return isToll;
	}

	public void setToll(boolean isToll) {
		this.isToll = isToll;
	}

	public Toll getTollData() {
		return tollData;
	}

	public void setTollData(Toll tollData) {
		this.tollData = tollData;
	}

}
