package ui;

import java.util.ArrayList;
import negocio.exceptions.CodigoIncorretoException;
import negocio.exceptions.EntidadeJaExisteException;
import negocio.exceptions.EntidadeNaoEncontradaException;
import negocio.exceptions.SenhaIncorretaException;
import negocio.financeiro.FormaDePagamento;
import negocio.localizacao.Local;
import negocio.localizacao.Viagem;
import negocio.pessoas.Cliente;
import negocio.pessoas.Motorista;
import negocio.pessoas.Pessoa;
import negocio.servicos.Fachada;
import negocio.servicos.Util;
import negocio.veiculos.Veiculo;
import simulacao.PessoasRandom;
import simulacao.SimuladorViagens;

class InterfacePrincipal {//destaquei com >>>>> a linha de algo que falta

    public static void main(final String[] args) throws Exception {
        
        //função apenas para legibilidade da UI
        mensagemInicial();
        limparTela(); 
        //main menu chamado nesse loop para permitir navegação
        //com flag para continuar rodando o menu
        boolean continuar = true;
        while (continuar) continuar = menu();
        //FIM DA MAIN!
    }

    static boolean menu() {
        @SuppressWarnings("unused")//implicando com a fachada mas não teria como eu instanciá-la no loop
        Fachada fachada = Fachada.getInstancia();
    
        while (true) {
            System.out.println("Escolha uma opção");
            System.out.println("1- Login");
            System.out.println("2- Registre-se");
            System.out.println("3- Esqueci minha senha");
            System.out.println("4- Caixa de entrada");
            System.out.println("5- Sair");
    
            int opcao = Util.entrada.nextInt();
            Util.entrada.nextLine(); // limpar buffer
            limparTela();
    
            switch (opcao) {
                case 1 -> {
                   //função de login
                    if (login()) return true;
                }
                case 2 -> {
                    //cadastro de pessoa
                    Pessoa novaPessoa = menuCadastro();
                    //caso id nao exista na base de dados, será cadastrada
                    if (novaPessoa != null) {
                        System.out.println("Cadastro realizado! Seu ID: <" + novaPessoa.getIDPessoa() + ">");
                        esperar1200();
                        limparTela();
                        menuLogado(novaPessoa);
                        return true;
                    }
                }
                case 3 -> {
                    //fica nessa subtela até pessoa sair
                    if (!mudarSenha()) return true;
                }
                case 4 -> {
                    //para codigo de alteração de senha
                    consultarCaixaEntrada();
                    return true;
                }
                case 5 -> {
                    System.out.println("H&C Transportes agradece a sua preferência!");
                    esperar1200();
                    System.out.println("Finalizando...");
                    esperar1800();
                    limparTela();
                    return false;
                }
                default -> {
                    System.out.println("Digite uma opção válida.");
                    esperar1800();
                    limparTela();
                }
            }
        }
    }
    private static boolean login() {
        Fachada fachada = Fachada.getInstancia();
    
        System.out.println("Qual seu ID?");
        String IDPessoa = Util.entrada.nextLine();
    
        try {
            Pessoa pessoa = fachada.buscarPessoa(IDPessoa);
            System.out.println("Bem-vindo de volta " + pessoa.getNome() + "! Digite sua senha:");
            String senha = Util.entrada.nextLine();
    
            try {
                fachada.receberSenhaLogin(senha, IDPessoa);
                esperar1200();
                limparTela();
    
                boolean continuar = menuLogado(pessoa);
                while (continuar) {
                    continuar = menuLogado(pessoa);
                }
                return true;
    
            } catch (SenhaIncorretaException e) {
                System.out.println("Senha incorreta. Deseja redefinir sua senha? (1-Sim, Qualquer tecla-Não)");
                String resposta = Util.entrada.nextLine();
    
                if (resposta.equalsIgnoreCase("1")) {
                    fachada.gerarCodigoRecuperacao(IDPessoa);
                    System.out.println("Código enviado para a caixa de entrada.");
                }
                esperar1200();
                limparTela();
            }
    
        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("ID não encontrado. Deseja cadastrar-se? (1-Sim, Qualquer tecla-Não)");
            String resposta = Util.entrada.nextLine();
    
            if (resposta.equalsIgnoreCase("1")) {
                Pessoa novaPessoa = menuCadastro();
                if (novaPessoa != null) {
                    menuLogado(novaPessoa);
                    return true;
                }
            }
            esperar1200();
            limparTela();
        }
    
        return false;
    }

