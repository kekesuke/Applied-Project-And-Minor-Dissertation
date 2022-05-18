
package com.fitnessbuddy.stomp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.web.socket.config.annotation.*;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").setAllowedOrigins("*").withSockJS();

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config){

        long [] heartbeat = new long[2];
        heartbeat[0] = 30000;
        heartbeat[1] = 30000;

        config.enableSimpleBroker("/topic","/queue", "/exchange")
                .setTaskScheduler(new DefaultManagedTaskScheduler())
                .setHeartbeatValue(heartbeat);

        config.setApplicationDestinationPrefixes("/topic", "/queue");
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration reg){
        reg.setMessageSizeLimit(8 * 1024);
    }

}
