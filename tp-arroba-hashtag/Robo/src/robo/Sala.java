package robo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import robo.Ponto;
import robo.Quadrante;

//Singleton
public class Sala {
	static int LARGURA = 10;
	static int ALTURA = 10;
	
	private List<List<Quadrante>> quadrantes;
	
	private static Sala instance = new Sala ();
	
	private Sala() {
		quadrantes = new ArrayList<List<Quadrante>>();
		for (int i = 0; i < LARGURA; i++) {
			quadrantes.add(new ArrayList<Quadrante>());
			for (int j = 0; j < ALTURA; j++)
				quadrantes.get(i).add(new Quadrante(new Ponto(i,j)));
		}
	}
        
        public int suja (){
            
            return 0;
        }
        
        
	public static Sala getInstance() {
		return instance;
	}
	
        public int getTamanhoSala(){
            return LARGURA*ALTURA;
        }
	public boolean disponivel(Point ponto) {
		return true;
	}
}
