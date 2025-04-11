package dados;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
    public Veiculo buscarPorPlaca(String placa) { //removi o throws pois estava dando conflito com outra exception
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return v;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Veiculo> listarVeiculos() {//apenas para o adm, o motorista so precisa ver seus veiculos
        return new ArrayList<>(veiculos);
    }

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
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
