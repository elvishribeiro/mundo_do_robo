package principal;

public class Quadrante {
	static int QUADRANTE_QTD = 0;
	private int id;
	Ponto coordenadas;
	
	public Quadrante(Ponto coordenadas) {
		id = QUADRANTE_QTD++;
		this.coordenadas = coordenadas;
	}
	
}
