package com.klobbix.database.query;

import com.klobbix.database.util.StringUtil;

public class SelectBuilder {

	protected SelectBuilder() {
	}

	public FromBuilder select(String... columns) {
		return new FromBuilder(get(StringUtil.separate(columns, ",")));
	}

	private void validate(String columns) throws InvalidQueryException {
		if(columns == null || columns.trim().equals("")) {
			throw new InvalidQueryException("Missing SELECT columns");
		}
	}

	public FromBuilder selectAll() {
		return new FromBuilder(get("*"));
	}

	private String get(String columns) {
		try {
			validate(columns);
		} catch (InvalidQueryException e) {
			e.printStackTrace();
		}
		return "SELECT " + columns;
	}
}
