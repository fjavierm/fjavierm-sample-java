package com.wordpress.infow.javaGeneralKnowledge.dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class SortedList<T> extends ArrayList<T> {

	private static final long serialVersionUID = 1L;

	private Comparator<? super T> comparator = null;

	public SortedList() {

	}

	public SortedList(Comparator<? super T> comparator) {
		this.comparator = comparator;
	}

	@Override
	public boolean add(T paramT) {
		int insertionPoint = Collections.binarySearch(this, paramT, this.comparator);
		super.add((insertionPoint > -1) ? insertionPoint : (-insertionPoint) - 1, paramT);
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends T> paramCollection) {
		boolean result = false;
		if (paramCollection.size() > 4) {
			result = super.addAll(paramCollection);
			Collections.sort(this, this.comparator);
		}
		else {
			for (T paramT : paramCollection) {
				result |= this.add(paramT);
			}
		}
		return result;
	}

	public boolean containsElement(T paramT) {
		return (Collections.binarySearch(this, paramT, this.comparator) > -1);
	}
}
