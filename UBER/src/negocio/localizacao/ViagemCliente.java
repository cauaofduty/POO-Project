package negocio.localizacao;

import negocio.pessoas.Cliente;
import negocio.pessoas.Motorista;

public class ViagemCliente extends Viagem {
    private static final long serialVersionUID = 300001L;

    public ViagemCliente(Local origem, Local destino, Cliente cliente, Motorista motorista, double preco, boolean simulacao){
        super(origem, destino, cliente, motorista, preco, simulacao);
    }


}
