import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

public class Tetris extends Application {
    // The variables
    public static final int moveShape = 25;
    public static final int sizeShape = 25;
    public static int XMAX = sizeShape * 12;
    public static int YMAX = sizeShape * 24;
    public static int[][] gameMesh = new int[XMAX / sizeShape][YMAX / sizeShape];
    private static final Pane group = new Pane(); // вспомогательный класс для позиционированния дочерних элементов
    private static Form object;
    private static final Scene scene = new Scene(group, XMAX + 150, YMAX); // окно игры целиком
    public static int score = 0;
    private static int top = 0;
    private static boolean game = true;
    private static Form nextShape = Controller.makeRect();
    private static int linesCounter = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Text bottomText = new Text();
        bottomText.setText("Made by Ivanovichev Ivan");
        bottomText.setFont(Font.font("Times New Romans", 10));
        bottomText.setTranslateX(220);
        bottomText.setTranslateY(480);

        VBox root = new VBox(bottomText);
        Scene scene = new Scene(root, 550, 500);
        stage.setScene(scene);

        Button startBtn = new Button("Start Game");
        Button settingsBtn = new Button("Game Settings");
        Button closeBtn = new Button("Close The Game");

        closeBtn.setCancelButton(true);
        startBtn.setPrefSize(225, 75);
//        startBtn.setStyle("button.css");
        settingsBtn.setPrefSize(225, 75);
//        settingsBtn.setStyle("-fx-background-color: #118AB2;");
        closeBtn.setPrefSize(225, 75);
//        closeBtn.setStyle("-fx-background-color: #118AB2;");

        startBtn.setOnAction(actionEvent -> startGame(stage));
        closeBtn.setOnAction(actionEvent -> System.exit(0));

        root.getChildren().addAll(startBtn, settingsBtn, closeBtn);

        startBtn.setTranslateX(162.5);
        startBtn.setTranslateY(200);
        settingsBtn.setTranslateX(162.5);
        settingsBtn.setTranslateY(210);
        closeBtn.setTranslateX(162.5);
        closeBtn.setTranslateY(220);

