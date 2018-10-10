package principal;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

//Singleton
public class Sala {
	static int LARGURA = 5;
	static int ALTURA = 5;
	
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
	
	public static Sala getInstance() {
		return instance;
	}
	
	public boolean disponivel(Point ponto) {
		return true;
	}
	
	public List<Integer> getVizinhos(int id){
		List<Integer> vizinhos = new ArrayList<Integer>();
		Quadrante q = this.quadFromID(id);
		Ponto p = q.getPonto();
		System.out.println(p.x + " " + p.y);
		
		System.out.println(p.acima().x + " " + p.acima().y);
		if(p.acima().valido(ALTURA, LARGURA))
			this.addVizinho(vizinhos, p.acima());

		System.out.println(p.acimaDireita().x + " " + p.acimaDireita().y);
		if(p.acimaDireita().valido(ALTURA, LARGURA))
			this.addVizinho(vizinhos, p.acimaDireita());
		
		System.out.println(p.direita().x + " " + p.direita().y);
		if(p.direita().valido(ALTURA, LARGURA))
			this.addVizinho(vizinhos, p.direita());
		
		System.out.println(p.baixoDireita().x + " " + p.baixoDireita().y);
		if(p.baixoDireita().valido(ALTURA, LARGURA))
			this.addVizinho(vizinhos, p.baixoDireita());
		
		System.out.println(p.baixo().x + " " + p.baixo().y);
		if(p.baixo().valido(ALTURA, LARGURA))
			this.addVizinho(vizinhos, p.baixo());
		
		System.out.println(p.baixoEsquerda().x + " " + p.baixoEsquerda().y);
		if(p.baixoEsquerda().valido(ALTURA, LARGURA))
			this.addVizinho(vizinhos, p.baixoEsquerda());

		System.out.println(p.esquerda().x + " " + p.esquerda().y);
		if(p.esquerda().valido(ALTURA, LARGURA))
			this.addVizinho(vizinhos, p.esquerda());
		
		System.out.println(p.acimaEsquerda().x + " " + p.acimaEsquerda().y);
		if(p.acimaEsquerda().valido(ALTURA, LARGURA))
			this.addVizinho(vizinhos, p.acimaEsquerda());
		
		return vizinhos;
	}
	
	private void addVizinho(List<Integer> vizinhos, Ponto p) {
		vizinhos.add(p.y * ALTURA + p.x);
	}
	
	public double getPorcentagem (int ID) {
		return this.quadFromID(ID).getPorcentagem();
	}
	
	public Quadrante quadFromID(int ID) {
		
		return this.quadrantes.get((int)ID/LARGURA).get(ID%LARGURA);
	}
	
	
}
