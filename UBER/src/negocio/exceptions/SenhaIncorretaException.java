package negocio.exceptions;

public class SenhaIncorretaException extends Exception {
    public SenhaIncorretaException() {
        super("Senha incorreta.");
    }

    public SenhaIncorretaException(String mensagem) {
        super(mensagem);
    }
}