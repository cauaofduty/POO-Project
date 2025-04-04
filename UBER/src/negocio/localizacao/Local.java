package negocio.localizacao;
public class Local {
    private final Cidade cidade;
    private final Bairro bairro;//criar as classes com arrays
    private final Zona zona;
    
    public Local(Cidade cidade, Bairro bairro, Zona zona) {
        this.cidade = cidade;
        this.bairro = bairro;
        this.zona = zona;
    }
    public Cidade getCidade() {
        return cidade;
    }
    public Bairro getBairro() {
        return bairro;
    }
    public Zona getZona() {
        return zona;
    }
    
   
   
    
    

    
    
}
