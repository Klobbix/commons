package com.klobbix.database.query;

import com.klobbix.database.util.StringUtil;

public class OrderByBuilder {

	private final String previous;

	protected OrderByBuilder(String previous) {
		this.previous = previous;
	}

	public EndQueryBuilder orderBy(String... columns) {
		return orderBy(false, columns);
	}

	public EndQueryBuilder orderBy(boolean desc, String... columns) {
		String str = get(StringUtil.separate(columns, ","));
		if (desc) {
			str += " DESC";
		}
		return new EndQueryBuilder(str);
	}

	private void validate(String columns) throws InvalidQueryException {
		if (columns == null || columns.trim().equals("")) {
			throw new InvalidQueryException("Invalid ORDER BY statement");
		}
	}

	private String get(String columns) {
		try {
			validate(columns);
		} catch (InvalidQueryException e) {
			e.printStackTrace();
		}
		return previous + " ORDER BY " + columns;
	}

	public String build() {
		return previous;
	}
}
