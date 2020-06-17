package br.com.garug.tagar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class MessageController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private SimpUserRegistry simpUserRegistry;

    @Autowired
    private RoomManager roomManager;

    private static final ConcurrentHashMap<String, Principal> actualLookingForChat = new ConcurrentHashMap<>();

    @MessageMapping("/secured/new")
    public void newRoom(Principal principal) {

        actualLookingForChat.entrySet().stream()
                .filter(entry -> !entry.getKey().equals(principal.getName()))
                .findAny()
                .ifPresentOrElse(
                        stranger -> {
                            var users = List.of(principal, stranger.getValue());
                            users.forEach( user -> simpMessagingTemplate.convertAndSendToUser(user.getName(), "", UUID.randomUUID()));
                            actualLookingForChat.remove(stranger.getKey());
                        },
                        () -> actualLookingForChat.put(principal.getName(), principal));
    }

    @MessageMapping("/chat/{chatId}")
    @SendTo("/chat/{chatId}")
    public Message messageChat(@Payload String message, Principal principal, @DestinationVariable String chatId) {
        return new Message(principal.getName(), message);
    }

    @SubscribeMapping("/chat/{chatId}")
    public String listenSubscriptions(@DestinationVariable String chatId, MessageHeaders headers, Principal user) {
        // TODO criar sala aqui
        System.out.println(headers);
        System.out.println(user);
        return "Conectado";
    }
}
