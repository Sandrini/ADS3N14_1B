package model;

/**
 * @author Rafael J.
 * 
 *         Classe que define lista encadeada e seus m�todos.
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
	 * M�todo que insere um novo nodo no in�cio da lista.
	 * 
	 */
	public void insert(Nodo<T> novo) {
		novo.setNext(head);
		head = novo;
		if (tail == null)
			tail = head;
	}

	/**
	 * M�todo que insere um nodo em uma posi��o espec�fica da lista.
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
	 * M�todo que insere um nodo no fim da lista.
	 */
	public void append(Nodo<T> novo) {
		tail.setNext(novo);
		tail = novo;
	}

	/**
	 * M�todo que imprime toda a lista.
	 */
	public void print() {
		Nodo<?> nodo = head;
		do {
			System.out.println(nodo.getData());
			nodo = nodo.getNext();
		} while (nodo != null);
	}

}
