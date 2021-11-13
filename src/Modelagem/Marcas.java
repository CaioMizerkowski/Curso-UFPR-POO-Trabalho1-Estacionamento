package Modelagem;

import java.util.ArrayList;
import java.util.Scanner;

public class Marcas {
   	private static ArrayList<Marca> marcas = new ArrayList<Marca>();
	private Scanner scanner = new Scanner(System.in);

	public void addMarca(Marca marca){
		marcas.add(marca);
	}
	
	public ArrayList<Marca> getMarcas() {
		return marcas;
	}

    public Marca newMarca(String nome) {
		Marca marca;
		marca = findMarca(nome);
		if(marca == null){
			System.out.println("Marca inexistente, adicionando marca.");
			marca = new Marca(nome);
			marcas.add(marca);
		}
		else{
			System.out.println("Marca já existe, retornando marca existente.");
		}
		return marca;
	}

    public Marca newMarca(int id, String nome) {
		Marca marca = new Marca(id,nome);
		marcas.add(marca);
		return marca;
	}

	public Modelo newModelo(String nome, Marca marca) {
		Modelo modelo;
		modelo = findModelo(nome, marca);
		if(modelo == null){
			System.out.println("Adicionando novo modelo.");
			modelo = new Modelo(nome, marca);
			marca.addModelo(modelo);
		}
		else{
			System.out.println("Modelo já existe, retornando modelo existente.");
		}
		return modelo;
	}

	private static Marca findMarca(String nome){
		for (Marca marca: marcas){
			if(marca.isNome(nome)){
				return marca;
			}
		}
		return null;
	}

	private static Modelo findModelo(String nome, Marca marca) {
		ArrayList<Modelo> modelos;
		modelos = marca.getModelos();
		if(modelos == null){
			return null;
		}
		for (Modelo modelo: modelos){
			if(modelo.isNome(nome)){
				return modelo;
			}
		}
		return null;
	}

	public void showMarcas(){
		int i=1;
		System.out.println("0 - Nova Marca");
		for (Marca marca : marcas) {
			System.out.println(i+" - "+marca);
			i++;
		}
	}

	public void showModelos(Marca marca){
		int i=1;
		System.out.println("0 - Novo Modelo");
		for (Modelo modelo : marca.getModelos()) {
			System.out.println(i+" - "+modelo);
			i++;
		}
	}

	public void showModelos(){
		for (Marca marca : marcas) {
			System.out.println(marca+":");
			for (Modelo modelo : marca.getModelos()) {
				System.out.println("-"+modelo);
			}
		}
	}

	public Marca selectMarca(){
		int idx;
		System.out.println("Escolha a marca que gostaria:");
		showMarcas();
		idx = scanner.nextInt();
		scanner.nextLine();

		if(idx==0){
			System.out.println("Defina o nome para o nova marca:");
			String nome_marca = scanner.nextLine();
			Marca marca = newMarca(nome_marca);
			return marca;
		}
		return marcas.get(idx-1);
	}

	public Modelo selectModelo(Marca marca){

		int idx;
		System.out.println("Escolha o modelo que gostaria:");
		showModelos(marca);
		idx = scanner.nextInt();
		scanner.nextLine();
		if(idx==0){
			System.out.println("Defina o nome para o novo modelo:");
			String nome_modelo = scanner.nextLine();
			Modelo modelo = newModelo(nome_modelo, marca);
			return modelo;
		}
		else if(!marca.existIdx(idx-1)){
			System.out.println("Modelo inválido!");
			System.out.println("Defina o nome para o novo modelo:");
			String nome_modelo = scanner.nextLine();
			Modelo modelo = newModelo(nome_modelo, marca);
			return modelo;
		}
		return marca.getModelo(idx-1);
	}

    public Modelo newModelo(int idModelo, String nomeModelo, Marca marca) {
		Modelo modelo = new Modelo(idModelo, nomeModelo, marca);
		marca.addModelo(modelo);
		return modelo;
    }

}
