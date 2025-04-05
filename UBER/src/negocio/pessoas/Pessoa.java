package negocio.pessoas;

import java.util.ArrayList;
import negocio.localizacao.Local;
import negocio.localizacao.Viagem;

public abstract class Pessoa {

    protected final String nome;//apenas nome de usuario
    protected final int IDPessoa;//imutavel apos criacao
    protected final int idade;
    protected Local localAtual;
    protected String senhaAcesso;
    protected ArrayList<Viagem> viagensHistorico;

    //construtor para simulacao
    public Pessoa(int IDPessoa, int idade, Local localAtual, String nome) {
        this.IDPessoa = IDPessoa;
        this.idade = idade;
        this.localAtual = localAtual;
        this.nome = nome;
        this.senhaAcesso = null;//vazio para criacao random
        this.viagensHistorico = new ArrayList<>();//vazio para simplificar criacao random
    }
    //construtor completo
    public Pessoa(int IDPessoa, int idade, Local localAtual, String nome, String senhaAcesso, ArrayList<Viagem> viagensHistorico){
        this(IDPessoa, idade, localAtual, nome);
        this.viagensHistorico = viagensHistorico;
        this.senhaAcesso = senhaAcesso;    
    }

    //getters default abaixo
    public String getNome(){
        return this.nome;
    }

    
    public int getIDPessoa(){
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
