package negocio.veiculos;

public class Economico extends Veiculo {
    private static final long serialVersionUID = 200004L;
    private final double taxaViagem = 0;
    private final String tipo = "Econômico"; //caso necessite identificar o tipo de veiculo, pode ser utilizado no repositorio de veiculos

    
    public Economico(String placa, String cor, String nomeVeiculo, int ano) {
        super(placa, cor, nomeVeiculo, ano);                   
    }


    @Override
    public double getTaxaViagem() {
        return taxaViagem;
    }

    @Override
    public String getTipo() {
        return tipo;
    }
    
    @Override//VER NO QUE ESTE OVERRIDE DA
    public String toString(){
        return super.toString();
    }

    
}
