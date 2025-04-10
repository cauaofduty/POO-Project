package ui;

import java.util.ArrayList;
import negocio.exceptions.CodigoIncorretoException;
import negocio.exceptions.EntidadeJaExisteException;
import negocio.exceptions.EntidadeNaoEncontradaException;
import negocio.exceptions.SenhaIncorretaException;
import negocio.financeiro.FormaDePagamento;
import negocio.localizacao.Cidade;
import negocio.localizacao.Local;
import negocio.pessoas.Pessoa;
import negocio.servicos.Fachada;
import negocio.servicos.Util;
import negocio.veiculos.Veiculo;

class InterfacePrincipal {//destaquei com >>>>> a linha de algo que falta

    public static void main(final String[] args) throws Exception {
        
        //função apenas para legibilidade da UI
        mensagemInicial();
        esperar1200();
        limparTela(); 
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
                                fachada.receberSenhaLogin(senha, IDPessoa);
                                // Tenta validar a senha
                                // Se a senha estiver correta, entra no menu logado
                                System.out.println("Olá " + pessoa.getNome() + "! O que deseja fazer?");
                                System.out.println("Menu logado abaixo");
                                esperar1200();
                                limparTela();
                                /* boolean continuar = menuLogado(pessoa);
                                while (continuar) {
                                    continuar = menuLogado(pessoa);
                                } */
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
                        pessoaOnline = menuCadastro();
                        System.out.println("Cadastro realizado com sucesso! Seu ID é\n<" + pessoaOnline.getIDPessoa() + ">\nLogando...");
                        esperar1200();
                        limparTela();
                        //menuLogado(pessoaOnline);
                        System.out.println("menu logado abaixo");
                        esperar1200();
                        limparTela();
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
    
    static Pessoa menuCadastro() { // falta limpar tela aqui e colocar delay
        Fachada fachada = Fachada.getInstancia();
        
        //variaveis para receber os dados do cadastro
        String nome = null, senha = null, confirmarSenha, cidade = null, placa, cor, modelo, IDPessoa = null;
        int idade = 0, tipoCadastro = 0, tipoVeiculo, ano;
        Pessoa pessoa = null;
        Local local = null;
        Veiculo veiculo;
        ArrayList<FormaDePagamento> formas = new ArrayList<>();

        boolean cadastroFinalizado = false;
        //usado para navegar entre etapas em caso de erros
        int etapa = 1;
        while (!cadastroFinalizado) {
            switch (etapa) {
                case 1 -> { //apenas nome
                    System.out.println("Qual seu nome?");
                    nome = Util.entrada.nextLine();
                    etapa++;
                }
                case 2 -> {// idade (retorna exception caso menor de idade)
                    System.out.println("Qual sua idade? (18 anos ou mais)");
                    try {
                        idade = Util.entrada.nextInt();
                        Util.entrada.nextLine();
                        if (idade < 18) throw new IllegalArgumentException("Digite uma idade válida.");
                        //caso idade seja valida, incrementa etapa
                        etapa++;
                    } catch (IllegalArgumentException e) {
                        //mostra erro e retorna ao inicio da etapa
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                case 3 -> {// criação e confirmação de senha
                    System.out.println("Digite sua senha:");
                    senha = Util.entrada.nextLine();
                    System.out.println("Confirme sua senha:");
                    confirmarSenha = Util.entrada.nextLine();
                    if (!senha.equals(confirmarSenha)) {
                        System.out.println("As senhas não coincidem.");
                    } else if (senha.length() < 8) {
                        System.out.println("A senha deve ter pelo menos 8 caracteres.");
                    } else {
                        etapa++;
                    }
                }
                case 4 -> {//nome da cidade
                    System.out.println("Digite o nome da sua cidade:");
                    cidade = Util.entrada.nextLine();
                    etapa++;
                }
                case 5 -> {
                    //decisão de cadastro
                    System.out.println("Como quer se cadastrar?");
                    System.out.println("1 - Motorista\n2 - Cliente");
                    
                    tipoCadastro = Util.entrada.nextInt();
                    Util.entrada.nextLine();
                    
                    while(tipoCadastro != 1 && tipoCadastro != 2) {
                        System.out.println("Opção inválida. Tente novamente:");
                        tipoCadastro = Util.entrada.nextInt();
                    }
                    etapa++;
                }
                case 6 -> {
                    Cidade cidadeObj = new Cidade(cidade);
                    local = new Local(cidadeObj);
                    IDPessoa = fachada.gerarIDPessoa();
                    etapa++;
                }
                case 7 -> {
                    if (tipoCadastro == 1) {
                        System.out.println("Digite a placa do veículo:");
                        placa = Util.entrada.nextLine();
                        while (placa.length() != 7) {
                            System.out.println("Placa inválida. Digite novamente:");
                            placa = Util.entrada.nextLine();
                        }
                        //placa nao existe na base, cadastra
                        if(fachada.buscarVeiculo(placa) == null){
                            //demais dados do veículo
                            System.out.println("Digite a cor do veículo:");
                            cor = Util.entrada.nextLine();
                            System.out.println("Digite o ano do veículo:");
                            ano = Util.entrada.nextInt();
                            Util.entrada.nextLine();
                            System.out.println("Digite o modelo:");
                            modelo = Util.entrada.nextLine(); 
                            
                            //intância de veículo
                            System.out.println("Tipo do veículo (1-Econômico, 2-Luxo, 3-SUV, 4-Motocicleta):");
                            tipoVeiculo = Util.entrada.nextInt();
                            Util.entrada.nextLine();
                            
                            while (tipoVeiculo < 1 || tipoVeiculo > 4) {
                                System.out.println("Tipo inválido. Tente novamente:");
                                tipoVeiculo = Util.entrada.nextInt();
                                Util.entrada.nextLine();
                            }
                            try {
                                veiculo = fachada.cadastrarVeiculo(placa, cor, ano, modelo, tipoVeiculo);

                                pessoa = fachada.cadastrarMotorista(veiculo, IDPessoa, idade, local, nome, senha);
                                System.out.println("Motorista cadastrado com sucesso!");
                                return pessoa;
                            } catch (EntidadeJaExisteException e ) {//nao ira cair nesse cath mas por segurança esta ai
                                System.out.println("Erro: " + e.getMessage());
                            }
                        //placa ja existe, volta ao inicio da etapa
                        } else {
                            System.out.println("Já existe um veículo cadastrado com essa placa. Tente novamente");
                        }
                    //cadastro de cliente
                    } else  {
                        cadastrarFormasPagamentoCliente(formas); //por ficar muito grande fiz função separada
                        try {
                            pessoa = fachada.cadastrarCliente(formas, IDPessoa, idade, local, nome, senha);
                        } catch (EntidadeJaExisteException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        System.out.println("Cliente cadastrado com sucesso!");
                    }
                    cadastroFinalizado = true;
                }
            }
        }
        return pessoa;// ENFIM
    }
    
    private static void cadastrarFormasPagamentoCliente(ArrayList<FormaDePagamento> formas) {
        Fachada fachada = Fachada.getInstancia();
        boolean adicionando = true;
    
        while (adicionando) {
            System.out.println("\nComo deseja adicionar uma forma de pagamento?");
            System.out.println("1 - Adicionar cartão");
            System.out.println("2 - Adicionar chave pix");
            System.out.println("3 - Pular (pagar pessoalmente)");
            System.out.println("0 - Finalizar cadastro de formas");
    
            int tipo = Util.entrada.nextInt();
            Util.entrada.nextLine();
    
            if (tipo == 0) {
                adicionando = false;
                continue;
            }
    
            switch (tipo) {
                case 1 -> {
                    System.out.println("Qual o limite do cartão?");
                    double limiteCartao = Util.entrada.nextDouble();
                    Util.entrada.nextLine();
    
                    while (limiteCartao < 0) {
                        System.out.println("Limite inválido. Digite o limite correto:");
                        limiteCartao = Util.entrada.nextDouble();
                        Util.entrada.nextLine();
                    }
    
                    System.out.println("Qual o número do cartão? (somente dígitos)");
                    String numeroCartao = Util.entrada.nextLine();
    
                    while (numeroCartao.length() < 16 || !numeroCartao.matches("\\d+")) {
                        System.out.println("Digite um número válido (16 dígitos, somente números):");
                        numeroCartao = Util.entrada.nextLine();
                    }
    
                    numeroCartao = fachada.formatarCartao(numeroCartao);
    
                    try {
                        fachada.cadastrarCartao(formas, numeroCartao, limiteCartao);
                        System.out.println("Cartão cadastrado com sucesso!");
                    } catch (EntidadeJaExisteException ex) {
                        System.out.println("Erro: " + ex.getMessage());
                    }
                }
                case 2 -> {
                    System.out.println("Digite a chave pix:");
                    String chave = Util.entrada.nextLine();
    
                    while (chave.contains(" ")) {
                        System.out.println("Digite uma chave válida (sem espaços):");
                        chave = Util.entrada.nextLine(); 
                    }
    
                    System.out.println("Digite seu saldo:");
                    double saldoPix = Util.entrada.nextDouble();
                    Util.entrada.nextLine();
    
                    while (saldoPix < 0) {
                        System.out.println("Digite seu saldo corretamente:");
                        saldoPix = Util.entrada.nextDouble();
                        Util.entrada.nextLine();
                    }
    
                    try {
                        fachada.cadastrarPix(formas, chave, saldoPix);
                        System.out.println("Pix cadastrado com sucesso!");
                    } catch (EntidadeJaExisteException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                case 3 -> {
                    System.out.println("Você optou por pagar pessoalmente. Nenhuma forma adicionada.");
                    adicionando = false;
                }
                default -> {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            }
    
            if (adicionando) {
                System.out.println("Deseja adicionar outra forma de pagamento? (s/n)");
                String resposta = Util.entrada.nextLine();
                if (!resposta.equalsIgnoreCase("s")) {
                    adicionando = false;
                }
            }
        }
    
        if (formas.isEmpty()) {
            System.out.println("Forma de pagamento pulada. Você pode adicionar uma forma de pagamento depois.");
        }
    }

    /* static boolean menuLogado(Pessoa pessoa){
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

    } */
    
    

    public static void esperar1200(){
        try {
            Thread.sleep(1650);
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

   
