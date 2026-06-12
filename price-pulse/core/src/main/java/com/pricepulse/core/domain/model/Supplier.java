package com.pricepulse.core.domain.model;

import java.util.UUID;

public class Supplier {
    private final UUID id;
    private final String name;
    private final boolean active;

    public Supplier(UUID id, String name, boolean active) {
        this.id = id !=null ? id: UUID.randomUUID();
        this.name = name;
        this.active = active;
    }

    public boolean canProcessPrice(){
        return this.active;
    }
    public UUID getId() { return id; }
    public String getName() { return name; }
    public boolean isActive() { return active; }

}
