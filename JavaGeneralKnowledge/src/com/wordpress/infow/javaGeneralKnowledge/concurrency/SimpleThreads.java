package com.wordpress.infow.javaGeneralKnowledge.concurrency;

public class SimpleThreads {

	/**
	 * Display a message, preceded by the name of the current Thread
	 */
	static void threadMessage(String message) {
		String threadName = Thread.currentThread().getName();

		System.out.format("%s: %s%n", threadName, message);
	}

	private static class MessageLoop implements Runnable {

		@Override
		public void run() {
			String importantInfo[] = {
					"Info 1",
					"Info 2",
					"Info 3",
					"Info 4"
			};

			try {
				for (int i = 0; i < importantInfo.length; i++) {
					Thread.sleep(4000);
					SimpleThreads.threadMessage(importantInfo[i]);

				}
			} catch (InterruptedException ex) {
				SimpleThreads.threadMessage("Error!!!");
			}
		}

		public static void main(String[] args) throws InterruptedException {
			// Delay before interrupt MessageLoop (miliseconds)
			long patience = 1000 * 60;

			SimpleThreads.threadMessage("Starting MessageLoop thread");
			long startTime = System.currentTimeMillis();
			Thread t = new Thread(new MessageLoop());
			t.start();

			SimpleThreads.threadMessage("Waiting for MessageLoop thread to finish");

			// loop until MessageLoop thread exits
			while (t.isAlive()) {
				SimpleThreads.threadMessage("Still waiting...");
				// Waiting maximun 1 second for MessageLoop thread
				t.join(1000);

				if (((System.currentTimeMillis() - startTime) > patience) && t.isAlive()) {
					SimpleThreads.threadMessage("Tired of waiting!");
					t.interrupt();
					// Shouldn't be long -- Wait indefinitely
					t.join();
				}
			}

			SimpleThreads.threadMessage("Finally!");
		}
	}
}
