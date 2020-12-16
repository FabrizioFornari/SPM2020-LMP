package com.spm.ParkMe.notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;

import com.google.common.net.HttpHeaders;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.models.UserSession;
import com.spm.ParkMe.repositories.UserRepository;
import com.spm.ParkMe.security.jwt.JwtUtils;

@Controller
public class NotificationsController {

    private final NotificationDispatcher dispatcher;
    
    @Autowired
	JwtUtils jwtUtils;
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    public NotificationsController(NotificationDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @MessageMapping("/start")
    public void start(StompHeaderAccessor stompHeaderAccessor, Authentication authentication) {
    	String token = stompHeaderAccessor.getFirstNativeHeader(HttpHeaders.AUTHORIZATION);
    	User user = userRepository.findByUsername(jwtUtils.getUserNameFromJwtToken(token)).orElseThrow(() -> new UsernameNotFoundException("User Not Found with token: " + token));
    	UserSession session = new UserSession(user, stompHeaderAccessor.getSessionId());
        dispatcher.add(session);
    }

    @MessageMapping("/stop")
    public void stop(StompHeaderAccessor stompHeaderAccessor) {
        dispatcher.remove(stompHeaderAccessor.getSessionId());
    }
}