package com.example.fit.repositories;

import com.example.fit.entities.Account;
import com.example.fit.entities.GrantAccess;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@RequestScoped
public class AccountRepositories {
    private DatabaseRepository data;
    public AccountRepositories(){
        data = new DatabaseRepository();
    }
    public List<Account> getAll(){
        return data.getAll();
}
    public Account getAccount(String name, String password){
        return data.getAccount(name,password);
    }
    public GrantAccess getAccountRole(String username, String password) {
        return data.getAccountRole(username, password);
    }

    public List<GrantAccess> getDsAccountRole() {
        return data.getDsAccountRole();
    }

}

