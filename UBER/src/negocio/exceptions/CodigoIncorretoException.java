package negocio.exceptions;

public class CodigoIncorretoException extends Exception {
    public CodigoIncorretoException() {
        super("Código incorreto.");
    }

    public CodigoIncorretoException(String mensagem) {
        super(mensagem);
    }
}
