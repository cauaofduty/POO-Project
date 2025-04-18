package ui;

import negocio.exceptions.CodigoIncorretoException;
import negocio.exceptions.EntidadeNaoEncontradaException;
import negocio.exceptions.SenhaIncorretaException;
import negocio.localizacao.Viagem;
import negocio.pessoas.Cliente;
import negocio.pessoas.Motorista;
import negocio.pessoas.Pessoa;
import negocio.servicos.Fachada;
import negocio.veiculos.Veiculo;

public class MainMenu {
    private static final Fachada fachada = Fachada.getInstancia();
    public static boolean mainMenu(){
        MainMenu mm = new MainMenu();
        return mm.menu();
    }

    private boolean menu() {

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1- Login");
            System.out.println("2- Registre-se");
            System.out.println("3- Esqueci minha senha");
            System.out.println("4- Caixa de entrada");
            System.out.println("5- Sair");
            System.out.print("Opção: ");
    
            //recebe opcao e limpa buffer
            int opcao = Util.entrada.nextInt();
            Util.entrada.nextLine(); 
            Util.transicaoCurta();
    
            switch (opcao) {
                case 1 -> {
                    //função de login
                    if (login()) return true;
                }
                case 2 -> {
                    //cadastro de pessoa
                    Pessoa novaPessoa = MenuCadastro.menuCadastro();
                    //caso id nao exista na base de dados, será cadastrada
                    if (novaPessoa != null) {
                        System.out.println("Seu ID: <" + novaPessoa.getIDPessoa() + ">");
                        System.out.println("Guarde-o, ele é importante para o login!");

                        System.out.println("Pressione qualquer tecla para continuar");
                        Util.entrada.nextLine();
                        Util.transicaoCurta();

                        abrirMenu(novaPessoa);
                        return true;
                    }
                    //caso não houve cadastro
                    Util.transicaoCurta();
                    return true;
                }
                case 3 -> {
                    System.out.println("Deseja mudar sua senha? (1-Sim, Qualquer tecla-Não)");
                    String resposta = Util.entrada.nextLine();
                    if (resposta.equalsIgnoreCase("1")) {
                        if (!mudarSenha()) return true;    
                    }
                    System.out.println("Retornando ao menu principal...");
                    Util.transicaoCurta();
                    return true;
                }
                case 4 -> {
                    //para codigo de alteração de senha
                    consultarCaixaEntrada();
                    return true;
                }
                case 5 -> {
                    System.out.println("H&C Transportes agradece a sua preferência!");
                    Util.esperar1200();
                    System.out.println("Finalizando...");
                    Util.transicaoLonga();
                    return false;
                }
                default -> {
                    System.out.println("Digite uma opção válida.");
                    Util.transicaoCurta();
                }
            }
        }
    }
    
    private static boolean login() {
    
        System.out.println("Qual seu ID?");
        String IDPessoa = Util.entrada.nextLine();
        
        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<CHECAGEM ESPECIAL PARA ADMIN>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        if(IDPessoa.equals("admin") ){
            System.out.println("Digite sua senha: ");
            String senha = Util.entrada.nextLine();
            while(!senha.equals("admin")){
                System.out.println("Senha incorreta. 1- Tentar novamente | Qualquer tecla- Sair");
                String opt = Util.entrada.nextLine();
                if(!opt.equals("1")){
                    return true;
                }
                //caso 1 recebe senha
                senha = Util.entrada.next();
            }
                menuAdmin();
                Util.limparTela();
                return true; 
            }
            //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       

        Pessoa pessoa; 
        try {
            pessoa = fachada.buscarPessoa(IDPessoa);
        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("\nID não encontrado. Deseja cadastrar-se? (1-Sim, Qualquer tecla-Não)");
            String resposta = Util.entrada.nextLine();
    
            if (resposta.equalsIgnoreCase("1")) {
                Util.transicaoCurta();
                Pessoa novaPessoa = MenuCadastro.menuCadastro();
                if (novaPessoa != null) {
                    abrirMenu(novaPessoa);
                    return true;
                }
                System.out.println("Retornando ao menu...");
                Util.transicaoLonga();
                return true;
            } else{
                System.out.println("Retornando ao menu...");
                Util.transicaoLonga();
                return true;
            }
        }

        System.out.println("Bem-Vindo(a) de volta "+ pessoa.getNome() + ". Digite sua senha: ");
        String senha = Util.entrada.nextLine();
            try {
                //tenta receber senha ou lançar exception
                fachada.receberSenhaLogin(senha, IDPessoa);
                } catch (SenhaIncorretaException e) {
                //captura senha errada e pergunta por redefinição
                System.out.println("Erro: " + e.getMessage());
                String resposta = Util.entrada.nextLine();
                if (resposta.equalsIgnoreCase("1")) {
                    fachada.gerarCodigoRecuperacao(IDPessoa);
                    System.out.println("Código enviado para a caixa de entrada. Retorne ao menu para vê-lo utilizando seu ID.");
                }
                Util.transicaoLonga();
                return true;
            }
            //caso de senha correta, abre menuLogado
            abrirMenu(pessoa);
            return true;//volta ao menu
    }

    private static boolean mudarSenha() {
    
            System.out.println("Digite seu ID:");
            String IDPessoa = Util.entrada.nextLine();
            
            Pessoa pessoa;
            try {
                pessoa = fachada.buscarPessoa(IDPessoa);
            }catch (EntidadeNaoEncontradaException e) {
                System.out.println("Erro: " + e.getMessage());
                Util.transicaoLonga();
                return true;
            }
                if(pessoa.getCodigoRecuperacao() == null){
                    System.out.println("Não há código na sua caixa de entrada. enviando um...");
                    fachada.gerarCodigoRecuperacao(IDPessoa);

                    Util.transicaoLonga();
                    System.out.println("Codigo recebido! Veja ele a seguir: " + pessoa.getCodigoRecuperacao());
                    fachada.salvarEAtualizarPessoa(pessoa);

                    System.out.println("Utilize-o a seguir para redefinir sua senha. Digite qualquer tecla para continuar");
                    Util.entrada.nextLine();

                    Util.limparTela();
                }
                System.out.println("Digite seu código:");
                String codigo = Util.entrada.nextLine();
            try {
                fachada.validarCodigoRecuperacao(codigo, pessoa);
            }catch (CodigoIncorretoException c){
                System.out.println("Erro: " + c.getMessage());
                Util.transicaoCurta();
                return true; // volta para o menu "esqueci senha"
            }
                System.out.println("Digite sua nova senha:");
                String novaSenha = Util.entrada.nextLine();
    
                fachada.mudarSenha(novaSenha, pessoa);
                System.out.println("Senha alterada com sucesso!");
                //atualiza no repositório
                fachada.salvarEAtualizarPessoa(pessoa);
                Util.transicaoCurta();
                return true;
    }

    private static void consultarCaixaEntrada() {
    
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
        Util.transicaoCurta();
    }

    
    
    
    private static void menuAdmin() {
        Util.limparTela();
        
        System.out.println("\n=== Painel Administrativo (Read-Only) ===");
    
        System.out.println("\n--- Clientes ---");
        for (Cliente cliente : fachada.listarClientes()) {
            System.out.println(cliente);
        }
    
        System.out.println("\n--- Motoristas ---");
        for (Motorista motorista : fachada.listarMotoristas()) {
            System.out.println(motorista);
        }
    
        System.out.println("\n--- Veículos ---");
        for (Veiculo veiculo : fachada.listarVeiculos()) {
            System.out.println(veiculo);
        }
    
        System.out.println("\n--- Viagens ---");
        for (Viagem viagem : fachada.listarViagensGeral()) {
            System.out.println(viagem);
        }
    
        System.out.println("\n=============================\n");

        System.out.println("Digite qualquer tecla para sair ");
        Util.entrada.nextLine();
    }

      //faz direcionamento de qual menu abrir
    private static void abrirMenu(Pessoa pessoa){
        switch(pessoa){
            case Cliente cliente ->{
                boolean logado = MenuCliente.menuLogado(cliente);
                while(logado) logado = MenuCliente.menuLogado(cliente);  
            }
            case Motorista motorista -> {
                boolean logado = MenuMotorista.menuLogado(motorista);
                while(logado) logado = MenuMotorista.menuLogado(motorista);
            }
            default->{}
        }
    }

}