    private static boolean mudarSenha() {
        Fachada fachada = Fachada.getInstancia();
    
        System.out.println("Deseja mudar sua senha? (1-Sim, Qualquer tecla-Não)");
        String resposta = Util.entrada.nextLine();
    
        if (resposta.equalsIgnoreCase("1")) {
            System.out.println("Digite seu ID:");
            String IDPessoa = Util.entrada.nextLine();
            
            Pessoa pessoa;
            try {
                pessoa = fachada.buscarPessoa(IDPessoa);
            }catch (EntidadeNaoEncontradaException e) {
                System.out.println("Erro: " + e.getMessage());
                esperar1800();
                limparTela();
                return true;
            }
                System.out.println("Digite seu código:");
                String codigo = Util.entrada.nextLine();
            try {
                fachada.validarCodigoRecuperacao(codigo, pessoa);
            }catch (CodigoIncorretoException c){
                System.out.println("Erro: " + c.getMessage());
                esperar1200();
                limparTela();
                return true; // volta para o menu "esqueci senha"
            }
                System.out.println("Digite sua nova senha:");
                String novaSenha = Util.entrada.nextLine();
    
                fachada.mudarSenha(novaSenha, pessoa);
                System.out.println("Senha alterada com sucesso!");
                esperar1200();
                limparTela();
              
        } else {
            System.out.println("Retornando ao menu principal...");
            esperar1200();
            limparTela();
        }
    
        return false;
    }

    private static void consultarCaixaEntrada() {
        Fachada fachada = Fachada.getInstancia();
    
        System.out.println("Digite seu ID para acessar a caixa de entrada:");
        String IDPessoa = Util.entrada.nextLine();
    
        try {
            Pessoa pessoa = fachada.buscarPessoa(IDPessoa);
            String codigo = pessoa.getCodigoRecuperacao();
    
            if (codigo == null || codigo.isBlank()) {
                System.out.println("Caixa vazia.");
            } else {
                System.out.println("Código: " + codigo);
                System.out.println("Deseja redefinir sua senha? (1-Sim, Qualquer tecla-Não)");
                String resposta = Util.entrada.nextLine();
                if (resposta.equalsIgnoreCase("1")) {
                    mudarSenha(); // já reaproveita a função
                }
            }
        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("ID não encontrado.");
        }
    
        esperar1200();
        limparTela();
    }
    
