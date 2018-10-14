package principal;

import java.util.Collections;
import java.util.Random;

import javax.swing.JFrame;

public class Principal {
	public static void main (String args[]) {
		Robo robo = Robo.getInstance();
		Sala sala = Sala.getInstance();
		sala.sujaPrimeiro();
		
		preparaTela();
		
		
		while(true) {
            try {
                Thread.sleep(1000/60);
            }catch(Exception ex) {
                return;
            }
            robo.anda();
        }
		
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
	
	public static void andaAToa(Robo robo) {
		Random r = new Random();
		Sala sala = Sala.getInstance();
		
		try {
			Thread.sleep(2000);
		}catch(Exception ex) {}
		sala.quadFromID(0).setSujeira(0);
		while(true) {
			try {
				Thread.sleep(500);
				int id = robo.getPosicao().y*Sala.LARGURA+ robo.getPosicao().x;
				
				id = Collections.max(sala.quadsFromIDs(sala.getVizinhos(id))).getID();
				
				if (sala.quadFromID(id).getSujeira() == 0) {
					id = robo.getPosicao().y*Sala.LARGURA+ robo.getPosicao().x;
					id = sala.getVizinhos(id).get(r.nextInt(sala.getVizinhos(id).size()-1));
				}
				sala.quadFromID(id).setSujeira(0);
				robo.setPosicao(sala.quadFromID(id).getPonto());
				
			}catch(Exception ex) {
				return;
			}
		}
	}
}
