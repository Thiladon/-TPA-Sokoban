package sample;

import javax.swing.*;
import java.awt.*;

public class Game extends Canvas {
	
	private String name = "Game";
	

	public String getName() {
		return name;
	}

	public Game() {

		setBackground(Color.black);
		setSize(300, 300);
	}

	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(100, 250, 100, 200);
	}
}