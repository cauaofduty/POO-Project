package negocio.veiculos;
import negocio.modelos.Veiculo;

public class Economico extends Veiculo{
    private final String nomeVeiculo;
    private final double taxaViagem;
    private final int ano;
    
    public Economico(String placa, String cor,String nomeVeiculo, int ano) {
        super(placa, cor);
        this.nomeVeiculo = nomeVeiculo;
        this.taxaViagem = 0;//economico nao possuira taxa
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
        return "Economico {"+ nomeVeiculo + " " + ano + " " + super.toString()  +   "}";
    }

   
    

    
}
