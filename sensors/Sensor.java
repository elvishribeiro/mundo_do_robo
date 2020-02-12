package sensors;

public abstract class Sensor {
	boolean valor = false;
	/** 
	Este método retorna o valor medido pelo sensor
	*/
	public boolean pegaValor(int idPosicao) { 
		return valor;
	}
	
}
