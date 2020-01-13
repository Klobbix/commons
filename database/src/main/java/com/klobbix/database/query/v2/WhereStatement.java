package com.klobbix.database.query.v2;

import com.klobbix.database.util.StringUtil;

public class WhereStatement {

	public static String where(String condition) {
		return "WHERE " + condition;
	}

	public static String whereNot(String condition) {
		return "WHERE NOT" + condition;
	}

	public static String whereAnd(String... conditions) {
		return "WHERE " + StringUtil.separate(conditions, " AND ");
	}

	public static String whereOr(String... conditions) {
		return "WHERE " + StringUtil.separate(conditions, " OR ");
	}

	public static String in(String value) {
		return "IN (" + value + ")";
	}

	public static String in(String... values) {
		return "IN (" + StringUtil.separate(values, ",") + ")";
	}

	public static String like(String pattern) {
		return "LIKE " + pattern;
	}

	public static String isNull() {
		return "IS NULL";
	}

	public static String isNotNull() {
		return "IS NOT NULL";
	}

	public static String notBetween(String value1, String value2) {
		return "NOT BETWEEN " + value1 + " AND " + value2;
	}

	public static String between(String value1, String value2) {
		return "BETWEEN " + value1 + " AND " + value2;
	}
}
