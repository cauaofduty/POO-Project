package negocio.modelos;
public class Local {
    private String cidade;//nao tem sentido colocar varias strings aqui sendo que o natural e o cliente separar tudo por virgula
    private String bairro;
    private String rua;
   
    public Local(String cidade, String bairro, String rua) {
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
    }
    
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }
    
    
}
