package com.bibek.zipCodeProcessor;

/**
 * This class models each set of zip code pair
 * 
 * @author Bibek
 *
 */

public class Pair {
	private int min;
	private int max;

	public Pair(int min, int max) {
		super();
		this.min = min;
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (o instanceof Pair) {
			Pair p = (Pair) o;
			if (p.getMin() == this.min && p.getMax() == this.max)
				return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hashValue = 13;
		hashValue = 23 * hashValue + min;
		hashValue = 23 * hashValue + max;
		return hashValue;
	}

	@Override
	public String toString() {
		return "[" + min + "," + max + "]";
	}

}
