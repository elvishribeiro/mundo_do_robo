package principal;

public class Ponto{
	int x,y;
	
	public Ponto(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Ponto acima () {
		return new Ponto(x, y+1);
	}
	
	public Ponto acimaDireita() {
		return new Ponto(x+1, y+1);
	}
	
	public Ponto direita() {
		return new Ponto(x+1, y);
	}
	
	public Ponto baixoDireita() {
		return new Ponto(x+1,y-1);
	}
	
	public Ponto baixo() {
		return new Ponto(x,y-1);
	}
	
	public Ponto baixoEsquerda() {
		return new Ponto(x-1, y-1);
	}
	
	public Ponto esquerda() {
		return new Ponto(x-1, y);
	}
	
	public Ponto acimaEsquerda() {
		return new Ponto(x-1, y+1);
	}
	
	public boolean valido(int altura, int largura) {
		return x >= 0 && x < largura && y >= 0 && y < altura;
	}
}
