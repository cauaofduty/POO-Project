package simulacao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import negocio.financeiro.Cartao;
import negocio.financeiro.Dinheiro;
import negocio.financeiro.FormaDePagamento;
import negocio.financeiro.Pix;
import negocio.localizacao.*;
import negocio.pessoas.Cliente;
import negocio.pessoas.Motorista;
import negocio.pessoas.Pessoa;
import negocio.veiculos.Economico;
import negocio.veiculos.Luxo;
import negocio.veiculos.Motocicleta;
import negocio.veiculos.SUV;
import negocio.veiculos.Veiculo;

public class PessoasRandom {
    private static final Random r = new Random();
    private final ArrayList<Pessoa> pessoasAleatorias;//para armazenar pessoas
    

    public static int geraInteirosRandom(int limMin, int limMax){//gera inteiros limitados para numero de pessoas
        int valor = r.nextInt(limMax - limMin) + limMin;
        return valor;
    }

    public PessoasRandom(Pessoa pessoa) {//gera já nop construtor para o gerenciador de locais
        this.pessoasAleatorias = gerarPessoasPara(pessoa);
    }

    public final ArrayList<Pessoa> gerarPessoasPara(Pessoa pessoa){
       
        //comeca criando tamanho para lista de pessoas
        int indice = geraInteirosRandom(5, 11);//intenção de criar entre 5 e 10 pessoas
        
        //desvio para qual tipo criar (Cliente cria morotista, vice-versa)
        if(pessoa instanceof Cliente){
            //precisa de lista de veiculos para checar se a placa ja existe
            List<Veiculo> veiculos = new ArrayList<>();
            //cria motorista com veiculo aleatorio
            for(int i = 0; i < indice; i++){
                String nome = gerarNome(pessoasAleatorias);//recebe a lista que esta sendo 
                String IDPessoa = gerarID(pessoasAleatorias);
                int idade = gerarIdade();
                Local local = gerarLocal(pessoa);
                Veiculo veiculo = gerarVeiculo(veiculos);
                veiculos.add(veiculo);
                pessoasAleatorias.add(new Motorista(0, veiculo, IDPessoa, idade, local, nome));
            }
        }else{//ESSE PROJETO E UM FILHO PRA MIM
            for(int i = 0; i < indice; i++){
                String nome = gerarNome(pessoasAleatorias);
                String IDPessoa = gerarID(pessoasAleatorias);
                int idade = gerarIdade();
                Local local = gerarLocal(pessoa);
                FormaDePagamento formaDePagamento = gerarFormaPagamento();
                ArrayList<FormaDePagamento> formas = new ArrayList<>();
                formas.add(formaDePagamento); 
                pessoasAleatorias.add(new Cliente(formas, IDPessoa, idade, local, nome));
            }
        }
        return pessoasAleatorias;
        }
       

    private static String gerarNome(ArrayList<Pessoa> pessoas){
        String[] nome = {
        "Hermes Trimegisto",
        "Apollo Sunshine",
        "Athena Sapiency",
        "Hefestus Magnus",
        "Dionisius StillHigh",
        "Poseidon McHorse",
        "Zeus Thundercloud",
        "Hera Vyperfang",
        "Ares Axel",
        "Afroditt Scarlett",
        "Hades Hellmans",
        "Artemis Greenbow"
        };
        String pessoa = nome[r.nextInt(nome.length)];
        if(pessoas.stream().anyMatch(p -> p.getNome().equalsIgnoreCase(pessoa)))//se a lista de nomes gerados contem o atual 
            return gerarNome(pessoas);//gera outro recursivamente
        return pessoa;//do contrario, retorna-o
    }

    public static String gerarID(ArrayList<Pessoa> pessoas){//utilizado pelo gerenciador para gerar ids
        int ID = r.nextInt(1000000);//6 digitos
        String IDFormatado = String.format("%06d", ID);
        if(pessoas.stream().anyMatch(p -> p.getIDPessoa().equals(IDFormatado)))//gera outro id se este ja existe
            gerarID(pessoas);
        return IDFormatado;
    }
    
    private static int gerarIdade(){
        int idade = geraInteirosRandom(2000, 3000);//deuses gregos ne pae
        return idade;
    }
 
    private static Local gerarLocal(Pessoa pessoa){
        //setando valores estaticos como "base de dados"
        //pega nome da cidade da pessoa online
        String cidadePessoaAtual = pessoa.getLocalAtual().getCidade().getNome();
        String[] bairrosRandom = {
            "Centro", "Cohab I", "Cohab II", "Boa Vista", "São José",
            "Heliópolis", "Magano", "Aluísio Pinto", "Dom Thiago Postma", "Francisco Figueira",
            "Indiano", "Brasília", "Novo Heliópolis", "Várzea", "Liberdade",
            "Manoel Chéu", "Massaranduba", "José Maria Dourado", "Severiano Moraes Filho", "Parque Fênix"};
        Zona[] zonas = Zona.values();

        //selecao aleatoria (entre bairros e zonas)
        int indexBairro = r.nextInt(bairrosRandom.length);
        int indexZona = r.nextInt(zonas.length);
       
        //criando atributos
        ArrayList<Bairro> bairros = new ArrayList<>();
        String nomeBairro = bairrosRandom[indexBairro];

        //instanciando classes
        Zona zona = zonas[indexZona];
        Bairro bairro = new Bairro(nomeBairro, zona);
        Cidade cidade = new Cidade(cidadePessoaAtual, bairros, bairro);
        Local local = new Local(cidade, bairro, zona);
        return local;
    }

