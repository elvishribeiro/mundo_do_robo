package sensors;

import java.awt.Point;
import robo.*;

public class ProximitySensor extends Sensor{
	boolean top = false, 
			topRight = false, 
			right = false,
			bottomRight = false,
			bottom = false,
			bottomLeft = false,
			Left = false,
			topLeft = false;
	
	private Mapa mapa = Mapa.getInstance();
	private Robo robo = Robo.getInstance();
	private Ponto posicao = robo.getPosicao();
	
	public float pegaValor() {
		return valor;
	}
	
	private float mede() {
		if (!mapa.disponivel(posicao.acima())){
			top = true;
		}
		if (!mapa.disponivel(posicao.acimaDireita())){
			
		}
		if (!mapa.disponivel(posicao.direita())){
			
		}
		if (!mapa.disponivel(posicao.baixoDireita())){
			
		}
		if (!mapa.disponivel(posicao.baixo())){
			
		}
		if (!mapa.disponivel(posicao.baixoEsquerda())){
			
		}
		if (!mapa.disponivel(posicao.esquerda())){
			
		}
		if (!mapa.disponivel(posicao.acimaEsquerda())){
			
		}
		
		
	}
	
}
