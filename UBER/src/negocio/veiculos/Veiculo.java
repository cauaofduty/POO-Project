package negocio.veiculos;

public abstract class Veiculo {
    protected final String placa;
    protected final String cor;
    private final String nomeVeiculo;
    private final int ano;

    //colocar atributo taxa para cada tipo de veiculo no extends!
    public Veiculo(String placa,String cor, int ano, String nomeVeiculo){
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
