package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Arvore<T extends Comparable<T>> {
	private Nodo<T> raiz;
	private int comparacoes;

	public Arvore() {
		this.raiz = null;
		this.comparacoes = 0;
	}

	public Arvore(Nodo<T> nodo) {
		this.raiz = nodo;

	}

	public void insere(Nodo<T> nodo) {
		if (this.raiz == null) {
			this.raiz = nodo;
		} else {
			if (nodo.compareTo(raiz) == 0) {
				this.raiz = nodo;
			} else {
				this.insereNodo(raiz, nodo);
			}
		}
	}

	public Nodo<T> busca(Nodo<T> nodoBusca) {
		int result = nodoBusca.compareTo(this.raiz);
		if (result == 0) {
			return this.raiz;
		} else if (result > 0) {
			return this.buscaNodo(this.raiz.getEsquerda(), nodoBusca);
		} else if (result < 0) {
			return this.buscaNodo(this.raiz.getDireita(), nodoBusca);
		} else {
			return null;
		}
	}

	public void remover(Nodo<T> nodoRemover) {
		if (!nodoRemover.possuiFilhos()) {
			Nodo<T> nodoPai = nodoRemover.getPai();
			nodoRemover.setPai(null);
			if (nodoPai.getEsquerda() != null) {
				if (nodoPai.getEsquerda().compareTo(nodoRemover) == 0) {
					nodoPai.setEsquerda(null);
				}
			} else if (nodoPai.getDireita() != null) {
				if (nodoPai.getDireita().compareTo(nodoRemover) == 0) {
					nodoPai.setDireita(null);
				}
			}
		} else if (nodoRemover.getDireita() != null
				^ nodoRemover.getEsquerda() != null) {
			Nodo<T> nodoPai = nodoRemover.getPai();
			nodoRemover.setPai(null);
			if (nodoRemover.getDireita() != null) {
				if (nodoPai.getEsquerda() != null) {
					if (nodoPai.getEsquerda().compareTo(nodoRemover) == 0) {
						nodoPai.setEsquerda(nodoRemover.getDireita());
					}
				} else if (nodoPai.getDireita() != null) {
					if (nodoPai.getDireita().compareTo(nodoRemover) == 0) {
						nodoPai.setDireita(nodoRemover.getDireita());
					}
				}
			} else if (nodoRemover.getEsquerda() != null) {
				if (nodoPai.getEsquerda() != null) {
					if (nodoPai.getEsquerda().compareTo(nodoRemover) == 0) {
						nodoPai.setEsquerda(nodoRemover.getEsquerda());
					}
				} else if (nodoPai.getDireita() != null) {
					if (nodoPai.getDireita().compareTo(nodoRemover) == 0) {
						nodoPai.setDireita(nodoRemover.getEsquerda());
					}
				}
			}
		} else {

			Nodo<T> nodoAnterior = nodoRemover.getEsquerda();
			nodoRemover.setData(nodoAnterior.getData());
			this.remover(nodoAnterior);

		}
	}

	public int getAlturaArvore() {
		return this.getAltura(this.raiz);
	}

	public int getAltura(Nodo<T> nodo) {
		if (nodo == null)
			return 0;
		else {
			int x = 0;
			if (nodo.getEsquerda() != null)
				x += getAltura(nodo.getEsquerda());
			if (nodo.getDireita() != null)
				x += getAltura(nodo.getDireita());
			return (1 + x);
		}
	}

	public Nodo<T> buscaProfundidade(Nodo<T> nodoBusca) {
		this.comparacoes = 1;
		if (this.raiz == null) {
			return null;
		}
		List<Nodo<T>> listaPreFixa = this.travessiaPreFixa();
		for (int i = 0; i < listaPreFixa.size(); i++) {
			this.comparacoes++;
			Nodo<T> nodoAtual = listaPreFixa.get(i);
			if (nodoBusca.compareTo(nodoAtual) == 0) {
				return nodoAtual;
			}
		}
		return null;
	}

	public Nodo<T> buscaLargura(Nodo<T> nodoBusca) {
		Queue<Nodo<T>> listaLargura = this.getListaLargura(this.raiz);
		this.comparacoes = 0;
		if (listaLargura != null) {
			while (!listaLargura.isEmpty()) {
				this.comparacoes++;
				Nodo<T> atual = listaLargura.remove();
				if (nodoBusca.compareTo(atual) == 0) {
					return atual;
				}
			}
		}
		return null;
	}

	public List<Nodo<T>> travessiaPreFixa() {
		List<Nodo<T>> list = new ArrayList<Nodo<T>>();
		list.addAll(this.adicionaFilhosPreFixa(this.getRaiz()));
		return list;
	}

	private List<Nodo<T>> adicionaFilhosPreFixa(Nodo<T> atual) {
		List<Nodo<T>> list = new ArrayList<Nodo<T>>();
		if (atual.getEsquerda() != null) {
			list.addAll(this.adicionaFilhosPreFixa(atual.getEsquerda()));
		}
		if (atual.getDireita() != null) {
			list.addAll(this.adicionaFilhosPreFixa(atual.getDireita()));
		}
		list.add(atual);
		return list;
	}

	public List<Nodo<T>> travessiaPosFixa() {
		List<Nodo<T>> list = this.adicionaFilhosPosFixa(this.getRaiz());
		return list;
	}

	private List<Nodo<T>> adicionaFilhosPosFixa(Nodo<T> nodo) {
		List<Nodo<T>> list = new ArrayList<Nodo<T>>();
		list.add(nodo);
		if (nodo.getEsquerda() != null) {
			list.addAll(this.adicionaFilhosPosFixa(nodo.getEsquerda()));
		}
		if (nodo.getDireita() != null) {
			list.addAll(this.adicionaFilhosPosFixa(nodo.getDireita()));
		}
		return list;
	}

	public List<Nodo<T>> travessiaInfixa() {
		if (this.raiz != null) {
			return this.getListaInfixa(this.raiz);
		} else {
			return null;
		}
	}

	private Queue<Nodo<T>> getListaLargura(Nodo<T> nodo) {
		Queue<Nodo<T>> fila = new LinkedList<Nodo<T>>();
		if (nodo == null) {
			return null;
		}
		fila.add(nodo);
		if (nodo.getEsquerda() != null) {
			fila.addAll(this.getListaLargura(nodo.getEsquerda()));
		}
		if (nodo.getDireita() != null) {
			fila.addAll(this.getListaLargura(nodo.getDireita()));
		}
		return fila;
	}

	private List<Nodo<T>> getListaInfixa(Nodo<T> nodo) {
		List<Nodo<T>> listaNodo = new ArrayList<Nodo<T>>();
		if (nodo.getDireita() != null) {
			listaNodo.addAll(this.getListaInfixa(nodo.getDireita()));
		}
		listaNodo.add(nodo);
		if (nodo.getEsquerda() != null) {
			listaNodo.addAll(this.getListaInfixa(nodo.getEsquerda()));
		}
		return listaNodo;
	}

	private Nodo<T> buscaNodo(Nodo<T> nodoPai, Nodo<T> nodoBusca) {
		if (nodoPai == null) {
			return null;
		} else {
			int result = nodoBusca.compareTo(nodoPai);
			if (result == 0) {
				return nodoPai;
			} else if (result > 0) {
				return this.buscaNodo(nodoPai.getEsquerda(), nodoBusca);
			} else if (result < 0) {
				return this.buscaNodo(nodoPai.getDireita(), nodoBusca);
			} else {
				return null;
			}
		}
	}

	private void insereDireita(Nodo<T> nodoPai, Nodo<T> nodoInserir) {
		if (nodoPai.getDireita() != null) {
			this.insereNodo(nodoPai.getDireita(), nodoInserir);
		} else {
			nodoPai.setDireita(nodoInserir);
		}
	}

	private void insereEsquerda(Nodo<T> nodoPai, Nodo<T> nodoInserir) {
		if (nodoPai.getEsquerda() != null) {
			this.insereNodo(nodoPai.getEsquerda(), nodoInserir);
		} else {
			nodoPai.setEsquerda(nodoInserir);
		}
	}

	private void insereNodo(Nodo<T> nodoPai, Nodo<T> nodoInserir) {
		int result = nodoPai.compareTo(nodoInserir);
		if (result < 0) {
			this.insereEsquerda(nodoPai, nodoInserir);
		} else if (result > 0) {
			this.insereDireita(nodoPai, nodoInserir);
		} else {
			nodoPai.setData(nodoInserir.getData());
			this.insereDireita(nodoPai, nodoInserir);
		}
	}

	public Nodo<T> getRaiz() {
		return this.raiz;
	}

	public int getComparacoes() {
		return this.comparacoes;
	}

}
