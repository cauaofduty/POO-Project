package negocio.servicos;

import dados.IRepositorioPessoa;
import dados.RepositorioPessoaArquivo;
import java.util.ArrayList;
import negocio.financeiro.Cartao;
import negocio.financeiro.FormaDePagamento;
import negocio.financeiro.Pix;
import negocio.localizacao.Cidade;
import negocio.localizacao.Local;
import negocio.pessoas.Cliente;
import negocio.pessoas.GeradorPessoas;
import negocio.pessoas.Pessoa;

public class GerenciadorPessoa {
    private final RepositorioPessoaArquivo repoPessoa;
    


    public GerenciadorPessoa() {
        this.repoPessoa = new RepositorioPessoaArquivo();
    }

    public IRepositorioPessoa<Pessoa> getRepoPessoa() {
        return repoPessoa;
    }
    //CRIAR CADASTRO PARA MOTORISTA | apos isso, começar UI!
    //public void cadastrarMotorista(){}

    public void cadastrarCliente(){
        //recebe atributos necessarios apenas para criar conta
        System.out.println("Qual seu nome?");
        String nome = Util.entrada.nextLine();//apenas nome de usuario
        
        String IDPessoa = GeradorPessoas.gerarID(repoPessoa.getPessoas());//necessita checar IDs ja criados
        
        System.out.println("qual sua idade?");
        int idade = Util.entrada.nextInt();
        while (idade < 18) {//garante validade da idade
            System.out.println("Digite uma idade válida.");
            idade = Util.entrada.nextInt();
        }

        System.out.println("Agora, digite sua senha (mínimo de 8 caracteres)");
        String senhaAcesso = criarSenha();//função dedicada
        
        System.out.println("Agora, digite o nome da sua cidade:");
        String cidade = Util.entrada.nextLine();
        
        //instanciações | VER SE ESTA MUITO COMPLICADO
        ArrayList<FormaDePagamento> formasDePagamento = new ArrayList<>();
        adicionarFormaPagamento(formasDePagamento);
        Cidade cidadeLocal = new Cidade(cidade);
        Local local = new Local(cidadeLocal);//pode estar complicado: cidade é um tipo, porém basta a cidade para mostrar o mapa então será instanciado cidade sem bairro ou zona e Local apenas com cidade por agora  
        System.out.println("Cliente " + nome+ " cadastrado com sucesso.");

        //adiciona ao repositorio
        repoPessoa.adicionar(new Cliente(formasDePagamento, IDPessoa, idade, local, nome, senhaAcesso)); 
    }

    //demais funções subordinadas:

    public void adicionarFormaPagamento(ArrayList<FormaDePagamento> formas){//revisar
        System.out.println("1-Adicionar cartão\n2-Adicionar chave pix");
        int forma = Util.entrada.nextInt();
        switch (forma) {
            case 1->{
                formas.add(cadastrarCartao());
            }
            case 2->{
                formas.add(cadastrarPix());
            }
            default->{
                System.out.println("Opção inválida.");
            }
        }
    }

    
    public String criarSenha(){
        String senhaAcesso = Util.entrada.nextLine();
        
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
            }
        } //pede senha correta enquanto estiver errada
    }

    public Cartao cadastrarCartao(){
        //recebe limite
        System.out.println("Qual o limite do cartão?");
        double limiteCartao = Util.entrada.nextDouble();
        //caso limite negativo
        while (limiteCartao < 0) {
            System.out.println("Limite inválido. Digite o limite correto");
            limiteCartao = Util.entrada.nextDouble();        
        }
        //recebe numero do cartao e checa e formata com espaços
        System.out.println("Qual o numero do cartão? (com espaço)");
        String numeroCartao = Util.entrada.nextLine();
        //pedira o numero ate que possua apenas numeros e possua 16 digitos
        while (numeroCartao.length() < 16 && !numeroCartao.matches("\\d+")) {
            System.out.println("Digite um número válido.");
            numeroCartao = Util.entrada.nextLine();
        }      
        //para adicioanr os espacoes manualmente para evitar mais complexidade
         String numeroCartaoFormatado = numeroCartao.substring(0, 4) + " " +
                                        numeroCartao.substring(4, 8) + " " +
                                        numeroCartao.substring(8, 12) + " " +
                                        numeroCartao.substring(12, 16);
        
        int cVV = Util.r.nextInt(1000);
        String cVVFormatado =String.format("%03d", cVV);
        return new Cartao(limiteCartao, numeroCartaoFormatado, cVVFormatado);
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
}
