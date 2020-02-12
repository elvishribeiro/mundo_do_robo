package sensors;
import principal.Sala;
import principal.SalaReal;

public class DirtSensor extends Sensor{
	private SalaReal salaReal = SalaReal.getInstance();
	int sensibilidade = 6;
	
	public boolean sujo(int idPosicao) {
		valor = false;
		if (salaReal.getSujeira(idPosicao) >= sensibilidade)
			valor = true;
		
		return valor;
	}
	
	
}
