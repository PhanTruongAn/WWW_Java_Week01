package com.example.fit.entities;

public enum IsGrant {
        ENABLE(1),
        DISABLE(0),
        DELETE (-1);


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
