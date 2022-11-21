package main_code;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Tetris extends JFrame {
    private JLabel gameOverText, gameOnPauseText;
    private JLabel score, finalScore;
    private JLabel line;

    public Tetris() {
        initUI();
    }

    private void initUI() {
        score = new JLabel(" 0");
        line = new JLabel(" 0");
        gameOnPauseText = new JLabel(" ");
        gameOverText = new JLabel(" ");
        finalScore = new JLabel(" ");

//        addMusic();

        getContentPane().setLayout(null);

        Board gameBoard = new Board(this);
        add(gameBoard);
        gameBoard.setLayout(null);
        gameBoard.start();
        gameBoard.setBounds(35, 35, 435, 601);
        gameBoard.setBackground(Color.GRAY);

        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setBounds(295, -2, 100, 604);
        separator.setBackground(Color.DARK_GRAY);
        separator.setForeground(Color.DARK_GRAY);
        gameBoard.add(separator);

        JLabel scoreText = new JLabel("Score:");
        scoreText.setFont(new Font("Times New Romans", Font.PLAIN, 20));
        gameBoard.add(scoreText);
        scoreText.setBounds(320, 124, 100, 100);

        JLabel lineText = new JLabel("Line:");
        lineText.setFont(new Font("Times New Romans", Font.PLAIN, 20));
        gameBoard.add(lineText);
        lineText.setBounds(320, 170, 100, 100);

        JLabel holdText = new JLabel("Holded Shape:");
        holdText.setFont(new Font("Times New Romans", Font.PLAIN, 14));
        gameBoard.add(holdText);
        holdText.setBounds(320, 215, 100, 100);

        score.setFont(new Font("Times New Romans", Font.PLAIN, 20));
        gameBoard.add(score);
        score.setBounds(378, 124, 100, 100);

        line.setFont(new Font("Times New Romans", Font.PLAIN, 20));
        gameBoard.add(line);
        line.setBounds(366, 170, 100, 100);

        gameOnPauseText.setFont(new Font("Times New Romans", Font.PLAIN, 20));
        gameBoard.add(gameOnPauseText);
        gameOnPauseText.setBounds(77, 0, 195, 100);

        gameOverText.setFont(new Font("Times New Romans", Font.PLAIN, 20));
        gameBoard.add(gameOverText);
        gameOverText.setBounds(97, 0, 195, 100);

        finalScore.setFont(new Font("Times New Romans", Font.PLAIN, 20));
        gameBoard.add(finalScore);
        finalScore.setBounds(97, 30, 195, 100);

        JButton restartBtn = new JButton("Restart");
        restartBtn.setBounds(317, 492, 106, 40);
        gameBoard.add(restartBtn);

        JButton backToMenuBtn = new JButton("Back to Menu");
        backToMenuBtn.setBounds(317, 560, 106, 40);
        gameBoard.add(backToMenuBtn);

        backToMenuBtn.addActionListener(e -> {
            this.dispose();
            MainMenu settings = new MainMenu();
            settings.setVisible(true);
        });
        restartBtn.addActionListener(e -> {
            this.dispose();
            Tetris tetris = new Tetris();
            tetris.setVisible(true);
        });

        setTitle("T E T R I S");
        getContentPane().setBackground(Color.DARK_GRAY);
        setSize(505, 699);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void addMusic() {
        String soundName = "/Users/user/IdeaProjects/TetrisMacOS/MyTetris/TETRIS_music.wav";
        AudioInputStream audioInputStream;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
        } catch (UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }
        Clip clip;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            clip.open(audioInputStream);
        } catch (LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-30.0f);
        clip.loop(10000);
        clip.start();
    }

    JLabel getGameOverText() {
        return gameOverText;
    }

    JLabel getGameOnPauseText() {
        return gameOnPauseText;
    }

    JLabel getFinalScore() {
        return finalScore;
    }

    JLabel getScore() {
        return score;
    }

    JLabel getLine() {
        return line;
    }
}