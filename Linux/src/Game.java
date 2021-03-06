package src;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1550691097823471818L;
	private int width, height, caseWidth, caseHeight;
	private int gameState = 0;
	private String name = "Game";
	private JFrame parent;
	private Thread thread;
	private Boolean running = false;
	private BufferStrategy bufferStrategy;
	private Handler handler;
	private String _RESSOURCE_DIR_ = "sample/images/";
	public Boolean isDrawed = true;
	

	public String getName() {
		return name;
	}

	public Game(JFrame p, int width, int height) {
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler, this));
		
		this.parent = p;
		this.width = width;
		this.height = height;
		this.caseWidth = this.width/24;
		this.caseHeight = this.height/24;

		this.parent.setPreferredSize(new Dimension(this.width+16, this.height+44));
		this.parent.setLocationRelativeTo(null);

		setSize(this.width+16, this.height+44);
		setBackground(Color.black);
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	private synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Getter:

	public void run() {
		long lastTime = System.nanoTime();
		double fps = 60.0;
		double skipTicks = 1000000000 / fps;
		double delta = 0.0;
		long nextGameTick = System.currentTimeMillis();
		int loops = 0;

		while(running) {
			long now = System.nanoTime();
			delta += (now + lastTime) / skipTicks;
			lastTime = now;

			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running) {
				render();
			}
			loops++;

			if(System.currentTimeMillis() - nextGameTick > 1000) {
				System.out.println("(Game.java:88) => " + loops);
				nextGameTick += 1000;
				loops = 0;
			}
		}
		stop(); 

	}

	private void tick() {
		handler.tick();
	}

	private void render() {

		this.bufferStrategy = this.parent.getBufferStrategy();

		if(bufferStrategy == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bufferStrategy.getDrawGraphics();

		if(this.gameState == 0) {
			System.out.println("(Game.java:105) => Start loading map");
			
			drawMap(g);

			System.out.println("(Game.java:109) => Loading map end");

			handler.addObject(new Player(4,4, ID.Player));
			System.out.println(handler.getObject(0).getStringCasePosition());
			System.out.println(handler.getObject(0).getId());
			
			this.gameState++;
			this.isDrawed = false;
		}
			

		if(this.isDrawed == false) {
			
			System.out.println("(Game.java:131) => Rendering handler");
			
			handler.render(g);
			
			g.dispose();

			bufferStrategy.show();
			
			this.isDrawed = true;
		}
	}

	public void drawMap(Graphics g) {
				
		URL mapSpritesheets = getClass().getClassLoader().getResource(_RESSOURCE_DIR_ + "mapSpritesheets.png");
		URL mainMenu = getClass().getClassLoader().getResource(_RESSOURCE_DIR_ + "mainMenu.png");

		if (mapSpritesheets != null && mainMenu != null) {
			try {

				Image img = ImageIO.read(mapSpritesheets);
				Image mainMenuImg = ImageIO.read(mainMenu);

				// Prepare an Image object to be used by drawImage()

				// The img "clip" bounded by (scrX1, scrY2) and (scrX2, srcY2) is scaled and drawn from
   			// (destX1, destY1) to (destX2, destY2) on the display.

				int i = 0;
				int block = -2;
				int srcx1 = 0, srcx2 = 24;

				for(int y = 0; y < this.caseHeight; y++) {
					
					block++;

					for(int x = 0; x < this.caseWidth; x++) { 
						i++;

						if(block >= 1) {
							block--;
							srcx1 = 24*block; 
							srcx2 = 24+(24*block);
						} else {
							block++;
							srcx1 = 24*block; 
							srcx2 = 24+(24*block);
						}

						int dstx1 = 8+(24*x),
							 dsty1 = 0+(24*y),
							 dstx2 = 8+(24+(24*x)),
							 dsty2 = 24+(24*y),
							 srcy1 = 0,
							 srcy2 = 24;

			    		/*System.out.println("(Game.java:165) => " +
			    			"Executed: " + i + "\n" +
			    			"                   block: " + block + "\n" +
			    			"                   dstx1: " + dstx1 + "\n" +
			    			"                   dsty1: " + dsty1 + "\n" +
			    			"                   dstx2: " + dstx2 + "\n" +
			    			"                   dsty2: " + dsty2 + "\n" +
			    			"                   srcx1: " + srcx1 + "\n" +
			    			"                   srcy1: " + srcy1 + "\n" +
			    			"                   srcx2: " + srcx2 + "\n" +
			    			"                   srcy2: " + srcy2
			    		);*/

						g.drawImage(img, dstx1, dsty1, dstx2, dsty2, srcx1, srcy1, srcx2, srcy2, this);
					}
				}

				// g.drawImage(mainMenuImg, 8, 0, this.width, this.height, this);
 
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
		   System.err.println("(Game.java:129) => Couldn't find file: " + mapSpritesheets + " or " + mainMenu);
		}
	    
	}
	
}