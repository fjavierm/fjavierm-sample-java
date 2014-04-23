package com.wordpress.infow.javaGeneralKnowledge.concurrency;

public class SynchronizedRGB {

	// Values must be between 0 and 255.
	private int red;
	private int green;
	private int blue;
	private String name;

	private void check(int red, int green, int blue) {
		if ((red < 0) || (red > 255) || (green < 0) || (green > 255) || (blue < 0) || (blue > 255)) {
			throw new IllegalArgumentException();
		}
	}

	public SynchronizedRGB(int red, int green, int blue, String name) {
		this.check(red, green, blue);

		this.red = red;
		this.green = green;
		this.blue = blue;
		this.name = name;
	}

	public void set(int red, int green, int blue, String name) {
		this.check(red, green, blue);

		synchronized (this) {
			this.red = red;
			this.green = green;
			this.blue = blue;
			this.name = name;
		}
	}

	public synchronized int getRGB() {
		return ((this.red << 16) | (this.green << 8) | this.blue);
	}

	public synchronized String getName() {
		return this.name;
	}

	public synchronized void invert() {
		this.red = 255 - this.red;
		this.green = 255 - this.green;
		this.blue = 255 - this.blue;
		this.name = "Inverse of " + this.name;
	}
}
