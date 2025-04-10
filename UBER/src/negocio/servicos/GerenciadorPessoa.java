package negocio.servicos;

import dados.IRepositorioPessoa;
import dados.RepositorioPessoaArquivo;
import java.util.ArrayList;
import negocio.exceptions.CodigoIncorretoException;
import negocio.exceptions.EntidadeJaExisteException;
import negocio.exceptions.EntidadeNaoEncontradaException;
import negocio.exceptions.SenhaIncorretaException;
import negocio.financeiro.Cartao;
import negocio.financeiro.FormaDePagamento;
import negocio.financeiro.Pix;
import negocio.localizacao.Local;
import negocio.pessoas.Cliente;
import negocio.pessoas.Motorista;
import negocio.pessoas.Pessoa;
import negocio.veiculos.Veiculo;

public class GerenciadorPessoa {
    private final RepositorioPessoaArquivo repoPessoa;

    public GerenciadorPessoa() {
        this.repoPessoa = new RepositorioPessoaArquivo();
    }

    public IRepositorioPessoa<Pessoa> getRepoPessoa() {
        return repoPessoa;
    }

    public Cliente cadastrarCliente(ArrayList<FormaDePagamento> formaDePagamentos, String IDPessoa, int idade, Local local, String nome, String senhaAcesso) throws EntidadeJaExisteException {//separado pois tratam atributos diferentes
        //exception caso IDPessoa ja exista no repositório
        if (repoPessoa.buscarPorID(IDPessoa) != null) {
            throw new EntidadeJaExisteException("Não foi possível cadastrar, já existe cliente com o mesmo ID.");
        }
        //instancia e adiciona ao repositorio
        Cliente cliente = new Cliente(formaDePagamentos, IDPessoa, idade, local, nome, senhaAcesso);
        repoPessoa.adicionar(cliente);//adiciona ao repositorio
        return cliente;
    }

    public Motorista cadastrarMotorista(Veiculo veiculo, String IDPessoa, int idade, Local local, String nome, String senhaAcesso) throws EntidadeJaExisteException {//separado pois tratam atributos diferentes
        //exception caso IDPessoa ja exista no repositório
        if (repoPessoa.buscarPorID(IDPessoa) != null) {
            throw new EntidadeJaExisteException("Não foi possível cadastrar, já existe motorista com o mesmo ID.");
        }
        //instancia motorista com os atributos necessários e adiciona ao repositório;
        Motorista motorista = new Motorista(idade, veiculo, IDPessoa, disponivel, idade, local, nome);
        repoPessoa.adicionar(motorista);//adiciona ao repositorio
        return motorista;
    }

    public Pessoa buscarPessoa(String IDPessoa) throws EntidadeNaoEncontradaException {//busca por ID, retorna pessoa ou Exception
        return repoPessoa.buscarPorID(IDPessoa);
    }

    public ArrayList<Cliente> listarClientes() {
        return repoPessoa.listarClientes();
    }

    public ArrayList<Motorista> listarMotoristas() {
        return repoPessoa.listarMotoristas();
    }

    //demais funções subordinadas:

    public void removerCliente(Cliente cliente) throws EntidadeNaoEncontradaException {//para pessoa administrador
        if (repoPessoa.buscarPorID(cliente.getIDPessoa()) == null) {
            throw new EntidadeNaoEncontradaException("Cliente não encontrado.");
        }
        //repoPessoa.remover(cliente);>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    }
    public Cartao cadastrarCartao(ArrayList<FormaDePagamento> formas,String numero, double limite) throws EntidadeJaExisteException {
        Cartao cartao = new Cartao(limite, numero); 
        if (formas.contains(cartao)) throw new EntidadeJaExisteException("Forma de pagamento já cadastrada.");
        return cartao;
    }
    
    public Pix cadastrarPix(ArrayList<FormaDePagamento> formas, String chave, double saldoPix)throws EntidadeJaExisteException {
        Pix pix = new Pix(chave, saldoPix);
        
        //adiciona forma de pagamento ao cliente, se já não estiver cadastrado 
        
        formas.add(pix);
        return pix;
    }

    public String formatarCartao(String numeroCartao) {
        //para adicionar os espacos manualmente para evitar mais complexidade
        return numeroCartao.substring(0, 4) + " " +
                numeroCartao.substring(4, 8) + " " +
                numeroCartao.substring(8, 12) + " " +
                numeroCartao.substring(12, 16);
    }

    //funcões para login, criar senha e mudar senha
    public void receberSenhaLogin(String senhaEsperada, String IDPessoa) throws SenhaIncorretaException, EntidadeNaoEncontradaException {
        Pessoa pessoa = repoPessoa.buscarPorID(IDPessoa);
        //se pessoa for null ou se senha estiver incorreta, lança exceção 
        if (pessoa == null) {
            throw new EntidadeNaoEncontradaException("Pessoa não encontrada.");
        } else if (!pessoa.getSenhaAcesso().equals(senhaEsperada)) {
            throw new SenhaIncorretaException("Senha incorreta");
        }
    }

    public void criarSenha(String senha, Pessoa pessoa) throws EntidadeNaoEncontradaException {
        Pessoa encontrada = repoPessoa.buscarPorID(pessoa.getIDPessoa());
    
        if (encontrada == null) {
            throw new EntidadeNaoEncontradaException("Pessoa não encontrada.");
        }
        encontrada.setSenhaAcesso(senha);
    }
    public void mudarSenha(Pessoa pessoa, String novaSenha) {
        pessoa.setSenhaAcesso(novaSenha);
    }
    public void gerarCodigoRecuperacao(String IDPessoa){//nao necessita lançar exceção pois é garantido que pessoa existe
        Pessoa pessoa = repoPessoa.buscarPorID(IDPessoa);
        String codigo = String.format("%04d", Util.r.nextInt(10000)); //4 digitos
        pessoa.setCodigoRecuperacao(codigo);
    }
    public String gerarIDPessoa() {
        String IDPessoa = String.format("%05d", Util.r.nextInt(100000)); //5 digitos
        //"enquanto houverem IDs iguais, gera novo ID"
        while (repoPessoa.buscarPorID(IDPessoa) != null) {
            IDPessoa = String.format("%04d", Util.r.nextInt(10000)); //4 digitos
        }
        return IDPessoa;
    }
    public void validarCodigoRecuperacao(String codigoInformado, Pessoa pessoa) throws CodigoIncorretoException {
        //verifica invalidade do codigo
        if (pessoa == null || pessoa.getCodigoRecuperacao() == null || !pessoa.getCodigoRecuperacao().equals(codigoInformado)) {
            throw new CodigoIncorretoException("Código de recuperação inválido.");
        }
    }

    public Motorista buscarMotoristaDisponivel() {
        for (Motorista motorista : listarMotoristas()) {
            if (motorista.isDisponivel()) {
                return motorista;
            }
        }
        return null;
    }
}
