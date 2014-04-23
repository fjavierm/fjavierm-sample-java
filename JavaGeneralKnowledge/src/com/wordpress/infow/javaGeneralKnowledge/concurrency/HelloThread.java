package com.wordpress.infow.javaGeneralKnowledge.concurrency;

public class HelloThread extends Thread {

	@Override
	public void run() {
		System.out.println("Hello World!");
	}

	public static void main(String args[]) {
		(new HelloThread()).start();
	}
}
