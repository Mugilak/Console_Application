package com.cricketnew.model;

import java.time.LocalDate;

public class Match {
	private final Inning firstInning, secondInning;
	private LocalDate date;
	private static Match match;

	private Match() {
		firstInning = new Inning();
		secondInning = new Inning();
		this.date = LocalDate.now();
	}

	public static Match getInstanceof() {
		if (match == null) {
			match = new Match();
		}
		return match;
	}

	public Inning getFirstInning() {
		return firstInning;
	}

	public Inning getSecondInning() {
		return secondInning;
	}
}
