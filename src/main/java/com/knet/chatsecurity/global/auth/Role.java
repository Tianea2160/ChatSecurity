package com.knet.chatsecurity.global.auth;

public enum Role {
    USER("ROLE_USER");

    private String key;

    Role(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
