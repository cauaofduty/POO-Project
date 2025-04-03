package negocio.veiculos;
import negocio.modelos.Veiculo;

public  class Motocicleta extends Veiculo{
    private final String nomeVeiculo;
    private final double taxaViagem;
    private final int ano;
    
    public Motocicleta(String placa, String cor, String nomeVeiculo, int ano) {
        super(placa, cor);
        this.nomeVeiculo = nomeVeiculo;
        this.taxaViagem = -0.2;//de moto e mais barato
        this.ano = ano;
    }

    public String getNomeVeiculo() {
        return nomeVeiculo;
    }

    public double getTaxaViagem() {
        return taxaViagem;
    }

    public int getAno() {
        return ano;
    }

    @Override//VER NO QUE ESTE OVERRIDE DA
    public String toString() {
        return "Motocicleta {"+ nomeVeiculo + " " + ano + " " + super.toString()  +   "}";
    }
}
