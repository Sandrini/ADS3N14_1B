package model;

public class ArvoreRedBlack<T extends Comparable<T>> extends Arvore<T> {
	
	static final int PRETO = 1;
	static final int VERMELHO = 0;
	private static final int VERMELHO_NEGATIVO = -1;
	private static final int PRETO_DUPLO = 2;

	public int contadorRotacao;
	public int contadorRemocao;
	public int contadorInsercaoRemocao;
	public int rbArvoreAltura;

	public ArvoreRedBlack() {
		raiz = null;
		contadorRotacao = 0;
	}

	public void addRBT(T dado) {

		contadorInsercao = 0;
		contadorInsercaoRemocao = 0;
		Nodo<T> novoNodo = new Nodo<T>(dado);
		adicionarRecursivo(novoNodo, this.raiz);

		corrigirDepoisAdicionar(novoNodo);
		rbArvoreAltura = arvoreAltura(this.raiz);
	}

	public void removeRBT(T data) {
		
		contadorRemocao = 0;
		contadorInsercaoRemocao = 0;
		Nodo<T> toBeRemoved = raiz;
		boolean achou = false;
		while (!achou && toBeRemoved != null) {

			if (toBeRemoved.dado.compareTo(data) == 0) {
				contadorRemocao++;
				achou = true;
			} else {
				if (toBeRemoved.dado.compareTo(data) > 0) {
					contadorRemocao++;
					toBeRemoved = toBeRemoved.esquerda;
				} else {
					toBeRemoved = toBeRemoved.direita;
				}
			}
		}

		if (!achou) {
			return;
		}

		if (toBeRemoved.esquerda == null || toBeRemoved.direita == null) {
			Nodo<T> novoFilho;
			if (toBeRemoved.esquerda == null) {
				novoFilho = toBeRemoved.direita;
			} else {
				novoFilho = toBeRemoved.esquerda;
			}

			corrigirAntesAdicionar(toBeRemoved);

			if (toBeRemoved.parente == null) {
				raiz = novoFilho;
			} 
			else {
				toBeRemoved.substituiCom(novoFilho);
			}
			return;
		}

		Nodo<T> smallest = toBeRemoved.direita;
		while (smallest.esquerda != null) {
			smallest = smallest.esquerda;
		}

		toBeRemoved.dado = smallest.dado;
		corrigirAntesAdicionar(smallest);
		smallest.substituiCom(smallest.direita);
	}

	private void corrigirDepoisAdicionar(Nodo<T> novoNodo) {
		if (novoNodo.parente == null) {
			novoNodo.cor = PRETO;
		} else {
			novoNodo.cor = VERMELHO;
			if (novoNodo.parente.cor == VERMELHO) {
				corrigeVermelhoDuplo(novoNodo);

			}
		}

	}

	private void corrigirAntesAdicionar(Nodo<T> removido) {
		if (removido.cor == VERMELHO) {
			return;
		}

		if (removido.esquerda != null || removido.direita != null) 
																	
		{
			
			if (removido.esquerda == null) {
				removido.direita.cor = PRETO;
			} else {
				removido.esquerda.cor = PRETO;
			}
		} else {
			move2(removido.parente);
		}
	}

	private void move2(Nodo<T> parente) {
		if (parente == null) {
			return;
		}
		parente.cor++;
		parente.esquerda.cor--;
		parente.direita.cor--;

		Nodo<T> filho = parente.esquerda;
		if (filho.cor == VERMELHO_NEGATIVO) {
			corrigeVermelhoNegativo(filho);
			return;
		} else if (filho.cor == VERMELHO) {
			if (filho.esquerda != null && filho.esquerda.cor == VERMELHO) {
				corrigeVermelhoDuplo(filho.esquerda);
				return;
			}
			if (filho.direita != null && filho.direita.cor == VERMELHO) {
				corrigeVermelhoDuplo(filho.direita);
				return;
			}
		}

		filho = parente.direita;
		if (filho.cor == VERMELHO_NEGATIVO) {
			corrigeVermelhoNegativo(filho);
			return;
		} else if (filho.cor == VERMELHO) {
			if (filho.esquerda != null && filho.esquerda.cor == VERMELHO) {
				corrigeVermelhoDuplo(filho.esquerda);
				return;
			}
			if (filho.direita != null && filho.direita.cor == VERMELHO) {
				corrigeVermelhoDuplo(filho.direita);
				return;
			}
		}

		if (parente.cor == PRETO_DUPLO) {
			if (parente.parente == null) {
				parente.cor = PRETO;
			} else {
				move2(parente.parente);
			}
		}
	}

