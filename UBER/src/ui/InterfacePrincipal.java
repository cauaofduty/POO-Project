package ui;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.SortOrder;

import negocio.exceptions.PessoaNaoEncontradaException;
import negocio.financeiro.FormaDePagamento;
import negocio.localizacao.Cidade;
import negocio.localizacao.Local;
import negocio.pessoas.Cliente;
import negocio.pessoas.GeradorPessoas;
import negocio.pessoas.Pessoa;
import negocio.servicos.Util;

class InterfacePrincipal {//destaquei com >>>>> a linha de algo que falta
    //instanciar fachada aqui E ANALIZAR ONDE HAVERÃO LIMPEZAS DE TELA E DEMORAS DE TELA>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    private static String codigo;//codigo de recuperação caso de senha esquecida
    private static Pessoa pessoa;//pessoa logada, pode ser motorista ou cliente
    public static void main(final String[] args) throws Exception {
        setCodigo(null);//codigo começa vazio
        System.out.println("\tH&C TRANSPORTES");
        System.out.println("TE LEVANDO ONDE VOCÊ QUER IR");
        esperar1200();
        System.out.println("Bem vindo ao sistema de transporte H&C! Escolha uma opção");
        
        //main menu chamado nesse loop para permitir navegação
        int valor = 1;
        while (valor == 1) {
            valor = menu();   
         }
        
    }

   
    static int menu(){//retorna 1 para continuar rodando, 0 para finalizar
        while (true) {
            System.out.println("1- Login");
            System.out.println("2- Registre-se");
            System.out.println("3- Caixa de entrada");//"notificações"-> so serve para codigo de recuperação
            System.out.println("4- Sair");

            int opcao = negocio.servicos.Util.entrada.nextInt(); 
            negocio.servicos.Util.entrada.nextLine(); // Limpar o buffer do scanner

            //limpar tela aqui (TESTE)
            while (true) {//while para permitir navegação entre opções
                limparTela();
                switch (opcao) {
                    case 1 -> {
                        //ver se presta limpar a tela aqui

                        System.out.println("Qual seu ID?"); 
                          String IDPessoa = negocio.servicos.Util.entrada.nextLine();
                        //buscar pessoa com fachada e fica com o objeto>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                        //Pessoa pessoa = fachada.buscarPessoa(IDPessoa); ->deve retornar pessoa ou null>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                        if(IDPessoa == null){//se ID for nulo, não existe pessoa cadastrada com esse ID, então pede para cadastrar
                            //pergunta se quer cadastrar-se
                            System.out.println("ID não encontrado. Deseja cadastrar-se? (1-Sim, Qualquer tecla-Não)");
                            String resposta = negocio.servicos.Util.entrada.nextLine();
                            if (resposta.equalsIgnoreCase("1")) {
                                //chama cadastrar cliente/motorista da fachada>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.>>>>
                            } else {
                                //não quer cadastrar, retorna ao menu principal
                                System.out.println("Retornando ao menu principal...");
                                esperar1200();
                                limparTela();
                                return 1;
                            }

                        }else{//pessoa existe
                            System.out.println("Bem vindo de volta " + "pessoa.nome" + "! Digite sua senha (mínimo de 8 caracteres):");  
                            
                            //se a senha estiver correta, true , else, pede para redefinir enviando codigo para caixa de entrada  
                            if(receberSenhaLogin("pessoa.senhaAcesso")){
                                System.out.println("Olá " + "pessoa.nome" + "! O que deseja fazer?");
                                //chama menu logado para essa pessoa e lá, checa se pessoa é motorista ou cliente>>>>>>>>>>>>>>>>>>>>>>>>>>
                                menuLogado(pessoa);//chama menu logado, que mostra as opções de acordo com o tipo de pessoa (motorista ou cliente)
                            } else {
                                System.out.println("Senha incorreta. Você deseja redefinir sua senha? (1-Sim, Qualquer tecla-Não)");
                                String resposta = negocio.servicos.Util.entrada.nextLine();
                                if (resposta.equalsIgnoreCase("1")) {
                                    //gera código de 4 dígitos e setta na interface para ser mostrada ao usuário na caixa de entrada
                                    String codigoRecuperacao = String.format("%04d", Util.r.nextInt(10000));
                                    setCodigo(codigoRecuperacao);//codigo armazenado na interface
                                    System.out.println("Código de recuperação enviado para sua caixa de entrada, utilize-o para redefinir sua senha." );
                                    esperar1200();
                                    return 1;//retorna ao menu principal
                                } else {
                                    //senha não foi redefinida, retorna ao menu principal
                                    System.out.println("Retornando ao menu principal...");
                                    esperar1200();
                                    limparTela();
                                    return 1;
                                }
                            }
                        }
                        
                    }
                    case 2 -> {
                        System.out.println("Forneça seus dados, mas primeiro, quer realizar cadastro como motorista ou cliente?");
                        System.out.println("1- Motorista\n2- Cliente");
                        int tipo = negocio.servicos.Util.entrada.nextInt();
                        negocio.servicos.Util.entrada.nextLine();// Limpar o buffer do scanner
                        while(tipo != 1 && tipo != 2){//garante opção válida
                            System.out.println("Opção inválida. Tente novamente.");
                            tipo = negocio.servicos.Util.entrada.nextInt();
                            negocio.servicos.Util.entrada.nextLine();// Limpar o buffer do scanner
                        }
                        setPessoa(menuCadastro(tipo));//chama menuCadastro, que retorna a pessoa cadastrada (motorista ou cliente) e setta na interface
                        System.out.println("Cadastro realizado com sucesso! Seu ID é\n<" + pessoa.getIDPessoa() + ">\nLogando...");
                        //ver se realiza login aqui ou no menu principal
                        esperar1200();
                        return 1;//retorna ao menu principal
                    }
                    case 3 -> {//caixa de entrada (para receber codigo de recuperacao e ID apos cadastro)
                        break;
                    }
                    case 4 -> {//caixa de entrada (para receber codigo de recuperacao e ID apos cadastro)
                        
                        return;
                    }
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }    
            }
            
        }
    } 
    
    static Pessoa menuCadastro(int tipo){
            //recebe atributos
            System.out.println("Qual seu nome?");
            String nome = Util.entrada.nextLine();//apenas nome de usuario
            //String IDPessoa = GeradorPessoas.gerarID("array do repositorio");//necessita checar IDs ja criados por meio da fachada>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            System.out.println("qual sua idade?");
            int idade = Util.entrada.nextInt();
            while (idade < 18) {//garante validade da idade
                System.out.println("Digite uma idade válida.");
                idade = Util.entrada.nextInt();
            }
            //função dedicada para receber senha
            String senhaAcesso = criarSenha();
            System.out.println("Agora, digite o nome da sua cidade:");
            String cidade = Util.entrada.nextLine();
            //a partir daqui cadastra os atributos especificos
            switch(tipo) {
                case 1 ->{
                    //criar função para cadastrar veiculo no repositorio de veiculos;
                    //cadastra motorista
                } 
                case 2 -> {//cadastra cliente, adicionando seus atributos especificos e instanciando a classe cliente
                    ArrayList<FormaDePagamento> formasDePagamento = new ArrayList<>();
                    adicionarFormaPagamento(formasDePagamento);
                    Cidade cidadeLocal = new Cidade(cidade);
                    Local local = new Local(cidadeLocal);//pode estar complicado: cidade é um tipo, porém basta a cidade para mostrar o mapa então será instanciado cidade sem bairro ou zona e Local apenas com cidade por agora  
                    Cliente cliente = new Cliente(formasDePagamento, IDPessoa, idade, local, nome, senhaAcesso);
                    System.out.println("Cliente " + nome+ " cadastrado com sucesso.");
                    //adiciona ao repositorio e retorna o cliente
                    this.repoPessoa.adicionar(cliente);//adiciona ao repositorio
                    return cliente;
                }
                default -> System.out.println("Opção inválida.");
            }
            return null;//apenas para fins de compilação, não deve ser chamado
        }

    static void menuLogado(Pessoa pessoa){
        //mostra uma UI diferente para cada tipo de pessoa
        if(pessoa instanceof Cliente){ 
            //mostra UI-Cliente
            //terminar gerenciador de mapa integrado com random que receba pessoa como parametro
        }else{
            //mostra UI-Motorista
        }

    }
     static boolean receberSenhaLogin(String senhaEsperada){//perguntar a IA se isto e correto
        String senha = negocio.servicos.Util.entrada.nextLine();
        int contador = 0;
        while (!senhaEsperada.equals(senha)) {
            System.out.println("Senhas não conferem. Digite novamente: ");
            senha = negocio.servicos.Util.entrada.nextLine();
            contador++;
            if (contador == 3) return false;// vai fazer processo de recuperação de senha para essa pessoa
        
        }
        return true;
    }
    /*
     public void mudarSenha(int codigoRecuperacao, String novaSenha, Pessoa pessoa){//muda a senha de acordo com o ID e o código de recuperação
        Pessoa pessoa = repoPessoa.buscarPorID(IDPessoa);
        if (pessoa != null) {
            if (pessoa.getCodigoRecuperacao() == codigoRecuperacao) {
                pessoa.setSenhaAcesso(novaSenha);
            } else {
                System.out.println("Código de recuperação inválido.");
            }
        } else {
            throw new PessoaNaoEncontradaException(IDPessoa);
        }

    }*/
    public static String criarSenha(){
        System.out.println("Digite sua senha (mínimo de 8 caracteres)");
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
    
    public static void esperar1200(){
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String getCodigo() {
        return codigo;
    }

    public static void setCodigo(String codigo) {
        InterfacePrincipal.codigo = codigo;
    }

    public static Pessoa getPessoa() {
        return pessoa;
    }

    public static void setPessoa(Pessoa pessoa) {
        InterfacePrincipal.pessoa = pessoa;
    }
}
