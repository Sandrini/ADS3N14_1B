package view;

import java.util.Scanner;

import model.ArvoreRedBlack;

public class MenuRb {
	
	public void menuRb() {

		Menu menu = new Menu();
		ArvoreRedBlack<Integer> rb = new ArvoreRedBlack<Integer>();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("=======================================");
		System.out.println("*******RB*******");
		System.out.println("=======================================");
		System.out.println("1 - INSERIR");
		System.out.println("2 - REMOVER");
		System.out.println("3 - MOSTRAR");
		System.out.println("4 - VOLTAR AO MENU PRINCIPAL\n\n");
		System.out.print("ESCOLHA UMA OPÇÃO:\n\n");

		int escolha = scanner.nextInt();
		switch (escolha) {
		case 1:
			System.out.println("DIGITE UM NUMERO:");
			int readInt = scanner.nextInt();
			rb.addRBT(readInt);
			System.out.println("Comparacoes feitas: " + rb.contadorInsercao
					+ " Rotacoes feitas: " + rb.contadorInsercaoRemocao
					+ " Tamanho da arvore: " + rb.rbArvoreAltura);
			break;
		case 2:
			System.out.println("DIGITE UM NUMERO:");
			int readInt1 = scanner.nextInt();
			rb.removeRBT(readInt1);
			System.out.println("Comparacoes feitas: " + rb.contadorRemocao
					+ " Rotacoes feitas: " + rb.contadorInsercaoRemocao
					+ " Tamanho da arvore: " + rb.rbArvoreAltura);
			break;
		case 3:
			System.out.println("-AVL- ");
			System.out.println("IN-FIXA:  " + rb.infixa());
			System.out.println("PRE-FIXA: " + rb.prefixa());
			System.out.println("POS-FIXA: " + rb.posfixa());
			break;
		case 4:
			menu.menuPrincipal();
			break;

		default:
			System.out.println("DIGITE SOMENTE NÚMEROS!\n\n");
			System.out.println("DIGITE NOVAMENTE, ESCOLHA UMA OPÇAO:\n\n");
			break;
		}
	}
	
}
