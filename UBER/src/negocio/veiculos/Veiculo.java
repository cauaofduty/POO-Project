package negocio.veiculos;

import java.io.Serializable;

public abstract class Veiculo implements Serializable {
    private static final long serialVersionUID = 200000L;
    protected final String placa;
    protected final String cor;
    private final String nomeVeiculo;
    private final int ano;

    //colocar atributo taxa para cada tipo de veiculo no extends!
    public Veiculo(String placa,String cor, String nomeVeiculo, int ano){
        this.placa = placa;
        this.cor = cor;
        this.nomeVeiculo = nomeVeiculo;
        this.ano = ano;
    }
    public String getPlaca() {
        return placa;
    }
    public String getCor() {
        return cor;
    }
    public String getNomeVeiculo() {
        return nomeVeiculo;
    }
    public int getAno() {
        return ano;
    }
}