    private static FormaDePagamento gerarFormaPagamento(){//fazer checagem de repeticao no final
        //indice aleatorio para switch
        int formaPagamento = r.nextInt(3) + 1;
        switch(formaPagamento){
            case 1 -> {
                // gera cartao com limite mais que suficiente
                
                //limite aleatorio
                double limite = r.nextDouble(250000.000 - 2000.0) + 2000.0;
                
                //cria array com numeros de 4 digitos (sem zeros a esquerda) e transforma em string
                int[] numeros = r.ints(4, 1000, 10000).toArray();
                String numeroCartao = Arrays.toString(numeros).replaceAll(", ", "").replaceAll("[\\[\\]]", "").trim();
            
                //cria e transforma em string digito de seguranca
                String cVV = String.valueOf(geraInteirosRandom(100, 1000));//nao vou mudar para format nao
                
                //instancia e retorna cartao
                return new Cartao(limite, numeroCartao, cVV);
            }

            case 2 -> {
                //dinheiro
                
                //cria saldo suficiente aleatorio
                double saldo = r.nextDouble(250000.0 - 2000.0) + 2000.0;
                
                //instancia e retorna dinheiro
                return new Dinheiro(saldo);
            }

            case 3 -> {//pix
                //saldo aleatorio
                double saldoPix = r.nextDouble(200000.0 - 2000.0) + 2000.0;

                //cria array com 3 inteiros de 3 caracteres e concatena com outro inteiro e 2 caracteres (chave CPF)
                int[] chaveRandom = r.ints(3, 100, 999).toArray();
                String chave = Arrays.toString(chaveRandom).replaceAll(", ", "").replaceAll("[\\[\\]]", "").trim();
                chave = chave.concat(String.valueOf(geraInteirosRandom(10, 100)));
                
                //instancia e retorna pix
                return new Pix(chave, saldoPix);

            }
            default -> throw new IllegalStateException("Forma de pagamento inválida: " + formaPagamento); //nao chegara a retornar este erro

        }
    }

    private static Veiculo gerarVeiculo(List<Veiculo> veiculos){
        //cria placa
        String placa = gerarPlacaAleatoria(veiculos);
       
        //cores e indice aleatorio
        String[] cores = {"Preto","Branco","Cinza","Prata","Vermelho","Verde","Roxo"};
        int indicePlaca = r.nextInt(7);
        String cor = cores[indicePlaca];
        
        //nomes (independente do carro ser do tipo que a classe indica) e indice aleatorio
        String[] nomesDeCarros = {"Volkswagen Gol","Fiat Uno","Chevrolet Celta","Hyundai HB20",
                                "Jeep Compass","Jeep Renegade","Ford Mustang","Chevrolet Camaro","Dodge Challenger",
                                "Chevrolet Corvette","Lamborghini Aventador","Lamborghini Huracan","Audi R8" };
        int indiceCarro = r.nextInt(13);
        String nome = nomesDeCarros[indiceCarro];

        //gera ano
        int ano = geraInteirosRandom(2000, 2026);
        
        //indice aleatorio para switch e instanciar e retornar cada tipo aleatoriamente
        int veiculoTipo = r.nextInt(4) + 1;
        switch (veiculoTipo) {
            case 1->{
                return new Economico(placa, cor, nome, ano);
            }
            case 2->{
                return new Luxo(placa, cor, nome, ano);
            }
            case 3->{
                return new Motocicleta(placa, cor, nome, ano);
            }
            case 4->{
                return new SUV(placa, cor, nome, ano);
            }
            
                default-> throw new ExceptionInInitializerError("erro desconhecido");// perguntar sobre estas exceptions
        }
    }
    private static String gerarPlacaAleatoria(List<Veiculo> veiculos) {
        String letras = "";
        for (int i = 0; i < 3; i++) {
            letras += (char) (r.nextInt(26) + 'A');
        }
    
        //um numero random
        int numero1 = r.nextInt(10);
    
        // uma letra
        char letraMeio = (char) (r.nextInt(26) + 'A');
    
        // gera mais dois algarismos
        int numero2 = r.nextInt(100); // de 0 a 99
    
        // formata numeros finais com dois digitos
        String numero2Formatado = String.format("%02d", numero2);//preguica de aplicar isso agora para o resto!
        
        String placa = letras + numero1 + letraMeio + numero2Formatado;
        if(veiculos.stream().anyMatch(v -> v.getPlaca().equalsIgnoreCase(placa))) //caso placa ja exista
            return gerarPlacaAleatoria(veiculos);
        return placa;
    }

    public ArrayList<Pessoa> getPessoasAleatorias() {
        return pessoasAleatorias;
    }
}
