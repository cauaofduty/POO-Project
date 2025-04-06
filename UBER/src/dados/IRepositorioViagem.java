package dados;

import java.util.List;

import negocio.localizacao.Viagem;

public interface IRepositorioViagem<V extends Viagem> {
    void adicionar(V viagem);
    // Poderia ser implementado um método para buscar uma viagem por ID ou outro critério, se necessário.
    // V buscarPorID(int idViagem) throws ViagemNaoEncontradaException;
    List<V> listarViagensCliente(int idCliente);
    List<V> listarViagensMotorista(int idMotorista);
}
