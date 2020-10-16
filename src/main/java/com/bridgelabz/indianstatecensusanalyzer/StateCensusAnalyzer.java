package com.bridgelabz.indianstatecensusanalyzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
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
			Iterator<StateCensusCSV> stateCensusCSVIterator = getCSVIterator(reader, StateCensusCSV.class);
			Iterable<StateCensusCSV> csvIterable = () -> stateCensusCSVIterator;
			int noOfEnteries = (int) StreamSupport.stream(csvIterable.spliterator(), false)
												  .count();

			String[] expectedHeader = { "State", "Population", "Area In Square Km", "Density Per Square Km" };

			if (isWrongDelimiter(expectedHeader, csvFilePath)) {
				throw new StateCensusAnalyzerException("Invalid delimiter",
						StateCensusAnalyzerException.ExceptionType.INCORRECT_DELIMITER);
			}
			if (isWrongHeader(expectedHeader, csvFilePath)) {
				throw new StateCensusAnalyzerException("Invalid CSV header",
						StateCensusAnalyzerException.ExceptionType.INCORRECT_CSV_HEADER);
			}
			return noOfEnteries;
		} catch (IOException e) {
			throw new StateCensusAnalyzerException("Invalid path entered",
					StateCensusAnalyzerException.ExceptionType.INCORRECT_PATH);
		}
	}

	public int readStateCodeCSVData() throws StateCensusAnalyzerException {

		try (Reader reader = Files.newBufferedReader(csvFilePath)) {
			Iterator<CSVStates> stateCodeCSVIterator = getCSVIterator(reader, CSVStates.class);
			Iterable<CSVStates> csvIterable = () -> stateCodeCSVIterator;
			int noOfEnteries = (int) StreamSupport.stream(csvIterable.spliterator(), false)
												  .count();

			String[] expectedHeader = { "State Name", "State Code" };
			if (isWrongDelimiter(expectedHeader, csvFilePath)) {
				throw new StateCensusAnalyzerException("Invalid delimiter",
						StateCensusAnalyzerException.ExceptionType.INCORRECT_DELIMITER);
			}
			if (isWrongHeader(expectedHeader, csvFilePath)) {
				throw new StateCensusAnalyzerException("Invalid CSV header",
						StateCensusAnalyzerException.ExceptionType.INCORRECT_CSV_HEADER);
			}
			return noOfEnteries;
		} catch (IOException e) {
			throw new StateCensusAnalyzerException("Invalid path entered",
					StateCensusAnalyzerException.ExceptionType.INCORRECT_PATH);
		}
	}

	private <E> Iterator<E> getCSVIterator(Reader reader, Class<E> csvClass) throws StateCensusAnalyzerException {
		try {
			CsvToBeanBuilder<E> builder = new CsvToBeanBuilder<E>(reader);
			CsvToBean<E> csvToBean = builder.withType(csvClass)
											.withIgnoreLeadingWhiteSpace(true)
											.build();
			return csvToBean.iterator();
		} catch (IllegalStateException e) {
			throw new StateCensusAnalyzerException("Invalid state present",
					StateCensusAnalyzerException.ExceptionType.INCORRECT_STATE);
		}
	}

	public boolean isWrongHeader(String[] expectedHeader, Path csvFilePath) throws StateCensusAnalyzerException {
		boolean isWrongHeader = false;
		try (BufferedReader br = Files.newBufferedReader(csvFilePath)) {
			String headerRow = br.readLine();
			String[] header = headerRow.split(",");
			if (!Arrays.equals(expectedHeader, header))
				isWrongHeader = true;
			return isWrongHeader;
		} catch (IOException e) {
			throw new StateCensusAnalyzerException("Invalid path entered",
					StateCensusAnalyzerException.ExceptionType.INCORRECT_PATH);
		}
	}

	public boolean isWrongDelimiter(String[] expectedHeader, Path csvFilePath) throws StateCensusAnalyzerException {
		boolean isWrongDelimiter = false;
		try (BufferedReader br = Files.newBufferedReader(csvFilePath)) {
			String headerRow = br.readLine();
			String[] header = headerRow.split(",");
			if (header.length < expectedHeader.length)
				isWrongDelimiter = true;
			return isWrongDelimiter;
		} catch (IOException e) {
			throw new StateCensusAnalyzerException("Invalid path entered",
					StateCensusAnalyzerException.ExceptionType.INCORRECT_PATH);
		}
	}
}
