package ui;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.SortOrder;

import negocio.financeiro.FormaDePagamento;
import negocio.localizacao.Cidade;
import negocio.localizacao.Local;
import negocio.pessoas.Cliente;
import negocio.pessoas.Motorista;
import negocio.pessoas.Pessoa;
import negocio.servicos.Util;
import negocio.veiculos.Veiculo;

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
        while (menu() == 1);//menu retorna 1 para continuar rodando, 0 para finalizar
        
    }

    static int menu(){//retorna 1 para continuar rodando, 0 para finalizar
        while (true) {
            System.out.println("1- Login");
            System.out.println("2- Registre-se");
            System.out.println("3- Esqueci minha senha");//mudar senha
            System.out.println("4- Caixa de entrada");//"notificações"-> so serve para codigo de recuperação
            System.out.println("5- Sair");

            int opcao = negocio.servicos.Util.entrada.nextInt(); 
            negocio.servicos.Util.entrada.nextLine(); // Limpar o buffer do scanner

            //limpar tela aqui (TESTE)
            while (true) {//while para permitir navegação entre opções
                limparTela();
                switch (opcao) {
                    case 1 -> {

                        System.out.println("Qual seu ID?"); 
                          String IDPessoa = negocio.servicos.Util.entrada.nextLine();
                        //buscar pessoa com fachada e fica com o objeto>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                        //Pessoa pessoa = fachada.buscarPessoa(IDPessoa); ->deve retornar pessoa ou null>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                        if(IDPessoa == null){//se ID for nulo, não existe pessoa cadastrada com esse ID, então pede para cadastrar
                            //pergunta se quer cadastrar-se
                            System.out.println("ID não encontrado. Deseja cadastrar-se? (1-Sim, Qualquer tecla-Não)");
                            String resposta = negocio.servicos.Util.entrada.nextLine();
                            if (resposta.equalsIgnoreCase("1")) {
                                //chama cadastrar cliente/motorista da fachada>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
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
                                //chama menu logado, que mostra as opções de acordo com o tipo de pessoa (motorista ou cliente)
                                System.out.println("Olá " + pessoa.getNome() + "! O que deseja fazer?");
                                boolean continuar = menuLogado(pessoa);//chama o menu logado, que mostra as opções de acordo com o tipo de pessoa (motorista ou cliente)
                                while(continuar){//menu logado retorna true para continuar rodando, false para finalizar
                                    continuar = menuLogado(pessoa);
                                }
                            } else {//caso de senha incorreta, pede para redefinir
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
                    case 2 -> {//cadastro + login
                        System.out.println("Forneça seus dados, mas primeiro, quer realizar cadastro como motorista ou cliente?");
                        System.out.println("1- Motorista\n2- Cliente");
                        int tipo = negocio.servicos.Util.entrada.nextInt();
                        negocio.servicos.Util.entrada.nextLine();// Limpar o buffer do scanner
                        while(tipo != 1 && tipo != 2){//garante opção válida
                            System.out.println("Opção inválida. Tente novamente.");
                            tipo = negocio.servicos.Util.entrada.nextInt();
                            negocio.servicos.Util.entrada.nextLine();// Limpar o buffer do scanner
                        }
                        setPessoa(menuCadastro(tipo));//chama menuCadastro, que retorna a pessoa cadastrada (motorista ou cliente) e setta na interface a pessoa online
                        System.out.println("Cadastro realizado com sucesso! Seu ID é\n<" + pessoa.getIDPessoa() + ">\nLogando...");
                        esperar1200();
                        limparTela();
                        menuLogado(pessoa);
                    }
                    case 3 -> {//mudar senha
                        System.out.println("Deseja mudar sua senha? (1-Sim, Qualquer tecla-Não)");
                        String resposta = negocio.servicos.Util.entrada.nextLine();
                        if (resposta.equalsIgnoreCase("1")) {
                            mudarSenha();
                            esperar1200();
                            return 1;//retorna ao menu principal
                        } else {//se houver codigo, mostra-o
                            System.out.println("Retornando ao menu principal...");
                            esperar1200();
                            return 1;//retorna ao menu principal
                        }
                        
                }
                    case 4 -> {//caixa de entrada (para receber codigo de recuperacao e ID apos cadastro)
                        //se o codigo for nulo, não há código de recuperação
                        if(codigo == null){
                            System.out.println("Não há nada na sua caixa de entrada... Retornando ao menu...");
                            esperar1200();
                            return 1;
                        } else {//se houver codigo, mostra-o
                            System.out.println("Codigo de redefinição de senha recebido: " + codigo + "\nUtilize-o para redefinir sua senha no menu principal");
                            return 1;
                        }
                    }
                    case 5 -> {//sair do sistema
                        System.out.println("Saindo do sistema...");
                        esperar1200();
                        return 0;//sai do loop e finaliza o programa
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
            Cidade cidadeLocal = new Cidade(cidade);//instancia cidade para ser passada para o local
            Local local = new Local(cidadeLocal);//pode estar complicado: cidade é um tipo, porém basta a cidade para mostrar o mapa então será instanciado cidade sem bairro ou zona e Local apenas com cidade por agora
            //a partir daqui cadastra os atributos especificos
            switch(tipo) {
                case 1 ->{ //cadastra primeiro veiculo depois motorista
                    System.out.println("Digite a placa do veículo: ");
                    String placa = Util.entrada.nextLine();
                    //garante que a placa tenha 7 caracteres
                    while(placa.length() != 7){
                        System.out.println("Placa inválida. Digite novamente: ");
                        placa = Util.entrada.nextLine();
                    }
                    //verifica se o veiculo ja existe no repositorio, se sim, pede para cadastrar outro>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                    System.out.println("Digite a cor do veículo: ");
                    String cor = Util.entrada.nextLine();
                    System.out.println("Digite o ano do veículo: ");
                    int ano = Util.entrada.nextInt();
                    System.out.println("Digite o nome do modelo do veículo: ");
                    String modelo = Util.entrada.nextLine();
                    System.out.println("Digite o tipo do veículo (1-Econômico, 2-Luxo, 3-SUV, 4-Motocicleta): ");
                    int tipoVeiculo = Util.entrada.nextInt();
                    while(tipoVeiculo < 1 || tipoVeiculo > 4){//garante opção válida
                        System.out.println("Opção inválida. Tente novamente.");
                        tipoVeiculo = Util.entrada.nextInt();
                    }
                    //fachada.cadastrarVeiculo(placa, cor, ano, modelo, tipoVeiculo);//chama a função de cadastro de veiculo da fachada

                    //agora cadastra motorista
                    ArrayList<Veiculo> historicoVeiculos = new ArrayList<>();//historico de veiculos do motorista, pára caso ele vá ter mais de um veiculo cadastrado
                    historicoVeiculos.add(veiculo);//adiciona o veiculo ao historico de veiculos do motorista
                    //Motorista motorista = fachada.cadastrarMotorista(veiculo, IDPessoa, idade, local, nome, senhaAcesso, historicoVeiculos);//chama a função de cadastro de motorista da fachada
                    System.out.println("Motorista " + nome+ " cadastrado com sucesso.");
                } 
                case 2 -> {//cadastra cliente, adicionando seus atributos especificos e instanciando a classe cliente
                    ArrayList<FormaDePagamento> formasDePagamento = new ArrayList<>();
                    //fachada.adicionarFormaPagamento(formasDePagamento); adicionar fachada>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                    //Cliente cliente = fachada.cadastrarCliente(formasDePagamento, IDPessoa, idade, local, nome, senhaAcesso);//chama a função de cadastro de cliente da fachada
                    System.out.println("Cliente " + nome+ " cadastrado com sucesso.");
                    return cliente;
                }
                default -> System.out.println("Opção inválida.");
            }
            return null;//apenas para fins de compilação, não deve ser chamado
        }

    static boolean menuLogado(Pessoa pessoa){
        //mostra uma UI diferente para cada tipo de pessoa
        if(pessoa instanceof Cliente){ 
            //mostra UI-Cliente
            
            System.out.println("1- Solicitar viagem\n2- Ver histórico de viagens\n3- Alterar forma de pagamento\n4- Alterar dados pessoais\n5- Voltar ao menu principal");
            int opcao = negocio.servicos.Util.entrada.nextInt();
            negocio.servicos.Util.entrada.nextLine(); // Limpar o buffer do scanner
            switch (opcao) {
                case 1 -> {//solicitar viagem
                    //chama a função de solicitar viagem da fachada, que retorna uma viagem ou null se não houver motorista disponível
                    //se houver motorista, mostra o mapa e a rota, se não houver motorista, mostra mensagem de erro
                    return true;//retorna ao menu logado
                }
                case 2 -> {//ver histórico de viagens
                    //chama a função de ver histórico de viagens da fachada, que retorna o histórico de viagens do cliente
                    return true;//retorna ao menu logado
                }
                case 3 -> {//alterar forma de pagamento
                    //chama a função de alterar forma de pagamento da fachada, que altera a forma de pagamento do cliente
                    return true;//retorna ao menu logado
                }
                case 4 -> {//alterar dados pessoais
                    //chama a função de alterar dados pessoais da fachada, que altera os dados pessoais do cliente
                    return true;//retorna ao menu logado
                }
                case 5 -> {
                    limparTela();
                    return false;
                    //volta ao menu principal
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
            return true;//retorna ao menu logado
        }else{//terminar gerenciador de mapa integrado com random que receba pessoa como parametro
            //mostra UI-Motorista
            System.out.println("Olá " + pessoa.getNome() + "! O que deseja fazer?");
            System.out.println("1- Aceitar corrida\n2- Ver histórico de viagens\n3- Alterar dados pessoais\n4- Voltar ao menu principal");
            int opcao = negocio.servicos.Util.entrada.nextInt();
            negocio.servicos.Util.entrada.nextLine(); // Limpar o buffer do scanner
            switch (opcao) {
                case 1 -> {//aceitar corrida
                    //chama a função de aceitar corrida da fachada, que retorna uma viagem ou null se não houver corrida disponível
                    //se houver corrida, mostra o mapa e a rota, se não houver corrida, mostra mensagem de erro
                    return true;//retorna ao menu logado
                }
                case 2 -> {//ver histórico de viagens
                    //chama a função de ver histórico de viagens da fachada, que retorna o histórico de viagens do motorista
                    return true;//retorna ao menu logado
                }
                case 3 -> {//alterar dados pessoais
                    //chama a função de alterar dados pessoais da fachada, que altera os dados pessoais do motorista
                    return true;//retorna ao menu logado
                }
                case 4 -> {
                    limparTela();
                    return false;
                    //volta ao menu principal
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
            return true;//retorna ao menu logado
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
    
    //usado na sessão mudar senha
    //gera código de 4 dígitos e setta na interface para ser mostrada ao usuário na caixa de entrada
    //se já houver codigo, pede ao usuario e faz checagem
    //chama a função para criar senha, que setta a senha criada direto na pessoa
    //se o código de recuperação for igual ao da interface, muda a senha e retorna ao menu principal
    //se o código de recuperação for diferente, retorna ao menu principal sem mudar a senha
    static void mudarSenha(){
        if(codigo == null){//nao tem codigo
            String codigoRecuperacao = String.format("%04d", Util.r.nextInt(10000));
            setCodigo(codigoRecuperacao);//codigo armazenado na interface
            System.out.println("Código de recuperação enviado para sua caixa de entrada, utilize-o para redefinir sua senha." );
        } else {//tem codigo
           System.out.println("Digite o código de 6 dígitos enviado para sua caixa de entrada");
           String codigoRecuperacao = Util.entrada.nextLine();
           if(codigoRecuperacao.equals(codigo)){
                pessoa.setSenhaAcesso(criarSenha());
                System.out.println("Senha alterada com sucesso!"); 
            } else {
                //retorna ao menu principal
                System.out.println("Código de recuperação inválido. Senha não alterada.");
            }
        }
    }
    //cria senha em qualquer situação (criação de conta ou mudança de senha)
    //verifica se a senha é válida (mínimo de 8 caracteres) e se as senhas conferem, retornando a senha criada (em loop até que a senha seja válida)
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

  /*  SUGESTOES DO CHAT GPT
   private static void solicitarViagem(Cliente cliente) {
    // lógica de solicitar viagem (exibir mapa, encontrar motorista, etc.)
}

private static void verHistorico(Pessoa pessoa) {
    // lógica para mostrar histórico (cliente ou motorista)
}

private static void alterarPagamento(Cliente cliente) {
    // lógica para alterar forma de pagamento
}

private static void alterarDados(Pessoa pessoa) {
    // lógica para alterar dados pessoais
}

private static void aceitarCorrida(Motorista motorista) {
    // lógica para aceitar corrida (buscar uma viagem na fila)
} */

}
