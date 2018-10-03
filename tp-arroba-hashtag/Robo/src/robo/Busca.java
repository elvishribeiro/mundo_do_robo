
package robo;
import java.util.Random;

public class Busca{
    int populacao[][];
    int tamanhoPopulacao;
    int tamanhoSala;

//System.out.print("antigo: " + pressao + " novo: ");    
    
    /*private void geraPopulacaoInicial(int idSaida, int idChegada){
        int i;
        int j;
        int id;
        int pos;
        
        int adjacentes[];
        int vetorTemporario[] = new int[tamanhoSala];
        int tamanhoVetor;
        
        Random random = new Random();
        
        sala S = new sala();
        
        for(i=0; i<tamanhoPopulacao; i++ ){
            vetorTemporario[0] = idSaida;
            
            id = 1;
            while(id<tamanhoSala){
                adjacentes = S.getAdjacentes(vetorTemporario[id-1]);
                tamanhoVetor = adjacentes.length;
                
                pos = random.nextInt(tamanhoVetor);
                vetorTemporario[id] = adjacentes[pos];
                
                if(vetorTemporario[id] == idChegada)
                    break;
                
                System.out.printf("%d  ",pos);
                
                id= id+1;
            }
            System.out.printf("\n");
        }
    }*/
    
    public int[] AG(int idSaida, int idChegada, int t){
        Sala S = Sala.getInstance();
        tamanhoSala = S.getTamanhoSala();
        //tamanhoPopulacao = t;
       // populacao = new int[tamanhoPopulacao][tamanhoSala];
        
        //geraPopulacaoInicial(idSaida, idChegada);*/
        
        int i;
        int id;
        int pos;
        
        int adjacentes[];
        int vetorTemporario[] = new int[tamanhoSala];
        int tamanhoVetor;
        int retorno[];
        
        Random random = new Random();
        
        while(1==1){
            id = 1;
            while(id<tamanhoSala){
                adjacentes = S.getAdjacentes(vetorTemporario[id-1]);
                tamanhoVetor = adjacentes.length;
                
                pos = random.nextInt(tamanhoVetor);
                vetorTemporario[id] = adjacentes[pos];
                
                if(vetorTemporario[id] == idChegada){
                    id = id+1;
                    break;
                }
                                
                id= id+1;
            }
            
            if(vetorTemporario[id-1] == idChegada){
                retorno = new int[id];
                
                for(i=0; i<id; i++)
                    retorno[i] = vetorTemporario[i];
                
                break;
            }
            
        }
        
        return retorno;
    }; 
}
