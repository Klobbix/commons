package com.klobbix.database.connection.impl;

import com.klobbix.database.connection.AbstractConnection;
import com.klobbix.database.connection.HikariConfigBuilder;
import com.zaxxer.hikari.HikariConfig;

public class MySqlConnection extends AbstractConnection {

	public MySqlConnection(String host, String port, String schema, String user, String password) {
		super(host, port, schema, user, password);
	}

	protected HikariConfig buildConfig(String host, String port, String schema, String user, String password) {
		return new HikariConfigBuilder()
				.withJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + schema + "?useSSL=false")
				.withUsername(user)
				.withPassword(password)
				.withDriverClassName("com.mysql.cj.jdbc.Driver")
				.withProperty("cachePrepStmts", "true")
				.withProperty("prepStmtCacheSize", "250")
				.withProperty("prepStmtCacheSqlLimit", "2048")
				.withProperty("useServerPrepStmts", "true")
				.build();
	}
}
