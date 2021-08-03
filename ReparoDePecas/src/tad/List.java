package tad;

public class List {

	private No inicio;
	private No fim;
	private int total;

	public List() {
		inicio = fim = null;
		total = 0;
	}

	public void insert(Peca dado) {
		insert(dado, size());
	}

	public void insert(Peca dado, int posicao) {
		No novoNo = new No(dado);

		if (posicao < 0 || posicao > total)
			throw new RuntimeException("==>Posicao Invalida!!");

		if (posicao == 0) {
			if (isEmpty())
				inicio = fim = novoNo;
			else {
				novoNo.prox = inicio;
				inicio = novoNo;
			}
		} else {
			if (posicao == size()) {
				fim.prox = novoNo;
				fim = novoNo;
			} else {
				No aux = inicio;
				int cont = 0;
				while (aux != null && (cont != posicao - 1)) {
					aux = aux.prox;
					cont++;
				}
				novoNo.prox = aux.prox;
				aux.prox = novoNo;
			}
		}
		total++;
	}

	public Peca remove(int posicao) {
		No temp;

		if (posicao < 0 || posicao >= total || isEmpty())
			throw new RuntimeException("==>Posicao Invalida ou Lista Vazia");

		if (posicao == 0) {
			temp = inicio;
			if (size() == 1)
				inicio = fim = null;
			else
				inicio = inicio.prox;
		} else {
			No aux = inicio;
			int cont = 0;
			while (aux != null && cont != posicao - 1) {
				aux = aux.prox;
				cont++;
			}
			temp = aux.prox;
			aux.prox = temp.prox;
		}
		total--;
		return temp.dado;
	}

	public void set(Peca dado, int posicao) {
		if (posicao < 0 || posicao >= total || isEmpty())
			throw new RuntimeException("==>Posicao Invalida ou Lista Vazia");

		No aux = inicio;
		int cont = 0;

		while (aux != null && cont != posicao) {
			aux = aux.prox;
			cont++;
		}

		aux.dado = dado;
	}

	public Peca get(int posicao) {
		if (posicao < 0 || posicao >= total || isEmpty())
			throw new IllegalArgumentException("==>Posicao Invalida ou Lista Vazia");

		No aux = inicio;
		int cont = 0;

		while (aux != null && posicao != cont) {
			aux = aux.prox;
			cont++;
		}
		return aux.dado;
	}

	public boolean exist(String nomePeca) {
		if (isEmpty())
			return false;

		No aux = inicio;
		while (aux != null) {

			if (aux.dado.getNome().equalsIgnoreCase(nomePeca))
				return true;
			aux = aux.prox;
		}

		return false;
	}

	public int size() {
		return total;
	}

	public boolean isEmpty() {
		return total == 0;
	}

	public String imprimirInfoTotalPecas() {
		StringBuilder saida = new StringBuilder();

		No atual = inicio;
		int cont = 1;
		while (atual != null) {
			saida.append(cont + ". " + atual.dado.getNome() + " - " + atual.dado.getQtdEstoque() + " unidade(s)")
					.append("\n");
			atual = atual.prox;
			cont++;
		}
		return saida.toString();
	}

	@Override
	public String toString() {
		StringBuilder saida = new StringBuilder();

		No atual = inicio;
		int cont = 1;
		while (atual != null) {
			saida.append(cont + ". " + atual.dado.getNome()).append("\n");
			atual = atual.prox;
			cont++;
		}
		return saida.toString();
	}

	public boolean qtdEstoqueSuficiente(int pos) {

		Peca peca = this.get(pos);

		if (peca.getQtdEstoque() == 0) {
			return false;
		}

		return true;
	}

	public void zerarLista() {
		if (isEmpty())
			throw new RuntimeException("==>Lista Vazia!!");

		while (!this.isEmpty()) {
			this.remove(0);
		}

	}

	public Peca removerDado(String nomePeca) {
		Peca pecaRemovida = null;

		if (this.isEmpty()) {
			throw new RuntimeException("==> Lista Vazia");
		}

		if (!this.exist(nomePeca)) {
			return null;
		}

		if (this.size() == 1 && inicio.dado.getNome().equalsIgnoreCase(nomePeca)) {
			pecaRemovida = inicio.dado;
			inicio = fim = null;
			total = 0;
			return pecaRemovida;
		}

		No atual = inicio;
		No ant = inicio;
		while (atual != null) {

			if (atual.dado.getNome().equalsIgnoreCase(nomePeca)) {
				pecaRemovida = atual.dado;
				ant.prox = atual.prox;
				total--;
				break;
			}

			ant = atual;
			atual = atual.prox;

		}

		return pecaRemovida;
	}

}
