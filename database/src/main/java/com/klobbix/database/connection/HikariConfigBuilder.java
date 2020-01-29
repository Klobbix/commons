package com.klobbix.database.connection;

import com.zaxxer.hikari.HikariConfig;

public class HikariConfigBuilder {

    private final HikariConfig config;

    public HikariConfigBuilder() {
        config = new HikariConfig();
    }

    public HikariConfigBuilder withUsername(String username) {
        config.setUsername(username);
        return this;
    }

    //Defaults to true
    public HikariConfigBuilder withAutoCommit(boolean autoCommit) {
        config.setAutoCommit(autoCommit);
        return this;
    }

    //Defaults to 30000 (30 seconds)
    public HikariConfigBuilder withConnectionTimeout(long timeout) {
        config.setConnectionTimeout(timeout);
        return this;
    }

    //Defaults to 600000 (10 minutes)
    //Setting to 0 will never remove idle connections from the pool
    public HikariConfigBuilder withIdleTimeout(long idleTimeout) {
        config.setIdleTimeout(idleTimeout);
        return this;
    }

    //Defaults to 1800000 (30 minutes)
    //Setting to 0 indicates infinite
    public HikariConfigBuilder withMaxLifetime(long maxLifetime) {
        config.setMaxLifetime(maxLifetime);
        return this;
    }

    //Defaults to maximumPoolSize
    public HikariConfigBuilder withMinimumIdle(int minimumIdle) {
        config.setMinimumIdle(minimumIdle);
        return this;
    }

    //Defaults to 10
    public HikariConfigBuilder withMaximumPoolSize(int maximumPoolSize) {
        config.setMaximumPoolSize(maximumPoolSize);
        return this;
    }

    //Defaults to auto-generated value
    public HikariConfigBuilder withPoolName(String poolName) {
        config.setPoolName(poolName);
        return this;
    }

    public HikariConfigBuilder withSchema(String schema) {
        config.setSchema(schema);
        return this;
    }

    public HikariConfigBuilder withPassword(String password) {
        config.setPassword(password);
        return this;
    }

    public HikariConfigBuilder withJdbcUrl(String jdbcUrl) {
        config.setJdbcUrl(jdbcUrl);
        return this;
    }

    /**
     * Omit this property unless you get an obvious error message indicating that the driver was not found.
     *
     * @param driverClassName The driver class name
     * @return HikariConfigBuilder
     */
    public HikariConfigBuilder withDriverClassName(String driverClassName) {
        config.setDriverClassName(driverClassName);
        return this;
    }

    public HikariConfigBuilder withDataSourceClassName(String dataSourceClassName) {
        config.setDataSourceClassName(dataSourceClassName);
        return this;
    }

    public HikariConfigBuilder withProperty(String property, String value) {
        config.addDataSourceProperty(property, value);
        return this;
    }

    public HikariConfig build() {
        return config;
    }
}
