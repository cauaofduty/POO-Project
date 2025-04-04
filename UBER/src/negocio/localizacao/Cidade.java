package negocio.localizacao;

import java.util.ArrayList;

public class Cidade {
    private final String nome;
    private final ArrayList<Bairro> bairros;

    public Cidade(String nome, ArrayList<Bairro> bairros){
        this.nome = nome;
        this.bairros = bairros;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Bairro> getBairros() {
        return bairros;
    }
}
