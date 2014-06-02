package model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Arvore<T extends Comparable<T>> {
	protected Nodo<T> raiz;

	private int largura = 0;

	private int contadorDfs = 0;

	private int contadorBfs = 0;

	public int contadorInsercao = 0;

	private int arvoreAltura = 0;

	private boolean achou = false;

	public Arvore() {

		raiz = null;
		largura = 0;
	}

	public void adicionar(T dado) {

		Nodo<T> n = new Nodo<T>(dado);
		adicionarRecursivo(n, this.raiz);
		largura += 1;
		contadorInsercao = 0;
		arvoreAltura = arvoreAltura(this.raiz);
	}

	public void adicionarRecursivo(Nodo<T> novoNodo, Nodo<T> arvore) {

		if (arvore == null) {

			this.raiz = novoNodo;

		} else {
			
			
			if (novoNodo.dado.compareTo(arvore.dado) <= 0) {
				contadorInsercao++;
				if (arvore.esquerda == null) {
					arvore.esquerda = novoNodo;
					novoNodo.parente = arvore;
				} else {
					adicionarRecursivo(novoNodo, arvore.esquerda);

				}

			} else if (novoNodo.dado.compareTo(arvore.dado) > 0) {
				contadorInsercao++;
				if (arvore.direita == null) {
					arvore.direita = novoNodo;
					novoNodo.parente = arvore;

				} else {
					adicionarRecursivo(novoNodo, arvore.direita);

				}

			}
		}
	}

	public int arvoreAltura(Nodo<T> arvore) {

		if (arvore == null) {
			return 0;
		}

		int alturaEsquerda = arvoreAltura(arvore.getEsquerda());
		int alturaDireita = arvoreAltura(arvore.getDireita());

		if (alturaEsquerda > alturaDireita)
			return alturaEsquerda + 1;
		else
			return alturaDireita + 1;
	}

	public boolean removerBST(T dado) {
	
		if (!ehVazio()) {
			try {
				achou = false;
				raiz = removerRecursivo(dado, this.raiz);

			} catch (Exception e) {

			}
		} else {
			System.out.println("Arvore vazia.");
		}

		largura -= 1;
		return achou;
	}

	private Nodo<T> removerRecursivo(T dado, Nodo<T> arvore) {

		if (arvore == null) {
			return null;
		}

		if (dado.compareTo(arvore.getDado()) == 0) {

			achou = true;
			arvore = removerNodo(arvore);
			return arvore;
		}

		if (arvore.getEsquerda() != null) {
			arvore.setEsquerda(removerRecursivo(dado, arvore.getEsquerda()));
		}

		if (arvore.getDireita() != null) {
			arvore.setDireita(removerRecursivo(dado, arvore.getDireita()));
		}

		return arvore;
	}

	private Nodo<T> removerNodo(Nodo<T> arvore) {

		if (arvore.getEsquerda() == null) { 
			return arvore.getDireita();
		} else if (arvore.getDireita() == null) {
													
			return arvore.getEsquerda();
		} else {

			Nodo<T> temp = esquerdaMaisAlto(arvore.getEsquerda());
			
			arvore.setDado(temp.getDado());

			arvore.setEsquerda(removerRecursivo(temp.getDado(),
					arvore.getEsquerda()));
		}
		return arvore;
	}

	private Nodo<T> esquerdaMaisAlto(Nodo<T> arvore) {

		while (arvore.getDireita() != null) {
			arvore = arvore.getDireita();
		}
		return arvore;
	}

	
	public String contemDfs(T dado) {

		return contemDfsRecursivo(dado, this.raiz);
	}

	private String contemDfsRecursivo(T dado, Nodo<T> raiz) {
		if (raiz == null) {
			return null;
		}

		Stack<Nodo<T>> stack = new Stack<Nodo<T>>();
		stack.push(raiz);

		while (!stack.isEmpty()) {
			Nodo<T> tempNode;
			tempNode = stack.pop();
			contadorDfs++;

			if (dado.compareTo(tempNode.dado) == 0) {

				return dado + " altura:" + stack.size();

			}
			if (tempNode.getEsquerda() != null) {

				stack.push(tempNode.getEsquerda());
			}

			if (tempNode.getDireita() != null) {

				stack.push(tempNode.getDireita());
			}
		}

		return null;
	}

	public T contemBfs(T dado) {
		return contemBfsRecursivo(dado, this.raiz);
	}

	public T contemBfsRecursivo(T dado, Nodo<T> raiz) {
		if (raiz == null) {
			return null;
		}

		Queue<Nodo<T>> q = new LinkedList<Nodo<T>>();
		Nodo<T> tempNode;
		q.add(raiz);

		while (!q.isEmpty()) {

			tempNode = q.remove();

			contadorBfs++;

			if (dado.compareTo(tempNode.getDado()) == 0) {
				return dado;
			} else {
				if (tempNode.getEsquerda() != null)
					q.add(tempNode.getEsquerda());

				if (tempNode.getDireita() != null)
					q.add(tempNode.getDireita());
			}
		}
		return null;
	}

	public int getTamanho() {
		return largura;
	}

	public int getArvoreAltura() {
		return arvoreAltura;
	}

	public int getContadorDfs() {
		return contadorDfs;
	}

	public int getContadorBfs() {
		return contadorBfs;
	}

	public boolean ehVazio() {
		return (raiz == null);
	}

	String bstList = "";

	public String infixa() {
		bstList = "";
		return infixa(this.raiz);
	}

	public String infixa(Nodo<T> arvore) {

		if (arvore != null) {
			infixa(arvore.getEsquerda());
			bstList += arvore.getDado() + " ";
			infixa(arvore.getDireita());
		}

		return bstList;
	}

	public String prefixa() {
		bstList = "";
		return prefixa(this.raiz);
	}

	public String prefixa(Nodo<T> branch) {

		String toReturn = "";
		
		toReturn += branch.getDado().toString() + " ";
		
		if (branch.getEsquerda() != null) {
			toReturn += prefixa(branch.getEsquerda());
		}
		
		if (branch.getDireita() != null) {
			toReturn += prefixa(branch.getDireita());
		}

		return toReturn;
	}

	public String posfixa() {
		bstList = "";
		return posfixa(this.raiz);
	}

	public String posfixa(Nodo<T> branch) {

		String toReturn = "";
		
		if (branch.getEsquerda() != null) {
			toReturn += posfixa(branch.getEsquerda()) + " ";
		}
		
		if (branch.getDireita() != null) {
			toReturn += posfixa(branch.getDireita()) + " ";
		}
		
		toReturn += branch.getDado().toString();

		return toReturn;
	}
}