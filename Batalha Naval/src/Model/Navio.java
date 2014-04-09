package Model;

import java.util.ArrayList;

public class Navio {
	
	protected ArrayList<Integer> embarcacao = new ArrayList<Integer>();
	
	protected Boolean vertical = false;

	public Boolean foiDestruido() {
		return (this.embarcacao.isEmpty() ? true : false);
	}

	public Boolean destruir(int posicao) {
		embarcacao.remove(posicao - 1);

		return foiDestruido();
	}

	public ArrayList<Integer> getEmbarcacao() {
		return embarcacao;
	}

	public void setEmbarcacao(ArrayList<Integer> embarcacao) {
		this.embarcacao = embarcacao;
	}

	public Boolean getVertical() {
		return vertical;
	}

	public void setVertical(Boolean vertical) {
		this.vertical = vertical;
	}

}
