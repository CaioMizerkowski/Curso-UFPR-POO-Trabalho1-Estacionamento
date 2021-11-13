package Modelagem;

import java.util.ArrayList;

import WarpSQL.InsertRecords;

public class Marca {

	private String nome;
	private int idMarca;
	private static int counter = 0;
	private ArrayList<Modelo> modelos = new ArrayList<Modelo>();

	public Marca(String nome){
		this.nome = nome;
		this.idMarca = ++counter;
	}

	public boolean isNome(String nome) {
		return nome.equals(this.nome);
	}

	public ArrayList<Modelo> getModelos() {
		return modelos;
	}

	public void addModelo(Modelo modelo){
		this.modelos.add(modelo);
	}

    public String getNome() {
        return this.nome;
    }

    public Modelo getModelo(int idx) {
        return modelos.get(idx);
    }

	public boolean existIdx(int idx){
		return idx < modelos.size() && idx >= 0;
	}

	public String toString() {
		return this.getNome();
	}

	public void saveMarca(){
        String valor = ""+idMarca+","+nome;
        InsertRecords insert = new InsertRecords(); 
		insert.InsertInto("Marca","id,marca",valor);
	}

	public int getIdMarca() {
		return idMarca;
	}
}
