package com.bridgelabz.indianstatecensusanalyzer;

public class StateCensusAnalyzerException extends Exception {

	enum ExceptionType {
		INCORRECT_PATH, INCORRECT_STATE, INCORRECT_DELIMETER, INCORRECT_CSV_HEADER;
	}

	ExceptionType type;

	public StateCensusAnalyzerException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}

}