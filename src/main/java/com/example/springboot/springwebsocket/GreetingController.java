package com.example.springboot.springwebsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
public class GreetingController {

    final SimpMessagingTemplate template;

    public GreetingController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greet(HelloMessage message) {
        return new Greeting("Hello," + HtmlUtils.htmlEscape(message.getName()));
    }

    @MessageMapping("/private-hello")
    @SendToUser("/topic/private-greetings")
    public Greeting privateGreet(HelloMessage message, final Principal principal) throws InterruptedException {
        Thread.sleep(1000);
        return new Greeting(HtmlUtils.htmlEscape("Hello privately, Sending private greeting to user: " + principal.getName() + message.getName()));
    }

    @PostMapping(value = "/send-private-hello/{id}")
    @ResponseBody
    public void sendPrivateGreet(@PathVariable final String id, @RequestBody HelloMessage message) throws InterruptedException {
        Thread.sleep(1000);
        template.convertAndSendToUser(id, "/topic/private-greetings",
                new Greeting(HtmlUtils.htmlEscape("Hello privately, Sending private greeting to user: "
                        + message.getName())));
    }
 }
