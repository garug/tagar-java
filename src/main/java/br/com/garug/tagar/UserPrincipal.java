package br.com.garug.tagar;

import org.springframework.messaging.simp.user.SimpSubscription;

import java.security.Principal;
import java.util.*;

public class UserPrincipal implements Principal {

    private UUID id;

    private final Set<SimpSubscription> subscriptions = new HashSet<>();

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

    public void addSubscription(SimpSubscription subscription) {
        this.subscriptions.add(subscription);
    }

    public void removeSubscription(SimpSubscription subscription) {
        this.subscriptions.remove(subscription);
    }

    @Override
    public String getName() {
        return id.toString();
    }
}
