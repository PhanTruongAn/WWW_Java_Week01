package com.example.fit.entities;

public enum Status {
    Active(1),
    Inactive(0),
    Delete(-1);

    private final int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
