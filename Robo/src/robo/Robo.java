package principal;

import sensors.*;

//Singleton


public class Robo {
	
	private float SujeiraColetada;
	private Ponto posicao;
	private Sensor proximidade;
	private Sensor sujeira;
	
	static private Robo instance = new Robo();
	
	private Robo() {

		SujeiraColetada=0;
		setPosicao(new Ponto(0,0));
		proximidade = new ProximitySensor();
		sujeira = new DirtSensor();
		
	}
	
	static public Robo getInstance() {
		return instance;
	}
	
	public Ponto getPosicao() {
		return posicao;
	}

	public void setPosicao(Ponto posicao) {
		this.posicao = posicao;
	}
}
