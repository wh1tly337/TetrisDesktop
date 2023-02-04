package main_code;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    Color blue = new Color(73, 132, 174);
    Color lightBlue = new Color(144, 173, 205);

    public MainMenu() {
        initUI();
    }

    public final void initUI() {
        JPanel panel = new JPanel();
        add(panel);

        ImageIcon logo = new ImageIcon("MyTetris\\TETRIS_logo.png");
//        ImageIcon logo = new ImageIcon("/Users/user/IdeaProjects/TetrisMacOS/MyTetris/TETRIS_logo.png");
        JLabel background = new JLabel("", logo, JLabel.CENTER);
        background.setBounds(0, 40, 530, 120);
        add(background);

        JButton startBtn = new JButton("START GAME");
        startBtn.setBounds(162, 200, 225, 75);
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
        bottomText.setFont(new Font("Arial", Font.PLAIN, 10));
        bottomText.setForeground(Color.black);
        bottomText.setBounds(215, 440, 200, 50);
        add(bottomText);

        setTitle("T E T R I S");
        setResizable(false);
        setSize(550, 511);
        setLocationRelativeTo(null);
        getContentPane().setBackground(lightBlue); //all window background
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void buttonsStyle(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 15));
        button.setForeground(Color.white);
        button.setFocusable(false);
        button.setBorderPainted(true);
        button.setBackground(blue);
        button.setOpaque(true);
        add(button);
    }

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.setVisible(true);
    }
}

