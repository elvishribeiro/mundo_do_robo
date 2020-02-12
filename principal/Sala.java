package principal;

import java.util.Map;

import javax.swing.JOptionPane;

public abstract class Sala {
	static protected int tamanho = -1;
	
	protected Map<Ponto, Integer> posID;
	
	static public void setTamanho(int tamanho) {
		if (tamanho > 1)
			Sala.tamanho = tamanho;
		else
			JOptionPane.showMessageDialog(null, "Tamanho " +tamanho+ " Inválido",
					"ERRO", JOptionPane.ERROR_MESSAGE);
	}
	
	static public int getTamanho() {
		return tamanho;
	}
}
