package org.czy.activemq.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.JmsListener;

import static org.czy.activemq.MessagingConstants.TOPIC;

@SpringBootApplication
@ComponentScan(basePackages = "org.czy.activemq")
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @JmsListener(destination = TOPIC, containerFactory = "jmsQueueListenerContainerFactory")
    public void receive1(String msg) {
        System.out.println("Consumer1: Message: " + msg);
    }

    @JmsListener(destination = TOPIC, containerFactory = "jmsQueueListenerContainerFactory")
    public void receive2(String msg) {
        System.out.println("Consumer2: Message: " + msg);
    }
}
