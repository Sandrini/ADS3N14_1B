package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pelas inserções e remoções, na lista.
 * 
 */

public class Agenda {
	private Arvore<Pessoa> arvore;

	public Agenda() {
		this.arvore = new Arvore<Pessoa>();
	}

	/**
	 * Inserçao de pessoa na Arvore.
	 * 
	 */
	
	public void inserePessoa(Pessoa p) {
		Nodo<Pessoa> nodo = new Nodo<Pessoa>(p);
		this.arvore.insere(nodo);
		System.out.println("ALTURA RAIZ: " + this.arvore.getAlturaArvore());
		List<Nodo<Pessoa>> listaNodos = this.arvore.travessiaPreFixa();
		System.out.println("A ARVORE CONTEM " + listaNodos.size() + " NODOS.");

	}

	/**
	 * Busca de pessoa na Arvore.
	 * 
	 */
	
	public Pessoa buscaPessoa(Pessoa p) {
		Nodo<Pessoa> nodo = new Nodo<Pessoa>(p);
		Nodo<Pessoa> nodoPessoa = this.arvore.busca(nodo);
		System.out.println("COMPARACOES: " + this.arvore.getComparacoes());
		if (nodoPessoa == null) {
			return null;
		} else {
			System.out.println("ALTURA DO NODO:"
					+ this.arvore.getAltura(nodoPessoa));
			return nodoPessoa.getData();
		}
	}

	/**
	 * Busca em largura na Arvore.
	 * 
	 */
	
	public Pessoa buscaLargura(Pessoa p) {
		Nodo<Pessoa> nodoPessoa = new Nodo<Pessoa>(p);
		Nodo<Pessoa> nodoResultado = this.arvore.buscaLargura(nodoPessoa);
		System.out.println("COMPARACOES: " + this.arvore.getComparacoes());
		if (nodoResultado == null) {
			return null;
		}
		System.out.println("ALTURA DO NODO: "
				+ this.arvore.getAltura(nodoResultado));
		return nodoResultado.getData();
	}

	public Pessoa buscaProfundidade(Pessoa p) {
		Nodo<Pessoa> nodoPessoa = new Nodo<Pessoa>(p);
		Nodo<Pessoa> nodoResultado = this.arvore.buscaProfundidade(nodoPessoa);
		System.out.println("COMPARACOES: " + this.arvore.getComparacoes());
		if (nodoResultado == null) {
			return null;
		}
		System.out.println("ALTURA DO NODO: "
				+ this.arvore.getAltura(nodoResultado));
		return nodoResultado.getData();
	}

	/**
	 * Remoção de pessoa na Arvore.
	 * 
	 */
	
	public void remove(Pessoa p) {
		Nodo<Pessoa> nodo = new Nodo<Pessoa>(p);
		Nodo<Pessoa> nodoRemover = this.arvore.busca(nodo);
		if (nodoRemover != null) {
			this.arvore.remover(nodoRemover);
		}
	}

	/**
	 * Metodo de retorno para Pre-Fixa.
	 * 
	 */
	
	public List<Pessoa> retornoTravessiaPreFixa() {
		List<Nodo<Pessoa>> listNodoPessoa = this.arvore.travessiaPreFixa();
		return this.conversorListaNodoParaListaPessoa(listNodoPessoa);
	}

	/**
	 * Metodo de retorno para Pos-Fixo.
	 * 
	 */

	public List<Pessoa> retornoTravessiaPosFixa() {
		List<Nodo<Pessoa>> listNodoPessoa = this.arvore.travessiaPosFixa();
		return this.conversorListaNodoParaListaPessoa(listNodoPessoa);
	}

	/**
	 * Metodo de retorno para In-Fixo.
	 *
	 */

	public List<Pessoa> retornoTravessiaInFixa() {
		List<Nodo<Pessoa>> listNodoPessoa = this.arvore.travessiaInfixa();
		if (listNodoPessoa != null) {
			return this.conversorListaNodoParaListaPessoa(listNodoPessoa);
		}
		return null;
	}

	/**
	 * Metodo de conversão do nodo, para Lista de Pessoas.
	 * 
	 */
	
	private List<Pessoa> conversorListaNodoParaListaPessoa(
			List<Nodo<Pessoa>> listaNodoPessoa) {
		List<Pessoa> listaPessoa = new ArrayList<Pessoa>();
		for (int i = 0; i < listaNodoPessoa.size(); i++) {
			listaPessoa.add(listaNodoPessoa.get(i).getData());
		}
		return listaPessoa;
	}

}
