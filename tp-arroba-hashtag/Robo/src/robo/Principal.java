package robo;

import robo.Sala;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.JFrame;

public class Principal {
	public static void main (String args[]) {
		Robo robo = Robo.getInstance();
		Sala sala = Sala.getInstance();
		
		preparaTela();
		
		andaAToa(robo);
	}
	
	
	public static void preparaTela() {
		JFrame janela = new JFrame();
		Tela desenho = new Tela();
		
		janela.add(desenho);
		//janela.setPreferredSize(new Dimension(600, 600));
		janela.pack();
		janela.setVisible(true);
		janela.setResizable(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setName("O mundo do aspirador");
		desenho.start();
	}
	
	public static void andaAToa(Robo robo) {
		Random r = new Random();
		while(true) {
			try {
				Thread.sleep(800);
				robo.setPosicao(new Ponto(r.nextInt(10), r.nextInt(10)));
			}catch(Exception ex) {
				return;
			}
		}
	}
}
