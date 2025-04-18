package ui;

import java.util.ArrayList;
import negocio.localizacao.Local;
import negocio.localizacao.Viagem;
import negocio.pessoas.Cliente;
import negocio.pessoas.Motorista;
import negocio.pessoas.Pessoa;
import negocio.servicos.Fachada;
import negocio.veiculos.Veiculo;
import simulacao.PessoasRandom;
import simulacao.SimuladorViagens;

public class MenuMotorista {

    private static final Fachada fachada = Fachada.getInstancia();
    private static final SimuladorViagens sim = new SimuladorViagens();

    public static boolean menuLogado(Motorista motorista) {

        int opcao;

        while (true) { 
            
            System.out.println("Olá " + motorista.getNome() + "! O que deseja fazer?");
            System.out.println("1- Aceitar corrida");
            System.out.println("2- Ver histórico de viagens");
            System.out.println("3- Trocar veículo");
            System.out.println("4- Voltar ao menu principal");

            // recebe opção e limpa o buffer do scanner
            System.out.print("Opção: ");
            opcao = Util.entrada.nextInt();
            Util.entrada.nextLine();

            switch (opcao) {
                case 1 -> {
                    aceitarCorrida(motorista);
                    break;
                }

                case 2 -> {
                    ArrayList<Viagem> viagens = fachada.listarViagensCliente(motorista.getIDPessoa());
                    if (viagens.isEmpty()) {
                        System.out.println("\nNenhuma viagem encontrada.");
                    } else {
                        System.out.println("Histórico de viagens:");
                        for (Viagem viagem : viagens) {
                            System.out.println(viagem.toString());
                        }
                    }

                    System.out.println("Digite qualquer tecla para voltar ao menu");
                    Util.entrada.nextLine();
                    Util.transicaoCurta();
                    break;
                }

                case 3 -> {
                    System.out.println("\nVeículo atual:");
                    System.out.println(motorista.getVeiculo());

                    System.out.println("\nDeseja trocar de veículo? (1 - Sim | Qualquer tecla - Não)");
                    String opt = Util.entrada.nextLine();

                    if (!opt.equals("1")) {
                        return true;
                    }

                    boolean trocando = true;
                    while (trocando) {
                        MenuCadastro mc = new MenuCadastro();
                        Veiculo novoVeiculo = mc.criarVeiculo();

                        if (novoVeiculo != null) {
                            motorista.setVeiculo(novoVeiculo);
                            fachada.salvarEAtualizarVeiculo(novoVeiculo);
                            System.out.println("\nVeículo alterado com sucesso!\n");
                            trocando = false;
                            Util.transicaoCurta();
                        } else {
                            System.out.println("\nErro ao cadastrar esse veículo. Deseja tentar novamente? (1 - Sim | Qualquer tecla - Não)");
                            String resp = Util.entrada.nextLine();
                            if (!resp.equals("1")) {
                                trocando = false;
                            }
                        }
                    }
                    Util.transicaoCurta();
                    return true;
                }

                case 4 -> {
                    Util.transicaoLonga();
                    return false;
                }

                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void aceitarCorrida(Motorista motorista) {
        System.out.println("Nenhum cliente está online no momento. Simulando clientes:");
        ArrayList<Pessoa> clientesRandom = PessoasRandom.gerarPessoasPara(motorista);
        ArrayList<Local> destinosRandom = new ArrayList<>();
    
        for (int i = 0; i < clientesRandom.size(); i++) {
            Pessoa pessoa = clientesRandom.get(i);
            destinosRandom.add(PessoasRandom.gerarLocal(pessoa));
            Cliente c = (Cliente) pessoa;
            System.out.println(i + " - " + c.getNome() +
                "\n|Origem: " + c.getLocalAtual() +
                "\n|Destino: " + destinosRandom.get(i) + "\n");
        }
    
        System.out.println("Digite o número do cliente que deseja aceitar:");
        int indice = Util.entrada.nextInt();
        Util.entrada.nextLine();
    
        if (indice < 0 || indice >= clientesRandom.size()) {
            System.out.println("Índice inválido. Nenhum cliente aceito.");
            return;
        }
    
        Cliente clienteSelecionado = (Cliente) clientesRandom.get(indice);
        Local destino = destinosRandom.get(indice);
    
        sim.simularViagemMotorista(clienteSelecionado, motorista, clienteSelecionado.getLocalAtual(), destino);
    
        System.out.println("Corrida realizada com sucesso! Qual sua avaliação para o cliente? (1-5)");
        int avaliacao = Util.entrada.nextInt();
        Util.entrada.nextLine();
    
        while (avaliacao < 1 || avaliacao > 5) {
            System.out.println("Avaliação inválida. Tente novamente:");
            avaliacao = Util.entrada.nextInt();
            Util.entrada.nextLine();
        }
    
        clienteSelecionado.setAvaliacoes(avaliacao);
    }
    

}
