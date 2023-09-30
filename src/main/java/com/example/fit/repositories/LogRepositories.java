package com.example.fit.repositories;

import com.example.fit.db.DBConnection;
import com.example.fit.entities.Account;
import com.example.fit.entities.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogRepositories {
         private Connection connection;
         public LogRepositories(){
             connection = DatabaseRepository.getConnection();
         }


         public List<Log> getAllLog(){
             List<Log> list = new ArrayList<>();
             try {
                 PreparedStatement statement = connection.prepareStatement("select * from mydb.log");
                 ResultSet rs = statement.executeQuery();
                 while (rs.next()) {
                     int Id = rs.getInt(1); // Replace "log_id" with the actual column name
                     String accountLog = rs.getString(2); // Replace "account_log" with the actual column name
                     LocalDateTime timeIn = rs.getTimestamp(3).toLocalDateTime(); // Replace "time_in" with the actual column name
                     LocalDateTime timeOut = rs.getTimestamp(4).toLocalDateTime(); // Replace "time_out" with the actual column name
                     String note = rs.getString(5); // Replace "additional_info" with the actual column name

                     Log log = new Log(Id, accountLog, timeIn, timeOut, note);
                     list.add(log);
                 }
             }catch (Exception e){
                 throw new RuntimeException(e);
             }

             return list;
         }
}
