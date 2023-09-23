package com.example.fit.entities;

public enum IsGrant {
        Enable(1),
        Disable(0),
        Delete(-1);


        private final int value;

    IsGrant(int value) {
            this.value = value;
        }

        public int getValue() {
           return value;

        }
public int getStatus(){
    return value;
}
}
