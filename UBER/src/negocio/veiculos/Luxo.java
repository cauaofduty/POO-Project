package negocio.veiculos;

public class Luxo extends Veiculo {
    private final double taxaViagem = 2.0;;

    public Luxo(String placa, String cor, int ano, String nomeVeiculo) {
        super(placa, cor, ano, nomeVeiculo);
    }

    public double getTaxaViagem() {
        return taxaViagem;
    }

    @Override//VER NO QUE ESTE OVERRIDE DA
    public String toString() {
        return "Luxo {" + super.toString()  + this.getTaxaViagem() +"}";
    }
}
