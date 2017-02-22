package com.giang.model;

import java.util.ArrayList;
import java.util.List;

public class Page {
	private double size;
	private int currentPosition;

	public double getSize() {
		return size;
	}

	public Page(int allRecords, int maxRecordPage, int currentPosition) {
		setSize(allRecords, maxRecordPage);
		this.currentPosition = currentPosition;
	}

	public Page() {
	}

	// Can set the size for page
	public void setSize(int allRecords, int maxRecordPage) {
		this.size = Math.ceil((double) allRecords / maxRecordPage);
	}

	public int getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}

	// get the value of the next page
	public int getNextPage() {
		if (currentPosition == size) {
			return currentPosition;
		} else {
			return currentPosition + 1;
		}
	}

	// get the value of the prev page
	public int getPrevPage() {
		if (currentPosition == 1) {
			return currentPosition;
		} else {
			return currentPosition - 1;
		}
	}

	// get a list of page
	public List<Integer> getNumberPageArray() {
		List<Integer> numberPageArray = new ArrayList<Integer>();
		for (int i = 1; i <= size; i++) {
			numberPageArray.add(i);
		}
		return numberPageArray;
	}

	public int getEndPage() {
		return (int)size;
	}

	public int getFirstPage() {
		return 1;
	}

}
