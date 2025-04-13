package negocio.veiculos;

public class SUV extends Veiculo {//ver isso
    private static final long serialVersionUID = 200001L;
    private final double taxaViagem = 1.5;
    private final String tipo = "SUV"; //caso necessite identificar o tipo de veiculo, pode ser utilizado no repositorio de veiculos
   
    
    public SUV(String placa, String cor, String nomeVeiculo, int ano) {
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

    @Override
    public String toString() {
        return super.toString();
    }
    
}
