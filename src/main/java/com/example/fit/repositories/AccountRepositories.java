package com.example.fit.repositories;

import com.example.fit.entities.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class AccountRepositories {
    private Connection connection;
    public AccountRepositories() {

        connection = DatabaseRepository.getConnection();
    }

    public List<Account> getAll() {
        List<Account> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * from mydb.account");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String statusValue = rs.getString(6);
                Status status = null;
                switch (statusValue) {
                    case "1":
                        status = Status.Active;
                        break;
                    case "0":
                        status = Status.Inactive;
                        break;
                    case "-1":
                        status = Status.Delete;
                        break;
                    default:

                        break;
                }
                Account account = new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), status);
                list.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public Account getAccount(String name, String password) {

        PreparedStatement statement = null;
        Account ac = null;
        try {
            statement = connection.prepareStatement("SELECT mydb.account.* , mydb.grant_access.note FROM mydb.account INNER JOIN mydb.grant_access ON account.account_id = grant_access.account_id  where account.account_id = ? and password = ?");
            statement.setString(1, name);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String statusValue = rs.getString(6);
                Status status = null;
                switch (statusValue) {
                    case "1":
                        status = Status.Active;
                        break;
                    case "0":
                        status = Status.Inactive;
                        break;
                    case "-1":
                        status = Status.Delete;
                        break;
                    default:

                        break;
                }
                ac = new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), status);
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
            statement = connection.prepareStatement("SELECT account.*,role.role_id,role.role_name, grant_access.is_grant " + "FROM mydb.grant_access INNER JOIN mydb.account ON grant_access.account_id = account.account_id \n" + "INNER JOIN mydb.role ON grant_access.role_id = role.role_id where account.account_id = ? and password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String statusValue = rs.getString(6);
                Status status = null;
                switch (statusValue) {
                    case "1":
                        status = Status.Active;
                        break;
                    case "0":
                        status = Status.Inactive;
                        break;
                    case "-1":
                        status = Status.Delete;
                        break;
                    default:

                        break;
                }
                String isGrantValue = rs.getString(9);
                IsGrant isGrant = null;
                switch (isGrantValue) {
                    case "1":
                        isGrant = IsGrant.Enable;
                        break;
                    case "0":
                        isGrant = IsGrant.Disable;
                        break;
                    default:

                        break;
                }
                grantAccess = new GrantAccess(new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), status), new Role(rs.getString(7), rs.getString(8)), isGrant);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return grantAccess;
    }

    public List<GrantAccess> getDsAccount() {

        PreparedStatement statement = null;
        List<GrantAccess> dsAccount = new ArrayList<>();
        String s = "admin";
        try {
            statement = connection.prepareStatement("SELECT mydb.account.*,mydb.role.role_id,mydb.role.role_name, mydb.grant_access.is_grant FROM mydb.grant_access INNER JOIN mydb.account ON grant_access.account_id = account.account_id \n" + "INNER JOIN mydb.role ON grant_access.role_id = role.role_id " +
                    "where grant_access.role_id !='" + s + "'");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String statusValue = rs.getString(6);
                Status status = null;
                switch (statusValue) {
                    case "1":
                        status = Status.Active;
                        break;
                    case "0":
                        status = Status.Inactive;
                        break;
                    case "-1":
                        status = Status.Delete;
                        break;
                    default:

                        break;
                }
                String isGrantValue = rs.getString(9);
                IsGrant isGrant = null;
                switch (isGrantValue) {
                    case "1":
                        isGrant = IsGrant.Enable;
                        break;
                    case "0":
                        isGrant = IsGrant.Disable;
                        break;
                    default:

                        break;
                }
                GrantAccess grantAccess = new GrantAccess(new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), status), new Role(rs.getString(7), rs.getString(8)),isGrant);
                dsAccount.add(grantAccess);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dsAccount;
    }

    public Account accountLogin(String name, String pass) {
        Account ac = null;
        try {
            // Truy vấn để tìm tài khoản dựa trên name và pass
            String selectQuery = "SELECT * FROM mydb.account WHERE account_id = ? AND password = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1, name);
            selectStatement.setString(2, pass);
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {
                String statusValue = rs.getString(6);
                Status status = null;
                switch (statusValue) {
                    case "1":
                        status = Status.Active;
                        break;
                    case "0":
                        status = Status.Inactive;
                        break;
                    case "-1":
                        status = Status.Delete;
                        break;
                    default:

                        break;
                }
                String updateQuery = "UPDATE mydb.account SET status = 1 WHERE account_id = ? AND password = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setString(1, name);
                updateStatement.setString(2, pass);
                updateStatement.executeUpdate();
                ac = new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), status);
                /////////
                LocalDateTime localDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String timeLogin = localDateTime.format(formatter);
                String insertLogTime = "INSERT INTO mydb.log(account_log,time_log) values(?,?)";
                PreparedStatement statementLog = connection.prepareStatement(insertLogTime);
                statementLog.setString(1,name);
                statementLog.setString(2,timeLogin);
                statementLog.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return ac;
    }

    public boolean accountLogout(String userName) {
        try {
            String updateQuery = "UPDATE mydb.account SET status = 0 WHERE account_id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, userName);
            updateStatement.executeUpdate();

            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timeLogout = localDateTime.format(formatter);
            String insertLogTime = "UPDATE mydb.log\n" +
                    "SET time_out = ?\n" +
                    "WHERE account_log = ? AND time_out IS NULL ";
            PreparedStatement statementLog = connection.prepareStatement(insertLogTime);
            statementLog.setString(1,timeLogout);
            statementLog.setString(2,userName);
            statementLog.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }
        return true;
    }

    public boolean addAccount(Account acc) {
        PreparedStatement statement = null;
        try {
            int status = 0;
            if (acc.getStatus() == Status.Active) {
                status=1;
            } else if (acc.getStatus() == Status.Inactive) {
                status= 0;
            } else if (acc.getStatus()  == Status.Delete) {
                status= -1;
            }
            statement = connection.prepareStatement("INSERT INTO mydb.account VALUES(?,?,?,?,?,?)");
            statement.setString(1, acc.getAccount_id());
            statement.setString(2, acc.getFull_name());
            statement.setString(3, acc.getPassword());
            statement.setString(4, acc.getEmail());
            statement.setString(5, acc.getPhone());
            statement.setInt(6, status);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (statement != null) {
            System.err.println("Add account success!!!!");

        } else {
            System.err.println("Add account false!!!!");
        }
        return true;
    }

    public boolean delAccountById(String id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("UPDATE mydb.account set status=-1 where account_id=?");
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (statement != null) {
            System.err.println("Delete account success!!!");
        } else {
            System.err.println("Delete account false!!!");
        }
        return true;
    }
    public Account findAccountById(String id) {
        Account acc = new Account();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM mydb.account WHERE account_id = ?");
            statement.setString(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String statusValue = rs.getString(6);
                Status status = null;
                switch (statusValue) {
                    case "1":
                        status = Status.Active;
                        break;
                    case "0":
                        status = Status.Inactive;
                        break;
                    case "-1":
                        status = Status.Delete;
                        break;
                    default:

                        break;
                }
                acc = new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), status);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return acc;
    }



    public boolean updateAccount(Account account) {
        PreparedStatement statement = null;

        try {
            int status = 0;
            if (account.getStatus() == Status.Active) {
                status=1;
            } else if (account.getStatus() == Status.Inactive) {
                status= 0;
            } else if (account.getStatus()  == Status.Delete) {
                status= -1;
            }
            statement = connection.prepareStatement("update mydb.account set full_name=?,password=?,email=?,phone=?,status=? where account_id=?");
            statement.setString(1, account.getFull_name());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getEmail());
            statement.setString(4, account.getPhone());
            statement.setInt(5, status);
            statement.setString(6, account.getAccount_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (statement != null) {
            System.err.println("Update account success!!!");
        } else {
            System.err.println("Update account false!!!");
        }
        return true;
    }
}

