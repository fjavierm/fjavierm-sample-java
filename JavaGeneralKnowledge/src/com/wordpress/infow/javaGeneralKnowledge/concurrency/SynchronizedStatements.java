package com.wordpress.infow.javaGeneralKnowledge.concurrency;

import java.util.ArrayList;
import java.util.List;

/**
 * Statement synchronization with this object
 */
public class SynchronizedStatements {

	int nameCount = 0;
	String lastName;
	List<String> nameList = new ArrayList<>();

	public void addName(String name) {
		synchronized (this) {
			this.lastName = name;
			this.nameCount++;
		}

		this.nameList.add(name);
	}
}

/**
 * Blocking statement but not class. Both variables should be completely
 * independent
 */
class MsLunch {

	private long c1 = 0;
	private long c2 = 0;
	private Object lock1 = new Object();
	private Object lock2 = new Object();

	public void inc1() {
		synchronized (this.lock1) {
			this.c1++;
		}
	}

	public void inc2() {
		synchronized (this.lock2) {
			this.c2++;
		}
	}
}