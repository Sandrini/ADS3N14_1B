package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import model.Agenda;
import model.Pessoa;

/**
 * Classe que imprime o Menu Principal e as funcionalidades da Agenda.
 * 
 */

public class Menu {

	public void menuPrincipal() {

		boolean finalizar = true;

		try {

			Agenda agenda = new Agenda();
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			lerArquivo(agenda);

			do {
				System.out.println("******AGENDA TELEFÔNICA******");
				System.out
						.println("=================================================");
				System.out.println("1 - NOVO CONTATO");
				System.out.println("2 - TODOS OS CONTATOS");
				System.out
						.println("3 - LISTAR UM CONTATO BASEADO NA INICIAL DO NOME");
				System.out.println("4 - LISTAR O PROXIMO CONTATO");
				System.out.println("5 - LISTAR O CONTATO ANTERIOR");
				System.out.println("6 - REMOVER UM CONTATO");
				System.out.println("7 - SAIR\n\n");
				System.out.print("DIGITE UMA OPÇÃO:\n\n");

				int escolha = scanner.nextInt();

				switch (escolha) {
				case 1:
					agenda.insertPessoa(inserirPessoa());
					break;
				case 2:
					agenda.printList();
					break;
				case 3:
					agenda.listByChar(getLetra());
					break;
				case 4:
					agenda.next();
					Pessoa p = agenda.getContatoAtual();
					if (p != null) {
						System.out.println(p);
					} else {
						System.out.println("LISTA VAZIA.\n\n");
					}
					break;
				case 5:
					agenda.prev();
					Pessoa p1 = agenda.getContatoAtual();
					if (p1 != null) {
						System.out.println(p1);
					} else {
						System.out.println("LISTA VAZIA.\n\n");
					}
					break;

				case 6:
					agenda.removePessoa();
					break;

				case 7:
					System.out.println("FENCHANDO A AGENDA...\n\n");
					finalizar = false;
					System.exit(0);
					break;
				default:
					System.out.println("DIGITE SOMENTE NÚMEROS!\n\n");
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

	/**
	 * Metodo para inserção de uma nova pessoa na lista.
	 * 
	 */
	
	public Pessoa inserirPessoa() throws IOException {

		BufferedWriter gravador = new BufferedWriter(new FileWriter(
				"C:\\agenda.txt", true));

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Pessoa p = new Pessoa();
		System.out.print("Digite o nome: ");
		String nome = scanner.next();
		gravador.write((nome).toUpperCase());
		p.setNome(nome);

		gravador.newLine();

		System.out.print("Digite o telefone: ");
		String telefone = scanner.next();
		gravador.write(telefone);
		p.setTelefone(telefone);

		gravador.newLine();
		gravador.flush();
		gravador.close();

		return p;
	}

	/**
	 * Metodo para capturar a letra inicial do nome, pesquisado na Agenda.
	 * 
	 */
	public static String getLetra() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.print("Insira a letra por qual deseja procurar: ");
		String termo = scanner.next();
		return termo;
	}

	/**
	 * Metodo que faz a leitura do arquivo e insere na lista.
	 * 
	 */

	public static void lerArquivo(Agenda agenda) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					"C:\\agenda.txt"));

			String nome;
			String telefone;

			while (reader.ready()) {
				nome = reader.readLine();
				telefone = reader.readLine();
				Pessoa p = new Pessoa(nome, telefone);
				agenda.insertPessoa(p);
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}