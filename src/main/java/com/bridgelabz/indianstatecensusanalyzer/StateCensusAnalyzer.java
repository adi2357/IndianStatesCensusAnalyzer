package com.bridgelabz.indianstatecensusanalyzer;

import java.io.BufferedReader;
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

			BufferedReader br = Files.newBufferedReader(csvFilePath);
			String header = br.readLine();
			String[] columnsForGivenDelimeter = header.split(",");
			if (columnsForGivenDelimeter.length < 4) {
				throw new StateCensusAnalyzerException("Invalid delimiter",
						StateCensusAnalyzerException.ExceptionType.INCORRECT_DELIMITER);
			}
			boolean isEqualsHeader = columnsForGivenDelimeter[0].equals("State")
					&& columnsForGivenDelimeter[1].equals("Population")
					&& columnsForGivenDelimeter[2].equals("Area In Square Km")
					&& columnsForGivenDelimeter[3].equals("Density Per Square Km");
			if (!isEqualsHeader) {
				throw new StateCensusAnalyzerException("Invalid CSV header",
						StateCensusAnalyzerException.ExceptionType.INCORRECT_CSV_HEADER);
			}
			return noOfEnteries;
		} catch (IOException e1) {
			throw new StateCensusAnalyzerException("Invalid path entered",
					StateCensusAnalyzerException.ExceptionType.INCORRECT_PATH);
		} catch (IllegalStateException e2) {
			throw new StateCensusAnalyzerException("Invalid state present",
					StateCensusAnalyzerException.ExceptionType.INCORRECT_STATE);
		}
	}

	public int readStateCodeCSVData() throws StateCensusAnalyzerException {

		try (Reader reader = Files.newBufferedReader(csvFilePath)) {
			CsvToBeanBuilder<CSVStates> builder = new CsvToBeanBuilder<CSVStates>(reader);
			CsvToBean<CSVStates> csvToBean = builder.withType(CSVStates.class).withIgnoreLeadingWhiteSpace(true)
					.build();
			Iterator<CSVStates> StateCodeCSVIterator = csvToBean.iterator();
			Iterable<CSVStates> csvIterable = () -> StateCodeCSVIterator;
			int noOfEnteries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();

			BufferedReader br = Files.newBufferedReader(csvFilePath);
			String header = br.readLine();
			String[] columnsForGivenDelimeter = header.split(",");
			if (columnsForGivenDelimeter.length < 2) {
				throw new StateCensusAnalyzerException("Invalid delimiter",
						StateCensusAnalyzerException.ExceptionType.INCORRECT_DELIMITER);
			}
			boolean isEqualsHeader = columnsForGivenDelimeter[0].equals("State Name")
					&& columnsForGivenDelimeter[1].equals("State Code");
			if (!isEqualsHeader) {
				throw new StateCensusAnalyzerException("Invalid CSV header",
						StateCensusAnalyzerException.ExceptionType.INCORRECT_CSV_HEADER);
			}
			return noOfEnteries;
		} catch (IOException e1) {
			throw new StateCensusAnalyzerException("Invalid path entered",
					StateCensusAnalyzerException.ExceptionType.INCORRECT_PATH);
		} catch (IllegalStateException e2) {
			throw new StateCensusAnalyzerException("Invalid state present",
					StateCensusAnalyzerException.ExceptionType.INCORRECT_STATE);
		}
	}
}
