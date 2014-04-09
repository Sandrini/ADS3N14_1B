package Model;

public class Coordenada {
	
	private Navio navio = null;
	private int posicao = 0;
	private Boolean jogada = true;

	public int getPosicao() {
		return posicao;
	}

	public void setParte(int posicao) {
		this.posicao = posicao;
	}

	public Navio getNavio() {
		return navio;
	}

	public void setNavio(Navio navio) {
		this.navio = navio;
	}

	public Boolean aJogada() {   
		return jogada;
	}

	public void fogo() {
		jogada = false;
	}

}
