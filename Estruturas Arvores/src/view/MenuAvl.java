package view;

import java.util.Scanner;

import model.ArvoreAvl;

public class MenuAvl {

	public void menuAvl() {

		Menu menu = new Menu();
		ArvoreAvl<Integer> avl = new ArvoreAvl<Integer>();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("============================================");
		System.out.println("*******AVL*******");
		System.out.println("============================================");
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
			avl.addAVL(readInt);
			System.out.println("Comparacoes feitas: " + avl.contadorInsercao
					+ " Rotacoes feitas: " + avl.contadorInsercaoRemocao
					+ " Tamanho da arvore: " + avl.avlArvoreAltura);
			break;
		case 2:
			System.out.println("DIGITE UM NUMERO:");
			int readInt1 = scanner.nextInt();
			avl.removeAvl(readInt1);
			System.out.println("Comparacoes feitas: " + avl.contadorRemocao
					+ " Rotacoes feitas: " + avl.contadorInsercaoRemocao
					+ " Tamanho da arvore: " + avl.avlArvoreAltura);
			break;
		case 3:
			System.out.println("-AVL- ");
			System.out.println("IN-FIXA:  " + avl.infixa());
			System.out.println("PRE-FIXA: " + avl.prefixa());
			System.out.println("POS-FIXA: " + avl.posfixa());
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
