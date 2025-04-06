package negocio.localizacao;

import java.io.Serializable;
import negocio.pessoas.Cliente;
import negocio.pessoas.Motorista;

public abstract class Viagem implements Serializable {
    private static final long serialVersionUID = 300000L;
    protected final Local origem; 
    protected final Local destino;
    protected final Cliente cliente;
    protected final Motorista motorista;
    protected  final double preco;//o calculo do preco vai ser colocado aqui?
    // Talvez fosse útil criar um campo com status da viagem, com um enum (Pendente, Em andamento, Concluída, Cancelada) (Hugo)
    //como elas são concluidas em tempo de execção, creio que não seja necessário 
  
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
