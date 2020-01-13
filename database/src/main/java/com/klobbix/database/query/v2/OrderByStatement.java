package com.klobbix.database.query.v2;

import com.klobbix.database.util.StringUtil;

public class OrderByStatement {

	public static String orderBy(String column) {
		return orderBy(false, column);
	}

	public static String orderBy(String... columns) {
		return orderBy(false, columns);
	}

	public static String orderBy(boolean desc, String column) {
		String order = "ORDER BY " + column;
		return desc ? (order + " DESC") : order;
	}

	public static String orderBy(boolean desc, String... columns) {
		String order = "ORDER BY " + StringUtil.separate(columns, ",");
		return desc ? (order + " DESC") : order;
	}

}
