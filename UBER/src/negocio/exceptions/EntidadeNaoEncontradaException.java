package negocio.exceptions;

public class EntidadeNaoEncontradaException extends Exception {//usada para busca, talvez login
    public EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
