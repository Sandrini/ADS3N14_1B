package model;

/**
 * Arvore AVL.
 *  
 */

public class ArvoreAvl<T extends Comparable<T>> extends Arvore<T> {
	
	public int contadorRotacao;
	public int contadorInsercaoRemocao;
	public int contadorRemocao;
	public int avlArvoreAltura;

	public ArvoreAvl() {
		raiz = null;
		contadorRotacao = 0;

	}

	public void addAVL(T dado) {
		contadorInsercao = 0;
		contadorInsercaoRemocao = 0;
		Nodo<T> n = new Nodo<T>(dado);
		adicionarRecursivo(n, this.raiz);
		balancoRecursivo(n);

		avlArvoreAltura = arvoreAltura(this.raiz);
	}

	public void balancoRecursivo(Nodo<T> atual) {

		setBalanco(atual);
		int balanco = atual.fatorBalanco;

		if (balanco == -2) {

			if (altura(atual.esquerda.esquerda) >= altura(atual.esquerda.direita)) {
				atual = rotacaoDireita(atual);
				contadorRotacao++;
				contadorInsercaoRemocao++;
			} else {
				atual = rotacaoDuplaEsquerdaDireita(atual);
				contadorRotacao += 2;
				contadorInsercaoRemocao += 2;
			}
		} else if (balanco == 2) {
			if (altura(atual.direita.direita) >= altura(atual.direita.esquerda)) {
				atual = rotacaoEsquerda(atual);
				contadorRotacao++;
				contadorInsercaoRemocao++;
			} else {
				atual = rotacaoDuplaDireitaEsquerda(atual);
				contadorRotacao += 2;
				contadorInsercaoRemocao += 2;
			}
		}

		if (atual.parente != null) {
			balancoRecursivo(atual.parente);
		} else {
			this.raiz = atual;
			
		}
	}

	public void removeAvl(T dado) {
		contadorRemocao = 0;
		contadorInsercaoRemocao = 0;
		removeAvlRecursivo(dado, this.raiz);
	}

	
	public void removeAvlRecursivo(T dado, Nodo<T> arvore) {
		if (arvore == null) {
			return;
		} else {
			if (arvore.dado.compareTo(dado) > 0) {
				contadorRemocao++;
				removeAvlRecursivo(dado, arvore.esquerda);
			} else if (arvore.dado.compareTo(dado) < 0) {
				contadorRemocao++;
				removeAvlRecursivo(dado, arvore.direita);
			} else if (arvore.dado.compareTo(dado) == 0) {
				contadorRemocao++;
				removerNodoEncontrado(arvore);
			}
		}
	}

	public void removerNodoEncontrado(Nodo<T> branch) {
		Nodo<T> temp1;

		if (branch.esquerda == null || branch.direita == null) {
			if (branch.parente == null) {
				this.raiz = null;
				branch = null;
				return;
			}
			temp1 = branch;
		} else {
			
			temp1 = successor(branch);
			branch.dado = temp1.dado;
		}

		Nodo<T> temp2;
		if (temp1.esquerda != null) {
			temp2 = temp1.esquerda;
		} else {
			temp2 = temp1.direita;
		}

		if (temp2 != null) {
			temp2.parente = temp1.parente;
		}

		if (temp1.parente == null) {
			this.raiz = temp2;
		} else {
			if (temp1 == temp1.parente.esquerda) {
				temp1.parente.esquerda = temp2;
			} else {
				temp1.parente.direita = temp2;
			}
			
			balancoRecursivo(temp1.parente);
		}
		temp1 = null;
	}

	public Nodo<T> rotacaoEsquerda(Nodo<T> n) {

		Nodo<T> temp = n.direita;
		temp.parente = n.parente;

		n.direita = temp.esquerda;

		if (n.direita != null) {
			n.direita.parente = n;
		}

		temp.esquerda = n;
		n.parente = temp;

		if (temp.parente != null) {
			if (temp.parente.direita == n) {
				temp.parente.direita = temp;
			} else if (temp.parente.esquerda == n) {
				temp.parente.esquerda = temp;
			}
		}

		setBalanco(n);
		setBalanco(temp);

		return temp;
	}

	public Nodo<T> rotacaoDireita(Nodo<T> n) {

		Nodo<T> temp = n.esquerda;
		temp.parente = n.parente;

		n.esquerda = temp.direita;

		if (n.esquerda != null) {
			n.esquerda.parente = n;
		}

		temp.direita = n;
		n.parente = temp;

		if (temp.parente != null) {
			if (temp.parente.direita == n) {
				temp.parente.direita = temp;
			} else if (temp.parente.esquerda == n) {
				temp.parente.esquerda = temp;
			}
		}

		setBalanco(n);
		setBalanco(temp);

		return temp;
	}

	public Nodo<T> rotacaoDuplaEsquerdaDireita(Nodo<T> u) {
		u.esquerda = rotacaoEsquerda(u.esquerda);

		return rotacaoDireita(u);
	}

	public Nodo<T> rotacaoDuplaDireitaEsquerda(Nodo<T> u) {
		u.direita = rotacaoDireita(u.direita);

		return rotacaoEsquerda(u);
	}

	public Nodo<T> successor(Nodo<T> q) {
		if (q.direita != null) {
			Nodo<T> r = q.direita;
			while (r.esquerda != null) {
				r = r.esquerda;
			}
			return r;
		} else {
			Nodo<T> p = q.parente;
			while (p != null && q == p.direita) {
				q = p;
				p = q.parente;
			}
			return p;
		}
	}

	private int altura(Nodo<T> atual) {
		if (atual == null) {
			return -1;
		}
		if (atual.esquerda == null && atual.direita == null) {
			return 0;
		} else if (atual.esquerda == null) {
			return 1 + altura(atual.direita);
		} else if (atual.direita == null) {
			return 1 + altura(atual.esquerda);
		} else {
			return 1 + maximum(altura(atual.esquerda), altura(atual.direita));
		}
	}

	private int maximum(int a, int b) {
		if (a >= b) {
			return a;
		} else {
			return b;
		}
	}

	private void setBalanco(Nodo<T> atual) {
		atual.fatorBalanco = altura(atual.direita) - altura(atual.esquerda);
	}

}
