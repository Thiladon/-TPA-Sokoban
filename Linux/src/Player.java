package src;

import java.awt.*;

public class Player extends GameObject {
	public Player(int x, int y, ID id) {
		super(x, y, id);
	}

	public void tick() {

	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(8+(24*x), 0+(24*y), 24, 24);
	}
}