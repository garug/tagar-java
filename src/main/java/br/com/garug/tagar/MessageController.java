package br.com.garug.tagar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class MessageController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private SimpUserRegistry simpUserRegistry;

    private static final ConcurrentHashMap<String, Principal> actualLookingForChat = new ConcurrentHashMap<>();

    @MessageMapping("/secured/new")
    public void newRoom(Principal principal) {
        actualLookingForChat.entrySet().stream()
                .filter(entry -> !entry.getKey().equals(principal.getName()))
                .findAny()
                .ifPresentOrElse(
                        stranger -> {
                            var chatKey = UUID.randomUUID().toString();
                            simpMessagingTemplate.convertAndSendToUser(stranger.getKey(), "", chatKey);
                            simpMessagingTemplate.convertAndSendToUser(principal.getName(), "", chatKey);
                            actualLookingForChat.remove(stranger.getKey());
                        },
                        () -> actualLookingForChat.put(principal.getName(), principal));
    }

    @MessageMapping("/chat/{chatId}")
    @SendTo("/chat/{chatId}")
    public Message messageChat(@Payload String message, Principal principal, @DestinationVariable String chatId) {
        return new Message(principal.getName(), message);
    }
}
