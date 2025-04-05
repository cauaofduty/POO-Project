package negocio.financeiro;

import negocio.pessoas.Pessoa;

import java.io.Serializable;

public class Cartao implements FormaDePagamento, Serializable {
    private static final long serialVersionUID = 300L;
    private double limiteCartao; 
    private final String numeroCartao;
    private final String cVV;
    private final String tipo = "Cartão";


    public Cartao(double limiteCartao, String numeroCartao, String cVV) {
        this.limiteCartao = limiteCartao;
        this.numeroCartao = numeroCartao;
        this.cVV = cVV;
    }


    @Override
    public void pagar(double valor) throws Exception {
        if(valor > this.limiteCartao) throw new PagamentoNegadoException("pagamento negado.",tipo);
        this.limiteCartao -= valor;
    }

    public double getLimiteCartao() {
        return limiteCartao;
    }


    public void setLimiteCartao(double limiteCartao) {
        this.limiteCartao = limiteCartao;
    }


    public String getNumeroCartao() {
        return numeroCartao;
    }


    public String getcVV() {
        return cVV;
    }



    @Override
    public String getTipo() {
        return this.tipo;
    }

    //TERMINAR
    @Override
    public void registrarPagamento(Pessoa cliente, Pessoa motorista, double valor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registrarPagamento'");
    }
    
    
}
