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
    protected ArrayList<Integer> avaliacoes;//quantidade de avaliacoes que a pessoa recebeu
    protected double nota;// media de avaliacoes que a pessoa recebeu
    
    
    //construtor completo
    public Pessoa(String IDPessoa, int idade, Local localAtual, String nome) {
        this.IDPessoa = IDPessoa;
        this.idade = idade;
        this.localAtual = localAtual;
        this.nome = nome;
        this.senhaAcesso = null;//vazio para criacao random
        this.nota = 0;
        this. avaliacoes = new ArrayList<>();//inicializa com 5 estrelas
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

    public ArrayList<Integer> getAvaliacoes() {
        return avaliacoes;
    }

    //adiciona avaliacao e atualiza a nota
    public void setAvaliacoes(int avaliacao) {
        this.avaliacoes.add(avaliacao);
        setNota();
    }

    public double getNota() {
        return nota;
    }
    //faz a media das avaliacoes
    public void setNota() {
        int soma = 0;
        for (int i = 0; i < avaliacoes.size(); i++) {
            soma += avaliacoes.get(i);
        }
        this.nota = (double) soma / avaliacoes.size();
    }

    @Override
    public String toString() {
        return "Nome: " + nome +
            " | ID: " + IDPessoa +
            " | Idade: " + idade +
            " | Nota média: " + String.format("%.2f", nota);
    }


}
