package com.fitnessbuddy.notification.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
 * Notification Queue Configuration
 */
@Configuration
@Getter
@Setter
public class NotificationConfig {
    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${rabbitmq.queue.notifications}")
    private String notificationQueue;

    @Value("${rabbitmq.routing-keys.internal-notifications}")
    private String internalNotificationRoutingKey;

    @Bean //Queue Topic and Binding
    public TopicExchange internalTopicExchange(){
        return  new TopicExchange(this.internalExchange);
    }

    @Bean // Queue Topic and Binding
    public Queue notificationQueue(){
        return new Queue(this.notificationQueue);
    }
    @Bean // Queue Topic and Binding
    public Binding internalToNotificationBinding(){
        return BindingBuilder
                .bind(notificationQueue())
                .to(internalTopicExchange())
                .with(this.internalNotificationRoutingKey);
    }
}
