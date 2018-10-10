package principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Busca{
    List<List<Integer>> grafoIDs   = new ArrayList<List<Integer>>();
    List<List<Double>>  grafoPesos = new ArrayList<List<Double>>();
    Sala sala;
    int tamanhoGrafo;
    
    private void atualizaDados(){
        double temp;
        List<Double> pesos = new ArrayList<Double>();
        
        int i;
        
        for(i=0; i<tamanhoGrafo; i++){
            
            for(Integer v: grafoIDs.get(i)){                
                temp = sala.getPorcentagem(v);

                if(temp <0.01) 
                    temp = 1/0.015;
                else
                    temp = 1/temp;
                
                pesos.add(temp);
                
            }
            
            grafoPesos.add(pesos);
        }        
    }
    
    public List<Integer> dijkstra(int saida, int chegada){
        atualizaDados();
        
        boolean visitados[]= new boolean[tamanhoGrafo];
        double dist      []= new double[tamanhoGrafo];
        int    pred      []= new int[tamanhoGrafo];
        
        double p;
        
        int i;
        int j;
        
        List<Integer> resultado = new ArrayList<Integer>();
        
        Queue<Integer> menorAberto = new PriorityQueue<Integer>();
        
        /*Map<Integer,Integer> pred = new HashMap<Integer,Integer>();
        Map<Integer,Integer> dist = new HashMap<Integer,Integer>();*/
        
        for(i=0; i<tamanhoGrafo; i++){
            pred[i] = -1;
            visitados[i] = false;
            dist[i] = 10e10;
        }
        
        dist[saida] = 0;
        visitados[saida] = true;
        
        menorAberto.add(saida);
        while(1==1){
            if(menorAberto.isEmpty())
                break;
            
            i = menorAberto.poll();
            visitados[i] = true;
            
            List<Integer> vizinhos = grafoIDs.get(i);
            
            j=0;
            for(Integer k: vizinhos){
                if(visitados[k])
                    continue;
                
                p = Math.min(dist[k], dist[i] + grafoPesos.get(i).get(j));
                
                if(p<dist[k]){
                    dist[k] = p;
                    menorAberto.add(k);
                    pred[k] = i;
                }
            }
        }
        
        i=chegada;
        while(1==1){
            if(i==-1)
                break;
            
            resultado.add(0,i);
            i = pred[i];
        }
        
        for(Integer k: resultado)
            System.out.printf("%d ", k);
        System.out.printf("\n");
        
        return resultado;
    }
    
    Busca(){
        int i;
        sala = Sala.getInstance();
        tamanhoGrafo = Sala.ALTURA*Sala.ALTURA;
        List<Integer> vizinhos = new ArrayList<Integer>();
        
        for(i=0; i<tamanhoGrafo; i++){
            //vizinhos = Sala.getVizinhos(i);
            vizinhos = sala.getVizinhos(i);
            
            grafoIDs.add(vizinhos);
            
        }
        
    }
}