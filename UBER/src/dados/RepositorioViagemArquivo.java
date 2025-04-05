package dados;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import negocio.localizacao.Viagem;

public class RepositorioViagemArquivo implements IRepositorioViagem<Viagem> {
    private final String arquivo = "viagens.bin";
    private final List<Viagem> viagens;

    public RepositorioViagemArquivo() {
        this.viagens = carregarArquivo();
    }

    @Override
    public void adicionar(Viagem viagem) {
        viagens.add(viagem);
        salvarArquivo();
    }

    @Override
    public List<Viagem> listarViagensCliente(int idCliente) {
        List<Viagem> viagemCliente = new ArrayList<>();
        for (Viagem v : viagens) { 
            if (v.getCliente().getIDPessoa() == idCliente) {
                viagemCliente.add(v);
            }
        }
        return viagemCliente;
    }

    @Override
    public List<Viagem> listarViagensMotorista(int idMotorista) {
        List<Viagem> viagemMotorista = new ArrayList<>();
        for (Viagem v : viagens) {
            if (v.getMotorista().getIDPessoa() == idMotorista) {
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
    
    private List<Viagem> carregarArquivo() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Viagem>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
