package com.klobbix.messaging;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;

public abstract class AbstractConsumer extends DefaultConsumer {

    private AbstractConsumer(Channel channel) {
        super(channel);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
        onReceive(envelope, properties, body);
        try {
            super.getChannel().basicAck(envelope.getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void onReceive(Envelope envelope, AMQP.BasicProperties properties, byte[] message);
}
