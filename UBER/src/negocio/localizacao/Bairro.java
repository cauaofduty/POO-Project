package negocio.localizacao;

public class Bairro { 
    private final Zona zona;

    public Bairro(Zona zona) {
        this.zona = zona;
    }

    public Zona getZona() {
        return zona;
    }
   
}
