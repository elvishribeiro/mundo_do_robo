package principal;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.util.List;

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
		AffineTransform af = new AffineTransform();
		af.rotate(-3.1415/2);
		Graphics2D g = (Graphics2D)buffer.getDrawGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		List<Integer> vizinhos;
		
		g.setColor(Color.ORANGE);
		g.fillRect(robo.getPosicao().x*blockSize + 7, 
				robo.getPosicao().y*blockSize + 7,
				blockSize - 14,blockSize - 14);
		int idAtual = (Sala.ALTURA*robo.getPosicao().y + robo.getPosicao().x);
		vizinhos = sala.getVizinhos(idAtual);
		
		g.setColor(Color.BLACK);
		for(int i = 0; i < Sala.LARGURA; i++) {
			for (int j = 0; j < Sala.ALTURA; j++) {
				g.setColor(Color.BLACK);
				g.drawRect(i*blockSize, j*blockSize, 
						blockSize, blockSize);
				idAtual = (Sala.ALTURA-j)*Sala.ALTURA - (Sala.LARGURA -i);
				if(vizinhos.contains(Sala.ALTURA*i + j)) {
					g.fillRect(i*blockSize, blockSize*j,
							blockSize, blockSize);
				
				}else{
					g.drawString(""+(idAtual), 
							i*blockSize + 10, j*blockSize + 10);
				}
					
				
			}
		}
		
		
		g.dispose();
		buffer.show();
		
		
	}
	
}
	