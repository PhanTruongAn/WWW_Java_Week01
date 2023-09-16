package com.example.fit.services;

import com.example.fit.entities.Account;
import com.example.fit.entities.GrantAccess;
import com.example.fit.repositories.AccountRepositories;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.List;
import jakarta.ws.rs.PathParam;

@RequestScoped
public class AccountServices {
    @Inject
    private AccountRepositories repository;
    public List<Account> getAll(){
        return repository.getAll();
    }
    public Account getAccount(String name, String password){
        return repository.getAccount(name, password);
    }
    public GrantAccess getAccountRole(String username, String password) {
        return repository.getAccountRole(username, password);
    }
    public List<GrantAccess> getDsAccountRole() {
        return repository.getDsAccountRole();
    }
}
