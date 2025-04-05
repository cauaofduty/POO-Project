package dados;

import java.util.List;

import negocio.pessoas.Pessoa;
import negocio.pessoas.Cliente;
import negocio.pessoas.Motorista;
import negocio.pessoas.PessoaNaoEncontradaException;

public interface IRepositorioPessoa <P extends Pessoa> {
    void adicionar(P pessoa);
    P buscarPorID(int idPessoa) throws PessoaNaoEncontradaException;
    List<Cliente> listarClientes();
    List<Motorista> listarMotoristas();
}
