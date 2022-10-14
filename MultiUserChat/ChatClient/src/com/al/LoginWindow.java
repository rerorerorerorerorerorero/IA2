package com.al;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginWindow extends JFrame {
    private final ChatClient client;
    JTextField loginField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Login");
    JButton regBut = new JButton("Register");

    public LoginWindow() throws SQLException {
        super("Login");

        this.client = new ChatClient("localhost", 8818);
        client.connect();
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\andre\\Downloads\\icon.png");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrame frame = new JFrame();
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        Color color = new Color(255,215,0);
        loginField.setSize(100, 20);
        passwordField.setSize(100, 20);
        loginButton.setSize(60, 20);
        loginField.setBackground(new java.awt.Color(44, 0, 63));
        passwordField.setBackground(new java.awt.Color(44, 0, 63));
        loginButton.setBackground(new java.awt.Color(44, 0, 63));
        loginButton.setForeground(new java.awt.Color(255, 215, 0));
        loginField.setForeground(color);
        passwordField.setForeground(color);
        loginButton.setBorder(new CompoundBorder(new EmptyBorder(1,1,1,1), new LineBorder(color, 1)));
        loginField.setBorder(new CompoundBorder(new EmptyBorder(1,1,1,1), new LineBorder(color, 1)));
        passwordField.setBorder(new CompoundBorder(new EmptyBorder(1,1,1,1), new LineBorder(color, 1)));
        frame.setIconImage(icon);
        p.setBackground(new java.awt.Color(44, 0, 63));
        p.setSize(200,200);


        regBut.setSize(60,20);
        regBut.setBorder(new CompoundBorder(new EmptyBorder(1,1,1,1), new LineBorder(color, 1)));
        regBut.setBackground(new java.awt.Color(44, 0, 63));
        regBut.setForeground(new java.awt.Color(255, 215, 0));

        regBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                RegisterPage page = new RegisterPage();
                page.setVisible(true);
                JLabel register = new JLabel("register");
                page.getContentPane().add(register);
                page.setBackground(new java.awt.Color(44, 0, 63));
                page.setForeground(new java.awt.Color(255, 215, 0));
                Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\andre\\Downloads\\icon.png");
                page.setIconImage(icon);



            }
        });


        p.add(loginField);
        p.add(passwordField);
        p.add(loginButton);
        p.add(regBut);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doLogin();
            }
        });

        getContentPane().add(p, BorderLayout.CENTER);

        pack();

        setVisible(true);
    }

    private void doLogin() {
        String login = loginField.getText();
        String password = passwordField.getText();

        try {
            if (client.login(login, password)) {
                Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\andre\\Downloads\\icon.png");
                UserListPane userListPane = new UserListPane(client);
                JFrame frame = new JFrame("Classmates List");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 600);
                Color color = new Color(255,215,0);
                frame.setForeground(color);
                userListPane.setBackground(new java.awt.Color(44, 0, 63));
                frame.setIconImage(icon);
                frame.getContentPane().add(userListPane, BorderLayout.CENTER);
                frame.setVisible(true);

                setVisible(false);
            } else {

                JOptionPane.showMessageDialog(this, "Invalid login/password.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LoginWindow loginWin = null;
        try {
            loginWin = new LoginWindow();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\andre\\Downloads\\icon.png");

        loginWin.setIconImage(icon);
        loginWin.setVisible(true);
        loginWin.setSize(200, 200);
        loginWin.loginField.setSize(130, 20);
        loginWin.passwordField.setSize(130, 20);
        loginWin.loginButton.setSize(60, 20);
    }
}
