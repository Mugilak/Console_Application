package com.cricketnew.model;

final public class Inning {
	private int time;
	private Team battingTeam, bowlingTeam;
	private int extras;

	public Team getBattingTeam() {
		return battingTeam;
	}

	public void setBattingTeam(Team battingTeam) {
		this.battingTeam = battingTeam;
	}

	public Team getBowlingTeam() {
		return bowlingTeam;
	}

	public void setBowlingTeam(Team bowlingTeam) {
		this.bowlingTeam = bowlingTeam;
	}

	public int getExtras() {
		return extras;
	}

	public void setExtras(int extras) {
		this.extras = extras;
	}

	public void addExtras(int extras) {
		this.extras += extras;
	}

}
