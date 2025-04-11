package negocio.localizacao;

import java.io.Serializable;
import java.util.ArrayList;

public class Cidade implements Serializable {
    private static final long serialVersionUID = 400001L;
    private final String nome;
    private final Bairro bairro;//bairro da localizacao atual
    private final ArrayList<Bairro> bairros;//bairros da cidade
    
    public Cidade(String cidade, ArrayList<Bairro> bairros, Bairro bairro){
        this.nome = cidade;
        this.bairro = bairro;
        this.bairros = bairros;
    }
    public Cidade(String cidade){//construtor para cadastro
        this(cidade, new ArrayList<>(), null);
    }

    public Bairro getBairro() {
            return bairro;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Bairro> getBairros() {
        return bairros;
    }
}
