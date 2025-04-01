package negocio.demaisEntidades;
public abstract class Veiculo {
    protected final String placa;
    protected final String cor;
    
    //colocar atributo taxa para cada tipo de veiculo no extends!
    public Veiculo(String placa,String cor){
        this.placa = placa;
        this.cor = cor;
    }
    public String getPlaca() {
        return placa;
    }
    public String getCor() {
        return cor;
    }
}