    static Pessoa menuCadastro() { // falta limpar tela aqui e colocar delay
        Fachada fachada = Fachada.getInstancia();
        
        //variaveis para receber os dados do cadastro
        String nome = null, senha = null, confirmarSenha, cidade = null, placa = null, cor = null, modelo = null, IDPessoa = null;
        int idade = 0, tipoCadastro = 0, tipoVeiculo, ano;
        Pessoa pessoa = null;
        Local local = null;
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
                    idade = Util.entrada.nextInt();
                    Util.entrada.nextLine();
                    try {
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
                     if (senha.length() < 8) {
                        System.out.println("A senha deve ter pelo menos 8 caracteres.");
                    }else {
                        System.out.println("Confirme sua senha:");
                        confirmarSenha = Util.entrada.nextLine();
                        if (!senha.equals(confirmarSenha)) {
                            System.out.println("As senhas não coincidem.");
                        } else{
                            etapa++;
                        }
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
                    local = new Local(cidade);
                    IDPessoa = fachada.gerarIDPessoa();
                    etapa++;
                }
                case 7 -> {
                    if (tipoCadastro == 1) {
                        Veiculo veiculo = criarVeiculo();
                        if(veiculo != null){
                            try {
                                pessoa = fachada.cadastrarMotorista(veiculo, IDPessoa, idade, local, nome, senha);
                                System.out.println("Motorista cadastrado com sucesso!");
                                return pessoa;
                            } catch (EntidadeJaExisteException e ) {//nao ira cair nesse cath mas por segurança esta ai
                                System.out.println("Erro: " + e.getMessage());
                            }
                        }
                        //caso veiculo null (placa ja existe)  
                        else {
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
    public static Veiculo criarVeiculo() {
        Fachada fachada = Fachada.getInstancia();
        System.out.println("Digite a placa do veículo:");
        String placa = Util.entrada.nextLine();
        while (placa.length() != 7) {
            System.out.println("Placa inválida. Digite novamente:");
            placa = Util.entrada.nextLine();
        }
    
        // Se a placa já existir, retorna null para o menu tratar
        if (fachada.buscarVeiculo(placa) != null) {
            System.out.println("Já existe um veículo cadastrado com essa placa. Tente novamente.");
            return null;
        }
    
        System.out.println("Digite a cor do veículo:");
        String cor = Util.entrada.nextLine();
    
        System.out.println("Digite o ano do veículo:");
        int ano = Util.entrada.nextInt();
        Util.entrada.nextLine();
    
        System.out.println("Digite o modelo:");
        String modelo = Util.entrada.nextLine();
    
        System.out.println("Tipo do veículo (1-Econômico, 2-Luxo, 3-SUV, 4-Motocicleta):");
        int tipoVeiculo = Util.entrada.nextInt();
        Util.entrada.nextLine();
    
        while (tipoVeiculo < 1 || tipoVeiculo > 4) {
            System.out.println("Tipo inválido. Tente novamente:");
            tipoVeiculo = Util.entrada.nextInt();
            Util.entrada.nextLine();
        }
        Veiculo veiculo = null;
        try {
            veiculo = fachada.cadastrarVeiculo(placa, cor, ano, modelo, tipoVeiculo);
        } catch (EntidadeJaExisteException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return veiculo;
    }

     static boolean menuLogado(Pessoa pessoa){
        //mostra uma UI diferente para cada tipo de pessoa
        Fachada fachada = Fachada.getInstancia();
        SimuladorViagens sim = new SimuladorViagens();
        //menu para cliente
        switch (pessoa) {
            case Cliente cliente -> { 
                //mostra UI-Cliente
                int opcao;
                //while para menu cliente
                while(true) {
                    limparTela();
                    System.out.println("Olá " + cliente.getNome() + "! O que deseja fazer?");
                    System.out.println("1-Solicitar viagem\n2-Ver histórico de viagens\n3-Adicionar/remover forma de pagamento\n4-Voltar ao menu principal");
                    opcao = negocio.servicos.Util.entrada.nextInt();
                    // Limpar o buffer do scanner
                    negocio.servicos.Util.entrada.nextLine();
                    switch (opcao) {
                        case 1 -> {//solicitar viagem
                            Local origem, destino;
                            // coleta dados para começar viagem
                            System.out.println("Como somos estagiários na empresa, não pudemos utilizar uma API de mapas, então é preciso que digite manualmente sua localização.");
                            
                            // Entrada da cidade
                            System.out.println("Digite a cidade de origem:");
                            String cidadeOrigem = negocio.servicos.Util.entrada.nextLine().trim();
                            
                            // Entrada do bairro
                            System.out.println("Digite o bairro de origem:");
                            String bairroOrigem = negocio.servicos.Util.entrada.nextLine().trim();
                            
                            // Entrada e validação da zona
                            System.out.println("Digite a zona de origem (CENTRO, NORTE, SUL, LESTE, OESTE):");
                            String zona = negocio.servicos.Util.entrada.nextLine().trim().toUpperCase();
                            
                            try {
                                //valida zona
                                fachada.validarZona(zona); // método que converte string para enum com validação
                                origem = fachada.criarLocal(cidadeOrigem, bairroOrigem, zona); // cria o local
                            }catch (EntidadeNaoEncontradaException e) {
                                System.out.println("Zona inválida. Tente novamente.");
                                continue; // volta para o menu
                            }
                            //pede dados para destino
                            System.out.println("Digite a cidade de destino:");
                            String cidadeDestino = negocio.servicos.Util.entrada.nextLine().trim();
                    
                            System.out.println("Digite o bairro de destino:");
                            String bairroDestino = negocio.servicos.Util.entrada.nextLine().trim();
                    
                            System.out.println("Digite a zona de destino (CENTRO, NORTE, SUL, LESTE, OESTE):");
                            String zonaDestinoStr = negocio.servicos.Util.entrada.nextLine().trim().toUpperCase();

                            try {
                                fachada.validarZona(zonaDestinoStr);
                                destino = fachada.criarLocal(cidadeDestino, bairroDestino, zonaDestinoStr);
                            }catch(EntidadeNaoEncontradaException e) {
                                System.out.println("Zona inválida. Tente novamente.");
                                continue; // volta para o menu
                            }
                            //decide qual tipo de viagem solicitar
                            System.out.println("Deseja solicitar uma viagem ou entrega de mercadoria? (1-Viagem, 2-Entrega)");
                            int tipo = negocio.servicos.Util.entrada.nextInt();
                            negocio.servicos.Util.entrada.nextLine(); // Limpar o buffer do scanner
                            while(tipo != 1 && tipo != 2) {
                                System.out.println("Opção inválida. Tente novamente:");
                                tipo = negocio.servicos.Util.entrada.nextInt();
                                negocio.servicos.Util.entrada.nextLine(); // Limpar o buffer do scanner
                            }

                            //observar esse switch, não consigo prever seu comportamento  E ADICIOANR A ESCOLHA DE MORTORISTA
                            switch(tipo){
                                case 1->{//viagem normal
                                    System.out.println("Motoristas disponíveis: ");
                                    //cria lista de3 motoristas random
                                    ArrayList<Pessoa> motoristasDisponiveis = PessoasRandom.gerarPessoasPara(cliente);

                                    for (int i = 0; i < motoristasDisponiveis.size(); i++) {
                                        Motorista motorista = (Motorista) motoristasDisponiveis.get(i);
                                        System.out.println(i + " - " + motorista.getNome()  + motorista.getVeiculo().toString() + " | Nota: " + motorista. getNota());
                                    }

                                    // user escolhe um motorista:
                                    System.out.println("Escolha o número do motorista desejado:");
                                    int escolha = Util.entrada.nextInt();
                                    Util.entrada.nextLine(); // limpar o buffer

                                    while (escolha < 0 || escolha >= motoristasDisponiveis.size()) {
                                        System.out.println("Digite uma escolha válida.");
                                        escolha = Util.entrada.nextInt();
                                        Util.entrada.nextLine();
                                    }
                                    //enfim
                                    Motorista motoristaEscolhido = (Motorista) motoristasDisponiveis.get(escolha);
                                    
                                    try {
                                        sim.simularViagemCliente(cliente, motoristaEscolhido, origem, destino);
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    System.out.println("Corrida realizada com sucesso! Qual sua avaliação para o motorista? (1-5)");
                                    int avaliacao = Util.entrada.nextInt();
                                    Util.entrada.nextLine();
                                    while (avaliacao < 1 || avaliacao > 5) {
                                        System.out.println("Avaliação inválida. Tente novamente:");
                                        avaliacao = Util.entrada.nextInt();
                                        Util.entrada.nextLine(); // Limpar o buffer do scanner
                                    }
                                    motoristaEscolhido.setAvaliacoes(avaliacao);
                                    negocio.servicos.Util.entrada.nextLine(); // Limpar o buffer do scanner
                                    //se deu certo, break
                                    break;
                                }
                                case 2->{
                                    //viagem entrega
                                    System.out.println("Digite o peso da mercadoria:");
                                    //sem limite ou exception, pois estamos muito proximos da entrega
                                    double peso = negocio.servicos.Util.entrada.nextDouble();
                                    
                                    System.out.println("Motoristas disponíveis: ");
                                    //cria lista de3 motoristas random
                                    ArrayList<Pessoa> motoristasDisponiveis = PessoasRandom.gerarPessoasPara(cliente);

                                    for (int i = 0; i < motoristasDisponiveis.size(); i++) {
                                        Motorista motorista = (Motorista) motoristasDisponiveis.get(i);
                                        System.out.println(i + " - " + motorista.getNome()  + motorista.getVeiculo().toString() + " | Nota: " + motorista. getNota());
                                    }

                                    // user escolhe um motorista:
                                    System.out.println("Escolha o número do motorista desejado:");
                                    int escolha = Util.entrada.nextInt();
                                    Util.entrada.nextLine(); // limpar o buffer

                                    while (escolha < 0 || escolha >= motoristasDisponiveis.size()) {
                                        System.out.println("Digite uma escolha válida.");
                                        escolha = Util.entrada.nextInt();
                                        Util.entrada.nextLine();
                                    }
                                    //enfim
                                    Motorista motoristaEscolhido = (Motorista) motoristasDisponiveis.get(escolha);
                                    
                                    try {
                                        sim.simularViagemEntrega(cliente, motoristaEscolhido, origem, destino, peso);
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    System.out.println("Corrida realizada com sucesso! Qual sua avaliação para o motorista? (1-5)");
                                    int avaliacao = Util.entrada.nextInt();
                                    Util.entrada.nextLine();
                                    while (avaliacao < 1 || avaliacao > 5) {
                                        System.out.println("Avaliação inválida. Tente novamente:");
                                        avaliacao = Util.entrada.nextInt();
                                        Util.entrada.nextLine(); // Limpar o buffer do scanner
                                    }
                                    motoristaEscolhido.setAvaliacoes(avaliacao);
                                    negocio.servicos.Util.entrada.nextLine(); // Limpar o buffer do scanner
                                    //se deu certo, break
                                    break;
                                }
                                default -> System.out.println("Opção inválida. Tente novamente.");
                            }
                            return true;//retorna ao menu logado
                        }
                        case 2 -> {//ver histórico de viagens
                            ArrayList<Viagem> viagens = fachada.listarViagensCliente(cliente.getIDPessoa());
                            if (viagens.isEmpty()) {
                                System.out.println("Nenhuma viagem encontrada.");
                            } else {
                                System.out.println("Histórico de viagens:");
                                for (Viagem viagem : viagens) {
                                    System.out.println(viagem.toString());
                                }
                            }
                            System.out.println("Digite qualquer tela para voltar ao menu");
                            Util.entrada.nextLine();
                            limparTela();
                            return true;//retorna ao menu logado
                        }
                        case 3 -> {//alterar//adicionar forma de pagamento// Cadastrar ou remover formas de pagamento
                            boolean editando = true;
                        
                            while (editando) {
                                System.out.println("1 - Adicionar forma de pagamento");
                                System.out.println("2 - Remover forma de pagamento");
                                System.out.println("3 - Voltar");
                        
                                int opt = Util.entrada.nextInt();
                                Util.entrada.nextLine();
                        
                                switch (opt) {
                                    case 1 -> {
                                        cadastrarFormasPagamentoCliente(cliente.getFormasPagamento());
                                        return true;
                                    }
                                    case 2 -> {
                                        ArrayList<FormaDePagamento> formas = cliente.getFormasPagamento();
                        
                                        if (formas.isEmpty()) {
                                            System.out.println("Você não possui nenhuma forma de pagamento cadastrada.");
                                            break;
                                        }
                        
                                        System.out.println("\nSuas formas de pagamento:");
                                        for (int i = 0; i < formas.size(); i++) {
                                            System.out.println(i +  " - " + formas.get(i));
                                        }
                        
                                        System.out.println("Digite o número da forma de pagamento que deseja remover:");
                                        int indice = Util.entrada.nextInt();
                                        Util.entrada.nextLine();
                        
                                        try {
                                            fachada.removerFormaPagamento(formas, indice);
                                            System.out.println("Forma de pagamento removida com sucesso.");
                                        } catch (IllegalArgumentException e) {
                                            System.out.println("Erro: " + e.getMessage());
                                        }
                                    }
                                    case 3 -> {
                                        editando = false; // Volta para o menu principal
                                    }
                                    default -> {
                                        System.out.println("Opção inválida. Tente novamente.");
                                    }
                                }
                            }
                            return true;//retorna ao menu logado
                        }
                        case 4 -> {
                            //sair
                            limparTela();
                            return false;
                            //volta ao menu principal
                        }
                        default -> System.out.println("Opção inválida. Tente novamente.");
                    }
                    
                    //retorna ao menu logado
                }
            }
            //mostra UI-Motorista
            case Motorista motorista -> {
                System.out.println("Olá " + motorista.getNome() + "! O que deseja fazer?");
                System.out.println("1- Aceitar corrida\n2- Ver histórico de viagens\n3- Trocar veículo\n4- Voltar ao menu principal");
                int opcao = negocio.servicos.Util.entrada.nextInt();
                // Limpar o buffer do scanner
                negocio.servicos.Util.entrada.nextLine();
                switch (opcao) {
                    case 1 -> {//aceitar corrida // Mostrar clientes online e aceitar corrida
                        //fiz apenas no "modo" simulação
                        System.out.println("Nenhum cliente está online no momento. Simulando clientes:");
                        //array com pessoas aleatorias
                        ArrayList<Pessoa> clientesRandom = PessoasRandom.gerarPessoasPara(motorista);
                        //arraylist para gerar locais aleatorios de destino para cada cliente e mostrar no loop
                        ArrayList<Local> destinosRandom = new ArrayList<>(); 
                        for (int i = 0; i < clientesRandom.size(); i++) {
                            //cria um destino aleatorio para cada cliente adicionando a um arraylist para aparecer para o motorista
                            destinosRandom.add(PessoasRandom.gerarLocal(pessoa));
                            Cliente c = (Cliente) clientesRandom.get(i);
                            System.out.println(i + " - " + c.getNome() + " \n|Origem: " + c.getLocalAtual() + "\n|Destino: " + destinosRandom.get(i) + "\n\n");//mostra locais na mesma cidade do motorista
                        }
                        System.out.println("Digite o número do cliente que deseja aceitar:");
                        int indice = Util.entrada.nextInt();
                        Util.entrada.nextLine();

                        if (indice < 0 || indice >= clientesRandom.size()) {
                            System.out.println("Índice inválido. Nenhum cliente aceito.");
                            break;
                        }
                        //seleciona cliente e seu respectivo destino
                        Cliente clienteSelecionado = (Cliente) clientesRandom.get(indice);
                        Local destino = destinosRandom.get(indice);
                        //simula e adiciona nas viagens
                        sim.simularViagemMotorista(clienteSelecionado, motorista, clienteSelecionado.getLocalAtual(), destino);
                        System.out.println("Corrida realizada com sucesso! Qual sua avaliação para o cliente? (1-5)");
                        int avaliacao = Util.entrada.nextInt();
                        Util.entrada.nextLine();
                        while (avaliacao < 1 || avaliacao > 5) {
                            System.out.println("Avaliação inválida. Tente novamente:");
                            avaliacao = Util.entrada.nextInt();
                            Util.entrada.nextLine(); // Limpar o buffer do scanner
                        }
                        clienteSelecionado.setAvaliacoes(avaliacao);
                        break;

                    }
                    case 2 -> {//ver histórico de viagens
                        ArrayList<Viagem> viagens = fachada.listarViagensCliente(motorista.getIDPessoa());
                        if (viagens.isEmpty()) {
                            System.out.println("Nenhuma viagem encontrada.");
                        } else {
                            System.out.println("Histórico de viagens:");
                            for (Viagem viagem : viagens) {
                                System.out.println(viagem.toString());
                            }
                        }
                        System.out.println("Digite qualquer tela para voltar ao menu");
                        Util.entrada.nextLine();
                        limparTela();
                        return true;//retorna ao menu logado
                    }
                    case 3 -> { // trocar veículo
                        System.out.println("\nVeículo atual:");
                        System.out.println(motorista.getVeiculo());
                        
                        System.out.println("Deseja trocar de veículo? (1 - Sim | Qualquer tecla - Não)");
                        String opt = Util.entrada.nextLine();
                        
                        if (!opt.equals("1")) {
                            return true;
                        }
                        
                        boolean trocando = true;
                        while (trocando) {
                            Veiculo novoVeiculo = criarVeiculo();
                            
                            if (novoVeiculo != null) {
                                motorista.setVeiculo(novoVeiculo);
                                System.out.println("Veículo alterado com sucesso!");
                                trocando = false;
                            } else {
                                System.out.println("Erro ao cadastrar esse veículo. Deseja tentar novamente? (1 - Sim | Qualquer tecla - Não)");
                                String resp = Util.entrada.nextLine();
                                if (!resp.equals("1")) {
                                    trocando = false;
                                }
                            }
                        }
                        
                        return true; // retorna ao menu logado
                    }
                    case 4 -> {
                        limparTela();
                        return false;
                        //volta ao menu principal
                    }
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            }
            default -> {
            }
        }
        return true;
    } 

    

    public static void  esperar1800(){
        try {
            Thread.sleep(2050);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void esperar1200(){
        try {
            Thread.sleep(1250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void mensagemInicial(){//apenas para legibilidade da UI
        esperar1200();
        System.out.println("\tH&C TRANSPORTES");
        System.out.println("TE LEVANDO ONDE VOCÊ QUER IR");
        esperar1800();
        limparTela();
        System.out.println("Bem vindo ao sistema de transportes H&C!");
        esperar1800();
    }
} 

   
