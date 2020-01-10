package com.klobbix.messaging;

import com.rabbitmq.client.Address;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class RabbitMQClient {

    private Connection connection;
    private Map<String, ChannelWrapper> channels = new HashMap<>();

    /**
     * Sets up a default RabbitMQ client for local development.
     * Username: guest
     * Password: guest
     * Virtual Host: /
     * Hostname: Localhost
     * Port: 5672 for regular connections, 5671 for TLS connections
     */
    public RabbitMQClient() {
        connection = new RabbitMQConnectionBuilder().build();
    }

    /**
     * Sets up a RabbitMQ client using a provided uri connection string.
     *
     * @param uri The connection string
     */
    public RabbitMQClient(String uri) {
        connection = new RabbitMQConnectionBuilder().withUri(uri).build();
    }

    /**
     * Sets up a RabbitMQ client using an array of connection addresses.
     *
     * @param addresses The addresses to connect to in order
     */
    public RabbitMQClient(Address[] addresses) {
        try {
            connection = new ConnectionFactory().newConnection(addresses);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets up a RabbitMQ client using parameters.
     *
     * @param username    The username to connect with
     * @param password    The password to connect with
     * @param virtualHost The virtual host to connect with
     * @param host        The host to connect to
     * @param port        The port to connect to
     */
    public RabbitMQClient(String username, String password, String virtualHost, String host, int port) {
        connection = new RabbitMQConnectionBuilder()
                .withUsername(username)
                .withPassword(password)
                .withVirtualHost(virtualHost)
                .withHost(host)
                .withPort(port)
                .build();
    }

    /**
     * Creates a new channel for the connection to use.
     *
     * @param name The name of the channel
     */
    public void createChannel(String name) {
        ChannelWrapper channelWrapper = null;
        try {
            Channel channel = connection.createChannel();
            channelWrapper = new ChannelWrapper(channel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (channelWrapper != null) {
            channels.put(name, channelWrapper);
        }
    }

    /**
     * Returns the connection.
     *
     * @return The connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Closes the connection.
     */
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Retrieves a created ChannelWrapper object if it exists.
     *
     * @param name The name of the created channel
     * @return The ChannelWrapper otherwise null
     */
    public ChannelWrapper getChannel(String name) {
        return channels.get(name);
    }
}
