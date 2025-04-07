package negocio.servicos;

import dados.IRepositorioViagem;
import dados.RepositorioViagemArquivo;
import negocio.localizacao.Viagem;

public class GerenciadorViagens {
    private final IRepositorioViagem<Viagem> repoViagem;

    public GerenciadorViagens() {
        this.repoViagem = new RepositorioViagemArquivo();
    }

    public IRepositorioViagem<Viagem> getRepoViagem(){
        return repoViagem;
    }

    public void registrarViagem(){
        
    }
}
