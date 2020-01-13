package com.klobbix.database.query;

public class FromBuilder {
	private final String previous;

	protected FromBuilder(String previous) {
		this.previous = previous;
	}

	public WhereBuilder from(String table) {
		return new WhereBuilder(get(table));
	}

	private void validate(String table) throws InvalidQueryException {
		if (table == null || table.trim().equals("")) {
			throw new InvalidQueryException("Missing FROM table");
		}
	}

	private String get(String table) {
		try {
			validate(table);
		} catch (InvalidQueryException e) {
			e.printStackTrace();
		}
		return previous + " FROM " + table;
	}
}
