package com.wordpress.infow.javaGeneralKnowledge.concurrency;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

import javax.imageio.ImageIO;

// To help you understand how the fork/join framework works, consider the following example. Suppose that you want to blur an image. The
// original source image is represented by an array of integers, where each integer contains the color values for a single pixel. The
// blurred destination image is also represented by an integer array with the same size as the source.
//
// Performing the blur is accomplished by working through the source array one pixel at a time. Each pixel is averaged with its surrounding
// pixels (the red, green, and blue components are averaged), and the result is placed in the destination array. Since an image is a large
// array, this process can take a long time. You can take advantage of concurrent processing on multiprocessor systems by implementing the
// algorithm using the fork/join framework. Here is one possible implementation:

public class ForkBlur extends RecursiveAction {

	private static final long serialVersionUID = 1L;

	private int[] mSource;
	private int mStart;
	private int mLength;
	private int[] mDestination;

	// Processing window size; should be odd.
	private int mBlurWidth = 15;

	public ForkBlur(int[] src, int start, int length, int[] dst) {
		this.mSource = src;
		this.mStart = start;
		this.mLength = length;
		this.mDestination = dst;
	}

	protected void computeDirectly() {
		int sidePixels = (this.mBlurWidth - 1) / 2;

		for (int index = this.mStart; index < (this.mStart + this.mLength); index++) {
			// Calculate average.
			float rt = 0, gt = 0, bt = 0;
			for (int mi = -sidePixels; mi <= sidePixels; mi++) {
				int mindex = Math.min(Math.max(mi + index, 0), this.mSource.length - 1);
				int pixel = this.mSource[mindex];
				rt += (float) ((pixel & 0x00ff0000) >> 16) / this.mBlurWidth;
				gt += (float) ((pixel & 0x0000ff00) >> 8) / this.mBlurWidth;
				bt += (float) ((pixel & 0x000000ff) >> 0) / this.mBlurWidth;
			}

			// Reassemble destination pixel.
			int dpixel = (0xff000000) | (((int) rt) << 16) | (((int) gt) << 8) | (((int) bt) << 0);

			this.mDestination[index] = dpixel;
		}
	}

	protected static int sThreshold = 100000;

	@Override
	protected void compute() {
		if (this.mLength < ForkBlur.sThreshold) {
			this.computeDirectly();
			return;
		}

		int split = this.mLength / 2;

		ForkJoinTask.invokeAll(new ForkBlur(this.mSource, this.mStart, split, this.mDestination),
				new ForkBlur(this.mSource, this.mStart + split, this.mLength - split,
						this.mDestination));
	}

	public static void main(String[] args) throws Exception {
		String srcName = "testImage01.jpg";
		File srcFile = new File(srcName);
		BufferedImage image = ImageIO.read(srcFile);

		System.out.println("Source image: " + srcName);

		BufferedImage blurredImage = ForkBlur.blur(image);

		String dstName = "testImage02.jpg";
		File dstFile = new File(dstName);
		ImageIO.write(blurredImage, "jpg", dstFile);

		System.out.println("Output image: " + dstName);
	}

	public static BufferedImage blur(BufferedImage srcImage) {
		int w = srcImage.getWidth();
		int h = srcImage.getHeight();

		int[] src = srcImage.getRGB(0, 0, w, h, null, 0, w);
		int[] dst = new int[src.length];

		System.out.println("Array size is " + src.length);
		System.out.println("Threshold is " + ForkBlur.sThreshold);

		int processors = Runtime.getRuntime().availableProcessors();
		System.out.println(Integer.toString(processors) + " processor" + (processors != 1 ? "s are " : " is ") + "available");

		ForkBlur fb = new ForkBlur(src, 0, src.length, dst); // Create a task that represents all of the work to be done.

		ForkJoinPool pool = new ForkJoinPool(); // Create the ForkJoinPool that will run the task

		long startTime = System.currentTimeMillis();
		pool.invoke(fb); // Run the task
		long endTime = System.currentTimeMillis();

		System.out.println("Image blur took " + (endTime - startTime) + " milliseconds.");

		BufferedImage dstImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		dstImage.setRGB(0, 0, w, h, dst, 0, w);

		return dstImage;
	}
}
