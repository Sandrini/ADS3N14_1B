package model;

public class Nodo <T extends Comparable<T>> implements Comparable<Nodo<T>> {

	private T chave;
	private Nodo<T> pai;
	private Nodo<T> esquerda;
	private Nodo<T> direita;

	public Nodo() {
		this.chave = null;
		this.pai = null;
		this.esquerda = null;
		this.direita = null;
	}

	public Nodo(T valor) {
		this.chave = valor;
		this.pai = null;
		this.esquerda = null;
		this.direita = null;
	}

	public boolean possuiFilhos() {
		if (this.getEsquerda() == null && this.getDireita() == null) {
			return false;
		}
		return true;
	}

	public T getData() {
		return chave;
	}

	public void setData(T chave) {
		this.chave = chave;
	}

	public Nodo<T> getPai() {
		return this.pai;
	}

	public void setPai(Nodo<T> pai) {
		this.pai = pai;
	}

	public Nodo<T> getEsquerda() {
		return this.esquerda;
	}

	public void setEsquerda(Nodo<T> esq) {
		this.esquerda = esq;
		if (this.esquerda != null) {
			this.esquerda.setPai(this);
		}
	}

	public Nodo<T> getDireita() {
		return this.direita;
	}

	public void setDireita(Nodo<T> dir) {
		this.direita = dir;
		if (this.direita != null) {
			this.direita.setPai(this);
		}
	}

	public int compareTo(Nodo<T> nodo) {
		return chave.compareTo(nodo.getData());
	}

}
