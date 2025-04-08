package ui;

import java.util.ArrayList;

import negocio.financeiro.FormaDePagamento;
import negocio.localizacao.Cidade;
import negocio.localizacao.Local;
import negocio.pessoas.Cliente;
import negocio.pessoas.GeradorPessoas;
import negocio.servicos.Util;

class InterfacePrincipal {
    //instanciar fachada aqui
    private static ArrayList<String> Mensagens = new ArrayList<>();
    public static void main(final String[] args) throws Exception {
        System.out.println("\tH&C TRANSPORTES");
        System.out.println("TE LEVANDO ONDE VOCÊ QUER IR");
        esperar1200();
        System.out.println("Bem vindo ao sistema de transporte H&C! Escolha uma opção");
        menu();
    }

   
    static void menu(){
        while (true) {
            System.out.println("1- Login");
            System.out.println("2- Registre-se");
            System.out.println("3- Caixa de entrada");
            System.out.println("4- Sair");

            int opcao = negocio.servicos.Util.entrada.nextInt(); // Aqui você deve capturar a entrada do usuário e atribuí-la a 'opcao'
            negocio.servicos.Util.entrada.nextLine(); // Limpar o buffer do scanner

            //limpar tela aqui (TESTE)
            limparTela();
            switch (opcao) {
                case 1 -> {
                System.out.println("Qual seu ID?");
                //buscar pessoa com fachada
                System.out.println("Bem vindo de volta " + "" + "! Digite sua senha");
                }
                case 2 -> {
                    System.out.println("Forneça seus dados, mas primeiro, quer realizar cadastro como motorista ou cliente?");
                    //System.out.println("1- Motorista\n2- Cliente");
                    //int tipo = negocio.servicos.Util.entrada.nextInt();
                    //negocio.servicos.Util.entrada.nextLine();// Limpar o buffer do scanner
                    //chama cadastrar cliente/motorista da fachada
                }
                case 3 -> {//caixa de entrada (para receber codigo de recuperacao e ID apos cadastro)
                    
                }
                case 4 -> {//caixa de entrada (para receber codigo de recuperacao e ID apos cadastro)
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    } 
    static void esperar1200(){
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
}