        root.setStyle("-fx-background-color: #0341AE;");
        stage.setResizable(false);
        stage.setTitle("T E T R I S");
        stage.show();
    }

    public void startGame(Stage stage) {
        for (int[] a : gameMesh) {
            Arrays.fill(a, 0);
        }

        Line line = new Line(XMAX, 0, XMAX, YMAX);
        Text scoretext = new Text("Score: ");
        scoretext.setStyle("-fx-font: 20 arial;");
        scoretext.setY(50);
        scoretext.setX(XMAX + 5);
        Text level = new Text("Lines: ");
        level.setStyle("-fx-font: 20 arial;");
        level.setY(100);
        level.setX(XMAX + 5);
        level.setFill(Color.GREEN);
        group.getChildren().addAll(scoretext, line, level);

        Form a = nextShape;
        group.getChildren().addAll(a.a, a.b, a.c, a.d);
        moveOnKeyPress(a);
        object = a;
        nextShape = Controller.makeRect();
        stage.setScene(scene);
        stage.setTitle("T E T R I S");
        stage.show();

        Timer fall = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                Platform.runLater(() -> {
                    if (object.a.getY() == 0 || object.b.getY() == 0 || object.c.getY() == 0
                            || object.d.getY() == 0) {
                        top++;
                    } else {
                        top = 0;
                    }

                    if (top == 2) {
                        // GAME OVER
                        Text over = new Text("GAME OVER");
                        over.setFill(Color.RED);
                        over.setStyle("-fx-font: 70 arial;");
                        over.setY(250);
                        over.setX(10);
                        group.getChildren().add(over);
                        game = false;
                    }
                    // Exit
                    if (top == 15) {
                        System.exit(0);
                    }

                    if (game) {
                        MoveDown(object);
                        scoretext.setText("Score: " + score);
                        level.setText("Lines: " + linesCounter);
                    }
                });
            }
        };
        fall.schedule(task, 0, 300);
    }

    private void moveOnKeyPress(Form form) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case RIGHT, D -> Controller.MoveRight(form);
                case DOWN, S -> {
                    MoveDown(form);
                    score++;
                }
                case LEFT, A -> Controller.MoveLeft(form);
                case SPACE -> TurnShape(form);
            }
        });
    }

    public void TurnShape(Form form) {
        int f = form.form;
        Rectangle a = form.a;
        Rectangle b = form.b;
        Rectangle c = form.c;
        Rectangle d = form.d;
        switch (form.getName()) {
            case "j":
                if (f == 1 && cB(a, 1, -1) && cB(c, -1, -1) && cB(d, -2, -2)) {
                    MoveRight(form.a);
                    MoveDown(form.a);
                    MoveDown(form.c);
                    MoveLeft(form.c);
                    MoveDown(form.d);
                    MoveDown(form.d);
                    MoveLeft(form.d);
                    MoveLeft(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, -2, 2)) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveLeft(form.d);
                    MoveLeft(form.d);
                    MoveUp(form.d);
                    MoveUp(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(a, -1, 1) && cB(c, 1, 1) && cB(d, 2, 2)) {
                    MoveLeft(form.a);
                    MoveUp(form.a);
                    MoveUp(form.c);
                    MoveRight(form.c);
                    MoveUp(form.d);
                    MoveUp(form.d);
                    MoveRight(form.d);
                    MoveRight(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 2, -2)) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveRight(form.d);
                    MoveRight(form.d);
                    MoveDown(form.d);
                    MoveDown(form.d);
                    form.changeForm();
                    break;
                }
                break;
            case "l":
                if (f == 1 && cB(a, 1, -1) && cB(c, 1, 1) && cB(b, 2, 2)) {
                    MoveRight(form.a);
                    MoveDown(form.a);
                    MoveUp(form.c);
                    MoveRight(form.c);
                    MoveUp(form.b);
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveRight(form.b);
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(a, -1, -1) && cB(b, 2, -2) && cB(c, 1, -1)) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveRight(form.b);
                    MoveRight(form.b);
                    MoveDown(form.b);
                    MoveDown(form.b);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(a, -1, 1) && cB(c, -1, -1) && cB(b, -2, -2)) {
                    MoveLeft(form.a);
                    MoveUp(form.a);
                    MoveDown(form.c);
                    MoveLeft(form.c);
                    MoveDown(form.b);
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveLeft(form.b);
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(a, 1, 1) && cB(b, -2, 2) && cB(c, -1, 1)) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveLeft(form.b);
                    MoveLeft(form.b);
                    MoveUp(form.b);
                    MoveUp(form.b);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    form.changeForm();
                    break;
                }
                break;
            case "o":
                break;
            case "s":
                if (f == 1 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, 0, 2)) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveUp(form.d);
                    MoveUp(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 0, -2)) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveDown(form.d);
                    MoveDown(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, 0, 2)) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveUp(form.d);
                    MoveUp(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 0, -2)) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveDown(form.d);
                    MoveDown(form.d);
                    form.changeForm();
                    break;
                }
                break;
            case "t":
                if (f == 1 && cB(a, 1, 1) && cB(d, -1, -1) && cB(c, -1, 1)) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveDown(form.d);
                    MoveLeft(form.d);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(a, 1, -1) && cB(d, -1, 1) && cB(c, 1, 1)) {
                    MoveRight(form.a);
                    MoveDown(form.a);
                    MoveLeft(form.d);
                    MoveUp(form.d);
                    MoveUp(form.c);
                    MoveRight(form.c);
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(a, -1, -1) && cB(d, 1, 1) && cB(c, 1, -1)) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveUp(form.d);
                    MoveRight(form.d);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(a, -1, 1) && cB(d, 1, -1) && cB(c, -1, -1)) {
                    MoveLeft(form.a);
                    MoveUp(form.a);
                    MoveRight(form.d);
                    MoveDown(form.d);
                    MoveDown(form.c);
                    MoveLeft(form.c);
                    form.changeForm();
                    break;
                }
                break;
            case "z":
                if (f == 1 && cB(b, 1, 1) && cB(c, -1, 1) && cB(d, -2, 0)) {
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveLeft(form.d);
                    MoveLeft(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(b, -1, -1) && cB(c, 1, -1) && cB(d, 2, 0)) {
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveRight(form.d);
                    MoveRight(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(b, 1, 1) && cB(c, -1, 1) && cB(d, -2, 0)) {
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveLeft(form.d);
                    MoveLeft(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(b, -1, -1) && cB(c, 1, -1) && cB(d, 2, 0)) {
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveRight(form.d);
                    MoveRight(form.d);
                    form.changeForm();
                    break;
                }
                break;
            case "i":
                if (f == 1 && cB(a, 2, 2) && cB(b, 1, 1) && cB(d, -1, -1)) {
                    MoveUp(form.a);
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.a);
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveDown(form.d);
                    MoveLeft(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 2 && cB(a, -2, -2) && cB(b, -1, -1) && cB(d, 1, 1)) {
                    MoveDown(form.a);
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.a);
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveUp(form.d);
                    MoveRight(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 3 && cB(a, 2, 2) && cB(b, 1, 1) && cB(d, -1, -1)) {
                    MoveUp(form.a);
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.a);
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveDown(form.d);
                    MoveLeft(form.d);
                    form.changeForm();
                    break;
                }
                if (f == 4 && cB(a, -2, -2) && cB(b, -1, -1) && cB(d, 1, 1)) {
                    MoveDown(form.a);
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.a);
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveUp(form.d);
                    MoveRight(form.d);
                    form.changeForm();
                    break;
                }
                break;
        }
    }

    private void RemoveRows() {
        ArrayList<Node> rects = new ArrayList<>();
        ArrayList<Integer> lines = new ArrayList<>();
        ArrayList<Node> newRects = new ArrayList<>();
        int full = 0;
        for (int i = 0; i < gameMesh[0].length; i++) {
            for (int[] gameMesh : gameMesh) {
                if (gameMesh[i] == 1)
                    full++;
            }
            if (full == gameMesh.length) {
                lines.add(i);
            }
            full = 0;
        }
        if (lines.size() > 0)
            do {
                for (Node node : Tetris.group.getChildren()) {
                    if (node instanceof Rectangle) {
                        rects.add(node);
                    }
                }
                score += 50;
                linesCounter++;

                for (Node node : rects) {
                    Rectangle a = (Rectangle) node;
                    if (a.getY() == lines.get(0) * sizeShape) {
                        gameMesh[(int) a.getX() / sizeShape][(int) a.getY() / sizeShape] = 0;
                        Tetris.group.getChildren().remove(node);
                    } else {
                        newRects.add(node);
                    }
                }

                for (Node node : newRects) {
                    Rectangle a = (Rectangle) node;
                    if (a.getY() < lines.get(0) * sizeShape) {
                        gameMesh[(int) a.getX() / sizeShape][(int) a.getY() / sizeShape] = 0;
                        a.setY(a.getY() + sizeShape);
                    }
                }
                lines.remove(0);
                rects.clear();
                newRects.clear();
                for (Node node : Tetris.group.getChildren()) {
                    if (node instanceof Rectangle) {
                        rects.add(node);
                    }
                }
                for (Node node : rects) {
                    Rectangle a = (Rectangle) node;
                    try {
                        gameMesh[(int) a.getX() / sizeShape][(int) a.getY() / sizeShape] = 1;
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                }
                rects.clear();
            } while (lines.size() > 0);
    }

    private void MoveDown(Rectangle rect) {
        if (rect.getY() + moveShape < YMAX) {
            rect.setY(rect.getY() + moveShape);
        }

    }

    private void MoveRight(Rectangle rect) {
        if (rect.getX() + moveShape <= XMAX - sizeShape) {
            rect.setX(rect.getX() + moveShape);
        }
    }

    private void MoveLeft(Rectangle rect) {
        if (rect.getX() - moveShape >= 0) {
            rect.setX(rect.getX() - moveShape);
        }
    }

    private void MoveUp(Rectangle rect) {
        if (rect.getY() - moveShape > 0) {
            rect.setY(rect.getY() - moveShape);
        }
    }

    private void MoveDown(Form form) {
        if (form.a.getY() == YMAX - sizeShape || form.b.getY() == YMAX - sizeShape || form.c.getY() == YMAX - sizeShape
                || form.d.getY() == YMAX - sizeShape || moveA(form) || moveB(form) || moveC(form) || moveD(form)) {
            gameMesh[(int) form.a.getX() / sizeShape][(int) form.a.getY() / sizeShape] = 1;
            gameMesh[(int) form.b.getX() / sizeShape][(int) form.b.getY() / sizeShape] = 1;
            gameMesh[(int) form.c.getX() / sizeShape][(int) form.c.getY() / sizeShape] = 1;
            gameMesh[(int) form.d.getX() / sizeShape][(int) form.d.getY() / sizeShape] = 1;
            RemoveRows();

            Form a = nextShape;
            nextShape = Controller.makeRect();
            object = a;
            group.getChildren().addAll(a.a, a.b, a.c, a.d);
            moveOnKeyPress(a);
        }

        if (form.a.getY() + moveShape < YMAX && form.b.getY() + moveShape < YMAX && form.c.getY() + moveShape < YMAX
                && form.d.getY() + moveShape < YMAX) {
            int movea = gameMesh[(int) form.a.getX() / sizeShape][((int) form.a.getY() / sizeShape) + 1];
            int moveb = gameMesh[(int) form.b.getX() / sizeShape][((int) form.b.getY() / sizeShape) + 1];
            int movec = gameMesh[(int) form.c.getX() / sizeShape][((int) form.c.getY() / sizeShape) + 1];
            int moved = gameMesh[(int) form.d.getX() / sizeShape][((int) form.d.getY() / sizeShape) + 1];
            if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                form.a.setY(form.a.getY() + moveShape);
                form.b.setY(form.b.getY() + moveShape);
                form.c.setY(form.c.getY() + moveShape);
                form.d.setY(form.d.getY() + moveShape);
            }
        }
    }

    private boolean moveA(Form form) {
        return (gameMesh[(int) form.a.getX() / sizeShape][((int) form.a.getY() / sizeShape) + 1] == 1);
    }

    private boolean moveB(Form form) {
        return (gameMesh[(int) form.b.getX() / sizeShape][((int) form.b.getY() / sizeShape) + 1] == 1);
    }

    private boolean moveC(Form form) {
        return (gameMesh[(int) form.c.getX() / sizeShape][((int) form.c.getY() / sizeShape) + 1] == 1);
    }

    private boolean moveD(Form form) {
        return (gameMesh[(int) form.d.getX() / sizeShape][((int) form.d.getY() / sizeShape) + 1] == 1);
    }

    private boolean cB(Rectangle rect, int x, int y) {
        boolean xb = false;
        boolean yb = false;
        if (x >= 0) {
            xb = rect.getX() + x * moveShape <= XMAX - sizeShape;
        }
        if (x < 0) {
            xb = rect.getX() + x * moveShape >= 0;
        }
        if (y >= 0) {
            yb = rect.getY() - y * moveShape > 0;
        }
        if (y < 0) {
            yb = rect.getY() + y * moveShape < YMAX;
        }
        return xb && yb && gameMesh[((int) rect.getX() / sizeShape) + x][((int) rect.getY() / sizeShape) - y] == 0;
    }

}