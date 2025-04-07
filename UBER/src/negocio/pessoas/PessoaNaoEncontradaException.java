package negocio.pessoas;

public class PessoaNaoEncontradaException extends Exception {
    public PessoaNaoEncontradaException(String IDPessoa) {
        super("Pessoa com ID " + IDPessoa + " não encontrada.");
    }
}
