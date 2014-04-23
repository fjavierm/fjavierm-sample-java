package com.wordpress.infow.javaGeneralKnowledge.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class ProducerConsumerExampleImproved {

	public static void main(String[] args) {
		BlockingQueue<String> drop = new SynchronousQueue<String>();
		(new Thread(new ProducerImproved(drop))).start();
		(new Thread(new ConsumerImproved(drop))).start();
	}
}
