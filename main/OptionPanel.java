package sample;

import javax.swing.*;
import java.awt.*;

public class OptionPanel extends JPanel{
	
	private String name = "OptionPanel";

	public String getName() {
		return name;
	}

	public OptionPanel() {
		this.add(new JTextField("TextField", 20));
	}
}