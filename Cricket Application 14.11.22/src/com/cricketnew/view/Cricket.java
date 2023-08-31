package com.cricketnew.view;

import java.util.List;
import java.util.Queue;

import com.cricketnew.controller.GameController;
import com.cricketnew.controller.InputController;

public class Cricket {
	private Queue<List<String>> inputData;

	public Cricket() {
		InputController input = new InputController();
		inputData = input.parseFileInput();
	}

	public static void main(String[] args) {
		new Cricket().startGame();
	}

	private void startGame() {
		if (inputData != null) {
			GameController output = new GameController();
			output.compute(inputData);
			System.out.println("___________\n"+output.showOutput());
		}
	}

}
