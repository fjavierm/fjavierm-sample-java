package com.wordpress.infow.javaGeneralKnowledge.concurrency;

public class SleepMessagesRunnable2 implements Runnable {

	@Override
	public void run() {
		String[] importantInfo = {
				"Info 1",
				"Info 2",
				"Info 3",
				"info 4"
		};

		for (int i = 0; i < importantInfo.length; i++) {
			this.heavyCrunch(importantInfo[i]);
			if (Thread.interrupted()) {
				// throw new InterruptedException();
				return;
			}
		}
	}

	private void heavyCrunch(String message) {
		// Business logic
		System.out.println(message);
	}

	public static void main(String args[]) {
		(new Thread(new SleepMessagesRunnable())).start();
	}
}
