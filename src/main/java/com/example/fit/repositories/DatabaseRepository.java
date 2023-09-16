package com.example.fit.repositories;


import com.example.fit.entities.Account;
import com.example.fit.entities.GrantAccess;
import com.example.fit.entities.Role;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseRepository {

    private Connection connection=null;

    public DatabaseRepository(){
        String url = "jdbc:mariadb://localhost:3306/mysql";
        try {
            Class.forName ("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(url, "root", "123");
            System.out.println(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e1){
            throw  new RuntimeException(e1);
        }
    }
    public List<Account> getAll(){
        List<Account> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * from mydb.account");
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()){

                Account account = new Account(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6)
                );
                list.add(account);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return list;
    }

    public void addAccount(Account acc){
        PreparedStatement statement = null;
        try {
            statement  = connection.prepareStatement("INSERT INTO mydb.account VALUES(?,?,?,?,?,?)");
            statement.setString(1, acc.getAccount_id());
            statement.setString(2,acc.getFull_name());
            statement.setString(3,acc.getPassword());
            statement.setString(4,acc.getEmail());
            statement.setString(5,acc.getPhone());
            statement.setInt(6,acc.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(statement !=null){
            System.err.println("Add account success!!!!");
        }else{
            System.err.println("Add account false!!!!");
        }
    }
    public void delAccountById(String id){
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("DELETE from mydb.account where account_id=?");
            statement.setString(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(statement != null){
            System.err.println("Delete account success!!!");
        }else{
            System.err.println("Delete account false!!!");
        }
    }
    public void updateAccount(Account account){
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("update mydb.account set full_name=?,password=?,email=?,phone=?,status=? where account_id=?");
            statement.setString(1,account.getFull_name());
            statement.setString(2,account.getPassword());
            statement.setString(3,account.getEmail());
            statement.setString(4,account.getPhone());
            statement.setInt(5,account.getStatus());
            statement.setString(6,account.getAccount_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(statement != null){
            System.err.println("Update account success!!!");
        }else{
            System.err.println("Update account false!!!");
        }
    }
//public String findAccByUserPass(String user,String pas) {
//
//    PreparedStatement statement = null;
//    String userName = null;
//    try {
//        statement = connection.prepareStatement("SELECT * FROM mydb.account where account_id=? and password=?");
//        statement.setString(1, user);
//        statement.setString(2, pas);
//        ResultSet rs = statement.executeQuery();
//        while (rs.next()) {
//            userName = rs.getString(1);
//
//        }
//
//    } catch (SQLException e) {
//        throw new RuntimeException(e);
//    }
//    return userName;
//}
public Account getAccount(String name, String password) {
    PreparedStatement statement = null;
    Account ac = null;
    try {
        statement = connection.prepareStatement("SELECT mydb.account.* , mydb.grant_access.note FROM mydb.account INNER JOIN mydb.grant_access ON account.account_id = grant_access.account_id" +
                " where account.account_id = ? and password = ?");
        statement.setString(1, name);
        statement.setString(2, password);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            ac = new Account(rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getInt(6));
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return ac;
}
    public GrantAccess getAccountRole(String username, String password) {
        PreparedStatement statement = null;
        GrantAccess grantAccess = null;
        try {
            statement = connection.prepareStatement("SELECT account.*,role.role_id,role.role_name, grant_access.is_grant " +
                    "FROM mydb.grant_access INNER JOIN mydb.account ON grant_access.account_id = account.account_id \n" +
                    "INNER JOIN mydb.role ON grant_access.role_id = role.role_id where account.account_id = ? and password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                grantAccess = new GrantAccess(new Account(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getInt(6)),
                        new Role(rs.getString(7), rs.getString(8)), rs.getInt(9)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return grantAccess;
    }

    public List<GrantAccess> getDsAccountRole() {
        PreparedStatement statement = null;
        List<GrantAccess> dsAccount = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT mydb.account.*,mydb.role.role_id,mydb.role.role_name, mydb.grant_access.is_grant FROM mydb.grant_access INNER JOIN mydb.account ON grant_access.account_id = account.account_id \n" +
                    "INNER JOIN mydb.role ON grant_access.role_id = role.role_id where account.account_id = ? and password = ?");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                GrantAccess grantAccess = new GrantAccess(new Account(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getInt(6)),
                        new Role(rs.getString(7), rs.getString(8)), rs.getInt(9)
                );
                dsAccount.add(grantAccess);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dsAccount;
    }
}