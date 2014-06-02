package model;

/**
 * Classe que define lista ordenada e seus metodos.
 */

public class ListaOrdenada <T extends Comparable<T>> extends ListaEncadeada<T> {

	/**
	 * Metodo que busca um determinado nodo, com base no valor informado e
	 * retorna o mesmo.
	 */
	
	private Nodo<T> procuraNodo(Nodo<T> novo) {
		Nodo<T> atual = getHead();
		Nodo<T> anterior = null;

		while (atual != null) {
			int comp = atual.compareTo(novo);
			if (comp < 0) {
				anterior = atual;
				atual = atual.getNext();
			}
			if (comp == 0)
				return atual;
			if (comp > 0)
				return anterior;
		}

		return anterior;
	}

	/**
	 * Metodo para exclusão do nodo.
	 */

	public void excluiNodo(Nodo<T> atual) {
		Nodo<T> anterior = this.getPrev(atual);
		Nodo<T> proximo = this.getNext(atual);

		if (atual.compareTo(this.getHead()) == 0) {
			this.setHead(atual.getNext());
		} else {
			if (proximo == null) {
				anterior.setNext(null);
			} else {
				anterior.setNext(proximo);
			}
		}
	}

	/**
	 * Metodo que busca o nodo anterior ao atual.
	 * 
	 */
	
	public Nodo<T> getPrev(Nodo<T> atual) {
		if (atual != null) {
			Nodo<T> aux = this.getHead();
			while (aux != null) {
				if (aux.getNext() != null) {
					if (aux.getNext().compareTo(atual) == 0) {
						return aux;
					}
				}
				aux = aux.getNext();
			}

		} else {
			return this.getHead();
		}

		return null;
	}

	/**
	 * Metodo que procura o nodo posterior ao atual.
	 */
	
	public Nodo<T> getNext(Nodo<T> atual) {
		if (atual != null) {
			return atual.getNext();
		} else {
			return this.getHead();
		}

	}

	/**
	 * Metodo que insere um nodo novo.
	 */

	@Override
	public void insert(Nodo<T> novo) {
		Nodo<T> anterior = procuraNodo(novo);
		if (anterior == null) {
			super.insert(novo);
		} else {
			super.insert(novo, anterior);
		}
	}

	/**
	 * Metodo que insere um novo nodo e seta, concomitantemente o anterior e o próximo.
	 */

	@Override
	public void insert(Nodo<T> novo, Nodo<T> anterior) {
		insert(novo);
	}

	/**
	 * Metodo que insere um nodo no fim da lista
	 */
	
	@Override
	public void append(Nodo<T> novo) {
		insert(novo);
	}

}
