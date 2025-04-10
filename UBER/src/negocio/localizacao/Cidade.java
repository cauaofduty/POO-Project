package negocio.localizacao;

import java.util.ArrayList;

public class Cidade {
    private final String nome;
    private final Bairro bairro;//bairro da localizacao atual
    public Bairro getBairro() {
        return bairro;
    }

    private final ArrayList<Bairro> bairros;//bairros da cidade

    public Cidade(String nome, ArrayList<Bairro> bairros, Bairro bairro){
        this.nome = nome;
        this.bairro = bairro;
        this.bairros = bairros;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Bairro> getBairros() {
        return bairros;
    }
}
