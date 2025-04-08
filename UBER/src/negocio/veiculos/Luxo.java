package negocio.veiculos;

public class Luxo extends Veiculo {
    private static final long serialVersionUID = 200003L;
    private final double taxaViagem = 2.0;;
    private final String tipo = "luxo";//caso necessite identificar o tipo de veiculo, pode ser utilizado no repositorio de veiculos
    

    public Luxo(String placa, String cor, String nomeVeiculo, int ano) {
        super(placa, cor, nomeVeiculo, ano);
    }

    public double getTaxaViagem() {
        return taxaViagem;
    }

    @Override//VER NO QUE ESTE OVERRIDE DA
    public String toString() {
        return "Luxo {" + super.toString()  + this.getTaxaViagem() +"}";
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getTipo() {
        return tipo;
    }
}
