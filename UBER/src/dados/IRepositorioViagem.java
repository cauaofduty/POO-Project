package dados;

import java.util.List;
import negocio.localizacao.Viagem;

public interface IRepositorioViagem<V extends Viagem> {
    void adicionar(V viagem);
    List<V> listarViagensCliente(int idCliente);
    List<V> listarViagensMotorista(int idMotorista);
}
