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
        //instancia motorista com os atributos necessários e adiciona ao repositório
        Motorista motorista = new Motorista(idade, veiculo, IDPessoa, idade, local, nome);
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

    public void adicionarFormaPagamento(ArrayList<FormaDePagamento> formas ,FormaDePagamento f){//revisar
        
        int forma = Util.entrada.nextInt();
        switch (forma) {
            case 1->{
                formas.add(f);
            }
            case 2->{
                formas.add(cadastrarPix());
            }
            default->{
                System.out.println("Opção inválida.");
            }
        }
    }
    public Pix cadastrarPix(){
        String chave = Util.entrada.nextLine();
        while(chave.contains(" ")){//unica checagem que farei pois chave pode ser cpf, email, celular e aleatoria e nenhum tipo contem espaços 
            System.out.println("Digite uma chave válida.");
            chave = Util.entrada.nextLine(); 
        }
        System.out.println("Digite seu saldo:");
        double saldoPix = Util.entrada.nextDouble();
        while(saldoPix < 0){
            System.out.println("Digite seu saldo correto.");
            saldoPix = Util.entrada.nextDouble();
        }
        return new Pix(chave, saldoPix);
    }


    public String formatarCartao(String numeroCartao) {
        //para adicionar os espacos manualmente para evitar mais complexidade
        return numeroCartao.substring(0, 4) + " " +
                numeroCartao.substring(4, 8) + " " +
                numeroCartao.substring(8, 12) + " " +
                numeroCartao.substring(12, 16);
    }

    public Pix cadastrarPix(){
        String chave = Util.entrada.nextLine();
        while(chave.contains(" ")){//unica checagem que farei pois chave pode ser cpf, email, celular e aleatoria e nenhum tipo contem espaços 
            System.out.println("Digite uma chave válida.");
            chave = Util.entrada.nextLine(); 
        }
        System.out.println("Digite seu saldo:");
        double saldoPix = Util.entrada.nextDouble();
        while(saldoPix < 0){
            System.out.println("Digite seu saldo correto.");
            saldoPix = Util.entrada.nextDouble();
        }
        return new Pix(chave, saldoPix);
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
        /* reutilizar na UI
        if(senhaAcesso.isEmpty() || senhaAcesso.length() < 8){
            System.out.println("Digite uma senha válida.");
            criarSenha();
        }
        System.out.println("Confirme sua senha:");
        while (true){ 
            String confirmarSenha = Util.entrada.nextLine();
            if(!senhaAcesso.equals(confirmarSenha)){
                System.out.println("Senhas não coincidem. Digite a confirmação novamente:");
            }else{
                return senhaAcesso;
                // talvez não seja necessário retornar senha, igualmente para cartão e pix
            }
        } //pede senha correta enquanto estiver errada */
}
