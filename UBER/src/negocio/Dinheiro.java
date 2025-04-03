package negocio;
import negocio.modelos.FormaDePagamento;

public class Dinheiro implements FormaDePagamento {
    private double saldo;

    public Dinheiro(double saldo){
        this.saldo = saldo;
    }
    
    
    
    
    @Override
    public void pagar(double valor) throws Exception {
        if(valor > saldo) throw new UnsupportedOperationException("Unimplemented method 'pagar'");
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
