package principal;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.util.List;

public class Tela extends Canvas implements Runnable{
	private int blockSize = 60;
	private int largura, altura;
	Graphics2D g;
	
	SalaVirtual salaVirtual;
	SalaReal salaReal;
	Robo robo;
	
	Input input;
	
	private Thread thread;
	
	public Tela () {
		input = Input.getInstance();
		this.setPreferredSize(new Dimension(blockSize*Sala.getTamanho()*2,
				blockSize*Sala.getTamanho()+50));
		this.addKeyListener(input);
	}

	public void start() {
		robo = Robo.getInstance();
		salaVirtual = robo.getSalaVirtual();
		salaReal = SalaReal.getInstance();
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
			update();
		}
	}
	
	public void draw() {
		BufferStrategy buffer = getBufferStrategy();
		if (buffer == null) {
			createBufferStrategy(3);
			return;
		}		

		g = (Graphics2D)buffer.getDrawGraphics();
		
		
		preencheQuadrado(0, 0, this.getWidth(), Color.WHITE);
		
		g.setColor(Color.black);
		g.fillRect(Sala.getTamanho()*blockSize+2, 0, 1, Sala.getTamanho()*blockSize+50);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 17)); 
		
		g.drawString("Sala Virtual", Sala.getTamanho()*blockSize/2-50, 40);
		g.drawString("Sala Real", Sala.getTamanho()*blockSize/2 + Sala.getTamanho()*blockSize+5-50, 40);
		g.setFont(new Font("Arial", Font.PLAIN, 12));
		g.translate(0, 50);
		
		desenhaSalaVirtual();
		
		desenhaSalaReal();
		
		
		g.dispose();
		buffer.show();	
	}
	
	
	private void desenhaSalaVirtual() {
		int idAtual;
		float porcentagem;
		
		for(int i = 0; i < Sala.getTamanho(); i++) {
			for (int j = 0; j < Sala.getTamanho(); j++) {
				
				idAtual = j*Sala.getTamanho() + i;
				
				porcentagem = (float)salaVirtual.getPorcentagem(idAtual);
				//porcentagem = (float)salaReal.getSujeira(idAtual)*(float)25.5;
				
				preencheQuadrado(i*blockSize, j*blockSize, blockSize-1, 
						novaCorPorcentagem(porcentagem));
				
				desenhaQuadrado(i*blockSize, j*blockSize, blockSize, Color.black);
				
				g.setColor(Color.BLACK);
				String s = String.format("ID:%d", idAtual);
				g.drawString(s, i*blockSize + 10, j*blockSize+10);
				s = String.format("p: %.2f",salaVirtual.getPorcentagem(idAtual));
				g.drawString(s, i*blockSize + 10, j*blockSize+26);
				s = String.format("af:%.2f",salaVirtual.getAlpha(idAtual)*100);
				g.drawString(s, i*blockSize + 10, j*blockSize+42);
			}
		}
		desenhaRobo();
		
	}
	
	private void desenhaSalaReal() {
		g.translate(Sala.tamanho*blockSize+5, 0);
		int idAtual;
		float sujeira;
		
		for(int i = 0; i < Sala.getTamanho(); i++) {
			for (int j = 0; j < Sala.getTamanho(); j++) {
				
				idAtual = j*Sala.getTamanho() + i;
				
				sujeira = (float)salaReal.getSujeira(idAtual);
				//porcentagem = (float)salaReal.getSujeira(idAtual)*(float)25.5;
				
				preencheQuadrado(i*blockSize, j*blockSize, blockSize-1, 
						novaCorSujeira(sujeira));
				
				desenhaQuadrado(i*blockSize, j*blockSize, blockSize, Color.black);
				
				g.setColor(Color.BLACK);
				String s = String.format("ID:%d", idAtual);
				g.drawString(s, i*blockSize + 10, j*blockSize+10);
				s = String.format("s: %.2f",salaReal.getSujeira(idAtual));
				g.drawString(s, i*blockSize + 10, j*blockSize+26);
			}
		}
	}
	
	private void desenhaRobo() {
		g.setColor(Color.BLACK);
		g.setColor(Color.ORANGE);
		g.fillOval((robo.getPosicao()%Sala.getTamanho())*blockSize+5, 
				(robo.getPosicao()/Sala.getTamanho())*blockSize+ 5,
				blockSize - 10,blockSize - 10);
		g.setColor(Color.BLACK);
		g.setColor(Color.BLACK);
		String s = String.format("t:%.2f", robo.getSujeiraColetada());
		
		g.drawString(s, 
				(robo.getPosicao()%Sala.getTamanho())*blockSize+blockSize/4+2,
				(robo.getPosicao()/Sala.getTamanho())*blockSize+ 5 + blockSize/2);
	}
	
	private void update() {
		if (input.isKeyPressed("pause")) {
			salaReal.pause();
			robo.pause();
		}
		if(!input.isKeyPressed("pause")){
			salaReal.free();
			robo.free();
		}
		if(input.isKeyTyped("restart")) {
			Principal.restart();
		}
	}
	
	//Métodos de desenho
	
	private void desenhaQuadrado(int x, int y, int tamanho, Color cor) {
		g.setColor(cor);
		g.drawRect(x*blockSize, y*blockSize, tamanho, tamanho);
	}
	
	private void preencheQuadrado(int x, int y, int tamanho, Color cor) {
		g.setColor(cor);
		g.fillRect(x, y, tamanho, tamanho);
	}
	
	
	private Color novaCorPorcentagem(float valor) {
		valor = valor*255*0.8F;
		float cor = 255-valor;
		if (cor < 0) cor = 0;
		
		return new Color((int)Float.min(cor,255), 
				(int)Float.min(cor,255), (int)Float.min(cor,255));
	}
	
	private Color novaCorSujeira(float valor) {
		valor = valor*25.5F*0.8F;
                float cor = 255-valor;
		if (cor < 0) cor = 0;
		return new Color(
				(int)(Float.min(cor +38,255)),
				(int)(Float.min(cor+7,255)),
				(int)(Float.min(cor,255)));
	}
}
	