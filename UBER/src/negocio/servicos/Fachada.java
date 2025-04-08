package negocio.servicos;

public class Fachada {
    private final GerenciadorPessoa PManager;
    private final GerenciadorVeiculos VeiculoManager;
    private final GerenciadorViagens ViagemManager;
    
    public Fachada(GerenciadorPessoa pManager, GerenciadorVeiculos veiculoManager, GerenciadorViagens viagemManager) {
        PManager = pManager;
        VeiculoManager = veiculoManager;
        ViagemManager = viagemManager;
    }
    void adicionarPessoa(String nome, String cpf, String telefone, String email, String endereco) {
        PManager.adicionarPessoa(nome, cpf, telefone, email, endereco);
    }
    void adicionarVeiculo(String placa, String modelo, String cor, String ano, String tipo) {
        VeiculoManager.adicionarVeiculo(placa, modelo, cor, ano, tipo);
    }
    void adicionarViagem(String origem, String destino, String data, String hora, String motorista) {
        ViagemManager.adicionarViagem(origem, destino, data, hora, motorista);
    }
    void listarPessoas() {
        PManager.listarPessoas();
    }
    void listarVeiculos() {
        VeiculoManager.listarVeiculos();
    }
    void listarViagens() {
        ViagemManager.listarViagens();
    }
}
