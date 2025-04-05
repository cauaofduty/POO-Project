package negocio.localizacao;

import negocio.Cliente;
import negocio.Motorista;
import negocio.modelos.Local;

public class ViagemEntrega extends Viagem {
    private double pesoEntrega;

    public ViagemEntrega(Local origem, Local destino, Cliente cliente, Motorista motorista, double preco, double pesoEntrega) {
        super(origem, destino, cliente, motorista, preco);
        this.pesoEntrega = pesoEntrega;
    }
    
    public double getPesoEntrega() {
        return pesoEntrega;
    }
    
    public void setPesoEntrega(double pesoEntrega) {
        if (pesoEntrega < 0) throw new IllegalArgumentException("Peso da entrega não pode ser negativo.");
        this.pesoEntrega = pesoEntrega;
    }

    // Adicionar toString() para a classe ViagemEntrega, se necessário
}