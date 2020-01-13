package com.klobbix.database.query;

import com.klobbix.database.util.StringUtil;

public class WhereBuilder {

	private final String previous;

	public WhereBuilder(String previous) {
		this.previous = previous;
	}

	public GroupByBuilder where(String... conditions) {
		return new GroupByBuilder(get(StringUtil.separate(conditions, " AND ")));
	}

	public OrderByBuilder groupBy(String... columns) {
		return new OrderByBuilder(previous + " GROUP BY " + StringUtil.separate(columns, ","));
	}

	public EndQueryBuilder orderBy(String... columns) {
		return orderBy(false, columns);
	}

	public EndQueryBuilder orderBy(boolean desc, String... columns) {
		String str = previous + " ORDER BY " + StringUtil.separate(columns, ",");
		if (desc) {
			str += " DESC";
		}
		return new EndQueryBuilder(str);
	}

	public String build() {
		return previous;
	}

	private String get(String conditions) {
		return previous + " WHERE " + conditions;
	}
}
