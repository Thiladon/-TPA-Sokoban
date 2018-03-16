package sample;

import javax.swing.*;
import java.awt.*;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1550691097823471818L;
	private String name = "Game";
	private JFrame parent;
	private Thread thread;
	private Boolean running = false;
	

	public String getName() {
		return name;
	}

	public Game(JFrame parent) {
		this.parent = parent;
		setBackground(Color.black);
		setSize(parent.getContentPane().getSize().width, parent.getContentPane().getSize().height);
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
				this.tick();
				delta--;
			}
			if (running) {
				this.render();
			}
			loops++;

			if(System.currentTimeMillis() - nextGameTick > 1000) {
				nextGameTick += 1000;
				System.out.println("FPS: " + loops);
				System.console().writer().println("FPS: " + loops);
				loops = 0;
			}
		}
		stop(); 

	}

	private void tick() {

	}

	private void render() {

		// BufferStrategy bs = this.parent.getBufferStrategy();

		BufferStrategy b = null;
		
		/* if(bs == null) {
			return;
		}

		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);

		g.dispose();
		bs.show(); */
	}

	/* 
		public void paint(Graphics g) {
			g.setColor(Color.white);
			g.drawRect(100, 250, 100, 200);
		}
	*/
}