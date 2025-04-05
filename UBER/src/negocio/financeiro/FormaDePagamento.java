package negocio.financeiro;
import negocio.pessoas.Pessoa;

public interface FormaDePagamento {
    void pagar(double valor) throws Exception;
    void registrarPagamento(Pessoa cliente, Pessoa motorista, double valor);
    String getTipo();
}
