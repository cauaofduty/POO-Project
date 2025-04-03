package negocio.financeiro;

import negocio.modelos.FormaDePagamento;

public class Cartao implements FormaDePagamento{
    private double limiteCartao; 
    private final String numeroCartao;
    private final String cVV;
    private final String senhaCartao;
    private final String tipo = "Cartão";


    public Cartao(double limiteCartao, String numeroCartao, String cVV, String senhaCartao) {
        this.limiteCartao = limiteCartao;
        this.numeroCartao = numeroCartao;
        this.cVV = cVV;
        this.senhaCartao = senhaCartao;
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


    public String getSenhaCartao() {
        return senhaCartao;
    }


    @Override
    public String getTipo() {
        return this.tipo;
    }
    
    
}
