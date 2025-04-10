package negocio.servicos;

import negocio.localizacao.Local;
import negocio.pessoas.Cliente;
import negocio.pessoas.Motorista;
import dados.IRepositorioViagem;
import dados.RepositorioViagemArquivo;
import negocio.localizacao.Viagem;
import negocio.localizacao.ViagemCliente;

import java.util.List;

public class GerenciadorViagens {
    private final IRepositorioViagem<Viagem> repoViagem;

    public GerenciadorViagens() {
        this.repoViagem = new RepositorioViagemArquivo();
    }

    public IRepositorioViagem<Viagem> getRepoViagem(){
        return repoViagem;
    }

    // Aqui teria que criar um para viagem de cliente e de viagem de entrega, ou seja, dois tipos de viagem, um para cada tipo (Hugo)
    
    public void adicionarViagemCliente(Local origem, Local destino, Cliente cliente, Motorista motorista, double preco) {
        ViagemCliente viagem = new ViagemCliente(origem, destino, cliente, motorista, preco);
        repoViagem.adicionar(viagem);
    }

    public List<Viagem> listarViagensCliente(int idCliente) {
        return repoViagem.listarViagensCliente(idCliente);
    }

    public List<Viagem> listarViagensMotorista(int idMotorista) {
        return repoViagem.listarViagensMotorista(idMotorista);
    }
}
