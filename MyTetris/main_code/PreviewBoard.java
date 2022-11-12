package main_code;

import main_code.Shape.ShapeList;

import javax.swing.*;
import java.awt.*;

public class PreviewBoard extends JPanel {
    private final int BOARD_WIDTH_P = 5; //10
    private final int BOARD_HEIGHT_P = 5; //22
    private static int curX_P = 0;
    private static int curY_P = 0;
    public static Shape curPiece_P;
    private ShapeList[] board_P;

    public PreviewBoard() {
        initBoard();
    }

    private void initBoard() {
        setFocusable(false);
    }

    private int squareWidth() {
        return 100 / BOARD_WIDTH_P;
    }

    private int squareHeight() {
        return 100 / BOARD_HEIGHT_P;
    }

    private ShapeList shapeAt(int x, int y) {
        return board_P[(y * BOARD_WIDTH_P) + x];
    }

    void start() {
        curPiece_P = new Shape();
        board_P = new ShapeList[BOARD_WIDTH_P * BOARD_HEIGHT_P];

        clearBoard_P();
        newPiece_P();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ShapeDrawing(g);
    }

    private void ShapeDrawing(Graphics g) {
        var size = getSize();
        int boardTop = (int) size.getHeight() - BOARD_HEIGHT_P * squareHeight();

        for (int i = 0; i < BOARD_HEIGHT_P; i++) {
            for (int j = 0; j < BOARD_WIDTH_P; j++) {
                ShapeList shape = shapeAt(j, BOARD_HEIGHT_P - i - 1);

                if (shape != ShapeList.NoShape) {
                    drawSquare(g, j * squareWidth(), boardTop + i * squareHeight(), shape);
                }
            }
        }

        if (curPiece_P.getShape() != ShapeList.NoShape) {
            for (int i = 0; i < 4; i++) {

                int x = curX_P + curPiece_P.x(i);
                int y = curY_P - curPiece_P.y(i);

                drawSquare(g, x * squareWidth(), boardTop + (BOARD_HEIGHT_P - y - 1) * squareHeight(), curPiece_P.getShape());
            }
        }
    }

    public void clearBoard_P() {
        for (int i = 0; i < BOARD_HEIGHT_P * BOARD_WIDTH_P; i++) {
            board_P[i] = ShapeList.NoShape;
        }
    }

    public void newPiece_P() {
        curPiece_P.setRandomShape();
        curX_P = BOARD_WIDTH_P / 2; // +1
        curY_P = BOARD_HEIGHT_P - 2 + curPiece_P.minY();
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
}
