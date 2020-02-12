package principal;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;

class QuadranteVirtual {
    	double alpha;
       	private double porcentagem;
        private double mediaMaior;
        private double mediaMenor;
        private int    coletasMaiores;
        private int    coletasMenores;
        
        public QuadranteVirtual(){
            alpha = 0; 
            porcentagem = 0;
            mediaMaior = 0;
            mediaMenor = 0;
            coletasMaiores = 0;
            coletasMenores = 0;    
        }
        
        public double getPorcentagem(){
            return porcentagem;
        }
        
        public double getAlpha() {
        	return alpha;
        }
        
        public void incrementaPorcentagem(){
            porcentagem = porcentagem + alpha;
        }
        
        private void setAlpha(double sujeira){
            double calcAlpha = 6/sujeira;
            calcAlpha = 1/calcAlpha;
            
            calcAlpha = calcAlpha/10;
            alpha = calcAlpha*0.08;

        }
        
        public void sujeiraColetada(double sujeira){
            if(porcentagem == 0){
                setAlpha(sujeira);
                return;
            }        
            
            double maximo;
            double minimo;
            
            maximo = 0.6*(porcentagem+0.15);
            minimo = 0.6*(porcentagem-0.15);
            
            if(sujeira>maximo){
                coletasMaiores++;
                mediaMaior = mediaMaior + sujeira;
                        
                coletasMenores = 0;
                mediaMenor = 0;
                
                if(coletasMaiores == 2){
                    mediaMaior = mediaMaior/2;
                    setAlpha(mediaMaior);
                    
                    mediaMaior = 0;
                    coletasMaiores = 0;
                }
            }
            else if(sujeira<minimo){
                coletasMenores++;
                mediaMenor = mediaMenor + sujeira;
                        
                coletasMaiores = 0;
                mediaMaior = 0;
                
                if(coletasMenores == 2){
                    mediaMenor = mediaMenor/2;
                    setAlpha(mediaMenor);
                    
                    mediaMenor = 0;
                    coletasMenores = 0;
                }
            }
            else{
                mediaMaior = 0;
                mediaMenor = 0;
                
                coletasMaiores = 0;
                coletasMenores = 0;
            } 
            porcentagem = 0;
        }    
}

//Singleton
public class SalaVirtual extends Sala{

    private List<QuadranteVirtual> quadrantes;
    
    //construtores
    
    public SalaVirtual(Integer tamanho) {
    	this.tamanho = tamanho;
    	if (tamanho == -1)
    		JOptionPane.showMessageDialog(null, "Nao ha sala real", "Erro!", 2);
    	
        posID = new HashMap<Ponto,Integer>();
       
        quadrantes = new ArrayList<QuadranteVirtual>();
        
        for (int i = 0; i < tamanho*tamanho; i++)
            quadrantes.add(new QuadranteVirtual());
            
        
    }
    public void atualizaPorcentagens(){
        int i;
        
        for(i=0; i<tamanho*tamanho; i++ )
            quadrantes.get(i).incrementaPorcentagem();
    }
        
    
    public List<Integer> getVizinhos(int id){
    	List<Integer> vizinhos = new ArrayList<Integer>();
    	
        if ((id + tamanho) < quadrantes.size())	//acima
            vizinhos.add(id + tamanho);
        
        if ((id + tamanho) < quadrantes.size() && (id + 1)%tamanho != 0) 	//acimaDireita
            vizinhos.add(id + tamanho + 1);
        
        if ((id + 1)%tamanho != 0)		//direita
            vizinhos.add(id + 1);
        
        if ((id - tamanho) >= 0)		//abaixo
           vizinhos.add(id - tamanho);
        
        if ((id - tamanho >= 0) && ((id + 1)%tamanho != 0))		//abaixoDireita
            vizinhos.add(id - tamanho + 1);

        if ((id - tamanho >= 0) && (id + 1)%tamanho != 1)		//abaixoEsquerda
            vizinhos.add(id - tamanho - 1);
            
        if ((id + 1) % tamanho != 1)			//esquerda
            vizinhos.add(id - 1);
        
        if (((id + tamanho) < quadrantes.size()) && ((id + 1) % tamanho != 1))	//esquerdaAcima
            vizinhos.add(id + tamanho - 1);
        
        return vizinhos;
    }
    
    
    
    //getters e setters
    
    public double getPorcentagem(Integer id) {
    	return quadrantes.get(id).getPorcentagem();
    }
    
    public void sujeiraColetada(int id, double sujeira) {
    	//quadrantes.get(id).porcentagem = sujeira/10;
    	quadrantes.get(id).sujeiraColetada(sujeira);
    }
    

  
    public double getAlpha(int id){
        return quadrantes.get(id).getAlpha();
    }

}



