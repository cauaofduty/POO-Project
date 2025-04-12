package dados;

import java.io.*;
import java.util.ArrayList;
import negocio.veiculos.Veiculo;

public class RepositorioVeiculoArquivo implements IRepositorioVeiculo<Veiculo> {
    private final String arquivo = "veiculos.bin";
    private final ArrayList<Veiculo> veiculos;

    public RepositorioVeiculoArquivo() {
        this.veiculos = carregarArquivo();
    }

    // Funções declaradas na classe IRepositorioVeiculo

    @Override
    public void adicionar(Veiculo veiculo) {
        veiculos.add(veiculo);
        salvarArquivo();
    }

    @Override
    public Veiculo buscarPorPlaca(String placa) {
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return v;
            }
        }
        return null;
    }
    
    @Override
    public ArrayList<Veiculo> listarVeiculos() {
        return new ArrayList<>(veiculos);
    }

    // Funções para persistência de dados

    private void salvarArquivo() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(veiculos);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private ArrayList<Veiculo> carregarArquivo() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (ArrayList<Veiculo>) in.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}