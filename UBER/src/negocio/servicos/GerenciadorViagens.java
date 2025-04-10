package negocio.servicos;

import negocio.exceptions.EntidadeNaoEncontradaException;
import negocio.localizacao.Local;
import negocio.pessoas.Cliente;
import negocio.pessoas.Motorista;
import dados.IRepositorioViagem;
import dados.RepositorioViagemArquivo;
import negocio.localizacao.ViagemEntrega;
import negocio.localizacao.Viagem;
import negocio.localizacao.ViagemCliente;
import negocio.servicos.GerenciadorPessoa;

import java.util.List;

public class GerenciadorViagens {
    private final IRepositorioViagem<Viagem> repoViagem;
    private final GerenciadorPessoa pessoaManager;

    public GerenciadorViagens() {
        this.repoViagem = new RepositorioViagemArquivo();
        this.pessoaManager = new GerenciadorPessoa();
    }
    

    public IRepositorioViagem<Viagem> getRepoViagem(){
        return repoViagem;
    }

    public void adicionarViagemCliente(Local origem, Local destino, Cliente cliente, Motorista motorista, double preco) {
        ViagemCliente viagem = new ViagemCliente(origem, destino, cliente, motorista, preco);
        repoViagem.adicionar(viagem);
    }

    public void adicionarViagemEntrega(Local origem, Local destino, Cliente cliente, Motorista motorista, double preco, double pesoEntrega) {
        ViagemEntrega viagem = new ViagemEntrega(origem, destino, cliente, motorista, preco, pesoEntrega);
        repoViagem.adicionar(viagem);
    }

    public void solicitarViagemCliente(Local origem, Local destino, Cliente cliente) throws EntidadeNaoEncontradaException {
        Motorista motorista = pessoaManager.buscarMotoristaDisponivel();
        if (motorista == null) {
            throw new EntidadeNaoEncontradaException("Nenhum motorista disponível no momento.");
        }

        double preco = calcularPreco(origem, destino);
        adicionarViagemCliente(origem, destino, cliente, motorista, preco);

        motorista.setDisponivel(false);
    }

    public void solicitarViagemEntrega(Local origem, Local destino, Cliente cliente, double peso) throws EntidadeNaoEncontradaException {
        Motorista motorista = pessoaManager.buscarMotoristaDisponivel();
        if (motorista == null) {
            throw new EntidadeNaoEncontradaException("Nenhum motorista disponível no momento.");
        }

        double preco = calcularPreco(origem, destino);
        adicionarViagemEntrega(origem, destino, cliente, motorista, preco, peso);
        motorista.setDisponivel(false);
    }

    public List<Viagem> listarViagensCliente(int idCliente) {
        return repoViagem.listarViagensCliente(idCliente);
    }

    public List<Viagem> listarViagensMotorista(int idMotorista) {
        return repoViagem.listarViagensMotorista(idMotorista);
    }


}
