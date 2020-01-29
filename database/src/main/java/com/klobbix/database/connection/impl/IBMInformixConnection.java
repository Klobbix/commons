package com.klobbix.database.connection.impl;

import com.klobbix.database.connection.AbstractConnection;
import com.klobbix.database.connection.HikariConfigBuilder;
import com.zaxxer.hikari.HikariConfig;

public class IBMInformixConnection extends AbstractConnection {

    public IBMInformixConnection(String host, String port, String schema, String user, String password) {
        super(host, port, schema, user, password);
    }

    @Override
    protected HikariConfig buildConfig(String host, String port, String schema, String user, String password) {
        return new HikariConfigBuilder()
            .withDataSourceClassName("com.informix.jdbcx.IfxDataSource")
            .withUsername(user)
            .withPassword(password)
            .withSchema(schema)
            .withProperty("serverName", host)
            .withProperty("portNumber", port)
            .build();
    }
}
