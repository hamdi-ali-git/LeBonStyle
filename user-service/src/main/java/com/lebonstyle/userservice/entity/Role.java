package com.lebonstyle.userservice.entity;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("Administrator"),
    USER("Regular User"),
    GUEST("Guest User");

    private final String roleName;

    // Constructor to initialize the string value
    Role(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return roleName; // Optional, if you want to return the string value directly
    }
}

