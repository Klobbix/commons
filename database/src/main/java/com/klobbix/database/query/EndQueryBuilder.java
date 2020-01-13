package com.klobbix.database.query;

public class EndQueryBuilder {

	private final String previous;

	protected EndQueryBuilder(String previous) {
		this.previous = previous;
	}

	public String build() {
		return previous;
	}
}
