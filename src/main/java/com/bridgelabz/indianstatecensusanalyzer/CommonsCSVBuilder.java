package com.bridgelabz.indianstatecensusanalyzer;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import com.bridgelabz.opencsvbuilder.CSVException;
import com.bridgelabz.opencsvbuilder.ICSVBuilder;

public class CommonsCSVBuilder<E> implements ICSVBuilder<E> {

	@Override
	public Iterator<E> getCSVIterator(Reader reader, Class<E> csvClass) throws CSVException {
		try {
			CSVParser parser = CSVParser.parse(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
			return (Iterator<E>) parser.getRecords().iterator();
		} catch (IllegalStateException | IOException e) {
			throw new CSVException("Invalid state present", CSVException.ExceptionType.INCORRECT_STATE);
		}
	}
}
