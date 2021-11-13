package main;
// Estacionamento base

// package src;

import java.util.ArrayList;
import java.util.Scanner;

import Modelagem.Carro;
import Modelagem.Marca;
import Modelagem.Modelo;
import Modelagem.Estacionamento;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Principal {

	// atributos static são atributos de classe
	private static ArrayList<Marca> marcas = new ArrayList<Marca>();
	private static Scanner scanner = new Scanner(System.in);
	private static Estacionamento estacionamento = new Estacionamento();

	public static void main(String[] args) {
		// outras variaveis locais
		// menu
		// opcao
		// chamar metodos static que correspondam as opcoes de menu
		String opcao;

		while(true){
			System.out.println("Bem-Vindo ao Sistema Júpiter de Controle de Estacionamento");
			System.out.println("Escolha dentre as opções para prosseguir:");
			System.out.println("R - Relatórios");
			System.out.println("J - Iniciar Júpiter");
			System.out.println("X - Salvar e sair");
			opcao = scanner.nextLine();
			switch(opcao.toUpperCase()){
				case "R":
					System.out.println();
					relatorios();
				break;

				case "J":
					System.out.println();
					jupiter();
				break;

				case "X":
					scanner.close();
					return;

				case "DUNA":
					System.out.println("The spice must flow.");
				break;

				default:
					System.out.println("Opção invalida!");

			}
		}
	}

	private static void relatorios(){
		String opcao;
		String opcao2;
		while(true){
			System.out.println("Escolha dentre as opções para prosseguir:");
			System.out.println("G - Relatório [g]erencial");
			System.out.println("L - Mostras vagas [l]ivres");
			System.out.println("O - Mostrar vagas [o]cupadas");
			System.out.println("X - Salvar e sair");
			opcao = scanner.nextLine();
			switch(opcao.toUpperCase()){

				case "G":
					System.out.println("Deseja ver o relatório:");
					System.out.println("D - De uma [d]ata especifica");
					System.out.println("T - De [t]odos os dias");
					System.out.println("X - Sair");
					opcao2 = scanner.nextLine();
					switch(opcao2.toUpperCase()){
					case "D":
						LocalDate data = defData();
						estacionamento.relatorioGerencial(data);
						estacionamento.showValorFaturado(data);
					break;

					case "T":
						estacionamento.relatorioGerencial();
						estacionamento.showValorFaturado();
					break;

					case "X":
						return;

					default:
						System.out.println("Opção invalida!");
					}
				break;

				case "L":
					estacionamento.showVagasLivres();
				break;

				case "O":
					estacionamento.showVagasOcupadas();
				break;

				case "X":
					return;

				default:
					System.out.println("Opção invalida!");

			}
		}
	}

	private static void jupiter(){
		System.out.println("Sistema Júpiter de Controle de Estacionamento");
		String opcao;
		String nome_marca;
		String nome_modelo;

		while(true){
			System.out.println("Escolha dentre as opções para prosseguir:");
			System.out.println("E - Entrada de veiculo");
			System.out.println("S - Saída de veiculo");
			System.out.println("A - Cadastrar Nova Marca");
			System.out.println("O - Cadastrar Novo Modelo");
			System.out.println("X - Salvar e sair");
			opcao = scanner.nextLine();

			switch(opcao.toUpperCase()){
				case "E":
					entradaCarro();
				break;

				case "S":
					saidaCarro();
				break;

				case "A":
					System.out.println("Qual o nome da marca?");
					nome_marca = scanner.nextLine();
					newMarca(nome_marca);
				break;

				case "O":
					System.out.println("Qual o nome da marca do modelo?");
					nome_marca = scanner.nextLine();
					System.out.println("Qual o nome do modelo?");
					nome_modelo = scanner.nextLine();
					newModelo(nome_modelo, newMarca(nome_marca));
				break;

				case "X":
					return;

				case "FEAR":
					System.out.println("I must not fear.");
					System.out.println("Fear is the mind-killer.");
					System.out.println("Fear is the little-death that brings total obliteration.");
				break;

				default:
					System.out.println("Opção invalida!");

			}
		}
	}

	private static Marca newMarca(String nome) {
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

	private static Modelo newModelo(String nome, Marca marca) {
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

	private static LocalDateTime defHorario(){
		LocalDateTime datetime = null;
		String opcao;

		while(true){
			System.out.println("Deseja utilizar o horario e data atual? [Y/n]");
			opcao = scanner.nextLine();
			switch(opcao.toUpperCase()){
				case "Y":
				case "":
					datetime = LocalDateTime.now();
					return datetime;

				case "N":
					System.out.println("Qual a data? (yyyy-mm-dd)");
					String data_carro = scanner.nextLine();
					System.out.println("Qual o horario? (hh:mm:ss)");
					String hora_carro = scanner.nextLine();
					datetime = LocalDateTime.parse(data_carro+"T"+hora_carro);
					return datetime;

				default:
					System.out.println("Opção invalida");
			}
		}
	}

	private static LocalDate defData(){
		LocalDate data = null;
		String opcao;

		while(true){
			System.out.println("Deseja utilizar a data atual? [Y/n]");
			opcao = scanner.nextLine();
			switch(opcao.toUpperCase()){
				case "Y":
				case "":
					data = LocalDate.now();
					return data;

				case "N":
					System.out.println("Qual a data? (yyyy-mm-dd)");
					String data_carro = scanner.nextLine();
					data = LocalDate.parse(data_carro);
					return data;

				default:
					System.out.println("Opção invalida");
			}
		}
	}

	private static void entradaCarro() {
		int idx;
		if(estacionamento.isFull()){
			System.out.println("Sem vagas disponiveis, estacionamento lotado");
			return;
		}
		Marca marca = selectMarca();

		Modelo modelo = selectModelo(marca);

		System.out.println("Qual a placa do carro?");
		String placa = scanner.nextLine();

		LocalDateTime dt_entrada = defHorario();

		Carro carro = new Carro(placa, modelo, dt_entrada);

		System.out.println("Escolha a vaga disponível para estacionar:");
		estacionamento.showVagasLivres();
		idx = scanner.nextInt();
		scanner.nextLine();
		estacionamento.setVaga(idx, carro);
	}

	private static float saidaCarro() {
		float preco = 0;
		int idx = -1;
		String placa;
		String opcao;
		Carro carro = null;
		LocalDateTime dt_saida = defHorario();

		while(carro == null || idx == -1){
			System.out.println("Deseja encontrar o carro através da:");
			System.out.println("P - Placa");
			System.out.println("V - Vaga");
			System.out.println("X - Salvar e sair");
			opcao = scanner.nextLine();

			switch(opcao.toUpperCase()){
				case "P":
					System.out.println("Digite a placa do carro:");
					estacionamento.showVagasOcupadas();
					placa = scanner.nextLine();
					carro = estacionamento.getCarro(placa);
					idx = estacionamento.getVaga(carro);
				break;

				case "V":
					System.out.println("Digite a vaga na qual o carro está:");
					estacionamento.showVagasOcupadas();
					idx = scanner.nextInt();
					scanner.nextLine();
					carro = estacionamento.getCarro(idx);
				break;

				case "X":
					return 0;

				default:
					System.out.println("Opção invalida!");
			}
		}

		estacionamento.removeCarro(idx);
		// logica para calcular preco do estacionamento e coloca-lo no historico
		carro.setSaida(dt_saida);
		preco = carro.getValor();
		System.out.println(preco);
		return preco;
	}

	private static void showMarcas(){
		int i=1;
		System.out.println("0 - Nova Marca");
		for (Marca marca : marcas) {
			System.out.println(i+" - "+marca.getNome());
			i++;
		}
	}

	private static void showModelos(Marca marca){
		int i=1;
		System.out.println("0 - Novo Modelo");
		for (Modelo modelo : marca.getModelos()) {
			System.out.println(i+" - "+modelo.getNome());
			i++;
		}
	}

	private static Marca selectMarca(){

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

	private static Modelo selectModelo(Marca marca){

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

}
