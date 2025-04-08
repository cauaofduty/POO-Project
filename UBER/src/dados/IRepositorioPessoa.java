package dados;

import java.util.List;

import negocio.exceptions.PessoaNaoEncontradaException;
import negocio.pessoas.Cliente;
import negocio.pessoas.Motorista;
import negocio.pessoas.Pessoa;

public interface IRepositorioPessoa <P extends Pessoa> {
    void adicionar(Pessoa pessoa);//mesmo efeito
    Pessoa buscarPorID(String IDPessoa) throws PessoaNaoEncontradaException;//mudei para mesmo nome do atributo
    List<Cliente> listarClientes();
    List<Motorista> listarMotoristas();
}
