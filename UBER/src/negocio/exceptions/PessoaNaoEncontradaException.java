package negocio.exceptions;

public class PessoaNaoEncontradaException extends Exception {//usada para busca, talvez login
    public PessoaNaoEncontradaException(String IDPessoa) {
        super(" Pessoa com ID " + IDPessoa + " não encontrada.");
    }
}
