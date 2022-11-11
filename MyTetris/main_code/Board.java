package main_code;

import main_code.Shape.ShapeList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel {
    private final int BOARD_WIDTH = 10; //12
    private final int BOARD_HEIGHT = 20; //24
    private Timer timer;
    private boolean isFallingFinished = false;
    private boolean isPaused = false;
    private int numLinesRemoved = 0;
    private int scoreCounter = 0;
    private int curX = 0;
    private int curY = 0;
    private JLabel gameStatus;
    private JLabel score;
    private JLabel line;
    private Shape curPiece;
    private ShapeList[] board;

    public Board(Tetris parent) {
        initBoard(parent);
    }

    private void initBoard(Tetris parent) {
        setFocusable(true);
        gameStatus = parent.getStatusBar();
        score = parent.getScore();
        line = parent.getLine();
        addKeyListener(new TAdapter());
    }

    private int squareWidth() {
        return 300 / BOARD_WIDTH;
    }

    private int squareHeight() {
        return 600 / BOARD_HEIGHT;
    }

    private ShapeList shapeAt(int x, int y) {
        return board[(y * BOARD_WIDTH) + x];
    }

    void start() {
        curPiece = new Shape();
        board = new ShapeList[BOARD_WIDTH * BOARD_HEIGHT];

        clearBoard();
        newPiece();

        int gameSpeed = 300;
        timer = new Timer(gameSpeed, new GameCycle());
        timer.start();
    }

    private void pause() {
        isPaused = !isPaused;

        if (isPaused) {
            gameStatus.setText("Game on pause");
        } else {
            gameStatus.setText("");
            line.setText(String.valueOf(numLinesRemoved));
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ShapeDrawing(g);
    }

    private void ShapeDrawing(Graphics g) {
        var size = getSize();
        int boardTop = (int) size.getHeight() - BOARD_HEIGHT * squareHeight();

        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                ShapeList shape = shapeAt(j, BOARD_HEIGHT - i - 1);

                if (shape != ShapeList.NoShape) {
                    drawSquare(g, j * squareWidth(), boardTop + i * squareHeight(), shape);
                }
            }
        }

        if (curPiece.getShape() != ShapeList.NoShape) {
            for (int i = 0; i < 4; i++) {

                int x = curX + curPiece.x(i);
                int y = curY - curPiece.y(i);

                drawSquare(g, x * squareWidth(), boardTop + (BOARD_HEIGHT - y - 1) * squareHeight(), curPiece.getShape());
            }
        }
    }

    private void dropDown() {
        int newY = curY;

        while (newY > 0) {
            if (!tryMove(curPiece, curX, newY - 1)) {
                break;
            }
            newY--;
        }
        pieceDropped();
    }

    private void oneLineDown() {
        if (!tryMove(curPiece, curX, curY - 1)) {
            pieceDropped();
        }
    }

    private void clearBoard() {
        for (int i = 0; i < BOARD_HEIGHT * BOARD_WIDTH; i++) {
            board[i] = ShapeList.NoShape;
        }
    }

    private void pieceDropped() {
        for (int i = 0; i < 4; i++) {
            int x = curX + curPiece.x(i);
            int y = curY - curPiece.y(i);
            board[(y * BOARD_WIDTH) + x] = curPiece.getShape();
        }

        removeFullLines();

        if (!isFallingFinished) {
            newPiece();
        }
    }

    private void newPiece() {
        curPiece.setRandomShape();
        curX = BOARD_WIDTH / 2 + 1;
        curY = BOARD_HEIGHT - 1 + curPiece.minY();

        if (!tryMove(curPiece, curX, curY)) {
            curPiece.setShape(ShapeList.NoShape);
            timer.stop();

            var msg = String.format("Game over.\nScore: %d", scoreCounter);
            gameStatus.setText(msg);
        }
    }

    private boolean tryMove(Shape newPiece, int newX, int newY) {
        for (int i = 0; i < 4; i++) {
            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);

            if (x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT) {
                return false;
            }

            if (shapeAt(x, y) != ShapeList.NoShape) {
                return false;
            }
        }

        curPiece = newPiece;
        curX = newX;
        curY = newY;

        repaint();

        return true;
    }

    private void removeFullLines() {
        int numFullLines = 0;
        for (int i = BOARD_HEIGHT - 1; i >= 0; i--) {
            boolean lineIsFull = true;

            for (int j = 0; j < BOARD_WIDTH; j++) {
                if (shapeAt(j, i) == ShapeList.NoShape) {
                    lineIsFull = false;
                    break;
                }
            }

            if (lineIsFull) {
                numFullLines++;
                for (int k = i; k < BOARD_HEIGHT - 1; k++) {
                    for (int j = 0; j < BOARD_WIDTH; j++) {
                        board[(k * BOARD_WIDTH) + j] = shapeAt(j, k + 1);
                    }
                }
            }
        }

        if (numFullLines > 0) {
            if (numFullLines == 1) {
                scoreCounter += 100;
                score.setText(String.valueOf(scoreCounter));
            } else if (numFullLines == 2) {
                scoreCounter += 300;
                score.setText(String.valueOf(scoreCounter));
            } else if (numFullLines == 3) {
                scoreCounter += 700;
                score.setText(String.valueOf(scoreCounter));
            } else if (numFullLines == 4) {
                scoreCounter += 1500;
                score.setText(String.valueOf(scoreCounter));
            }

            numLinesRemoved += numFullLines;
            line.setText(String.valueOf(numLinesRemoved));
            isFallingFinished = true;
            curPiece.setShape(ShapeList.NoShape);
        }
    }

    private void drawSquare(Graphics g, int x, int y, ShapeList shape) {
        Color[] colors = {
                new Color(0, 0, 0),
                new Color(204, 102, 102),
                new Color(102, 204, 102),
                new Color(102, 102, 204),
                new Color(204, 204, 102),
                new Color(204, 102, 204),
                new Color(102, 204, 204),
                new Color(218, 170, 0)
        };

        var color = colors[shape.ordinal()];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + 1);
    }

    private class GameCycle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            doGameCycle();
        }
    }

    private void doGameCycle() {
        updateGameStatus();
        repaint();
    }

    private void updateGameStatus() {
        if (isPaused) {
            return;
        }

        if (isFallingFinished) {
            isFallingFinished = false;
            newPiece();
        } else {
            oneLineDown();
        }
    }

    class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (curPiece.getShape() == ShapeList.NoShape) {
                return;
            }

            int keycode = e.getKeyCode();

            switch (keycode) {
                case KeyEvent.VK_ESCAPE -> pause();
                case KeyEvent.VK_LEFT, KeyEvent.VK_A -> tryMove(curPiece, curX - 1, curY);
                case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> tryMove(curPiece, curX + 1, curY);
                case KeyEvent.VK_DOWN, KeyEvent.VK_S -> oneLineDown();
                case KeyEvent.VK_UP, KeyEvent.VK_W -> dropDown();
                case KeyEvent.VK_SPACE -> tryMove(curPiece.rotateRight(), curX, curY);
            }
        }
    }
}
