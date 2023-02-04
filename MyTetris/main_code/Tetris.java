package main_code;

import javax.swing.*;
import java.awt.*;

public class Tetris extends JFrame {
    private JLabel gameOverText, gameOnPauseText, maxRecordText, maxRecordValue;
    private JLabel score;
    private JLabel line;
    Color lightBlue = new Color(144, 173, 205);
    Color blue = new Color(73, 132, 174);

    public Tetris() {
        initUI();
    }

    private void initUI() {
        score = new JLabel(" 0");
        line = new JLabel(" 0");
        gameOnPauseText = new JLabel(" ");
        gameOverText = new JLabel(" ");
        maxRecordText = new JLabel(" ");
        maxRecordValue = new JLabel(" ");

        getContentPane().setLayout(null);

        Board gameBoard = new Board(this);
        add(gameBoard);
        gameBoard.setLayout(null);
        gameBoard.start();
        gameBoard.setBounds(35, 35, 500, 601);
        gameBoard.setBackground(blue);

        JLabel scoreText = new JLabel("Score:");
        scoreText.setFont(new Font("Arial", Font.BOLD, 20));
        scoreText.setForeground(Color.white);
        gameBoard.add(scoreText);
        scoreText.setBounds(335, 121, 100, 100);

        JLabel lineText = new JLabel("Line:");
        lineText.setFont(new Font("Arial", Font.BOLD, 20));
        lineText.setForeground(Color.white);
        gameBoard.add(lineText);
        lineText.setBounds(335, 167, 100, 100);

        JLabel holdText = new JLabel("Holded Shape:");
        holdText.setFont(new Font("Arial", Font.BOLD, 20));
        holdText.setForeground(Color.white);
        gameBoard.add(holdText);
        holdText.setBounds(335, 215, 150, 100);

        maxRecordText.setFont(new Font("Arial", Font.BOLD, 20));
        maxRecordText.setForeground(Color.white);
        gameBoard.add(maxRecordText);
        maxRecordText.setBounds(335, 395, 210, 100);

        maxRecordValue.setFont(new Font("Arial", Font.BOLD, 20));
        maxRecordValue.setForeground(Color.white);
        gameBoard.add(maxRecordValue);
        maxRecordValue.setBounds(335, 425, 210, 100);

        score.setFont(new Font("Arial", Font.BOLD, 20));
        score.setForeground(Color.white);
        gameBoard.add(score);
        score.setBounds(403, 121, 200, 100);

        line.setFont(new Font("Arial", Font.BOLD, 20));
        line.setForeground(Color.white);
        gameBoard.add(line);
        line.setBounds(403, 167, 100, 100);

        gameOnPauseText.setFont(new Font("Arial", Font.BOLD, 40));
        gameOnPauseText.setForeground(Color.white);
        gameBoard.add(gameOnPauseText);
        gameOnPauseText.setBounds(2, 250, 300, 100);

        gameOverText.setFont(new Font("Arial", Font.BOLD, 40));
        gameOverText.setForeground(Color.white);
        gameBoard.add(gameOverText);
        gameOverText.setBounds(47, 250, 300, 100);

        JButton restartBtn = new JButton("RESTART");
        restartBtn.setBounds(332, 504, 168, 40);
        buttonsStyle(restartBtn, gameBoard);

        JButton backToMenuBtn = new JButton("BACK TO MENU");
        backToMenuBtn.setBounds(332, 560, 168, 40);
        buttonsStyle(backToMenuBtn, gameBoard);

        backToMenuBtn.addActionListener(e -> {
            try {
                Board.music.stop();
            } catch (Exception ignored) {
            }
            Timer timer = Board.getTimer();
            timer.stop();
            this.dispose();
            MainMenu menu = new MainMenu();
            menu.setVisible(true);
        });
        restartBtn.addActionListener(e -> {
            try {
                Board.music.stop();
            } catch (Exception ignored) {
            }
            Timer timer = Board.getTimer();
            timer.stop();
            this.dispose();
            Tetris tetris = new Tetris();
            tetris.setVisible(true);
        });

        JPanel downRect = new JPanel();
        downRect.setBackground(lightBlue);
        downRect.setBounds(300, 416, 200, 200);
        gameBoard.add(downRect);
        JPanel middleRect = new JPanel();
        middleRect.setBackground(lightBlue);
        middleRect.setBounds(300, 133, 200, 150);
        gameBoard.add(middleRect);
        JPanel leftRect = new JPanel();
        leftRect.setBackground(lightBlue);
        leftRect.setBounds(300, 0, 35, 600);
        gameBoard.add(leftRect);

        setTitle("T E T R I S");
        getContentPane().setBackground(lightBlue);
        setSize(584, 699);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void buttonsStyle(JButton button, Board gameBoard) {
        button.setFont(new Font("Roboto", Font.BOLD, 15));
        button.setForeground(Color.white);
        button.setFocusable(false);
        button.setBackground(blue);
        button.setOpaque(true);
        button.setBorderPainted(true);
        gameBoard.add(button);
    }

    JLabel getGameOverText() {
        return gameOverText;
    }

    JLabel getGameOnPauseText() {
        return gameOnPauseText;
    }

    JLabel getMaxRecordText() {
        return maxRecordText;
    }

    JLabel getMaxRecordValue() {
        return maxRecordValue;
    }

    JLabel getScore() {
        return score;
    }

    JLabel getLine() {
        return line;
    }
}