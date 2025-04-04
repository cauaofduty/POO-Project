package negocio.locais;
public class Local {
    private String bairro;//criar as classes com arrays
    private String rua;
    
    public Local(String bairro, String rua){
        this.bairro = bairro;
        this.rua = rua;
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
