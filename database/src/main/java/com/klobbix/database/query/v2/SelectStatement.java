package com.klobbix.database.query.v2;

import com.klobbix.database.util.StringUtil;

public class SelectStatement {

	public static String select(String column) {
		return "SELECT " + column;
	}

	public static String selectDistinct(String column) {
		return "SELECT DISTINCT " + column;
	}

	public static String selectDistinct(String... columns) {
		return "SELECT DISTINCT " + StringUtil.separate(columns, ",");
	}

	public static String select(String... columns) {
		return "SELECT " + StringUtil.separate(columns, ",");
	}

	public static String selectAll() {
		return "SELECT *";
	}
	
}
