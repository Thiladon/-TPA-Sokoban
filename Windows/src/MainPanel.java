package sample;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel{
	
	private String name = "MainPanel";
	
	public String getName() {
		return name;
	}

	public MainPanel() {

		this.add(new JButton("Button 1"));
        this.add(new JButton("Button 2"));
        this.add(new JButton("Button 3"));
	}
}