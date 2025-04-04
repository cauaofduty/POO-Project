package negocio.veiculos;

public class SUV extends Veiculo{//ver isso
    private final double taxaViagem;

    public SUV(String placa, String cor, int ano, String nomeVeiculo, double TaxaViagem) {
        super(placa, cor, ano, nomeVeiculo);
        this.taxaViagem = 1.5;
    }

    

    public double getTaxaViagem() {
        return taxaViagem;
    }

    @Override//VER NO QUE ESTE OVERRIDE DA
    public String toString() {
        return "SUV {" + super.toString()  + this.getTaxaViagem() +"}";
    }
    
}
