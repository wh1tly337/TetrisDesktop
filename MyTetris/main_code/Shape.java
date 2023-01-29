package main_code;

import java.util.Random;

public class Shape {

    protected enum ShapeList {
        EmptyShape, ZShape, SShape, LineShape,
        TShape, SquareShape, LShape, MirroredLShape
    }

    private ShapeList pieceShape;
    private final int[][] coords;

    public Shape() {
        coords = new int[4][2];
        setShape(ShapeList.EmptyShape);
    }

    void setShape(ShapeList shape) {
        int[][][] coordsTable = new int[][][]{
                {{0, 0}, {0, 0}, {0, 0}, {0, 0}},
                {{0, -1}, {0, 0}, {-1, 0}, {-1, 1}},
                {{0, -1}, {0, 0}, {1, 0}, {1, 1}},
                {{0, -1}, {0, 0}, {0, 1}, {0, 2}},
                {{-1, 0}, {0, 0}, {1, 0}, {0, 1}},
                {{0, 0}, {1, 0}, {0, 1}, {1, 1}},
                {{-1, -1}, {0, -1}, {0, 0}, {0, 1}},
                {{1, -1}, {0, -1}, {0, 0}, {0, 1}}
        };

        for (int i = 0; i < 4; i++) {
            System.arraycopy(coordsTable[shape.ordinal()], 0, coords, 0, 4);
        }

        pieceShape = shape;
    }

    private void setX(int index, int x) {
        coords[index][0] = x;
    }

    private void setY(int index, int y) {
        coords[index][1] = y;
    }

    int x(int index) {
        return coords[index][0];
    }

    int y(int index) {
        return coords[index][1];
    }

    ShapeList getShape() {
        return pieceShape;
    }

    void setRandomShape() {
        var r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;

        ShapeList[] values = ShapeList.values();
        setShape(values[x]);
    }

    void setNextRandomShape() {
        var r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;

        ShapeList[] values = ShapeList.values();
        setShape(values[x]);
    }

    int minY() {
        int m = coords[0][1];
        for (int i = 0; i < 4; i++) {
            m = Math.min(m, coords[i][1]);
        }
        return m;
    }

// for future settings
//    Shape rotateLeft() {
//        if (pieceShape == ShapeList.SquareShape) {
//            return this;
//        }
//
//        var result = new Shape();
//        result.pieceShape = pieceShape;
//
//        for (int i = 0; i < 4; i++) {
//            result.setX(i, y(i));
//            result.setY(i, -x(i));
//        }
//        return result;
//    }

    Shape rotateRight() {

        if (pieceShape == ShapeList.SquareShape) {
            return this;
        }

        var result = new Shape();
        result.pieceShape = pieceShape;

        for (int i = 0; i < 4; i++) {
            result.setX(i, -y(i));
            result.setY(i, x(i));
        }
        return result;
    }
}
