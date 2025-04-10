package ui;

import java.util.ArrayList;

import negocio.exceptions.CodigoIncorretoException;
import negocio.exceptions.EntidadeNaoEncontradaException;
import negocio.exceptions.SenhaIncorretaException;
import negocio.financeiro.FormaDePagamento;
import negocio.localizacao.Cidade;
import negocio.localizacao.Local;
import negocio.pessoas.Cliente;
import negocio.pessoas.Pessoa;
import negocio.servicos.Fachada;
import negocio.servicos.Util;
import negocio.veiculos.Veiculo;

class InterfacePrincipal {//destaquei com >>>>> a linha de algo que falta

    public static void main(final String[] args) throws Exception {
        
        //função apenas para legibilidade da UI
        mensagemInicial();
        
        //main menu chamado nesse loop para permitir navegação
        //com flag para continuar rodando o menu
        boolean continuar = true;
        while (continuar) continuar = menu();
        //FIM DA MAIN!
    }

    static boolean menu(){//todos os menus retonam boolean para permitir navegação entre eles
        //finally
        Pessoa pessoaOnline = null;//variavel para receber a pessoa logada
        Fachada fachada = Fachada.getInstancia(); 
        while (true) {

            System.out.println("Escolha uma opção");
            System.out.println("1- Login");
            System.out.println("2- Registre-se");
            System.out.println("3- Esqueci minha senha");//mudar senha
            System.out.println("4- Caixa de entrada");//"notificações"-> so serve para codigo de recuperação
            System.out.println("5- Sair");
            
            //Recebe opcao do usuario e limpa o buffer do scanner
            int opcao = negocio.servicos.Util.entrada.nextInt(); 
            negocio.servicos.Util.entrada.nextLine(); 

            //while para permitir navegação entre opções no switch
            //começa limpando tela
            while (true) {
                limparTela();
                switch (opcao) {
                    //login
                    case 1 -> {
                        System.out.println("Qual seu ID?");
                        String IDPessoa = negocio.servicos.Util.entrada.nextLine();

                        //fara tratamento de duas exceptions
                        try {
                            Pessoa pessoa = fachada.buscarPessoa(IDPessoa);
                            System.out.println("Bem-vindo de volta " + pessoa.getNome() + "! Digite sua senha (lembrando limite mínimo de 8 caracteres):");
                            String senha = negocio.servicos.Util.entrada.nextLine();
                            //preparado para tratar exceptions de senha
                            try {
                                // Tenta validar a senha
                                fachada.receberSenhaLogin(senha, IDPessoa);
                                // Se a senha estiver correta, entra no menu logado
                                System.out.println("Olá " + pessoa.getNome() + "! O que deseja fazer?");
                                boolean continuar = menuLogado(pessoa);
                                while (continuar) {
                                    continuar = menuLogado(pessoa);
                                }
                                return true;//retorna ao menu principal
                                
                            // Se a senha estiver incorreta, lança uma exceção
                            } catch (SenhaIncorretaException e) {
                                // Senha incorreta tratada aqui
                                System.out.println("Senha incorreta. Deseja redefinir sua senha? (1-Sim, Qualquer tecla-Não)");
                                String resposta = negocio.servicos.Util.entrada.nextLine();
                                //caso sim, pessoa recebe codigo para ir a sessao redefinir senha
                                if (resposta.equalsIgnoreCase("1")) {
                                    fachada.gerarCodigoRecuperacao(IDPessoa);
                                    System.out.println("Código de recuperação enviado para sua caixa de entrada, utilize-o para redefinir sua senha.");
                                }
                                //caso não, retorna ao menu principal
                                System.out.println("Retornando ao menu principal...");
                                esperar1200();
                                limparTela();
                                return true;
                            }

                        } catch (EntidadeNaoEncontradaException e) {
                            // Trata o caso de pessoa não encontrada
                            System.out.println("ID não encontrado. Deseja cadastrar-se? (1-Sim, Qualquer tecla-Não)");
                            String resposta = negocio.servicos.Util.entrada.nextLine();
                            //caso sim, vai para cadastro no switch
                            if (resposta.equalsIgnoreCase("1")) {
                                limparTela();
                                opcao = 2;//vai para opção de cadastro no switch
                                continue;
                            } else {
                                System.out.println("Retornando ao menu principal...");
                                esperar1200();
                                limparTela();
                                return true;
                            }
                        }
                    }
                    case 2 -> {//cadastro + login
                        //Recebe tipo para qual cadastrar e limpa o buffer do scanner
                        System.out.println("Forneça seus dados, mas primeiro, quer realizar cadastro como motorista ou cliente?");
                        System.out.println("1- Motorista\n2- Cliente");
                        int tipo = negocio.servicos.Util.entrada.nextInt();
                        negocio.servicos.Util.entrada.nextLine();
                        
                        //garante opção válida
                        while(tipo != 1 && tipo != 2){
                            System.out.println("Opção inválida. Tente novamente.");
                            tipo = negocio.servicos.Util.entrada.nextInt();
                            negocio.servicos.Util.entrada.nextLine();
                        }
                        
                        //chama menuCadastro, que retorna a pessoa cadastrada (motorista ou cliente) e mostra menu logado
                        pessoaOnline = menuCadastro(tipo);
                        System.out.println("Cadastro realizado com sucesso! Seu ID é\n<" + pessoaOnline.getIDPessoa() + ">\nLogando...");
                        esperar1200();
                        limparTela();
                        menuLogado(pessoaOnline);
                    }
                    case 3 -> {//mudar senha
                        System.out.println("Deseja mudar sua senha? (1-Sim, Qualquer tecla-Não)");
                        String resposta = Util.entrada.nextLine();
                    
                        if (resposta.equalsIgnoreCase("1")) {
                            System.out.println("Digite seu ID:");
                            String IDPessoa = Util.entrada.nextLine();
                    
                            try {
                                // Verifica se a pessoa existe
                                Pessoa pessoa = fachada.buscarPessoa(IDPessoa);
                    
                                System.out.println("Digite seu código:");
                                String codigo = Util.entrada.nextLine();
                    
                                // Valida o código de recuperação
                                fachada.validarCodigoRecuperacao(codigo, pessoa);
                    
                                // Solicita nova senha
                                System.out.println("Digite sua nova senha:");
                                String novaSenha = Util.entrada.nextLine();
                    
                                fachada.mudarSenha(novaSenha, pessoa); // altera de fato
                                System.out.println("Senha alterada com sucesso!");
                    
                                System.out.println("Você precisa fazer login...");
                                esperar1200();
                                limparTela();
                    
                                // Volta para o menu de login
                                opcao = 1;
                                continue;
                    
                            } catch (EntidadeNaoEncontradaException | CodigoIncorretoException e) {
                                System.out.println("Erro: " + e.getMessage());
                                System.out.println("Tente novamente.");
                                esperar1200();
                                limparTela();
                                opcao = 3; // volta ao case 3
                                continue;
                            }
                        //nao quer mudar senha
                        } else {
                            System.out.println("Retornando ao menu principal...");
                            esperar1200();
                            limparTela();
                            opcao = -1; // menu principal
                        }
                    }
                    case 4 -> {
                        if (pessoaOnline == null) {
                            System.out.println("Nenhum usuário logado no momento.");
                            esperar1200();
                            return true;
                        }
                        //se existir pessoa online realiza isto:
                        String codigo = pessoaOnline.getCodigoRecuperacao();
                    
                        if (codigo == null || codigo.isBlank()) {
                            System.out.println("Não há nada na sua caixa de entrada... Retornando ao menu...");
                            esperar1200();
                        //mostra codigo e oferece ir direto mudar senha
                        } else {
                            System.out.println("Código de redefinição de senha recebido: " + codigo + "\nUtilize-o para redefinir sua senha.");
                            System.out.println("Deseja redefinir sua senha? (1-Sim, Qualquer tecla-Não)");
                            String resposta = Util.entrada.nextLine();
                            if (resposta.equalsIgnoreCase("1")) {
                                opcao = 3; // vai para mudar senha
                                continue;
                            } else {
                                System.out.println("Retornando ao menu principal...");
                                esperar1200();
                            }
                        }
                        return true;
                    }
                    case 5 -> {//sair do sistema
                        System.out.println("H*&C Transportes agradece a sua preferência!");
                        esperar1200();
                        limparTela();
                        return false;//sai do loop e finaliza o programa
                    }
                    default -> {
                        System.out.println("Digite uma opção válida");
                        esperar1200();  
                        limparTela();
                    }
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
    public static void mensagemInicial(){//apenas para legibilidade da UI
        System.out.println("\tH&C TRANSPORTES");
        System.out.println("TE LEVANDO ONDE VOCÊ QUER IR");
        esperar1200();
        System.out.println("Bem vindo ao sistema de transporte H&C!");
    }
}    

   
