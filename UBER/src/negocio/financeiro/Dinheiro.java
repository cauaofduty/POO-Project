package negocio.financeiro;

import negocio.exceptions.PagamentoNegadoException;
import negocio.pessoas.Pessoa;

public class Dinheiro extends FormaDePagamento{
    private static final long serialVersionUID = 400001L;
    private double saldo;
    private final String tipo = "Dinheiro";


    public Dinheiro(double saldo) {
        this.saldo = saldo;
    }


    @Override
    public void pagar(double valor) throws Exception {
        if(valor > saldo) throw new PagamentoNegadoException("Pagamento recusado.", tipo);
        this.saldo -= valor; 
    }

    //TERMINAR
    @Override
    public void registrarPagamento(Pessoa cliente, Pessoa motorista, double valor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getTipo() {
        return this.tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
