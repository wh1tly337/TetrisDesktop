package main_code;

import javax.swing.*;
import java.awt.*;

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

        getContentPane().setLayout(null);

        Board gameBoard = new Board(this);
        add(gameBoard);
        gameBoard.setLayout(null);
        gameBoard.start();
        gameBoard.setBounds(35, 35, 435, 601);
        gameBoard.setBackground(Color.GRAY);

        // this separator need only for macOS
//        JSeparator separator = new JSeparator();
//        separator.setOrientation(SwingConstants.VERTICAL);
//        separator.setBounds(295, -2, 100, 604);
//        separator.setBackground(Color.DARK_GRAY);
//        separator.setForeground(Color.DARK_GRAY);
//        gameBoard.add(separator);

        JLabel scoreText = new JLabel("Score:");
        scoreText.setFont(new Font("Tahoma", Font.PLAIN, 20));
        scoreText.setForeground(Color.black);
        gameBoard.add(scoreText);
        scoreText.setBounds(335, 121, 100, 100);

        JLabel lineText = new JLabel("Line:");
        lineText.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lineText.setForeground(Color.black);
        gameBoard.add(lineText);
        lineText.setBounds(335, 167, 100, 100);

        JLabel holdText = new JLabel("Holded Shape:");
        // this size need only for macOS
//        holdText.setFont(new Font("Tahoma", Font.PLAIN, 14));
        holdText.setFont(new Font("Tahoma", Font.PLAIN, 15));
        holdText.setForeground(Color.black);
        gameBoard.add(holdText);
        holdText.setBounds(335, 215, 100, 100);

        score.setFont(new Font("Tahoma", Font.PLAIN, 20));
        score.setForeground(Color.black);
        gameBoard.add(score);
        score.setBounds(393, 121, 100, 100);

        line.setFont(new Font("Tahoma", Font.PLAIN, 20));
        line.setForeground(Color.black);
        gameBoard.add(line);
        line.setBounds(393, 167, 100, 100);

        gameOnPauseText.setFont(new Font("Tahoma", Font.PLAIN, 20));
        gameOnPauseText.setForeground(Color.black);
        gameBoard.add(gameOnPauseText);
        gameOnPauseText.setBounds(77, 0, 195, 100);

        gameOverText.setFont(new Font("Tahoma", Font.PLAIN, 20));
        gameOverText.setForeground(Color.black);
        gameBoard.add(gameOverText);
        gameOverText.setBounds(97, 0, 195, 100);

        finalScore.setFont(new Font("Tahoma", Font.PLAIN, 20));
        finalScore.setForeground(Color.black);
        gameBoard.add(finalScore);
        finalScore.setBounds(97, 30, 195, 100);

        JButton restartBtn = new JButton("RESTART");
        restartBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        // this size only for macOS
//        restartBtn.setBounds(332, 492, 106, 40);
        restartBtn.setBounds(332, 472, 103, 40);
        buttonsStyle(restartBtn, gameBoard);

        JButton backToMenuBtn = new JButton("<html>BACK<p>TO<p>MENU</html>");
        backToMenuBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        // this size only for macOS
//        backToMenuBtn.setBounds(332, 560, 106, 40);
        backToMenuBtn.setBounds(332, 540, 103, 60);
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
        downRect.setBackground(Color.DARK_GRAY);
        downRect.setBounds(300, 416, 150, 200);
        gameBoard.add(downRect);
        JPanel middleRect = new JPanel();
        middleRect.setBackground(Color.DARK_GRAY);
        middleRect.setBounds(300, 133, 150, 150);
        gameBoard.add(middleRect);
        JPanel leftRect = new JPanel();
        leftRect.setBackground(Color.DARK_GRAY);
        leftRect.setBounds(300, 0, 35, 600);
        gameBoard.add(leftRect);

        setTitle("T E T R I S");
        getContentPane().setBackground(Color.DARK_GRAY);
        // this size only for macOS
//        setSize(505, 699);
        setSize(520, 699);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void buttonsStyle(JButton button, Board gameBoard) {
        button.setFocusable(false);
        button.setBackground(Color.gray);
        button.setOpaque(true);
        button.setBorderPainted(false);
        gameBoard.add(button);
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