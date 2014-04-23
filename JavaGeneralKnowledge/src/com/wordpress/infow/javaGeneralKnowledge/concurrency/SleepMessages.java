package com.wordpress.infow.javaGeneralKnowledge.concurrency;

public class SleepMessages {

	public static void main(String[] args) throws InterruptedException {
		String[] importantInfo = {
				"Info 1",
				"Info 2",
				"Info 3",
				"info 4"
		};

		for (int i = 0; i < importantInfo.length; i++) {
			Thread.sleep(4000);
			System.out.println(importantInfo[i]);
		}
	}
}
