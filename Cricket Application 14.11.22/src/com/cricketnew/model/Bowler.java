package com.cricketnew.model;

public class Bowler {
	private String name;
	private int runs;
	private int overs;
	private int wickets;
	private int ballsBowled;
	private int contribution;

	public Bowler(String name) {
		this.name = name;
	}

	public int getRuns() {
		return runs;
	}

	public void setRuns(int runs) {
		this.runs = runs;
	}

	public void addRuns(int runs) {
		this.runs += runs;
	}

	public int getOvers() {
		return overs;
	}

	public void setOvers(int overs) {
		this.overs = overs;
	}

	public int getWicket() {
		return wickets;
	}

	public void setWicket(int wicket) {
		this.wickets = wicket;
	}

	public void addWicket(int wicket) {
		this.wickets += wicket;
	}

	public int getContribution() {
		return contribution;
	}

	public void setContribution(int contribution) {
		this.contribution = contribution;
	}

	public int getBallsBowled() {
		return ballsBowled;
	}

	public void setBallsBowled(int ballsBowled) {
		this.ballsBowled = ballsBowled;
	}

	public void addBallsBowled(int ballsBowled) {
		this.ballsBowled += ballsBowled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addOvers(int over) {
		this.overs += over;
	}

}
