//package main_code;
//
//import main_code.Shape.ShapeList;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class HoldBoard extends JPanel {
//    private final int BOARD_WIDTH = 5; //10
//    private final int BOARD_HEIGHT = 5; //22
//    public boolean isFallingFinished = false;
//    private int curX = 0;
//    private int curY = 0;
//    public Shape curPiece;
//    private ShapeList[] board;
//
//    public HoldBoard() {
//        initBoard();
//    }
//
//    private void initBoard() {
//        setFocusable(false);
//    }
//
//    private int squareWidth() {
//        return 100 / BOARD_WIDTH;
//    }
//
//    private int squareHeight() {
//        return 100 / BOARD_HEIGHT;
//    }
//
//    private ShapeList shapeAt(int x, int y) {
//        return board[(y * BOARD_WIDTH) + x];
//    }
//
//    void start() {
//        curPiece = new Shape();
//        board = new ShapeList[BOARD_WIDTH * BOARD_HEIGHT];
//
//        clearBoard();
//        newPiece();
//
////        curPiece.setRandomShape();
////        curX = BOARD_WIDTH / 2 - 1; // +1
////        curY = BOARD_HEIGHT - 1 + curPiece.minY();
//
//        int gameSpeed = 1000;
//        Timer timer = new Timer(gameSpeed, new GameCycle());
//        timer.start();
//    }
//
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        ShapeDrawing(g);
//    }
//
//    private void ShapeDrawing(Graphics g) {
//        var size = getSize();
//        int boardTop = (int) size.getHeight() - BOARD_HEIGHT * squareHeight();
//
//        for (int i = 0; i < BOARD_HEIGHT; i++) {
//            for (int j = 0; j < BOARD_WIDTH; j++) {
//                ShapeList shape = shapeAt(j, BOARD_HEIGHT - i - 1);
//
//                if (shape != ShapeList.NoShape) {
//                    drawSquare(g, j * squareWidth(), boardTop + i * squareHeight(), shape);
//                }
//            }
//        }
//
//        if (curPiece.getShape() != ShapeList.NoShape) {
//            for (int i = 0; i < 4; i++) {
//
//                int x = curX + curPiece.x(i);
//                int y = curY - curPiece.y(i);
//
//                drawSquare(g, x * squareWidth(), boardTop + (BOARD_HEIGHT - y - 1) * squareHeight(), curPiece.getShape());
//            }
//        }
//    }
//
////    public void oneLineDown() {
////        if (!tryMove(curPiece, curX, curY - 1)) {
////            pieceDropped();
////        }
////    }
//
//    private void clearBoard() {
//        for (int i = 0; i < BOARD_HEIGHT * BOARD_WIDTH; i++) {
//            board[i] = ShapeList.NoShape;
//        }
//    }
//
////    private void pieceDropped() {
////        for (int i = 0; i < 4; i++) {
////            int x = curX + curPiece.x(i);
////            int y = curY - curPiece.y(i);
////            board[(y * BOARD_WIDTH) + x] = curPiece.getShape();
////        }
////
////        removeFullLines();
////
////        if (!isFallingFinished) {
////            newPiece();
////        }
////    }
//
//    public void newPiece() {
//        curPiece.setRandomShape();
//        curX = BOARD_WIDTH / 2; // +1
//        curY = BOARD_HEIGHT - 1 + curPiece.minY(); // -1
//    }
//
////    private boolean tryMove(Shape newPiece, int newX, int newY) {
////        for (int i = 0; i < 4; i++) {
////            int x = newX + newPiece.x(i);
////            int y = newY - newPiece.y(i);
////
////            if (x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT) {
////                return false;
////            }
////
////            if (shapeAt(x, y) != ShapeList.NoShape) {
////                return false;
////            }
////        }
////
////        curPiece = newPiece;
////        curX = newX;
////        curY = newY;
////
////        repaint();
////
////        return true;
////    }
//
//    private void removeFullLines() {
//        for (int i = BOARD_HEIGHT - 1; i >= 0; i--) {
//            for (int k = i; k < BOARD_HEIGHT - 1; k++) {
//                for (int j = 0; j < BOARD_WIDTH; j++) {
//                    board[(k * BOARD_WIDTH) + j] = shapeAt(j, k + 1);
//                }
//            }
//        }
//
//        isFallingFinished = true;
//        curPiece.setShape(ShapeList.NoShape);
//    }
//
//
//    private void drawSquare(Graphics g, int x, int y, ShapeList shape) {
//        Color[] colors = {
//                new Color(0, 0, 0),
//                new Color(204, 102, 102),
//                new Color(102, 204, 102),
//                new Color(102, 102, 204),
//                new Color(204, 204, 102),
//                new Color(204, 102, 204),
//                new Color(102, 204, 204),
//                new Color(218, 170, 0)
//        };
//
//        var color = colors[shape.ordinal()];
//
//        g.setColor(color);
//        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);
//
//        g.setColor(color.brighter());
//        g.drawLine(x, y + squareHeight() - 1, x, y);
//        g.drawLine(x, y, x + squareWidth() - 1, y);
//
//        g.setColor(color.darker());
//        g.drawLine(x + 1, y + squareHeight() - 1,
//                x + squareWidth() - 1, y + squareHeight() - 1);
//        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1,
//                x + squareWidth() - 1, y + 1);
//    }
//
//    private class GameCycle implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            doGameCycle();
//        }
//
//    }
//
//    private void doGameCycle() {
//        updateGameStatus();
//        repaint();
//    }
//
//    private void updateGameStatus() {
//        if (isFallingFinished) {
//            isFallingFinished = false;
//            newPiece();
//        } else {
//            removeFullLines();
//        }
//    }
//}
