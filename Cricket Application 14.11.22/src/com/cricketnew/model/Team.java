package com.cricketnew.model;

import java.util.ArrayList;
import java.util.List;

public class Team {
	private String name;
	List<Batsman> batsmen;
	List<Bowler> bowlers;
	private int extras;
	private int totalRuns;
	private int wicket;
	private int totalOvers;
	private int totalBalls;

	public Team(String name) {
		this.name = name;
		this.batsmen = new ArrayList<>();
		this.bowlers = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Batsman> getBatsmen() {
		return batsmen;
	}
	public int eligibleCount() {
		return batsmen.size()-1;
	}

	public void setBatsmen(List<Batsman> batsmen) {
		this.batsmen = batsmen;
	}

	public List<Bowler> getBowlers() {
		return bowlers;
	}

	public void setBowlers(List<Bowler> bowlers) {
		this.bowlers = bowlers;
	}

	public int getExtras() {
		return extras;
	}

	public void setExtras(int extras) {
		this.extras = extras;
	}

	public int getTotalRuns() {
		return totalRuns;
	}

	public void setTotalRuns(int totalRuns) {
		this.totalRuns = totalRuns;
	}

	public int getWicket() {
		return wicket;
	}

	public void setWicket(int wicket) {
		this.wicket = wicket;
	}

	public int getTotalOvers() {
		return totalOvers;
	}

	public void setTotalOvers(int totalOvers) {
		this.totalOvers = totalOvers;
	}

	public int getTotalBalls() {
		return totalBalls;
	}

	public void setTotalBalls(int totalBalls) {
		this.totalBalls = totalBalls;
	}
}
