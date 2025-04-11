package dados;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import negocio.localizacao.Viagem;
import negocio.pessoas.Pessoa;

public class RepositorioViagemArquivo implements IRepositorioViagem<Viagem> {
    private final String arquivo = "viagens.bin";
    private List<Viagem> viagens;

    public RepositorioViagemArquivo() {
        this.viagens = carregarArquivo();
    }

    public void adicionar(Viagem viagem) {
        viagens.add(viagem);
        salvarArquivo();
    }

    public List<Viagem> listarViagensCliente(int idCliente) {
        List<Viagem> viagemCliente = new ArrayList<>();
        for (Viagem v : viagens) { 
            if (Integer.parseInt(v.getCliente().getIDPessoa()) == idCliente) {
                viagemCliente.add(v);
            }
        }
        return viagemCliente;
    }

    public List<Viagem> listarViagensMotorista(int idMotorista) {
        List<Viagem> viagemMotorista = new ArrayList<>();
        for (Viagem v : viagens) {
            if (Integer.parseInt(v.getMotorista().getIDPessoa()) == idMotorista) {
                viagemMotorista.add(v);
            }
        }
        return viagemMotorista;
    }

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
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
