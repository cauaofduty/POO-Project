package negocio.veiculos;

public  class Motocicleta extends Veiculo{
    private final double taxaViagem = -0.2;

    public Motocicleta(String placa, String cor, int ano, String nomeVeiculo) {
        super(placa, cor, ano, nomeVeiculo);
    }

    public double getTaxaViagem() {
        return taxaViagem;
    }

    @Override//VER NO QUE ESTE OVERRIDE DA
    public String toString() {
        return "Motocicleta {" + super.toString()  + this.getTaxaViagem() +"}";
    }
}
