import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Form {
    Rectangle a;
    Rectangle b;
    Rectangle c;
    Rectangle d;
    Color color;
    private final String name;
    public int form = 1;

    public Form(Rectangle a, Rectangle b, Rectangle c, Rectangle d, String name) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.name = name;

        switch (name) {
            case "j" -> color = Color.ORANGE;
            case "l" -> color = Color.RED;
            case "o" -> color = Color.GREEN;
            case "s" -> color = Color.BLUE;
            case "t" -> color = Color.YELLOW;
            case "z" -> color = Color.CYAN;
            case "i" -> color = Color.PURPLE;
        }
        this.a.setFill(color);
        this.b.setFill(color);
        this.c.setFill(color);
        this.d.setFill(color);
    }


    public String getName() {
        return name;
    }


    public void changeForm() {
        if (form != 4) {
            form++;
        } else {
            form = 1;
        }
    }
}