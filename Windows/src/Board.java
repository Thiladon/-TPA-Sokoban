package src;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.net.URL;

public class Board extends Canvas {
	private List<ArrayList<Integer>> boardList;
	private Image img;

	private String _IMAGES_DIR_ = "src/images/";
	private URL mapSpritesheets = getClass().getClassLoader().getResource(_IMAGES_DIR_ + "mapSpritesheets.png");

	public Board(List<ArrayList<Integer>> boardList) {

		this.boardList = boardList;
		this.img = null;
		
		if (mapSpritesheets != null) {
			try {
				this.img = ImageIO.read(mapSpritesheets);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
		   	System.err.println("(Game.java:129) => Couldn't find file: " + mapSpritesheets);
		}
	}

	public void tick() { }

	public void render(Graphics g) {

		if (this.img != null) {
			for(int y = 0; y < boardList.size(); y++) {
				ArrayList<Integer> row = boardList.get(y);

				for (int x = 0; x < row.size(); x++) {
					int cell = row.get(x);
				
					int dstx1 = 0+(24*x), // Point x, y (bord haut gauche), de notre image,
						dsty1 = 0+(24*y), // pour définir le début de sa position sur le canvas.
						dstx2 = 24+(24*x), // Point x, y (bord bas droite), de notre image,
						dsty2 = 24+(24*y), // pour définir la fin de sa position sur le canvas.
						srcx1 = (cell != 9999 && cell != 9998 ) ? 24*cell : 24, // Point x, y (bord haut gauche),
						srcy1 = 0, 												// à prendre sur le fichier img.
						srcx2 = (cell != 9999 && cell != 9998 ) ? 24 + (24*cell) : 48, 	// Point x, y (bord bas droit),
						srcy2 = 24;														// à prendre sur le fichier img.

					g.drawImage(img, dstx1, dsty1, dstx2, dsty2, srcx1, srcy1, srcx2, srcy2, this);
				}
			}
		}
	}
}
