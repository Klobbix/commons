package com.klobbix.database.query.v2;

public class LimitStatement {
	public static String limit(String value) {
		return "LIMIT " + value;
	}
}
