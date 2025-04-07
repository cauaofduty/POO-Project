package negocio.localizacao;
public class Local {
    private final Cidade cidade;
    private final Bairro bairro;
    private final Zona zona;
    
    //construtor completo
    public Local(Cidade cidade, Bairro bairro, Zona zona) {
        this.cidade = cidade;
        this.bairro = bairro;
        this.zona = zona;
    }

    //construtor cadastro (so com cidade)
    public Local(Cidade cidade) {
        this(cidade, null, null);
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
