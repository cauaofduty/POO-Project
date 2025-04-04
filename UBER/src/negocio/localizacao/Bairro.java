package negocio.localizacao;

public class Bairro { 
    private final String nome;
    private final Zona zona;
    private final Zona[] zonas = Zona.values();//constante


    public Bairro(String nome,Zona zona) {
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
