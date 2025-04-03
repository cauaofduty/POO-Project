package negocio.modelos;
import negocio.Cliente;
import negocio.Motorista;

public class Viagem {
    protected Local origem;//aqui literalmente so muda o nome das variaveis para distinção 
    protected Local destino;
    protected Cliente cliente;
    protected Motorista motorista;
    protected double preco;
    protected int minutosViagem;//perguntar se essa implementação faz sentdo para fins de simulação

    public Viagem(Local origem, Local destino, Cliente cliente, Motorista motorista) {
        this.origem = origem;
        this.destino = destino;
        this.cliente = cliente;
        this.motorista = motorista;
    }
    public Local getOrigem() {
        return origem;
    }
    public void setOrigem(Local origem) {
        this.origem = origem;
    }
    public Local getDestino() {
        return destino;
    }
    public void setDestino(Local destino) {
        this.destino = destino;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Motorista getMotorista() {
        return motorista;
    }
    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }
    
}
