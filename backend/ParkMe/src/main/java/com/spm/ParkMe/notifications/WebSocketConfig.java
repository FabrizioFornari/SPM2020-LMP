package com.spm.ParkMe.notifications;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/notification", "/SPM2020-LMP/notification");
        registry.setApplicationDestinationPrefixes("/swns", "/SPM2020-LMP/swns");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/notifications")
                .setAllowedOrigins("http://localhost:4200", "http://127.0.0.1:4200", "http://localhost:8080", "http://127.0.0.1:8080", "http://localhost:8080/SPM2020-LMP", "http://127.0.0.1:8080/SPM2020-LMP")
                .withSockJS();
        
        registry.addEndpoint("/SPM2020-LMP/notifications")
        .setAllowedOrigins("http://localhost:4200", "http://127.0.0.1:4200", "http://localhost:8080", "http://127.0.0.1:8080", "http://localhost:8080/SPM2020-LMP", "http://127.0.0.1:8080/SPM2020-LMP")
        .withSockJS();
    }
}