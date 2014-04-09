package View;


import java.util.Scanner;

import Controller.ControllerBatalhaNaval;
import Model.Coordenada;

public class ViewBatalhaNaval
{
	private Scanner scan = new Scanner(System.in);

	public void menuPrincipal() {
		boolean finalizar = true;

		do {
			mensagem(" Jogo Batalha Naval\n\n1 - Jogar\n\n2 - Sair\n\nEscolha uma Opção:");
			int escolha = scan.nextInt();

			switch (escolha) {
			case 1:
				mensagem("Play!\n");
				ControllerBatalhaNaval crt = new ControllerBatalhaNaval();
				ViewBatalhaNaval view = new ViewBatalhaNaval();

				crt.startGame(view);
				crt.play();
				break;
			case 2:
				mensagem("Fim de jogo...\n OK!");
				finalizar = false;
				System.exit(0);
				break;

			default:
				mensagem("Opção invalida, Digite Novamente.\n\n");
				break;
			}

		} while (finalizar);

	}

	public String leitura(String entrada) {
		System.out.println("\n" + entrada + ": ");
		return scan.nextLine();
	}

	public void mensagem(String mensagem) {
		System.out.println(mensagem);
	}

	public void score(int pontos) {
		System.out.println(pontos);
	}

	public void mensagemErro(String mensagem) {
		mensagem("Erro: " + mensagem);
	}

	public void imprimirTabuleiro(Coordenada[][] matriz) {
		System.out.printf("\t A\t B\t C\t D\t E\t F\t G\t H\t I\t J\n");
		for (int x = 0; x < matriz.length; x++) {
			System.out.printf("%d", x + 1);
			for (int y = 0; y < matriz[x].length; y++) {
				System.out.printf("\t %s", (matriz[x][y].aJogada() ? "."
						: (matriz[x][y].getNavio() != null ? "o" : "-")));
			}
			System.out.println();
		}
	}
}
