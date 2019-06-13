package com.bibek.zipCodeProcessor;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class contains the main method
 *
 *
 * @author Bibek
 */
public class ZipCodeProcessorSolution {
	private static final Logger logger = LogManager.getLogger();
	public static void main(String[] args) {
		ZipCodeCompressor z1 = new ZipCodeCompressor();
		logger.log(Level.INFO, "Application started");
		String input = "[94133,94133] [94200,94299] [94226,94399]";
		System.out.println("Input: " + input);
		System.out.println("Output: " + z1.compressZipCode(input));
	}
}
