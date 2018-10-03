package robo;

import robo.Sala;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public class Tela extends Canvas implements Runnable{
	private int blockSize = 60;
	private int largura, altura;
	Sala sala;
	Robo robo;
	
	private Thread thread;
	
	public Tela () {
		this.setPreferredSize(new Dimension(600,600));
	}

	public void start() {
		robo = Robo.getInstance();
		sala = Sala.getInstance();
		thread = new Thread(this);
		thread.start();

	}
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000/60);
			}catch(Exception ex) {
				return;
			}
			draw();
		}
	}
	
	public void draw() {
		BufferStrategy buffer = getBufferStrategy();
		if (buffer == null) {
			createBufferStrategy(3);
			return;
		}		
		Graphics2D g = (Graphics2D)buffer.getDrawGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		for(int i = 0; i < Sala.LARGURA; i++) {
			for (int j = 0; j < Sala.ALTURA; j++) {
				g.setColor(Color.BLACK);
				g.drawRect(i*blockSize, j*blockSize, 
						blockSize, blockSize);
			}
		}
		
		g.setColor(Color.ORANGE);
		g.fillRect(robo.getPosicao().x*blockSize + 7, 
				robo.getPosicao().y*blockSize + 7,
				blockSize - 14,blockSize - 14);
		g.dispose();
		buffer.show();
		
		
	}
	
}
	