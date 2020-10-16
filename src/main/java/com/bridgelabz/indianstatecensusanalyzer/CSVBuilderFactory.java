package com.bridgelabz.indianstatecensusanalyzer;

public class CSVBuilderFactory {

	public static <E> ICSVBuilder<E> createCSVBuilder() {
		return new OpenCSVBuilder<E>();
	}

}
