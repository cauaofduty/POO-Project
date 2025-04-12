package negocio.localizacao;

import negocio.pessoas.Cliente;
import negocio.pessoas.Motorista;

public class ViagemCliente extends Viagem {
    private static final long serialVersionUID = 300001L;

    public ViagemCliente(Local origem, Local destino, Cliente cliente, Motorista motorista, double preco, boolean simulacao){
        super(origem, destino, cliente, motorista, preco, simulacao);
    }

    @Override
    public String toString() {
        return "Viagem {\nCliente: " + cliente.getNome() +
                "\nOrigem: " + origem.getZona() + ", " + origem.getBairro() + ", " + origem.getCidade() +
                "\nDestino: " + destino.getZona() + ", " + destino.getBairro() + ", " + destino.getCidade() +
                "\nMotorista: " + motorista.getNome() +
                "\nVeículo: " + motorista.getVeiculo() + ", " + "Tipo: " + motorista.getVeiculo().getClass() + ", " + "Placa: " + motorista.getVeiculo().getPlaca() +
                "\nSimulação: " + isSimulacao +
                "\nValor: " + preco +
                "\n}\n";
    }
    
}
