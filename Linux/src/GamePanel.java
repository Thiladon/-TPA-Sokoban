package sample;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{
	
	private String name = "GamePanel";
	public Game game;

	public String getName() {
		return name;
	}

	public GamePanel(JFrame parent, int width, int height) {
		game = new Game(parent, width, height);
		this.add(game);
	}
}