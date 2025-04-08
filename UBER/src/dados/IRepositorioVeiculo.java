package dados;

import java.util.List;

import negocio.exceptions.PlacaNaoEncontradaException;
import negocio.veiculos.Veiculo;

public interface IRepositorioVeiculo<V extends Veiculo> {
    void adicionar(V veiculo);
    V buscarPorPlaca(String placa) throws PlacaNaoEncontradaException;
    List<V> listarVeiculos();
}
