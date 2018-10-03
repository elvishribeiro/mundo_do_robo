package sensors;

public class DirtSensor extends Sensor{
	private Mapa mapa = Mapa.getInstance();
	
	public float pegaValor() {
		valor = mapa.pegaValorSujeira();
		return valor;
	}
	
	
}
