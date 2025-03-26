package Negocios;
public abstract class Veiculo {
    protected String placa;
    protected String cor;
    //colocar atributo taxa para cada tipo de veiculo no extends!
    public Veiculo(String placa,String cor,double taxaViagem){
        this.placa = placa;
        this.cor = cor;
    }
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public String getCor() {
        return cor;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }
}
