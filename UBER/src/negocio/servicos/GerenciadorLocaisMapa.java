package negocio.servicos;
import java.util.ArrayList;
import negocio.localizacao.Local;

public class GerenciadorLocaisMapa {
    private ArrayList<Local> locaisPessoasDisponiveis;//localizacoes para motorista e cliente

    public GerenciadorLocaisMapa(ArrayList<Local> locaisPessoasDisponiveis) {
        this.locaisPessoasDisponiveis = locaisPessoasDisponiveis;
    }

    public ArrayList<Local> getLocais() {
        return locaisPessoasDisponiveis;
    }



}
