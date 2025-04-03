package negocio.modelos;

public interface FormaDePagamento {
    void pagar(double valor) throws Exception;
    String getTipo();
}
