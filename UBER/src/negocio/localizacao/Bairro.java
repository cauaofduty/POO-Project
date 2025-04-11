package negocio.localizacao;

import java.io.Serializable;

public class Bairro implements Serializable {
    private static final long serialVersionUID = 400002L;
    private final String nome;
    private final Zona zona;
    private final Zona[] zonas = Zona.values(); //constante


    public Bairro(String nome, Zona zona) {
        this.nome = nome;
        this.zona = zona;
    }


    public String getNome() {
        return nome;
    }

    public Zona[] getZonas() {
        return zonas;
    }

    public Zona getZona() {
        return zona;
    }
}
