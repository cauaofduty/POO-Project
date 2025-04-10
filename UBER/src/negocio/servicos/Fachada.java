package negocio.servicos;

import java.util.ArrayList;
import java.util.List;

import negocio.exceptions.EntidadeJaExisteException;
import negocio.exceptions.EntidadeNaoEncontradaException;
import negocio.financeiro.Cartao;
import negocio.financeiro.FormaDePagamento;
import negocio.financeiro.Pix;
import negocio.localizacao.Local;
import negocio.localizacao.Viagem;
import negocio.pessoas.Pessoa;
import negocio.pessoas.Cliente;
import negocio.veiculos.Veiculo;
import negocio.pessoas.Motorista;

public class Fachada {
    private final GerenciadorPessoa pessoaManager;
    private final GerenciadorVeiculos veiculoManager;
    private final GerenciadorViagens viagemManager;
    
    public Fachada() {
        this.pessoaManager = new GerenciadorPessoa();
        this.veiculoManager = new GerenciadorVeiculos();
        this.viagemManager = new GerenciadorViagens();
    }

    // Funções de GerenciadorPessoa

    void cadastrarCliente(ArrayList<FormaDePagamento> formaDePagamentos, String IDPessoa, int idade, Local local, String nome, String senhaAcesso) throws EntidadeJaExisteException {
        pessoaManager.cadastrarCliente(formaDePagamentos, IDPessoa, idade, local, nome, senhaAcesso);
    }

    void cadastrarMotorista(Veiculo veiculo, String IDPessoa, int idade, Local local, String nome, String senhaAcesso, ArrayList<Veiculo> historicoVeiculos) throws EntidadeJaExisteException {
        pessoaManager.cadastrarMotorista(veiculo, IDPessoa, idade, local, nome, senhaAcesso, historicoVeiculos);
    }

    public Pessoa buscarPessoa(String IDPessoa) throws EntidadeNaoEncontradaException {
        return pessoaManager.buscarPessoa(IDPessoa);
    }

    // Parando para pensar, as funções de listarClientes e listarMotoristas não existem em um aplicativo como o UBER né? Seriam funções de admin, talves fosse útil perguntar no inicio quem esta acessando o sistema
    public ArrayList<Cliente> listarClientes() {
        return pessoaManager.listarClientes();
    }

    public ArrayList<Motorista> listarMotoristas() {
        return pessoaManager.listarMotoristas();
    }

    public String criarSenha() {
        return pessoaManager.criarSenha();
    }

    public void mudarSenha(int codigoRecuperacao, String novaSenha, Pessoa pessoa) {
        pessoaManager.mudarSenha(codigoRecuperacao, novaSenha, pessoa); 
    }

    public void adicionarFormaPagamento(ArrayList<FormaDePagamento> formas) {
        pessoaManager.adicionarFormaPagamento(formas);
    }

    public Cartao cadastrarCartao(){
       return pessoaManager.cadastrarCartao();
    }

    public Pix cadastrarPix(){
         return pessoaManager.cadastrarPix();
    }

   // Funções de GerenciadorVeiculos

    void cadastrarVeiculo(String placa, String cor, int ano, String nome, int tipoVeiculo) throws EntidadeJaExisteException {
        veiculoManager.cadastrarVeiculo(placa, cor, ano, nome, tipoVeiculo);
    }

    public Veiculo buscarVeiculo(String placa) {
        return veiculoManager.buscarVeiculo(placa);
    }

    public List<Veiculo> listarVeiculos() {
        return veiculoManager.listarVeiculos();
    }

    // Funções de GerenciadorViagens

    public void solicitarViagemCliente(Local origem, Local destino, Cliente cliente) throws EntidadeNaoEncontradaException {
        viagemManager.solicitarViagemCliente(origem, destino, cliente);
    }

    public void adicionarViagemCliente(Local origem, Local destino, Cliente cliente, Motorista motorista, double preco) {
        viagemManager.adicionarViagemCliente(origem, destino, cliente,motorista, preco);
    }

    public void solicitarViagemEntrega(Local origem, Local destino, Cliente cliente, double peso) throws EntidadeNaoEncontradaException {
        viagemManager.solicitarViagemEntrega(origem, destino, cliente, peso);
    }

    public void adicionarViagemEntrega(Local origem, Local destino, Cliente cliente, Motorista motorista, double preco, double pesoEntrega) {
        viagemManager.adicionarViagemEntrega(origem, destino, cliente,motorista, preco, pesoEntrega);
    }

    public List<Viagem> listarViagensCliente(int idCliente) {
        return viagemManager.listarViagensCliente(idCliente);
    }

    public List<Viagem> listarViagensMotorista(int idMotorista) {
        return viagemManager.listarViagensMotorista(idMotorista);
    }
}
