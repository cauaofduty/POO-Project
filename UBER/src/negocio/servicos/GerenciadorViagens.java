package negocio.servicos;

import dados.IRepositorioViagem;
import dados.RepositorioViagemArquivo;
import negocio.localizacao.Viagem;

public class GerenciadorViagens {
    private IRepositorioViagem<Viagem> repoViagem;

    public GerenciadorViagens() {
        this.repoViagem = new RepositorioViagemArquivo();
    }
}
