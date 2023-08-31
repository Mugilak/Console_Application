package com.cricketnew.model;

public class Batsman {
	private int runs;
	private String name;

	public Batsman(String name) {
		this.status = "in";
		this.name = name;
	}

	private String status;
	private int ballsFaced;
	private int overs;
	private int strikeRate;
	private int contribution;

	public int getRuns() {
		return runs;
	}

	public void setRuns(int runs) {
		this.runs = runs;
	}

	public void addRuns(int runs) {
		this.runs += runs;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getBallsFaced() {
		return ballsFaced;
	}

	public void setBallsFaced(int ballsFaced) {
		this.ballsFaced = ballsFaced;
	}

	public void addBallsFaced(int ballsFaced) {
		this.ballsFaced += ballsFaced;
	}

	public int getOvers() {
		return overs;
	}

	public void setOvers(int overs) {
		this.overs = overs;
	}

	public int getStrikeRate() {
		return strikeRate;
	}

	public void setStrikeRate(int strikeRate) {
		this.strikeRate = strikeRate;
	}

	public int getContribution() {
		return contribution;
	}

	public void setContribution(int contribution) {
		this.contribution = contribution;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
