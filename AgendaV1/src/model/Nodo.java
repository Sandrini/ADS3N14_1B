package model;

/**
 * @author Rafael J.
 * 
 *         Classe que define os nodos.
 */

public class Nodo<T extends Comparable<T>> implements Comparable<Nodo<T>> {

	private T dado;
	private Nodo<T> next;
	private Nodo<T> prev;

	public Nodo() {
		this.dado = null;
		this.next = null;
		this.prev = null;
	}

	public Nodo(T valor) {
		dado = valor;
		next = null;
	}

	public void setData(T dado) {
		this.dado = dado;
	}

	public T getData() {
		return dado;
	}

	public void setNext(Nodo<T> next) {
		this.next = next;
	}

	public Nodo<T> getNext() {
		return next;
	}

	public void setPrev(Nodo<T> prev) {
		this.prev = prev;
	}

	public Nodo<T> getPrev() {
		return prev;
	}

	public int compareTo(Nodo<T> nodo) {
		return dado.compareTo(nodo.getData());
	}
}