	private void corrigeVermelhoDuplo(Nodo<T> filho) {
		Nodo<T> parente = filho.parente;
		Nodo<T> grandParent = parente.parente;
		contadorRotacao++;
		contadorInsercaoRemocao++;
		if (grandParent == null) {
			parente.cor = PRETO;

			return;

		}

		Nodo<T> n1, n2, n3, t1, t2, t3, t4;

		if (parente == grandParent.esquerda) {
			n3 = grandParent;
			t4 = grandParent.direita;
			if (filho == parente.esquerda) {
				n1 = filho;
				n2 = parente;
				t1 = filho.esquerda;
				t2 = filho.direita;
				t3 = parente.direita;

			} else {
				n1 = parente;
				n2 = filho;
				t1 = parente.esquerda;
				t2 = filho.esquerda;
				t3 = filho.direita;

			}

		} else {
			n1 = grandParent;
			t1 = grandParent.esquerda;

			if (filho == parente.esquerda) {
				n2 = filho;
				n3 = parente;
				t2 = filho.esquerda;
				t3 = filho.direita;
				t4 = parente.direita;

			} else {
				n2 = parente;
				n3 = filho;
				t2 = parente.esquerda;
				t3 = filho.esquerda;
				t4 = filho.direita;

			}

		}

		if (grandParent == raiz) {
			raiz = n2;
			n2.parente = null;

		} else {
			grandParent.substituiCom(n2);

		}

		n1.setFilhoEsquerda(t1);
		n1.setFilhoDireita(t2);
		n2.setFilhoEsquerda(n1);
		n2.setFilhoDireita(n3);
		n3.setFilhoEsquerda(t3);
		n3.setFilhoDireita(t4);
		n2.cor = grandParent.cor - 1;
		n1.cor = PRETO;
		n3.cor = PRETO;

		if (n2 == raiz) {
			raiz.cor = PRETO;

		} else if (n2.cor == VERMELHO && n2.parente.cor == VERMELHO) {
			corrigeVermelhoDuplo(n2);

		}

	}

	private void corrigeVermelhoNegativo(Nodo<T> vermelhoNegativo) {
		Nodo<T> n1, n2, n3, n4, t1, t2, t3, filho;
		Nodo<T> parente = vermelhoNegativo.parente;

		contadorRotacao++;
		contadorInsercaoRemocao++;
		if (parente.esquerda == vermelhoNegativo) {
			n1 = vermelhoNegativo.esquerda;
			n2 = vermelhoNegativo;
			n3 = vermelhoNegativo.direita;
			n4 = parente;
			t1 = n3.esquerda;
			t2 = n3.direita;
			t3 = n4.direita;
			n1.cor = VERMELHO;
			n2.cor = PRETO;
			n4.cor = PRETO;
			n2.setFilhoDireita(t1);

			T temp = n4.dado;
			n4.dado = n3.dado;
			n3.dado = temp;

			n3.setFilhoEsquerda(t2);
			n3.setFilhoDireita(t3);
			n4.setFilhoDireita(n3);
			filho = n1;
		} else {
			n4 = vermelhoNegativo.direita;
			n3 = vermelhoNegativo;
			n2 = vermelhoNegativo.esquerda;
			n1 = parente;
			t3 = n2.direita;
			t2 = n2.esquerda;
			t1 = n1.esquerda;
			n4.cor = VERMELHO;
			n3.cor = PRETO;
			n1.cor = PRETO;
			n3.setFilhoEsquerda(t3);

			T temp = n1.dado;
			n1.dado = n2.dado;
			n2.dado = temp;

			n2.setFilhoDireita(t2);
			n2.setFilhoEsquerda(t1);
			n1.setFilhoEsquerda(n2);
			filho = n4;
		}

		if (filho.esquerda != null && filho.esquerda.cor == VERMELHO) {
			corrigeVermelhoDuplo(filho.esquerda);

			return;
		}
		if (filho.direita != null && filho.direita.cor == VERMELHO) {
			corrigeVermelhoDuplo(filho.direita);

		}

	}

}
