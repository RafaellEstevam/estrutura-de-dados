package app;

import javax.swing.JOptionPane;

import tad.List;
import tad.Peca;
import tad.Queue;
import tad.Stack;

public class AppReparoDePecas {

	public static void main(String[] args) {

		JOptionPane.showMessageDialog(null,
				"Projeto elaborado por Rafaell Estevam");

		List listaRelacaoPecas = new List();
		Queue filaProcessos = new Queue();

		boolean retornaMP = true;

		do {
			String opcaoMP = JOptionPane.showInputDialog("       =========MENU=========\n" + "1.Relação de Peças\n"
					+ "2.Processos de Desmontagem\n" + "3.Reparar Objeto\n" + "4.Sair");

			switch (opcaoMP) {
			case "1":
				boolean opcaoInvalida;

				do {
					opcaoInvalida = false;
					String opcaoM1 = JOptionPane.showInputDialog(
							"=========RELAÇÃO DE PEÇAS=========\n" + "1.Cadastrar Nova Relação de Peças\n"
									+ "2.Exibir Relação de Peças Atual\n" + "3.Voltar ao Menu Principal");

					switch (opcaoM1) {
					case "1":

						if (!listaRelacaoPecas.isEmpty()) {
							listaRelacaoPecas.zerarLista();

						}

						if (!filaProcessos.isEmpty()) {
							filaProcessos.zerarFila();
						}

						cadastrarRelacaoPecas(listaRelacaoPecas);
						break;
					case "2":

						if (!listaRelacaoPecas.isEmpty()) {
							JOptionPane.showMessageDialog(null,
									"========Relação de Peças========\n" + listaRelacaoPecas.imprimirInfoTotalPecas());
						} else {
							JOptionPane.showMessageDialog(null, "A Relação de Peças está Vazia! ");
							opcaoInvalida = true;
						}

						break;
					case "3":
						break;
					default:
						JOptionPane.showMessageDialog(null, "Opção inválida!");
						opcaoInvalida = true;
						break;
					}
				} while (opcaoInvalida);

				break;
			case "2":

				do {
					opcaoInvalida = false;
					String opcaoM1 = JOptionPane.showInputDialog(
							"=========PROCESSOS DE DESMONTAGEM=========\n" + "1.Configurar Processos de Desmontagem\n"
									+ "2.Exibir Configuração Atual \n" + "3.Voltar ao Menu Principal");

					switch (opcaoM1) {
					case "1":
						if (listaRelacaoPecas.isEmpty()) {
							JOptionPane.showMessageDialog(null, "A Relação de Peças Não Pode Estar Vazia!");
						} else {
							cadastrarProcessosDesmontagem(listaRelacaoPecas, filaProcessos);
						}

						break;
					case "2":

						if (!filaProcessos.isEmpty()) {
							JOptionPane.showMessageDialog(null,
									"       ====Ordem De Desmontagem Para Reparo====\n    " + filaProcessos.toString());
						} else {
							JOptionPane.showMessageDialog(null, "Nenhum Processo foi Cadastrado! ");
							opcaoInvalida = true;
						}

						break;
					case "3":
						break;
					default:
						JOptionPane.showMessageDialog(null, "Opção inválida!");
						opcaoInvalida = true;
						break;
					}
				} while (opcaoInvalida);

				break;
			case "3":

				Peca pecaCDefeito = null;
				boolean qtdSuficiente = true;

				if (listaRelacaoPecas.isEmpty() || filaProcessos.isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"A Relação de Peças e os Processos de Desmontagem Devem Estar Cadastrados!");
				} else {
					do {
						opcaoInvalida = false;
						try {
							int pos = Integer.parseInt(JOptionPane.showInputDialog(
									"    ========Relação de Peças========\n" + listaRelacaoPecas.toString()
											+ "\nInforme o Número da Peça com defeito: "));

							qtdSuficiente = listaRelacaoPecas.qtdEstoqueSuficiente(pos - 1);

							if (!qtdSuficiente) {
								JOptionPane.showMessageDialog(null,
										"A Operação Não Pode Ser Realizada Pois Não Existe Quantidade Suficiente da Peça No Estoque!");

							} else {
								pecaCDefeito = listaRelacaoPecas.get(pos - 1);
							}

						} catch (IllegalArgumentException e) {
							JOptionPane.showMessageDialog(null, "Selecione Um Número Da Lista!");
							opcaoInvalida = true;
						}
					} while (opcaoInvalida);

					if (qtdSuficiente) {

						repararPeca(pecaCDefeito, filaProcessos);
					}

				}

				break;
			case "4":

				System.exit(0);
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opção inválida!");
				break;
			}

		} while (retornaMP);

	}// fim do Main

	private static void cadastrarRelacaoPecas(List listaPecas) {

		int resposta;
		boolean opcaoInvalida;
		do {
			String nomePeca = JOptionPane.showInputDialog("Informe o Nome da Peça");

			do {
				opcaoInvalida = false;
				try {
					int qtdPeca = Integer
							.parseInt(JOptionPane.showInputDialog("Informe a Quantidade da Peça no Estoque"));

					listaPecas.insert(new Peca(nomePeca, qtdPeca));

				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Informe Somente Números Inteiros!");
					opcaoInvalida = true;
				}
			} while (opcaoInvalida);

			resposta = JOptionPane.showConfirmDialog(null, "Deseja Cadastrar Outra Peça?", "Cadastro de Peças",
					JOptionPane.YES_NO_OPTION);

		} while (resposta == JOptionPane.YES_OPTION);

		JOptionPane.showMessageDialog(null, "Relação de Peças Cadastrada com Sucesso!");

		JOptionPane.showMessageDialog(null, "======Relação de Peças======\n" + listaPecas.imprimirInfoTotalPecas());

		JOptionPane.showMessageDialog(null, "Cadastre um Novo Processo de Desmontagem");

	}

	private static void cadastrarProcessosDesmontagem(List listaPecas, Queue filaProcessos) {

		if (!filaProcessos.isEmpty()) {
			filaProcessos.zerarFila();
		}

		boolean recadastrarFila = false;

		do {

			int pos = 0;
			int cont = 1;
			boolean opcaoInvalida;
			recadastrarFila = false;

			while (cont <= listaPecas.size()) {
				do {
					opcaoInvalida = false;
					try {
						pos = Integer.parseInt(JOptionPane.showInputDialog(
								"              ========Relação de Peças========\n" + listaPecas.toString()
										+ "\nQuantidade de Peças Incluídas na Fila: " + filaProcessos.size()
										+ "\nInforme o Número da Peça a Ser Inclusa na Fila de Desmontagem: "));

						filaProcessos.enqueue(listaPecas.get(pos - 1));
						JOptionPane.showMessageDialog(null, "Peça Incluída Com Sucesso!");

					} catch (IllegalArgumentException e) {
						JOptionPane.showMessageDialog(null, "Selecione Um Número Da Lista!");
						opcaoInvalida = true;
					}

				} while (opcaoInvalida);
				cont++;
			}

			int resposta = JOptionPane.showConfirmDialog(null,
					"       ====Ordem De Desmontagem Para Reparo====\n    " + filaProcessos.toString()
							+ "\n   A Fila de Desmontagem está Correta?",
					"Confirmar Fila De Processos", JOptionPane.YES_NO_OPTION);

			if (resposta == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, "Processo de Desmontagem Cadastrado com Sucesso!");
			} else {
				if (resposta == JOptionPane.NO_OPTION) {
					filaProcessos.zerarFila();
					JOptionPane.showMessageDialog(null, "Cadastre novamente a Fila de Desmontagem");
					recadastrarFila = true;
				}
			}
		} while (recadastrarFila);

	}

	private static void repararPeca(Peca pecaDefeito, Queue filaProcessosOriginal) {
		Stack pilhaDesmontagem = new Stack();
		Peca pecaAtual = null;
		boolean pecaDefeitoLocalizada = false;

		Queue filaProcessos = filaProcessosOriginal.copiarFila();

		JOptionPane.showMessageDialog(null, "O Processo de Desmontagem será Iniciado!");
		while (!pecaDefeitoLocalizada) {
			pecaAtual = filaProcessos.dequeue();
			JOptionPane.showMessageDialog(null, pecaAtual.getNome() + " Removido(a)!");

			if (pecaAtual.getNome().equalsIgnoreCase(pecaDefeito.getNome())) {
				JOptionPane.showMessageDialog(null, "Peça com Defeito Localizada!");
				pecaAtual.diminuirQtdEstoque();
				JOptionPane.showMessageDialog(null, pecaAtual.getNome() + " Substituído(a) e Instalado(a)!");
				pecaDefeitoLocalizada = true;
			} else {
				pilhaDesmontagem.push(pecaAtual);
			}

		}

		if (!filaProcessos.isEmpty()) {
			filaProcessos.zerarFila();
		}

		if (!pilhaDesmontagem.isEmpty()) {
			JOptionPane.showMessageDialog(null, "O Processo de Remontagem será Iniciado!");

			while (!pilhaDesmontagem.isEmpty()) {
				JOptionPane.showMessageDialog(null, pilhaDesmontagem.pop().getNome() + " Foi Instalado(a)!");
			}
		}

		JOptionPane.showMessageDialog(null, "O Reparo do Produto foi Finalizado!");

	}

}
