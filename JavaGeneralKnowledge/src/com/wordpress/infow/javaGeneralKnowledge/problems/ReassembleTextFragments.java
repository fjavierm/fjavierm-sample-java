package com.wordpress.infow.javaGeneralKnowledge.problems;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ReassembleTextFragments {

	/**
	 * Reassamble
	 * 
	 * @param fragment
	 *            String separated by ";"
	 * @return String - unambiguous, final, reassembled document.
	 */
	public String reassemble(String fragment) {
		// list of fragments
		List<String> fragments = this.fragmentToList(fragment);

		// Sorted by length
		fragments = this.sortByLength(fragments);

		// Process list
		return this.assemble(fragments);
	}

	/**
	 * Assemble algorithm
	 * 
	 * @param list
	 *            Different document fragments
	 * @return String - Assembled document
	 */
	private String assemble(List<String> list) {
		String fragment = list.get(0);
		list.remove(0);

		for (int total = list.size() - 1; total >= 0; total--) {
			// indexes to manage maximal overlap match, and partial
			int prefixIndex = 0;
			int maxPrefixMatchLength = 0;

			int sufixIndex = 0;
			int maxSufixMatchLength = 0;

			int prefixMatchLength = 0;
			int sufixMatchLength = 0;

			// Traverse all the elements and compare
			for (int i = list.size() - 1; i >= 0; i--) {
				prefixMatchLength = this.assembleAsPrefix(fragment, list.get(i));

				if (prefixMatchLength > 0) {
					if (prefixMatchLength > maxPrefixMatchLength) {
						maxPrefixMatchLength = prefixMatchLength;
						prefixIndex = i;
					}

				}

				sufixMatchLength = this.assembleAsSufix(fragment, list.get(i));

				if (sufixMatchLength > 0) {
					if (sufixMatchLength > maxSufixMatchLength) {
						maxSufixMatchLength = sufixMatchLength;
						sufixIndex = i;
					}

				}

			}

			// Select max overlap match and add to the document
			if ((maxPrefixMatchLength != 0) || (maxSufixMatchLength != 0)) {
				if (maxPrefixMatchLength > maxSufixMatchLength) {
					fragment = list.get(prefixIndex).substring(0, list.get(prefixIndex).length() - maxPrefixMatchLength) + fragment;
					list.remove(list.get(prefixIndex));
				} else {
					fragment = fragment + list.get(sufixIndex).substring(maxSufixMatchLength - 1, list.get(sufixIndex).length());
					list.remove(list.get(sufixIndex));
				}
			} else {
				list.remove(total); // If doesn't match with nothing, remove.
			}
		}

		return fragment;
	}

	/**
	 * Check if there is a prefix match
	 * 
	 * @param fragment
	 *            Document partially assembled
	 * @param newFragment
	 *            New fragment
	 * @return int - Number of matching characters
	 */
	private int assembleAsPrefix(String fragment, String newFragment) {
		for (int i = 0; i < (newFragment.length() - 1); i++) {
			if (fragment.startsWith(newFragment.substring(i, newFragment.length()))) {
				return newFragment.length() - i;
			}
		}

		return 0;
	}

	/**
	 * Check if there is a sufix match
	 * 
	 * @param fragment
	 *            Document partially assembled
	 * @param newFragment
	 *            New fragment
	 * @return int - Number of matching characters
	 */
	private int assembleAsSufix(String fragment, String newFragment) {
		for (int i = newFragment.length() - 1; (i >= 1); i--) {
			if (fragment.endsWith(newFragment.substring(0, i))) {
				return i + 1; // Number of characters not position
			}
		}

		return 0;
	}

	/**
	 * Sort String list descendant by length
	 * 
	 * @param list
	 *            List of fragments
	 * @return Sorted list
	 */
	private List<String> sortByLength(List<String> list) {
		Collections.sort(list, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o2.length() - o1.length();
			}
		});

		return list;
	}

	/**
	 * Split the input separated by ";"
	 * 
	 * @param fragment
	 * @return List - A list with the different fragments
	 */
	private List<String> fragmentToList(String fragment) {
		String[] fragments = fragment.split(";");

		return new ArrayList<String>(Arrays.asList(fragments));
	}

	/*
	 * Main method
	 */
	public static void main(String[] args) {
		ReassembleTextFragments fjms = new ReassembleTextFragments();

		try (BufferedReader in = new BufferedReader(new FileReader(args[0]))) {
			String fragmentProblem;
			while ((fragmentProblem = in.readLine()) != null) {
				System.out.println(fjms.reassemble(fragmentProblem));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
