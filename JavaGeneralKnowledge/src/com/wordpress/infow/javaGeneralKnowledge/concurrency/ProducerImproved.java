package com.wordpress.infow.javaGeneralKnowledge.concurrency;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class ProducerImproved implements Runnable {

	private BlockingQueue<String> drop;

	public ProducerImproved(BlockingQueue<String> drop) {
		this.drop = drop;
	}

	@Override
	public void run() {
		String importantInfo[] = {
				"Info 1",
				"Info 2",
				"Info 3",
				"Info 4"
		};
		Random random = new Random();

		try {
			for (int i = 0; i < importantInfo.length; i++) {
				this.drop.put(importantInfo[i]);
				Thread.sleep(random.nextInt(5000));
			}
			this.drop.put("DONE");
		} catch (InterruptedException e) {
		}
	}
}
