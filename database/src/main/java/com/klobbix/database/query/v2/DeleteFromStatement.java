package com.klobbix.database.query.v2;

public class DeleteFromStatement {

	public static String deleteFrom(String table) {
		return "DELETE FROM " + table;
	}

}
