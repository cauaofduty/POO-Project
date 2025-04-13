package negocio.servicos;

import java.util.ArrayList;
import java.util.List;
import negocio.exceptions.CodigoIncorretoException;
import negocio.exceptions.EntidadeJaExisteException;
import negocio.exceptions.EntidadeNaoEncontradaException;
import negocio.exceptions.SenhaIncorretaException;
import negocio.financeiro.Cartao;
import negocio.financeiro.FormaDePagamento;
import negocio.financeiro.Pix;
import negocio.localizacao.Local;
import negocio.localizacao.Viagem;
import negocio.localizacao.Zona;
import negocio.pessoas.Cliente;
import negocio.pessoas.Motorista;
import negocio.pessoas.Pessoa;
import negocio.veiculos.Veiculo;

public class Fachada {
    private final GerenciadorPessoa pessoaManager;
    private final GerenciadorVeiculos veiculoManager;
    private final GerenciadorViagens viagemManager;
    private static Fachada instancia = null;
    
    private Fachada() {
        this.pessoaManager = new GerenciadorPessoa();
        this.veiculoManager = new GerenciadorVeiculos();
        this.viagemManager = new GerenciadorViagens();
    }
    public static Fachada getInstancia() {
        //cria fachada caso não criada, do contrario retorna a ja criada para evitar instanciações desnecessarias (singleton)
        if (instancia == null) instancia = new Fachada();
        return instancia;
    }

    // Funções de GerenciadorPessoa (TALVEZ SOLTAR EXCEPTION DE SEGUNDA SENHA INCORRETA???????)

    public Cliente cadastrarCliente(ArrayList<FormaDePagamento> formaDePagamentos, String IDPessoa, int idade, Local local, String nome, String senhaAcesso) throws EntidadeJaExisteException {
        return pessoaManager.cadastrarCliente(formaDePagamentos, IDPessoa, idade, local, nome, senhaAcesso);
    }

    public Motorista cadastrarMotorista(Veiculo veiculo, String IDPessoa, int idade, Local local, String nome, String senhaAcesso) throws EntidadeJaExisteException {
        return pessoaManager.cadastrarMotorista(veiculo, IDPessoa, idade, local, nome, senhaAcesso);
    }

    public Pessoa buscarPessoa(String IDPessoa) throws EntidadeNaoEncontradaException {
        return pessoaManager.buscarPessoa(IDPessoa);
    }

    // Parando para pensar, as funções de listarClientes e listarMotoristas não existem em um aplicativo como o UBER né? Seriam funções de admin, talves fosse útil perguntar no inicio quem esta acessando o sistema //CAua-> mesmo assim ele quer, e a implementação vao ficar pro final, com remoção de usuarios e tudo
    public ArrayList<Cliente> listarClientes() {
        return pessoaManager.listarClientes();
    }

    public ArrayList<Motorista> listarMotoristas() {
        return pessoaManager.listarMotoristas();
    }

    public String formatarCartao(String cartao) {
        return pessoaManager.formatarCartao(cartao);
    }
    
   // Funções de GerenciadorVeiculos

    public Veiculo cadastrarVeiculo(String placa, String cor, int ano, String nome, int tipoVeiculo) throws EntidadeJaExisteException {
        return veiculoManager.cadastrarVeiculo(placa, cor, ano, nome, tipoVeiculo);
    }

    public Veiculo buscarVeiculo(String placa) {
        return veiculoManager.buscarVeiculo(placa);
    }

    public List<Veiculo> listarVeiculos() {
        return veiculoManager.listarVeiculos();
    }

    // Funções de GerenciadorViagens

    //recebe string e transforma em zona se for válida
    public Local criarLocal(String cidade, String bairro, String zona) throws EntidadeNaoEncontradaException {
        return viagemManager.criarLocal(cidade, bairro, zona);
    }

    public void validarZona(String entrada) throws EntidadeNaoEncontradaException {
        Zona.valueOf(entrada.trim().toUpperCase());
    }

    public void adicionarViagemCliente(Local origem, Local destino, Cliente cliente, Motorista motorista, double preco, boolean simulacao) {
        viagemManager.adicionarViagemCliente(origem, destino, cliente,motorista, preco, simulacao );
    }

    public void adicionarViagemEntrega(Local origem, Local destino, Cliente cliente, Motorista motorista, double preco, double pesoEntrega, boolean simulacao) {
        viagemManager.adicionarViagemEntrega(origem, destino, cliente,motorista, preco, pesoEntrega, simulacao);
    }

    public ArrayList<Viagem> listarViagensCliente(String idCliente) {
        return viagemManager.listarViagensCliente(idCliente);
    }

    public ArrayList<Viagem> listarViagensMotorista(String idMotorista) {
        return viagemManager.listarViagensMotorista(idMotorista);
    }
    
    public ArrayList<Viagem> listarViagensGeral() {
        return viagemManager.listarViagensGeral();
    }

    //funções subordinadas da UI
    public void criarSenha(String senhaAcesso, Pessoa pessoa) throws EntidadeNaoEncontradaException, IllegalArgumentException {
        pessoaManager.criarSenha(senhaAcesso, pessoa);
    }

    public void receberSenhaLogin(String senha, String IDPessoa) throws SenhaIncorretaException{
        pessoaManager.receberSenhaLogin(senha, IDPessoa);
    }

    public void mudarSenha(String novaSenha, Pessoa pessoa){
        pessoaManager.mudarSenha(pessoa, novaSenha); 
    } 
    
    public void validarCodigoRecuperacao(String codigoRecuperacao, Pessoa pessoa) throws CodigoIncorretoException {
        pessoaManager.validarCodigoRecuperacao(codigoRecuperacao, pessoa);
    }

    public String gerarIDPessoa() {
        return pessoaManager.gerarIDPessoa();
    }

    public void gerarCodigoRecuperacao(String IDPessoa) {
        pessoaManager.gerarCodigoRecuperacao(IDPessoa);
    }

    public Cartao cadastrarCartao(ArrayList<FormaDePagamento> formas, String numero, double limite) throws EntidadeJaExisteException {
        return pessoaManager.cadastrarCartao(formas, numero, limite);
    }
    public Pix cadastrarPix(ArrayList<FormaDePagamento> formas,String chave, double saldoPix) throws EntidadeJaExisteException {
        return pessoaManager.cadastrarPix(formas, chave, saldoPix);
    }

    public void removerFormaPagamento(ArrayList<FormaDePagamento> formas, int indice) {
        pessoaManager.removerFormaPagamento(formas, indice);
    }

    public void adicionarFormaPagamento(ArrayList<FormaDePagamento> formas, FormaDePagamento forma) throws EntidadeJaExisteException {
        pessoaManager.adicionarFormaPagamento(formas, forma);
    }
    
}
