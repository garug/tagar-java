package br.com.garug.tagar;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserPrincipal implements Principal {

    private UUID id;

    private final List<String> chats = new ArrayList<>();

    public UserPrincipal(UUID id) {
        this.id = id;
    }

    public UserPrincipal() {
        this(UUID.randomUUID());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void addChat(String chat) {
        this.chats.add(chat);
    }

    public void removeChat(String chat) {
        this.chats.remove(chat);
    }

    @Override
    public String getName() {
        return id.toString();
    }
}
