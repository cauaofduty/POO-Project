package dados;

import java.util.ArrayList;
import negocio.localizacao.Viagem;

public interface IRepositorioViagem<V extends Viagem> {
    void adicionar(V viagem);
    // Poderia ser implementado um método para buscar uma viagem por ID ou outro critério, se necessário.a viagem ja esta no historico do cliente. motorista, assim como pagamentos
    // V buscarPorID(int idViagem) throws ViagemNaoEncontradaException;
    ArrayList<V> listarViagensCliente(String IDPessoa);//creio nao necessitar dessa separação mas ok
    ArrayList<V> listarViagensMotorista(String IDPessoa);//creio nao necessitar dessa separação mas ok
}
