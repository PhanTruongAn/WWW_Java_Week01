package com.example.fit;

import com.example.fit.db.DBConnection;
import com.example.fit.entities.Account;
import com.example.fit.entities.GrantAccess;
import com.example.fit.entities.Log;
import com.example.fit.entities.Status;
import com.example.fit.repositories.AccountRepositories;
import com.example.fit.repositories.DatabaseRepository;
import com.example.fit.repositories.LogRepositories;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Test {

    public static void main(String[] args) {
       LogRepositories data = new LogRepositories();
//Account acc = data.findAccountById("teo");
//System.err.println(acc);
        //GetAll
//        List<GrantAccess> list = data.getDsAccount();
//        list.forEach(x->System.err.println(x));
        //Add
//        Account acc = new Account("an123","Phan Truong An","123","phanan45@gmail.com","021478563",1);
//        data.addAccount(acc);
        //Delete
//       data.delAccountById("an123");
        //Update
//        data.updateAccount(new Account("an123","Phan Truong An","123","phanan@gmail.com","021478563",1));
        //Find By UserName and Password
//        String acc = data.findAccByUserPass("an123","123");
//        System.err.println(acc);
        //GrandAccess
        List<Log> list = data.getAllLog();
        list.forEach(x->System.err.println(x));
    }
}
