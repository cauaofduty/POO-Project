package negocio;

import negocio.modelos.FormaDePagamento;

import java.io.Serializable;

public class Cartao implements FormaDePagamento, Serializable {
    private static final long serialVersionUID = 300L;
    private double limiteCartao; 
    private final String numeroCartao;
    private final String cVV;
    private final String senhaCartao;


    public Cartao(double limiteCartao, String numeroCartao, String cVV, String senhaCartao) {
        this.limiteCartao = limiteCartao;
        this.numeroCartao = numeroCartao;
        this.cVV = cVV;
        this.senhaCartao = senhaCartao;
    }


    @Override
    public void pagar(double valor) throws Exception {
        if(valor > this.limiteCartao)
        throw new UnsupportedOperationException("");
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
    
    
}
