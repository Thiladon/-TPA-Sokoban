package src;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1550691097823471818L;
	private String name = "Sokoban";	//Nom de la JFrame.

	/*
	 * On instancie les variables nécessaire
	 * au fonctionnement du jeu.
	 */

	private Thread thread;
	private Board board;
	private List<ArrayList<Integer>> map;
	private Config config;
	private Boolean running = false;
	private BufferStrategy bufferStrategy;
	public int width, height, gameLevel;
	public Handler handler;

	/*
	 * Public Game() :
	 *		(Type) => Constructor.
	 */

	public Game() {
		this.handler = new Handler(); 			// On met le gestionnaire en top pour éviter une thread-0 error.
		this.gameLevel = 1;						// Niveau de jeu (Instancié à 1 par défaut).
		this.config = new Config();				// Configuration de base de l'interface.
		this.width  = config.width[1];			// Largeur par défaut.
		this.height = config.height[1];			// Hauteur par défaut.
		this.map = this.readXsbFile(gameLevel);	// Définit la map se lisant comme un int[y][x] par lecture d'un fichier (.xsb).
		this.board  = new Board(map);			// Instance de la variable board par rapport à map.

		this.init();
		
		new Window(width, height, this.name, this); //On créé la fenêtre
	}

	public void init() {
		/*
		*
		*/
		for(int y = 0; y < map.size(); y++) {
			ArrayList<Integer> row = map.get(y);
			for (int x = 0; x < row.size(); x++) {
				int cell = row.get(x);
				System.out.println("cell: " + cell + "x: " + x + "\ny: " + y);
				if (cell == 9999) {
					System.out.println("new Player(" + x + ", " + y + ", " + ID.Player + ") added to handler");
					this.handler.addObject(new Player(x, y, ID.Player, map, handler, this));
				}
				if (cell == 9998) {
					System.out.println("new Block(" + x + ", " + y + ", " + ID.Block + ") added to handler");
					this.handler.addObject(new Block(x, y, ID.Block, map, handler));
				}
			}
		}
	}

	public String getBoardList() {
		
		// fonction utilisé pour le débugage
		
		String message = "(Board.java:25) boardList :\n";
		for(int y = 0; y < map.size(); y++) {
			ArrayList<Integer> row = map.get(y);
			message += map.get(y) + "\n";
		}
		return message;
	}

	public List<ArrayList<Integer>> readXsbFile(int level) {
		
		// Lecture d'un fichier (.xsb).
		
		List<ArrayList<Integer>> rowList = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> colList = new ArrayList<Integer>();

		FileReader inputStream = null;
		try {
			inputStream = new FileReader("src/levels/level-" + level + ".xsb");
			int character;
			while ((character = inputStream.read()) != -1) {
				if (character == 35 && character != 69) colList.add(0); 	// Wall
				if (character == 45 && character != 69) colList.add(1); 	// FLoor
				if (character == 46 && character != 69) colList.add(2); 	// Hole
				if (character == 36 && character != 69) colList.add(9998); 	// Block
				if (character == 64 && character != 69) colList.add(9999); 	// Player
				if (character == 38 && character != 69) {
					rowList.add(colList);
					colList = new ArrayList<Integer>();
				}
			}
			
			if (inputStream != null) {
				inputStream.close();
			}
			
			return rowList;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rowList;
	
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void sleep(int i) {
		try {
			thread.sleep(i);
			System.out.println("Thread Sleep");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.currentTimeMillis();
		double fps = 60.0; 	// 60 images par seconde
		double skipTicks = 1000 / fps; 	// 1 seconde divisé par le nombre d'image par seconde pour un résultat en ms ( 16.6666667 ms )
		double delta = 0.0;
		long nextGameTick = System.currentTimeMillis();
		int loops = 0;
		int deltaNumber = 0;

		while(running) {
			long now = System.currentTimeMillis();

			// Diff de temps écouler en l'espace d'un tour de la boucle / nos 16.666667 ms
			// Nous permet de faire 60 passage dans le boucle while(delta>=1) => Donc d'être à 60 fps.

			delta += (now - lastTime) / skipTicks;

			lastTime = now;

			while (delta >= 1) {
				delta--;
				tick();
			}
			if (running) {
				render();
			}
			loops++;

			if(System.currentTimeMillis() - nextGameTick > 1000) {
				nextGameTick += 1000;
				System.out.println("(Game.java:83) => FPS: " + loops);
				loops = 0;
			}
		}
		stop();

	}

	private void tick() {
		handler.tick();
	}

	private void render() {
		BufferStrategy bufferStrategy = this.getBufferStrategy();

		if(bufferStrategy == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bufferStrategy.getDrawGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);


		board.render(g);
		handler.render(g);

		g.dispose();

		bufferStrategy.show();
	}

	public static void main(String[] args) {
		new Game();
	}

}
