package dados;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import negocio.pessoas.Cliente;
import negocio.pessoas.Motorista;
import negocio.pessoas.Pessoa;
import negocio.pessoas.PessoaNaoEncontradaException;

public class RepositorioPessoaArquivo implements IRepositorioPessoa<Pessoa> {
    private final String arquivo = "pessoas.bin";
    private final List<Pessoa> pessoas;

    public RepositorioPessoaArquivo() {
        this.pessoas = carregarArquivo();
    }

    public void adicionar(Pessoa pessoa) {
        pessoas.add(pessoa);
        salvarArquivo();
    }

    public Pessoa buscarPorID(int idPessoa) throws PessoaNaoEncontradaException {
        for (Pessoa p : pessoas) {
            if (p.getIDPessoa() == idPessoa) {
                return p;
            }
        }
        throw new PessoaNaoEncontradaException(idPessoa);
    }

    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        for (Pessoa p : pessoas) {
            if (p instanceof Cliente) {
                clientes.add((Cliente) p);

            }
        }
        return clientes;
    }

    public List<Motorista> listarMotoristas() {
        List<Motorista> motoristas = new ArrayList<>();
        for (Pessoa p : pessoas) {
            if (p instanceof Motorista) {
                motoristas.add((Motorista) p);
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
    private List<Pessoa> carregarArquivo() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Pessoa>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
