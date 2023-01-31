package main_code;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public MainMenu() {
        initUI();
    }

    public final void initUI() {
        JPanel panel = new JPanel();
        add(panel);
        ImageIcon logo = new ImageIcon("MyTetris\\TETRIS_logo.png");
        JLabel background = new JLabel("", logo, JLabel.CENTER);
        background.setBounds(0, 40, 550, 120);
        add(background);

        JButton startBtn = new JButton("START GAME"); // making button
        startBtn.setBounds(162, 200, 225, 75); // set button coords on board
        buttonsStyle(startBtn);

        JButton settingsBtn = new JButton("GAME SETTINGS");
        settingsBtn.setBounds(162, 285, 225, 75);
        buttonsStyle(settingsBtn);


        JButton closeBtn = new JButton("EXIT");
        closeBtn.setBounds(162, 370, 225, 75);
        buttonsStyle(closeBtn);


        startBtn.addActionListener(e -> {
            this.dispose();
            Tetris tetris = new Tetris();
            tetris.setVisible(true);
        });
        settingsBtn.addActionListener(e -> {
            this.dispose();
            Settings settings = new Settings();
            settings.setVisible(true);
        });
        closeBtn.addActionListener(e -> System.exit(0));

        setLayout(null); // need for right working
        JLabel bottomText = new JLabel("Made by Ivanovichev Ivan");
        bottomText.setFont(new Font("Times New Romans", Font.PLAIN, 10));
        bottomText.setBounds(215, 440, 200, 50);
        add(bottomText);

        setTitle("T E T R I S");
        setResizable(false);
        setSize(550, 500);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.darkGray); //all window background
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void buttonsStyle(JButton button) {
        button.setFont(new Font("Tahoma", Font.PLAIN, 15));
        button.setFocusable(false);
        button.setBorderPainted(false);
        button.setBackground(Color.gray);
        button.setOpaque(true);
        button.setBorderPainted(true);
        add(button);
    }

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.setVisible(true);
    }
}

