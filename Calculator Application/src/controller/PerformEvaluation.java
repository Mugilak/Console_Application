package controller;

import java.util.Stack;

public class PerformEvaluation {
	String expression;
	Stack<Character> operatorStack = new Stack<>();
	Stack<Double> NumberStack = new Stack<>();
	public int peekPrecedence, opPrecedence;

	public PerformEvaluation(String expression) {
		this.expression = expression;
	}

	private int getPrecedence(char op) {
		if (op == '+' || op == '-') {
			return 1;
		} else if (op == '*' || op == '/')
			return 2;
		else if (op == '^')
			return 3;
		return 0;
	}

	private double operate(char popped, double value1, double value2) throws ArithmeticException {
		if ('+' == popped) {
			return value1 + value2;
		}
		if ('-' == popped) {
			return value2 - value1;
		}
		if ('^' == popped) {
			return Math.pow(value2, value1);
		}
		if ('*' == popped) {
			return value1 * value2;
		}
		if ('/' == popped) {
			if (value1 != 0)
				return value2 / value1;
			else {
				throw new ArithmeticException("Cannot Divide by Zero !");
			}
		}
		return 0;
	}

	private double operate(double value) {
		return (double) value / 100;
	}

	public Double evaluate() throws ArithmeticException {
		char[] each = expression.toCharArray();
		int j, k, length = each.length;

		double number, sign;
		for (int i = 0; i < length; i++) {
			sign = 1;
			if (each[i] == ' ') {
				continue;
			} else if ((each[i] >= '0' && each[i] <= '9')) {
				StringBuilder sb = new StringBuilder();
				j = i - 1;
				k = i - 2;
				if (j == 0 && each[j] == '-') {
					sign = -(1);
				}
				if (((j > 0 && k >= 0 && each[j] == '-')) && (each[k] == '-' || each[k] == '+' || each[k] == '*'
						|| each[k] == '/' || each[k] == '^' || each[k] == '(')) {
					sign = -(1);
				}
				while ((i < length && each[i] >= '0' && each[i] <= '9') || (i < length && each[i] == '.')) {
					sb.append(each[i++]);
				}
				number = sign * (Double.parseDouble(sb.toString()));
				j = i + 1;
				if ((i < length && each[i] == '%')
						|| (i < length && each[i] == ')' && (j < length && each[j] == '%'))) {
					NumberStack.push(operate(number));
					if ((i < each.length && each[i] == '%'))
						i++;
				} else {
					NumberStack.push(number);
				}
				--i;
			} else if (each[i] == '(' || each[i] == ')') {
				if (each[i] == '(') {
					j = i - 1;
					if (j >= 0 && (each[j] == ')' || (each[j] >= '0' && each[j] <= '9'))) {
						operatorStack.push('*');
					}
					operatorStack.push(each[i]);
				} else {

					while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
						NumberStack.push(operate(operatorStack.pop(), NumberStack.pop(), NumberStack.pop()));
					}
					if ((!operatorStack.isEmpty()) && operatorStack.peek() == '(')
						operatorStack.pop();
					j = i + 1;
					if (j < each.length && (each[j] >= '0' && each[j] <= '9')) {
						operatorStack.push('*');
					}
				}

			} else if (each[i] == '+' || each[i] == '-' || each[i] == '*' || each[i] == '/' || each[i] == '^') {
				j = i + 1;
				k = i - 1;
				if (i == 0)
					continue;
				else if (k >= 0 && each[k] == '(') {
					continue;
				} else if (each[i] == '-'
						&& (k >= 0 && (each[k] == '+' || each[k] == '*' || each[k] == '/' || each[k] == '^'))) {
					continue;
				} else
					i = operatorPush(each[i], i);
			}
		}
		while (operatorStack.isEmpty() == false) {
			NumberStack.push(operate(operatorStack.pop(), NumberStack.pop(), NumberStack.pop()));
		}
		return NumberStack.peek();
	}

	private int operatorPush(char each, int i) {
		if (operatorStack.isEmpty()) {
			operatorStack.push(each);
		} else {
			peekPrecedence = getPrecedence(operatorStack.peek());
			opPrecedence = getPrecedence(each);
			if (opPrecedence > peekPrecedence)
				operatorStack.push(each);
			else {
				NumberStack.push(operate(operatorStack.pop(), NumberStack.pop(), NumberStack.pop()));
				--i;
			}
		}
		return i;
	}
}