package com.bridgelabz.indianstatecensusanalyzer;

import java.nio.file.Paths;
import org.junit.Test;
import junit.framework.Assert;

public class SateCensusAnalyzerTest {
	private static final String STATE_CENSUS_CSV_FILE_PATH = "C:\\Users\\aaada\\Dev\\eclipse-workspace\\IndianStateCensusAnalyzer\\StateCensusCSV.csv";

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
}
