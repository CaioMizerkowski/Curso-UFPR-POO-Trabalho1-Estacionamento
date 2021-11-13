package Modelagem;

import WarpSQL.InsertRecords;

public class Modelo {

	private String nome;
	private Marca marca;
	private int idModelo;
	private static int counter = 0;

	public Modelo(String nome, Marca marca){
		this.nome = nome;
		this.marca = marca;
		this.idModelo = ++counter;
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

	public String toString() {
		return this.getNome();
	}

	public void saveModelo(){
        String valor = ""+idModelo+","+nome+","+marca.getIdMarca();
        InsertRecords insert = new InsertRecords(); 
		insert.InsertInto("Modelo","id,modelo,idmarca",valor);
	}

	public int getIdModelo() {
		return idModelo;
	}
}
