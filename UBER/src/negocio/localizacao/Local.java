package negocio.localizacao;

import java.io.Serializable;

public class Local implements Serializable {
    private static final long serialVersionUID = 400000L;
    private final Cidade cidade;
    private final Bairro bairro;
    private final Zona zona;
    
    //construtor completo
    public Local (Cidade cidade, Bairro bairro, Zona zona) {
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
