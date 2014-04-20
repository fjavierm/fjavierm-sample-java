package com.wordpress.infow.javaGeneralKnowledge.problems;

public class MostRepeatedLetters {

	public static void main(String args[]) throws Exception {
		MostRepeatedLetters myWork = new MostRepeatedLetters();

		System.out.println(myWork.processString("asdfghjklasdqwaertyuioasd"));
	}

	public String processString(String string) {
		String solution = "";

		char[] stringArray = string.toCharArray();
		byte[] counter = new byte[256];

		for (char c : stringArray) {
			counter[c]++;
		}

		int max = 0;
		for (int i = 0; i < counter.length; i++) {
			if (counter[i] > max) {
				max = counter[i];
			}
		}

		for (int i = 0; i < counter.length; i++) {
			if (counter[i] == max) {
				solution += (char) i;
			}
		}
		return solution + " " + max;
	}
}
