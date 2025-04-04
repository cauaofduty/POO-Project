package negocio.locais;

import negocio.pessoas.Cliente;
import negocio.pessoas.Motorista;

public class ViagemCliente extends Viagem{

    public ViagemCliente(Local origem, Local destino, Cliente cliente, Motorista motorista, double preco){
        super(origem, destino, cliente, motorista, preco);
    }
    
    
}
