package principal;

import java.util.List;

import sensors.*;

//Singleton


public class Robo implements Runnable{
    
    private float SujeiraColetada;
    private Ponto posicao;
    private Sensor proximidade;
    private Sensor sujeira;
    private Busca busca = new Busca();
    private Thread thread;
    private List<Integer> percurso;
    Sala sala = Sala.getInstance();
    
    static private Robo instance = new Robo();
    
    private Robo() {

        SujeiraColetada=0;
        setPosicao(new Ponto(0,0));
        proximidade = new ProximitySensor();
        sujeira = new DirtSensor();
        
    }
    
    public void start() {
        thread = new Thread(this);
        thread.start();
    }
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000/60);
            }catch(Exception ex) {
                return;
            }
            anda();
        }
    }
    
    public void anda(){
    	System.out.println("atual: " +Sala.idFromPonto(this.posicao)+ "  indo: "+percurso);
    	percurso = busca.dijkstra(Sala.idFromPonto(this.posicao));
        sala.atualizaPorcentagens();
       for (Integer c: percurso){
           try {
                Thread.sleep(800);
            }catch(Exception ex) {
                return;
            }
            posicao = Sala.PontoFromId(c);
            //System.out.println("Nova posicao " + posicao.x + " " + posicao.x);
            sala.quadFromID(c).setSujeira();
            
       }
      
       
       
    }
    
    static public Robo getInstance() {
        return instance;
    }
    
    public Ponto getPosicao() {
        return posicao;
    }

    public void setPosicao(Ponto posicao) {
        this.posicao = posicao;
    }
}
