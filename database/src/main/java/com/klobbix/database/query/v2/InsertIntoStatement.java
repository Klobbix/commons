package com.klobbix.database.query.v2;

import com.klobbix.database.util.StringUtil;

public class InsertIntoStatement {

	public static String insertInto(String table, String[] columns, String[] values) {
		return "INSERT INTO " + table + " ("
				+ StringUtil.separate(columns, ",")
				+ ") VALUES ("
				+ StringUtil.separate(values, ",") + ")";
	}

	public static String insertInto(String table, String... values) {
		return "INSERT INTO " + table + " VALUES (" + StringUtil.separate(values, ",") + ")";
	}

}
