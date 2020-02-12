package principal;

import java.util.List;

import javax.swing.JOptionPane;

import sensors.*;

//Singleton

public class Robo implements Runnable {
	private boolean pauseVar = false;

	private double sujeiraColetada;
	private int distanciaPercorrida;
	private double CR;
	private double tempo = 0;
	private int intervalo = 10;
	
	private int idPosicao;
	private Sensor proximidade;
	private DirtSensor sensorSujeira;

	private Busca busca;
	private Thread thread;
	private List<Integer> percurso;

	private SalaVirtual salaVirtual;
	private SalaReal salaReal; // usada apenas para funcao de limpeza

	static private Robo instance = new Robo();

	private Robo() {

		sujeiraColetada = 0;
		idPosicao = 0;
		// proximidade = new ProximitySensor();
		sensorSujeira = new DirtSensor();

		salaReal = SalaReal.getInstance();

	}

	// metodos
	// relacionados a thread
	public void start() {
		tempo = System.currentTimeMillis();
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {

		while (true) {
			if (System.currentTimeMillis()-tempo >= 10000) {
				System.out.println("Tempo: " + intervalo +"s\n"
						+"sujeira coletada: " + sujeiraColetada + 
						"\ndistancia percorrida: " + distanciaPercorrida + 
						"\nCR: " + sujeiraColetada/distanciaPercorrida+"\n");
				tempo = System.currentTimeMillis();
				intervalo+=10;
			}
			try {
				Thread.sleep(1000 / 60);
			} catch (Exception ex) {
				return;
			}
			if (!pauseVar)
				anda();
		}
	}

	// relacionados a busca
	public void primeiraVarrida() {

		double sujeira = 0;
		
		int sentido = 1;
		boolean acabouDeMudar = true;
		//percorre todas os quadrantes
		for (int i = 0; i < Sala.getTamanho() * Sala.getTamanho(); i=i+sentido) {
			//da um tempinho pra gente ver o robo andando
			try {
				Thread.sleep(50);
			} catch (Exception ex) {
				return;
			}
			
			//anda o robo, limpa, e informa pra salaVirtual a sujeira coletada
			//a fim de alterar as porcentagens iniciais
			
			idPosicao = i;
			sujeira = salaReal.limpa(i);
			salaVirtual.sujeiraColetada(i, sujeira);
			
			//adaptacao feita para fazer o movimento do robo
			//originalmente era pra ser implementado usando o sensor de proximidade
			if ((i+1)% Sala.getTamanho() == 0 && !acabouDeMudar) {
				i = i + Sala.getTamanho()+1;
				sentido = -1;
				acabouDeMudar = true;
			}else if(i%Sala.getTamanho() == 0 && !acabouDeMudar) {
				i = i + Sala.getTamanho()-1;
				sentido = 1;
				acabouDeMudar = true;
			}else
				acabouDeMudar = false;
		}
		salaVirtual.atualizaPorcentagens();
	}

	public void anda() {
		
		percurso = busca.dijkstra(idPosicao);
		//percurso = busca.subidaDeEncosta(idPosicao);
		

		for (Integer c : percurso) {
			try { 
				Thread.sleep(50);
			} catch (Exception ex) {
				return;
			}

			idPosicao = c;
                        
			double sujeira = salaReal.limpa(idPosicao);
			sujeiraColetada += sujeira;
			salaVirtual.sujeiraColetada(idPosicao, sujeira);
			distanciaPercorrida +=1;
		}
		CR = sujeiraColetada;
		salaVirtual.atualizaPorcentagens();
		
	}

	// getters e setters
	static public Robo getInstance() {
		return instance;
	}

	public Integer getPosicao() {
		return idPosicao;
	}

	public void setPosicao(Integer posicao) {
		this.idPosicao = posicao;
	}

	public SalaVirtual getSalaVirtual() {
		return salaVirtual;
	}
	
	public float getSujeiraColetada() {
		return (float)sujeiraColetada;
	}

	public void aloca() {
		salaVirtual = new SalaVirtual(Sala.getTamanho());

		busca = new Busca(salaVirtual);
	}
	
	public void pause() {
		pauseVar = true;
	}
	
	public void free() {
		pauseVar = false;
	} 

}
