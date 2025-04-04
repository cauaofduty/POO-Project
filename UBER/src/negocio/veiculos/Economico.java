package negocio.veiculos;

public class Economico extends Veiculo{
    private final double taxaViagem;

    public Economico(String placa, String cor, int ano, String nomeVeiculo) {
        super(placa, cor, ano, nomeVeiculo);
        this.taxaViagem = 0;
    }
    
    

    public double getTaxaViagem() {
        return taxaViagem;
    }


    @Override//VER NO QUE ESTE OVERRIDE DA
    public String toString(){
        return "Economico {" + super.toString()  + this.getTaxaViagem() +"}";
    }

   
    

    
}
