package principal;

public class QuadranteReal extends Quadrante{
	private Ponto ponto;
	private float sujeira;
	private float multSujeira;
	
	
	//construtores
	public QuadranteReal(Ponto ponto) {
		this.ponto = ponto;
		this.sujeira = 0;
	}
	
	
	//métodos
	
	
	//getters e setters
	public void setSujeira(float sujeira) {
		this.sujeira = sujeira;
	}
	
	public float getSujeira() {
		return sujeira;
	}
	
	public void setMultSujeira(float multSujeira) {
		this.multSujeira = multSujeira;
	}
	
	public float getMultSujeira() {
		return multSujeira;
	}
	
}
