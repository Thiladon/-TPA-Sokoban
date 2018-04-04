package src;

import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

public class Block extends GameObject {

	private int isMoving, animation_delay, deplacement_delay, direction;
	private List<ArrayList<Integer>> map;
	private Handler handler;
	
	public Block(int x, int y, ID id, List<ArrayList<Integer>> map, Handler handler) {
		super(x, y, id);

		this.direction = 0;

		this.map = map;
		this.handler = handler;

		this.velX = 0;
		this.velY = 0;
	}

	public void tick() {

		int nextX = x + velX,
			nextY = y + velY;

		// On vérifie que la case demandée est bien située dans la carte
		Boolean isCollided = mapCollision( map, nextX, nextY );

		int direction = direction(velX, velY);
		
		// On ne peut pas se déplacer si un mouvement est déjà en cours !
		if(isMoving < 0 && isCollided != true) {

			isMoving = 1;

			for (GameObject _tempObject_ : handler.object) {
				if( _tempObject_.getId() == ID.Block ) {
					// On cherche les blocks.

					if ( nextX == _tempObject_.getX() && nextY == _tempObject_.getY()) {
						Boolean isBlockCollided = mapCollision( map, nextX + velX, nextY + velY );
						if (isBlockCollided != true) {
							_tempObject_.setX(nextX);
							_tempObject_.setY(nextY);
						} else {
							nextX -= velX;
							nextY -= velY;
							isMoving = -1;
						}
					}
				}
			}

			this.direction = direction;
			
			// On commence l'animation
			isMoving = 1;
				
			// On effectue le déplacement
			x = nextX;
			y = nextY;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.blue);

		int _frame 		= 0, // Numéro de l'image à prendre pour l'animation
			_decalageX 	= 0,
			_decalageY 	= 0; // Décalage à appliquer à la position du player
		
		if(isMoving >= deplacement_delay)
		{
			// Si le déplacement a atteint ou dépassé le temps nécéssaire pour s'effectuer, on le termine
			isMoving = -1;
		}
		else if(isMoving >= 0)
		{
			// On calcule l'image (frame) de l'animation à afficher
			_frame = isMoving / this.animation_delay;
			
			if(_frame > 3)
			{
				_frame %= 4;
			}
			
			// Nombre de pixels restant à parcourir entre les deux cases
			int deltaPosition = 24 - (24 * (isMoving / deplacement_delay));
			
			// À partir de ce nombre, on définit le décalage en x et y.
			if(direction == 3) { // haut
				_decalageY = deltaPosition;
			} else if(direction == 0) { // bas
				_decalageY = -deltaPosition;
			} else if(direction == 1) { // gauche
				_decalageX = deltaPosition;
			} else if(direction == 2) { // droite
				_decalageX = -deltaPosition;
			}
			
			// On incrémente d'une frame
			isMoving++;
		}

		g.fillRect((x * 24) + _decalageX, (y * 24) + _decalageY, 24, 24);
	}
}