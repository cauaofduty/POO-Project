package negocio.veiculos;

public class Economico extends Veiculo {
    private static final long serialVersionUID = 200004L;
    private final double taxaViagem = 0;

    public Economico(String placa, String cor, int ano, String nomeVeiculo) {
        super(placa, cor, ano, nomeVeiculo);
    }

    public double getTaxaViagem() {
        return taxaViagem;
    }

    @Override//VER NO QUE ESTE OVERRIDE DA
    public String toString(){
        return "Economico {" + super.toString()  + this.getTaxaViagem() +"}";
    }

   
    

    
}
