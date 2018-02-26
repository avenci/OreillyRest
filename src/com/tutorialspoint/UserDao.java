package com.tutorialspoint;

/**
 * Created by avenci on 2/24/18.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public List<User> getAllUsers(){
        addUser("adam", "stuff");

        Connection conn = null;
        List<User> userList = null;
        userList = new ArrayList<User>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn =
                DriverManager.getConnection("jdbc:mysql://127.0.0.1/OREILLY","root", "pass");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM USER;");

            while(rs.next()){
                int id  = rs.getInt("ID");
                String name = rs.getString("Name");
                String profession = rs.getString("Profession");

                User newUser = new User(id, name, profession);

                System.out.println(newUser.toString());
                userList.add(newUser);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void addUser(String name, String profession) {
        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn =
                DriverManager.getConnection("jdbc:mysql://127.0.0.1/OREILLY","root", "pass");

            Statement stmt = conn.createStatement();
            String qry = String.format("INSERT INTO USER (Name, Profession) VALUES ('%s','%s')",name,profession);
            stmt.execute(qry);
            stmt.close();
            conn.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}