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
    //funcoes de cadastro e auxiliares>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
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
    
    public void criarSenha(String senha, Pessoa pessoa) throws EntidadeNaoEncontradaException, IllegalArgumentException {
        Pessoa encontrada = repoPessoa.buscarPorID(pessoa.getIDPessoa());
        //caso pessoa não exista
        if (encontrada == null) throw new EntidadeNaoEncontradaException("Pessoa não encontrada.");
        //caso senha fora do padrão
        if(senha.length() < 8) throw new IllegalArgumentException("Senha não pode ter menos de 8 caracteres.");

        encontrada.setSenhaAcesso(senha);
    }

    public Motorista cadastrarMotorista(Veiculo veiculo, String IDPessoa, int idade, Local local, String nome, String senhaAcesso) throws EntidadeJaExisteException {//separado pois tratam atributos diferentes
        boolean disponivel = true; //motorista começa disponível
        //exception caso IDPessoa ja exista no repositório
        if (repoPessoa.buscarPorID(IDPessoa) != null) {
            throw new EntidadeJaExisteException("Não foi possível cadastrar, já existe motorista com o mesmo ID.");
        }
        //instancia motorista com os atributos necessários e adiciona ao repositório;
        Motorista motorista = new Motorista(veiculo, IDPessoa, disponivel, idade, local, nome, senhaAcesso);
        repoPessoa.adicionar(motorista);//adiciona ao repositorio
        return motorista;
    }

    public Pessoa buscarPessoa(String IDPessoa) throws EntidadeNaoEncontradaException {//busca por ID, retorna pessoa ou Exception
        //caso null = não encontrado
        if(repoPessoa.buscarPorID(IDPessoa) == null) throw new EntidadeNaoEncontradaException("ID não encontrado na base de dados.");
        return repoPessoa.buscarPorID(IDPessoa);
    }


    //demais funções subordinadas:

    public Cartao cadastrarCartao(ArrayList<FormaDePagamento> formas, String numero, double limite) throws EntidadeJaExisteException {
        Cartao cartao = new Cartao(limite, numero); 
        if (formas.contains(cartao)) throw new EntidadeJaExisteException("Forma de pagamento já cadastrada.");
        formas.add(cartao);
        return cartao;
    }
    public Pix cadastrarPix(ArrayList<FormaDePagamento> formas, String chave, double saldoPix)throws EntidadeJaExisteException {
        Pix pix = new Pix(chave, saldoPix);
        if(formas.contains(pix)) throw new EntidadeJaExisteException("Forma de pagamento já cadastrada.");
        //adiciona forma de pagamento ao cliente, se já não estiver cadastrado 
        formas.add(pix);
        return pix;
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    
    
    //funcões para UI>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public void receberSenhaLogin(String senhaEsperada, String IDPessoa) throws SenhaIncorretaException, EntidadeNaoEncontradaException {
        Pessoa pessoa = repoPessoa.buscarPorID(IDPessoa);
        //se pessoa for null ou se senha estiver incorreta, lança exceção 
        if (pessoa == null) {
            throw new EntidadeNaoEncontradaException("Pessoa não encontrada.");
        } else if (!pessoa.getSenhaAcesso().equals(senhaEsperada)) {
            throw new SenhaIncorretaException("Senha incorreta");
        }
    }
    public void removerFormaPagamento(ArrayList<FormaDePagamento> formas, int indice) {
        if (formas == null || formas.isEmpty()) {
            throw new IllegalArgumentException("Não há formas de pagamento cadastradas.");
        }
    
        if (indice < 0 || indice >= formas.size()) {
            throw new IllegalArgumentException("Índice inválido. Nenhuma forma de pagamento removida.");
        }
    
        formas.remove(indice);
    }

    public void adicionarFormaPagamento(ArrayList<FormaDePagamento> formas, FormaDePagamento forma) throws EntidadeJaExisteException {
        if (formas.contains(forma)) {
            throw new EntidadeJaExisteException("Forma de pagamento já cadastrada.");
        }
        formas.add(forma);
    }

    public String formatarCartao(String numeroCartao) {
        //para adicionar os espacos manualmente para evitar mais complexidade
        return numeroCartao.substring(0, 4) + " " +
                numeroCartao.substring(4, 8) + " " +
                numeroCartao.substring(8, 12) + " " +
                numeroCartao.substring(12, 16);
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
    public ArrayList<Cliente> listarClientes() {
        return repoPessoa.listarClientes();
    }

    public ArrayList<Motorista> listarMotoristas() {
        return repoPessoa.listarMotoristas();
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    /* public Motorista buscarMotoristaDisponivel() {
        for (Motorista motorista : listarMotoristas()) {
            if (motorista.isDisponivel()) {
                return motorista;
            }
        }
        return null;
    } */
}
