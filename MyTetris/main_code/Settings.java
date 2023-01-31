package main_code;

import javax.swing.*;
import java.awt.*;

public class Settings extends JFrame {
    public Settings() {
        initUI();
    }

    public final void initUI() {
        JPanel panel = new JPanel();
        add(panel);

        JButton applyBtn = new JButton("Apply changes");
        applyBtn.setBounds(162, 200, 225, 75);
        buttonsStyle(applyBtn);

        JButton backBtn = new JButton("Back to Menu");
        backBtn.setBounds(162, 285, 225, 75);
        buttonsStyle(backBtn);

        applyBtn.addActionListener(e -> {
            System.out.println("All working");
            System.out.println("All working");
        });
        backBtn.addActionListener(e -> {
            this.dispose();
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
        });

        setLayout(null);
        JLabel bottomText = new JLabel("Made by Ivanovichev Ivan");
        bottomText.setFont(new Font("Tahoma", Font.PLAIN, 10));
        bottomText.setBounds(215, 440, 200, 50);
        bottomText.setForeground(Color.black);
        add(bottomText);

        setTitle("SETTINGS");
        setResizable(false);
        setSize(550, 511);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.darkGray); //all window background
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void buttonsStyle(JButton button) {
        button.setFocusable(false);
        button.setBorderPainted(false);
        button.setBackground(Color.gray);
        button.setOpaque(true);
        button.setBorderPainted(true);
        add(button);
    }
}
