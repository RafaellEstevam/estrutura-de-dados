package tad;

public class No {

	Peca dado;
	No prox;

	public No(Peca dado) {
		this.dado = dado;
		prox = null;
	}

	@Override
	public String toString() {
		return String.valueOf(dado);
	}
}