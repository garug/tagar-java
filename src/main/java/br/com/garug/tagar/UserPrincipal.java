package br.com.garug.tagar;

import java.security.Principal;
import java.util.UUID;

public class UserPrincipal implements Principal {

    private UUID id;

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

    @Override
    public String getName() {
        return id.toString();
    }
}
