package com.wordpress.infow.javaGeneralKnowledge.algorithmics;

import java.util.ArrayList;
import java.util.List;

public class SortMain {

	public static void main(String[] args) {
		SortMain.sortMethods();
	}

	private static void sortMethods() {
		List<Integer> list = SortMain.generateUnsortedList();
		System.out.print("Selection sort algorithm:\t");
		SortMain.printList(Sort.selection(list));

		list = SortMain.generateUnsortedList();
		System.out.print("Insertion sort algorithm:\t");
		SortMain.printList(Sort.insertion(list));

		list = SortMain.generateUnsortedList();
		System.out.print("Bubble sort algorithm:\t");
		SortMain.printList(Sort.bubble(list));

		list = SortMain.generateUnsortedList();
		System.out.print("Shell sort algorithm:\t");
		SortMain.printList(Sort.shellSort(list));

		list = SortMain.generateUnsortedList();
		System.out.print("Quick sort algorithm:\t");
		SortMain.printList(Sort.quickSort(list, 0, list.size() - 1));

		list = SortMain.generateUnsortedList();
		System.out.print("Heap sort algorithm:\t");
		SortMain.printList(Sort.heapSort(list));

		// list = SortMain.generateUnsortedList();
		// System.out.print("Merge sort algorithm:\t");
		// SortMain.printList(Sort.mergeSort(list));
	}

	private static List<Integer> generateUnsortedList() {
		List<Integer> list = new ArrayList<>();

		list.add(8);
		list.add(5);
		list.add(4);
		list.add(7);
		list.add(2);
		list.add(1);
		list.add(9);
		list.add(0);
		list.add(6);
		list.add(3);

		return list;
	}

	private static void printList(List<Integer> list) {
		System.out.print("list = {");

		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i));
			if (i != (list.size() - 1)) {
				System.out.print(", ");
			}
		}

		System.out.println("}");
	}
}
