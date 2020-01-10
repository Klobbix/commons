package com.klobbix.messaging;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.GetResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class ChannelWrapper {

    private final Channel channel;

    public ChannelWrapper(Channel channel) {
        this.channel = channel;
    }

    /**
     * Returns the RabbitMQ Channel object.
     *
     * @return The channel
     */
    public Channel handle() {
        return channel;
    }

    /**
     * Returns whether or not the channel is open and not closing/closed.
     *
     * @return True if open otherwise false
     */
    public boolean isOpen() {
        return channel.isOpen();
    }

    /**
     * Creates an exchange.
     *
     * @param name The name of the exchange
     */
    public void createExchange(String name) {
        try {
            channel.exchangeDeclare(name, "direct", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes an exchange.
     *
     * @param name The name of the exchange
     */
    public void deleteExchange(String name) {
        try {
            channel.exchangeDelete(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a queue with the following default parameters:
     * Durable: true
     * Exclusive: false
     * AutoDelete: false
     * Args: null
     *
     * @param name The name of the queue
     */
    public void createQueue(String name) {
        createQueue(name, true, false, false, null);
    }

    /**
     * Creates a queue.
     *
     * @param name       The name of the queue
     * @param durable    The queue will survive a broker restart
     * @param exclusive  Used by only one connection and the queue will be deleted when that connection closes
     * @param autoDelete Queue that has had at least one consumer is deleted when last consumer unsubscribes
     * @param args       Optional - used by plugins and broker-specific features such as message TTL, queue length limit, etc
     */
    public void createQueue(String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> args) {
        try {
            channel.queueDeclare(name, durable, exclusive, autoDelete, args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a queue.
     *
     * @param name The name of the queue
     */
    public void deleteQueue(String name) {
        try {
            channel.queueDelete(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes any messages in the queue.
     *
     * @param name The queue
     */
    public void purgeQueue(String name) {
        try {
            channel.queuePurge(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates and binds to an exchange with a given routing key. This will set the queue name to be randomly declared.
     *
     * @param exchange   The exchange to create and bind to
     * @param routingKey The routing key for the bind
     */
    public void createAndBind(String exchange, String routingKey) {
        createExchange(exchange);
        String queue = null;
        try {
            queue = channel.queueDeclare().getQueue();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bind(queue, exchange, routingKey);
    }

    /**
     * Creates and binds to an exchange/queue with a given routing key.
     *
     * @param queue      The queue to create and bind to
     * @param exchange   The exchange to create and bind to
     * @param routingKey The routing key for the bind
     */
    public void createAndBind(String queue, String exchange, String routingKey) {
        createExchange(exchange);
        createQueue(queue);
        bind(queue, exchange, routingKey);
    }

    /**
     * Binds an exchange/queue with a given routing key.
     *
     * @param queue      The queue to bind
     * @param exchange   The exchange to bind
     * @param routingKey The routing key for the bind
     */
    public void bind(String queue, String exchange, String routingKey) {
        try {
            channel.queueBind(queue, exchange, routingKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Publishes a message to the exchange.
     *
     * @param exchange   The name of the exchange to send the message to
     * @param routingKey The routing key
     * @param message    The message to send
     */
    public void publish(String exchange, String routingKey, String message) {
        publish(exchange, routingKey, null, message.getBytes());
    }

    /**
     * Publishes a message to the exchange.
     *
     * @param exchange   The name of the exchange to send the message to
     * @param routingKey The routing key
     * @param properties Optional properties to send with the message
     * @param message    The message to send
     */
    public void publish(String exchange, String routingKey, AMQP.BasicProperties properties, String message) {
        publish(exchange, routingKey, properties, message.getBytes());
    }

    /**
     * Publishes a message to the exchange.
     *
     * @param exchange   The name of the exchange to send the message to
     * @param routingKey The routing key
     * @param properties Optional properties to send with the message
     * @param message    The message to send
     */
    public void publish(String exchange, String routingKey, AMQP.BasicProperties properties, byte[] message) {
        try {
            channel.basicPublish(exchange, routingKey, properties, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Subscribes as a consumer to listen for messages automatically.
     *
     * @param queueName The queue to listen to
     * @param tag       The consumer tag
     * @param consumer  The consumer to handle the received message
     */
    public void subscribe(String queueName, String tag, Consumer consumer) {
        try {
            channel.basicConsume(queueName, false, tag, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Unsubscribes from being a consumer for a given consumer tag.
     *
     * @param tag The consumer tag
     */
    public void unsubscribe(String tag) {
        try {
            channel.basicCancel(tag);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a response that a message was received
     *
     * @param deliveryTag The message received delivery tag id.
     *                    This is retrieved through the received message's GetReponse.getEnvelope().getDeliveryTag() method.
     */
    public void acknowledge(long deliveryTag) {
        try {
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Asks the queue for any messages. This is not needed if subscribe() is called already.
     *
     * @param queueName The name of the queue
     * @param autoAck   To auto-acknowledge that the message was received.
     *                  If this is set to false, you must explicitly call acknowledge() after handling the message.
     * @return
     */
    public GetResponse pull(String queueName, boolean autoAck) {
        GetResponse response = null;
        try {
            response = channel.basicGet(queueName, autoAck);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Closes the channel.
     */
    public void close() {
        try {
            channel.close();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }


}
