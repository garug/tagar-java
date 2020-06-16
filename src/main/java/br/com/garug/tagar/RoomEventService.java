package br.com.garug.tagar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class RoomEventService {

    @Autowired
    private RoomManager roomManager;

    public void disconnect() {
        // lógica de desconexão
    }

    public void unsubscribe(String subscriptionId, Principal userUnsubscribe) {
        var room = roomManager.get(subscriptionId.split("/")[2]);
        room.ifPresent(r -> {
            // TODO alterar para notificar sala sobre saída do usuário
            System.out.println(String.format("sala %s - usuário %s saiu.", r.getId(), userUnsubscribe.getName()));
            roomManager.remove(r);
        });
    }

}
