package dados;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import negocio.localizacao.Viagem;

public class RepositorioViagemArquivo implements IRepositorioViagem<Viagem> {
    private final String arquivo = "viagens.bin";
    private final ArrayList<Viagem> viagens;

    public RepositorioViagemArquivo() {
        this.viagens = carregarArquivo();
    }

    @Override
    public void adicionar(Viagem viagem) {
        viagens.add(viagem);
        salvarArquivo();
    }

    @Override
    public ArrayList<Viagem> listarViagensCliente(String idCliente) {//como so é chamado no menuLogado, não necessita lançar exception
        ArrayList<Viagem> viagemCliente = new ArrayList<>();
        for (Viagem v : viagens) { 
            if ((v.getCliente().getIDPessoa()).equals(idCliente)) {
                viagemCliente.add(v);
            }
        }
        return viagemCliente;
    }

    @Override
    public ArrayList<Viagem> listarViagensMotorista(String IDPessoa) {
        ArrayList<Viagem> viagemMotorista = new ArrayList<>();
        for (Viagem v : viagens) {
            if (v.getMotorista().getIDPessoa().equals(IDPessoa)) {
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
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
