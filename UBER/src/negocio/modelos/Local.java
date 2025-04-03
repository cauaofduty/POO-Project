package negocio.modelos;
import negocio.locais.*;
public class Local {
    private Bairro bairro;//nao tem sentido colocar varias strings aqui sendo que o natural e o cliente separar tudo por virgula
    private Zona zona;
    
    public Local(Bairro bairro, Zona zona) {//ver se e necessario haverem setters para a classe
        this.bairro = bairro;
        this.zona = zona;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }
    public int getX(){/* pega as coordenadas do local no "mapa" */
        return this.bairro.ordinal();
    }
    public int getY(){/* pega as coordenadas do local no "mapa" */
        return this.zona.ordinal();
    }
   
    
    

    
    
}
