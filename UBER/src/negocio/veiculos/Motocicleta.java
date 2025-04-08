package negocio.veiculos;

public  class Motocicleta extends Veiculo {
    private static final long serialVersionUID = 200002L;
    private final double taxaViagem = -0.2;
    private final String tipo = "motocicleta";//caso necessite identificar o tipo de veiculo, pode ser utilizado no repositorio de veiculos
   
    public Motocicleta(String placa, String cor, String nomeVeiculo, int ano) {
        super(placa, cor, nomeVeiculo, ano);
    }

    public double getTaxaViagem() {
        return taxaViagem;
    }

    @Override//VER NO QUE ESTE OVERRIDE DA
    public String toString() {
        return "Motocicleta {" + super.toString()  + this.getTaxaViagem() +"}";
    }

    public String getTipo() {
        return tipo;
    }
}
