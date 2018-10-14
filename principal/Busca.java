/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.swing.JOptionPane;

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
    
    Sala sala = Sala.getInstance();
    
    int tamanhoGrafo;
    
    private int atualizaDados(){
        double temp;
        double maior = -10e10;
        int chegada = -1;
        
        int i;
        
        for(i=0; i<tamanhoGrafo; i++){
            for(Grafo g: grafo.get(i)){                
                temp = sala.getPorcentagem(g.getID());
                
                if(temp> maior){
                    chegada = g.getID();
                    maior = temp;
                }
                
                if(temp <0.01) 
                    temp = 1/0.015;
                else
                    temp = 1/temp;
                
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
        
        chegada = atualizaDados();
        
        if(chegada == -1)
            return resultado;        
        
        for(i=0; i<tamanhoGrafo; i++){
            pred[i] = -1;
            visitados[i] = false;
            dist[i] = 10e10;
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
        
       // for(Integer a: resultado)
       //     System.out.printf("%d ", a);
       // System.out.printf("\n");
        
        return resultado;
    }
    
    Busca(){
        int i;
  
        tamanhoGrafo = Sala.getAltura()*Sala.getAltura();
        List<Integer> vizinhos = new ArrayList<Integer>();
        grafo = new ArrayList<List<Grafo>>();
        
        for(i=0; i<tamanhoGrafo; i++){
            vizinhos = sala.getVizinhos(i);
            
            grafo.add(new ArrayList<Grafo>());            
            
            for(Integer temp: vizinhos){
                Grafo g = new Grafo(temp, 0.0);
                grafo.get(i).add(g);
            }

            
        }
        
    }
}
