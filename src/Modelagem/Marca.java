package Modelagem;

import java.util.ArrayList;

public class Marca {

	private String nome;
	private ArrayList<Modelo> modelos = new ArrayList<Modelo>();

	public Marca(String nome){
		this.nome = nome;
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

}
