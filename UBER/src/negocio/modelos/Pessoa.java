package negocio.modelos;
public abstract class Pessoa {
    protected String nome;//apenas nome de usuario
    protected String telefone;
    protected final int IDPessoa;//imutavel apos criacao
    protected String dataNascimento;
    protected Local localAtual;//pulo do gato
    protected String senhaAcesso; 
        public Pessoa(String nome,String telefone,int IDPessoa, String dataNascimento, String senhaAcesso){//atributos default para pessoa
            this.nome = nome;
            this.telefone = telefone;
            this.IDPessoa = IDPessoa;
            this.dataNascimento = dataNascimento;
            this.senhaAcesso = senhaAcesso;
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

        public Local getLocalAtual() {
            return localAtual;
        }
    
        public void setLocalAtual(Local localAtual) {
            this.localAtual = localAtual;
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
