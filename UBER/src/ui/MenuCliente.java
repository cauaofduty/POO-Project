package ui;


import java.util.ArrayList;

import negocio.exceptions.EntidadeNaoEncontradaException;
import negocio.financeiro.FormaDePagamento;
import negocio.localizacao.CalculadorPreco;
import negocio.localizacao.Local;
import negocio.localizacao.Viagem;
import negocio.servicos.Fachada;
import negocio.pessoas.Cliente;
import negocio.pessoas.Motorista;
import negocio.pessoas.Pessoa;
import simulacao.PessoasRandom;
import simulacao.SimuladorViagens;

public class MenuCliente {
    private static final Fachada fachada = Fachada.getInstancia();
    private static final SimuladorViagens sim = new SimuladorViagens();

    public static boolean menuLogado(Cliente cliente) {
        

        int opcao;

        while (true) {
            System.out.println("Olá " + cliente.getNome() + "! O que deseja fazer?");
            System.out.println("1 - Solicitar viagem");
            System.out.println("2 - Ver histórico de viagens");
            System.out.println("3 - Adicionar/remover forma de pagamento");
            System.out.println("4 - Voltar ao menu principal");
            System.out.print("Opção: ");
            
            //opcao e buffer
            opcao = Util.entrada.nextInt();
            Util.entrada.nextLine();
            Util.limparTela();

            switch (opcao) {
                case 1 -> {
                    solicitarViagem(cliente);
                    Util.transicaoCurta();
                    break;
                }
                case 2 -> {
                    ArrayList<Viagem> viagens = fachada.listarViagensCliente(cliente.getIDPessoa());
                    if (viagens.isEmpty()) {
                        System.out.println("\nNenhuma viagem encontrada.");
                    } else {
                        System.out.println("Histórico de viagens:");
                        for (Viagem viagem : viagens) {
                            System.out.println(viagem);
                        }
                    }
                    System.out.println("Digite qualquer tecla para voltar ao menu");
                    Util.entrada.nextLine();
                    Util.transicaoCurta();
                    break;
                }
                case 3 -> {
                    //metodo auxiliar da classe
                    gerenciarFormasPagamento(cliente);
                    break;
                }
                case 4 -> {
                    System.out.println("Retornando ao menu principal...");
                    Util.transicaoLonga();
                    return false;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void solicitarViagem(Cliente cliente) {
        System.out.println("Qual tipo de viagem deseja solicitar?");
        System.out.println("1 - Viagem com passageiro");
        System.out.println("2 - Viagem para entrega");
        System.out.print("Opção: ");

        int opcao = Util.entrada.nextInt();
        Util.entrada.nextLine();
        
        while(true){
            switch (opcao) {
                case 1 ->{
                    solicitarViagemComum(cliente);
                    return;
                }
                case 2 -> {
                    solicitarViagemEntrega(cliente);
                    return;
                } 
                default -> {
                    System.out.println("Digite uma opção válida.");
                    opcao = Util.entrada.nextInt();
                    Util.entrada.nextLine();
                }
            }
        }
    }

    private static void solicitarViagemComum(Cliente cliente) {
        Local origem = escolherLocal("origem");
        Local destino = escolherLocal("destino");

        if (origem == null || destino == null) return;

        //lista motoristas random
        System.out.println("\nMotoristas disponíveis:");
        ArrayList<Pessoa> motoristasDisponiveis = PessoasRandom.gerarPessoasPara(cliente);

        for (int i = 0; i < motoristasDisponiveis.size(); i++) {
            Motorista motorista = (Motorista) motoristasDisponiveis.get(i);
            System.out.println(i + " - " + motorista.getNome()  + motorista.getVeiculo().toString() + " | Nota: " + motorista. getNota());
        }

        // user escolhe um motorista:
        System.out.println("\nEscolha o número do motorista escolhido:");
        int escolha = Util.entrada.nextInt();
        Util.entrada.nextLine(); // limpar o buffer

        while (escolha < 0 || escolha >= motoristasDisponiveis.size()) {
            System.out.println("\nDigite uma escolha válida.");
            escolha = Util.entrada.nextInt();
            Util.entrada.nextLine();
        }
        //seleciona motorista e calcula preço da viagem
        Motorista motoristaEscolhido = (Motorista) motoristasDisponiveis.get(escolha);
        double preco = CalculadorPreco.calcularPrecoViagem(origem, destino, motoristaEscolhido.getVeiculo());
                                    
        try {
            Viagem viagem = sim.simularViagemCliente(cliente, motoristaEscolhido, origem, destino, preco);
            fachada.adicionarViagemCliente(origem, destino, cliente, motoristaEscolhido, viagem.getPreco(), true);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\nCorrida realizada com sucesso! Qual sua avaliação para o motorista? (1-5)");
        int avaliacao = Util.entrada.nextInt();
        Util.entrada.nextLine();
        while (avaliacao < 1 || avaliacao > 5) {
            System.out.println("\nAvaliação inválida. Tente novamente:");
            avaliacao = Util.entrada.nextInt();
            Util.entrada.nextLine(); // Limpar o buffer do scanner
        }
        motoristaEscolhido.setAvaliacoes(avaliacao);
    }

    private static void solicitarViagemEntrega(Cliente cliente) {
        Local origem = escolherLocal("origem");
        Local destino = escolherLocal("destino");

        if (origem == null || destino == null) return;

        System.out.println("Digite o peso do pacote:");
        //sem limite ou exception, pois estamos muito proximos da entrega
        double peso = ui.Util.entrada.nextDouble();
            
        System.out.println("Motoristas disponíveis: ");
        //cria lista de motoristas random
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
            double preco = CalculadorPreco.calcularPrecoEntrega(origem, destino, motoristaEscolhido.getVeiculo(), peso);
            
            try {
                Viagem viagem = sim.simularViagemEntrega(cliente, motoristaEscolhido, origem, destino, peso, preco);
                fachada.adicionarViagemEntrega(origem, destino, cliente, motoristaEscolhido, viagem.getPreco() , peso, true); //usado getPreco para evitar warning de variavel em desuso
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
    }

    private static Local escolherLocal(String tipo) {
        // Entrada da cidade
        System.out.print("Digite o nome da cidade de " + tipo + ": ");
        String cidade = Util.entrada.nextLine();

        //entrada do bairro
         System.out.println("\nDigite o bairro de " + tipo + ": ");
        String bairro = Util.entrada.nextLine().trim();
                            
        // Entrada e validação da zona
        System.out.println("\nDigite a zona de " + tipo + "(CENTRO, NORTE, SUL, LESTE, OESTE): ");
        String zona = Util.entrada.nextLine().trim().toUpperCase();
        while (true) {
            try {
                //valida zona
                fachada.validarZona(zona); // método que converte string para enum com validação
                break;
            }catch (EntidadeNaoEncontradaException e) {
                System.out.println("\nZona inválida. Tente novamente.");
                zona = Util.entrada.nextLine().trim().toUpperCase();
            }   
        }
        
        Local local;
        
        try {
            local = fachada.criarLocal(cidade, bairro, zona);
        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Erro ao criar local. Tente novamente.");
            
            return escolherLocal(tipo);
        } // cria o local
        return local;
    }

    private static void gerenciarFormasPagamento(Cliente cliente) {
        MenuCadastro mc = new MenuCadastro();
        boolean editando = true;

        while (editando) {
            System.out.println("\n1 - Adicionar forma de pagamento");
            System.out.println("2 - Remover forma de pagamento");
            System.out.println("3 - Voltar");
            System.out.print("Opção: ");

            int opt = Util.entrada.nextInt();
            Util.entrada.nextLine();

            switch (opt) {
                case 1 -> {
                    mc.cadastrarFormasPagamentoCliente();
                    
                }
                case 2 -> {
                    ArrayList<FormaDePagamento> formas = cliente.getFormasPagamento();

                    if (formas.isEmpty()) {
                        System.out.println("Você não possui nenhuma forma de pagamento cadastrada.");
                        break;
                    }

                    System.out.println("\nSuas formas de pagamento:");
                    for (int i = 0; i < formas.size(); i++) {
                        System.out.println(i + " - " + formas.get(i));
                    }

                    System.out.println("\nDigite o número da forma de pagamento que deseja remover:");
                    int indice = Util.entrada.nextInt();
                    Util.entrada.nextLine();

                    try {
                        fachada.removerFormaPagamento(formas, indice);
                        System.out.println("Forma de pagamento removida com sucesso.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                case 3 -> editando = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }

        fachada.salvarEAtualizarPessoa(cliente);
    }

}
