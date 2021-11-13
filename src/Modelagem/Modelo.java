package Modelagem;

public class Modelo {

	private String nome;
	private Marca marca;

	public Modelo(String nome, Marca marca){
		this.nome = nome;
		this.marca = marca;
	}

	public Marca getMarca() {
		return marca;
	}

	public String getNome() {
		return nome;
	}

	public boolean isNome(String nome) {
		return nome.equals(this.nome);
	}

}
