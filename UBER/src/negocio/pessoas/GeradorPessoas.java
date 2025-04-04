package negocio.pessoas;
import negocio.localizacao.*;
import java.util.ArrayList;
import java.util.Random;

public class GeradorPessoas {
    private static Random r = new Random();
       
    public static int geraInteirosRandom(int limMin, int limMax){//gera inteiros limitados para numero de pessoas
        int valor = r.nextInt(limMax - limMin) + limMin;
        return valor;
    }

    /* public ArrayList<Pessoa> gerarPessoasPara(Pessoa pessoa){
        int valor = geraInteirosRandom(5, 11);//intenção de criar entre 5 e 10 pessoas
        ArrayList<Pessoa> pessoasAleatorias = new ArrayList<>();//para armazenar pessoas
        return pessoas;

    } */

    /* private static Pessoa gerarPessoaPara(Pessoa pessoa){
        if(pessoa instanceof Cliente){//se for cliente gerará motoristas em locais aleatorios
            //Motorista motorista = new Motorista(); 
        } else {

            //Cliente cliente = new Cliente(, null, 0, 0, null, null)
            //cria pessoas aleatórias no mapa
        }
        return pessoa;
    } */

   /*  private static String gerarNome(ArrayList<Pessoa> pessoas){
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
        int idade = r.nextInt(3000);//deuses gregos ne pae
        return idade;
    } */
 





    //CONTINUAR AQUI
    private static Local gerarLocal(ArrayList<Pessoa> pessoas){
        String[] cidades = {
            "Garanhuns", "Caruaru", "Belo Jardim", "Lajedo", "Pesqueira",
            "Santa Cruz do Capibaribe", "Toritama", "Surubim", "São Bento do Una", "Brejo da Madre de Deus",
            "Taquaritinga do Norte", "Bonito", "Bezerros", "Gravatá", "Jataúba",
            "Agrestina", "Panelas", "Altinho", "Cupira", "Lagoa dos Gatos"
        };
        String[] bairros = {
            "Centro", "Cohab I", "Cohab II", "Boa Vista", "São José",
            "Heliópolis", "Magano", "Aluísio Pinto", "Dom Thiago Postma", "Francisco Figueira",
            "Indiano", "Brasília", "Novo Heliópolis", "Várzea", "Liberdade",
            "Manoel Chéu", "Massaranduba", "José Maria Dourado", "Severiano Moraes Filho", "Parque Fênix"
        };
        Zona[] zonas = Zona.values();
        
        int indexCidadeRandom = r.nextInt(cidades.length);
        Cidade cidade = new Cidade(cidades[indexCidadeRandom], null);
        
        int indexBairro = r.nextInt(bairros.length);
        int 
        
        Local local = new Local();
    }

}
