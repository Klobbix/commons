package com.klobbix.database.query.v2;

import com.klobbix.database.util.StringUtil;

public class GroupByStatement {

	public static String groupBy(String column) {
		return "GROUP BY " + column;
	}

	public static String groupBy(String... columns) {
		return "GROUP BY " + StringUtil.separate(columns, ",");
	}

	public static String groupByHaving(String condition, String... columns) {
		return groupBy(columns) + " HAVING " + condition;
	}

}
