package negocio.localizacao;

import java.io.Serializable;

public class Local implements Serializable {
    private static final long serialVersionUID = 300003L;
    private final String cidade;
    private final String bairro;
    private final Zona zona;
    

    //construtor completo
    public Local (String cidade, String bairro, Zona zona) {
        this.cidade = cidade;
        this.bairro = bairro;
        this.zona = zona;
    }


    //construtor cadastro (so com cidade)
    public Local(String cidade) {
        this(cidade, null, null);
    }
    
    
    public String getCidade() {
        return cidade;
    }
    public String getBairro() {
        return bairro;
    }

    public Zona getZona() {
        return zona;
    }
}

