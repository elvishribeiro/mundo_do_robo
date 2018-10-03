package sensors;

public abstract class Sensor {
	float valor = 0;
	
	/** 
	Este método retorna o valor medido pelo sensor
	*/
	public float pegaValor() { 
		return valor;
	}
	
}
