package negocio.pessoas;


import java.io.Serializable;
import java.util.ArrayList;
import negocio.localizacao.Local;
import negocio.localizacao.Viagem;

public abstract class Pessoa implements Serializable {
    private static final long serialVersionUID = 100000L;
    protected final String nome;//apenas nome de usuario
    protected final String IDPessoa;//imutavel apos criacao
    protected final int idade;
    protected Local localAtual;
    protected String senhaAcesso;
    protected ArrayList<Viagem> viagensHistorico;
    
    
    //construtor completo
    public Pessoa(String IDPessoa, int idade, Local localAtual, String nome) {
        this.IDPessoa = IDPessoa;
        this.idade = idade;
        this.localAtual = localAtual;
        this.nome = nome;
        this.senhaAcesso = null;//vazio para criacao random
        this.viagensHistorico = new ArrayList<>();//vazio para simplificar criacao random
    } 

    //construtor para simulacao
    public Pessoa(String IDPessoa, int idade, Local localAtual, String nome, String senhaAcesso, ArrayList<Viagem> viagensHistorico){
        this(IDPessoa, idade, localAtual, nome);
        this.viagensHistorico = viagensHistorico;
        this.senhaAcesso = senhaAcesso;    
    }

    public Pessoa(String IDPessoa, int idade, Local localAtual, String nome, String senhaAcesso) {
        this.IDPessoa = IDPessoa;
        this.idade = idade;
        this.localAtual = localAtual;
        this.nome = nome;
        this.senhaAcesso = senhaAcesso;
    }

    
    

    //getters default abaixo
    public String getNome(){
        return this.nome;
    }

    
    public String getIDPessoa(){
        return this.IDPessoa;
    }


    public Local getLocalAtual() {
        return localAtual;
    }

    public void setLocalAtual(Local localAtual) {
        this.localAtual = localAtual;
    }

    public int getIdade() {
        return idade;
    }

    public ArrayList<Viagem> getViagensHistorico() {
        return viagensHistorico;
        }


    public String getSenhaAcesso() {
        return senhaAcesso;
    }
}
