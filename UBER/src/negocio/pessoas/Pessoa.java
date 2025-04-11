package negocio.pessoas;

import java.io.Serializable;
import java.util.ArrayList;
import negocio.localizacao.Local;
import negocio.localizacao.Viagem;

public abstract class Pessoa implements Serializable {
    private static final long serialVersionUID = 100000L;
    protected final String nome;
    protected final String IDPessoa;
    protected final int idade;
    protected Local localAtual;
    protected String senhaAcesso;
    protected String codigoRecuperacao;
    
    
    //construtor completo
    public Pessoa(String IDPessoa, int idade, Local localAtual, String nome) {
        this.IDPessoa = IDPessoa;
        this.idade = idade;
        this.localAtual = localAtual;
        this.nome = nome;
        this.senhaAcesso = null; //vazio para criacao random
    } 


    //construtor para simulacao
    public Pessoa(String IDPessoa, int idade, Local localAtual, String nome, String senhaAcesso, ArrayList<Viagem> viagensHistorico) {
        this(IDPessoa, idade, localAtual, nome);
        this.senhaAcesso = senhaAcesso;    
    }


    public Pessoa(String IDPessoa, int idade, Local localAtual, String nome, String senhaAcesso) {
        this.IDPessoa = IDPessoa;
        this.idade = idade;
        this.localAtual = localAtual;
        this.nome = nome;
        this.senhaAcesso = senhaAcesso;
    }


    public String getNome() {
        return this.nome;
    }
    
    public String getIDPessoa() {
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

    public String getSenhaAcesso() {
        return senhaAcesso;
    }

    public void setSenhaAcesso(String senhaAcesso) {
        this.senhaAcesso = senhaAcesso;
    }

    public String getCodigoRecuperacao() {
        return codigoRecuperacao;
    }

    public void setCodigoRecuperacao(String codigoRecuperacao) {
        this.codigoRecuperacao = codigoRecuperacao;
    }
    
}
