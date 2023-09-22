package com.example.fit.repositories;

import jakarta.enterprise.context.RequestScoped;

import java.sql.Connection;

@RequestScoped
public class GrantAccessRepository {
    private Connection connection;
    public GrantAccessRepository(){
        connection = DatabaseRepository.getConnection();
    }

}
