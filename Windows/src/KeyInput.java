package src;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private Handler handler;
	private Game game;

	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
	}

	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();

		for( int i = 0; i < handler.object.size(); i++) {
			GameObject _tempObject_ = handler.object.get(i);

			if(_tempObject_.getId() == ID.Player) {
				// Key event for Player 1:

				this.game.isDrawed = false;
				
				if( key == 81){
					System.out.println("(KeyInput.java:25) => Left");
					System.out.println("(KeyInput.java:26) => getX: " + _tempObject_.getX());
					_tempObject_.setX(_tempObject_.getX() - 1); // Goes left
					System.out.println("(KeyInput.java:28) => getX After: " + _tempObject_.getX());
								
				} else if( key == 90) {

					System.out.println("(KeyInput.java:31) => Up");
					System.out.println("(KeyInput.java:32) => getY: " + _tempObject_.getY());
					_tempObject_.setY(_tempObject_.getY() - 1); // Goes up
					System.out.println("(KeyInput.java:34) => getY After: " + _tempObject_.getY());
								
				} else if( key == 83) {

					System.out.println("(KeyInput.java:37) => Down");
					System.out.println("(KeyInput.java:38) => getY: " + _tempObject_.getY());
					_tempObject_.setY(_tempObject_.getY() + 1); // Goes down
					System.out.println("(KeyInput.java:40) => getY After: " + _tempObject_.getY());
								
				} else if( key == 68) {

					System.out.println("(KeyInput.java:43) => Right");
					System.out.println("(KeyInput.java:44) => getX: " + _tempObject_.getX());
					_tempObject_.setX(_tempObject_.getX() + 1); // Goes right
					System.out.println("(KeyInput.java:46) => getX: After" + _tempObject_.getX());

				} else {
					this.game.isDrawed = true;
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
	}
}