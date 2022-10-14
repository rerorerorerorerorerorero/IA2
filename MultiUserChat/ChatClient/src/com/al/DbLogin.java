package com.al;

import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbLogin {
    final String url = "jdbc:postgresql://localhost/postgres";
    final String user = "postgres";
    final String password = "Andr2004";

    PreparedStatement ps;
    ResultSet rs;
    String uname;
    String pass;

    String query = "SELECT * FROM students WHERE username =? AND pass =?";

    try(Connection conn = DriverManager.getConnection(url, user, password))
    private final ChatClient client;

    {
        ps = conn.prepareStatement(query);
        ps.setString(1, uname);
        ps.setString(2, pass);

        rs = ps.executeQuery();

        if(rs.next())
        {
            dispose();
            UserListPane page = new UserListPane(client);
            page.setVisible(true);
            JLabel welcomeLabel = new JLabel("Welcome:" + uname);
            page.getContentPane().add(welcomeLabel);
            PassedUser = uname;
        }
        else{
            JOptionPane.showMessageDialog(null, "Incorrect Username Or Password", "Login Failed", 2);
        }

    } catch (
    SQLException ex) {
        Logger.getLogger(LoginWindow.class.getName()).log(Level.SEVERE, null, ex);
    }
}