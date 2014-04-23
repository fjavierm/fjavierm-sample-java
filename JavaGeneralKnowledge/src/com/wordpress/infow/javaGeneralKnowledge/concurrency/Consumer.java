package com.wordpress.infow.javaGeneralKnowledge.concurrency;

import java.util.Random;

public class Consumer implements Runnable {

	private Drop drop;

	public Consumer(Drop drop) {
		this.drop = drop;
	}

	@Override
	public void run() {
		Random random = new Random();
		for (String message = this.drop.take(); !message.equals("DONE"); message = this.drop.take()) {
			System.out.format("MESSAGE RECEIVED: %s%n", message);

			try {
				Thread.sleep(random.nextInt(5000));
			} catch (InterruptedException e) {
			}
		}
	}

}
