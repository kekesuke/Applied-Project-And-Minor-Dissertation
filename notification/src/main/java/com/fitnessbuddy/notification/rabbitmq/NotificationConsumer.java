package com.fitnessbuddy.notification.rabbitmq;

import com.fitnessbuddy.clients.notification.response.NotificationRequest;
import com.fitnessbuddy.notification.services.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
/*
 *RabbitMq Listener consumer class
 */
@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queue.notifications}")
    public void consumer(NotificationRequest notificationRequest){
        log.info("Consumed {} from queue ", notificationRequest);
        notificationService.send((notificationRequest));
    }
}
