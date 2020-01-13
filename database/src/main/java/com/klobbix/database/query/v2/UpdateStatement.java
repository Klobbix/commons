package com.klobbix.database.query.v2;

import java.util.Map;

public class UpdateStatement {

	public static String update(String table, Map<String, String> values) {
		StringBuilder builder = new StringBuilder();
		values.forEach((key, value) -> {
			builder.append(key);
			builder.append("=");
			builder.append(value);
			builder.append(",");
		});
		return "UPDATE " + table + " SET " + builder.toString().substring(0, builder.length() - 1);
	}

}
