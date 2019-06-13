package com.bibek.zipCodeProcessor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class contains utility methods to process given string to minimize the
 * representation of zip code restriction pairs.
 * 
 * @author Bibek
 *
 */
public class ZipCodeCompressor {

	private static final Logger logger = LogManager.getLogger();

	/**
	 * Method to parse string of zip codes, sort each pair in ascending order
	 * and return the minimized list of pairs that represent the same
	 * restrictions as the input.
	 * 
	 * @param zipCodesString
	 *            string representing zip codes
	 * @return string representing compressed zip codes.
	 * 
	 */
	public String compressZipCode(String zipCodesString) {
		if (zipCodesString == null || zipCodesString.isEmpty()) {
			logger.log(Level.ERROR, "Invalid input!");
			throw new IllegalArgumentException("Invalid Input!");
		}
		List<Pair> zipCodeList = formatZipCode(zipCodesString);
		zipCodeList.sort(new Comparator<Pair>() {
			public int compare(Pair o1, Pair o2) {
				return o1.getMin() - o2.getMin();
			}
		});
		List<Pair> result = compressZipCodes(zipCodeList);
		StringBuilder resultStringBuilder = new StringBuilder();
		for (Pair s : result) {
			resultStringBuilder.append(s).append(" ");
		}
		return resultStringBuilder.toString().trim();
	}

	/**
	 * Helper method for {@link compressZipCode}
	 * 
	 * @param zipCodesString
	 * @return list of zipcode pairs.
	 */
	private List<Pair> formatZipCode(String zipCodesString) {
		List<Pair> zipCodeList = new ArrayList<Pair>();
		String[] zipCodes = zipCodesString.split(" ");
		for (String zipCode : zipCodes) {
			String zipCodeTemp = zipCode.substring(1, zipCode.length() - 1);
			String[] minMax = zipCodeTemp.split(",");
			if (minMax.length < 0 || minMax[0].length() != 5 || minMax[1].length() != 5) {
				logger.log(Level.ERROR, "Zip Code must be of 5 digits: " + minMax[0]);
				throw new IllegalArgumentException("Zip Code must be of 5 digits!");
			}
			Pair zipPair = new Pair(Integer.valueOf(minMax[0]), Integer.valueOf(minMax[1]));
			zipCodeList.add(zipPair);
		}
		return zipCodeList;
	}

	/**
	 * Helper method for {@link compressZipCode}
	 * 
	 * @param zipCodeList
	 * @return list of compressed zip codes
	 */
	private List<Pair> compressZipCodes(List<Pair> zipCodeList) {
		List<Pair> result = new ArrayList<Pair>();
		Pair head = zipCodeList.get(0);
		for (int i = 1; i < zipCodeList.size(); i++) {
			Pair following = zipCodeList.get(i);
			boolean flag = false;
			Pair combined = null;
			if (following.getMin() - 1 <= head.getMax() && following.getMin() >= head.getMin()) {
				combined = new Pair(head.getMin(), Math.max(following.getMax(), head.getMax()));
				head = combined;
				flag = true;
				if (i == zipCodeList.size() - 1) {
					result.add(combined);
				}
			} else if (flag == true) {
				result.add(combined);
				head = following;
			} else {
				result.add(head);
				head = following;
				if (i == zipCodeList.size() - 1) {
					result.add(following);
				}
			}
		}
		return result;
	}
}
