package negocio.locais;

import negocio.Cliente;
import negocio.Motorista;
import negocio.modelos.Local;

public class ViagemCliente extends Viagem{

    public ViagemCliente(Local origem, Local destino, Cliente cliente, Motorista motorista, double preco){
        super(origem, destino, cliente, motorista, preco);
    }
    
    
}
