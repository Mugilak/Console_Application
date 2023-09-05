package com.toll.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.toll.model.Highway;
import com.toll.model.Points;
import com.toll.model.Toll;

public class InputController {

	public void parseInput() {
		String path = "C:\\Users\\91638\\eclipse-workspace\\Toll Payment System\\src\\com\\toll\\model\\input.txt";
		BufferedReader b = null;
		try {
			b = new BufferedReader(new FileReader(path));
			parseInput(b);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				b.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void parseInput(BufferedReader b) {
		Highway hw = Highway.getInstanceOf();
		List<String> list = b.lines().collect(Collectors.toList());
		for (String line : list) {
			Toll toll = null;
			Points point = null;
			line = line.strip();
			if (line == null) {
				continue;
			}
			String[] word = line.split(" ");
			point = new Points(word[0], word.length != 1);
			if (word.length != 1) {
				toll = new Toll(word[0]);
			}
			for (int i = 1; i < word.length; i++) {
				if (i == 1)
					toll.setRates("car", word[i].split(","));
				if (i == 2)
					toll.setRates("truck", word[i].split(","));
				if (i == 3)
					toll.setRates("bus", word[i].split(","));
			}
			point.setTollData(toll);
			if (word.length != 1)
				hw.setTolls(toll);
			hw.setPoints(point);
		}
	}

}
