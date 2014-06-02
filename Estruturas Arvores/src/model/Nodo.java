package model;
/**
 * Classe do Nodo.
 * 
 */

public class Nodo <T extends Comparable<T>>  {

	public Nodo<T> esquerda;
	public Nodo<T> direita;
	public Nodo<T> parente;
	public T dado;
	public int fatorBalanco;
	public int cor;

	public Nodo() {

	}

	public Nodo(T dado) {
		esquerda = null;
		direita = null;
		parente = null;
		fatorBalanco = 0;
		this.dado = dado;
	}

	public String toString() {
		return "" + dado;
	}

	public void setFilhoEsquerda(Nodo<T> filho) {
		esquerda = filho;
		if (filho != null) {
			filho.parente = this;
		}
	}

	public void setFilhoDireita(Nodo<T> filho) {
		direita = filho;
		if (filho != null) {
			filho.parente = this;
		}
	}

	public void substituiCom(Nodo<T> substitui) {
		if (parente == null)
			return;
		if (this == parente.esquerda)
			parente.setFilhoEsquerda(substitui);
		else
			parente.setFilhoDireita(substitui);
	}

	public T getDado() {
		return dado;
	}

	public void setDado(T data) {
		this.dado = data;
	}

	public Nodo<T> getEsquerda() {
		return esquerda;
	}

	public void setEsquerda(Nodo<T> esquerda) {
		this.esquerda = esquerda;
	}

	public Nodo<T> getDireita() {
		return direita;
	}

	public void setDireita(Nodo<T> direita) {
		this.direita = direita;
	}

}
