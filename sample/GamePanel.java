package sample;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{
	
	private String name = "GamePanel";

	public String getName() {
		return name;
	}

	public GamePanel(JFrame parent) {
		Game canvas = new Game(parent);
		this.add(canvas);
		canvas.start();
	}
}