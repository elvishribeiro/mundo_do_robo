package principal;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Singleton
public class Sala {
	static int LARGURA = 3;
	static int ALTURA = 3;
	
	private List<List<Quadrante>> quadrantes;
	
	private static Sala instance = new Sala ();
	
	private Sala() {
		quadrantes = new ArrayList<List<Quadrante>>();
		for (int i = 0; i < LARGURA; i++) {
			quadrantes.add(new ArrayList<Quadrante>());
			for (int j = 0; j < ALTURA; j++)
				quadrantes.get(i).add(new Quadrante(new Ponto(i,j)));
		}
	}
	
	public static Sala getInstance() {
		return instance;
	}
	
	public boolean disponivel(Ponto ponto) {
		return true;
	}
	
	public List<Integer> getVizinhos(int id){
		List<Integer> vizinhos = new ArrayList<Integer>();
		Quadrante q = this.quadFromID(id);
		Ponto p = q.getPonto();
		
		if(p.acima().valido(ALTURA, LARGURA))
			this.addVizinho(vizinhos, p.acima());

		if(p.acimaDireita().valido(ALTURA, LARGURA))
			this.addVizinho(vizinhos, p.acimaDireita());
		
		if(p.direita().valido(ALTURA, LARGURA))
			this.addVizinho(vizinhos, p.direita());
		
		if(p.baixoDireita().valido(ALTURA, LARGURA))
			this.addVizinho(vizinhos, p.baixoDireita());
		
		if(p.baixo().valido(ALTURA, LARGURA))
			this.addVizinho(vizinhos, p.baixo());
		
		if(p.baixoEsquerda().valido(ALTURA, LARGURA))
			this.addVizinho(vizinhos, p.baixoEsquerda());

		if(p.esquerda().valido(ALTURA, LARGURA))
			this.addVizinho(vizinhos, p.esquerda());
		
		if(p.acimaEsquerda().valido(ALTURA, LARGURA))
			this.addVizinho(vizinhos, p.acimaEsquerda());
		
		return vizinhos;
	}
	
	private void addVizinho(List<Integer> vizinhos, Ponto p) {
		vizinhos.add(p.y * ALTURA + p.x);
	}
	
	public double getPorcentagem (int ID) {
		return this.quadFromID(ID).getPorcentagem();
	}
	
	public Quadrante quadFromID(int ID) {
		
		return this.quadrantes.get((int)ID/LARGURA).get(ID%LARGURA);
	}
	
	public List<Quadrante> quadsFromIDs(List<Integer> ids){
		List<Quadrante> quadrantes = new ArrayList<Quadrante>();
		for (int i : ids) {
			quadrantes.add(quadFromID(i));
		}
		return quadrantes;
	}
	
	
	public List<List<Quadrante>> getQuadrantes(){
		return quadrantes;
	}
	
	public int sujaPrimeiro (){
        Random r = new Random();
      
        float sujeira;
        
         for (int i = 0; i < LARGURA; i++) {
              for (int j = 0; j < ALTURA; j++){
                  sujeira = r.nextFloat();
                  quadrantes.get(i).get(j).setSujeira(sujeira);
                  quadrantes.get(i).get(j).sujeiraColetada(sujeira);
              }
         }
         return 0;
     }
	
        public void atualizaPorcentagens(){
            for (int i = 0; i < LARGURA; i++) {
              for (int j = 0; j < ALTURA; j++){
                  quadrantes.get(i).get(j).incrementaPorcentagem();
              }
         }
        }
        
	static public int getAltura() {
		return ALTURA;
	}
	
	static public int idFromPonto(Ponto ponto) {
		return ponto.y*ALTURA + ponto.x;
	}
	
	static public Ponto PontoFromId(int id) {
		return new Ponto((int)id/LARGURA,id%LARGURA);
	}

	
	
}
