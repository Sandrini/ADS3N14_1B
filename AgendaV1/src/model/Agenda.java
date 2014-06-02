package model;

/**
 * Classe responsável pelas inserções e remoções, na lista. 
 */

public class Agenda {
	
	private ListaOrdenada<Pessoa> lista = new ListaOrdenada<Pessoa>();
	private Nodo<Pessoa> atual = null;

	public Agenda() {

	}

	/**
	 * Inserçao de pessoa na lista.
	 * 
	 */

	public void insertPessoa(Pessoa p) {
		Nodo<Pessoa> nodoPessoa = new Nodo<Pessoa>(p);
		this.lista.append(nodoPessoa);
	}

	/**
	 * Remoção de pessoa da lista.
	 */
	public void removePessoa() {

		if (this.atual != null) {
			this.lista.excluiNodo(this.atual);
		}
	}

	/**
	 * Imprime lista
	 */
	public void printList() {
		this.lista.print();
	}

	public void listByChar(String nome) {
		Nodo<Pessoa> nodoPessoa = this.lista.getHead();
		while (nodoPessoa != null) {
			if (nodoPessoa.getData().getNome().toUpperCase().charAt(0) == nome
					.toUpperCase().charAt(0)) {
				System.out.println(nodoPessoa.getData().toString());
			}
			nodoPessoa = nodoPessoa.getNext();
		}
	}

	/**
	 * Retorna o contato atual.
	 */
	public Pessoa getContatoAtual() {
		if (this.atual != null) {
			return this.atual.getData();
		}
		return null;
	}

	public void next() {
		this.atual = this.lista.getNext(this.atual);
	}

	public void prev() {
		this.atual = this.lista.getPrev(this.atual);
	}

}
