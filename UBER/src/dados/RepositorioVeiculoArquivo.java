package dados;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import negocio.pessoas.PlacaNaoEncontradaException;
import negocio.veiculos.Veiculo;

public class RepositorioVeiculoArquivo implements IRepositorioVeiculo<Veiculo> {
    private final String arquivo = "veiculos.bin";
    private final List<Veiculo> veiculos;

    public RepositorioVeiculoArquivo() {
        this.veiculos = carregarArquivo();
    }

    @Override
    public void adicionar(Veiculo veiculo) {
        veiculos.add(veiculo);
        salvarArquivo();
    }
    
    @Override
    public Veiculo buscarPorPlaca(String placa) throws PlacaNaoEncontradaException {
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return v;
            }
        }
        throw new PlacaNaoEncontradaException(placa);
    }

    @Override
    public List<Veiculo> listarVeiculos() {
        return new ArrayList<>(veiculos);
    }

    private void salvarArquivo() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(veiculos);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    private List<Veiculo> carregarArquivo() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Veiculo>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
