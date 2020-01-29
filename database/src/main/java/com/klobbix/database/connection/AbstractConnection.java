package com.klobbix.database.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractConnection implements DatabaseConnection {

    private HikariDataSource dataSource;
    private HikariConfig config;

    protected AbstractConnection(String host, String port, String schema, String user, String password) {
        config = buildConfig(host, port, schema, user, password);
        dataSource = new HikariDataSource(config);
    }

    protected abstract HikariConfig buildConfig(String host, String port, String schema, String user, String password);

    public HikariDataSource getDataSource() {
        return dataSource;
    }

    public HikariConfig getConfig() {
        return config;
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeDataSource() {
        dataSource.close();
    }
}
