package com.klobbix.database.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

public interface DatabaseConnection {

	HikariDataSource getDataSource();

	HikariConfig getConfig();

	Connection getConnection();

	void closeConnection();

}
