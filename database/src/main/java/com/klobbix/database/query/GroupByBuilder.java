package com.klobbix.database.query;

import com.klobbix.database.util.StringUtil;

public class GroupByBuilder {

	private final String previous;

	protected GroupByBuilder(String previous) {
		this.previous = previous;
	}

	public OrderByBuilder groupBy(String... columns) {
		return new OrderByBuilder(get(StringUtil.separate(columns, ",")));
	}

	public EndQueryBuilder orderBy(String... columns) {
		String str = previous + StringUtil.separate(columns, ",");
		return new EndQueryBuilder(str);
	}

	private void validate(String columns) throws InvalidQueryException {
		if (columns == null || columns.trim().equals("")) {
			throw new InvalidQueryException("Invalid GROUP BY statement");
		}
	}

	private String get(String columns) {
		try {
			validate(columns);
		} catch (InvalidQueryException e) {
			e.printStackTrace();
		}
		return previous + " GROUP BY " + columns;
	}

	public String build() {
		return previous;
	}
}
