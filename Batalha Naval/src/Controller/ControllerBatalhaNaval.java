package Controller;

import java.util.ArrayList;

import Model.Tabuleiro;
import View.ViewBatalhaNaval;

public class ControllerBatalhaNaval 
{
	private Tabuleiro tabuleiro = null;
	private ViewBatalhaNaval view = null;
	private ArrayList<String> index = new ArrayList<String>();
	String mensagemAnterior = "";

	public void startGame(ViewBatalhaNaval view) {

		index.add("A");
		index.add("B");
		index.add("C");
		index.add("D");
		index.add("E");
		index.add("F");
		index.add("G");
		index.add("H");
		index.add("I");
		index.add("J");

		this.tabuleiro = new Tabuleiro();
		this.view = view;
		tabuleiro.iniciar();
		view.mensagem(" Início do Jogo ");
	}

	public void play() {
		imprimirTabuleiro();
		imprimirPontuacao();
		if (mensagemAnterior != "")
			view.mensagem(mensagemAnterior);

		int linha = 99;
		int coluna = 99;

		do {
			try {
				linha = Integer.parseInt(view.leitura("Linha")) - 1;
				if (linha < 0 || linha > 9)
					view.mensagem("Valor inválido.");
			} catch (Exception e) {
				view.mensagem("Valor inválido");
			}
		} while (linha > 9 || linha < 0);

		do {
			try {
				coluna = index.indexOf(view.leitura("Coluna").toUpperCase());
				if (coluna < 0 || coluna > 9)
					view.mensagem("Valor inválido.");
			} catch (Exception e) {
				view.mensagem("Valor inválido");
			}
		} while (coluna > 9 || coluna < 0);

		switch (tabuleiro.fire(linha, coluna)) {
		case -1:
			mensagemAnterior = "Falhou...";
			break;
		case 2:
			mensagemAnterior = "Acertou o alvo!";
			break;
		case 7:
			String tipo = tabuleiro.tipoNavio(linha, coluna);
			mensagemAnterior = "Voce destruiu um " + tipo
					+ "!";
			break;
		default:
			mensagemAnterior = "Esta posicao já recebeu um tiro!";
		}

		if (tabuleiro.fimDeJogo())
			if (tabuleiro.getPontos() < 1) {
				if (mensagemAnterior != "")
					view.mensagem(mensagemAnterior);
				view.mensagem("Game Over!");
			} else {
				if (mensagemAnterior != "")
					view.mensagem(mensagemAnterior);
				view.mensagem("Parabéns você Venceu!\nVocê fez "
						+ tabuleiro.getPontos() + " pontos!");
			}
		else
			play();
	}

	private void imprimirPontuacao() {
		view.score(tabuleiro.getPontos());

	}

	private void imprimirTabuleiro() {
		view.imprimirTabuleiro(tabuleiro.getMatriz());

	}
}
