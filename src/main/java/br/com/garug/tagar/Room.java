package br.com.garug.tagar;

import java.security.Principal;
import java.util.*;
import java.util.stream.Stream;

public class Room {

    private final String id;
    private final Set<Principal> users;

    public Room(String id) {
        this.id = id;
        this.users = new HashSet<>();
    }

    public Room(String id, Principal... users) {
        this(id);
        this.users.addAll(Arrays.asList(users));
    }

    public Room(Principal... users) {
        this(UUID.randomUUID().toString(), users);
    }

    public void addUser(Principal user) {
        this.users.add(user);
    }

    public String getId() {
        return id;
    }

    public Set<Principal> getUsers() {
        return users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
