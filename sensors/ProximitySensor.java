package sensors;

import java.awt.Point;
import principal.*;

public class ProximitySensor extends Sensor{
	boolean top = false, 
			topRight = false, 
			right = false,
			bottomRight = false,
			bottom = false,
			bottomLeft = false,
			left = false,
			topLeft = false;
	
	private Sala sala;
	private Robo robo;
	private Ponto posicao;
	
	public ProximitySensor() {
		sala = Sala.getInstance();
		robo = Robo.getInstance();
		//posicao = robo.getPosicao();
	}
	
	public float pegaValor() {
		return valor;
	}
	
	private void mede() {
		posicao = robo.getPosicao();
		top = !sala.disponivel(posicao.acima());
		topRight = !sala.disponivel(posicao.acimaDireita());
		right = !sala.disponivel(posicao.direita());
		bottomRight = !sala.disponivel(posicao.baixoDireita());
		bottom = !sala.disponivel(posicao.baixo());
		bottomLeft = !sala.disponivel(posicao.baixoEsquerda());
		left = !sala.disponivel(posicao.esquerda());
		topLeft = !sala.disponivel(posicao.acimaEsquerda());
	}
	
}
