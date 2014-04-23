package com.wordpress.infow.javaGeneralKnowledge.concurrency;

public class SynchronizedCounter {

	private int c = 0;

	public synchronized void increment() {
		this.c++;
	}

	public synchronized void decrement() {
		this.c--;
	}

	public synchronized int value() {
		return this.c;
	}
}
