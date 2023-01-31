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

        JLabel soundsText = new JLabel("Sounds");
        soundsText.setFont(new Font("Tahoma", Font.PLAIN, 15));
        soundsText.setBounds(80, 75, 100, 30);
        soundsText.setForeground(Color.black);
        add(soundsText);
        JButton soundsOnBtn = new JButton("On");
        soundsOnBtn.setBounds(270, 75, 100, 30);
        buttonsStyle(soundsOnBtn);
        JButton soundsOffBtn = new JButton("Off");
        soundsOffBtn.setBounds(370, 75, 100, 30);
        buttonsStyle(soundsOffBtn);

        JLabel musicText = new JLabel("Music");
        musicText.setFont(new Font("Tahoma", Font.PLAIN, 15));
        musicText.setBounds(80, 145, 100, 30);
        musicText.setForeground(Color.black);
        add(musicText);
        JButton musicOnBtn = new JButton("On");
        musicOnBtn.setBounds(270, 145, 100, 30);
        buttonsStyle(musicOnBtn);
        JButton musicOffBtn = new JButton("Off");
        musicOffBtn.setBounds(370, 145, 100, 30);
        buttonsStyle(musicOffBtn);

        JLabel rotateText = new JLabel("Turn (which way)");
        rotateText.setFont(new Font("Tahoma", Font.PLAIN, 15));
        rotateText.setBounds(80, 215, 150, 30);
        rotateText.setForeground(Color.black);
        add(rotateText);
        JButton rotateOnBtn = new JButton("Left");
        rotateOnBtn.setBounds(270, 215, 100, 30);
        buttonsStyle(rotateOnBtn);
        JButton rotateOffBtn = new JButton("Right");
        rotateOffBtn.setBounds(370, 215, 100, 30);
        buttonsStyle(rotateOffBtn);

        JButton applyBtn = new JButton("Apply Changes");
        applyBtn.setBounds(80, 375, 150, 30);
        buttonsStyle(applyBtn);

        JButton backBtn = new JButton("Back To Menu");
        backBtn.setBounds(320, 375, 150, 30);
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
