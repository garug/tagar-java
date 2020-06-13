package br.com.garug.tagar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.UUID;

@Controller
public class MessageController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/secured/room")
    public void sendRoom(@Payload Message msg, @Header("id") String id, SimpMessageHeaderAccessor headerAccessor) throws Exception {
        LocalDateTime timestamp = LocalDateTime.now();
        var incomingMsg = new Message(msg.getFrom(), msg.getMessage(), timestamp);
        simpMessagingTemplate.convertAndSendToUser("teste", "secured/user/specific", msg);
    }
}
