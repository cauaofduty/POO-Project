package negocio.pessoas;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import negocio.financeiro.Cartao;
import negocio.financeiro.Dinheiro;
import negocio.financeiro.FormaDePagamento;
import negocio.financeiro.Pix;
import negocio.localizacao.*;
import negocio.veiculos.Economico;
import negocio.veiculos.Luxo;
import negocio.veiculos.Motocicleta;
import negocio.veiculos.SUV;
import negocio.veiculos.Veiculo;

public class GeradorPessoas {//MUITO TRABALHO
    private static final Random r = new Random();
       
    public static int geraInteirosRandom(int limMin, int limMax){//gera inteiros limitados para numero de pessoas
        int valor = r.nextInt(limMax - limMin) + limMin;
        return valor;
    }

    public ArrayList<Pessoa> gerarPessoasPara(Pessoa pessoa){
       
        //comeca criando tamanho para lista de pessoas
        int indice = geraInteirosRandom(5, 11);//intenção de criar entre 5 e 10 pessoas
        
        //cria o array para pessoas e usa um for para preenche-lo com os atributos de pessoa
        ArrayList<Pessoa> pessoasAleatorias = new ArrayList<>();//para armazenar pessoas
        
        //desvio para qual tipo criar
        if(pessoa instanceof Cliente){
            List<Veiculo> veiculos = new ArrayList<>();//para armazenar veiculos (foco em placas)
            for(int i = 0; i < indice; i++){
                String nome = gerarNome(pessoasAleatorias);
                int IDPessoa = gerarID(pessoasAleatorias);
                int idade = gerarIdade();
                Local local = gerarLocal();
                Veiculo veiculo = gerarVeiculo(veiculos);
                veiculos.add(veiculo);
                pessoasAleatorias.add(new Motorista(0, veiculo, IDPessoa, idade, local, nome));
            }
        }else{//ESSE PROJETO E UM FILHO PRA MIM
            for(int i = 0; i < indice; i++){
                String nome = gerarNome(pessoasAleatorias);
                int IDPessoa = gerarID(pessoasAleatorias);
                int idade = gerarIdade();
                Local local = gerarLocal();
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

    private static int gerarID(ArrayList<Pessoa> pessoas){
        int ID = geraInteirosRandom(100000, 999999);//6 digitos
        if(pessoas.stream().anyMatch(p -> p.getIDPessoa() == (ID)))
            gerarID(pessoas);
        return ID;
    }
    
    private static int gerarIdade(){
        int idade = geraInteirosRandom(2000, 3000);//deuses gregos ne pae
        return idade;
    }
 
    private static Local gerarLocal(){
        //setando valores estaticos como "base de dados"
        String[] cidadesRandom = {
            "Garanhuns", "Caruaru", "Belo Jardim", "Lajedo", "Pesqueira",
            "Santa Cruz do Capibaribe", "Toritama", "Surubim", "São Bento do Una", "Brejo da Madre de Deus",
            "Taquaritinga do Norte", "Bonito", "Bezerros", "Gravatá", "Jataúba",
            "Agrestina", "Panelas", "Altinho", "Cupira", "Lagoa dos Gatos"
        };
        String[] bairrosRandom = {
            "Centro", "Cohab I", "Cohab II", "Boa Vista", "São José",
            "Heliópolis", "Magano", "Aluísio Pinto", "Dom Thiago Postma", "Francisco Figueira",
            "Indiano", "Brasília", "Novo Heliópolis", "Várzea", "Liberdade",
            "Manoel Chéu", "Massaranduba", "José Maria Dourado", "Severiano Moraes Filho", "Parque Fênix"
        };
        Zona[] zonas = Zona.values();

        //selecao aleatoria
        int indexCidadeRandom = r.nextInt(cidadesRandom.length);
        int indexBairro = r.nextInt(bairrosRandom.length);
        int indexZona = r.nextInt(zonas.length);
        
        
        //criando atributos
        ArrayList<Bairro> bairros = new ArrayList<>();//arraylist vazio para prevenir problemas
        String nomeCidade = cidadesRandom[indexCidadeRandom];
        String nomeBairro = bairrosRandom[indexBairro];

        //instanciando classes
        Zona zona = zonas[indexZona];
        Bairro bairro = new Bairro(nomeBairro, zona);
        Cidade cidade = new Cidade(nomeCidade, bairros, bairro);
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
                String cVV = String.valueOf(geraInteirosRandom(100, 1000));
                
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
                return new Economico(placa, cor, ano, nome);
            }
            case 2->{
                return new Luxo(placa, cor, ano, nome);
            }
            case 3->{
                return new Motocicleta(placa, cor, ano, nome);
            }
            case 4->{
                return new SUV(placa, cor, ano, nome);
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
}
