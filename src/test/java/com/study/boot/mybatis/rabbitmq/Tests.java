package com.study.boot.mybatis.rabbitmq;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Xingyu Sun
 * @date 2019/4/16 10:56
 */
public class Tests {
    @Test
    public void publish() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setHost("118.24.118.135");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        String exchangeName = "exchange_test";
        String routingKey = "";
        String message = "hello rabbitmq";
        for (int i = 0; i < 5; i++) {
            channel.basicPublish(exchangeName,routingKey,null,message.getBytes());
        }
        channel.close();
        connection.close();
    }

    @Test
    public void consumer() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setHost("118.24.118.135");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        String queueName = "queue_test";
        channel.basicQos(0,1,false);
        channel.basicConsume(queueName,true, new MyConsumer(channel));
        channel.close();
        connection.close();
    }
}
