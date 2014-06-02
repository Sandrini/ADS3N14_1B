package model;

/**
 * @author Rafael J.
 * 
 *         Classe que define lista encadeada e seus métodos.
 */

public class ListaEncadeada<T extends Comparable<T>> {
	protected Nodo<T> head;
	protected Nodo<T> tail;

	public Nodo<T> getHead() {
		return head;
	}

	public void setHead(Nodo<T> nodo) {
		this.head = nodo;
	}

	/**
	 * Método que insere um novo nodo no início da lista.
	 * 
	 */
	public void insert(Nodo<T> novo) {
		novo.setNext(head);
		head = novo;
		if (tail == null)
			tail = head;
	}

	/**
	 * Método que insere um nodo em uma posição específica da lista.
	 * 
	 */
	public void insert(Nodo<T> novo, Nodo<T> anterior) {
		if (anterior == tail) {
			tail.setNext((Nodo<T>) novo);
			tail = novo;
		} else {
			novo.setNext(anterior.getNext());
			anterior.setNext(novo);
		}
	}

	/**
	 * Método que insere um nodo no fim da lista.
	 */
	public void append(Nodo<T> novo) {
		tail.setNext(novo);
		tail = novo;
	}

	/**
	 * Método que imprime toda a lista.
	 */
	public void print() {
		Nodo<?> nodo = head;
		do {
			System.out.println(nodo.getData());
			nodo = nodo.getNext();
		} while (nodo != null);
	}

}
