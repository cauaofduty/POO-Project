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
    protected  final double preco;
    protected boolean isSimulacao;
  
    public Viagem(Local origem, Local destino, Cliente cliente, Motorista motorista, double preco , boolean simulacao){
        this.origem = origem;
        this.destino = destino;
        this.cliente = cliente;
        this.motorista = motorista;
        this.preco = preco;
        this.isSimulacao = simulacao;
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

    public double getPreco() {
        return preco;
    }

    public boolean isIsSimulacao() {
        return isSimulacao;
    }

    public void setIsSimulacao(boolean isSimulacao) {
        this.isSimulacao = isSimulacao;
    }

    @Override
    public String toString() {
        return " | Cliente: " + cliente.getNome() + " | Motorista: " + motorista.getNome() + 
               " | Origem: " + origem.getZona() + ", " + origem.getBairro() + ", " + origem. getCidade() +
               " | Destino: " + destino.getZona() + ", " + destino.getBairro() + ", " + destino. getCidade() +
               " | Preço: R$ " + String.format("%.2f", preco);
    }
    
}
