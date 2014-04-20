package com.wordpress.infow.javaGeneralKnowledge.problems;

import java.util.Stack;

public class ParenthesisMatching {

	public static void main(String args[]) throws Exception {
		ParenthesisMatching pm = new ParenthesisMatching();
		pm.checkMatching("(((([[([])]]))))");
	}

	void checkMatching(String cadena) throws Exception {
		boolean answer = this.evaluateString(cadena.toCharArray());

		if (answer == true) {
			System.out.println("Yes");
		}
		if (answer == false) {
			System.out.println("No");
		}
	}

	boolean evaluateString(char[] string) {
		Stack<Character> cola = new Stack<Character>();

		for (char c : string) {
			if ((c == '[') || (c == '(')) {
				cola.push(c);
				continue;
			}

			if (cola.isEmpty()) {
				return false;
			}

			if ((c == ')') && (cola.pop() != '(')) {
				return false;
			}

			if ((c == ']') && (cola.pop() != '[')) {
				return false;
			}
		}

		if (!cola.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
}
