package com.bridgelabz.indianstatecensusanalyzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.bridgelabz.opencsvbuilder.CSVException;
import com.bridgelabz.opencsvbuilder.ICSVBuilder;

public class StateCensusAnalyzer {
	public Path csvFilePath;

	public StateCensusAnalyzer(Path csvFilePath) {
		this.csvFilePath = csvFilePath;
	}

	public int readStateCensusCSVData() throws StateCensusAnalyzerException {

		try (Reader reader = Files.newBufferedReader(csvFilePath)) {
			ICSVBuilder<StateCensusCSV> csvBuilder = CSVBuilderFactory.createCommonsCSVBuilder();
			Iterator<StateCensusCSV> stateCensusCSVIterator = csvBuilder.getCSVIterator(reader, StateCensusCSV.class);

			String[] expectedHeader = { "State", "Population", "Area In Square Km", "Density Per Square Km" };
			if (isWrongDelimiter(expectedHeader, csvFilePath)) {
				throw new StateCensusAnalyzerException("Invalid delimiter", StateCensusAnalyzerException.ExceptionType.INCORRECT_DELIMITER);
			}
			if (isWrongHeader(expectedHeader, csvFilePath)) {
				throw new StateCensusAnalyzerException("Invalid CSV header", StateCensusAnalyzerException.ExceptionType.INCORRECT_CSV_HEADER);
			}
			return getCount(stateCensusCSVIterator);
		} catch (IOException | CSVException e) {
			throw new StateCensusAnalyzerException("Invalid path entered", StateCensusAnalyzerException.ExceptionType.INCORRECT_PATH);
		}
	}

	public int readStateCodeCSVData() throws StateCensusAnalyzerException {

		try (Reader reader = Files.newBufferedReader(csvFilePath)) {
			ICSVBuilder<CSVStates> csvBuilder = CSVBuilderFactory.createCommonsCSVBuilder();
			Iterator<CSVStates> stateCodeCSVIterator = csvBuilder.getCSVIterator(reader, CSVStates.class);

			String[] expectedHeader = { "State Name", "State Code" };
			if (isWrongDelimiter(expectedHeader, csvFilePath)) {
				throw new StateCensusAnalyzerException("Invalid delimiter", StateCensusAnalyzerException.ExceptionType.INCORRECT_DELIMITER);
			}
			if (isWrongHeader(expectedHeader, csvFilePath)) {
				throw new StateCensusAnalyzerException("Invalid CSV header", StateCensusAnalyzerException.ExceptionType.INCORRECT_CSV_HEADER);
			}
			return getCount(stateCodeCSVIterator);
		} catch (IOException | CSVException e) {
			throw new StateCensusAnalyzerException("Invalid path entered", StateCensusAnalyzerException.ExceptionType.INCORRECT_PATH);
		}
	}

	private <E> int getCount(Iterator<E> csvIterator) {
		Iterable<E> csvIterable = () -> csvIterator;
		int noOfEnteries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
		return noOfEnteries;
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
			throw new StateCensusAnalyzerException("Invalid path entered", StateCensusAnalyzerException.ExceptionType.INCORRECT_PATH);
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
			throw new StateCensusAnalyzerException("Invalid path entered", StateCensusAnalyzerException.ExceptionType.INCORRECT_PATH);
		}
	}
}
