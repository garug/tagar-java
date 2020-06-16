package br.com.garug.tagar;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomManager {

    private final List<Room> rooms = new ArrayList<>();

    public void add(Room room) {
        this.rooms.add(room);
    }

    public void remove(Room room){
        this.rooms.remove(room);
    }

    public Optional<Room> get(String id) {
        return this.rooms.stream()
                .filter(room -> room.getId().equals(id))
                .findFirst();
    }
}
