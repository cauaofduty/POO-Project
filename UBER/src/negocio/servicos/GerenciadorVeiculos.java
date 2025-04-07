package negocio.servicos;

import dados.IRepositorioVeiculo;
import dados.RepositorioVeiculoArquivo;
import negocio.veiculos.Veiculo;

public class GerenciadorVeiculos {
    private IRepositorioVeiculo<Veiculo> repoVeiculo;
    

    public GerenciadorVeiculos() {
        this.repoVeiculo = new RepositorioVeiculoArquivo();
    }
}
