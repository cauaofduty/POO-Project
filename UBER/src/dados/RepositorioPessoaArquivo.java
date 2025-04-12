package dados;

import java.io.*;
import java.util.ArrayList;
import negocio.pessoas.Cliente;
import negocio.pessoas.Motorista;
import negocio.pessoas.Pessoa;

public class RepositorioPessoaArquivo implements IRepositorioPessoa<Pessoa> {
    private final String arquivo = "pessoas.bin";
    private final ArrayList<Pessoa> pessoas;//mudei pois usei no codigo todo este tipo

    public RepositorioPessoaArquivo() {
        this.pessoas = carregarArquivo();
    }
    @Override
    public void adicionar(Pessoa pessoa) {
        pessoas.add(pessoa);
        salvarArquivo();
    }
    @Override
    public Pessoa buscarPorID(String IDPessoa) {
        for (Pessoa p : pessoas) {
            if (p.getIDPessoa().equals(IDPessoa)) {
                return p;
            }
        }
        return null;
    }
    @Override
    public ArrayList<Cliente> listarClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        for (Pessoa p : pessoas) {
            if (p instanceof Cliente cliente) {
                clientes.add(cliente);

            }
        }
        return clientes;
    }

    @Override
    public ArrayList<Motorista> listarMotoristas() {
        ArrayList<Motorista> motoristas = new ArrayList<>();
        for (Pessoa p : pessoas) {
            if (p instanceof Motorista motorista) {
                motoristas.add(motorista);
            }
        }
        return motoristas;
    }

    private void salvarArquivo() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(pessoas);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private ArrayList<Pessoa> carregarArquivo() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (ArrayList<Pessoa>) in.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public ArrayList<Pessoa> getPessoas() {//precisei para adicao de certos atributos (id por exemplo)
        return pessoas;
    }
}
