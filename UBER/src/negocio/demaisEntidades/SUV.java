package negocio.demaisEntidades;

public class SUV extends Veiculo{
    private final String nomeVeiculo;
    private final int ano;
    private final double taxaViagem;

    public SUV(int ano, String nomeVeiculo, String placa, String cor) {
        super(placa, cor);
        this.ano = ano;
        this.nomeVeiculo = nomeVeiculo;
        this.taxaViagem = 1.5;
    }

    public String getNomeVeiculo() {
        return nomeVeiculo;
    }

    public int getAno() {
        return ano;
    }

    public double getTaxaViagem() {
        return taxaViagem;
    }

    @Override//VER NO QUE ESTE OVERRIDE DA
    public String toString() {
        return "SUV {"+ nomeVeiculo + " " + ano + " " + super.toString()  +   "}";
    }
    
}
