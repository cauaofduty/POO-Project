package dados;

import java.util.List;
import negocio.pessoas.*;

public interface IRepositorioPessoa <P extends Pessoa> {
    void adicionar(Pessoa pessoa);
    Pessoa buscarPorID(String IDPessoa);
    List<Cliente> listarClientes();
    List<Motorista> listarMotoristas();
}
