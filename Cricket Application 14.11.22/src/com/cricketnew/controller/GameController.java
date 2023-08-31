package com.cricketnew.controller;

import java.util.*;

import com.cricketnew.model.*;

public class GameController {
	Match match = Match.getInstanceof();

	public void compute(Queue<List<String>> input) {
		List<String> list = input.poll();
		Team battingTeam = new Team(list.get(0));
		Team bowlingTeam = new Team(list.get(1));
		list = input.poll();
		setTeam(list, battingTeam);
		list = input.poll();
		setTeam(list, bowlingTeam);
		match.getFirstInning().setBattingTeam(battingTeam);
		match.getFirstInning().setBowlingTeam(bowlingTeam);
		match.getSecondInning().setBattingTeam(bowlingTeam);
		match.getSecondInning().setBowlingTeam(battingTeam);
		compute(input, match.getFirstInning(), Integer.MAX_VALUE);
		int target = match.getFirstInning().getBattingTeam().getTotalRuns();
		compute(input, match.getSecondInning(), target);
	}

	private void setTeam(List<String> list, Team team) {
		List<Batsman> batsmen = new ArrayList<>();
		List<Bowler> bowlers = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if (i < 3) {
				Batsman b = new Batsman(list.get(i));
				batsmen.add(b);
			} else {
				Bowler b = new Bowler(list.get(i));
				bowlers.add(b);
			}
		}
		team.setBatsmen(batsmen);
		team.setBowlers(bowlers);
	}

	private void compute(Queue<List<String>> input, Inning inning, int target) {
		List<Batsman> batsmen = inning.getBattingTeam().getBatsmen();
		List<Bowler> bowlers = inning.getBowlingTeam().getBowlers();
		int runs = 0, totalRuns = 0, extras = 0, balls = 0, overs = 0, wickets = 0, i = 0;
		int size = batsmen.size();

		Batsman striker = batsmen.get(i++), nonStriker = batsmen.get(i++);
		striker.setStatus("in");
		Bowler currentBowler = bowlers.get(0);

		while (input != null) {
			List<String> list = input.poll();
			for (String each : list) {
				runs = 0;
				if (each.matches("[0-9]")) {
					runs = Integer.valueOf(each);
					striker.addBallsFaced(1);
					currentBowler.addBallsBowled(1);
					balls++;
				} else if (each.matches("W")) {
					striker.addBallsFaced(1);
					striker.setStatus("out");
					if (i < size) {
						striker = batsmen.get(i++);
						striker.setStatus("in");
					}
					currentBowler.addWicket(1);
					currentBowler.addBallsBowled(1);
					balls++;
					wickets++;
				} else if (each.matches("^[0-9]*Wd$")) {
					runs = Integer.valueOf(each.substring(0, each.length() - 2));
					currentBowler.addRuns(1);
					striker.addBallsFaced(1);
					extras++;
				} else if (each.matches("^[0-9]*Nb$")) {
					runs = Integer.valueOf(each.substring(0, each.length() - 2));
					currentBowler.addRuns(1);
					striker.addBallsFaced(1);
					extras++;
				} else if (each.matches("^[0-9]*B$")) {
					runs = Integer.valueOf(each.substring(0, each.length() - 2));
					currentBowler.addRuns(1);
					extras++;
				} else if (each.matches("^[0-9]*Lb$")) {
					runs = Integer.valueOf(each.substring(0, each.length() - 2));
					currentBowler.addRuns(1);
					extras++;
				}
				striker.addRuns(runs);
				currentBowler.addRuns(runs);
				totalRuns += runs;

				if (runs % 2 == 1) {
					Batsman temp = striker;
					striker = nonStriker;
					nonStriker = temp;
				}
			}
			overs++;
			currentBowler.addOvers(1);
			Batsman temp = striker;
			striker = nonStriker;
			nonStriker = temp;
			if (currentBowler == bowlers.get(0)) {
				currentBowler = bowlers.get(1);
			} else {
				currentBowler = bowlers.get(0);
			}

			if (wickets >= size - 1 || totalRuns > target || overs == 4) {
				break;
			}
		}
		inning.setExtras(extras);
		inning.getBattingTeam().setExtras(extras);
		inning.getBattingTeam().setTotalOvers(overs);
		inning.getBattingTeam().setWicket(wickets);
		inning.getBattingTeam().setTotalRuns(totalRuns + extras);
		inning.getBattingTeam().setTotalBalls(balls);
		findStats(inning.getBattingTeam().getBatsmen(), inning.getBattingTeam().getTotalRuns());
		findStatsBowl(inning.getBowlingTeam().getBowlers(), wickets);
	}

	private void findStatsBowl(List<Bowler> bowlers, int wickets) {
		int c = 0;
		for (Bowler b : bowlers) {
			if (wickets > 0)
				c = (int) Math.ceil((double) (b.getWicket() / wickets) * 100);
			b.setContribution(c);
		}
	}

	private void findStats(List<Batsman> batsmen, int totalRuns) {
		double s = 0, c = 0;
		for (Batsman b : batsmen) {
			if (b.getBallsFaced() > 0)
				s = Math.round((double) b.getRuns() / b.getBallsFaced() * 100);
			if (totalRuns > 0)
				c = Math.round((double) b.getRuns() / totalRuns * 100);
			b.setStrikeRate((int) s);
			b.setContribution((int) c);
		}
	}

	public String showOutput() {
		String output = showTeamScore("Team Scores \n\n", match.getFirstInning());
		return showTeamScore(output + "~~~~~~~~~~~~~~~~~~~~~\n\n", match.getSecondInning());
	}

	private String showTeamScore(String output, Inning inning) {
		output += "" + inning.getBattingTeam().getName();
		output += " - " + inning.getBattingTeam().getTotalRuns() + "/" + inning.getBattingTeam().getWicket();
		output += "("
				+ ((inning.getBattingTeam().getWicket() == inning.getBattingTeam().eligibleCount()) ? "all out, " : "");
		output += inning.getBattingTeam().getTotalOvers() + " overs)\n\n";

		output = getBattingScore(output, inning);

		output += "Innings Extras - " + inning.getBattingTeam().getExtras() + "\n\n";

		output = getBowlingScore(output, inning);

		output += "Player Stats : \n";
		output = getBatsmanStats(output, inning);
		output = getBowlerStats(output, inning);
		return output;
	}

	private String getBowlerStats(String output, Inning inning) {
		List<Bowler> bowlers = inning.getBattingTeam().getBowlers();
		for (Bowler b : bowlers) {
			output += b.getName();
			output += " - " + b.getRuns() + "/" + b.getWicket() + " (" + b.getOvers() + ") - ";
			output += "W " + b.getWicket() + " - C " + b.getContribution() + "%\n";
		}
		return output + "\n";
	}

	private String getBatsmanStats(String output, Inning inning) {
		List<Batsman> batsman = inning.getBattingTeam().getBatsmen();
		for (Batsman b : batsman) {
			output += b.getName() + " - ";
			output += b.getRuns() + "(";
			output += b.getBallsFaced() + ") - ";
			output += "SR " + b.getStrikeRate() + "% - ";
			output += "C " + b.getContribution() + "%\n";
		}
		return output + "\n";
	}

	private String getBowlingScore(String output, Inning inning) {
		output += "Bowling :\n";
		List<Bowler> bowlers = inning.getBattingTeam().getBowlers();
		for (Bowler b : bowlers) {
			output += b.getName() + " - ";
			double over = Math.floor((double) b.getBallsBowled() / 6 * 10) / 10;
			output += over + " overs - ";
			output += b.getRuns() + "/" + b.getWicket() + "\n";
		}
		return output + "\n";
	}

	private String getBattingScore(String output, Inning inning) {
		output += "Batting :\n";
		List<Batsman> batsman = inning.getBattingTeam().getBatsmen();
		for (Batsman b : batsman) {
			output += b.getName() + (b.getStatus().equals("out") ? "*" : "") + " - ";
			output += b.getRuns() + " runs (";
			output += b.getBallsFaced() + " balls)\n";
		}
		return output + "\n";
	}

}
