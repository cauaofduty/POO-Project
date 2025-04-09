package negocio.exceptions;

public class EntidadeJaExisteException extends Exception {
    public EntidadeJaExisteException(String mensagem) {
        super(mensagem);
    }
}
