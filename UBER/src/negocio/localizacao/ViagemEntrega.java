package negocio.localizacao;

import negocio.pessoas.Cliente;
import negocio.pessoas.Motorista;

public class ViagemEntrega extends Viagem {
    private static final long serialVersionUID = 300002L;
    private double pesoEntrega;

    public ViagemEntrega(Local origem, Local destino, Cliente cliente, Motorista motorista, double preco, double pesoEntrega, boolean simulacao) {
        super(origem, destino, cliente, motorista, preco, simulacao);
        this.pesoEntrega = pesoEntrega;
    }
    

    public double getPesoEntrega() {
        return pesoEntrega;
    }
    
    public void setPesoEntrega(double pesoEntrega) {
        if (pesoEntrega < 0) throw new IllegalArgumentException("Peso da entrega não pode ser negativo.");
        this.pesoEntrega = pesoEntrega;
    }

    @Override
    public String toString() {
        return super.toString() + " | Peso da Entrega: " + String.format("%.2f", pesoEntrega) + " kg";
    }
}

