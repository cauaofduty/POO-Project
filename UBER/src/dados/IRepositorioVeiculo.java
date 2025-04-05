package dados;

import java.util.List;

import negocio.veiculos.Veiculo;
import negocio.pessoas.PlacaNaoEncontradaException;

public interface IRepositorioVeiculo<V extends Veiculo> {
    void adicionar(V veiculo);
    V buscarPorPlaca(String placa) throws PlacaNaoEncontradaException;
    List<V> listarVeiculos();
}
