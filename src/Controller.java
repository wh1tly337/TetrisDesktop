import javafx.scene.shape.Rectangle;

public class Controller {
	// Getting the numbers and the gameMesh from Tetris
	public static final int moveShape = Tetris.moveShape;
	public static final int sizeShape = Tetris.sizeShape;
	public static int XMAX = Tetris.XMAX;
	public static int[][] gameMesh = Tetris.gameMesh;

	public static void MoveRight(Form form) {
		if (form.a.getX() + moveShape <= XMAX - sizeShape && form.b.getX() + moveShape <= XMAX - sizeShape
				&& form.c.getX() + moveShape <= XMAX - sizeShape && form.d.getX() + moveShape <= XMAX - sizeShape) {
			int movea = gameMesh[((int) form.a.getX() / sizeShape) + 1][((int) form.a.getY() / sizeShape)];
			int moveb = gameMesh[((int) form.b.getX() / sizeShape) + 1][((int) form.b.getY() / sizeShape)];
			int movec = gameMesh[((int) form.c.getX() / sizeShape) + 1][((int) form.c.getY() / sizeShape)];
			int moved = gameMesh[((int) form.d.getX() / sizeShape) + 1][((int) form.d.getY() / sizeShape)];
			if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
				form.a.setX(form.a.getX() + moveShape);
				form.b.setX(form.b.getX() + moveShape);
				form.c.setX(form.c.getX() + moveShape);
				form.d.setX(form.d.getX() + moveShape);
			}
		}
	}

	public static void MoveLeft(Form form) {
		if (form.a.getX() - moveShape >= 0 && form.b.getX() - moveShape >= 0 && form.c.getX() - moveShape >= 0
				&& form.d.getX() - moveShape >= 0) {
			int movea = gameMesh[((int) form.a.getX() / sizeShape) - 1][((int) form.a.getY() / sizeShape)];
			int moveb = gameMesh[((int) form.b.getX() / sizeShape) - 1][((int) form.b.getY() / sizeShape)];
			int movec = gameMesh[((int) form.c.getX() / sizeShape) - 1][((int) form.c.getY() / sizeShape)];
			int moved = gameMesh[((int) form.d.getX() / sizeShape) - 1][((int) form.d.getY() / sizeShape)];
			if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
				form.a.setX(form.a.getX() - moveShape);
				form.b.setX(form.b.getX() - moveShape);
				form.c.setX(form.c.getX() - moveShape);
				form.d.setX(form.d.getX() - moveShape);
			}
		}
	}

	public static Form makeRect() {
		int block = (int) (Math.random() * 100);
		String name;
		Rectangle a = new Rectangle(sizeShape-1, sizeShape-1),
				b = new Rectangle(sizeShape-1, sizeShape-1),
				c = new Rectangle(sizeShape-1, sizeShape-1),
				d = new Rectangle(sizeShape-1, sizeShape-1);
		if (block < 14) {
			a.setX(XMAX / 2 - sizeShape);
			b.setX(XMAX / 2 - sizeShape);
			b.setY(sizeShape);
			c.setX(XMAX / 2);
			c.setY(sizeShape);
			d.setX(XMAX / 2 + sizeShape);
			d.setY(sizeShape);
			name = "j";
		} else if (block < 28) {
			a.setX(XMAX / 2 + sizeShape);
			b.setX(XMAX / 2 - sizeShape);
			b.setY(sizeShape);
			c.setX(XMAX / 2);
			c.setY(sizeShape);
			d.setX(XMAX / 2 + sizeShape);
			d.setY(sizeShape);
			name = "l";
		} else if (block < 42) {
			a.setX(XMAX / 2 - sizeShape);
			b.setX(XMAX / 2);
			c.setX(XMAX / 2 - sizeShape);
			c.setY(sizeShape);
			d.setX(XMAX / 2);
			d.setY(sizeShape);
			name = "o";
		} else if (block < 56) {
			a.setX(XMAX / 2 + sizeShape);
			b.setX(XMAX / 2);
			c.setX(XMAX / 2);
			c.setY(sizeShape);
			d.setX(XMAX / 2 - sizeShape);
			d.setY(sizeShape);
			name = "s";
		} else if (block < 70) {
			a.setX(XMAX / 2 - sizeShape);
			b.setX(XMAX / 2);
			c.setX(XMAX / 2);
			c.setY(sizeShape);
			d.setX(XMAX / 2 + sizeShape);
			name = "t";
		} else if (block < 84) {
			a.setX(XMAX / 2 + sizeShape);
			b.setX(XMAX / 2);
			c.setX(XMAX / 2 + sizeShape);
			c.setY(sizeShape);
			d.setX(XMAX / 2 + sizeShape + sizeShape);
			d.setY(sizeShape);
			name = "z";
		} else {
			a.setX(XMAX / 2 - sizeShape - sizeShape);
			b.setX(XMAX / 2 - sizeShape);
			c.setX(XMAX / 2);
			d.setX(XMAX / 2 + sizeShape);
			name = "i";
		}
		return new Form(a, b, c, d, name);
	}
}