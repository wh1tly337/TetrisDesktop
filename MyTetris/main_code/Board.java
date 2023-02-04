package main_code;

import main_code.Shape.ShapeList;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Board extends JPanel {
    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 20;
    public static Timer timer;
    private boolean isFallingFinished = false;
    private boolean isPaused = false;
    private boolean wasHoldChanged = false;
    private boolean isGameOver = false;
    private int gameSpeed = 370;
    private int level = 1;
    private int numLinesRemoved = 0;
    private int scoreCounter = 0;
    private int curX, curY = 0;
    private double nextX, nextY, holdedX, holdedY = 0;
    private JLabel gameOverText, gameOnPauseText, maxRecordText, maxRecordValue;
    private JLabel score;
    private JLabel line;
    private Shape curPiece, nextPiece, holdedPiece, memory;
    private ShapeList[] board;
    public static Clip clip, music;
    ArrayList<String> values = Settings.fileReader();
    boolean needSounds = Boolean.parseBoolean(Objects.requireNonNull(values).get(0));
    boolean needMusic = Boolean.parseBoolean(values.get(1));
    String turn = values.get(2);
    int maxRecord = Integer.parseInt(values.get(3));

    public Board(Tetris parent) {
        initBoard(parent);
    }

    public static Timer getTimer() {
        return timer;
    }

    private void initBoard(Tetris parent) {
        setFocusable(true);
        score = parent.getScore();
        line = parent.getLine();
        maxRecordText = parent.getMaxRecordText();
        maxRecordValue = parent.getMaxRecordValue();
        gameOnPauseText = parent.getGameOnPauseText();
        gameOverText = parent.getGameOverText();
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
        board = new ShapeList[BOARD_WIDTH * BOARD_HEIGHT];
        clearBoard();

        nextPiece();
        newPiece();

        holdedPiece = new Shape();
        holdedPiece.setShape(ShapeList.EmptyShape);

        memory = new Shape();
        memory.setShape(ShapeList.EmptyShape);

        addMusic();
        timer = new Timer(gameSpeed, new GameCycle());
        timer.start();
    }

    private void pause() {
        isPaused = !isPaused;

        if (isPaused) {
            gameOnPauseText.setText("Game on Pause");
        } else {
            gameOnPauseText.setText(" ");
            line.setText(String.valueOf(numLinesRemoved));
        }
        repaint();
    }

    private void finish() {
        isGameOver = true;

        try {
            music.stop();
        } catch (Exception ignored) {
        }
        addSounds("GameOver");

        curPiece.setShape(ShapeList.EmptyShape);
        nextPiece.setShape(ShapeList.EmptyShape);
        holdedPiece.setShape(ShapeList.EmptyShape);

        timer.stop();

        gameOverText.setText("Game Over");
        if (Integer.parseInt(score.getText()) + 10 > maxRecord) {
            maxRecord = Integer.parseInt(score.getText());
            maxRecordText.setText("New Max Record:");
            maxRecordValue.setText(String.valueOf(maxRecord + 10));
            Settings.fileWriter(needSounds, needMusic, turn, maxRecord + 10);
        } else {
            maxRecordText.setText("Max Record:");
            maxRecordValue.setText(String.valueOf(maxRecord));
        }
    }

    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    private void nextPiece() {
        nextPiece = new Shape();
        nextPiece.setRandomShape();

        switch (nextPiece.getShape()) {
            case ZShape -> {
                nextX = BOARD_WIDTH / 2 + 1.15;
                nextY = BOARD_HEIGHT - 1.35;
            }
            case SShape -> {
                nextX = BOARD_WIDTH / 2 + 0.25;
                nextY = BOARD_HEIGHT - 1.35;
            }
            case SquareShape -> {
                nextX = BOARD_WIDTH / 2 + 0.25;
                nextY = BOARD_HEIGHT - 0.9;
            }
            case LShape -> {
                nextX = BOARD_WIDTH / 2 + 1.1;
                nextY = BOARD_HEIGHT - 1.4;
            }
            case MirroredLShape -> {
                nextX = BOARD_WIDTH / 2 + 0.3;
                nextY = BOARD_HEIGHT - 1.4;
            }
            case TShape -> {
                nextX = BOARD_WIDTH / 2 + 0.75;
                nextY = BOARD_HEIGHT - 0.92;
            }
            default -> {
                nextX = BOARD_WIDTH / 2 + 0.75;
                nextY = BOARD_HEIGHT - 0.9;
            }
        }
    }

    private void newPiece() {
        curPiece = nextPiece;
        nextPiece();

        curX = BOARD_WIDTH / 2 - 1;
        curY = BOARD_HEIGHT - 1 + curPiece.minY();

        if (!tryToMove(curPiece, curX, curY)) {
            finish();
        }
    }

    private void holdPiece() {
        if (!wasHoldChanged) {
            if (holdedPiece.getShape() == ShapeList.EmptyShape) {
                holdedPiece = curPiece;
                holdedPieceCoords(holdedPiece);
                newPiece();
            } else {
                memory = holdedPiece;
                holdedPiece = curPiece;
                holdedPieceCoords(holdedPiece);
                curPiece = memory;
                curX = BOARD_WIDTH / 2 - 1;
                curY = BOARD_HEIGHT - 1 + curPiece.minY();
            }
            wasHoldChanged = true;
        }
    }

    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    private void holdedPieceCoords(Shape holdedPiece) {
        switch (holdedPiece.getShape()) {
            case ZShape -> {
                holdedX = BOARD_WIDTH / 2 + 1.15;
                holdedY = BOARD_HEIGHT - 1.8;
            }
            case SShape -> {
                holdedX = BOARD_WIDTH / 2 + 0.2;
                holdedY = BOARD_HEIGHT - 1.73;
            }
            case SquareShape -> {
                holdedX = BOARD_WIDTH / 2 + 0.27;
                holdedY = BOARD_HEIGHT - 1.29;
            }
            case LShape -> {
                holdedX = BOARD_WIDTH / 2 + 1.1;
                holdedY = BOARD_HEIGHT - 1.79;
            }
            case MirroredLShape -> {
                holdedX = BOARD_WIDTH / 2 + 0.3;
                holdedY = BOARD_HEIGHT - 1.88;
            }
            case TShape -> {
                holdedX = BOARD_WIDTH / 2 + 0.75;
                holdedY = BOARD_HEIGHT - 1.35;
            }
            default -> {
                holdedX = BOARD_WIDTH / 2 + 0.75;
                holdedY = BOARD_HEIGHT - 1.27;
            }
        }
    }

    private void dropShapeDown() {
        int newY = curY;
        if (!isGameOver) {
            while (newY > 0) {
                if (!tryToMove(curPiece, curX, newY - 1)) {
                    break;
                }
                newY--;
            }

            pieceDropped();

            scoreCounter += level * 10;
            score.setText(String.valueOf(scoreCounter));
        } else {
            finish();
        }
    }

    private void oneLineDown() {
        if (!isGameOver) {
            if (!tryToMove(curPiece, curX, curY - 1)) {
                pieceDropped();

                scoreCounter += level;
                score.setText(String.valueOf(scoreCounter));
            }
        } else {
            finish();
        }
    }

    private void clearBoard() {
        for (int i = 0; i < BOARD_HEIGHT * BOARD_WIDTH; i++) {
            board[i] = ShapeList.EmptyShape;
        }
    }

    private void pieceDropped() {
        if (!isGameOver) {
            for (int i = 0; i < 4; i++) {
                int x = curX + curPiece.x(i);
                int y = curY - curPiece.y(i);

                try {
                    board[(y * BOARD_WIDTH) + x] = curPiece.getShape();

                    scoreCounter += level * 5;
                    score.setText(String.valueOf(scoreCounter));
                } catch (ArrayIndexOutOfBoundsException e) {
                    finish();
                }
            }

            removeFullLines();
            wasHoldChanged = false;

            if (!isFallingFinished) {
                newPiece();
                addSounds("BrickDown");
            }
        } else {
            finish();
        }
    }

    private boolean tryToMove(Shape newPiece, int newX, int newY) {
        for (int i = 0; i < 4; i++) {
            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);

            if (x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT) {
                return false;
            }

            if (shapeAt(x, y) != ShapeList.EmptyShape) {
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
        if (!isGameOver) {
            int numFullLines = 0;

            for (int i = BOARD_HEIGHT - 1; i >= 0; i--) {
                boolean lineIsFull = true;

                for (int j = 0; j < BOARD_WIDTH; j++) {
                    if (shapeAt(j, i) == ShapeList.EmptyShape) {
                        lineIsFull = false;
                        break;
                    }
                }

                if (lineIsFull) {
                    addSounds("ClearLine");
                    numFullLines++;
                    for (int k = i; k < BOARD_HEIGHT - 1; k++) {
                        for (int j = 0; j < BOARD_WIDTH; j++) {
                            board[(k * BOARD_WIDTH) + j] = shapeAt(j, k + 1);
                        }
                    }
                }
            }

            if (numLinesRemoved % 10 == 0 && numLinesRemoved != 0) {
                if (gameSpeed > 100) {
                    gameSpeed -= 18;
                    level++;
                }
            }

            if (numFullLines > 0) {
                if (numFullLines == 1) {
                    scoreCounter += level * 40;
                    score.setText(String.valueOf(scoreCounter));
                } else if (numFullLines == 2) {
                    scoreCounter += level * 100;
                    score.setText(String.valueOf(scoreCounter));
                } else if (numFullLines == 3) {
                    scoreCounter += level * 300;
                    score.setText(String.valueOf(scoreCounter));
                } else if (numFullLines == 4) {
                    scoreCounter += level * 1200;
                    score.setText(String.valueOf(scoreCounter));
                }

                numLinesRemoved += numFullLines;
                line.setText(String.valueOf(numLinesRemoved));
                isFallingFinished = true;
                curPiece.setShape(ShapeList.EmptyShape);
            }
        } else {
            finish();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawShape(g);
    }

    private void drawShape(Graphics g) {
        var size = getSize();
        int boardTop = (int) size.getHeight() - BOARD_HEIGHT * squareHeight();

        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                ShapeList shape = shapeAt(j, BOARD_HEIGHT - i - 1);

                if (shape != ShapeList.EmptyShape) {
                    drawPiece(g, j * squareWidth(), boardTop + i * squareHeight(), shape);
                }
            }
        }

        if (curPiece.getShape() != ShapeList.EmptyShape) {
            for (int i = 0; i < 4; i++) {

                int x = curX + curPiece.x(i);
                int y = curY - curPiece.y(i);

                drawPiece(g, x * squareWidth(), boardTop + (BOARD_HEIGHT - y - 1) * squareHeight(), curPiece.getShape());
            }
        }

        if (nextPiece.getShape() != ShapeList.EmptyShape) {
            for (int i = 0; i < 4; i++) {

                double x = nextX + nextPiece.x(i);
                double y = nextY - nextPiece.y(i);

                drawPiece(g, x * squareWidth() + 230, boardTop + (BOARD_HEIGHT - y - 1) * squareHeight() + 40, nextPiece.getShape());
            }
        }

        if (holdedPiece.getShape() != ShapeList.EmptyShape) {
            for (int i = 0; i < 4; i++) {

                double x = holdedX + holdedPiece.x(i);
                double y = holdedY - holdedPiece.y(i);

                drawPiece(g, x * squareWidth() + 230, boardTop + (BOARD_HEIGHT - y - 1) * squareHeight() + 310, holdedPiece.getShape());
            }
        }
    }

    private void drawPiece(Graphics g, double x, double y, ShapeList shape) {
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
        g.fillRect((int) (x + 1), (int) (y + 1), squareWidth() - 2, squareHeight() - 2);

        g.setColor(color.brighter());
        g.drawLine((int) x, (int) (y + squareHeight() - 1), (int) x, (int) y);
        g.drawLine((int) x, (int) y, (int) (x + squareWidth() - 1), (int) y);

        g.setColor(color.darker());
        g.drawLine((int) (x + 1), (int) (y + squareHeight() - 1),
                (int) (x + squareWidth() - 1), (int) (y + squareHeight() - 1));
        g.drawLine((int) (x + squareWidth() - 1), (int) (y + squareHeight() - 1),
                (int) (x + squareWidth() - 1), (int) (y + 1));
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
            if (curPiece.getShape() == ShapeList.EmptyShape) {
                return;
            }

            int keycode = e.getKeyCode();

            switch (keycode) {
                case KeyEvent.VK_ESCAPE -> pause();
                case KeyEvent.VK_LEFT, KeyEvent.VK_A -> tryToMove(curPiece, curX - 1, curY);
                case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> tryToMove(curPiece, curX + 1, curY);
                case KeyEvent.VK_DOWN, KeyEvent.VK_S -> oneLineDown();
                case KeyEvent.VK_UP, KeyEvent.VK_W -> dropShapeDown();
                case KeyEvent.VK_SPACE -> {
                    if (Objects.equals(turn, "right")) {
                        tryToMove(curPiece.rotateRight(), curX, curY);
                    } else {
                        tryToMove(curPiece.rotateLeft(), curX, curY);
                    }
                }
                case KeyEvent.VK_ENTER -> holdPiece();
            }
        }
    }

    private void addSounds(String fromWhere) {
        if (needSounds) {
            if (Objects.equals(fromWhere, "BrickDown")) {
                String soundName = "MyTetris/music_sounds/BrickDownSound.wav";
                music(soundName, false);
            } else if (Objects.equals(fromWhere, "ClearLine")) {
                String soundName = "MyTetris/music_sounds/ClearLineSound.wav";
                music(soundName, false);
            } else if (Objects.equals(fromWhere, "GameOver")) {
                String soundName = "MyTetris/music_sounds/GameOverSound.wav";
                music(soundName, false);
            }
        }
    }

    private void addMusic() {
        if (needMusic) {
            String soundName = "MyTetris/music_sounds/TETRIS_music.wav";
            music(soundName, true);
        }
    }

    static void music(String soundName, Boolean needLoop) {
        AudioInputStream audioInputStream;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
        } catch (UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }
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
        gainControl.setValue(-40.0f);

        if (needLoop) {
            music = clip;
            clip.loop(10000);
        }
        clip.start();
    }

}
