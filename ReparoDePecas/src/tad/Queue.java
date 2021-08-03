package tad;

public class Queue {

	private No inicio;
	private No fim;
	private int total;

	public Queue() {

		inicio = fim = null;
		total = 0;
	}

	public void enqueue(Peca dado) {
		No novoNo = new No(dado);

		if (isEmpty())
			inicio = fim = novoNo;
		else {
			fim.prox = novoNo;
			fim = novoNo;
		}
		total++;
	}

	public Peca dequeue() {
		if (isEmpty())
			throw new RuntimeException("==>Fila Vazia!!");

		No temp = inicio;

		if (size() == 1)
			inicio = fim = null;
		else
			inicio = inicio.prox;
		total--;

		return temp.dado;
	}

	public void zerarFila() { //
		if (isEmpty())
			throw new RuntimeException("==>Fila Vazia!!");

		while (!this.isEmpty()) {
			this.dequeue();
		}

	}

	public String front() {
		if (isEmpty())
			throw new RuntimeException("==>Fila Vazia!!");
		return inicio.dado.getNome();
	}

	public boolean isEmpty() {
		return total == 0;
	}

	public int size() {
		return total;
	}

	public Queue copiarFila() {
		Queue filaCopia = new Queue();

		No atual = inicio;

		while (atual != null) {
			filaCopia.enqueue(atual.dado);
			atual = atual.prox;
		}

		return filaCopia;

	}

	@Override
	public String toString() {
		StringBuilder saida = new StringBuilder();

		No atual = inicio;

		while (atual != null) {
			saida.append(atual.dado.getNome()).append(" -- ");

			atual = atual.prox;
		}
		return saida.toString();
	}
}
