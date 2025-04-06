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
        this.pesoEntrega = pesoEntrega;
    }
}