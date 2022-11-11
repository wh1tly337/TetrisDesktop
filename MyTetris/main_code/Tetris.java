package main_code;

import java.awt.*;
import javax.swing.*;

public class Tetris extends JFrame {
    private JLabel statusbar;

    public Tetris() {
        initUI();
    }

    private void initUI() {
//        statusbar = new JLabel(" 0");
//        add(statusbar, BorderLayout.SOUTH); // old score
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
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    JLabel getStatusBar() {
        return statusbar;
    }
}