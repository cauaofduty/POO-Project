package ui;

import java.util.ArrayList;
import negocio.exceptions.EntidadeJaExisteException;
import negocio.financeiro.Cartao;
import negocio.financeiro.FormaDePagamento;
import negocio.financeiro.Pix;
import negocio.localizacao.Local;
import negocio.pessoas.Pessoa;
import negocio.servicos.Fachada;
import negocio.veiculos.Veiculo;

public class MenuCadastro {
    // atributos aqui para evitar problemas de escopo
    private final Fachada fachada = Fachada.getInstancia();
    private final ArrayList<FormaDePagamento> formasPagamento = new ArrayList<>();
    private Veiculo veiculo;
    private String nome, senha, cidade, IDPessoa;
    private int idade, tipoCadastro;
    private Pessoa pessoa;
    private Local local;

    public static Pessoa menuCadastro() {
        MenuCadastro cadastro = new MenuCadastro();
        return cadastro.executarCadastro();
    }

    private Pessoa executarCadastro() {
        int etapa = 1;

        while (true) {
            switch (etapa) {
                case 1 -> etapa = solicitarNome();
                case 2 -> etapa = solicitarIdade();
                case 3 -> etapa = solicitarSenha();
                case 4 -> etapa = solicitarCidade();
                case 5 -> etapa = escolherTipoCadastro();
                case 6 -> etapa = gerarIDeLocal();
                case 7 -> etapa = realizarCadastro();
                case Integer.MIN_VALUE -> { return pessoa; }
            }
        }
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<CLASSES SUBORDINADAS>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    private int solicitarNome() {
        System.out.println("Qual seu nome?");
        nome = ui.Util.entrada.nextLine();
        return 2;
    }

    private int solicitarIdade() {
        System.out.println("\nQual sua idade? (18 anos ou mais)");
        try {
            idade = Util.entrada.nextInt();
            Util.entrada.nextLine();
            if (idade < 18) throw new IllegalArgumentException("Digite uma idade válida.");
            return 3;
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
            return 2;
        }
    }

    private int solicitarSenha() {
        System.out.println("\nDigite sua senha:");
        String senha1 = Util.entrada.nextLine();
        System.out.println("Confirme sua senha:");
        String senha2 = Util.entrada.nextLine();

        if (!senha1.equals(senha2)) {
            System.out.println("As senhas não coincidem.");
            return 3;
        } else if (senha1.length() < 8) {
            System.out.println("A senha deve ter pelo menos 8 caracteres.");
            return 3;
        }

        senha = senha1;
        return 4;
    }

    private int solicitarCidade() {
        System.out.println("\nDigite o nome da sua cidade:");
        cidade = Util.entrada.nextLine();
        return 5;
    }

    private int escolherTipoCadastro() {
        System.out.println("\nComo quer se cadastrar?");
        System.out.println("1 - Motorista\n2 - Cliente");
        System.out.print("Opção: ");

        tipoCadastro = Util.entrada.nextInt();
        Util.entrada.nextLine();

        if (tipoCadastro != 1 && tipoCadastro != 2) {
            System.out.println("\nOpção inválida. Tente novamente.");
            return 5;
        }

        return 6;
    }

    private int gerarIDeLocal() {
        local = new Local(cidade);
        IDPessoa = fachada.gerarIDPessoa();
        return 7;
    }

    private int realizarCadastro() {
        if (tipoCadastro == 1) {
            return CadastrarMotorista();
        } else {
            return tentarCadastrarCliente();
        }
    }

    private int CadastrarMotorista() {
        veiculo = criarVeiculo();
        if (veiculo == null) {
            System.out.println("Já existe um veículo cadastrado com essa placa.");
            return etapaDecisao();
        }
        try {
            pessoa = fachada.cadastrarMotorista(veiculo, IDPessoa, idade, local, nome, senha);
        } catch (EntidadeJaExisteException e) {
            System.out.println("Erro ao cadastrar motorista: " + e.getMessage());
        }
        if (pessoa != null) {
            System.out.println("\nMotorista cadastrado com sucesso!");
            Util.transicaoLonga();
            return Integer.MIN_VALUE;
        }
        //caso de erro vai a tela de decisão
        return etapaDecisao();
    }

    private int tentarCadastrarCliente() {
        //cadastra forma de pagamento já recebendo a lista de formas que é atributo da classe
        cadastrarFormasPagamentoCliente();
        try {
            pessoa = fachada.cadastrarCliente(formasPagamento, IDPessoa, idade, local, nome, senha);
        } catch (EntidadeJaExisteException ex) {
            System.out.println("Falha ao cadastrar cliente: " + ex.getMessage());
        }
        if (pessoa != null) {
            System.out.println("Cliente cadastrado com sucesso!");
            Util.transicaoLonga();
            return Integer.MIN_VALUE;
        }
        //caso de erro
        return etapaDecisao();
    }

    
    public void cadastrarFormasPagamentoCliente() {
        boolean adicionando = true;
        while (adicionando) {
            System.out.println("\nQual forma de pagamento deseja adicionar??");
            System.out.println("1 - Adicionar cartão");
            System.out.println("2 - Adicionar chave pix");
            System.out.println("3 - Pular (pagar pessoalmente)");
            System.out.println("0 - Finalizar cadastro");
            System.out.print("Opção: ");
    
            int tipo = Util.entrada.nextInt();
            Util.entrada.nextLine();
    
            if (tipo == 0) {
                adicionando = false;
                continue;
            }
    
            switch (tipo) {
                case 1 -> {
                    System.out.println("\nQual o limite do cartão?");
                    double limiteCartao = Util.entrada.nextDouble();
                    Util.entrada.nextLine();
    
                    while (limiteCartao < 0) {
                        System.out.println("\nLimite inválido. Digite o limite correto:");
                        limiteCartao = Util.entrada.nextDouble();
                        Util.entrada.nextLine();
                    }
    
                    System.out.println("\nQual o número do cartão? (somente dígitos)");
                    String numeroCartao = Util.entrada.nextLine();
    
                    while (numeroCartao.length() < 16 || !numeroCartao.matches("\\d+")) {
                        System.out.println("\nDigite um número válido (16 dígitos, somente números):");
                        numeroCartao = Util.entrada.nextLine();
                    }
    
                    numeroCartao = fachada.formatarCartao(numeroCartao);
                    try {
                        Cartao cartao = fachada.cadastrarCartao(formasPagamento, numeroCartao, limiteCartao);
                        System.out.println("Cartão final "+ cartao.getNumeroCartao().substring(16-4)  +  " cadastrado com sucesso!");
                    } catch (EntidadeJaExisteException ex) {
                        System.out.println("Erro: " + ex.getMessage());
                    }
                   
                }
                case 2 -> {
                    System.out.println("\nDigite a chave pix:");
                    String chave = Util.entrada.nextLine();
    
                    while (chave.contains(" ")) {
                        System.out.println("\nDigite uma chave válida (sem espaços):");
                        chave = Util.entrada.nextLine(); 
                    }
    
                    System.out.println("\nDigite seu saldo:");
                    double saldoPix = Util.entrada.nextDouble();
                    Util.entrada.nextLine();
    
                    while (saldoPix < 0) {
                        System.out.println("\nDigite seu saldo corretamente:");
                        saldoPix = Util.entrada.nextDouble();
                        Util.entrada.nextLine();
                    }

                    try {
                        @SuppressWarnings("unused")//problemas de escopo com try
                        Pix pix = fachada.cadastrarPix(formasPagamento, chave, saldoPix);
                        System.out.println("Pix cadastrado com sucesso!");
                    } catch (EntidadeJaExisteException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                case 3 -> {
                    adicionando = false;
                }
                default -> {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            }
    
            if (adicionando) {
                System.out.println("\nDeseja adicionar outra forma de pagamento? (s/n)");
                String resposta = Util.entrada.nextLine();
                if (!resposta.equalsIgnoreCase("s")) {
                    adicionando = false;
                }
            }
        }
    
        if (formasPagamento.isEmpty()) {
            System.out.println("Forma de pagamento pulada. Você pode adicionar uma forma de pagamento depois.");
        }
    }

    private int etapaDecisao() {
        System.out.println("\nO que deseja fazer?");
        System.out.println("1 - Refazer a etapa anterior");
        System.out.println("2 - Voltar ao início do cadastro");
        System.out.println("3 - Tentar novamente esta etapa");
        System.out.println("4 - Cancelar cadastro");

        String escolha = Util.entrada.nextLine();
        return switch (escolha) {
            case "1" -> 6;
            case "2" -> 1;
            case "3" -> 7;
            case "4" -> {
                System.out.println("Cadastro cancelado.");
                yield Integer.MIN_VALUE;
            }
            default -> {
                System.out.println("Opção inválida. Retornando à etapa 7.");
                yield 7;
            }
        };
    }
    public Veiculo criarVeiculo() {
        System.out.println("\nDigite a placa do veículo:");
        String placa = Util.entrada.nextLine();
        while (placa.length() != 7) {
            System.out.println("\nPlaca inválida. Digite novamente:");
            placa = Util.entrada.nextLine();
        }
    
        // Se a placa já existir, retorna null para o menu tratar
        if (fachada.buscarVeiculo(placa) != null) {
            System.out.println("\nJá existe um veículo cadastrado com essa placa. Tente novamente.");
            return null;
        }
    
        System.out.println("\nDigite a cor do veículo:");
        String cor = Util.entrada.nextLine();
    
        System.out.println("\nDigite o ano do veículo:");
        int ano = Util.entrada.nextInt();
        Util.entrada.nextLine();
    
        System.out.println("\nDigite o modelo:");
        String modelo = Util.entrada.nextLine();
    
        System.out.println("\nTipo do veículo (1-Econômico, 2-Luxo, 3-SUV, 4-Motocicleta):");
        int tipoVeiculo = Util.entrada.nextInt();
        Util.entrada.nextLine();
    
        while (tipoVeiculo < 1 || tipoVeiculo > 4) {
            System.out.println("\nTipo inválido. Tente novamente:");
            tipoVeiculo = Util.entrada.nextInt();
            Util.entrada.nextLine();
        }
        veiculo = null;
        try {
            veiculo = fachada.cadastrarVeiculo(placa, cor, ano, modelo, tipoVeiculo);
        } catch (EntidadeJaExisteException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        fachada.salvarEAtualizarVeiculo(veiculo);
        return veiculo;
    }
}
