package src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public abstract class GameObject extends Canvas {

	protected ID id;
	protected int x, y, velX, velY;
	
	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;

		this.id = id;
	}

	public abstract void tick();
	public abstract void render(Graphics g);

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public int getVelY() {
		return velY;
	}

	public void setId(ID id) {
		this.id = this.id;
	}

	public ID getId() {
		return this.id;
	}

	public Boolean mapCollision(List<ArrayList<Integer>> map, int nextX, int nextY){

		// Si le joueur part en direction du mur ou autre.

		for(int j = 0; j < map.size(); j++) {
			ArrayList<Integer> row = map.get(j);

			for (int i = 0; i < row.size(); i++) {

				int cell = row.get(i);  // Contient la valeur de la cellule [x][y].

				if ( i == nextX && j == nextY ) {
					if ( cell == 0 ) 
						return true; // Return True si c'est un mur.
				}
			}
		}

		return false;
	}

	public int direction(int velX, int velY) {
		if (velY == -1) return 3; // haut
		else if (velX == -1) return 1; // gauche
		else if (velY == 1) return 0; // bas
		else if (velX == 1) return 2; // droite
		else return 4;
	}

	public int[] getCasePosition() {
	    int[] _temp_ = {(this.x + 1), (this.y  + 1)};
	    return _temp_;
	}

	public String getStringCasePosition() {
		return "(" + this.getCasePosition()[0] + ", " + this.getCasePosition()[0] + ")";
	}

}