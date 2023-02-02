package main_code;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class Settings extends JFrame {
    Color green = new Color(93, 187, 99);
    ArrayList<String> values = fileReader();
    boolean sounds = Boolean.parseBoolean(Objects.requireNonNull(values).get(0));
    boolean music = Boolean.parseBoolean(values.get(1));
    String turn = values.get(2);

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
        if (sounds) {
            soundsOnBtn.setBackground(green);
        } else {
            soundsOffBtn.setBackground(green);
        }
        soundsOnBtn.addActionListener(e -> {
            sounds = true;
            soundsOffBtn.setBackground(Color.gray);
            soundsOnBtn.setBackground(green);
        });
        soundsOffBtn.addActionListener(e -> {
            sounds = false;
            soundsOnBtn.setBackground(Color.gray);
            soundsOffBtn.setBackground(green);
        });

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
        if (music) {
            musicOnBtn.setBackground(green);
        } else {
            musicOffBtn.setBackground(green);
        }
        musicOnBtn.addActionListener(e -> {
            music = true;
            musicOffBtn.setBackground(Color.gray);
            musicOnBtn.setBackground(green);
        });
        musicOffBtn.addActionListener(e -> {
            music = false;
            musicOnBtn.setBackground(Color.gray);
            musicOffBtn.setBackground(green);
        });

        JLabel rotateText = new JLabel("Turn (which way)");
        rotateText.setFont(new Font("Tahoma", Font.PLAIN, 15));
        rotateText.setBounds(80, 215, 150, 30);
        rotateText.setForeground(Color.black);
        add(rotateText);

        JButton rotateLeftBtn = new JButton("Left");
        rotateLeftBtn.setBounds(270, 215, 100, 30);
        buttonsStyle(rotateLeftBtn);
        JButton rotateRightBtn = new JButton("Right");
        rotateRightBtn.setBounds(370, 215, 100, 30);
        buttonsStyle(rotateRightBtn);
        if (Objects.equals(turn, "right")) {
            rotateRightBtn.setBackground(green);
        } else {
            rotateLeftBtn.setBackground(green);
        }
        rotateRightBtn.addActionListener(e -> {
            turn = "right";
            rotateLeftBtn.setBackground(Color.gray);
            rotateRightBtn.setBackground(green);
        });
        rotateLeftBtn.addActionListener(e -> {
            turn = "left";
            rotateRightBtn.setBackground(Color.gray);
            rotateLeftBtn.setBackground(green);
        });

        JButton applyBtn = new JButton("Apply Changes");
        applyBtn.setBounds(80, 375, 150, 30);
        buttonsStyle(applyBtn);
        applyBtn.addActionListener(e -> fileWriter());

        JButton backBtn = new JButton("Back To Menu");
        backBtn.setBounds(320, 375, 150, 30);
        buttonsStyle(backBtn);
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

    private void buttonsStyle(JButton button) {
        button.setFocusable(false);
        button.setBorderPainted(false); // обводка вокруг кнопки
        button.setBackground(Color.gray);
        button.setOpaque(true);
        add(button);
    }

    public static ArrayList<String> fileReader() {
        BufferedReader reader;

        try {
//            reader = new BufferedReader(new FileReader("MyTetris\\settings.txt"));
            reader = new BufferedReader(new FileReader("/Users/user/IdeaProjects/TetrisMacOS/MyTetris/settings.txt"));
            String string = reader.readLine();
            ArrayList<String> values = new ArrayList<>();

            while (string != null) {
                String[] line = string.split(" ");
                values.add(line[1]);
                string = reader.readLine();
            }

            reader.close();

            return values;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void fileWriter() {
        try {
//            FileWriter writer = new FileWriter("MyTetris\\settings.txt");
            FileWriter writer = new FileWriter("/Users/user/IdeaProjects/TetrisMacOS/MyTetris/settings.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write("sounds " + sounds);
            bufferedWriter.newLine();
            bufferedWriter.write("music " + music);
            bufferedWriter.newLine();
            bufferedWriter.write("rotate " + turn);

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
