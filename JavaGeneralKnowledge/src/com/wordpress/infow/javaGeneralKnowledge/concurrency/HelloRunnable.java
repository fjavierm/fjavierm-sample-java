package com.wordpress.infow.javaGeneralKnowledge.concurrency;

public class HelloRunnable implements Runnable {

	@Override
	public void run() {
		System.out.println("Hello World!");
	}

	public static void main(String args[]) {
		(new Thread(new HelloRunnable())).start();
	}

}
