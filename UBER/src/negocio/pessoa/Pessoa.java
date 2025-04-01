package negocio.pessoa;
public abstract class Pessoa {
    protected String nome;//apenas nome de usuario
    protected String telefone;
    protected final int IDPessoa;//imutavel apos criacao
    protected String dataNascimento;

        public Pessoa(String nome,String telefone,int IDPessoa, String dataNascimento){//atributos default para pessoa
            this.nome = nome;
            this.telefone = telefone;
            this.IDPessoa = IDPessoa;
            this.dataNascimento = dataNascimento;
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
        public String getDataNascimento(){
            return this.dataNascimento;
        }

        public void setNome(String nome){
            this.nome = nome;
        }
        public void setTelefene(String telefone){
            this.telefone = telefone;
        }
        public void setDataNascimento(String dataNascimento){
            this.dataNascimento = dataNascimento;
        }
}
