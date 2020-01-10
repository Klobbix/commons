package com.klobbix.messaging;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class RabbitMQConnectionBuilder {

    private ConnectionFactory factory;

    /**
     * Sets the uri connection string.
     * Example: amqp://userName:password@hostName:portNumber/virtualHost
     *
     * @param uri The connection string
     * @return The builder
     */
    public RabbitMQConnectionBuilder withUri(String uri) {
        try {
            factory.setUri(uri);
        } catch (URISyntaxException | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Sets the username. Defaults to guest if none is provided.
     *
     * @param username The username for the connection
     * @return The builder
     */
    public RabbitMQConnectionBuilder withUsername(String username) {
        factory.setUsername(username);
        return this;
    }

    /**
     * Sets the password. Defaults to guest if none is provided.
     *
     * @param password The password for the connection
     * @return The builder
     */
    public RabbitMQConnectionBuilder withPassword(String password) {
        factory.setPassword(password);
        return this;
    }

    /**
     * Sets the virtual host. Defaults to / if none is provided.
     *
     * @param virtualHost The virtual host for the connection
     * @return The builder
     */
    public RabbitMQConnectionBuilder withVirtualHost(String virtualHost) {
        factory.setVirtualHost(virtualHost);
        return this;
    }

    /**
     * Sets the host. Defaults to localhost if none is provided.
     *
     * @param host The host for the connection
     * @return The builder
     */
    public RabbitMQConnectionBuilder withHost(String host) {
        factory.setHost(host);
        return this;
    }

    /**
     * Sets the port. Defaults to 5672 for regular connections and 5671 for TLS connections if none is provided.
     *
     * @param port The port for the connection
     * @return The builder
     */
    public RabbitMQConnectionBuilder withPort(int port) {
        factory.setPort(port);
        return this;
    }

    /**
     * Builds the connection using the parameters from the builder object.
     *
     * @return A RabbitMQ Connection
     */
    public Connection build() {
        try {
            return factory.newConnection();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }
}
