package dados;

import java.util.ArrayList;
import negocio.localizacao.Viagem;

public interface IRepositorioViagem<V extends Viagem> {
    void adicionar(V viagem);
    ArrayList<V> listarViagensCliente(String IDPessoa);
    ArrayList<V> listarViagensMotorista(String IDPessoa);
}
