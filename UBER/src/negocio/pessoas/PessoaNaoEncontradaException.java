package negocio.pessoas;

public class PessoaNaoEncontradaException extends Exception {
    public PessoaNaoEncontradaException(int IDPessoa) {
        super("Pessoa com ID " + IDPessoa + " não encontrada.");
    }
}
