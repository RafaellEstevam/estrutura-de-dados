package tad;

/**
 * @author Rafaell Estevam
 *
 */
public class Peca {

	private String nome;
	private int qtdEstoque;

	public Peca(String nome, int qtdEstoque) {
		this.nome = nome;
		this.qtdEstoque = qtdEstoque;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	@Override
	public String toString() {
		return nome;
	}

	public void diminuirQtdEstoque() {
		this.qtdEstoque--;
	}

}
