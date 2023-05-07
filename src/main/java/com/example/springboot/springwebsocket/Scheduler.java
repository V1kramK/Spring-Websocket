package com.example.springboot.springwebsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class Scheduler {
    @Autowired
    SimpMessagingTemplate template;

//     @Scheduled(fixedDelay = 5000)
//     public void sendMessage() {
//         template.convertAndSend("/topic/greetings",new Greeting("Scheduled Message"));
//     }
}
