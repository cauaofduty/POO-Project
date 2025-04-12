package negocio.financeiro;

import java.io.Serializable;
import negocio.pessoas.Pessoa;

public abstract class FormaDePagamento implements Serializable {
    static final long serialVersionUID = 400000L;
    
    public abstract void pagar(double valor) throws Exception;
    public abstract void registrarPagamento(Pessoa cliente, Pessoa motorista, double valor);
    public abstract String getTipo();
}
