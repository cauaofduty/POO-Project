package negocio.servicos;
import dados.IRepositorioViagem;
import dados.RepositorioViagemArquivo;
import java.util.ArrayList;
import negocio.exceptions.EntidadeNaoEncontradaException;
import negocio.localizacao.CalculadorPreco;
import negocio.localizacao.Local;
import negocio.localizacao.Viagem;
import negocio.localizacao.ViagemCliente;
import negocio.veiculos.Economico;
import negocio.veiculos.Motocicleta;
import negocio.localizacao.ViagemEntrega;
import negocio.localizacao.Zona;
import negocio.pessoas.Cliente;
import negocio.pessoas.Motorista;

import negocio.veiculos.Veiculo;

//como cumprem a mesma função e uma é intrinsecamente ligada a outra
//locais serão gerenciados aqui (além que não precisam ser persistidos "exclusivamente", com paga,mentos sofrendo do mesmo caso)
public class GerenciadorViagens {
    private final IRepositorioViagem<Viagem> repoViagem;

    public GerenciadorViagens() {
        this.repoViagem = new RepositorioViagemArquivo();
    }
    

    public IRepositorioViagem<Viagem> getRepoViagem(){
        return repoViagem;
    }

    public Local criarLocal(String cidade, String bairro, String zona) throws EntidadeNaoEncontradaException {
        if (cidade == null || cidade.trim().isEmpty()) throw new EntidadeNaoEncontradaException("Cidade não pode ser nula ou vazia.");
            try {
                Zona zonaSaida = Zona.valueOf(zona.trim().toUpperCase());
                return new Local(cidade, bairro, zonaSaida);
            } catch (IllegalArgumentException e) {
                throw new EntidadeNaoEncontradaException("Zona inválida: " + zona);
            }
    }

    public void validarZona(String entrada) throws EntidadeNaoEncontradaException {
        try {
            //checa se zona é válida
            Zona.valueOf(entrada.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new EntidadeNaoEncontradaException("Zona inválida: " + entrada);
        }
    }
    

    public void adicionarViagemCliente(Local origem, Local destino, Cliente cliente, Motorista motorista, double preco, boolean simulacao) {
        ViagemCliente viagem = new ViagemCliente(origem, destino, cliente, motorista, preco, simulacao);
        repoViagem.adicionar(viagem);
    }

    public void adicionarViagemEntrega(Local origem, Local destino, Cliente cliente, Motorista motorista, double preco, double pesoEntrega, boolean simulacao) {
        ViagemEntrega viagem = new ViagemEntrega(origem, destino, cliente, motorista, preco, pesoEntrega, simulacao);
        repoViagem.adicionar(viagem);
    }

    /* public void solicitarViagemCliente(Local origem, Local destino, Cliente cliente) throws EntidadeNaoEncontradaException {
        Motorista motorista = pessoaManager.buscarMotoristaDisponivel();
        if (motorista == null) {
            throw new EntidadeNaoEncontradaException("Nenhum motorista disponível no momento.");
        }

        Veiculo veiculo = motorista.getVeiculo();
        double preco = calcularPrecoViagem(origem, destino, veiculo);
        adicionarViagemCliente(origem, destino, cliente, motorista, preco);
        motorista.setDisponivel(false);
    }

    public void solicitarViagemEntrega(Local origem, Local destino, Cliente cliente, double pesoKg) throws EntidadeNaoEncontradaException {
    Motorista motoristaPermitido = null;

    // Procura por um motorista disponível com veículo apropriado para entrega, veículos que não sejam do tipo Luxo ou SUV
    for (Motorista motorista : pessoaManager.listarMotoristas()) {
        if (motorista.isDisponivel()) {
            Veiculo veiculo = motorista.getVeiculo();
            if (veiculo instanceof Motocicleta || veiculo instanceof Economico) {
                motoristaPermitido = motorista;
                break;
            }
        }
    }

    if (motoristaPermitido == null) {
        throw new EntidadeNaoEncontradaException("Nenhum motorista com veículo adequado disponível para entrega.");
    }

    Veiculo veiculo = motoristaPermitido.getVeiculo();
    double preco = calcularPrecoEntrega(origem, destino, veiculo, pesoKg);
    adicionarViagemEntrega(origem, destino, cliente, motoristaPermitido, preco, pesoKg);

    motoristaPermitido.setDisponivel(false);
    }   */

    //a ideia é mostrar antes da viagem e realizar pagamento antes
    public double calcularPrecoViagem(Local origem, Local destino, Veiculo veiculo) {
        return CalculadorPreco.calcularPrecoViagem(origem, destino, veiculo);
    }

    public double calcularPrecoEntrega(Local origem, Local destino, Veiculo veiculo, double pesoKg) {
        return CalculadorPreco.calcularPrecoEntrega(origem, destino, veiculo, pesoKg);
    }

    public ArrayList<Viagem> listarViagensCliente(String IDPessoa) {
        return repoViagem.listarViagensCliente(IDPessoa);
    }

    public ArrayList<Viagem> listarViagensMotorista(String IDPessoa) {
        return repoViagem.listarViagensMotorista(IDPessoa);
    }

}
