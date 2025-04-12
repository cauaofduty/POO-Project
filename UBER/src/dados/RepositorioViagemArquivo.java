package dados;

import java.io.*;
import java.util.ArrayList;
import negocio.localizacao.Viagem;

public class RepositorioViagemArquivo implements IRepositorioViagem<Viagem> {
    private final String arquivo = "viagens.bin";
    private final ArrayList<Viagem> viagens;


    public RepositorioViagemArquivo() {
        this.viagens = carregarArquivo();
    }


    // Funções declaradas na classe IRepositorioViagem

    @Override
    public void adicionar(Viagem viagem) {
        viagens.add(viagem);
        salvarArquivo();
    }

    @Override
    public ArrayList<Viagem> listarViagensCliente(int idCliente) {
        ArrayList<Viagem> viagemCliente = new ArrayList<>();
        for (Viagem v : viagens) { 
            if ((v.getCliente().getIDPessoa()).equals(idCliente)) {
                viagemCliente.add(v);
            }
        }
        return viagemCliente;
    }

    @Override
    public ArrayList<Viagem> listarViagensMotorista(int idMotorista) {
        ArrayList<Viagem> viagemMotorista = new ArrayList<>();
        for (Viagem v : viagens) {
            if (v.getMotorista().getIDPessoa().equals(idMotorista)) {
                viagemMotorista.add(v);
            }
        }
        return viagemMotorista;
    }

    // Funções para persistência de dados

    private void salvarArquivo() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(viagens);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }
  
    @SuppressWarnings("unchecked")
    private ArrayList<Viagem> carregarArquivo() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (ArrayList<Viagem>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
