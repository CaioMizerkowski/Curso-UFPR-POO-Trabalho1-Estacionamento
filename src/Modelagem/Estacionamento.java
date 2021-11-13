package Modelagem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

public class Estacionamento {
    private BidiMap<Integer, Carro> vagas = new DualHashBidiMap<Integer, Carro>();
    private ArrayList<Carro> historico = new ArrayList<Carro>();

    public BidiMap<Integer, Carro> getVagas() {
        return vagas;
    }

    public Collection<Carro> getCarros() {
        return vagas.values();
    }

    public Carro getCarro(Integer idx) {
        return vagas.get(idx);
    }

    public Carro getCarro(String placa) {     
		for (Carro carro: vagas.values()){
			if(carro.isPlaca(placa)){
				return carro;
			}
		}
		return null;
	}

    public void showVagasOcupadas(){
        Collection<Integer> vagas_ocupadas = vagas.keySet();
        System.out.println("Vaga:Placa");
        for (Integer idx : vagas_ocupadas) {
            System.out.println(idx+":"+vagas.get(idx).getPlaca());
        }
    }

    public void showVagasLivres(){
        Collection<Integer> vagas_ocupadas = vagas.keySet();
        System.out.println("Vaga:Livre");
        for (Integer idx = 1; idx <= 100; idx++) {
            if(!vagas_ocupadas.contains(idx)){
                System.out.println(idx+":Livre");  
            }
        }
    }

    public int getVaga(Carro carro) {
        return vagas.getKey(carro);
    }

    public void setVaga(Integer idx, Carro carro) {
        if(idx > 100 || idx < 1)
        {   
            System.out.println("Vaga invalida");
            return;
        }
        if(carro == null){
            this.vagas.remove(idx);
        }
        else{
            this.vagas.put(idx, carro);
        }
    }

    public void removeCarro(Carro carro){
        historico.add(carro);
        vagas.removeValue(carro);
    }

    public void removeCarro(Integer idx){
        historico.add(vagas.get(idx));
        vagas.remove(idx);
    }

    public void relatorioGerencial(){
        for (Carro carro : historico) {
            System.out.println(carro);
        }
    }

    public void relatorioGerencial(LocalDate data){
        historico.sort((o1, o2) -> o1.getEntrada().compareTo(o2.getEntrada()));
        for (Carro carro : historico) {
            if(carro.isDateEntrada(data)){
                System.out.println(carro);
            }
        }
    }

    public void showValorFaturado(){
        float valor_total = 0;
        for (Carro carro : historico) {
                valor_total += carro.getValor();
        }
        System.out.println(valor_total);
    }

    public void showValorFaturado(LocalDate data){
        float valor_total = 0;
        for (Carro carro : historico) {
            if(carro.isDateEntrada(data)){
                valor_total += carro.getValor();
            }
        }
        System.out.println(valor_total);
    }

    public Integer getPrimeiraVagaLivre(){
        Collection<Integer> vagas_ocupadas = vagas.keySet();
        System.out.println("Vaga:Livre");
        for (Integer idx = 1; idx <= 100; idx++) {
            if(!vagas_ocupadas.contains(idx)){
                return idx;
            }
        }
        return null;
    }

    public boolean isFull(){
        if(getPrimeiraVagaLivre()==null){
            return true;
        }
        return false;
    }
}
