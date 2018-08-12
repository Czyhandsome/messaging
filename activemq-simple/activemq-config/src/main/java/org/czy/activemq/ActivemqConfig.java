package org.czy.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

@Configuration
public class ActivemqConfig {
    private static final String ActiveMQ_URL = "tcp://localhost:61616";
    private static final String ActiveMQ_USER = "admin";
    private static final String ActiveMQ_PASSWORD = "admin";

    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(ActiveMQ_URL);
        connectionFactory.setUserName(ActiveMQ_USER);
        connectionFactory.setPassword(ActiveMQ_PASSWORD);
        return connectionFactory;
    }

    @Bean(name = "jmsQueueListenerContainerFactory")
    public DefaultJmsListenerContainerFactory jmsQueueListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        // 设置连接数
        factory.setConcurrency("3-10");
        // 重连间隔时间
        factory.setRecoveryInterval(1000L);
        // 允许发布-订阅模式
        factory.setPubSubDomain(true);
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        // 允许发布-订阅模式
        jmsTemplate.setPubSubDomain(true);
        return jmsTemplate;
    }
}
