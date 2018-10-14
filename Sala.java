package robo;

import java.awt.Point;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Singleton
public class Sala {
	static int LARGURA = 3;
	static int ALTURA = 3;
	
        
	private List<List<Quadrante>> quadrantes;
        
        private List<List<Float>> chao;
	
	private static Sala instance = new Sala ();
	
	private Sala() {
		quadrantes = new ArrayList<List<Quadrante>>();
		for (int i = 0; i < LARGURA; i++) {
			quadrantes.add(new ArrayList<Quadrante>());
			for (int j = 0; j < ALTURA; j++)
				quadrantes.get(i).add(new Quadrante(new Ponto(i,j)));
		}                              
	}
        
        public int sujaPrimeiro (){
           Random r = new Random();
           float sujeira;
           float x = r.nextInt(11);
            for (int i = 0; i < LARGURA; i++) {			
                 for (int j = 0; j < ALTURA; j++){                    
                     sujeira = r.nextFloat()*10;                     
                     quadrantes.get(i).get(j).setSujeira(sujeira);
                 }
            }                                             
        return 0;
        }
        
        
        void InsereMultiplicador(){
            for (int i = 0; i < LARGURA; i++) {			
                for (int j = 0; j < ALTURA; j++){
                   float sujeira = quadrantes.get(i).get(j).getSujeira(); 
                   if (sujeira >9 && sujeira <= 10){
                       quadrantes.get(i).get(j).setMultSujeira((float) 0.6);
                   }else
                        if (sujeira >8 && sujeira <= 9){
                            quadrantes.get(i).get(j).setMultSujeira((float) 0.5);
                        }else
                            if (sujeira >6 && sujeira <= 8){
                                quadrantes.get(i).get(j).setMultSujeira((float) 0.4);
                            }else
                                if (sujeira >4 && sujeira <= 6){
                                    quadrantes.get(i).get(j).setMultSujeira((float) 0.3);
                                }else
                                    if (sujeira >2 && sujeira <= 4){
                                        quadrantes.get(i).get(j).setMultSujeira((float) 0.2);
                                    }else{
                                        quadrantes.get(i).get(j).setMultSujeira((float) 0.1);
                                    }                
                }
            }                        
        }
        void AtualizaPoeira(){
            for (int i = 0; i < LARGURA; i++) {			
               for (int j = 0; j < ALTURA; j++){
                float sujeira = quadrantes.get(i).get(j).getSujeira();
                sujeira += quadrantes.get(i).get(j).getMultSujeira();
                    if (sujeira > 10){
                    quadrantes.get(i).get(j).setSujeira((float) 10.0);                    
                    }else{
                    quadrantes.get(i).get(j).setSujeira(sujeira);
                    }
               }
            }
        }                
        public void exibirSala(){
             System.out.print("Mapa da partida\n");
            for (int i = 0; i < LARGURA; i++) {			
		for (int j = 0; j < ALTURA; j++){
                    System.out.print(" quadrante: "+ i +" "+ j +" sujeira = "+quadrantes.get(i).get(j).getSujeira());
		}
            System.out.println(" ");    
            }                            
        }  
	public static Sala getInstance() {
		return instance;
	}
	
        public int getTamanhoSala(){
            return LARGURA*ALTURA;
        }
	public boolean disponivel(Point ponto) {
		return true;
	}
}
