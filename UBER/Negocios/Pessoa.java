package Negocios;
public abstract class Pessoa {
    protected String nome;
    protected String telefone;
    protected int IDPessoa;

        public Pessoa(String nome,String telefone,int IDPessoa){//atributos default para pessoa
            this.nome = nome;
            this.telefone = telefone;
            this.IDPessoa = IDPessoa;
        }
        //getters default abaixo
        public String getNome(){
            return this.nome;
        }

        public String getTelefone(){
            return this.telefone;
        }
        
        public int getIDPessoa(){
            return this.IDPessoa;
        }
}
