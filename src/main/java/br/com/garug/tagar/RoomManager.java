package br.com.garug.tagar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomManager {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public RoomManager(@Lazy SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    private final static List<Room> rooms = new ArrayList<>();

    public void add(Room room) {
        rooms.add(room);
    }

    public void remove(Room room){
            rooms.remove(room);
    }

    public Optional<Room> get(String id) {
        return rooms.stream()
                .filter(room -> room.getId().equals(id))
                .findFirst();
    }

    public void unsubscribe(String destination, Principal userUnsubscribe) {
        messagingTemplate.convertAndSend(destination.substring(0, destination.length() - 1), new Message(userUnsubscribe.getName(),
                String.format("user %s unsubscribe", userUnsubscribe.getName())));
    }

    public void subscribe(String destination, Principal user) {
        System.out.println(String.format("Subscribe room: %s  user: %s", destination, user.getName()));
    }
}
