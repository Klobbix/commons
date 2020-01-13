package com.klobbix.database.query;

public class QueryBuilder {

	SelectBuilder selectBuilder = new SelectBuilder();

	public FromBuilder selectAll() {
		return selectBuilder.selectAll();
	}

	public FromBuilder select(String... columns) {
		return selectBuilder.select(columns);
	}


}
