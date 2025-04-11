package negocio.veiculos;

public class Economico extends Veiculo {
    private static final long serialVersionUID = 200004L;
    private final double taxaViagem = 0;
    private final String tipo = "economico"; //caso necessite identificar o tipo de veiculo, pode ser utilizado no repositorio de veiculos

    
    public Economico(String placa, String cor, String nomeVeiculo, int ano) {
        super(placa, cor, nomeVeiculo, ano);                   
    }


    @Override
    public double getTaxaViagem() {
        return taxaViagem;
    }

    public String getTipo() {
        return tipo;
    }

    public String toString() {
        return "Economico {" + super.toString()  + this.getTaxaViagem() +"}";
    }
    
}
