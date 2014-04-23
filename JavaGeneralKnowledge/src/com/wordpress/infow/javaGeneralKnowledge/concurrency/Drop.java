package com.wordpress.infow.javaGeneralKnowledge.concurrency;

public class Drop {

	// Message sent from producer to consumer
	private String message;
	// True if consumer should wait for producer to send message, false if producer should wait for consumer to retrieve message.
	private boolean empty = true;

	public synchronized String take() {
		// Wait until message is available.
		while (this.empty) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}

		// Toggle status.
		this.empty = true;

		// Notify producer that status has changed.
		this.notifyAll();

		return this.message;
	}

	public synchronized void put(String message) {
		// Wait until message has been retrieved.
		while (!this.empty) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}

		// Toggle status.
		this.empty = false;

		// Store message.
		this.message = message;

		// Notify consumer that status has changed.
		this.notifyAll();
	}
}
