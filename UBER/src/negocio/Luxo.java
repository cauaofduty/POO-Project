package negocio;
import negocio.modelos.Veiculo;

public class Luxo extends Veiculo {
    private final String nomeVeiculo;
    private final int ano;
    private final double taxaViagem;

    public Luxo(String placa, String cor, String nomeVeiculo, int ano) {
        super(placa, cor);
        this.nomeVeiculo = nomeVeiculo;
        this.taxaViagem = 2.0;//definido pela empresa
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
        return "Luxo {"+ nomeVeiculo + " " + ano + " " + super.toString()  +   "}";
    }
}
