package com.cricketnew.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class InputController {
//	private Match match = Match.getInstanceof();

	public Queue<List<String>> parseFileInput() {
		String filePath = "C:\\Users\\91638\\git\\Console_Application\\Cricket Application 14.11.22\\src\\com\\cricketnew\\model\\input.txt";
		try {
			File file = new File(filePath);
			Scanner scan = new Scanner(file);
			return parseData(scan);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Queue<List<String>> parseData(Scanner scan) {
		String line = "";
		Queue<List<String>> input = new LinkedList<>();
		List<String> list = null;
		int i = 0;
		while (scan.hasNext()) {
			line = scan.nextLine();
			if (i <= 2) {
				list = new LinkedList<>(Arrays.asList(line.split(", ")));
			} else if (i >= 3) {
				list = new LinkedList<>(Arrays.asList(line.split(",")));
			}
			input.offer(list);
			i++;
		}
		return input;
	}

}
