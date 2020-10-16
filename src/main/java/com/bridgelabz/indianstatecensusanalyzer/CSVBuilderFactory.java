package com.bridgelabz.indianstatecensusanalyzer;

import com.bridgelabz.opencsvbuilder.ICSVBuilder;
import com.bridgelabz.opencsvbuilder.OpenCSVBuilder;

public class CSVBuilderFactory {

	public static <E> ICSVBuilder<E> createOpenCSVBuilder() {
		return new OpenCSVBuilder<E>();
	}

	public static <E> ICSVBuilder<E> createCommonsCSVBuilder() {
		return new CommonsCSVBuilder<E>();
	}
}
