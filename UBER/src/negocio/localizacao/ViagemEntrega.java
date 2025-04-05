package negocio.localizacao;
import negocio.Cliente;
import negocio.Motorista;
import negocio.modelos.Viagem;

public class ViagemEntrega extends Viagem{
    private double pesoEntrega;//implementar tempo nas classes, perguntar ao professor


    public ViagemEntrega(double pesoEntrega, Local origem, Local destino, Cliente cliente, Motorista motorista) {
        super(origem, destino, cliente, motorista);
        this.pesoEntrega = pesoEntrega;
    }
    
    public double getPesoEntrega() {
        return pesoEntrega;
    }


    public void setPesoEntrega(double pesoEntrega) {
        this.pesoEntrega = pesoEntrega;
    }
}