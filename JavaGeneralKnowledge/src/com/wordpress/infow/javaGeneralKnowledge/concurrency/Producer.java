package com.wordpress.infow.javaGeneralKnowledge.concurrency;

import java.util.Random;

public class Producer implements Runnable {

	private Drop drop;

	public Producer(Drop drop) {
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

		for (int i = 0; i < importantInfo.length; i++) {
			this.drop.put(importantInfo[i]);

			try {
				Thread.sleep(random.nextInt(5000));
			} catch (InterruptedException e) {
			}
		}

		this.drop.put("DONE");
	}

}
