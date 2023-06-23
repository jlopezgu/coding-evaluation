package com.aa.act.interview.org;

import java.util.UUID;

public class Employee {

    private UUID identifier;
    private Name name;

    public Employee(Name name) {
        if(name == null)
            throw new IllegalArgumentException("name cannot be null");
        this.identifier = java.util.UUID.randomUUID();
        this.name = name;
    }
    
    public UUID getIdentifier() {
        return identifier;
    }
    
    public Name getName() {
        return name;
    }

    @Override
    public String toString() {
        return name.toString() + ": " + identifier;
    }
}
