package view;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import model.ArvoreAvl;
import model.ArvoreRedBlack;

/**
 * Classe do Menu Principal.
 * 
 */

public class Menu {
	public void menuPrincipal() {

		boolean finalizar = true;

		try {

			MenuAvl menuAvl = new MenuAvl();
			MenuRb menuRbt = new MenuRb();
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);

			ArvoreAvl<Integer> avlNormalInsertion = new ArvoreAvl<Integer>();
			ArvoreRedBlack<Integer> rbNormalInsertion = new ArvoreRedBlack<Integer>();

			int n = 50;
			int[] newArray = new int[n];
			Random rd = new Random();
			System.out.print("NUMEROS INSERIDOS:");
			
			for (int i = 0; i < newArray.length; i++) {
				newArray[i] = rd.nextInt(100);
				if (i > 0)
					for (int j = 0; j <= i - 1; j++)
						if (newArray[j] == newArray[i])
							i--;
			}

			for (int k = 0; k < newArray.length; k++) {
				System.out.print(newArray[k] + " ");
				avlNormalInsertion.addAVL(newArray[k]);
				rbNormalInsertion.addRBT(newArray[k]);
			}

			System.out.println();
			System.out.println("-AVL- INSERCAO NORMAL ");
			System.out.println("IN-FIXA:  " + avlNormalInsertion.infixa());

			System.out.println("PRE-FIXA: " + avlNormalInsertion.prefixa());
			System.out.println("POS-FIXA: " + avlNormalInsertion.posfixa());

			System.out
					.println("Rotacoes feitas pela AVL para inserir todos os elementos: "
							+ avlNormalInsertion.contadorInsercao
							+ " | Altura da AVL: "
							+ avlNormalInsertion.avlArvoreAltura);

			System.out.println();

			System.out.println("-RED BLACK- INSERCAO NORMAL ");
			System.out.println("IN-FIXA:  " + rbNormalInsertion.infixa());

			System.out.println("PRE-FIXA: " + rbNormalInsertion.prefixa());
			System.out.println("POS-FIXA: " + rbNormalInsertion.posfixa());
			System.out.println();
			System.out
					.println("Rotacoes feitas pela RB para inserir todos os elementos: "
							+ rbNormalInsertion.contadorInsercao
							+ " | Altura da RBT: "
							+ rbNormalInsertion.rbArvoreAltura);
			System.out.println();
			if (avlNormalInsertion.contadorRotacao < rbNormalInsertion.contadorRotacao) {
				System.out
						.println("AVL TEM DESEMPENHO MAIOR EM UM VETOR DESORDENADO");
			} else {
				System.out
						.println("RB TEM UM DESEMPENHO MAIOR EM UM VETOR DESORDENADO");
			}
			if (avlNormalInsertion.contadorRotacao == rbNormalInsertion.contadorRotacao) {

				System.out.println("EMPATE");
			}
			System.out.println("==============================================");

			ArvoreAvl<Integer> avlInOrderInsertion = new ArvoreAvl<Integer>();
			ArvoreRedBlack<Integer> rbInOrderInsertion = new ArvoreRedBlack<Integer>();

			Arrays.sort(newArray);
			for (int i = 0; i < n; i++) {

				avlInOrderInsertion.addAVL(newArray[i]);
				rbInOrderInsertion.addRBT(newArray[i]);
			}

			System.out.println("-AVL- INSERCAO ORDENADA ");
			System.out.println("IN-FIXA:  " + avlInOrderInsertion.infixa());
			System.out.println("PRE-FIXA: " + avlInOrderInsertion.prefixa());
			System.out.println("POS-FIXA: " + avlInOrderInsertion.posfixa());
			System.out.println("Rotacoes feitas pela AVL: "
					+ avlInOrderInsertion.contadorRotacao
					+ " | Altura da AVL: "
					+ avlInOrderInsertion.avlArvoreAltura);
			System.out.println();

			System.out.println("-RED BLACK- INSERCAO ORDENADA ");
			System.out.println("IN-FIXA:  " + rbInOrderInsertion.infixa());
			System.out.println(" PRE-FIXA: " + rbInOrderInsertion.prefixa());
			System.out.println("POS-FIXA: " + rbInOrderInsertion.posfixa());
			System.out.println();
			System.out
					.println("Rotacoes feitas pela RB para inserir todos os elementos: "
							+ rbInOrderInsertion.contadorRemocao
							+ " | Altura da RB: "
							+ rbInOrderInsertion.rbArvoreAltura);
			System.out.println();

			if (avlInOrderInsertion.contadorRotacao < rbInOrderInsertion.contadorRotacao) {
				System.out
						.println("AVL TEM DESEMPENHO MAIOR EM UM VETOR ORDENADO");
			} else {
				System.out
						.println("RB TEM DESEMPENHO MAIOR EM UM VETOR ORDENADO");
			}
			if (avlInOrderInsertion.contadorRotacao == rbInOrderInsertion.contadorRotacao) {

				System.out.println("EMPATE");
			}
			System.out.println("==============================================");

			ArvoreAvl<Integer> avlRevertedInsertion = new ArvoreAvl<Integer>();
			ArvoreRedBlack<Integer> rbRevertedInsertion = new ArvoreRedBlack<Integer>();
		
			for (int i = newArray.length - 1; i >= 0; i--) {

				avlRevertedInsertion.addAVL(newArray[i]);
				rbRevertedInsertion.addRBT(newArray[i]);

			}
			System.out.println("-AVL- INSERCAO INVERSA ");
			System.out.println("IN-FIXA:  " + avlRevertedInsertion.infixa());

			System.out.println("PRE-FIXA: " + avlRevertedInsertion.prefixa());
			System.out.println("POS-FIXA: " + avlRevertedInsertion.posfixa());
			System.out.println("Rotacoes feitas pela AVL: "
					+ avlRevertedInsertion.contadorRotacao
					+ " | Altura da AVL: "
					+ avlRevertedInsertion.avlArvoreAltura);
			System.out.println();

			System.out.println("-RED BLACK- INSERCAO INVERSA ");
			System.out.println("IN-FIXA:  " + rbRevertedInsertion.infixa());

			System.out.println("PRE-FIXA: " + rbRevertedInsertion.prefixa());
			System.out.println("POS-FIXA: " + rbRevertedInsertion.posfixa());
			System.out.println();
			System.out
					.println("Rotacoes feitas pela RB para inserir todos os elementos: "
							+ rbRevertedInsertion.contadorRemocao
							+ " | Altura da RB: "
							+ rbRevertedInsertion.rbArvoreAltura);
			System.out.println();
			if (avlRevertedInsertion.contadorRotacao < rbRevertedInsertion.contadorRotacao) {
				System.out
						.println("AVL TEM DESEMPENHO MAIOR EM UM VETOR ORDENADO INVERSAMENTE");
			} else {
				System.out
						.println("RB TEM DESEMPENHO MAIOR EM UM VETOR ORDENADO INVERSAMENTE");
			}
			if (avlRevertedInsertion.contadorRotacao == rbRevertedInsertion.contadorRotacao) {

				System.out.println("EMPATE");
			}

			System.out.println("==============================================");

			System.out.println();
			do {

				System.out.println("1 - AVL");
				System.out.println("2 - RB");
				System.out.println("7 - SAIR\n\n");
				System.out.print("ESCOLHA UMA OPÇÃO:\n\n");

				int escolha = scanner.nextInt();

				switch (escolha) {
				case 1:
					menuAvl.menuAvl();
					break;
				case 2:
					menuRbt.menuRb();
					break;

				case 7:
					System.out.println("FECHANDO A AGENDA...\n\n");
					finalizar = false;
					System.exit(0);
					break;
				default:
					System.out
							.println("DIGITE SOMENTE NÚMEROS!\n\n");
					System.out
							.println("DIGITE NOVAMENTE, ESCOLHA UMA OPÇAO:\n\n");
					break;
				}
			} while (finalizar);
		} catch (Exception e) {

			System.out.println("DIGITE SOMENTE NÚMEROS!\n\n");
			menuPrincipal();
		}

	}
}
