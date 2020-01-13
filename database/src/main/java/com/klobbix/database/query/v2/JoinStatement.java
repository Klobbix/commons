package com.klobbix.database.query.v2;

public class JoinStatement {

	//returns rows that have matching values in both tables.
	public static String innerJoin(String table, String condition) {
		return "INNER JOIN " + table + " ON " + condition;
	}

	//returns all rows from the left table, and the matching rows from the right table.
	// The result is NULL from the right side, if there is no match.
	public static String leftJoin(String table, String condition) {
		return "LEFT JOIN " + table + " ON " + condition;
	}

	//returns all rows from the right table, and the matching records from the left table.
	// The result is NULL from the left side, when there is no match.
	public static String rightJoin(String table, String condition) {
		return "RIGHT JOIN " + table + " ON " + condition;
	}

	//returns all rows when there is a match in either left table or right table.
	public static String fullOuterJoin(String table, String condition) {
		return "FULL OUTER JOIN " + table + " ON " + condition;
	}
}
