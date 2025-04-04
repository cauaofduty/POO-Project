package negocio.localizacao;
import negocio.pessoas.Cliente;
import negocio.pessoas.Motorista;

public abstract class Viagem {
    protected final Local origem; 
    protected final Local destino;
    protected final Cliente cliente;
    protected final Motorista motorista;
    protected  final double preco;//o calculo do preco vai ser colocado aqui?

    public Viagem(Local origem, Local destino, Cliente cliente, Motorista motorista, double preco) {
        this.origem = origem;
        this.destino = destino;
        this.cliente = cliente;
        this.motorista = motorista;
        this.preco = preco;
    }
    public Local getOrigem() {
        return origem;
    }
    public Local getDestino() {
        return destino;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public Motorista getMotorista() {
        return motorista;
    }
    
}
