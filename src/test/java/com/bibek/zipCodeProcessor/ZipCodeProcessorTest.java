package com.bibek.zipCodeProcessor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.TestCase;

@RunWith(JUnit4.class)
public class ZipCodeProcessorTest extends TestCase {
	private ZipCodeCompressor classUnderTest;
	private String zipCodesString;
	
	@Before
	public void setup() {
		this.classUnderTest = new ZipCodeCompressor();
		this.zipCodesString = "[94133,94133] [94200,94299] [94226,94399]";
	}
	
	@After
	public void destroy() {
		this.classUnderTest = null;
	}

	@Test
	public void whenCompressZipCodeIsGivenValidZipCodeStringThenReturnsListOfPairs() {
		String pairs = this.classUnderTest.compressZipCode(zipCodesString);
		assertNotNull("List returned should not be null.", pairs);
		assertTrue("List returned should not be empty", !pairs.isEmpty());
		
		String expectedPairList = "[94133,94133] [94200,94399]";
		assertEquals(expectedPairList, pairs);
	}
	
	@Test
	public void whenCompressZipCodesIsGivenInvalidInputThenIllegalArgumentExceptionIsThrown() {
		try{
			this.classUnderTest.compressZipCode(null);
		} catch(IllegalArgumentException iae) {
			assertEquals("Invalid Input!", iae.getMessage());
		}
	}
	
	@Test
	public void whenCompressZipCodesIsGivenEmptyStringInputThenIllegalArgumentExceptionIsThrown() {
		try{
			this.classUnderTest.compressZipCode("");
		} catch(IllegalArgumentException iae) {
			assertEquals("Invalid Input!", iae.getMessage());
		}
	}
	
	@Test
	public void whenCompressZipCodesIsGivenInvalidInputZipCodeThenIllegalArgumentExceptionIsThrown() {
		try{
			this.classUnderTest.compressZipCode("[94133,941] [94200,94399]");
		} catch(IllegalArgumentException iae) {
			assertEquals("Zip Code must be of 5 digits!", iae.getMessage());
		}
	}
}

