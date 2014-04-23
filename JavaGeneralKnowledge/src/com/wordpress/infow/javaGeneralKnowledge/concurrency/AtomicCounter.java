package com.wordpress.infow.javaGeneralKnowledge.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {

	private AtomicInteger c = new AtomicInteger(0);

	public void increment() {
		this.c.incrementAndGet();
	}

	public void decrement() {
		this.c.decrementAndGet();
	}

	public int value() {
		return this.c.get();
	}
}
