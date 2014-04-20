package com.wordpress.infow.javaGeneralKnowledge.problems;

import java.math.BigInteger;

public class PrimeNumber {

	public static void main(String[] args) {
		PrimeNumber pn = new PrimeNumber();
		String number = "34354543543223";
		long longNumber = new Long(number);
		BigInteger number1 = new BigInteger(number);

		System.out.println(pn.isPrime(longNumber));
		System.out.println(pn.isPrimeUsingAPI(number1, 3));
	}

	public boolean isPrime(long number) {
		if (number < 2) { // 0 and 1 are not prime numbers
			return false;
		}

		if (number == 2) { // 2 is prime number
			return true;
		}

		if ((number % 2) == 0) { // Even numbers are not prime numbers
			return false;
		}

		for (int i = 3; (i * i) <= number; i += 2) { // Check only odd numbers
			if ((number % i) == 0) {
				return false;
			}
		}

		return true;
	}

	// Using Miller Rabin from BigInteger API
	public boolean isPrimeUsingAPI(BigInteger number, int certainty) {
		return number.isProbablePrime(certainty);
	}
}
