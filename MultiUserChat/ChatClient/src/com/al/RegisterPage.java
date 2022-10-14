package com.al;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterPage extends JFrame {
    String UserEmail, UserName, UserSurname, UserPass, UserUsername, UserPassCheck;


    RegisterPage() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Register Page");
        setSize(600, 400);
        Frame f = new Frame();
        JButton regBut;
        final JTextField email, username, name, surname, pass, passCheck;
        JPanel panel = new JPanel();
        Color back = new java.awt.Color(44, 0, 63);
        Color text = new java.awt.Color(255, 215, 0);
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\andre\\Downloads\\icon.png");
        f.setIconImage(icon);
        f.setBackground(back);
        regBut = new JButton("Register");

        email = new JTextField();
        email.setText("Enter email");
        email.addActionListener(e -> UserEmail = email.getText());
        email.setForeground(text);
        email.setBackground(back);
        email.setBorder(new CompoundBorder(new EmptyBorder(1,1,1,1), new LineBorder(text, 1)));

        username = new JTextField();
        username.setText("Enter username");
        username.setForeground(text);
        username.setBackground(back);
        username.addActionListener(e -> UserUsername = username.getText());
        username.setBorder(new CompoundBorder(new EmptyBorder(1,1,1,1), new LineBorder(text, 1)));


        name = new JTextField();
        name.setText("Enter name");
        name.addActionListener(e -> UserName = name.getText());
        name.setForeground(text);
        name.setBackground(back);
        name.setBorder(new CompoundBorder(new EmptyBorder(1,1,1,1), new LineBorder(text, 1)));

        surname = new JTextField();
        surname.setText("Enter surname");
        surname.addActionListener(e -> UserSurname = surname.getText());
        surname.setForeground(text);
        surname.setBackground(back);
        surname.setBorder(new CompoundBorder(new EmptyBorder(1,1,1,1), new LineBorder(text, 1)));

        pass = new JTextField();
        pass.setText("Enter password");
        pass.setForeground(text);
        pass.setBackground(back);
        pass.addActionListener(e -> UserPass = pass.getText());
        pass.setBorder(new CompoundBorder(new EmptyBorder(1,1,1,1), new LineBorder(text, 1)));

        passCheck = new JTextField();
        passCheck.setText("Repeat password");
        passCheck.addActionListener(e -> UserPassCheck = passCheck.getText());
        passCheck.setForeground(text);
        passCheck.setBackground(back);
        passCheck.setBorder(new CompoundBorder(new EmptyBorder(1,1,1,1), new LineBorder(text, 1)));

        regBut.setForeground(text);
        regBut.setBackground(back);
        regBut.setBorder(new CompoundBorder(new EmptyBorder(1,1,1,1), new LineBorder(text, 1)));

        panel = new JPanel((new GridLayout(8,1)));
        panel.add(name);
        panel.add(surname);
        panel.add(username);
        panel.add(email);
        panel.add(pass);
        panel.add(passCheck);
        panel.add(regBut);
        panel.setBackground(back);
        JTextArea area = new JTextArea("Registed succesfully");
        area.setEditable(false);
        panel.add(area);
        area.setVisible(false);
        JTextArea areaFalse = new JTextArea("Passwords dont match");
        areaFalse.setEditable(false);
        panel.add(areaFalse);
        areaFalse.setVisible(false);

        add(panel, BorderLayout.CENTER);
        setTitle("Register form");

        regBut.addActionListener(e -> {
            if (UserPass.equals(UserPassCheck)){
                area.setVisible(true);
                final String url = "jdbc:postgresql://localhost/postgres";
                final String user = "postgres";
                final String password = "Andr2004";
                try(Connection conn = DriverManager.getConnection(url, user, password);
                    Statement stmt = conn.createStatement();) {
                    String sql = "INSERT INTO students VALUES(nextval('id_student') ,'"+UserName+"', '"+UserSurname+"', '"+UserUsername+"', '"+UserPass+"')";
                    stmt.executeUpdate(sql);
                    dispose();
                } catch (SQLException xe) {
                    xe.printStackTrace();
                } ;
            }else {
                areaFalse.setVisible(true);
            }
        });

    }
}
