package tad;

public class Stack {

	private No topo;

	public Stack() {
		topo = null;
	}

	public boolean isEmpty() {
		return topo == null;
	}

	public void push(Peca dado) {
		No novoNo = new No(dado);
		novoNo.prox = topo;
		topo = novoNo;
	}

	public Peca pop() {
		if (!isEmpty()) {
			No temp = topo;
			topo = topo.prox;
			return temp.dado;
		} else
			throw new RuntimeException("==> Pilha Vazia!");
	}

	public String peek() {
		if (!isEmpty()) {
			return topo.dado.getNome();
		} else
			throw new RuntimeException("==> Pilha Vazia!");
	}

	public int size() {
		No atual = topo;
		int qtd = 0;
		while (atual != null) {
			qtd++;
			atual = atual.prox;
		}
		return qtd;
	}

	@Override
	public String toString() {
		No atual = topo;
		StringBuilder saida = new StringBuilder();
		while (atual != null) {
			saida.append(atual.dado.getNome()).append("\n");
			atual = atual.prox;
		}
		return saida.toString();
	}
}
