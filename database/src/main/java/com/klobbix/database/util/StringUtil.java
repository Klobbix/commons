package com.klobbix.database.util;

public class StringUtil {

	public static String separate(String[] strings, String separator) {
		StringBuilder str = new StringBuilder();
		for (String s : strings) {
			str.append(s).append(separator);
		}
		return str.toString().substring(0, str.lastIndexOf(separator));
	}

}
