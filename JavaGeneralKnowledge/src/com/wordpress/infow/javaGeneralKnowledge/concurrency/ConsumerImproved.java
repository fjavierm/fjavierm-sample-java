package com.wordpress.infow.javaGeneralKnowledge.concurrency;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class ConsumerImproved implements Runnable {

	private BlockingQueue<String> drop;

	public ConsumerImproved(BlockingQueue<String> drop) {
		this.drop = drop;
	}

	@Override
	public void run() {
		Random random = new Random();
		try {
			for (String message = this.drop.take(); !message.equals("DONE"); message = this.drop.take()) {
				System.out.format("MESSAGE RECEIVED: %s%n", message);
				Thread.sleep(random.nextInt(5000));
			}
		} catch (InterruptedException e) {
		}
	}
}
