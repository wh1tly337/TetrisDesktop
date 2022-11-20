package main_code;

import java.awt.*;
import javax.swing.*;

public class Tetris extends JFrame {
    private JLabel isGameOver, isGameOnPause;
    private JLabel score;
    private JLabel finalScore;
    private JLabel line;

    public Tetris() {
        initUI();
    }

    private void initUI() {
        score = new JLabel(" 0");
        line = new JLabel(" 0");
        isGameOnPause = new JLabel(" ");
        isGameOver = new JLabel(" ");
        finalScore = new JLabel(" ");

        getContentPane().setLayout(null);

        Board gameBoard = new Board(this);
        add(gameBoard);
        gameBoard.setLayout(null);
        gameBoard.start();
        gameBoard.setBounds(35, 36, 435, 600);
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
        score.setBounds(390, 124, 100, 100);

        line.setFont(new Font("Times New Romans", Font.PLAIN, 20));
        gameBoard.add(line);
        line.setBounds(390, 170, 100, 100);

        isGameOnPause.setFont(new Font("Times New Romans", Font.PLAIN, 20));
        gameBoard.add(isGameOnPause);
        isGameOnPause.setBounds(77, 0, 195, 100);

        isGameOver.setFont(new Font("Times New Romans", Font.PLAIN, 20));
        gameBoard.add(isGameOver);
        isGameOver.setBounds(97, 0, 195, 100);

        finalScore.setFont(new Font("Times New Romans", Font.PLAIN, 20));
        gameBoard.add(finalScore);
        finalScore.setBounds(97, 30, 195, 100);

        JButton holdBtn = new JButton("Hold Shape");
        holdBtn.setBounds(317, 382, 106, 40);
        JButton restartBtn = new JButton("Restart");
        restartBtn.setBounds(317, 492, 106, 40);
        JButton backToMenuBtn = new JButton("Back to Menu");
        backToMenuBtn.setBounds(317, 560, 106, 40);
        gameBoard.add(holdBtn);
        gameBoard.add(restartBtn);
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

    JLabel getIsGameOver() {
        return isGameOver;
    }

    JLabel getIsGameOnPause() {
        return isGameOnPause;
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