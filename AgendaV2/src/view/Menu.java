package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import model.Agenda;
import model.Pessoa;

/**
 * Classe do Menu Principal e funcionalidades da Agenda.
 * 
 */

public class Menu {
	
	public static final String CAMINHO_ARQUIVO = "agenda.txt";

	public void menuPrincipal() {

		boolean finalizar = true;

		try {

			Agenda agenda = new Agenda();
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			lerArquivo(agenda);

			do {
				System.out
						.println("******LISTA TELEFÔNICA******");
				System.out
						.println("=============================================");
				System.out.println("1 - NOVO CONTATO");
				System.out.println("2 - IMPRIMIR PRE-FIXA");
				System.out.println("3 - IMPRIMIR POS-FIXA");
				System.out.println("4 - IMPRIMIR IN-FIXA");
				System.out.println("5 - PESQUISAR NOME");
				System.out.println("6 - REMOVER CONTATO");
				System.out.println("7 - BUSCA EM LARGURA");
				System.out.println("8 - BUSCA EM PROFUNDIDADE");
				System.out.println("0 - SAIR\n\n");
				System.out.print("DIGITE UMA OPÇÃO:\n\n");

				int escolha = scanner.nextInt();

				switch (escolha) {
				case 1:
					Pessoa p = inserirPessoa();
					agenda.inserePessoa(p);
					break;

				case 2:
					List<Pessoa> pessoasPreFixa = agenda
							.retornoTravessiaPreFixa();

					imprimeAgenda(pessoasPreFixa);

					break;

				case 3:
					List<Pessoa> pessoasPosFixa = agenda
							.retornoTravessiaPosFixa();

					imprimeAgenda(pessoasPosFixa);

					break;
				case 4:
					List<Pessoa> pessoasInFixa = agenda
							.retornoTravessiaInFixa();

					imprimeAgenda(pessoasInFixa);

					break;
				case 5:
					Pessoa pSearch = armazenaNome();
					Pessoa pReturn = agenda.buscaPessoa(pSearch);
					System.out.println(pReturn);

					break;

				case 6:
					Pessoa pDelete = armazenaNome();

					agenda.remove(pDelete);

					break;

				case 7:
					Pessoa pBuscar2 = armazenaNome();
					Pessoa pResultado2 = agenda.buscaLargura(pBuscar2);
					System.out.println(pResultado2);
					break;

				case 8:
					Pessoa pBuscar3 = armazenaNome();
					Pessoa pResultado3 = agenda.buscaProfundidade(pBuscar3);
					System.out.println(pResultado3);
					break;

				case 0:
					escreverArquivo(agenda);
					System.out.println("FENCHANDO A AGENDA...\n\n");
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

	/**
	 * 
	 * Metodo para inserção de uma nova pessoa na lista.
	 * 
	 */
	public Pessoa inserirPessoa() {

		Pessoa p = new Pessoa();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.print("DIGITE O NOME:\n\n");
		String nome = scanner.next();
		p.setNome(nome);

		System.out.print("DIGITE O TELEFONE:\n\n");
		String telefone = scanner.next();
		p.setTelefone(telefone);

		return p;
	}

	/**
	 * Metodo que busca o nome a ser pesquisado ou deletado.
	 * 
	 */
	
	private static Pessoa armazenaNome() {

		Pessoa p = new Pessoa();

		System.out.print("DIGITE UM NOME PARA PESQUISA:\n\n");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String nome = scanner.next();

		p.setNome(nome);

		return p;

	}

	/**
	 * Metodo que imprime a Agenda.
	 * 
	 */

	private static void imprimeAgenda(List<Pessoa> pessoas) {

		if (pessoas != null) {

			for (int i = 0; i < pessoas.size(); i++) {

				System.out.println(pessoas.get(0));

			}

		} else {

			System.out.println("NOME NÃO ENCONTRADO");

		}

	}

	/**
	 * Metodo que escreve no arquivo de texto.
	 * 
	 */
	public static void escreverArquivo(Agenda agenda) {
		try {

			List<Pessoa> pessoas = agenda.retornoTravessiaPreFixa();

			if (pessoas != null) {

				BufferedWriter writer = new BufferedWriter(new FileWriter(
						CAMINHO_ARQUIVO));

				for (int i = 0; i < pessoas.size(); i++) {

					Pessoa p = pessoas.get(i);
					writer.write(p.getNome());
					writer.newLine();
					writer.write(p.getTelefone());
					writer.newLine();
					writer.flush();
				}

				writer.close();

			}

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	/**
	 * Metodo que faz a leitura do arquivo e insere na lista.
	 * 
	 */
	public static void lerArquivo(Agenda agenda) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					CAMINHO_ARQUIVO));

			String nome;
			String telefone;

			while (reader.ready()) {
				nome = reader.readLine();
				telefone = reader.readLine();
				Pessoa p = new Pessoa(nome, telefone);
				agenda.inserePessoa(p);
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
