package principal;

import java.util.Collections;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSpinner;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal {
	private static boolean seleciona = false; 
	public static void main (String args[]) {
		int tam = 0;

		//alocações
		SalaReal salaReal = SalaReal.getInstance();
		//preparaJanelaTamanho(salaReal);
		salaReal.aloca(7);
		
		Robo robo = Robo.getInstance();
		robo.aloca();
		//primmeiras coisas
		preparaTela();
		salaReal.sujaPrimeiro(1);
		
		
		
		
		
		//threads
		 try {
             Thread.sleep(2000);
         }catch(Exception ex) {
             return;
         }
		 salaReal.insereMultiplicador();
		 robo.primeiraVarrida();
		
		//salaReal.sujaPrimeiro(0.07);
		salaReal.start();
		robo.start();		
		//subidaDeEncosta();
		
		/*while(true) {
            try {
                Thread.sleep(1000/60);
            }catch(Exception ex) {
                return;
            }
            robo.anda();
        }*/
		
	}
	
	
	public static void preparaTela() {
		JFrame janela = new JFrame();
		Tela desenho = new Tela();
		
		janela.add(desenho);
		//janela.setPreferredSize(new Dimension(600, 600));
		janela.pack();
		janela.setVisible(true);
		janela.setResizable(true);
		janela.setLocationRelativeTo(null);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setName("O mundo do aspirador");
		desenho.start();
	}
	
	public static void preparaJanelaTamanho(SalaReal salaReal) {
		int tam;
		Boolean seleciona = new Boolean(false);
		JFrame janelaTamanho = new JFrame();
		JSpinner tamanho = new	JSpinner();
		JButton okBotao = new JButton();
		
		//tamanho.setBounds(30, 20, 30, 20);
		okBotao.setBounds(70, 20, 10, 20);
		tamanho.setBounds(0, 0, 30, 30);
		okBotao.setText("OK");
		okBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTamanhoSala(Integer.parseInt(tamanho.getValue() + ""), salaReal);
				janelaTamanho.dispose();
			}
		});
		
		
	
		janelaTamanho.setLocationRelativeTo(null);
		janelaTamanho.setSize(new Dimension(800, 600));
		janelaTamanho.add(tamanho);
		janelaTamanho.add(okBotao);
		janelaTamanho.pack();
		janelaTamanho.setVisible(true);
		janelaTamanho.setResizable(true);
		
		janelaTamanho.setName("O mundo do aspirador");
		
		//return Integer.parseInt(tamanho.getValue() + "");
	}
	
	static private void setTamanhoSala(int tam, SalaReal salaReal) {
		seleciona = true;
		salaReal.aloca(tam);
	}
	public static void subidaDeEncosta() {
		Random r = new Random();
		Robo robo = Robo.getInstance();
		SalaReal salaReal = SalaReal.getInstance();
		SalaVirtual salaVirtual = robo.getSalaVirtual();
		try {
			Thread.sleep(2000);
		}catch(Exception ex) {}
		
		salaReal.limpa(robo.getPosicao());
		
		double tempo = System.currentTimeMillis();
		double sujeiraColetada = 0;
		int intervalo = 10;
		int distanciaPercorrida=0;
		
		while(true) {
			if (System.currentTimeMillis()-tempo >= 10000) {
				System.out.println("Tempo: " + intervalo +"s\n"
						+"sujeira coletada: " + sujeiraColetada + 
						"\ndistancia percorrida: " + distanciaPercorrida + 
						"\nCR: " + sujeiraColetada/distanciaPercorrida+"\n");
				tempo = System.currentTimeMillis();
				intervalo+=10;
			}
			try {
				Thread.sleep(50);
				
				int id = Collections.max(salaVirtual.getVizinhos(robo.getPosicao()));
				
				if (salaReal.getSujeira(id) == 0) {
					id = salaVirtual.getVizinhos(robo.getPosicao()).get(r.nextInt(salaVirtual.getVizinhos(id).size()-1));
				}
				sujeiraColetada = salaReal.limpa(id);
				robo.setPosicao(id);
				distanciaPercorrida++;
			}catch(Exception ex) {
				return;
			}
		}
	}
	
	static public void restart() {
		
		SalaReal salaReal = SalaReal.getInstance();
		salaReal.pause();
		salaReal.sujaPrimeiro(1);
		salaReal.insereMultiplicador();
		
		Robo robo = Robo.getInstance();
		robo.pause();
		robo.primeiraVarrida();
		
	
		salaReal.free();
		robo.free();
	}
}
