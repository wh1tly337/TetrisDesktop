package main_code;

import java.awt.*;
import javax.swing.*;

public class Tetris extends JFrame {
    private JLabel gameStatus;
    private JLabel score;
    private JLabel line;

    public Tetris() {
        initUI();
    }

    private void initUI() {
        JLabel scoreText = new JLabel("Score:");
        scoreText.setFont(new Font("Times New Romans", Font.PLAIN, 20));
        getContentPane().add(scoreText);
        scoreText.setBounds(370, 124, 100, 100);

        JLabel lineText = new JLabel("Line:");
        lineText.setFont(new Font("Times New Romans", Font.PLAIN, 20));
        getContentPane().add(lineText);
        lineText.setBounds(370, 170, 100, 100);

        score = new JLabel(" 0");
        score.setFont(new Font("Times New Romans", Font.PLAIN, 20));
        add(score);
        score.setBounds(440, 124, 100, 100);

        line = new JLabel(" 0");
        line.setFont(new Font("Times New Romans", Font.PLAIN, 20));
        add(line);
        line.setBounds(440, 170, 100, 100);

        gameStatus = new JLabel(" ");

        getContentPane().setLayout(null);

        Board gameBoard = new Board(this);
        add(gameBoard);
        gameBoard.start();
        gameBoard.setBounds(35, 36, 300, 600);
        gameBoard.setBackground(Color.gray);

        JLabel sbBoard = gameStatus;
        sbBoard.setFont(new Font("Times New Romans", Font.PLAIN, 20));
        gameBoard.add(sbBoard);
        sbBoard.setBounds(0, 0, 100, 100);

        var nextShapeBoard = new Board(this);
        add(nextShapeBoard);
        nextShapeBoard.start();
        nextShapeBoard.setBounds(370, 36, 100, 100);
        nextShapeBoard.setBackground(Color.gray);

        JButton backToMenuBtn = new JButton("Back to Menu");
        backToMenuBtn.setBounds(367, 600, 106, 40);
        JButton restartBtn = new JButton("Restart");
        restartBtn.setBounds(367, 532, 106, 40);
        getContentPane().setLayout(null);
        getContentPane().add(backToMenuBtn);
        getContentPane().add(restartBtn);

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

//        board.setLayout(null);
//        ImageIcon img = new ImageIcon("/Users/user/IdeaProjects/TetrisMacOS/MyTetris/tetrisBackground.jpg");
//        JLabel background = new JLabel("", img, JLabel.CENTER);
//        background.setBounds(0, 0, 300, 600);
//        board.add(background);

        setTitle("T E T R I S");
        getContentPane().setBackground(Color.darkGray);
        setSize(505, 699);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    JLabel getStatusBar() {
        return gameStatus;
    }

    JLabel getScore() {
        return score;
    }

    JLabel getLine() {
        return line;
    }
}