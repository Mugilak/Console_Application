package controller;

public class ExpressionValidation {
	public boolean validate(String expression) {
		char[] exp = expression.toCharArray();
		int length = exp.length;
		if (isBalanced(expression) == true) {
			for (int i = 0; i < length; i++) {
				if ((exp[i] == '+' || exp[i] == '-' || exp[i] == '*' || exp[i] == '/' || exp[i] == '^')
						&& ((i + 1 < length) && (exp[i + 1] == '+' || exp[i + 1] == '*' || exp[i + 1] == '^'
								|| exp[i + 1] == '/' || exp[i + 1] == '%' || exp[i + 1] == ')'))) {
					return false;
				}
				if (exp[i] == '(' && ((i + 1 >= 0) && (exp[i + 1] == '%' || exp[i + 1] == '*' || exp[i + 1] == '^'
						|| exp[i + 1] == '/' || exp[i + 1] == ')'))) {
					return false;
				}

				if (exp[i] == '%' && ((i + 1 < length) && (exp[i + 1] == '+' || exp[i + 1] == '*' || exp[i + 1] == '^'
						|| exp[i + 1] == '/' || ((int) exp[i + 1] >= 48 && (int) exp[i + 1] <= 57)))) {
					return false;
				}
				if (exp[i] == ')' && ((i - 1 >= 0) && (exp[i - 1] == '+' || exp[i - 1] == '-' || exp[i - 1] == '*'
						|| exp[i - 1] == '^' || exp[i - 1] == '/'))) {
					return false;
				}
				if (exp[length - 1] == '+' || exp[length - 1] == '-' || exp[length - 1] == '*' || exp[length - 1] == '/'
						|| exp[length - 1] == '^') {
					return false;
				}
				if (exp[0] == '*' || exp[0] == '/' || exp[0] == '^') {
					return false;
				}
				if ((exp[i] >= 'a' && exp[i] <= 'z') || (exp[i] >= 'A' && exp[i] <= 'Z')
						|| (exp[i] >= 'U' && exp[i] <= 'Z') || (exp[i] >= 58 && exp[i] <= 63) || (exp[i]) == '!'
						|| (exp[i]) == '"' || (exp[i]) == '#' || (exp[i]) == '&' || (exp[i]) == '@' || (exp[i]) == '~'
						|| (exp[i]) == '`' || (exp[i]) == '|' || (exp[i]) == '$' || (exp[i]) == '_' || (exp[i]) == ','
						|| (exp[i]) == '\\') {

					if ((i < length - 5) && (expression.substring(i, i + 4) == "sqrt")) {
						return true;
					} else if ((i < length - 3) && (expression.substring(i, i + 2) == "pi")) {

					} else {
						return false;
					}
				}
			}
			return true;
		}
		return false;

	}

	private boolean isBalanced(String s) {
		int[] array = new int[3];
		for (int i = 0; i < s.length(); i++) {
			if ((array[0] != -1) && (array[1] != -1) && (array[2] != -1)) {
				char c = s.charAt(i);
				if (c == '(')
					array[0]++;
				else if (c == ')')
					array[0]--;
				else if (c == '[')
					array[1]++;
				else if (c == ']')
					array[1]--;
				else if (c == '{')
					array[2]++;
				else if (c == '}')
					array[2]--;
			} else

				return false;
		}
		if (array[0] == 0 && array[1] == 0 && array[2] == 0) {
			return true;
		}
		return false;
	}
}
