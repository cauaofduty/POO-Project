package dados;

import java.util.List;
import negocio.veiculos.Veiculo;

public interface IRepositorioVeiculo<V extends Veiculo> {
    void adicionar(V veiculo);
    V buscarPorPlaca(String placa);
    List<V> listarVeiculos();
}
