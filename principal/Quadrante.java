package principal;

public class Quadrante implements Comparable<Quadrante> {
	static int QUADRANTE_QTD = 0;
        
	private int id;
        
	private double alpha;
        private double sujeiraEsperada;
        private double porcentagem;
        private double mediaMaior;
        private double mediaMenor;
        private int    coletasMaiores;
        private int    coletasMenores;
        private float sujeira;
        private Ponto coordenadas;
	
	public Quadrante(Ponto coordenadas) {
		id = QUADRANTE_QTD++;
		this.coordenadas = coordenadas;
                
                this.porcentagem = 0;
                this.sujeiraEsperada = 0;
                this.alpha = 0;
                
                this.coletasMaiores = 0;
                this.coletasMenores = 0;
                this.sujeira = 0;
	}
	
        public void sujeiraColetada(double sujeira){
            if(porcentagem == 0){
                alpha = sujeira/10;
                sujeiraEsperada = 6;
                
                return;
            }
            
            double sujeiraMaximo = sujeiraEsperada*1.15;
            double sujeiraMinimo = sujeiraEsperada*0.85;
            

            if(sujeira<sujeiraMinimo){
                mediaMenor = mediaMenor + sujeira;
                mediaMaior = 0;
                
                coletasMenores++;
                coletasMaiores = 0;
            }
            else if(sujeira>sujeiraMaximo){
                mediaMaior = mediaMaior + sujeira;
                mediaMenor = 0;
                
                coletasMaiores++;
                coletasMenores = 0;
            }
            else{
                coletasMaiores = 0;
                coletasMenores = 0;
                
                porcentagem = 0;
                return;
            }
            
            if(coletasMaiores == 3){                
                mediaMaior = mediaMaior/3;
                mediaMaior = mediaMaior/sujeiraEsperada;
                //sujeiraEsperada = temp;
                
                alpha = alpha*mediaMaior;
                
                
                mediaMaior = 0;
                coletasMaiores = 0;
            }
            if(coletasMenores == 3){                 
                mediaMenor = mediaMenor/3;                
                mediaMenor = mediaMenor/sujeiraEsperada;
                //sujeiraEsperada = temp;
                
                //System.out.printf("%f  ",alpha);
                alpha = alpha*mediaMenor;
                //System.out.printf("%f\n",alpha);
                
                mediaMenor = 0;
                coletasMenores = 0;
            }
            
            porcentagem = 0;
        }
        
        public double getPorcentagem(){
            return porcentagem;
        }
        
        public void incrementaPorcentagem(){
            porcentagem = porcentagem + alpha;
        }
        
        public double getAlhpa(){
            return alpha;
        }
        
        public Ponto getPonto() {
        	return coordenadas;
        }
        
         void setSujeira(float sujeira) {
        	this.sujeira = sujeira;
        }
        
        public float getSujeira() {
        	return this.sujeira;
        }
        
        public int getID() {
        	return id;
        }

		@Override
		public int compareTo(Quadrante q) {
			return (int)Float.compare(this.sujeira, q.getSujeira());
		}
        
        
        
        
}