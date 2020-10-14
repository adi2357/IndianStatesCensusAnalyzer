package com.bridgelabz.indianstatecensusanalyzer;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.stream.StreamSupport;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyzer {
	public Path csvFilePath;

	public StateCensusAnalyzer(Path csvFilePath) {
		this.csvFilePath = csvFilePath;
	}

	public int readStateCensusCSVData() throws StateCensusAnalyzerException {

		try (Reader reader = Files.newBufferedReader(csvFilePath)) {
			CsvToBeanBuilder<StateCensusCSV> builder = new CsvToBeanBuilder<StateCensusCSV>(reader);
			CsvToBean<StateCensusCSV> csvToBean = builder.withType(StateCensusCSV.class)
					.withIgnoreLeadingWhiteSpace(true).build();
			Iterator<StateCensusCSV> StateCensusCSVIterator = csvToBean.iterator();
			Iterable<StateCensusCSV> csvIterable = () -> StateCensusCSVIterator;
			int noOfEnteries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
			return noOfEnteries;
		} catch (IOException e1) {
			throw new StateCensusAnalyzerException("Invalid path entered", StateCensusAnalyzerException.ExceptionType.INCORRECT_PATH);
		}catch(IllegalStateException e2) {
			throw new StateCensusAnalyzerException("Invalid state present", StateCensusAnalyzerException.ExceptionType.INCORRECT_STATE);
		}
	}
}
