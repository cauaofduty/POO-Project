package negocio.localizacao;

import negocio.pessoas.Cliente;
import negocio.pessoas.Motorista;

public class ViagemEntrega extends Viagem {
    private static final long serialVersionUID = 300002L;
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

     @Override
    public String toString() {
        return "Viagem {\nCliente: " + cliente.getNome() +
                "\nOrigem: " + origem.getZona() + ", " + origem.getBairro() + ", " + origem.getCidade() +
                "\nDestino: " + destino.getZona() + ", " + destino.getBairro() + ", " + destino.getCidade() +
                "\nMotorista: " + motorista.getNome() +
                "\nVeículo: " + motorista.getVeiculo() + ", " + "Tipo: " + motorista.getVeiculo().getClass() + ", " + "Placa: " + motorista.getVeiculo().getPlaca() +
                "\nPeso da entrega: " + pesoEntrega + " kg" +
                "\nValor: " + preco +
                "\n}\n";
    }
}
