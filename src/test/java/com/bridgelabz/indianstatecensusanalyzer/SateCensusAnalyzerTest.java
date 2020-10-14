package com.bridgelabz.indianstatecensusanalyzer;

import java.nio.file.Paths;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import junit.framework.Assert;

public class SateCensusAnalyzerTest {
	private static final String STATE_CENSUS_CSV_FILE_PATH = "C:\\Users\\aaada\\Dev\\eclipse-workspace\\IndianStateCensusAnalyzer\\StateCensusCSV.csv";
	private static final String WRONG_CSV_FILE_PATH = "C:\\\\Users\\\\aaada\\\\Dev\\\\eclipse-workspace\\\\IndianStateCensusAnalyzer\\src\\StateCensusCSV.csv";
	private static final String CSV_FILE_WRONG_DELIMITER_PATH = "C:\\Users\\aaada\\Dev\\eclipse-workspace\\IndianStateCensusAnalyzer\\StateCensusCSVInvalidDelimiter.csv";
	private static final String CSV_FILE_WRONG_HEADER_PATH = "C:\\Users\\aaada\\Dev\\eclipse-workspace\\IndianStateCensusAnalyzer\\StateCensusCSVInvalidHeader.csv";

	@Test
	public void givenStateCensusCSVFile_ShouldReturnNumberOfRecords() {
		try {
			StateCensusAnalyzer censusAnalyzer = new StateCensusAnalyzer(Paths.get(STATE_CENSUS_CSV_FILE_PATH));
			int noOfEntries = censusAnalyzer.readStateCensusCSVData();
			Assert.assertEquals(5, noOfEntries);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenStateCensusCSVFile_WhenPathIncorrect_ShouldThrowException() {
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(StateCensusAnalyzerException.class);
			StateCensusAnalyzer censusAnalyzer = new StateCensusAnalyzer(Paths.get(WRONG_CSV_FILE_PATH));
			censusAnalyzer.readStateCensusCSVData();
		} catch (StateCensusAnalyzerException e) {
			Assert.assertEquals(StateCensusAnalyzerException.ExceptionType.INCORRECT_PATH, e.type);
		}
	}

	@Test
	public void givenStateCensusCSVFile_WhenStateIncorrect_ShouldThrowException() {
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(StateCensusAnalyzerException.class);
			StateCensusAnalyzer censusAnalyzer = new StateCensusAnalyzer(Paths.get(STATE_CENSUS_CSV_FILE_PATH));
			censusAnalyzer.readStateCensusCSVData();
		} catch (StateCensusAnalyzerException e) {
			Assert.assertEquals(StateCensusAnalyzerException.ExceptionType.INCORRECT_STATE, e.type);
		}
	}

	@Test
	public void givenStateCensusCSVFile_WhenIncorrectDelimeter_ShouldThrowException() {
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(StateCensusAnalyzerException.class);
			StateCensusAnalyzer censusAnalyzer = new StateCensusAnalyzer(Paths.get(CSV_FILE_WRONG_DELIMITER_PATH));
			censusAnalyzer.readStateCensusCSVData();
		} catch (StateCensusAnalyzerException e) {
			Assert.assertEquals(StateCensusAnalyzerException.ExceptionType.INCORRECT_DELIMITER, e.type);
		}
	}

	@Test
	public void givenStateCensusCSVFile_WhenIncorrectCSVHeader_ShouldThrowException() {
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(StateCensusAnalyzerException.class);
			StateCensusAnalyzer censusAnalyzer = new StateCensusAnalyzer(Paths.get(CSV_FILE_WRONG_HEADER_PATH));
			censusAnalyzer.readStateCensusCSVData();
		} catch (StateCensusAnalyzerException e) {
			Assert.assertEquals(StateCensusAnalyzerException.ExceptionType.INCORRECT_CSV_HEADER, e.type);
		}
	}

	private static final String STATE_CODE_CSV_FILE_PATH = "C:\\Users\\aaada\\Dev\\eclipse-workspace\\IndianStateCensusAnalyzer\\StateCodeCSV.csv";
	private static final String WRONG_STATE_CODE_CSV_FILE_PATH = "C:\\\\Users\\\\aaada\\\\Dev\\\\eclipse-workspace\\\\IndianStateCensusAnalyzer\\src\\StateCodeCSV.csv";

	@Test
	public void givenStateCodeCSVFile_ShouldReturnNumberOfRecords() {
		try {
			StateCensusAnalyzer censusAnalyzer = new StateCensusAnalyzer(Paths.get(STATE_CODE_CSV_FILE_PATH));
			int noOfEntries = censusAnalyzer.readStateCodeCSVData();
			Assert.assertEquals(7, noOfEntries);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenStateCodeCSVFile_WhenPathIncorrect_ShouldThrowException() {
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(StateCensusAnalyzerException.class);
			StateCensusAnalyzer censusAnalyzer = new StateCensusAnalyzer(Paths.get(WRONG_STATE_CODE_CSV_FILE_PATH));
			censusAnalyzer.readStateCodeCSVData();
		} catch (StateCensusAnalyzerException e) {
			Assert.assertEquals(StateCensusAnalyzerException.ExceptionType.INCORRECT_PATH, e.type);
		}
	}

	@Test
	public void givenStateCodeCSVFile_WhenStateIncorrect_ShouldThrowException() {
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(StateCensusAnalyzerException.class);
			StateCensusAnalyzer censusAnalyzer = new StateCensusAnalyzer(Paths.get(STATE_CODE_CSV_FILE_PATH));
			censusAnalyzer.readStateCodeCSVData();
		} catch (StateCensusAnalyzerException e) {
			Assert.assertEquals(StateCensusAnalyzerException.ExceptionType.INCORRECT_STATE, e.type);
		}
	}

}
