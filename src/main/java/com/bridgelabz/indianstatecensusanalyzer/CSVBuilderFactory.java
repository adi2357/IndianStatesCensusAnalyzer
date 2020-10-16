package com.bridgelabz.indianstatecensusanalyzer;
import com.bridgelabz.opencsvbuilder.ICSVBuilder;
import com.bridgelabz.opencsvbuilder.OpenCSVBuilder;

public class CSVBuilderFactory {

	public static <E> ICSVBuilder<E> createCSVBuilder() {
		return new OpenCSVBuilder<E>();
	}
}
