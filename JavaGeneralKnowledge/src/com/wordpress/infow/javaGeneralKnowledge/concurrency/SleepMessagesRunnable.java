package com.wordpress.infow.javaGeneralKnowledge.concurrency;

public class SleepMessagesRunnable implements Runnable {

	@Override
	public void run() {
		String[] importantInfo = {
				"Info 1",
				"Info 2",
				"Info 3",
				"info 4"
		};

		for (int i = 0; i < importantInfo.length; i++) {
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				return;
			}
			System.out.println(importantInfo[i]);
		}
	}

	public static void main(String args[]) {
		(new Thread(new SleepMessagesRunnable())).start();
	}

}
