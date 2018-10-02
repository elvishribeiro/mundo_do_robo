package principal;

import sensors.*;

//Singleton


public class Robo {
	
	private float SujeiraColetada=0;
	private Ponto posicao = new Ponto();
	private Sensor proximidade = new ProximitySensor();
	private Sensor sujeira = new DirtSensor();
	
	static private Robo instance = new Robo();
	
	private Robo() {
		
	}
	
	static public Robo getInstance() {
		return instance;
	}
	
	public Ponto getPosicao() {
		return posicao;
	}
}
