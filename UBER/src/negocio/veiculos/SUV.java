package negocio.veiculos;

public class SUV extends Veiculo {//ver isso
    private static final long serialVersionUID = 200001L;
    private final double taxaViagem = 1.5;

    public SUV(String placa, String cor, int ano, String nomeVeiculo) {
        super(placa, cor, ano, nomeVeiculo);
    }

    

    public double getTaxaViagem() {
        return taxaViagem;
    }

    @Override//VER NO QUE ESTE OVERRIDE DA
    public String toString() {
        return "SUV {" + super.toString()  + this.getTaxaViagem() +"}";
    }
    
}
