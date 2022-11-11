package main_code;

import java.awt.*;
import javax.swing.*;

public class Tetris extends JFrame {
    private JLabel statusbar;
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

        statusbar = new JLabel(" 0");
        statusbar.setFont(new Font("Times New Romans", Font.PLAIN, 20));
        add(statusbar);
        statusbar.setBounds(370, 216, 100, 100);

        getContentPane().setLayout(null);

        var gameBoard = new Board(this);
        add(gameBoard);
        gameBoard.start();
        gameBoard.setBounds(35, 36, 300, 600);
        gameBoard.setBackground(Color.gray); // for game zone

        var nextShapeBoard = new Board(this);
        add(nextShapeBoard);
        nextShapeBoard.start();
        nextShapeBoard.setBounds(370, 36, 100, 100);
        nextShapeBoard.setBackground(Color.gray); // for next shape

//        board.setLayout(null);
//        ImageIcon img = new ImageIcon("/Users/user/IdeaProjects/TetrisMacOS/MyTetris/tetrisBackground.jpg");
//        JLabel background = new JLabel("", img, JLabel.CENTER);
//        background.setBounds(0, 0, 300, 600);
//        board.add(background);

        setTitle("T E T R I S");
        getContentPane().setBackground(Color.darkGray); //all window background
        setSize(505, 699);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    JLabel getStatusBar() {
        return statusbar;
    }

    JLabel getScore() {
        return score;
    }

    JLabel getLine() {
        return line;
    }
}