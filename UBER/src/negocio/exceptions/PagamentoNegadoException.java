package negocio.exceptions;

public class PagamentoNegadoException extends Exception{
    private final String tipo;

    public PagamentoNegadoException(String message, String tipo){
        super(message);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
