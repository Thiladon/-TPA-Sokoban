package src;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private Handler handler;

	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		System.out.println("(KeyInput.java:20) => Touch pressed");

		for( int i = 0; i < handler.object.size(); i++) {
			GameObject _tempObject_ = handler.object.get(i);

			if(_tempObject_.getId() == ID.Player) {

				if (key == 90) _tempObject_.setVelY(-1);	// Aller en haut
				if (key == 81) _tempObject_.setVelX(-1);	// Aller à gauche
				if (key == 83) _tempObject_.setVelY(1);	// Aller en bas
				if (key == 68) _tempObject_.setVelX(1); // Aller à droite

				// for (GameObject _blockObject_ : handler.object) {
				// 	if( _blockObject_.getId() == ID.Block ) {
						
				// 			System.out.println("PENIIS");
				// 		if ( _tempObject_.getX() + _tempObject_.getVelX() == _blockObject_.getX() && _tempObject_.getX() + _tempObject_.getVelX() == _blockObject_.getY()) {
				// 			_blockObject_.setVelX(_tempObject_.getVelX());
				// 			_blockObject_.setVelY(_tempObject_.getVelY());
				// 		} else {
				// 			_blockObject_.setVelX(0);
				// 			_blockObject_.setVelY(0);
				// 		}
				// 	}
				// }

			
				// if (key == 90) _tempObject_.setY(_tempObject_.getY() - 1);	// Aller en haut
				// if (key == 81) _tempObject_.setX(_tempObject_.getX() - 1);	// Aller à gauche
				// if (key == 83) _tempObject_.setY(_tempObject_.getY() + 1);	// Aller en bas
				// if (key == 68) _tempObject_.setX(_tempObject_.getX() + 1);  // Aller à droite
			



			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		System.out.println("(KeyInput.java:20) => Touch released");

		for( int i = 0; i < handler.object.size(); i++) {
			GameObject _tempObject_ = handler.object.get(i);

			if(_tempObject_.getId() == ID.Player) {

				if (key == 90) _tempObject_.setVelY(0);	// Aller en haut
				if (key == 81) _tempObject_.setVelX(0);	// Aller à gauche
				if (key == 83) _tempObject_.setVelY(0);	// Aller en bas
				if (key == 68) _tempObject_.setVelX(0); // Aller à droite

				System.out.println(
					"x : " + _tempObject_.getX() +
					"y : " + _tempObject_.getY()
				);
				
			}
		}
	}
}