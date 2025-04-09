package dados;

import java.util.List;

import negocio.pessoas.Cliente;
import negocio.pessoas.Motorista;
import negocio.pessoas.Pessoa;

public interface IRepositorioPessoa <P extends Pessoa> {
    void adicionar(Pessoa pessoa);//mesmo efeito
    Pessoa buscarPorID(String IDPessoa);
    List<Cliente> listarClientes();
    List<Motorista> listarMotoristas();
}
