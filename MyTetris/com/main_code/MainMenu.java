package com.main_code;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public MainMenu() {
        initUI();
    }

    public final void initUI() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        JButton startBtn = new JButton("Start Game");
        JButton settingsBtn = new JButton("Game Settings");
        JButton closeBtn = new JButton("Close The Game");
        startBtn.setBounds(162, 200, 225, 75);
        settingsBtn.setBounds(162, 285, 225, 75);
        closeBtn.setBounds(162, 370, 225, 75);
        getContentPane().setLayout(null);
        getContentPane().add(startBtn);
        getContentPane().add(settingsBtn);
        getContentPane().add(closeBtn);

        startBtn.addActionListener(e -> new Tetris());
        settingsBtn.addActionListener(e -> new Settings());
        closeBtn.addActionListener(e -> System.exit(0));

        JLabel bottomText = new JLabel("Made by Ivanovichev Ivan");
        bottomText.setFont(new Font("Times New Romans", Font.PLAIN, 10));
        getContentPane().add(bottomText);
        bottomText.setBounds(215, 440, 200, 50);

        setTitle("T E T R I S");
        setResizable(false);
        setSize(550, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.setVisible(true);
    }
}
