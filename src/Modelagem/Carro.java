package Modelagem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import WarpSQL.InsertRecords;

import java.time.Duration;
import java.time.LocalDate;
import java.lang.Math;

public class Carro {

	private Modelo modelo;
	private String placa;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private float valor;
	private static float preco_primeira_hora = 10;
	private static float preco_quarto_de_hora = 2;
	private int idCarro;
	private static int counter = 0;

	public Carro(String placa, Modelo modelo, LocalDateTime entrada) {
		this.placa = placa;
		this.modelo = modelo;
		this.entrada = entrada;
		this.idCarro = ++counter;
	}
	
	public Carro(int idCarro, String placa, String entrada, String saida, Modelo modelo) {
		this.placa = placa;
		this.modelo = modelo;
		this.entrada = LocalDateTime.parse(entrada);
		if(!saida.equals("null")){
			System.out.println(saida);
			this.saida = LocalDateTime.parse(saida);
			getValor();
		}
		this.idCarro = idCarro;
		counter = counter>idCarro?++counter:idCarro+1;
	}

	public void setSaida(LocalDateTime saida) {
		if(saida.isAfter(this.entrada)){
			this.saida = saida;
		}
		else{
			System.out.println("Saída invalida, ocorreu antes da entrada");
		}
	}

	public void setSaida() {
		this.saida = LocalDateTime.now();
		if(this.saida.isBefore(this.entrada)){
			this.saida = null;
			System.out.println("Saída invalida, ocorreu antes da entrada");
		}
	}

	public float getValor() {
		Duration duration = Duration.between(this.entrada,this.saida);
		long minutos = duration.toMinutes();
		if(minutos<=60)
		{
			return preco_primeira_hora;
		}
		int quarto_de_hora = (int) Math.ceil(((float) minutos - 60)/15);
		this.valor = preco_quarto_de_hora * quarto_de_hora;
		return this.valor;
	}

	public String getPlaca() {
		return placa;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public Marca getMarca() {
		return this.modelo.getMarca();
	}

	public LocalDateTime getEntrada() {
		return entrada;
	}

	public boolean isPlaca(String placa) {
		return placa.equals(this.placa);
	}

	public String toString(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'yyyy-MM-dd HH:mm'");
		String marca = this.getMarca().getNome();
		String modelo = this.getModelo().getNome();
		String entrada = this.entrada.format(formatter);
		String saida = this.saida.format(formatter);
		String placa = this.placa;
		String valor = Float.toString(this.valor);

		String retorno = "CARRO\n-Placa: "+placa+
		"\n-Marca:"+marca+
		"\n-Modelo"+modelo+
		"\n-Entrada:"+entrada+
		"\n-Saida:"+saida+
		"\n-Valor:"+valor+"\n";
		return retorno;
	}

	public boolean isDateEntrada(LocalDate data){
		if(this.entrada.toLocalDate() == data){
			return true;
		}
			return false;
	}

	public void saveCarro(){
		String valor;
		valor = "'"+idCarro+"','"+placa+"','"+entrada+"','"+saida+"','"+modelo.getIdModelo()+"'";
        InsertRecords insert = new InsertRecords();
		insert.InsertInto("Carro","id,placa,dtEntrada,dtSaida,idModelo",valor);
	}

	public LocalDateTime getSaida() {
		return saida;
	}
}
