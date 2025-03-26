package Negocios;
public class Local {
    private String endereco;//nao tem sentido colocar varias strings aqui sendo que o natural e o cliente separar tudo por virgula

    public Local(String endereco){//setter tambem caso va modificar endereco
        this.endereco = endereco;
    }
    public String getEndereco(){
        return this.endereco;
    }
}
