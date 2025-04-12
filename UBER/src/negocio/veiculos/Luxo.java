package negocio.veiculos;

public class Luxo extends Veiculo {
    private static final long serialVersionUID = 200003L;
    private final double taxaViagem = 2.0;;
    private final String tipo = "Luxo"; //caso necessite identificar o tipo de veiculo, pode ser utilizado no repositorio de veiculos
    

    public Luxo(String placa, String cor, String nomeVeiculo, int ano) {
        super(placa, cor, nomeVeiculo, ano);
    }


    @Override
    public double getTaxaViagem() {
        return taxaViagem;
    }

    public String getTipo() {
        return tipo;
    }
    @Override
    public String toString() {
        return super.toString();
    }
    
}
