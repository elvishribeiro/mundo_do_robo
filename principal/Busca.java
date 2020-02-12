/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;


class Grafo implements Comparable<Grafo>{
        private int id;
        private double peso;
        
        public int getID() {
            return id;
        }
        public double getPeso() {
            return peso;
        }
        public Grafo(int id, Double peso) {
            this.id = id;
            this.peso = peso;
        }
        
        public void setPeso(double peso) {
            this.peso = peso;
        }
        
        public int compareTo(Grafo compareGrafo) {
            double compareValor = compareGrafo.peso;
            if (this.peso <= compareValor)
                return -1;
            if (this.peso > compareValor)
                return 1;
            return 0;
        }   
        
        public boolean equals (Grafo compareGrafo) {
            return this.id == compareGrafo.getID();
        }
    }

public class Busca{
    List<List<Integer>> grafoIDs   = new ArrayList<List<Integer>>();
    List<List<Double>>  grafoPesos = new ArrayList<List<Double>>();
    
    List<List<Grafo>> grafo;
    
    SalaVirtual salaVirtual;

    
    int tamanhoGrafo;
    
    public Busca(SalaVirtual salaVirtual){
        int i;
        this.salaVirtual = salaVirtual;
       
        tamanhoGrafo = Sala.getTamanho()*Sala.getTamanho();
        
        List<Integer> vizinhos = new ArrayList<Integer>();
        grafo = new ArrayList<List<Grafo>>();
        
        
        for(i=0; i<tamanhoGrafo; i++){
        	vizinhos = salaVirtual.getVizinhos(i);
           
            grafo.add(new ArrayList<Grafo>());            
            
            for(Integer temp: vizinhos){
                Grafo g = new Grafo(temp, 0.0);
                grafo.get(i).add(g);
            }            
        }        
    }
    
    private double distanciaEuclidiana(int id, int id2){
        int x1;
        int y1;
        int x2;
        int y2;
        
        x1 = id/Sala.getTamanho();
        y1 = id%Sala.getTamanho();
        
        x2 = id2/Sala.getTamanho();
        y2 = id2%Sala.getTamanho();
        
        double d= Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        
        return d;
    }
    
    private int atualizaDados(int saida){
        double temp;
        double maior = 0.6;
        int chegada = -1;
        
        int i;
        for(i=0; i<tamanhoGrafo; i++){
            for(Grafo g: grafo.get(i)){ 
            	
                temp = salaVirtual.getPorcentagem(g.getID());
                if(temp>= maior){
                    chegada = g.getID();
                    maior = temp;
                }
                
                if(distanciaEuclidiana(saida,g.getID())<10e-8)
                    continue;
                
                
                temp = (1/(100*temp))+0.3*(distanciaEuclidiana(saida,g.getID()));                
                
                g.setPeso(temp);
                
            }     
            
        } 
        
        return chegada;
    }
    
    public List<Integer> dijkstra(int saida){
        
        boolean visitados[]= new boolean[tamanhoGrafo];
        double dist      []= new double[tamanhoGrafo];
        int    pred      []= new int[tamanhoGrafo];
        
        double p;
        
        int i;
        int j;
        int k;
        int chegada;
        
        List<Integer> resultado = new ArrayList<Integer>();
        
        Queue<Grafo> menorAberto = new PriorityQueue<Grafo>();
        
        chegada = atualizaDados(saida);
        
        if(chegada == -1)
            chegada = 0;        
        
        for(i=0; i<tamanhoGrafo; i++){
            pred[i] = -1;
            visitados[i] = false;
            dist[i] = 10e20;
        }
        
        dist[saida] = 0;
        visitados[saida] = true;
        
        menorAberto.add(new Grafo(saida,0.0));
        while(true){
            if(menorAberto.isEmpty())
                break;
            
            i = menorAberto.poll().getID();
            visitados[i] = true;
            
            List<Grafo> vizinhos =  grafo.get(i);
            
            j=0;
            for(Grafo g: vizinhos){
                k = g.getID();
                
                if(visitados[k])
                    continue;
                
                p = Math.min(dist[k], dist[i] + grafo.get(i).get(j).getPeso());
                
                if(p<dist[k]){
                    dist[k] = p;
                    menorAberto.add(new Grafo(k,p));
                    pred[k] = i;
                }
            }
        }
        
        i=chegada;
        while(true){
            if(i==-1)
                break;
            
            resultado.add(0,i);
            i = pred[i];
        }
        
        /*if(!salaVirtual.getVizinhos(saida).contains(resultado.get(1))){
            
        }*/
        
        
       // for(Integer a: resultado)
       //     System.out.printf("%d ", a);
       // System.out.printf("\n");
        
        return resultado;
    }
    
    public List<Integer> subidaDeEncosta(int saida) {
    	Random r = new Random();
       	SalaReal salaReal = SalaReal.getInstance();
       	List<Integer> resultado = new ArrayList<Integer>();
				
		int id = Collections.max(grafo.get(saida)).getID();
		
		double temp;
        double maior = 0.6;
        int chegada = -1;
		for(Grafo g: grafo.get(saida)){ 
        	
            temp = salaVirtual.getPorcentagem(g.getID());
            if(temp>= maior){
                chegada = g.getID();
                maior = temp;
            }
            
        }   
		id = chegada;
		
		if (Collections.max(grafo.get(saida)).getPeso() == 0) {
			id = grafo.get(saida).get(r.nextInt(grafo.get(saida).size()-1)).getID();
		}
		resultado.add(id);
    	return resultado;
    }
    
}
