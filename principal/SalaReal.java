package principal;
//singleton

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SalaReal extends Sala implements Runnable{
	static private SalaReal instance = new SalaReal();
	Thread thread;
	
	private List<QuadranteReal> quadrantes;
	public boolean pauseVar = false;
	
	
	
	//construtores
	private SalaReal() {
		quadrantes = new ArrayList<QuadranteReal>();
		
	}
	
	//métodos
	
	public double limpa(Integer ID) {
		double temp;
		
		temp = quadrantes.get(ID).getSujeira();
		quadrantes.get(ID).setSujeira((float)0.0);
		return temp;
	}
	
	public float getSujeira(Integer ID) {
		return quadrantes.get(ID).getSujeira();
	}
	
	public void sujaPrimeiro(double coeficiente) {
		Random r = new Random();
		/*for (int i = 0; i < quadrantes.size(); i++) {
			suja(i,(float)coeficiente*r.nextInt(10)+1);
		}*/
                
                int cont =0;
                double rand;
                rand = 0.1;
                for(int i=0; i<tamanho; i++){
                    //rand = (float)coeficiente*r.nextFloat()*10;
                    rand = rand + 0.99;
                    for(int j=0; j<tamanho; j++){
                        
                        suja(cont,(float)rand);
                        cont = cont+1;
                    }
                }
                
               
	}
	
	public void suja(Integer ID, float sujeira) {
		quadrantes.get(ID).setSujeira(sujeira);
	}
	
	public void aloca(int tamanho) {
		super.setTamanho(tamanho);
		for (int i = 0; i < tamanho*tamanho; i++) {
			Ponto ponto = new Ponto(i/tamanho, i%tamanho);
			quadrantes.add(new QuadranteReal(ponto));
		}
	}
	
	//SUJA A SALA
	void insereMultiplicador(){
        for (int i = 0; i < Sala.getTamanho()*Sala.getTamanho(); i++) {
               float sujeira = quadrantes.get(i).getSujeira(); 
               if (sujeira >9){
                   quadrantes.get(i).setMultSujeira((float) 0.6);
               }else
                    if (sujeira >8 && sujeira <= 9){
                        quadrantes.get(i).setMultSujeira((float) 0.5);
                    }else
                        if (sujeira >6 && sujeira <= 8){
                            quadrantes.get(i).setMultSujeira((float) 0.4);
                        }else
                            if (sujeira >4 && sujeira <= 6){
                                quadrantes.get(i).setMultSujeira((float) 0.3);
                            }else
                                if (sujeira >2 && sujeira <= 4){
                                    quadrantes.get(i).setMultSujeira((float) 0.2);
                                }else{
                                    quadrantes.get(i).setMultSujeira((float) 0.1);
                                }
        }
    }
	
	void atualizaPoeira(){
        for (int i = 0; i < Sala.getTamanho()*Sala.getTamanho(); i++) {
            float sujeira = quadrantes.get(i).getSujeira();
            sujeira += quadrantes.get(i).getMultSujeira();
            quadrantes.get(i).setSujeira(sujeira);
                
        }
    }
	
	//getters e setters 
	static public SalaReal getInstance() {
		return instance;
	}
	
	public List<QuadranteReal> getQuadrantes(){
		return quadrantes;
	}
	
	
	public void start() {
	    thread = new Thread(this);
	    thread.start();
	}


	@Override
	public void run() {
	    while(true) {
	    	if (pauseVar)
	    		continue;
	        try {
	            Thread.sleep((120*tamanho)*tamanho/(tamanho+1));
         
	        }catch(Exception ex) {
	            return;
	        }
	        atualizaPoeira();
	    }
	}
	
	public void pause() {
		pauseVar = true;
	}
	
	public void free() {
		pauseVar = false;
	}
	
}
