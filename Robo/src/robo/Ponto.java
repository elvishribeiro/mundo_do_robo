package principal;

import java.awt.Point;

public class Ponto extends Point{
	
	public Ponto(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point acima () {
		return new Point(x+1, y);
	}
	
	public Point acimaDireita() {
		return new Point(x+1, y+1);
	}
	
	public Point direita() {
		return new Point(x, y+1);
	}
	
	public Point baixoDireita() {
		return new Point(x-1,y+1);
	}
	
	public Point baixo() {
		return new Point(x-1,y);
	}
	
	public Point baixoEsquerda() {
		return new Point(x-1, y-1);
	}
	
	public Point esquerda() {
		return new Point(x, y-1);
	}
	
	public Point acimaEsquerda() {
		return new Point(x-1, y-1);
	}
}
