package com.wordpress.infow.javaGeneralKnowledge.algorithmics;

import java.util.List;

public class Sort {

	// Complexity - O(n^2)
	public static List<Integer> selection(List<Integer> list) {
		int i = 0, j = 0, k = 0;
		int tmp = 0;

		for (i = 0; i < list.size(); i++) {
			for (k = i, j = i + 1; j < list.size(); j++) {
				if (list.get(j) < list.get(k)) {
					k = j;
				}
			}
			if (k != i) {
				tmp = list.get(i);
				list.set(i, list.get(k));
				list.set(k, tmp);
			}
		}

		return list;
	}

	// Complexity - O(n^2) - If it's almost sorted: O(n) - Sort with another
	// algorithm before
	public static List<Integer> insertion(List<Integer> list) {
		int i = 0, j = 0;
		int tmp = 0;

		for (i = 0; i < list.size(); i++) {
			tmp = list.get(i);
			j = i - 1;
			while ((j >= 0) && (tmp < list.get(j))) {
				list.set(j + 1, list.get(j));
				j--;
			}
			list.set(j + 1, tmp);
		}

		return list;
	}

	// Complexity - O(n^2) - With exchangeIndex improvement, a bit less
	public static List<Integer> bubble(List<Integer> list) {
		int i = 0, j = 0, tmp = 0;
		int exchangeIndex = 0;

		i = list.size() - 1;

		while (i > 0) {
			exchangeIndex = 0;
			for (j = 0; j < i; j++) {
				if (list.get(j + 1) < list.get(j)) {
					tmp = list.get(j);
					list.set(j, list.get(j + 1));
					list.set(j + 1, tmp);
					exchangeIndex = j;
				}
			}

			i = exchangeIndex;
		}

		return list;
	}

	// Complexity - O(n^2) - tens of thousand elements
	public static List<Integer> shellSort(List<Integer> list) {
		int incr = 0, j = 0, k = 0;
		int tmp = 0;

		incr = list.size() / 2;

		while (incr > 0) {
			for (int i = incr; i < list.size(); i++) {
				j = i - incr;
				while (j >= 0) {
					k = j + incr;
					if (list.get(j) <= list.get(k)) {
						j -= 1;
					} else {
						tmp = list.get(j);
						list.set(j, list.get(k));
						list.set(k, tmp);
						j -= incr;
					}
				}
			}
			incr /= 2;
		}
		;

		return list;
	}

	// Complexity - O(n log n)
	public static List<Integer> quickSort(List<Integer> list, int first, int last) {
		int i = 0, j = 0, midle = 0;
		int pivot = 0;
		int tmp = 0;

		midle = (first + last) / 2;
		pivot = list.get(midle);
		i = first;
		j = last;

		do {
			while (list.get(i) < pivot) {
				i++;
			}
			while (list.get(j) > pivot) {
				j--;
			}

			if (i <= j) {
				tmp = list.get(i);
				list.set(i, list.get(j));
				list.set(j, tmp);
				i++;
				j--;
			}
		} while (i <= j);

		if (first < j) {
			Sort.quickSort(list, first, j);
		}

		if (i < last) {
			Sort.quickSort(list, i, last);
		}

		return list;
	}

	// Complexity - O(n log n) - Theoretically more quickly, but actually less
	// than shellSort with correct increment
	public static List<Integer> heapSort(List<Integer> list) {
		int i = 0;
		int tmp;

		for (i = list.size() / 2; i >= 0; i--) {
			Sort.filterDesc(list, i, list.size() - 1);
		}

		for (i = list.size() - 1; i > 0; i--) {
			tmp = list.get(0);
			list.set(0, list.get(i));
			list.set(i, tmp);
			Sort.filterDesc(list, 0, i - 1);
		}

		return list;
	}

	private static void filterDesc(List<Integer> list, int i, int l) {
		int tmp = list.get(i);
		int son = 2 * i;

		if ((son < l) && (list.get(son + 1) > list.get(son))) {
			son++;
		}

		while ((son <= l) && (tmp < list.get(son))) {
			if ((son < l) && (list.get(son + 1) > list.get(son))) {
				son++;
			}
			list.set(i, list.get(son));
			i = son;
			son = 2 * i;
		}

		list.set(i, tmp);
	}

	// // Complexity - O(n log n)
	// public static List<Integer> mergeSort(List<Integer> list) {
	// if (list.size() <= 1) {
	// return list;
	// }
	//
	// int mitad = list.size() / 2;
	// List<Integer> izq = list.subList(0, mitad);
	// List<Integer> der = list.subList(mitad, list.size() - 1);
	//
	// Sort.mergeSort(izq);
	// Sort.mergeSort(der);
	//
	// Sort.combine(list, izq, der);
	//
	// return list;
	// }
	//
	// private static void combine(List<Integer> list, List<Integer> izq,
	// List<Integer> der) {
	// int i = 0;
	// int j = 0;
	//
	// for (int k = 0; k < list.size(); k++) {
	// if (i >= izq.size()) {
	// if (j < der.size()) {
	// list.set(k, der.get(j++));
	// }
	// } else if (j >= der.size()) {
	// if (i < izq.size()) {
	// list.set(k, izq.get(i++));
	// }
	// } else {
	// list.set(k, (izq.get(i) < der.get(j)) ? izq.get(i++) : der.get(j++));
	// }
	// }
	// }
}
