package ui;

class InterfacePrincipal {

    public static void main(final String[] args) throws Exception {
        
        //função apenas para legibilidade da UI
        mensagemInicial();
        //main menu chamado nesse loop para permitir navegação
        //com flag para continuar rodando o menu
        boolean continuar = true;
        while (continuar) continuar = MainMenu.mainMenu();
        //FIM DA MAIN!
    }

    private static void mensagemInicial(){//apenas para legibilidade da UI
        Util.transicaoCurta();
        System.out.println("\t\tH&C TRANSPORTES\n\tTE LEVANDO AONDE VOCÊ QUER IR");
        Util.transicaoCurta();
        System.out.println("\n\tBem vindo ao sistema de transportes H&C!");
        Util.transicaoLonga();
    }
} 

   
