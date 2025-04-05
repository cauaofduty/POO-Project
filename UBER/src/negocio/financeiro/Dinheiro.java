package negocio.financeiro;

import negocio.pessoas.Pessoa;

public class Dinheiro implements FormaDePagamento {
    private double saldo;
    private final String tipo = "Dinheiro";

    public Dinheiro(double saldo){
        this.saldo = saldo;
    }
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }


    @Override
        public void pagar(double valor) throws Exception {
            if(valor > saldo) throw new PagamentoNegadoException("pagamento recusado.", tipo);
            this.saldo -= valor; 
        }

    @Override
    public String getTipo() {
        throw new UnsupportedOperationException("Unimplemented method 'getTipo'");
    }

    //TERMINAR
    @Override
    public void registrarPagamento(Pessoa cliente, Pessoa motorista, double valor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
