package negocio.financeiro;

import java.util.Objects;
import negocio.exceptions.PagamentoNegadoException;
import negocio.pessoas.Pessoa;

public class Cartao extends FormaDePagamento {
    private static final long serialVersionUID = 400002L;
    private double limiteCartao; 
    private final String numeroCartao;
    private final String tipo = "Cartão";


    public Cartao(double limiteCartao, String numeroCartao) {
        this.limiteCartao = limiteCartao;
        this.numeroCartao = numeroCartao;
    }


    @Override
    public void pagar(double valor) throws Exception {
        if(valor > this.limiteCartao) throw new PagamentoNegadoException("Pagamento negado.", tipo);
        this.limiteCartao -= valor;
    }

    //TERMINAR
    @Override
    public void registrarPagamento(Pessoa cliente, Pessoa motorista, double valor) {
        throw new UnsupportedOperationException("Unimplemented method 'registrarPagamento'");
    }

    @Override
    public String getTipo() {
        return this.tipo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cartao cartao = (Cartao) obj;
        return numeroCartao.equals(cartao.numeroCartao); // considera iguais se o número do cartão for igual
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroCartao);
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

    @Override
    public String toString() {
    return "Tipo: " + tipo +
           " | Número: " + numeroCartao +
           " | Limite: R$ " + String.format("%.2f", limiteCartao);
}
}
