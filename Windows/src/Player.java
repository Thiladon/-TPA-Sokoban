package src;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;

public class Player extends GameObject {

	private Handler handler;
	private List<ArrayList<Integer>> map;
	private Game game;
	private int isMoving, animation_delay, deplacement_delay, direction;

	public Player(int x, int y, ID id, List<ArrayList<Integer>> map, Handler handler, Game game) {
		super(x, y, id);

		this.game = game; // temps
		this.handler = handler;
		this.map = map;
		this.direction = 0;
		this.isMoving = -1; // false
		this.animation_delay = 10;
		this.deplacement_delay = 60;

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

			// On commence l'animation
			isMoving = 1;

			int nb = 0;
			for (GameObject _tempObject_ : handler.object) {
				if( _tempObject_.getId() == ID.Block ) {
					System.out.println(
						"(Player.java:49) nb : " + nb++ + "\n" + 
						"(Player.java:49) _tempObject_.getVelX() : " + _tempObject_.getVelX() + "\n" + 
						"(Player.java:49) _tempObject_.getVelY() : " + _tempObject_.getVelY() + "\n"
					);
					// On cherche les blocks.

					// _tempObject_.setVelX(0);
					// _tempObject_.setVelY(0);

					if ( nextX == _tempObject_.getX() && nextY == _tempObject_.getY()) {
						Boolean isBlockCollided = mapCollision( map, nextX + velX, nextY + velY );
						if (isBlockCollided != true) {
							_tempObject_.setVelX(velX);
							_tempObject_.setVelY(velY);
						} else {
							nextX = x;
							nextY = y;
							isMoving = -1;
						}
					} else {
						_tempObject_.setVelX(0);
						_tempObject_.setVelY(0);
					}
				}
			}

			// On change la direction du player
			this.direction = direction;
			
				
			// On effectue le déplacement
			x = nextX;
			y = nextY;
		}

		// System.out.println("Called : " + tickNumber++ + " times.");
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		
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
