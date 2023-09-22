package com.example.fit.services;

import com.example.fit.entities.Account;
import com.example.fit.entities.GrantAccess;
import com.example.fit.repositories.AccountRepositories;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.List;

@RequestScoped
public class AccountServices {
    @Inject
    private AccountRepositories repository;
    public AccountServices(){
        repository = new AccountRepositories();
    }

    public List<Account> getAll(){
        return repository.getAll();
    }
    public Account getAccount(String name, String password){
        return repository.getAccount(name, password);
    }
    public GrantAccess getAccountRole(String username, String password) {
        return repository.getAccountRole(username, password);
    }
    public List<GrantAccess> getDsAccount() {
        return repository.getDsAccount();
    }
    public Account accountLogin(String name, String pass){
        return repository.accountLogin(name,pass);
    }
    public boolean accountLogout(String userName) {
        return true;
    }
    public boolean addAccount(Account acc){
        return true;
    }
}
