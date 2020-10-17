package com.bridgelabz.indianstatecensusanalyzer;

public class StateCensusAnalyzerException extends Exception {

	public enum ExceptionType {
		INCORRECT_PATH, INCORRECT_STATE, INCORRECT_DELIMITER, INCORRECT_CSV_HEADER;
	}

	ExceptionType type;

	public StateCensusAnalyzerException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}
}
