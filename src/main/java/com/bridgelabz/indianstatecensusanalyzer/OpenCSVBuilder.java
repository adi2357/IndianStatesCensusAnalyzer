package com.bridgelabz.indianstatecensusanalyzer;

import java.io.Reader;
import java.util.Iterator;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder<E> implements ICSVBuilder<E> {

	public Iterator<E> getCSVIterator(Reader reader, Class<E> csvClass) throws CSVException {
		try {
			CsvToBeanBuilder<E> builder = new CsvToBeanBuilder<E>(reader);
			CsvToBean<E> csvToBean = builder.withType(csvClass)
											.withIgnoreLeadingWhiteSpace(true)
											.build();
			return csvToBean.iterator();
		} catch (IllegalStateException e) {
			throw new CSVException("Invalid state present", CSVException.ExceptionType.INCORRECT_STATE);
		}
	}
}
