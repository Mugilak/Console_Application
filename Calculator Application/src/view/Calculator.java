package view;

import java.util.Scanner;

import controller.ExpressionValidation;
import controller.PerformEvaluation;

public class Calculator {
	private ExpressionValidation expressions;
	private PerformEvaluation perform;

	Calculator() {
		expressions = new ExpressionValidation();
	}

	public static void main(String[] args) {
		Calculator cal = new Calculator();
		cal.entry();
	}

	private void entry() {
		System.out.println(
				"    --- Welcome to Calculator Application ---\n\n>>>Perform Arithmetic Operations here and get relax\n");
		getCalculator();
		getInput();
	}

	private void getCalculator() {
		String[][] array = { { "1", "2", "3", "4", "5" }, { "6", "7", "8", "9", "0" }, { "+", "-", "*", "/", "^" },
				{ "%", ".", "(", ")", " " } };
		for (int outer = 0; outer < array.length; outer++) {
			for (int inner = 0; inner < array[0].length; inner++) {
				System.out.print(array[outer][inner] + " |");
			}
			System.out.println();
		}
	}

	private void getInput() {
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("\nEnter expression : ");
			String expression = input.nextLine();
			do {
				if (expressions.validate(expression)) {
					try {
						perform = new PerformEvaluation(expression);
						System.out.println(expression + " = " + perform.evaluate());
					} catch (ArithmeticException e) {
						System.out.println("  Infinity");
					} catch (NumberFormatException e) {
						System.out.println("Invalid input");
					}
				} else
					System.out.println("invalid expression to compute !\n");
				System.out.println("\n");
				getCalculator();
				System.out.println("\n0 - exit  --> Enter expression ");
				expression = input.nextLine();
			} while (!expression.equals("0"));
		}
		System.out.println("Calculator Closed ! ");
	}
}
