package main_code;

import javax.swing.*;
import java.awt.*;

public class Settings extends JFrame {
    public Settings() {
        initUI();
    }

    public final void initUI() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        JButton applyBtn = new JButton("Apply changes");
        JButton backBtn = new JButton("Back to Menu");
        applyBtn.setBounds(162, 200, 225, 75);
        backBtn.setBounds(162, 285, 225, 75);
        getContentPane().setLayout(null);
        getContentPane().add(applyBtn);
        getContentPane().add(backBtn);

        applyBtn.addActionListener(e -> {
            System.out.println("All working");
            System.out.println("All working");
        });
        backBtn.addActionListener(e -> {
            this.dispose();
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
        });

        JLabel bottomText = new JLabel("Made by Ivanovichev Ivan");
        bottomText.setFont(new Font("Times New Romans", Font.PLAIN, 10));
        getContentPane().add(bottomText);
        bottomText.setBounds(215, 440, 200, 50);

        setTitle("Settings");
        setResizable(false);
        setSize(550, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
