package negocio.servicos;

import dados.IRepositorioPessoa;
import dados.RepositorioPessoaArquivo;
import negocio.pessoas.Pessoa;

public class GerenciadorPessoa {
    private IRepositorioPessoa<Pessoa> repoPessoa;

    public GerenciadorPessoa() {
        this.repoPessoa = new RepositorioPessoaArquivo();
    }
}
